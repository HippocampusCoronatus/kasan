/* Copyright © 2017- Kasan All Rights Reserved. */
package jp.co.kasan.facebook;

import jp.co.kasan.facebook.bean.FBAccessToken;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

/**
 * Facebookへのアクセスを提供します。
 * @author rued97
 */
public class Facebook {
	
	/**
	 * アクセストークンを取得します。
	 * リダイレクトURLには認可取得時と同じURLが指定する必要があります。
	 * コードには認可取得時に取得したコードを指定する必要があります。
	 * @param redirectURL リダイレクトURL
	 * @param code コード
	 * @return 
	 */
	public static FBAccessToken requestAccessToken(String redirectURL, String code) {
		String url = FacebookURLGenerator.generate("/oauth/access_token");
		WebTarget target =ClientBuilder.newClient()
			.target(url)
			.queryParam("client_id", FacebookConfig.getClientId())
			.queryParam("client_secret", FacebookConfig.getClientSecret())
			.queryParam("redirect_uri", redirectURL)
			.queryParam("code", code);
		return target.request().get(FBAccessToken.class);
	}
	
}