package com.gqshop.kiosk.configuration;

import java.io.IOException;

import javax.sql.DataSource;

import org.h2.jdbcx.JdbcConnectionPool;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatasourceConfiguration {

//    @Bean
//    public DataSource dataSource() throws IOException {
//        // we're using the in-memory h2 database for simplicity for this example.
//        // For more info on h2 see http://www.h2database.com/html/features.html
//        String jdbcUrl = "jdbc:h2:mem:example;MODE=Oracle;INIT=runscript from 'classpath:/" + SCHEMA_INITIALISATION_SCRIPT + "'";
//        String username = "CLEAN_ARCHITECTURE";
//        String password = "";
//        return JdbcConnectionPool.create(jdbcUrl, username, password);
//    }
//
//    @Bean
//    public JdbcTemplate jdbcTemplate(DataSource datasource) {
//        return new JdbcTemplate(datasource);
//    }
//
//    @Bean
//    public DataSourceTransactionManager transactionManager(DataSource dataSource) {
//        return new DataSourceTransactionManager(dataSource);
//    }

}
