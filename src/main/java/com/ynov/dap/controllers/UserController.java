package com.ynov.dap.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ynov.dap.data.AppUser;
import com.ynov.dap.repository.AppUserRepository;

@Service
public class UserController {
	
	  @Autowired
	  private AppUserRepository repository;

    @RequestMapping("/user/add/{userKey}")
    public String addAccount(@PathVariable final String userKey, final HttpServletRequest request) {

  
    	AppUser appUser = new AppUser();
    	appUser.setName(userKey);
    	
    	repository.save(appUser);
    	
    	
        return "welcome";
    }
	
}
