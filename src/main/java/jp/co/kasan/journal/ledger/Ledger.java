/* Copyright © 2017- Kasan All Rights Reserved. */
package jp.co.kasan.journal.ledger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import jp.co.kasan.db.entity.MAccountTitle;
import jp.co.kasan.db.entity.MAccountTitleItem;
import jp.co.kasan.journal.type.AccountTitleType;
import jp.co.kasan.journal.type.DebitCreditType;

/**
 * 元帳です。
 * @author rued97
 */
public class Ledger {

	/** 勘定科目 */
	protected final MAccountTitle AccountTitle;
	/** 明細一覧 */
	protected final List<LedgerDetail> LedgerDetails;
	/** 元帳の概要 */
	private Summary LedgerSummary;
	/** 元帳品目の概要 */
	private Map<MAccountTitleItem, Summary> LedgerItemSummaryMap;

	/**
	 * コンストラクタ。
	 * @param accountTitle 扱う勘定科目
	 */
	protected Ledger(MAccountTitle accountTitle) {
		this.AccountTitle = accountTitle;
		this.LedgerDetails = new ArrayList<>();
		this.LedgerSummary = new Summary();
		this.LedgerItemSummaryMap = new HashMap<>();
	}

	/**
	 * 勘定科目詳細を追加します。
	 * 勘定科目の異なる勘定科目詳細は無視されます。
	 * @param detail 
	 */
	protected void add(LedgerDetail detail) {
		if(this.AccountTitle.equals(detail.AccountTitle) == false) {
			return;
		}
		this.LedgerDetails.add(detail);
		// サマリの計算。
		this.LedgerSummary.addAmmount(detail.Ammount, detail.DebitCredit, this.AccountTitle.getType());
		detail.LedgerItems.stream()
				.forEach(item -> {
					this.LedgerItemSummaryMap.computeIfAbsent(item.AccountTitleItem, key -> new Summary())
							.addAmmount(item.Ammount, detail.DebitCredit, this.AccountTitle.getType());
				});
	}

	/**
	 * 概要を取得します。
	 * @return 概要
	 */
	public Summary summary() {
		return this.LedgerSummary;
	}

	/**
	 * アイテムの概要を取得します。
	 * @return アイテムの概要
	 */
	public Map<MAccountTitleItem, Summary> summaries() {
		return this.LedgerItemSummaryMap;
	}

	/**
	 * 元帳のサマリを扱います。
	 */
	public static class Summary {
		/** 借方金額 */
		protected long DebitAmmount;
		/** 貸方金額 */
		protected long CreditAmmount;
		/** 残高金額 */
		protected long BalanceAmmount;

		/**
		 * サマリに金額を加えます。
		 * @param ammount 金額
		 * @param postedType 転記された貸方借方
		 * @param balanceType 借方残高か、貸方残高か。nullの場合は貸方残高になる。
		 */
		private void addAmmount(long ammount, DebitCreditType postedType, AccountTitleType balanceType) {
			if(postedType.equals(DebitCreditType.Debit)) {
				this.DebitAmmount += ammount;
			} else {
				this.CreditAmmount += ammount;
			}
			// 貸方残高・またはどちらでもない場合が貸方残高。
			boolean isCreditBalance = (balanceType.isEqualsBalanceTyep(DebitCreditType.Debit) == false);
			if(isCreditBalance) {
				this.BalanceAmmount = this.CreditAmmount - this.DebitAmmount;
			} else {
				this.BalanceAmmount = this.DebitAmmount - this.CreditAmmount;
			}
		}

		/**
		 * 借方金額を取得します。
		 * @return 借方金額
		 */
		public long getDebitAmmount() {
			return this.DebitAmmount;
		}

		/**
		 * 貸方金額を取得します。
		 * @return 貸方金額
		 */
		public long getCreditAmmount() {
			return CreditAmmount;
		}

		/**
		 * 残高金額
		 * @return 残高金額
		 */
		public long getBalanceAmmount() {
			return BalanceAmmount;
		}

	}

}
