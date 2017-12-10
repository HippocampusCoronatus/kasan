/* Copyright © 2017- Kasan All Rights Reserved. */
package jp.co.kasan.journal.type;

import java.util.EnumSet;

/**
 * システムで使用する固定の勘定科目。
 * @author rued97
 */
public enum SystemAccountTitle {
	// ======資産======
	現金("101001", AccountTitleType.Assets),
	貸付金("102001", AccountTitleType.Assets),
	// ======負債======
	借入金("202001", AccountTitleType.Liabilities),
	// ======純資産======
	// ======費用======
	// ======収益======
	// ======その他======
	損益("901001", AccountTitleType.Expenses),
	;

	/** コード */
	private final String Code;
	/** 種別 */
	private final AccountTitleType Type;

	/**
	 * コンストラクタ。
	 * @param code コード
	 */
	private SystemAccountTitle(String code, AccountTitleType type) {
		this.Code = code;
		this.Type = type;
	}

	/**
	 * コードを取得します。
	 * @return コード
	 */
	public String getCode() {
		return this.Code;
	}

	/**
	 * 種別を取得します。
	 * @return 種別
	 */
	public AccountTitleType getType() {
		return this.Type;
	}

	/**
	 * 名称を取得します。
	 * @return 名称
	 */
	public String getName() {
		// Enumの名前をそのまま使用。
		return this.name();
	}

	/**
	 * コードからSystemAccountTitleを取得します。
	 * @param code コード
	 * @return SystemAccountTitle
	 */
	public static SystemAccountTitle parse(String code) {
		for(SystemAccountTitle e : EnumSet.allOf(SystemAccountTitle.class)) {
			if(e.Code.equals(code)) {
				return e;
			}
		}
		throw new IllegalStateException("勘定科目の変換に失敗しました。[コード：" + code + "]");
	}
}
