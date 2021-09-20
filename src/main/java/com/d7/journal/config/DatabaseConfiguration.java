package com.d7.journal.config;

import com.zaxxer.hikari.HikariDataSource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.MybatisProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Slf4j
@RequiredArgsConstructor
@MapperScan(value = "com.d7.journal.db.mapper", sqlSessionFactoryRef = "commonSqlSessionFactory")
@EnableTransactionManagement
@EnableConfigurationProperties({MybatisProperties.class})
@Configuration
public class DatabaseConfiguration {

    private final MybatisProperties mybatisProperties;

//    @Bean
//    public ConnectionFactory connectionFactory() {
//        return ConnectionFactories.get(
//                ConnectionFactoryOptions.builder()
//                        .option(DRIVER, "postgresql")
//                        .option(HOST, "192.168.0.24")
//                        .option(PORT, 5433)
//                        .option(USER, "postgres")
//                        .option(PASSWORD, "qlalfqjsghekd")
//                        .option(DATABASE, "postgres")
//                        .option(MAX_SIZE, 40)
//                        .build());
//    }

        @Bean
    public DataSource dataSource() {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl("jdbc:postgresql://192.168.0.24:5433/postgres");
        dataSource.setUsername("postgres");
        dataSource.setPassword("qlalfqjsghekd");
//        dataSource.setConnectionTestQuery(databaseProperties.getConnectionTestQuery());
        dataSource.setDriverClassName("org.postgresql.Driver");
        return dataSource;
    }

    @Bean(name = "commonSqlSessionFactory")
    public SqlSessionFactory commonSqlSessionFactory(DataSource dataSource, ApplicationContext applicationContext) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setTypeAliasesPackage("com.d7.journal.db.vo");
        sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath:querys/**/*.xml"));
        sqlSessionFactoryBean.setTypeHandlersPackage("com.d7.journal.common.db");
        sqlSessionFactoryBean.setConfiguration(mybatisProperties.getConfiguration());
        return sqlSessionFactoryBean.getObject();
    }
}
