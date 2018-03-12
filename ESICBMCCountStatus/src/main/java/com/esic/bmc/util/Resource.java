package com.esic.bmc.util;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;

@Configuration(value = "resource")
@PropertySources({ @PropertySource("classpath:config/database.properties"), @PropertySource("classpath:config/query.properties"), @PropertySource("classpath:config/mail.properties") })
public class Resource {

	@Autowired
	private Environment env;

	public String getProperty(String key) {
		return StringUtils.trim(env.getProperty(key));
	}
}
