package com.gv.volunteer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gv.volunteer.dao.VolunteerDao;
import com.gv.volunteer.model.Event;
import com.gv.volunteer.model.ListAllEvents;
import com.gv.volunteer.model.LocationLookUp;
import com.gv.volunteer.model.Login;
import com.gv.volunteer.model.LoginResponse;


@Service(value="volunteerService")
public class VolunteerService implements IVolunteerService{
	@Autowired
	private VolunteerDao volunteerDao;
	
	public LoginResponse findUserById(Login login){
		return volunteerDao.findUserByEmailId(login);
	}
	
	public List<LocationLookUp> listAllLocations(){
		return volunteerDao.listAllLocations();
	}
	
	public List<ListAllEvents> listAllEvents(String tab, Long locId){
		return volunteerDao.listAllEvents(tab, locId);
	}
	
	public Integer createEvent(Event event){
		return volunteerDao.saveCreateEvent(event);
	}
}
