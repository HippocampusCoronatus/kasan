/* Copyright © 2017- Kasan All Rights Reserved. */
package jp.co.kasan.web.service.api;

import java.net.URI;
import java.net.URISyntaxException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import jp.co.kasan.facebook.Facebook;
import jp.co.kasan.facebook.FacebookConfig;
import jp.co.kasan.facebook.bean.FBAccessToken;

/**
 * 認証関連のサービスを提供します。
 * @author rued97
 */
@Path("authorization")
public class AuthorizationAPI {

	/**
	 * Facebookの認可用リダイレクトを返します。
	 * @return Response
	 */
	@Path("facebook")
	@GET
	public Response authorization() {
		String redirectPath = FacebookConfig.getAuthorizationRedirectPath();
		URI uri;
		try {
			uri = new URI(redirectPath);
		} catch(URISyntaxException ex) {
			throw new IllegalStateException("リダイレクトURLの構築に失敗しました。", ex);
		}
		return Response.seeOther(uri).build();
	}

	/**
	 * Facebook認可の受け口です。
	 * アクセストークンの取得後、成功した場合は適切なページをリダイレクトします。
	 * @param code Facebookから送信されるコード
	 * @return 適切なページリンク
	 */
	@Path("facebook/login")
	@GET
	public Response login(@QueryParam("code") String code) {
		if(code == null) {
			throw new IllegalStateException("Facebookの認証に失敗しました。");
		}
		// アクセストークンを取得する
		FBAccessToken accessToken = Facebook.requestAccessToken(FacebookConfig.getAuthorizationRedirectPath(), code);
		if((accessToken == null) || (accessToken.access_token == null) || (accessToken.access_token.isEmpty())) {
			throw new IllegalStateException("Facebookの認証に失敗しました。");
		}
		URI uri;
		try {
			uri = new URI("http://～～～");
		} catch(URISyntaxException ex) {
			throw new IllegalStateException("リダイレクトURLの構築に失敗しました。", ex);
		}
		return Response.seeOther(uri).build();
	}

}