package com.esic.service;

import java.util.List;
import com.esic.model.Userdetails;

public interface IUserdetailsService {

	public List<Userdetails> getUserdetailsById(String username);

}
