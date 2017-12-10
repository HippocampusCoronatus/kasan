/* Copyright © 2017- Kasan All Rights Reserved. */
package jp.co.kasan.web.auth;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import jp.co.kasan.db.entity.MMember;

/**
 * 会員だよ。
 * @author rued97
 */
public class Member {

	/** ログイン会員 */
	private MMember Member;

	/** コンストラクタ */
	public Member() {}

	/**
	 * 会員エンティティから会員を生成します。
	 * @param entity 会員エンティティ
	 */
	public Member(MMember entity) {
		this.Member = entity;
	}

	/**
	 * 会員Noを取得します。
	 * @return 会員No
	 */
	@XmlElement(name="No")
	public long getNo() {
		return this.Member.getNo();
	}

	/**
	 * Eメールを取得します。
	 * @return Eメール
	 */
	@XmlElement(name="Email")
	public String getEMail() {
		return this.Member.getEmail();
	}

	/**
	 * 会員名を取得します。
	 * @return 会員名
	 */
	@XmlElement(name="Name")
	public String getName() {
		return this.Member.getName();
	}

	/**
	 * 会員マスタを取得します。
	 * @return 会員マスタ
	 */
	@XmlTransient
	public MMember getEntity() {
		return this.Member;
	}

}
