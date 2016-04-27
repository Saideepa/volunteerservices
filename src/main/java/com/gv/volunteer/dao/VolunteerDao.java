package com.gv.volunteer.dao;

import java.util.List;

import com.gv.volunteer.model.Event;
import com.gv.volunteer.model.ListAllEvents;
import com.gv.volunteer.model.LocationLookUp;
import com.gv.volunteer.model.Login;
import com.gv.volunteer.model.LoginResponse;

public interface VolunteerDao {
	public LoginResponse findUserByEmailId(Login login);
	
	public List<LocationLookUp> listAllLocations();
	
	public List<ListAllEvents> listAllEvents(String tab, Long locId);
	
	public Integer saveCreateEvent(Event event);

}
