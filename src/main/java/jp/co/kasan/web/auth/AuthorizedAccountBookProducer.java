/* Copyright © 2017- Kasan All Rights Reserved. */
package jp.co.kasan.web.auth;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import jp.co.kasan.db.entity.MAccountBook;

/**
 * 認可済みの会計帳簿をコントロールします。
 * @author rued97
 */
@RequestScoped
public class AuthorizedAccountBookProducer {

	/** 認可済みの会計帳簿 */
	private MAccountBook AuthorizedAccountBook;

	/**
	 * 認可済みの会計帳簿を取得します。
	 * @return 認可済みの会計帳簿
	 */
	@Produces
	@Authorized
	public MAccountBook getAuthorizedAccountBook() {
		return this.AuthorizedAccountBook;
	}

	/**
	 * 認可済みの会計帳簿を設定します。
	 * @param authorizedAccountBook 認可済みの会計帳簿
	 */
	public void setAuthorizedAccountBook(MAccountBook authorizedAccountBook) {
		this.AuthorizedAccountBook = authorizedAccountBook;
	}

}
