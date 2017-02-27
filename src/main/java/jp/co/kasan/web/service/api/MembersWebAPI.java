/* Copyright © 2017- Kasan All Rights Reserved. */
package jp.co.kasan.web.service.api;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import jp.co.kasan.web.auth.LoggedIn;
import jp.co.kasan.web.auth.Member;

/**
 * 会員情報を提供します。
 * @author rued97
 */
@RequestScoped
@Path("members")
public class MembersWebAPI {

	@Inject @LoggedIn
	private Member LoggedInMember;

	/**
	 * 自身の会員情報を取得します。
	 * @return 会員
	 */
	@Path("me")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Member getMe() {
		return this.LoggedInMember;
	}

}
