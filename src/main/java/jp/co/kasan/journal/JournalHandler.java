/* Copyright © 2017- Kasan All Rights Reserved. */
package jp.co.kasan.journal;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import jp.co.kasan.db.entity.MAccountBook;
import jp.co.kasan.db.entity.MAccountTitle;
import jp.co.kasan.db.entity.MAccountTitleItem;
import jp.co.kasan.db.entity.TJournal;
import jp.co.kasan.db.entity.TJournalDetail;
import jp.co.kasan.db.entity.TJournalDetailItem;
import jp.co.kasan.journal.ledger.GeneralLedger;
import jp.co.kasan.journal.type.DebitCreditType;
import jp.co.kasan.web.auth.Authorized;

/**
 * 仕訳を取り扱う人。
 * @author rued97
 */
@Dependent
public class JournalHandler {

	@Inject
	private EntityManager EM;
	/** 会計帳簿 */
	@Inject @Authorized
	private MAccountBook MembersBook;

	/**
	 * 仕訳します。
	 * @param date 取引日
	 * @return 仕訳のビルダ
	 */
	@Transactional
	public JournalBuilder journalize(LocalDate date) {
		return new JournalBuilder(date);
	}

	/**
	 * 総勘定元帳に転記します。
	 * @return 転記した総勘定元帳
	 */
	public GeneralLedger postToLedger() {
		return GeneralLedger.build(this.MembersBook.getTJournalList());
	}

	/**
	 * 仕訳のビルダ
	 */
	public class JournalBuilder {

		/** 会計帳簿 */
		private final MAccountBook MembersBook;
		/** 仕訳帳 */
		private final TJournal Journal;

		/** コンストラクタ */
		private JournalBuilder(LocalDate date) {
			this.MembersBook = JournalHandler.this.MembersBook;

			List<TJournal> journals = this.MembersBook.getTJournalList();
			long no = journals.stream()
					.map(TJournal::getNo)
					.max(Comparator.naturalOrder())
					.orElse(0L);
			this.Journal = new TJournal(this.MembersBook, no);
			this.Journal.setTJournalDetailList(new ArrayList<>());
			this.Journal.setTradingDate(date);
			journals.add(this.Journal);
		}

		/**
		 * 仕訳帳を確定します。
		 * @return 仕訳帳
		 */
		public TJournal build() {
			return this.Journal;
		}

		/**
		 * 貸方に記載します。
		 * @param ammount 金額
		 * @param code 勘定科目コード
		 * @return このオブジェクト
		 */
		public JournalDetailBuilder credit(long ammount, String code) {
			TJournalDetail detail = this.addDetail(ammount, code, DebitCreditType.Credit);
			return new JournalDetailBuilder(this, detail);
		}

		/**
		 * 借方に記載します。
		 * @param ammount 金額
		 * @param code 勘定科目コード
		 * @return このオブジェクト
		 */
		public JournalDetailBuilder debit(long ammount, String code) {
			TJournalDetail detail = this.addDetail(ammount, code, DebitCreditType.Debit);
			return new JournalDetailBuilder(this, detail);
		}

		/**
		 * 仕訳詳細を追加します。
		 * @param ammount 金額
		 * @param code 勘定科目コード
		 * @param type 記載する借方貸方
		 * @return 仕訳詳細
		 */
		private TJournalDetail addDetail(long ammount, String code, DebitCreditType type) {
			MAccountTitle title = this.findMAccountTitleBy(code);
			if(title == null) {
				throw new IllegalStateException("存在しない勘定科目コード[" + code + "]が指定されました。");
			}

			List<TJournalDetail> details = this.Journal.getTJournalDetailList();
			TJournalDetail detail = new TJournalDetail(this.Journal, type, details.size());
			detail.setMAccountTitle(title);
			detail.setAmount(ammount);
			detail.setTJournalDetailItemList(new ArrayList<>());

			details.add(detail);
			return detail;
		}

		/**
		 * 指定されたコードから勘定科目を取得します。
		 * @param code コード
		 * @return 勘定科目
		 */
		private MAccountTitle findMAccountTitleBy(String code) {
			return this.MembersBook.getMAccountTitleList().stream()
					.filter(title -> title.getCode().equals(code))
					.findFirst().orElse(null);
		}

		/**
		 * 品目のビルダ
		 */
		public class JournalDetailBuilder {

			/** 仕訳ビルダ */
			private final JournalBuilder ParentBuilder;
			/** 仕訳詳細 */
			private final TJournalDetail Detail;

			/**
			 * コンストラクタ
			 * @param builder 仕訳ビルダ
			 * @param detail 仕訳詳細
			 */
			private JournalDetailBuilder(JournalBuilder builder, TJournalDetail detail) {
				this.ParentBuilder = builder;
				this.Detail = detail;
			}

			/**
			 * 品目を設定します。
			 * @param ammount 金額
			 * @param no 品目番号
			 * @return このオブジェクト
			 */
			public JournalDetailBuilder item(long ammount, long no) {
				MAccountTitle title = this.Detail.getMAccountTitle();
				MAccountTitleItem titleItem = title.getMAccountTitleItemList().stream()
						.filter(i -> i.getNo() == no)
						.findFirst().orElse(null);
				if(titleItem == null) {
					throw new IllegalStateException("存在しない勘定科目品目番号[コード：" + title.getCode() + " 番号：" + no + "]が指定されました。");
				}

				List<TJournalDetailItem> items = this.Detail.getTJournalDetailItemList();
				TJournalDetailItem item = new TJournalDetailItem(this.Detail, items.size());
				item.setMAccountTitleItem(titleItem);
				item.setAmount(ammount);

				items.add(item);
				return this;
			}

			/**
			 * 次の明細に移ります。
			 * @return 仕訳ビルダ
			 */
			public JournalBuilder next() {
				return this.ParentBuilder;
			}

			/**
			 * 仕訳帳を確定します。
			 * @return 仕訳帳
			 */
			public TJournal build() {
				return this.ParentBuilder.build();
			}

		}
	}

}
