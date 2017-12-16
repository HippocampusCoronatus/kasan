/* Copyright © 2017- Kasan All Rights Reserved. */
package jp.co.kasan.members;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import jp.co.kasan.db.Transactional;
import jp.co.kasan.db.entity.MAccountBook;
import jp.co.kasan.db.entity.MMember;
import jp.co.kasan.journal.GeneralAccountBookGenerator;
import jp.co.kasan.utils.PasswordUtils;

/**

 * 会員の管理者。
 * @author rued97
 */
@Dependent
public class MemberManager {

	@Inject
	private EntityManager EM;
	@Inject
	private GeneralAccountBookGenerator BookGenerator;
	@Inject
	private MemberNoIncrementor MemberNoIncrementor;

	/**
	 * 会員を新規登録します。
	 * 検証NGの場合はValidateException例外を投げます。
	 * @param container 会員の項目値
	 */
	@Transactional
	public void register(MemberForRegister container) {

		// TODO:検証する

		// 検証OKなので会員登録。
		MMember m = container.convertToMMember(this.MemberNoIncrementor.next());
		// TODO:家計簿は別途登録作業を必要としたい
		MAccountBook book = this.BookGenerator.generate(m.getName() + "さんの家計簿");
		m.addMAccountBook(book);
		this.EM.persist(m);
	}

	/**
	 * 登録用の会員情報を保持。
	 */
	public static class MemberForRegister {

		/** Eメール */
		public String EMail;
		/** パスワード */
		public String Password;
		/** 名前 */
		public String Name;

		/**
		 * 会員マスタに変換します。
		 * 検証済みであることが前提です。
		 * @param no 会員番号
		 * @return 会員マスタ
		 */
		public MMember convertToMMember(long no) {
			MMember m = new MMember();
			m.setNo(no);
			m.setName(this.Name);
			m.setEmail(this.EMail);
			byte[] hashedPassword = PasswordUtils.hash(this.Password, this.EMail);
			m.setPassword(hashedPassword);
			return m;
		}
		
	}

}
