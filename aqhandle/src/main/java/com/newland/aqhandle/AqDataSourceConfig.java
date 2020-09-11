package com.newland.aqhandle;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class AqDataSourceConfig {
    @Bean(name="aqDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.aq")
    public DataSource aQdataSourceProperties()
    {
        return DataSourceBuilder.create().build();
    }

}
