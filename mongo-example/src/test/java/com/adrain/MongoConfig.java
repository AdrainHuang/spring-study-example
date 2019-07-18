package com.adrain;

import de.flapdoodle.embed.mongo.Command;
import de.flapdoodle.embed.mongo.config.DownloadConfigBuilder;
import de.flapdoodle.embed.mongo.config.ExtractedArtifactStoreBuilder;
import de.flapdoodle.embed.mongo.config.RuntimeConfigBuilder;
import de.flapdoodle.embed.process.config.IRuntimeConfig;
import de.flapdoodle.embed.process.extract.ITempNaming;
import de.flapdoodle.embed.process.extract.UUIDTempNaming;
import de.flapdoodle.embed.process.io.directories.FixedPath;
import de.flapdoodle.embed.process.io.directories.IDirectory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

/**
 * @Author AdrainHuang
 * @Date 2019/7/18 23:57
 **/
@Configuration
public class MongoConfig {
	
	@Bean
	@Order(-1)
	public IRuntimeConfig iRuntimeConfig(){
		System.out.println("执行了这个配置了。。。。。。。。。。。。。。");
		IDirectory artifactStorePath = new FixedPath(System.getProperty("user.home") + "/.efg");
		ITempNaming executableNaming = new UUIDTempNaming();
		
		Command command = Command.MongoD;
		
		IRuntimeConfig runtimeConfig = new RuntimeConfigBuilder()
		.defaults(command)
		.artifactStore(new ExtractedArtifactStoreBuilder()
		.defaults(command)
		.download(new DownloadConfigBuilder()
		.defaultsForCommand(command)
		.artifactStorePath(artifactStorePath))
		.executableNaming(executableNaming))
		.build();
		return runtimeConfig;
	}
	
//	@Bean
//	public IMongodConfig iMongodConfig() throws IOException {
//		IMongodConfig mongodConfig = new MongodConfigBuilder()
//		.version(Version.Main.PRODUCTION)
//		.net(new Net(27017, Network.localhostIsIPv6()))
//		.build();
//		return mongodConfig;
//	}
//	@Bean(initMethod = "start", destroyMethod = "stop")
//	public MongodExecutable embeddedMongoServer(IMongodConfig mongodConfig) throws IOException {
//
//		return mongodStarter.prepare(mongodConfig);
//	}
}
