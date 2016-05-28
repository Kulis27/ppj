package cz.tul;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

@SpringBootApplication
public class SemestralApplication {

	private static Logger logger = LoggerFactory.getLogger(SemestralApplication.class);

	@Bean
	protected ServletContextListener listener() {
		return new ServletContextListener() {

			public void contextInitialized(ServletContextEvent sce) {
				logger.info("ServletContext initialized");
			}

			public void contextDestroyed(ServletContextEvent sce) {
				logger.info("ServletContext destroyed");
			}

		};
	}

	public static void main(String[] args) {
		SpringApplication.run("classpath:/application.xml", args);;
	}

}
