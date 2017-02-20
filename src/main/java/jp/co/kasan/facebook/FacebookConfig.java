/* Copyright © 2017- Kasan All Rights Reserved. */
package jp.co.kasan.facebook;

import java.util.ResourceBundle;

/**
 * Facebookの設定を管理します。
 * @author rued97
 */
public class FacebookConfig {

	/** 設定ファイル */
	private static final ResourceBundle  FBConfProperties;

	static {
		FBConfProperties = ResourceBundle.getBundle("facebook");
	}

	/**
	 * 認証時のリダイレクトパスを取得します。
	 * @return 認証時のリダイレクトパス
	 */
	public static String getAuthorizationRedirectPath() {
		return FBConfProperties.getString("AuthorizationRedirectPath");
	}

	/**
	 * クライアントIDを取得します
	 * @return クライアントID
	 */
	public static String getClientId() {
		return FBConfProperties.getString("ClientId");
	}

	/**
	 * クライアントシークレットを取得します。
	 * @return くらいあんとしーくれっと
	 */
	public static String getClientSecret() {
		return FBConfProperties.getString("ClientSecret");
	}
}