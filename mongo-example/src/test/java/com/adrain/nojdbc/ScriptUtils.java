package com.adrain.nojdbc;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.script.ScriptException;
import java.io.IOException;
import java.io.LineNumberReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @Author AdrainHuang
 * @Date 2019/8/4 11:07
 **/
public class ScriptUtils {
	public static final String DEFAULT_STATEMENT_SEPARATOR = ";";
	public static final String FALLBACK_STATEMENT_SEPARATOR = "\n";
	public static final String EOF_STATEMENT_SEPARATOR = "^^^ END OF SCRIPT ^^^";
	public static final String DEFAULT_COMMENT_PREFIX = "--";
	public static final String DEFAULT_BLOCK_COMMENT_START_DELIMITER = "/*";
	public static final String DEFAULT_BLOCK_COMMENT_END_DELIMITER = "*/";
	private static final Log logger = LogFactory.getLog(ScriptUtils.class);
	
	public ScriptUtils() {
	}
	
	public static void splitSqlScript(String script, char separator, List<String> statements) throws ScriptException {
		splitSqlScript(script, String.valueOf(separator), statements);
	}
	
	public static void splitSqlScript(String script, String separator, List<String> statements) throws ScriptException {
		splitSqlScript((EncodedResource)null, script, separator, "--", "/*", "*/", statements);
	}
	
	public static void splitSqlScript(@Nullable EncodedResource resource, String script, String separator, String commentPrefix, String blockCommentStartDelimiter, String blockCommentEndDelimiter, List<String> statements) throws ScriptException {
		Assert.hasText(script, "'script' must not be null or empty");
		Assert.notNull(separator, "'separator' must not be null");
		Assert.hasText(commentPrefix, "'commentPrefix' must not be null or empty");
		Assert.hasText(blockCommentStartDelimiter, "'blockCommentStartDelimiter' must not be null or empty");
		Assert.hasText(blockCommentEndDelimiter, "'blockCommentEndDelimiter' must not be null or empty");
		StringBuilder sb = new StringBuilder();
		boolean inSingleQuote = false;
		boolean inDoubleQuote = false;
		boolean inEscape = false;
		
		for(int i = 0; i < script.length(); ++i) {
			char c = script.charAt(i);
			if (inEscape) {
				inEscape = false;
				sb.append(c);
			} else if (c == '\\') {
				inEscape = true;
				sb.append(c);
			} else {
				if (!inDoubleQuote && c == '\'') {
					inSingleQuote = !inSingleQuote;
				} else if (!inSingleQuote && c == '"') {
					inDoubleQuote = !inDoubleQuote;
				}
				
				if (!inSingleQuote && !inDoubleQuote) {
					if (script.startsWith(separator, i)) {
						if (sb.length() > 0) {
							statements.add(sb.toString());
							sb = new StringBuilder();
						}
						
						i += separator.length() - 1;
						continue;
					}
					
					int indexOfCommentEnd;
					if (script.startsWith(commentPrefix, i)) {
						indexOfCommentEnd = script.indexOf(10, i);
						if (indexOfCommentEnd <= i) {
							break;
						}
						
						i = indexOfCommentEnd;
						continue;
					}
					
					if (script.startsWith(blockCommentStartDelimiter, i)) {
						indexOfCommentEnd = script.indexOf(blockCommentEndDelimiter, i);
						if (indexOfCommentEnd <= i) {
						}
						
						i = indexOfCommentEnd + blockCommentEndDelimiter.length() - 1;
						continue;
					}
					
					if (c == ' ' || c == '\r' || c == '\n' || c == '\t') {
						if (sb.length() <= 0 || sb.charAt(sb.length() - 1) == ' ') {
							continue;
						}
						
						c = ' ';
					}
				}
				
				sb.append(c);
			}
		}
		
		if (StringUtils.hasText(sb)) {
			statements.add(sb.toString());
		}
		
	}
	
	static String readScript(EncodedResource resource) throws IOException {
		return readScript(resource, "--", ";", "*/");
	}
	
	private static String readScript(EncodedResource resource, @Nullable String commentPrefix, @Nullable String separator, @Nullable String blockCommentEndDelimiter) throws IOException {
		LineNumberReader lnr = new LineNumberReader(resource.getReader());
		
		String var5;
		try {
			var5 = readScript(lnr, commentPrefix, separator, blockCommentEndDelimiter);
		} finally {
			lnr.close();
		}
		
		return var5;
	}
	
	public static String readScript(LineNumberReader lineNumberReader, @Nullable String lineCommentPrefix, @Nullable String separator, @Nullable String blockCommentEndDelimiter) throws IOException {
		String currentStatement = lineNumberReader.readLine();
		
		StringBuilder scriptBuilder;
		for(scriptBuilder = new StringBuilder(); currentStatement != null; currentStatement = lineNumberReader.readLine()) {
			if (blockCommentEndDelimiter != null && currentStatement.contains(blockCommentEndDelimiter) || lineCommentPrefix != null && !currentStatement.startsWith(lineCommentPrefix)) {
				if (scriptBuilder.length() > 0) {
					scriptBuilder.append('\n');
				}
				
				scriptBuilder.append(currentStatement);
			}
		}
		
		appendSeparatorToScriptIfNecessary(scriptBuilder, separator);
		return scriptBuilder.toString();
	}
	
	private static void appendSeparatorToScriptIfNecessary(StringBuilder scriptBuilder, @Nullable String separator) {
		if (separator != null) {
			String trimmed = separator.trim();
			if (trimmed.length() != separator.length()) {
				if (scriptBuilder.lastIndexOf(trimmed) == scriptBuilder.length() - trimmed.length()) {
					scriptBuilder.append(separator.substring(trimmed.length()));
				}
				
			}
		}
	}
	
	public static boolean containsSqlScriptDelimiters(String script, String delim) {
		boolean inLiteral = false;
		boolean inEscape = false;
		
		for(int i = 0; i < script.length(); ++i) {
			char c = script.charAt(i);
			if (inEscape) {
				inEscape = false;
			} else if (c == '\\') {
				inEscape = true;
			} else {
				if (c == '\'') {
					inLiteral = !inLiteral;
				}
				
				if (!inLiteral && script.startsWith(delim, i)) {
					return true;
				}
			}
		}
		
		return false;
	}
	
	public static void executeSqlScript(Connection connection, EncodedResource resource, boolean continueOnError, boolean ignoreFailedDrops, String commentPrefix, @Nullable String separator, String blockCommentStartDelimiter, String blockCommentEndDelimiter) throws ScriptException {
		try {
			if (logger.isDebugEnabled()) {
				logger.debug("Executing SQL script from " + resource);
			}
			
			long startTime = System.currentTimeMillis();
			
			String script;
			try {
				script = readScript(resource, commentPrefix, separator, blockCommentEndDelimiter);
			} catch (IOException var27) {
				throw new RuntimeException(var27);
			}
			
			if (separator == null) {
				separator = ";";
			}
			
			if (!"^^^ END OF SCRIPT ^^^".equals(separator) && !containsSqlScriptDelimiters(script, separator)) {
				separator = "\n";
			}
			
			List<String> statements = new ArrayList();
			splitSqlScript(resource, script, separator, commentPrefix, blockCommentStartDelimiter, blockCommentEndDelimiter, statements);
			int stmtNumber = 0;
			Statement stmt = connection.createStatement();
			
			try {
				Iterator var14 = statements.iterator();
				
				while(var14.hasNext()) {
					String statement = (String)var14.next();
					++stmtNumber;
					
					try {
						stmt.execute(statement);
						int rowsAffected = stmt.getUpdateCount();
						if (logger.isDebugEnabled()) {
							logger.debug(rowsAffected + " returned as update count for SQL: " + statement);
							
							for(SQLWarning warningToLog = stmt.getWarnings(); warningToLog != null; warningToLog = warningToLog.getNextWarning()) {
								logger.debug("SQLWarning ignored: SQL state '" + warningToLog.getSQLState() + "', error code '" + warningToLog.getErrorCode() + "', message [" + warningToLog.getMessage() + "]");
							}
						}
					} catch (SQLException var28) {
						boolean dropStatement = StringUtils.startsWithIgnoreCase(statement.trim(), "drop");
						if (!continueOnError && (!dropStatement || !ignoreFailedDrops)) {
//							throw new ScriptStatementFailedException(statement, stmtNumber, resource, var28);
						}
						
						if (logger.isDebugEnabled()) {
//							logger.debug(ScriptStatementFailedException.buildErrorMessage(statement, stmtNumber, resource), var28);
						}
					}
				}
			} finally {
				try {
					stmt.close();
				} catch (Throwable var26) {
					logger.trace("Could not close JDBC Statement", var26);
				}
				
			}
			
			long elapsedTime = System.currentTimeMillis() - startTime;
			if (logger.isDebugEnabled()) {
				logger.debug("Executed SQL script from " + resource + " in " + elapsedTime + " ms.");
			}
			
		} catch (Exception var30) {
			if (var30 instanceof ScriptException) {
				throw (ScriptException)var30;
			} else {
//				throw new UncategorizedScriptException("Failed to execute database script from resource [" + resource + "]", var30);
			}
		}
	}
}
