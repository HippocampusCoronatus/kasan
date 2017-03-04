/* Copyright © 2017- Kasan All Rights Reserved. */
package jp.co.kasan.web.auth;

import java.util.List;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import jp.co.kasan.db.entity.MMember;
import jp.co.kasan.db.finder.MMemberFinder;
import jp.co.kasan.utils.PasswordUtils;

/**
 * 認証を提供します。
 * @author rued97
 */
@Dependent
public class AuthenticationHandler {

	@Inject
	private LoggedInMemberProducer LoggedInMemberProducer;
	@Inject
	private MMemberFinder MemberFinder;

	/**
	 * メールとパスワードから会員の認証を行います。
	 * 認証に成功した場合は、成功した会員を返します。
	 * 会員の認証に失敗した場合はnullを返します。
	 * 
	 * また、会員認証に成功した場合は、認証した会員として登録されます。
	 * @param email メールアドレス
	 * @param password パスワード
	 * @return 認証に成功した会員。失敗した場合はnull。
	 */
	public Member authenticate(String email, String password) {
		byte[] hashedPassword = PasswordUtils.hash(password, email);
		List<MMember> founrds = this.MemberFinder.findByEMailAndPassword(email, hashedPassword);

		if(founrds.isEmpty()) {
			return null;
		}
		if(founrds.size() > 1) {
			throw new IllegalStateException("同一会員が重複して登録されています。想定される範囲では発生しない例外です。");
		}

		MMember found = founrds.get(0);
		Member m = new Member(found);
		this.LoggedInMemberProducer.setLoggedInMember(m);
		return m;
	}

}
