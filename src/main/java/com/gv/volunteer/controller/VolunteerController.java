package com.gv.volunteer.controller;

import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.ServletConfigAware;
import org.springframework.web.context.ServletContextAware;

import com.gv.volunteer.model.*;

import com.gv.volunteer.service.VolunteerService;

@RestController
public class VolunteerController implements ApplicationContextAware, ServletContextAware, ServletConfigAware{
	Logger logger = LoggerFactory.getLogger(VolunteerController.class);
	@Autowired
	private ServletContext context;
	@Autowired
	private ServletConfig config;
	
	private ApplicationContext ctx;
	
	@Autowired
	private VolunteerService volunteerService;
	
    @RequestMapping("/login")
    public LoginResponse login(@RequestBody Login login) {
    	logger.info("VolunteerController:login() - Login ID: "+login.getLoginId()+" Password: "+login.getPassword());
    	return volunteerService.findUserById(login);
    }

    @RequestMapping("/locations")
    public List<LocationLookUp> listLocation() {
    	logger.info("VolunteerController:listLocation()");
    	return volunteerService.listAllLocations();
    }
    
    @RequestMapping("/homeEvents")
    public List<ListAllEvents> listEvents(@RequestParam String tab, Long locId) {
    	logger.info("VolunteerController:listEvents() - Tab Selected: "+tab+" Location ID: "+locId);
    	return volunteerService.listAllEvents(tab, locId);
    }
    
    @RequestMapping("/createEvent")
    public Integer createEvent(@RequestBody Event event) {
    	logger.info("VolunteerController:createEvent()");
    	return volunteerService.createEvent(event);
    }    
    
	public void setServletConfig(ServletConfig servletConfig) {
		this.config = servletConfig;
		
	}

	public void setServletContext(ServletContext servletContext) {
		this.context = servletContext;
		
	}

	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.ctx = applicationContext;
		
	}

}
