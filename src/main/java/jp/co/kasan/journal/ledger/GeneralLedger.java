/* Copyright © 2017- Kasan All Rights Reserved. */
package jp.co.kasan.journal.ledger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import jp.co.kasan.db.entity.MAccountTitle;
import jp.co.kasan.db.entity.TJournal;

/**
 * 総勘定元帳。
 * 勘定科目ごとの取引を扱います。
 * @author rued97
 */
public class GeneralLedger {

	/** 総勘定元帳マップ */
	private final Map<MAccountTitle, Ledger> LedgerMap;

	/** コンストラクタ */
	private GeneralLedger() {
		this.LedgerMap = new HashMap<>();
	}

	/**
	 * 仕訳一覧から総勘定元帳を生成します。
	 * @param journals 仕訳一覧
	 * @return 総勘定元帳
	 */
	public static GeneralLedger build(List<TJournal> journals) {
		final GeneralLedger generalLegder = new GeneralLedger();
		journals.stream()
				.flatMap(journal -> journal.getTJournalDetailList().stream())
				.map(LedgerDetail::new)
				.forEach(ledger -> {
					generalLegder.LedgerMap
							.computeIfAbsent(ledger.AccountTitle, key -> new Ledger(ledger.AccountTitle))
							.add(ledger);
					
				});
		return generalLegder;
	}

	/**
	 * 指定された勘定科目に該当する元帳を取得します。
	 * @param accountTitle 勘定科目
	 * @return 元帳一覧
	 */
	public Ledger get(MAccountTitle accountTitle) {
		return this.LedgerMap.get(accountTitle);
	}
}
