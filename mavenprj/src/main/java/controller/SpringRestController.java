package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import javax.validation.Valid;




@RestController  
@RequestMapping("/rest")  
public class SpringRestController {
    //comment
	@RequestMapping( value = "/checkDBConnection" ,
			method = RequestMethod.GET,
			consumes=MediaType.APPLICATION_JSON,
			produces=MediaType.APPLICATION_JSON)  
	
	 public Response checkDataBaseConnection()   throws Exception
	 {

		// TODO Auto-generated method stub
		
			
		System.out.println("-------- MySQL JDBC Connection Testing ------------");

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Where is your MySQL JDBC Driver?");
			e.printStackTrace();
			return Response.serverError().entity(e.getMessage()).build();
		}

		System.out.println("MySQL JDBC Driver Registered!");
		Connection connection = null;
	    Statement st=null;
	    ResultSet rs=null;

		try {
			connection = DriverManager
			.getConnection("jdbc:mysql://172.30.196.32:3306/profitcenterbrmsdb","admin", "admin");
//					.getConnection("jdbc:mysql://localhost:3306/inmarsatrulesdb","root", "Password-1");
			System.out.println("check connection");
			if(connection != null){
			System.out.println("connection successful!!");
				st=connection.createStatement();
			rs=st.executeQuery("select * from person");
//				rs=st.executeQuery("select * from person1");
			while(rs.next())
			{
				System.out.println("name: " + rs.getString("NAME") + " age: " + rs.getInt("AGE"));
			}
			}
		    

		} catch (SQLException e) {
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			return Response.serverError().entity(e.getMessage()).build();
		}

		if (connection != null) {
			System.out.println("You made it, take control your database now!");
			connection.close();
			st.close();
			return Response.ok().entity("COnnection Succes").build();
		} else {
			return Response.ok().entity("Failed to create Connection").build();
		}
			
	 }
	
	 
}
