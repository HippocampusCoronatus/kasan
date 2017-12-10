/* Copyright © 2016- rued97 All Rights Reserved. */
package jp.co.kasan.db.converter;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * JPAでLocalDateTimeを使用して永続化するためのConverter。
 * @author rued97
 */
@Converter(autoApply=true)
public class LocalDateTimeConverter implements AttributeConverter<LocalDateTime, Timestamp> {

	/**
	 * データベースへの保存時の変換を行います。
	 * @param localDateTime
	 * @return 処理結果
	 */
	@Override
	public Timestamp convertToDatabaseColumn(LocalDateTime localDateTime) {
		if(localDateTime == null) {
			return null;
		}
		return Timestamp.valueOf(localDateTime);
	}

	/**
	 * データベースからの読み込み時に変換を行います。
	 * @param timestamp
	 * @return 処理結果
	 */
	@Override
	public LocalDateTime convertToEntityAttribute(Timestamp timestamp) {
		if(timestamp == null) {
			return null;
		}
		return timestamp.toLocalDateTime();
	}

}
