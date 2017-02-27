/* Copyright © 2017- Kasan All Rights Reserved. */
package jp.co.kasan.web.auth;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;

/**
 * ログイン会員の創造主。
 * @author rued97
 */
// TODO:本来はセッション持ちたくないかも。
@SessionScoped
public class LoggedInMemberProducer implements Serializable {

	/** ログイン会員 */
	private Member LoggedInMember;

	/**
	 * ログイン中の会員を取得します。
	 * @return ログイン会員
	 */
	@Produces
	@LoggedIn
	public Member getLoggedInMember() {
		return this.LoggedInMember;
	}

	/**
	 * ログイン中の会員を設定します。
	 * @param loggedInMember ログイン会員
	 */
	public void setLoggedInMember(Member loggedInMember) {
		this.LoggedInMember = loggedInMember;
	}
}