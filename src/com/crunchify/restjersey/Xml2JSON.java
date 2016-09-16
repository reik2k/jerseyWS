package com.crunchify.restjersey;

import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.json.JSONObject;
import org.json.JSONException;
import org.json.XML;

@Path("/utils")
public class Xml2JSON {
	
	public static int PRETTY_PRINT_INDENT_FACTOR = 4;
	
	@POST
	@Path("/xml2json")
	@Consumes("application/xml")
	@Produces("application/json")
	public Response xml2Json(String xml){
		String response="";
		JSONObject json=new JSONObject();
		
		try{
			json = XML.toJSONObject(xml);
			response = json.toString(PRETTY_PRINT_INDENT_FACTOR);
			
		}catch (JSONException e) {
			json.put("code", 500);
			json.put("message", e.getMessage());
			json.put("trace", e.getStackTrace());
			response=json.toString();
			return Response.status(500).entity(response).build();
		}

		return Response.status(200).entity(response).build();
	}
	
	@GET
	@Path("/helloWorld/{name}")
	@Produces("application/json")
	public Response helloStupid(@PathParam("name") String name)  throws JSONException {
		
		JSONObject jsonResponse=new JSONObject();
		if(name.isEmpty()){
			
			name="mothafucka";
		}
		jsonResponse.put("response", "Hello "+name+", you're Strupid! :-)");
		
		String response = jsonResponse.toString();
		return Response.status(200).entity(response).build();
	}
	
	@GET
	@Path("/helloWorld")
	@Produces("application/json")
	public Response helloStupid2()  throws JSONException {
		
		JSONObject jsonResponse=new JSONObject();
		
		jsonResponse.put("response", "Hello mothafucka, you're Strupid! :-)");
		
		String response = jsonResponse.toString();
		return Response.status(200).entity(response).build();
	}
	
	@GET
	@Produces("application/json")
	public Response utils()  throws JSONException {
		
		JSONObject jsonResponse=new JSONObject();
		String[] methods={"/helloWorld","/xml2json"};
		
		jsonResponse.put("methods", methods);
		jsonResponse.put("response", "those are methods whose you could request it");  
		
		String response = jsonResponse.toString();
		return Response.status(200).entity(response).build();
	}
}
