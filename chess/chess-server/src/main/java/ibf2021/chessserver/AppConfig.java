package ibf2021.chessserver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CommonsRequestLoggingFilter;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import ibf2021.chessserver.controllers.ChessEndpoint;
import ibf2021.chessserver.services.ChessRepositoryService;

@Configuration
@EnableWebSocket
public class AppConfig implements WebSocketConfigurer {

	@Autowired
	private ChessRepositoryService chessRepoSvc;

	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry reg) {
		reg.addHandler(new ChessEndpoint(chessRepoSvc), "/game/**").setAllowedOrigins("*");
	}

	@Bean
	public CommonsRequestLoggingFilter logging() {
		final CommonsRequestLoggingFilter logger = new CommonsRequestLoggingFilter();
		logger.setIncludeClientInfo(true);
		logger.setIncludeQueryString(true);
		return logger;
	}

}
