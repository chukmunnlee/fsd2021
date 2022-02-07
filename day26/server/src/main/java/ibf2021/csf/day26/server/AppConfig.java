package ibf2021.csf.day26.server;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AppConfig implements WebMvcConfigurer {

	@Override
	public void addCorsMappings(CorsRegistry cors) {
		// Adding CORS headers to all request to /api
		System.out.println(">>>> configuring cors");
		cors.addMapping("/api/**");
	}
}
