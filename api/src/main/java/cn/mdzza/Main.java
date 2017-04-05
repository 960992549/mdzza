package cn.mdzza;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ImportResource;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

/**
 * 应用入口：
 * 1.用main方法，可以在开发时，启动一个内嵌的Web服务器
 * 2.继承SpringBootServletInitializer，部署到Web服务器时，会自动执行onStartup()方法
 * @author Administrator
 * 2015-12-01
 */
@SpringBootApplication
@ImportResource(value = {"classpath:providers.xml"}) // 使用 providers.xml 配置；
public class Main extends org.springframework.boot.web.support.SpringBootServletInitializer {
	/**log对象 */
	static Logger _log = LoggerFactory.getLogger(Main.class);

	public static void main(String[] args) throws Exception {
		SpringApplication.run(Main.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(Main.class);
	}

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		super.onStartup(servletContext);
		_log.debug("---------------------" + this.getClass().getName() + "-----------------------");
	}
}