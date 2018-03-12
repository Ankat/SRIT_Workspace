package com.esic.bmc.repository;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.esic.bmc.model.ESICIncident;
import com.esic.bmc.util.Resource;

@Repository(value = "esicIncidentRepository")
public class ESICIncidentRepository implements IESICIncident {

	@Autowired
	private Resource resource;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<ESICIncident> getESICIncidentList() {
		String query = resource.getProperty("getESICIncidentList");
		List<ESICIncident> esicIncidents = jdbcTemplate.query(query, new BeanPropertyRowMapper<ESICIncident>(ESICIncident.class));
		return esicIncidents;
	}

}
