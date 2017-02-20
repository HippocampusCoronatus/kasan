/* Copyright © 2017- Kasan All Rights Reserved. */
package jp.co.kasan.facebook.bean;

/**
 * FBのAccessToken。
 * @author rued97
 */
public class FBAccessToken implements FBBean {
	/** アクセストークン */
	public String access_token;
	/** トークンタイプ */
	public String token_type;
	/** 期限 */
	public String expires_in;
}
