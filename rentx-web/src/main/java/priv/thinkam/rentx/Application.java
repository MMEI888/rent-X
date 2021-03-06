package priv.thinkam.rentx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableAsync;
import priv.thinkam.rentx.common.util.ApplicationContextUtil;

/**
 *
 * @author yanganyu
 * @date 2019/1/9 9:34
 */
@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@EnableAsync
public class Application {
	public static void main(String[] args) {
		ApplicationContext applicationContext = SpringApplication.run(Application.class, args);
		ApplicationContextUtil.setApplicationContext(applicationContext);
	}
}
