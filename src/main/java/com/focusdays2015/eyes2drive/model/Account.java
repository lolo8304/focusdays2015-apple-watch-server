package com.focusdays2015.eyes2drive.model;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.ServerErrorException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlRootElement;

import org.jboss.resteasy.annotations.providers.jaxb.json.BadgerFish;

/* based on
 * http://docs.jboss.org/resteasy/docs/3.0.9.Final/userguide/html_single/index.html#d4e111
 * */

@XmlRootElement
@Produces("application/json")
@BadgerFish

public class Account implements JSONObject {

	private String id;
	private String name;
	private String email;
	
	private List<Event> lastEvents = new ArrayList<Event>();
	private List<Trip> historyTrips = new ArrayList<Trip>();
	private Trip trip;
	
	public Account() {
		
	}
	public Account(String id, String email, String name) {
		setId(id);
		setEmail(email);
		setName(name);
	}

	@XmlElementRef (name = "lastEvents", type = Event.class)
	public List<Event> getLastEvents() {
		return lastEvents;
	}

	public void setLastEvents(List<Event> lastEvents) {
		this.lastEvents = lastEvents;
	}

	@XmlAttribute
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@XmlAttribute
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@XmlAttribute
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@XmlElementRef
	public Trip getTrip() {
		return trip;
	}

	public void setTrip(Trip trip) {
		this.trip = trip;
	}

	@XmlElementRef
	public List<Trip> getHistoryTrips() {
		return historyTrips;
	}

	public void setHistoryTrips(List<Trip> historyTrips) {
		this.historyTrips = historyTrips;
	}

	@GET
	@Path("/lastEvents")
	public Response getLastEventsJSON() {
		StringBuffer b = new StringBuffer();
		b.append("[");
		boolean first = true;
		for (Event v : this.getLastEvents()) {
			if (first) {
				b.append(v.toJSON());
			} else {
				b.append(",");
				b.append(v.toJSON());
			}
		}
		b.append("]");
		return Response.status(Status.OK).entity(b.toString()).build();

	}
	
	@GET
	@Path("/trip")
	public Response getLastTripJSON() {
		return Response.status(Status.OK).entity(this.getTrip().toJSON()).build();

	}
	@GET
	@Path("/trip/{id}")
	public Response getTripJSON(@PathParam("id") String id) {
		Trip t = this.getTrip(id);
		if (t != null) {
			return Response.status(Status.OK).entity(t.toJSON()).build();
		} else {
			throw new ServerErrorException(Status.NOT_FOUND);
		}
	}
	
	private Trip getTrip(String id) {
		for (Trip trip : historyTrips) {
			if (trip.getId().equals(id)) return trip;
		}
		return null;
	}
	
	/* add */
	@PUT
	@Path("/trip")
    public Response addObject(@QueryParam("startTime") String startTime) {
		Trip t = new Trip();
		t.setStartTime(startTime);
		t.setStatus("started");
		t.setStopTime("");
		this.getHistoryTrips().add(t);
		this.setTrip(t);
		return Response.status(Status.OK).entity(t.toJSON()).build();
    }

	@Override
	public String toJSON() {
		return "{ \"id\": \"" + this.getId() + "\", \"email\": \""
				+ this.getEmail() + "\", \"name\": \"" + this.getName() + "\"}";
	}

}
