package com.adrain.nojdbc;

import com.adrain.annotation.Script;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.ScriptOperations;
import org.springframework.data.mongodb.core.script.ExecutableMongoScript;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.support.AbstractTestExecutionListener;
import org.springframework.test.context.util.TestContextResourceUtils;
import org.springframework.util.ClassUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author AdrainHuang
 * @Date 2019/8/4 00:14
 **/
public class MongoScriptsTestExecutionListener extends AbstractTestExecutionListener {
	
	private static final Log logger = LogFactory.getLog(MongoScriptsTestExecutionListener.class);
	
	
	/**
	 * Returns {@code 5000}.
	 */
	@Override
	public final int getOrder() {
		return 5000;
	}
	
	/**
	 * Execute Script scripts configured via {@link Script @Script} for the supplied
	 * {@link TestContext} <em>before</em> the current test method.
	 */
	@Override
	public void beforeTestMethod(TestContext testContext) throws Exception {
		executeScriptScripts(testContext, Script.ExecutionPhase.BEFORE_TEST_METHOD);
	}
	
	/**
	 * Execute Script scripts configured via {@link Script @Script} for the supplied
	 * {@link TestContext} <em>after</em> the current test method.
	 */
	@Override
	public void afterTestMethod(TestContext testContext) throws Exception {
		executeScriptScripts(testContext, Script.ExecutionPhase.AFTER_TEST_METHOD);
	}
	
	/**
	 * Execute Script scripts configured via {@link Script @Script} for the supplied
	 * {@link TestContext} and {@link Script.ExecutionPhase}.
	 */
	private void executeScriptScripts(TestContext testContext, Script.ExecutionPhase executionPhase) throws Exception {
		boolean classLevel = false;
		Script scriptAnnotations = AnnotatedElementUtils.getMergedAnnotation(testContext.getTestMethod(), Script.class);
//		Set<Script> scriptAnnotations = AnnotatedElementUtils.getMergedRepeatableAnnotations(testContext.getTestMethod(), Script.class);
//		if (scriptAnnotations.isEmpty()) {
//			scriptAnnotations = AnnotatedElementUtils.getMergedRepeatableAnnotations(
//			testContext.getTestClass(), Script.class);
//			if (!scriptAnnotations.isEmpty()) {
//				classLevel = true;
//			}
//		}
		
//		for (Script script : scriptAnnotations) {
			executeScriptScripts(scriptAnnotations, executionPhase, testContext, classLevel);
//		}
	}
	
	/**
	 * Execute the Script scripts configured via the supplied {@link Script @Script}
	 * annotation for the given {@link Script.ExecutionPhase} and {@link TestContext}.
	 * <p>Special care must be taken in order to properly support the configured
	 * @param script the {@code @Script} annotation to parse
	 * @param executionPhase the current execution phase
	 * @param testContext the current {@code TestContext}
	 */
	private void executeScriptScripts(Script script, Script.ExecutionPhase executionPhase, TestContext testContext, boolean classLevel)
	throws Exception {
		
		if (executionPhase != script.executionPhase()) {
			return;
		}
		
		String[] scripts = getScripts(script, testContext, classLevel);
		scripts = TestContextResourceUtils.convertToClasspathResourcePaths(testContext.getTestClass(), scripts);
		List<Resource> scriptResources = TestContextResourceUtils.convertToResourceList(
		testContext.getApplicationContext(), scripts);
		EncodedResource resource = new EncodedResource(scriptResources.get(0));
		String s = ScriptUtils.readScript(resource);
		List<String> statements = new ArrayList<>();
		ScriptUtils.splitSqlScript(resource,s,ScriptUtils.DEFAULT_STATEMENT_SEPARATOR,ScriptUtils.DEFAULT_COMMENT_PREFIX,ScriptUtils.DEFAULT_BLOCK_COMMENT_START_DELIMITER,ScriptUtils.DEFAULT_BLOCK_COMMENT_END_DELIMITER,statements);
		for (String stmt : statements) {
			if (StringUtils.hasText(stmt)) {
				stmt = stmt.trim();
				MongoTemplate mongoTemplate = testContext.getApplicationContext().getBean(MongoTemplate.class);
				ScriptOperations operations = mongoTemplate.scriptOps();
				ExecutableMongoScript mongoScript = new ExecutableMongoScript(stmt);
				Object object4 = operations.execute(mongoScript);
			}
		}
//		populator.setScripts(scriptResources.toArray(new Resource[0]));
		
	}
	
	
	private String[] getScripts(Script script, TestContext testContext, boolean classLevel) {
		String[] scripts = script.scripts();
		if (ObjectUtils.isEmpty(scripts) && ObjectUtils.isEmpty(script.statements())) {
			scripts = new String[] {detectDefaultScript(testContext, classLevel)};
		}
		return scripts;
	}
	
	/**
	 * Detect a default Script script by implementing the algorithm defined in
	 * {@link Script#scripts}.
	 */
	private String detectDefaultScript(TestContext testContext, boolean classLevel) {
		Class<?> clazz = testContext.getTestClass();
		Method method = testContext.getTestMethod();
		String elementType = (classLevel ? "class" : "method");
		String elementName = (classLevel ? clazz.getName() : method.toString());
		
		String resourcePath = ClassUtils.convertClassNameToResourcePath(clazz.getName());
		if (!classLevel) {
			resourcePath += "." + method.getName();
		}
		resourcePath += ".Script";
		
		String prefixedResourcePath = ResourceUtils.CLASSPATH_URL_PREFIX + resourcePath;
		ClassPathResource classPathResource = new ClassPathResource(resourcePath);
		
		if (classPathResource.exists()) {
			if (logger.isInfoEnabled()) {
				logger.info(String.format("Detected default Script script \"%s\" for test %s [%s]",
				prefixedResourcePath, elementType, elementName));
			}
			return prefixedResourcePath;
		}
		else {
			String msg = String.format("Could not detect default Script script for test %s [%s]: " +
			"%s does not exist. Either declare statements or scripts via @Script or make the " +
			"default Script script available.", elementType, elementName, classPathResource);
			logger.error(msg);
			throw new IllegalStateException(msg);
		}
	}
	
}
