package student;

import java.awt.PageAttributes.MediaType;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;



@Path("/")
public class student {
	private studentDB sdb=new studentDB();
	@SuppressWarnings("unchecked")
	@GET
	  @Path("/getStudentInfo/sid/{id}")
	 
	    public Response getInfo(@PathParam("id") Integer studentid) {
	        System.out.println(studentid);
	        try {
	        	String query = "select * from studentInfo where sid="+studentid;	    		
	    		ResultSet res = sdb.executeQuery(query); 		
	    		JSONObject text = new JSONObject();

	    		if(res.next()){
	    			text.put("sid",res.getString("sid"));
	    			text.put("name",res.getString("name"));
	    		}
	    		return Response.ok().entity(text.toJSONString()).build();	        
	        } catch (Exception e) {
	            return Response.serverError().build();
	        }
	        
	    }
	  
	  @GET
	  @Path("/getEnrolledCourse/{id}")
	 
	    public Response getEnrolledCourse(@PathParam("id") Integer studentid) {
	        System.out.println(studentid);
	        try {
	            String coursename = "machine mearning";
	            return Response.ok().entity(coursename).build();
	        } catch (Exception e) {
	            return Response.serverError().build();
	        }
	        
	    }
	  
	  @POST
	  @Path("/addCourse/cid/{cid}/sid/{sid}")
	    public Response addCourse(@PathParam("cid") Integer courseid,@PathParam("sid")  Integer studentid) {
	        System.out.println(courseid);
	        try {
	        	String query0 = "select * from studentInfo where sid="+studentid;	    		
	    		ResultSet res0 = sdb.executeQuery(query0);
	    		if(res0.next()){
		        	String query1 = "insert into registration (sid,cid) values("+studentid+","+courseid+");";	
		        	String query2 = "insert into log (slog) values('add "+studentid+" "+courseid+"');";
		    		int res = sdb.execute(query1); 
		    		if(res==1) sdb.execute(query2);
		            return Response.ok().entity(res).build();
	    		}else return Response.ok().entity("-1").build();
	        } catch (Exception e) {
	            return Response.serverError().build();
	        }
	    }
	  
	  
	  
	  @DELETE
	  @Path("/dropCourse/cid/{cid}/sid/{sid}")
	    public Response dropCourse(@PathParam("cid") Integer courseid,@PathParam("sid")  Integer studentid) {
	        System.out.println(courseid);
	        try {
	        	String query1 = "delete from registration where sid= "+studentid+" and cid ="+courseid+";";
	        	String query2 = "insert into log (slog) values('drop "+studentid+" "+courseid+"');";
	    		int res = sdb.executeUpdate(query1); 
	    		if(res==1) sdb.execute(query2);
	            return Response.ok().entity(res).build();
	        //} catch (NotFoundException e) {
	           // return Response.status(Response.Status.NOT_FOUND).entity(Constants.notFound).build();
	        } catch (Exception e) {
	            return Response.serverError().build();
	        }
	    }
	  
	  @DELETE
	  @Path("/dropAll/cid/{cid}")
	    public Response dropALl(@PathParam("cid") Integer courseid) {
	        System.out.println(courseid);
	        try {
	        	String query1 = "delete from registration where cid= "+courseid+";";
	        	String query2 = "insert into log (slog) values('del null '"+courseid+");";
	    		int res = sdb.executeUpdate(query1); 
	    		if(res==1) sdb.execute(query2);
	            return Response.ok().entity(res).build();
	        //} catch (NotFoundException e) {
	           // return Response.status(Response.Status.NOT_FOUND).entity(Constants.notFound).build();
	        } catch (Exception e) {
	            return Response.serverError().build();
	        }
	    }
	 
	  
	  @POST
	  @Path("/createStudent/sid/{id}/name/{name}")
	    public Response getStaff(@PathParam("id") Integer studentid,@PathParam("name") String studentname) {
	        System.out.println(studentid);
	        try {
	        	String query = "insert into studentInfo (sid,name) values("+studentid+","+studentname+");";
	    		
	    		int res = sdb.execute(query); 
	            return Response.ok().entity(res).build();
	        } catch (Exception e) {
	            return Response.serverError().build();
	        }
	    }
	  
	  @DELETE
	  @Path("/deleteStudent/sid/{id}")
	    public Response getStaff(@PathParam("id") Integer studentid) {
	        System.out.println(studentid);
	        try {
	        	String query1 = "delete from studentInfo where sid= "+studentid+";";
	        	String query2 = "delete from registration where sid= "+studentid+";";
	        	String query3 = "insert into log (slog) values('del "+studentid+"' null);";	    		
	    		int res = sdb.executeUpdate(query1); 
	    		sdb.executeUpdate(query2);
	    		sdb.executeUpdate(query3);
	            return Response.ok().entity(res).build();
	        } catch (Exception e) {
	            return Response.serverError().build();
	        }
	    }
	  
	  
	  
	  @POST
	  @Path("/updateStudent/sid/{id}/name/{name}")
	    public Response updateCourse(@PathParam("id") Integer studentid,@PathParam("name") String studentname) {
	        System.out.println(studentid);
	        try {
	        	String query = "update studentInfo set name="+studentname+" where sid= "+studentid+";";
	    	
	    		int res = sdb.executeUpdate(query); 
	            return Response.ok().entity(res).build();
	        } catch (Exception e) {
	            return Response.serverError().build();
	        }
	    }
	  
	  @POST
	  @Path("/studentAddAttribute/columnname/{attributename}/columntype/{type}")
	    public Response courseAddAttribute(@PathParam("attributename") String attributename, @PathParam("type") String attributetype) {
	        System.out.println(attributename);
	        try {
	        	String query = "alter table studentInfo add column "+ attributename +" " + attributetype+";";    
	        	System.out.println(query);
	    		int res = sdb.execute(query); 
	            return Response.ok().entity(res).build();
	      
	        } catch (Exception e) {
	            return Response.serverError().build();
	        }
	    }
	  
	  @DELETE
	  @Path("/studentDeleteAttribute/columnname/{attributename}")
	    public Response courseDeleteAttribute(@PathParam("attributename") String attributename) {
	        System.out.println(attributename);
	        try {
	        	String query = "alter table studentInfo drop column "+ attributename +";";
	    		
	    		int res = sdb.execute(query); 
	            return Response.ok().entity(res).build();
	        
	        } catch (Exception e) {
	            return Response.serverError().build();
	        }
	    }
	  
	  @SuppressWarnings("unchecked")
	  @GET	  
	  @Path("/StudentGetLog")
	  @Consumes("application/json")
	    public Response courseGetLog() {
	        
		  try {
	        	String query = "select * from log;";
	    		
	    		ResultSet res = sdb.executeQuery(query); 		
	    		JSONObject text = new JSONObject();
	    		while(res.next()){
	    			text.put(res.getString("index"),res.getString("slog"));	    	
	    		}
	    		return Response.ok().entity(text.toJSONString()).build();
		  } catch (Exception e) {
	          return Response.serverError().build();
	      }
	       
	    }
}
