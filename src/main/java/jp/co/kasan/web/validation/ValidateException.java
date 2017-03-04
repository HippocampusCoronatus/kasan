/* Copyright © 2017- Kasan All Rights Reserved. */
package jp.co.kasan.web.validation;

/**
 * 検証エラーを表現します。
 * @author rued97
 */
public class ValidateException extends RuntimeException {

	/** メッセージ */
	private String Message;

	/**
	 * メッセージを指定してインスタンスを生成します。
	 * @param message メッセージ
	 */
	public ValidateException(String message) {
		this.Message = message;
	}

}
