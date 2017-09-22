package com.example.prueba.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;

@Configuration
public class PersistenceContext {
	
	@Value("${spring.datasource.driver-class-name}")
    private String databaseDriver;

    @Value("${spring.datasource.url}")
    private String databaseUrl;
	
	@Value("${spring.datasource.username}")
	private String dataBaseUserName;
	
	@Value("${spring.datasource.password}")
    private String databasePassword;
	
	@Value("${spring.datasource.initialize}")
	private String generateDDL;
	
	@Value("${spring.hibernate.show_sql}")
	private String showSql;
	
	@Value("${spring.jpa.database-platform}")
	private String databaseDialect;
	
	@Value("${spring.datasource.platform}")
	private String databasePlatForm;
	
	/**
     * Bean datasource
     *
     * @return dataSource
     */
    @Bean
    public DataSource dataSource() {
        return DataSourceBuilder
                .create()
                .username(dataBaseUserName)
                .password(databasePassword)
                .driverClassName(databaseDriver)
                .url(databaseUrl)
                .build();
    }

	/**
     * Bean JpaVendorAdapter
     *
     * @return JpaVendorAdapter
     */
    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(Boolean.parseBoolean(generateDDL));
        vendorAdapter.setShowSql(Boolean.parseBoolean(showSql));
        vendorAdapter.setDatabasePlatform(databaseDialect);
        vendorAdapter.setDatabase(Database.valueOf(databasePlatForm));
        return vendorAdapter;
    }

}
