package com.startupmashup.bean;

import java.util.Date;





public class HackathonBean extends BaseBean {
	
	private String name;
	private String status;
	private Date date;
	private String venue;
	private String participantCount;
	private String image;
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getVenue() {
		return venue;
	}
	public void setVenue(String venue) {
		this.venue = venue;
	}
	public String getParticipantCount() {
		return participantCount;
	}
	public void setParticipantCount(String participantCount) {
		this.participantCount = participantCount;
	}

}
