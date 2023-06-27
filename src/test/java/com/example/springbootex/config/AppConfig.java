package com.example.springbootex.config;

import io.zonky.test.db.postgres.embedded.EmbeddedPostgres;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@ComponentScan
@Configuration
public class AppConfig {

    @Bean
    @Primary
    public DataSource inMemoryDS() throws Exception {
        DataSource embeddedPostgresDS = EmbeddedPostgres.builder()
                .start().getPostgresDatabase();

        return embeddedPostgresDS;
    }


}
