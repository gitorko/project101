package com.demo.project101.config;

import javax.sql.DataSource;

import liquibase.exception.LiquibaseException;
import liquibase.integration.spring.SpringLiquibase;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class LiquibaseConfig {

    final DataSource americaDataSource;
    final DataSource asiaDataSource;

    @Bean
    public SpringLiquibase liquibaseForAsia() throws LiquibaseException {
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setDataSource(asiaDataSource);
        liquibase.setDefaultSchema("asia");
        liquibase.setChangeLog("classpath:db/changelog/db.changelog.yaml");
        liquibase.afterPropertiesSet();
        return liquibase;
    }

    @Bean
    public SpringLiquibase liquibaseForAmerica() throws LiquibaseException {
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setDataSource(americaDataSource);
        liquibase.setDefaultSchema("america");
        liquibase.setChangeLog("classpath:db/changelog/db.changelog.yaml");
        liquibase.afterPropertiesSet();
        return liquibase;
    }
}

