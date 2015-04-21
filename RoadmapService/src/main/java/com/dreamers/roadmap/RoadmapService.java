package com.dreamers.roadmap;
 
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.codehaus.jettison.json.JSONObject;
 
@Path("/user")
public class RoadmapService {
 
	@GET
	@Path("/{param_name}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public User getUser(@PathParam("param_name") String name) {
 
		User user = new User();
		user.setName(name);
		user.setEmail(name + "@gmail.com");
		user.setPassword("password");
 
		return user;
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public User createUser(User user) {
		
		System.out.println(user.toString());
		
		return user;
	}
 
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public User createUser(MultivaluedMap<String, String> params) {
		
		User user = new User();
		user.setName(params.getFirst("name"));
		user.setEmail(params.getFirst("email"));
		user.setPassword(params.getFirst("password"));
		
		System.out.println(user.toString());
		
		return user;
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createUser(String jsonString) throws Exception
	{
		JSONObject userObj = new JSONObject(jsonString);
		User user = new User();
		user.setName(userObj.getString("name"));
		user.setEmail(userObj.getString("email"));
		user.setPassword(userObj.getString("password"));
		
		return Response.status(Status.OK).entity(user).build();
	}
}