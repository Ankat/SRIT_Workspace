package com.esic.bmc.config;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import com.esic.bmc.util.Resource;

@Configuration(value = "bmcDataSourceConfiguration")
public class BMCDataSourceConfiguration {

	@Autowired
	private Resource resource;
	
	@Autowired
	private DataSource dataSource;

	@Bean(name = "dataSource")
	public DataSource getDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(resource.getProperty("spring.datasource.driverClassName"));
		dataSource.setUrl(resource.getProperty("spring.datasource.url"));
		dataSource.setUsername(resource.getProperty("spring.datasource.username"));
		dataSource.setPassword(resource.getProperty("spring.datasource.password"));
		return dataSource;
	}

	@Bean(name = "jdbcTemplate")
	public JdbcTemplate jdbcTemplate() {
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		jdbcTemplate.setDataSource(dataSource);
		return jdbcTemplate;
	}

}