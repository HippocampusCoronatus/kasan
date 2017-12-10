/* Copyright © 2017- Kasan All Rights Reserved. */
package jp.co.kasan.journal.ledger;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import jp.co.kasan.db.entity.MAccountTitle;
import jp.co.kasan.db.entity.MAccountTitleItem;
import jp.co.kasan.db.entity.TJournal;
import jp.co.kasan.db.entity.TJournalDetail;
import jp.co.kasan.db.entity.TJournalDetailItem;
import jp.co.kasan.journal.type.DebitCreditType;

/**
 * 元帳の明細です。
 * @author rued97
 */
public class LedgerDetail {

	/** 勘定科目 */
	protected MAccountTitle AccountTitle;
	/** 相手勘定科目 */
	protected MAccountTitle PartnerAccountTitle;

	/** 取引日 */
	protected LocalDate TradingDate;
	/** 借方/貸方種別 */
	protected DebitCreditType DebitCredit;
	/** 金額 */
	protected long Ammount;
	/** 品目一覧 */
	protected List<LedgerItem> LedgerItems;

	/**
	 * 仕訳詳細から元帳を生成します。
	 * @param detail 
	 */
	protected LedgerDetail(TJournalDetail detail) {
		this.AccountTitle = detail.getMAccountTitle();
		this.DebitCredit = detail.getType();
		this.Ammount = detail.getAmount();

		TJournal journal = detail.getTJournal();
		this.TradingDate = journal.getTradingDate();
		// 相手勘定科目は仕訳テーブルから取得する。
		List<TJournalDetail> partnerJournals = journal.getTJournalDetailList().stream()
				.filter(d -> (d.getType().equals(this.DebitCredit) == false))
				.collect(Collectors.toList());
		if(partnerJournals.isEmpty()) {
			throw new IllegalStateException("相手勘定科目がない仕訳詳細が存在します。");
		}
		if(partnerJournals.size() > 1) {
			// 相手勘定科目が複数ある場合は特定できないため設定しない。（『諸口』扱いになる想定）
		} else {
			this.PartnerAccountTitle = partnerJournals.get(0).getMAccountTitle();
		}

		this.LedgerItems = detail.getTJournalDetailItemList().stream()
				.map(LedgerItem::new)
				.collect(Collectors.toList());
	}

	/**
	 * 元帳品目。
	 */
	public static class LedgerItem {
		/** 勘定科目品目 */
		protected MAccountTitleItem AccountTitleItem;
		/** 金額 */
		protected long Ammount;

		/**
		 * 仕訳詳細費目から元帳費目を生成します。
		 * @param item 仕訳詳細費目
		 */
		private LedgerItem(TJournalDetailItem item) {
			this.AccountTitleItem = item.getMAccountTitleItem();
			this.Ammount = item.getAmount();
		}
	}

}
