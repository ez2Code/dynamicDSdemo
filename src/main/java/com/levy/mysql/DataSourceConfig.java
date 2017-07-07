package com.levy.mysql;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by li_weia on 2017/7/6.
 */
@Configuration
public class DataSourceConfig {

    @Bean(name = "dynamicSource")
    public DynamicDataSource dataSource(@Qualifier("dbMaster")DataSource dbMaster,
                                        @Qualifier("dbRead")DataSource dbRead) {
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        // 默认数据源
        dynamicDataSource.setDefaultTargetDataSource(dbMaster);

        // 配置多数据源
        Map<Object, Object> dsMap = new HashMap<>(5);
        dsMap.put("db-master", dbMaster);
        dsMap.put("db-read", dbRead);
        dynamicDataSource.setTargetDataSources(dsMap);
        return dynamicDataSource;
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.db-master")
    public DataSource dbMaster() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.db-read")
    public DataSource dbRead() {
        return DataSourceBuilder.create().build();
    }
}
