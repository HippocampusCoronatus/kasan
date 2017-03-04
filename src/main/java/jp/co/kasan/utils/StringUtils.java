/* Copyright © 2017- Kasan All Rights Reserved. */
package jp.co.kasan.utils;

/**
 * String関連のユーティリティ。
 * @author rued97
 */
public class StringUtils {

	/**
	 * nullチェックとString#isEmptyのチェックを行います。
	 * @param str 検証文字列
	 * @return nullまたは、String#isEmptyの場合はtrue
	 */
	public static boolean isEmpty(String str) {
		return ((str == null) || str.isEmpty());
	}

}
