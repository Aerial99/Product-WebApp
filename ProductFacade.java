/**
*Retrieves products from the database
**/
package com.cs330;
import java.sql.*;
import javax.naming.*;
import java.util.*;

public class ProductFacade {
	
	//Private InterFace
	private static ProductFacade singleton;
	
	private ProductDataAccess dao;
	
	private ProductFacade() throws NamingException, SQLException {
		this.dao = ProductDataAccess.getInstance();
	}
	
	//Public Interface
	
	/**
	 * Get Instance Method
	 * @return singleton object
	 * @throws NamingException
	 * @throws SQLException
	 */
	public static ProductFacade getInstance() throws NamingException, SQLException {
		if(singleton == null) {
			singleton = new ProductFacade();
		}
		
		return singleton;
	}
	
	/**
	 * Return all products
	 * @return product array
	 * @throws SQLException
	 */
	public Product[] getProducts() throws SQLException {
		//get the connection from singleton object
		Connection con = dao.getConnection();
		
		//Execute Query
		PreparedStatement stmt = con.prepareStatement("SELECT id, name, description, price, stock FROM product");
		
		ResultSet rs = stmt.executeQuery();
		
		//Build array of product objects
		Product[] prodArray = new Product[100];
		int count=0;
		while(rs.next()) {
			int theId = rs.getInt("id");		
			String theName = rs.getString("name");
			String descr = rs.getString("description");
			double price = rs.getDouble("price");
			int stock = rs.getInt("stock");
			Product ing = new Product(theId,theName,descr,price,stock);
			prodArray[count] = ing;
			count++;
		}
		
		if(count>0) {
			prodArray = Arrays.copyOf(prodArray,count);
			return prodArray;
		}
		else{return null;}
		
	}
	
	
	/**
	 * Retrieve product from database by name
	 * @param theName
	 * @return products by name
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public Product[] getProductByName(String theName) throws SQLException, ClassNotFoundException {
		//get the connection from singleton object
		Connection con = dao.getConnection();
		
		//Execute Query
		PreparedStatement stmt = con.prepareStatement("SELECT id, name, description, price, stock FROM product where name=?");
		stmt.setString(1,theName);
		ResultSet rs = stmt.executeQuery();
		
		//Build array of product objects
		Product[] prodArray = new Product[100];
		int count=0;
		while(rs.next()) {
			int theId = rs.getInt("id");		
			String theName2 = rs.getString("name");
			String descr = rs.getString("description");
			double price = rs.getDouble("price");
			int stock = rs.getInt("stock");
			Product ing = new Product(theId,theName2,descr,price,stock);
			prodArray[count] = ing;
			count++;
		}
		
		if(count>0) {
			prodArray = Arrays.copyOf(prodArray,count);
			return prodArray;
		}
		else{return null;}
		
	}
	
	/**
	 * Retrieves product by id from database
	 * @param theId
	 * @return product by id
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public Product[] getProductById(int theId) throws SQLException, ClassNotFoundException {
		
		//get the connection from singleton object
		Connection con = dao.getConnection();
		
		//Execute Query
		PreparedStatement stmt = con.prepareStatement("SELECT id, name, description, price, stock FROM product where id=?");
		stmt.setInt(1,theId);
		ResultSet rs = stmt.executeQuery();
		
		//Build array of Ingredient objects
		Product[] idArray = new Product[100];
		int count=0;
		while(rs.next()) {
			int theId2 = rs.getInt("id");		
			String theName = rs.getString("name");
			String descr = rs.getString("description");
			double price = rs.getDouble("price");
			int stock = rs.getInt("stock");
			Product ing = new Product(theId2,theName,descr,price,stock);
			idArray[count] = ing;
			count++;
		}
		
		if(count>0) {
			idArray = Arrays.copyOf(idArray,count);
			return idArray;
		}
		else{return null;}
		
	}
	
	public Product[] createProduct(Product theProductToAdd) throws SQLException, ClassNotFoundException {
		//get the connection from singleton object
		Connection con = dao.getConnection();
		
		String newName = theProductToAdd.getName();
		String descr = theProductToAdd.getDescription();
		double price = theProductToAdd.getPrice();
		int stock = theProductToAdd.getStock();
		
		//Execute Query
		PreparedStatement createStmt = con.prepareStatement("INSERT INTO product (name,description,price,stock) VALUE (?,?,?,?)");
		createStmt.setString(1,newName);
		createStmt.setString(2,descr);
		createStmt.setDouble(3, price);
		createStmt.setInt(4, stock);
		int res = createStmt.executeUpdate();
		
		//Retrieve
		PreparedStatement retrieveStmt = con.prepareStatement("SELECT * from product where name=? AND description=?");
		retrieveStmt.setString(1, newName);
		retrieveStmt.setString(2,descr);
		ResultSet rs = retrieveStmt.executeQuery();
		
		//Build array of Product objects
		Product[] prodArray = new Product[100];
		int count=0;
		while(rs.next()) {
			int theId2 = rs.getInt("id");		
			String theName2 = rs.getString("name");
			String theDescr = rs.getString("description");
			double thePrice = rs.getDouble("price");
			int theStock = rs.getInt("stock");
			Product ing = new Product(theId2,theName2,theDescr,thePrice,theStock);
			prodArray[count] = ing;
			count++;
		}
		
		if(count>0) {
			prodArray = Arrays.copyOf(prodArray,count);
			return prodArray;
		}
		else{return null;}
		
	}

}
