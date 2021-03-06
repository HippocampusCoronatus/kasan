/* Copyright © 2017- Kasan All Rights Reserved. */
package jp.co.kasan.web.service.api;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import jp.co.kasan.message.MessageContainer;
import jp.co.kasan.message.MessageManager;
import jp.co.kasan.web.auth.AuthenticationHandler;
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
	@Inject
	private AuthenticationHandler AuthenticationHandler;
	@Inject
	private MessageManager MessageManager;

	/**
	 * 新規にログインします。
	 * @param login 認証情報
	 */
	@Path("login")
	@POST
	public void login(Authentication login) {
		Member m = this.AuthenticationHandler.authenticate(login.EMail, login.Password);
		if(m == null) {
			this.MessageManager.addMessage(new MessageContainer("Errors_InvalidAuthentication", "メールアドレスかパスワードに誤りがあります。"));
			throw new BadRequestException();
		}
	}

	/**
	 * ログイン情報を保持します。
	 */
	public static class Authentication {
		/** Eメール */
		public String EMail;
		/** パスワード */
		public String Password;
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
		this.logger.log(Level.FINE, "ログイン情報破棄　[{0}]", loggedInMember.getNo());
		this.LoggedInMemberProducer.setLoggedInMember(null);
	}

}
