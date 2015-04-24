package com.dreamers.roadmap;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.codehaus.jettison.json.JSONObject;

import com.dreamers.roadmap.model.User;

@Path("user")
public class UserResource 
{
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
