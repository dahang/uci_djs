package com.rbc.djs;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.data.r2dbc.config.EnableR2dbcAuditing;
import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer;
import org.springframework.r2dbc.connection.init.ResourceDatabasePopulator;

import io.r2dbc.spi.ConnectionFactory;
import io.swagger.v3.oas.models.info.Info;

@SpringBootApplication
@EnableR2dbcAuditing
public class DjsApplication {

	public static void main(String[] args) {
		SpringApplication.run(DjsApplication.class, args);
	}

	@Bean
	public GroupedOpenApi stockDataOpenApi(@Value("${springdoc.version}") String appVersion) {
		String[] paths = { "/api/stock-data/**" };
		return GroupedOpenApi.builder().group("stock-data")
				.addOpenApiCustomiser(openApi -> openApi.info(new Info().title("Stock DATA API").version(appVersion)))
				.pathsToMatch(paths)
				.build();
	}

	@Bean
	ConnectionFactoryInitializer initializer(ConnectionFactory connectionFactory) {

		var initializer = new ConnectionFactoryInitializer();
		initializer.setConnectionFactory(connectionFactory);
		initializer
				.setDatabasePopulator(new ResourceDatabasePopulator(new ByteArrayResource(("DROP TABLE IF EXISTS stock;"
						+ "CREATE TABLE stock (quarter CHAR(10), stock CHAR(20), date CHAR(20), open VARCHAR(100), high VARCHAR(100), low VARCHAR(100), close VARCHAR(100), volume VARCHAR(100), percent_change_price VARCHAR(100), percent_change_volume_over_last_wk VARCHAR(100),previous_weeks_volume VARCHAR(100),next_weeks_open VARCHAR(100), next_weeks_close VARCHAR(100), percent_change_next_weeks_price VARCHAR(100), days_to_next_dividend VARCHAR(100) ,percent_return_next_dividend VARCHAR(100) );")
						.getBytes())));

		return initializer;
	}

}
