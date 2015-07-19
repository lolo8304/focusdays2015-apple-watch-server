package com.focusdays2015.eyes2drive.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.ws.rs.Produces;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;

import org.jboss.resteasy.annotations.providers.jaxb.json.BadgerFish;

@XmlRootElement
@Produces("application/json")
@BadgerFish

public class Trip {

	private String id;
	private String startTime;
	private String stopTime;
	private String status;
	
	private List<Event> historyEvents = new ArrayList<Event>();
	private List<Event> lastEvents = new ArrayList<Event>();

	
	public Trip(String id) {
		setId(id);
	}
	public Trip() {
		this(UUID.randomUUID().toString());
	}

	@XmlAttribute
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@XmlAttribute
	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	@XmlAttribute
	public String getStopTime() {
		return stopTime;
	}

	public void setStopTime(String stopTime) {
		this.stopTime = stopTime;
	}

	@XmlAttribute
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@XmlAttribute
	public long getDuration() {
		return 0;
	}

	public List<Event> getHistoryEvents() {
		return historyEvents;
	}
	public void setHistoryEvents(List<Event> historyEvents) {
		this.historyEvents = historyEvents;
	}
	@XmlElementRef
	public List<Event> getLastEvents() {
		return lastEvents;
	}
	public void setLastEvents(List<Event> lastEvents) {
		this.lastEvents = lastEvents;
	}
	
	
	
	public String toJSON() {
		return "{ \"id\": \"" + this.getId() + "\", \"startTime\": \""
				+ this.getStartTime() + "\", \"stopTime\": \"" + this.getStopTime()
				+ "\", \"duration\": " + this.getDuration() + ", \"status\": "
				+ this.getStatus() + "}";
	}

}
