package com.gv.volunteer.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Event {
	
	private Integer eventId;

	private String eventName;
	private Integer location;
	private String hostname;
	private String co_host1;
	private String co_host2;
	private String desc;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm a z")	
	private Date sdatetime;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm a z")	
	private Date edatetime;

	/**
	 * @return the eventId
	 */
	public Integer getEventId() {
		return eventId;
	}
	/**
	 * @param eventId the eventId to set
	 */
	public void setEventId(Integer eventId) {
		this.eventId = eventId;
	}
	/**
	 * @return the eventName
	 */
	public String getEventName() {
		return eventName;
	}
	/**
	 * @param eventName the eventName to set
	 */
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	/**
	 * @return the location
	 */
	public Integer getLocation() {
		return location;
	}
	/**
	 * @param location the location to set
	 */
	public void setLocation(Integer location) {
		this.location = location;
	}
	/**
	 * @return the hostname
	 */
	public String getHostname() {
		return hostname;
	}
	/**
	 * @param hostname the hostname to set
	 */
	public void setHostname(String hostname) {
		this.hostname = hostname;
	}
	/**
	 * @return the co_host1
	 */
	public String getCo_host1() {
		return co_host1;
	}
	/**
	 * @param co_host1 the co_host1 to set
	 */
	public void setCo_host1(String co_host1) {
		this.co_host1 = co_host1;
	}
	/**
	 * @return the co_host2
	 */
	public String getCo_host2() {
		return co_host2;
	}
	/**
	 * @param co_host2 the co_host2 to set
	 */
	public void setCo_host2(String co_host2) {
		this.co_host2 = co_host2;
	}
	/**
	 * @return the desc
	 */
	public String getDesc() {
		return desc;
	}
	/**
	 * @param desc the desc to set
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}
	/**
	 * @return the sdatetime
	 */
	public Date getSdatetime() {
		return sdatetime;
	}
	/**
	 * @param sdatetime the sdatetime to set
	 */
	public void setSdatetime(Date sdatetime) {
		this.sdatetime = sdatetime;
	}
	/**
	 * @return the edatetime
	 */
	public Date getEdatetime() {
		return edatetime;
	}
	/**
	 * @param edatetime the edatetime to set
	 */
	public void setEdatetime(Date edatetime) {
		this.edatetime = edatetime;
	}

}
