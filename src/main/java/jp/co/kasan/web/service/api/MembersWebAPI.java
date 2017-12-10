/* Copyright © 2017- Kasan All Rights Reserved. */
package jp.co.kasan.web.service.api;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import jp.co.kasan.members.MemberManager;
import jp.co.kasan.members.MemberManager.MemberForRegister;
import jp.co.kasan.web.auth.LoggedIn;
import jp.co.kasan.web.auth.Member;

/**
 * 会員情報を提供します。
 * @author rued97
 */
@RequestScoped
@Path("members")
public class MembersWebAPI {

	@Inject
	private Logger logger;
	@Inject @LoggedIn
	private Member LoggedInMember;
	@Inject
	private MemberManager MemberManager;

	/**
	 * 会員を新規に登録します。
	 * @param member 会員の入力
	 */
	@Path("/")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void register(MemberForRegister member) {
		this.MemberManager.register(member);
	}

	/**
	 * 自身の会員情報を取得します。
	 * @return 会員
	 */
	@Path("/me")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Member getMe() {
		this.logger.log(Level.FINE, "メンバー情報　[{0}]", this.LoggedInMember.getNo());		
		return this.LoggedInMember;
	}

}
