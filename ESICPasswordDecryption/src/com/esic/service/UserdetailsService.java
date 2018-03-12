package com.esic.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.esic.model.Userdetails;
import com.esic.repository.IUserdetailsRepository;

@Service(value="userdetailsService")
public class UserdetailsService implements IUserdetailsService {

	@Autowired
	IUserdetailsRepository userdetailsRepository;
	
	@Override
	public List<Userdetails> getUserdetailsById(String username) {
		return userdetailsRepository.getUserdetailsById(username);
	}

}
