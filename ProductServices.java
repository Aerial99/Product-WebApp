package com.cs330;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.*;
import com.google.gson.Gson;
import java.sql.*;
import javax.naming.*;

@Path("ws2")
public class ProductServices {
	@Path("/products")
	@GET
	@Produces("text/plain")
	public Response getProducts() throws SQLException, ClassNotFoundException, NamingException {
		//this function retrieves all products
		//Reference to productFacade singleton object
		ProductFacade aFacade = ProductFacade.getInstance();
		
		
		Product[] getAll = aFacade.getProducts();
		
		
		if(getAll != null) {
			Gson theGsonObj = new Gson();
			String result = theGsonObj.toJson(getAll);
			
			ResponseBuilder rb = Response.ok(result, MediaType.TEXT_PLAIN);
			
			rb.status(200);
			return rb.build();
		}
		else {
			return Response.status(700).build();
		}
		
	}
	
	
	/**
	 * Return product found with id
	 * @param theId
	 * @return the Product
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @throws NamingException
	 */
	@Path("/products/{id}")
	@GET
	@Produces("text/plain")
	public Response getProductById(@PathParam("id") String theId) throws SQLException, ClassNotFoundException, NamingException {
		
		//Reference to ingredientFacade singleton object
		ProductFacade iFacade = ProductFacade.getInstance();
		System.out.println(theId);
		//Convert id to an int
		int intId=0;
		try {
			intId = Integer.parseInt(theId);
		}
		catch (NumberFormatException ne) {
			intId = 1;
		}
		
		Product[] idArray = iFacade.getProductById(intId);
		
		if(idArray != null) {
			Gson theGsonObj = new Gson();
			String result = theGsonObj.toJson(idArray);
			
			ResponseBuilder rb = Response.ok(result, MediaType.TEXT_PLAIN);
			
			rb.status(200);
			return rb.build();
		}
		else {
			return Response.status(700).build();
		}
			
	}
	
	/**
	 * Gets product by name
	 * @param theName
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @throws NamingException
	 */
	@Path("/products/product")
	@GET
	@Produces("text/plain")
	public Response getProductByName(@QueryParam("name") String theName) throws SQLException, ClassNotFoundException, NamingException {
		//Reference to ingredientFacade singleton object
		ProductFacade nFacade = ProductFacade.getInstance();

		
		Product[] nameArray = nFacade.getProductByName(theName);
		
		if(nameArray != null) {
			Gson theGsonObj = new Gson();
			String result = theGsonObj.toJson(nameArray);
			
			ResponseBuilder rb = Response.ok(result, MediaType.TEXT_PLAIN);
			
			rb.status(200);
			return rb.build();
		}
		else {
			return Response.status(700).build();
		}
	}
	
	/**
	 * Creates a new Product
	 * @param formFields
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @throws NamingException
	 */
	@Path("/products")
	@POST
	@Produces("text/plain")
	@Consumes("application/X-WWW-form-urlencoded")
	public Response createProduct(MultivaluedMap<String,String> formFields) throws SQLException, ClassNotFoundException, NamingException {
		ProductFacade cFacade = ProductFacade.getInstance();
		
		//Get values 
		String newName = formFields.getFirst("name");
		String newDescr = formFields.getFirst("description");
		String nPrice = formFields.getFirst("price");
		String nStock = formFields.getFirst("stock");
		
		//Convert stock and price to a number
		double newPrice=0;
		int newStock = 0;
		try {
			newPrice = Double.parseDouble(nPrice);
			newStock = Integer.parseInt(nStock);
		}
		catch (NumberFormatException ne) {
			newPrice = 1;
			newStock = 1;
		}
		
		Product newProd = new Product(newName,newDescr,newPrice,newStock);
		
		Product[] createArray = cFacade.createProduct(newProd);
		
		
		if(createArray != null) {
			Gson theGsonObj = new Gson();
			String result = theGsonObj.toJson(createArray);
			
			ResponseBuilder rb = Response.ok(result, MediaType.TEXT_PLAIN);
			//System.out.println(rb.build());
			//System.out.println(newProd.toString());
			rb.status(200);
			return rb.build();
		}
		else {
			//System.out.println("it is here");
			return Response.status(700).build();
		}
		
	}
	
}
