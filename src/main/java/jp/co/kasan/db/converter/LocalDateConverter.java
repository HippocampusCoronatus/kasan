/* Copyright © 2016- rued97 All Rights Reserved. */
package jp.co.kasan.db.converter;

import java.time.LocalDate;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * JPAでLocalDateを使用して永続化するためのConverter。
 * @author rued97
 */
@Converter(autoApply=true)
public class LocalDateConverter implements AttributeConverter<LocalDate, java.sql.Date> {

	/**
	 * データベースへの保存時の変換を行います。
	 * @param localDate
	 * @return 処理結果
	 */
	@Override
	public java.sql.Date convertToDatabaseColumn(LocalDate localDate) {
		if(localDate == null) {
			return null;
		}
		return java.sql.Date.valueOf(localDate);
	}

	/**
	 * データベースからの読み込み時に変換を行います。
	 * @param date
	 * @return 処理結果
	 */
	@Override
	public LocalDate convertToEntityAttribute(java.sql.Date date) {
		if(date == null) {
			return null;
		}
		return date.toLocalDate();
	}

}
