package com.gv.volunteer.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gv.volunteer.controller.VolunteerController;
import com.gv.volunteer.model.Event;
import com.gv.volunteer.model.ListAllEvents;
import com.gv.volunteer.model.LocationLookUp;
import com.gv.volunteer.model.Login;
import com.gv.volunteer.model.LoginResponse;



@Component
public class VolunteerDaoImpl implements VolunteerDao{
	Logger logger = LoggerFactory.getLogger(VolunteerController.class);
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	
	public LoginResponse findUserByEmailId(Login login){
		
		return this.sqlSessionTemplate.selectOne("findUserByEmailId", login);
	}


	public List<LocationLookUp> listAllLocations() {
		
		return this.sqlSessionTemplate.selectList("listAllLocations");
	}

	public List<ListAllEvents> listAllEvents(String tab, Long locId){	
		
		if(null == tab || tab.equals(null))
			tab = "Today";
		
		if(null == locId || locId.equals(null))
			locId = (long)1;
		
		if(tab.equals("Today")){
			
			return this.sqlSessionTemplate.selectList("ListAllEvents",locId);	
			
		} else if(tab.equals("Weekly")){
			
			return this.sqlSessionTemplate.selectList("ListAllWeeklyEvents",locId);	
			
		} else 
		
			return this.sqlSessionTemplate.selectList("ListAllMonthlyEvents",locId);	
	}
	
	public Integer saveCreateEvent(Event event){
		 
		this.sqlSessionTemplate.insert("InsertNewEvent", event);
		logger.info("VolunteerDaoImpl.saveCreateEvent():Event Id" +event.getEventId());
		Integer eventId = event.getEventId();
		return eventId;
	}
}
