package com.levy.mysql;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * Created by li_weia on 2017/7/6.
 */
public class DynamicDataSource extends AbstractRoutingDataSource {
    private static final Logger log = LoggerFactory.getLogger(DynamicDataSource.class);
    @Override
    protected Object determineCurrentLookupKey() {
        log.debug("数据源为{}", DataSourceContextHolder.getDB());
        return DataSourceContextHolder.getDB();
    }
}
