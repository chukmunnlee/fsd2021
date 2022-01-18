package ibf2021.ssf.assessment.review;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import redis.clients.jedis.JedisClientConfig;

@Configuration
public class AppConfiguration {
	@Value("${spring.redis.host}")
	private String redisHost;

	@Value("${spring.redis.port}")
	private Integer redisPort;

	@Value("${spring.redis.database}")
	private Integer redisDatabase;

	@Bean
	@Scope("singleton")
	public RedisTemplate<String, Object> createRedisTemplate() {

		RedisStandaloneConfiguration config = new RedisStandaloneConfiguration();
		config.setHostName(redisHost);
		config.setPort(redisPort);
		config.setDatabase(redisDatabase);

		final String redisPassword = System.getenv("REDIS_PASSWORD");
		if ((redisPassword != null) && (redisPassword.length() > 0))
			config.setPassword( redisPassword);

		JedisClientConfiguration jedisClientConfig = JedisClientConfiguration.builder().build();
		JedisConnectionFactory jedisFac = new JedisConnectionFactory(config, jedisClientConfig);
		jedisFac.afterPropertiesSet();

		RedisTemplate<String, Object> template = new RedisTemplate<>();
		template.setConnectionFactory(jedisFac);
		template.setKeySerializer(new StringRedisSerializer());
		template.setValueSerializer(new StringRedisSerializer());

		return template;
	}
}
