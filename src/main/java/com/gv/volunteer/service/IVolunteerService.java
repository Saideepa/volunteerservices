package com.gv.volunteer.service;

import java.util.List;

import com.gv.volunteer.model.Event;
import com.gv.volunteer.model.LocationLookUp;
import com.gv.volunteer.model.Login;
import com.gv.volunteer.model.LoginResponse;

public interface IVolunteerService {
	public LoginResponse findUserById(Login login);
	public List<LocationLookUp> listAllLocations();
	public Integer createEvent(Event event);
}
