/* Copyright © 2017- Kasan All Rights Reserved. */
package jp.co.kasan.facebook;

/**
 * FacebookのURLを生成します。
 * @author rued97
 */
public class FacebookURLGenerator {

	/** FacebookAPIのURL */
	// とりあえずv2.8しか対応してない。柔軟に対応できるならここじゃないほうがいいのかしら。
	private static final String GRAPH_API_BASE_URL_V2_8 = "https://graph.facebook.com/v2.8";

	/**
	 * FacebookAPI用のURLを生成します。
	 * @param path ぱす
	 * @return 生成したぱす
	 */
	public static String generate(String path) {
		if(path.startsWith("/")) {
			return GRAPH_API_BASE_URL_V2_8 + path;
		} else {
			return GRAPH_API_BASE_URL_V2_8 + "/" + path;
		}
	}
}
