package com.focusdays2015.eyes2drive.model;

import java.util.UUID;

import javax.ws.rs.Produces;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import org.jboss.resteasy.annotations.providers.jaxb.json.BadgerFish;

@XmlRootElement
@Produces("application/json")
@BadgerFish

public class Event {

	private String id;
	private String tripId;
	private String color;
	private String time;
	private int duration;

	public Event() {
	}

	public Event(String tripId, String color, String time, int duration) {
		setId(UUID.randomUUID().toString());
		setTripId(tripId);
		setColor(color);
		setTime(time);
		setDuration(duration);
	}

	@XmlAttribute
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@XmlAttribute
	public String getTripId() {
		return tripId;
	}

	public void setTripId(String tripId) {
		this.tripId = tripId;
	}

	@XmlAttribute
	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	@XmlAttribute
	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	@XmlAttribute
	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public String toJSON() {
		return "{ \"id\": \"" + this.getId() + "\", \"tripId\": \""
				+ this.getTripId() + "\", \"color\": \""
				+ this.getColor() + "\", \"time\": \"" + this.getTime()
				+ "\", \"duration\": " + this.getDuration() + "}";
	}

}
