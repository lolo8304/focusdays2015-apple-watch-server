package com.focusdays2015.glass;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.ServerErrorException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

/* based on
 * http://docs.jboss.org/resteasy/docs/3.0.9.Final/userguide/html_single/index.html#d4e111
 * */


@Path("/store")
public class ValueableObjectStore {

	private static Map<String, ValueObject> STORE = new HashMap<String, ValueObject>();
	static {
		STORE.put("1", new ValueObject("1", "iPhone 5s", "450"));
	}
	
	   @GET
	   @Path("/objects")
	   public Response getObjects() {
			StringBuffer b = new StringBuffer();
			b.append("[");
			boolean first = true;
			for (Entry<String, ValueObject> v : STORE.entrySet()) {
				if (first) {
					b.append(v.getValue().toJSON());
				} else {
					b.append(",");
					b.append(v.getValue().toJSON());
				}
			}
			b.append("]");
			return Response.status(Status.OK).entity(b.toString()).build();
		   
	   }

	   @GET
	   @Path("/object/{id}")
	   public Response getObject(@PathParam("id") String id) {
		   if (STORE.containsKey(id)) {
				return Response.status(Status.OK).entity(STORE.get(id).toJSON()).build();
		   } else {
				return Response.status(Status.NOT_FOUND).build();
		   }
	   }

	   @PUT
	   @Path("/object/{id}")
	   public void addObject(@PathParam("id") String id, @QueryParam("name") String name, @QueryParam("price") String price) {
		   STORE.put(id, new ValueObject(id, name, price));
	   }

	   @DELETE
	   @Path("/object/{id}")
	   public void removeObject(@PathParam("id") String id) {
		   if (STORE.containsKey(id)) {
			   STORE.remove(id);
		   } else {
			   throw new ServerErrorException(Status.NOT_FOUND);
		   }
	   }
	
}
