package com.esic.repository;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.esic.model.Userdetails;

@Repository(value = "userdetailsRepository")
public class UserdetailsRepository implements IUserdetailsRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<Userdetails> getUserdetailsById(String username) {
		List<Userdetails> lstUserdetails = null;
		try {
			String query = "SELECT * FROM userdetails WHERE USERNAME = ?";
			lstUserdetails = jdbcTemplate.query(query, new Object[] { username }, new BeanPropertyRowMapper(Userdetails.class));
		} catch (Exception ex) {
			lstUserdetails = null;
			System.out.println("Error - UserdetailsRepository - getUserdetailsById : " + ex.getMessage());
		}
		return lstUserdetails;
	}

}
