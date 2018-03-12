package com.esic.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.esic.decrypt.ESICDeCrypt;
import com.esic.model.Userdetails;
import com.esic.service.IUserdetailsService;
import com.esic.util.ESICResponseBody;

@Controller(value = "userdetailsController")
public class UserdetailsController {

	@Autowired
	private IUserdetailsService userdetailsService;

	@Autowired
	private ESICDeCrypt esicDeCrypt;
	
	@RequestMapping(value = "/index.html", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView defaultPage(@ModelAttribute("userdetails") Userdetails userdetails) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("index");
		return modelAndView;
	}

	@RequestMapping(value = "/fetch_userdetails.html", method = { RequestMethod.POST })
	public @ResponseBody String fetchUserdetails(String username) {
		List<Userdetails> lstUserdetails = userdetailsService.getUserdetailsById(username);
		return ESICResponseBody.constructTable(lstUserdetails,esicDeCrypt);
	}

}