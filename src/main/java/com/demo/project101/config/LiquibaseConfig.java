package com.demo.project101.config;

import java.util.List;
import javax.sql.DataSource;

import jakarta.annotation.PostConstruct;
import liquibase.exception.LiquibaseException;
import liquibase.integration.spring.SpringLiquibase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LiquibaseConfig {

    @Autowired
    @Qualifier("routingDataSource")
    private DataSource dataSource;

    @Value("${app.tenants}")
    private List<String> tenants;

    @PostConstruct
    public void applyLiquibase() throws LiquibaseException {
        for (String tenant : tenants) {
            SpringLiquibase liquibase = new SpringLiquibase();
            liquibase.setDataSource(dataSource);
            liquibase.setDefaultSchema(tenant);
            liquibase.setChangeLog("classpath:db/changelog/db.changelog.yaml");
            liquibase.afterPropertiesSet();
        }
    }

}

