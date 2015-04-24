package com.dreamers.roadmap;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.dreamers.roadmap.database.DatabaseConnection;
import com.dreamers.roadmap.model.User;

@Path("user")
public class UserResource 
{
	private DatabaseConnection connection = new DatabaseConnection();
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createUser(User user) throws Exception
	{
		connection.open();
		connection.createUser(user);
		connection.close();
		
		return Response.status(Status.OK).entity(user).build();
	}
	
	@GET
	@Path("{userName}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUser(@PathParam("userName") String userName) throws Exception
	{
		System.out.println(userName);
		
		connection.open();
		User user = connection.getUser(userName);
		
		if(user == null)
		{
			return Response.status(Status.NOT_FOUND).build();
		}
		
		connection.close();
		
		return Response.status(Status.OK).entity(user).build();
	}
}
