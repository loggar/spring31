package com.loggar.component.log;

import net.sf.log4jdbc.Slf4jSpyLogDelegator;
import net.sf.log4jdbc.Spy;
import net.sf.log4jdbc.tools.LoggingType;

/**
 * SQL format for property of net.sf.log4jdbc.Log4jdbcProxyDataSource.
 * 
 * log4jdbc 사용하지 않음. -> 기능 mybaits interceptor 로 대체.
 * 
 * @author loggar.lee
 *
 */
public class LoggarLog4JdbcFormatter extends Slf4jSpyLogDelegator {
	private LoggingType loggingType = LoggingType.DISABLED;
	private String sqlPrefix = "\n";

	@Override
	public String sqlOccured(Spy spy, String methodCall, String rawSql) {
		if (loggingType == LoggingType.DISABLED) return "";

		
		if (loggingType != LoggingType.MULTI_LINE) return rawSql.replaceAll("\r", "").replaceAll("\n", "");

		getSqlOnlyLogger().info(sqlPrefix + rawSql);
		
		return rawSql;
	}

	@Override
	public String sqlOccured(Spy spy, String methodCall, String[] sqls) {
		String s = "";
		for (int i = 0; i < sqls.length; i++) {
			s += sqlOccured(spy, methodCall, sqls[i]) + String.format("%n");
		}
		return s;
	}

	public LoggingType getLoggingType() {
		return loggingType;
	}

	public void setLoggingType(LoggingType loggingType) {
		this.loggingType = loggingType;
	}
}
