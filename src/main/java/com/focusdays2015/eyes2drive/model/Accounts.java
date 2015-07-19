package com.focusdays2015.eyes2drive.model;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.ServerErrorException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.jboss.resteasy.annotations.providers.jaxb.json.BadgerFish;

/* based on
 * http://docs.jboss.org/resteasy/docs/3.0.9.Final/userguide/html_single/index.html#d4e111
 * 
 * rest easy annotations using
 * http://www.mkyong.com/webservices/jax-rs/download-json-from-jax-rs-with-jaxb-resteasy/
 * 
 * 
 * */

@Path("/")
@Produces("application/json")
@BadgerFish
public class Accounts implements JSONObject {

	private static Map<String, Account> STORE = new HashMap<String, Account>();
	static {
		Account lolo = new Account("1", "lorenz.haenggi@bluewin.ch", "Lolo");
		Account doris = new Account("20", "doris2502@hotmail.com", "Didi");

		Trip t = new Trip("1");
		lolo.setTrip(t);
		
		Event e = new Event(t.getId(), "ORANGE", new Date().toString(), 20000);
		t.getLastEvents().add(e);
		lolo.getLastEvents().add(e);

		Event e2 = new Event(t.getId(), "RED", new Date().toString(), 30000);
		t.getLastEvents().add(e2);
		lolo.getLastEvents().add(e2);

		
		
		STORE.put(lolo.getId(), lolo);
		STORE.put(doris.getId(), doris);
	}

	public Accounts() {
	}

	@GET
	@Path("/accounts")
	public Response getObjects() {
		return Response.status(Status.OK).entity(this.toJSON()).build();
	}

	@GET
	@Path("/{id}")
	public Response getAccount(@PathParam("id") String id) {
		if (STORE.containsKey(id)) {
			return Response.status(Status.OK).entity(STORE.get(id)).build();
		} else {
			return Response.status(Status.NOT_FOUND).build();
		}
	}

	@GET
	@Path("/{id}/lastTrip")
	public Response getAccountLastTrip(@PathParam("id") String id) {
		if (STORE.containsKey(id)) {
			return Response.status(Status.OK).entity(STORE.get(id).getTrip()).build();
		} else {
			return Response.status(Status.NOT_FOUND).build();
		}
	}

	
	@GET
	@Path("/{id}/lastEvents")
	public Response getAccountLastEvents(@PathParam("id") String id) {
		if (STORE.containsKey(id)) {
			return Response.status(Status.OK).entity(STORE.get(id).getLastEvents())
					.type(MediaType.APPLICATION_JSON).build();
		} else {
			return Response.status(Status.NOT_FOUND).build();
		}
	}

	@PUT
	@Path("/{id}")
	public void addAccount(@PathParam("id") String id,
			@QueryParam("email") String email, @QueryParam("name") String name) {
		STORE.put(id, new Account(id, email, name));
	}

	@DELETE
	@Path("/{id}")
	public void removeAccount(@PathParam("id") String id) {
		if (STORE.containsKey(id)) {
			STORE.remove(id);
		} else {
			throw new ServerErrorException(Status.NOT_FOUND);
		}
	}

	@Override
	public String toJSON() {
		StringBuffer b = new StringBuffer();
		b.append("[");
		boolean first = true;
		for (Entry<String, Account> v : STORE.entrySet()) {
			if (first) {
				b.append(v.getValue().toJSON());
				first = false;
			} else {
				b.append(",");
				b.append(v.getValue().toJSON());
			}
		}
		b.append("]");
		return b.toString();
	}

}
