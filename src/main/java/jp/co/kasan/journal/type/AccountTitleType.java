/* Copyright © 2017- Kasan All Rights Reserved. */
package jp.co.kasan.journal.type;

import static jp.co.kasan.journal.type.DebitCreditType.Credit;
import static jp.co.kasan.journal.type.DebitCreditType.Debit;

/**
 * 勘定科目タイプです。
 * @author rued97
 */
public enum AccountTitleType {

	/** 資産 */
	Assets(Debit),
	/** 負債 */
	Liabilities(Credit),
	/** 純資産 */
	NetAssets(Credit),
	/** 収益 */
	Revenue(Credit),
	/** 費用 */
	Expenses(Debit),
	/** その他 */
	Others(null)
	;

	/** 残高タイプ */
	private final DebitCreditType BalanceType;

	/**
	 * コンストラクタ。
	 * @param balanceType 残高タイプ
	 */
	private AccountTitleType(DebitCreditType balanceType) {
		this.BalanceType = balanceType;
	}

	/**
	 * 残高タイプを取得します。
	 * @return 残高タイプ
	 */
	public DebitCreditType getBalanceType() {
		return this.BalanceType;
	}

	/**
	 * 残高タイプが指定されたタイプと一致するか判定します。
	 * @param balanceTyep 残高タイプ
	 * @return 一致する場合はtrue
	 */
	public boolean isEqualsBalanceTyep(DebitCreditType balanceTyep) {
		if(this.BalanceType == null) {
			return balanceTyep == null;
		}
		return this.BalanceType.equals(balanceTyep);
	}

}
