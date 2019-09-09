import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import java.util.List;

/**
 * @Author AdrainHuang
 * @Date 2019/8/29 00:48
 **/
@Mojo(name = "ad", defaultPhase = LifecyclePhase.COMPILE)
public class AdMojo  extends AbstractMojo {
	
	@Parameter
	private String msg;
	
	@Parameter
	private List<String> list;
	
	public void execute() throws MojoExecutionException, MojoFailureException {
		System.out.println("==================hello plugin: "+msg + "list: "+ list);
	}
}
