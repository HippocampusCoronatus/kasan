/* Copyright © 2017- Kasan All Rights Reserved. */
package jp.co.kasan.logging;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

/**
 * Loggerの創造主。
 * @author rued97
 */
@Dependent
public class LoggerProducer {

	/**
	 * ロガーを取得します。
	 * @param ip InjectionPoint
	 * @return ロガー
	 */
	@Produces
	@Dependent
	public Logger getLogger(InjectionPoint ip) {
		String name = ip.getMember().getDeclaringClass().getName();
		// TODO:とりあえず。
		Logger logger = createLogger(name);
		logger.setLevel(Level.FINEST);
		return createLogger(name);
	}

	/**
	 * ロガー名を指定してロガーを取得。
	 * @param loggerName ロガー名
	 * @return ロガー
	 */
	private static Logger createLogger(String loggerName) {
		return Logger.getLogger(loggerName);
	}

}