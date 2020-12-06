package com.gqshop.kiosk.configuration;

import java.io.IOException;

import javax.sql.DataSource;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

@Configuration
public class DatasourceConfiguration {

	@Bean
	public DataSource dataSource() throws IOException {
		// @ref https://www.baeldung.com/spring-boot-configure-data-source-programmatic
		System.out.println("calling datasource creation");
		DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
		dataSourceBuilder.driverClassName("org.h2.Driver");//      
		dataSourceBuilder.url("jdbc:h2:mem:testdb");
		dataSourceBuilder.username("SA");
		dataSourceBuilder.password("");
		DataSource dataSource = dataSourceBuilder.build();

		// schema init
		// @ref https://stackoverflow.com/a/41873743
		Resource initSchema = new ClassPathResource("/database/h2/create.sql");
		DatabasePopulator databaseInitPopulator = new ResourceDatabasePopulator(initSchema);
		DatabasePopulatorUtils.execute(databaseInitPopulator, dataSource);

		Resource dataSchema = new ClassPathResource("/database/h2/data.sql");
		DatabasePopulator databaseDataPopulator = new ResourceDatabasePopulator(dataSchema);
		DatabasePopulatorUtils.execute(databaseDataPopulator, dataSource);

		return dataSource;
	}

	@Bean
	public JdbcTemplate jdbcTemplate(DataSource datasource) {
		return new JdbcTemplate(datasource);
	}

	@Bean
	public DataSourceTransactionManager transactionManager(DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}

}
