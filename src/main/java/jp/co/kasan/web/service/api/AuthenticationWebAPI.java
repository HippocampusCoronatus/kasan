/* Copyright © 2017- Kasan All Rights Reserved. */
package jp.co.kasan.web.service.api;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import jp.co.kasan.web.auth.LoggedInMemberProducer;
import jp.co.kasan.web.auth.Member;

/**
 * 認証に関するサービスを提供します。
 * @author rued97
 */
@RequestScoped
@Path("authentication")
public class AuthenticationWebAPI {

	@Inject
	private Logger logger;
	@Inject
	private LoggedInMemberProducer LoggedInMemberProducer;

	/**
	 * TODO:全然未実装
	 */
	@Path("login")
	@POST
	public void login() {
		Member loggedInMember = new Member();
		loggedInMember.ID = "99999";
		loggedInMember.Name = "山田太郎";

		this.logger.log(Level.FINE, "ログイン成功　[{0}]", loggedInMember.ID);
		this.LoggedInMemberProducer.setLoggedInMember(loggedInMember);
	}

	/**
	 * ログイン会員情報を破棄します。
	 * TODO:多分認証破棄も必要になる。
	 */
	@Path("logout")
	@POST
	public void logout() {
		Member loggedInMember = this.LoggedInMemberProducer.getLoggedInMember();
		if(loggedInMember == null) {
			return;
		}
		this.logger.log(Level.FINE, "ログイン情報破棄　[{0}]", loggedInMember.ID);
		this.LoggedInMemberProducer.setLoggedInMember(null);
	}

}