package com.focusdays2015.eyes2drive.model;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.ws.rs.Produces;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import org.jboss.resteasy.annotations.providers.jaxb.json.BadgerFish;

@XmlRootElement
@Produces("application/json")
@BadgerFish
public class Device implements JSONObject {
	
	static SimpleDateFormat TIMESTAMP_FORMATTER = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	

	public String deviceId;
	public String created;

	@Override
	public String toJSON() {
		return "{ \"device-id\": \"" + this.getDeviceId() + "\", \"created\": \""
				+ this.getCreated() + "\"}";
	}

	@XmlAttribute
	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	@XmlAttribute
	public String getCreated() {
		return created;
	}

	public void setCreated(String created) {
		this.created = created;
	}
	
	public void setCreatedNow() {
		this.setCreated(TIMESTAMP_FORMATTER.format(new Date()));
	}
	
	

}
