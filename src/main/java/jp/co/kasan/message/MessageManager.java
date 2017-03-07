/* Copyright © 2017- Kasan All Rights Reserved. */
package jp.co.kasan.message;

import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.Dependent;

/**
 * メッセージを管理します。
 * @author rued97
 */
@Dependent
public class MessageManager {

	/** メッセージコンテナリスト */
	private final List<MessageContainer> MessageContainers;

	/** コンストラクタ */
	public MessageManager() {
		this.MessageContainers = new ArrayList<>();
	}

	/**
	 * メッセージを追加します。
	 * @param container メッセージコンテナ
	 */
	public void addMessage(MessageContainer container) {
		this.MessageContainers.add(container);
	}

}
