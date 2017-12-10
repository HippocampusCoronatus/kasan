/* Copyright © 2017- Kasan All Rights Reserved. */
package jp.co.kasan.journal;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.transaction.Transactional;
import jp.co.kasan.db.entity.MAccountBook;
import jp.co.kasan.db.entity.MAccountTitle;
import jp.co.kasan.db.entity.MAccountTitleItem;
import jp.co.kasan.db.entity.pk.MAccountTitleItemPK;
import jp.co.kasan.journal.ledger.GeneralLedger;
import jp.co.kasan.journal.ledger.Ledger;
import jp.co.kasan.journal.type.SystemAccountTitle;
import jp.co.kasan.web.auth.Authorized;

/**
 * ローンに関するサービスを提供します。
 * @author rued97
 */
@Dependent
public class LoanService {

	/** 会計帳簿 */
	@Inject @Authorized
	private MAccountBook MembersBook;
	/** 仕訳人 */
	@Inject
	private JournalHandler JournalHandler;
	@Inject
	private EntityManager EM;

	/** 貸付金の勘定科目 */
	private MAccountTitle LendAccountTitle;
	/** 借入金の勘定科目 */
	private MAccountTitle BorrowAccountTitle;

	@Transactional
	public void enter(Long no, String name, long lendAmmount, long borrowAmmount) {
		// 会計簿を変更するため全体をロックしておく。
		this.EM.lock(this.MembersBook, LockModeType.PESSIMISTIC_WRITE);

		List<Loan> loans = this.getLoans();
		if(no == null) {
			// 新規の取引相手なので新たな品目を追加する。
			long maxNo = loans.stream()
					.map(Loan::getNo)
					.max(Comparator.naturalOrder())
					.get();
			long itemNo = maxNo + 1;
			// 貸付金・借入金で同一の取引相手が同一の番号となるように登録する。
			// TODO:この辺は後々別サービスに委任したい。。。
			{
				MAccountTitleItemPK pk = new MAccountTitleItemPK();
				pk.setAccountBookNo(this.MembersBook.getNo());
				pk.setAccountTitleCode(SystemAccountTitle.貸付金.getCode());
				pk.setNo(itemNo);

				MAccountTitleItem item = new MAccountTitleItem();
				item.setMAccountTitleItemPK(pk);
				item.setName(name);
			}
			{
				MAccountTitleItemPK pk = new MAccountTitleItemPK();
				pk.setAccountBookNo(this.MembersBook.getNo());
				pk.setAccountTitleCode(SystemAccountTitle.借入金.getCode());
				pk.setNo(itemNo);

				MAccountTitleItem item = new MAccountTitleItem();
				item.setMAccountTitleItemPK(pk);
				item.setName(name);
			}
		}

		// 取引を登録する。
		if(lendAmmount > 0) {
			this.JournalHandler.journalize(LocalDate.now())
					.credit(lendAmmount, SystemAccountTitle.貸付金.getCode())
						.next()
					.debit(lendAmmount,  SystemAccountTitle.現金.getCode())
						.item(lendAmmount, no)
						.build();
		}
		if(borrowAmmount > 0) {
			this.JournalHandler.journalize(LocalDate.now())
					.credit(borrowAmmount, SystemAccountTitle.現金.getCode())
						.item(lendAmmount, no)
						.next()
					.debit(borrowAmmount,  SystemAccountTitle.借入金.getCode())
						.build();
		}
		// TODO:貸付金と借入金の相殺取引をやる？
	}

	/**
	 * ローンの一覧を返します。
	 * @return ローンの一覧
	 */
	public List<Loan> getLoans() {
		GeneralLedger generalLedger = this.JournalHandler.postToLedger();
		Map<MAccountTitleItem, Ledger.Summary> lendSummaries = generalLedger.get(this.findLendAccountTitle()).summaries();
		Map<MAccountTitleItem, Ledger.Summary> borrowSummaries = generalLedger.get(this.findBorrowAccountTitle()).summaries();
		if(lendSummaries.size() != borrowSummaries.size()) {
			throw new IllegalStateException("貸付金・借入金の品目数が一致していません。");
		}
		return lendSummaries.entrySet().stream()
				.map(lendE -> {
					MAccountTitleItem item = lendE.getKey();
					if(borrowSummaries.containsKey(item) == false) {
						throw new IllegalStateException("貸付金・借入金の品目種が一致していません。");
					}
					Loan loan = new Loan();
					loan.No = item.getNo();
					loan.PartnerMemberName = item.getName();
					loan.LoanAmount = lendE.getValue().getBalanceAmmount() - borrowSummaries.get(item).getBalanceAmmount();
					return loan;
				})
				.sorted(Comparator.comparing(Loan::getNo, Comparator.naturalOrder()))
				.collect(Collectors.toList());
	}

	/**
	 * ローン情報を保持します。
	 */
	public static class Loan {
		/** ローン番号 */
		private long No;
		/** 取引相手会員名 */
		private String PartnerMemberName;
		/** 貸付金（融資額）　借りてる場合はマイナスになります。 */
		private long LoanAmount;

		/**
		 * ローン番号を取得します。
		 * @return ローン番号
		 */
		public long getNo() {
			return this.No;
		}

		/**
		 * 取引相手会員名を取得します。
		 * @return 会員名
		 */
		public String getPartnerMemberName() {
			return this.PartnerMemberName;
		}

		/**
		 * 貸付金（融資額）を取得します。
		 * 借りている場合はマイナスになります。
		 * @return 貸付金（融資額）
		 */
		public long getLoanAmount() {
			return this.LoanAmount;
		}

	}

	/**
	 * 貸付金用の勘定科目を探します。
	 * @return 
	 */
	private MAccountTitle findLendAccountTitle() {
		if(this.LendAccountTitle != null) {
			return this.LendAccountTitle;
		}
		this.LendAccountTitle = this.MembersBook.getMAccountTitleList().stream()
				.filter(title -> title.getCode().equals(SystemAccountTitle.貸付金.getCode()))
				.findFirst().orElse(null);
		return this.LendAccountTitle;
	}

	/**
	 * 借入金用の勘定科目を探します。
	 * @return 
	 */
	private MAccountTitle findBorrowAccountTitle() {
		if(this.BorrowAccountTitle != null) {
			return this.BorrowAccountTitle;
		}
		this.BorrowAccountTitle = this.MembersBook.getMAccountTitleList().stream()
				.filter(title -> title.getCode().equals(SystemAccountTitle.借入金.getCode()))
				.findFirst().orElse(null);
		return BorrowAccountTitle;
	}

}
