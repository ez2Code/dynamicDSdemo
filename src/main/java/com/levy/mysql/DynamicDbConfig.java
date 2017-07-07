package com.levy.mysql;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import javax.sql.DataSource;

/**
 * Created by li_weia on 2017/7/6.
 */
@Configuration
@MapperScan(basePackages = {"com.levy.dao.mapper"}, sqlSessionFactoryRef = "dynamicSqlSessionFactory")
public class DynamicDbConfig {

    private static String MYBATIS_CONFIG = "mybatis/mybatis-config.xml";
    /**     * mybatis mapper resource 路径     */
    private static String MAPPER_PATH = "mybatis/mapper/**.xml";


    private final DataSource dynamicDS;

    @Autowired
    public DynamicDbConfig(DataSource dynamicSource) {
        this.dynamicDS = dynamicSource;
    }

    @Bean
    public SqlSessionFactory dynamicSqlSessionFactory() throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setConfigLocation(new ClassPathResource(MYBATIS_CONFIG));

        PathMatchingResourcePatternResolver pathMatchingResourcePatternResolver = new PathMatchingResourcePatternResolver();

        String packageSearchPath = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX + MAPPER_PATH;

        factoryBean.setMapperLocations(pathMatchingResourcePatternResolver.getResources(packageSearchPath));
        factoryBean.setDataSource(dynamicDS);
        return factoryBean.getObject();

    }
}
