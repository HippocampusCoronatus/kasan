/* Copyright © 2017- Kasan All Rights Reserved. */
package jp.co.kasan.facebook;

import java.util.Arrays;
import java.util.stream.Collectors;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

/**
 * Facebookへの認証を提供します。
 * @author rued97
 */
public class FacebookAuthorization {

	/**
	 * 認可取得用のURLをパラメータ付きで生成します。<br>
	 * Facebookの仕様上、ブラウザでFacebookにログインした後、指定されたURLにリダイレクトされます。
	 * アクセストークン取得に、必要なコードはリダイレクト先にパラメータとして付与されます。<br>
	 * <br>
	 * @param redirectURL リダイレクトURL
	 * @param permissions 要求するパーミッション
	 * @return 認可取得用URL
	 */
	public static String generateAuthorizeWithParam(String redirectURL, String... permissions) {
		// 認可取得用URL
		String url = FacebookURLGenerator.generate("redirect_uri");
		// パーミッションをカンマで連結
		String values = String.join(",", Arrays.stream(permissions)
		  .collect(Collectors.toList()));
		// パラメータまで設定する。
		WebTarget target =ClientBuilder.newClient()
		  .target(url)
		  .queryParam("redirect_uri", redirectURL)
		  .queryParam("client_id", FacebookConfig.getClientId())
		  .queryParam("scope", values);
		return target.getUri().toString();
	}
	
}