/* Copyright © 2017- Kasan All Rights Reserved. */
package jp.co.kasan.web.auth;

import jp.co.kasan.db.entity.MMember;

/**
 * 会員だよ。
 * @author rued97
 */
public class Member {

	/** 会員NO **/
	public long No;
	/** Eメール **/
	public String EMail;
	/** 会員名 **/
	public String Name;

	/** コンストラクタ */
	public Member() {}

	/**
	 * 会員エンティティから会員を生成します。
	 * @param entity 会員エンティティ
	 */
	public Member(MMember entity) {
		this.No = entity.getNo();
		this.EMail = entity.getEmail();
		this.Name = entity.getName();
	}

}
