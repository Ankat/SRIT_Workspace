package com.esic.repository;

import java.util.List;
import com.esic.model.Userdetails;

public interface IUserdetailsRepository {

	public List<Userdetails> getUserdetailsById(String username);

}
