package ibf2021.csf.weather.server;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AppConfig implements WebMvcConfigurer {

	@Override
	public void addCorsMappings(CorsRegistry reg) {
		reg.addMapping("/api/**");
	}
}
