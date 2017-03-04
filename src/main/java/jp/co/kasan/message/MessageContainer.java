/* Copyright © 2017- Kasan All Rights Reserved. */
package jp.co.kasan.message;

/**
 * メッセージを保持。
	* TODO:決めあぐねている
 * @author rued97
 */
public class MessageContainer {

	/** メッセージID */
	public String ID;
	/** 本文 */
	public String Body;

	/**
	 * メッセージIDと本文からコンテナを生成します。
	 * @param id メッセージID
	 * @param body 本文
	 */
	public MessageContainer(String id, String body) {
		this.ID = id;
		this.Body = body;
	}

}
