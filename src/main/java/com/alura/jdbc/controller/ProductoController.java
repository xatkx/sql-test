package com.alura.jdbc.controller;

import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ProductoController {

	public void modificar(String nombre, String descripcion, Integer id) {
		// TODO
	}

	public void eliminar(Integer id) {
		// TODO
	}

	public List<?> listar() throws SQLException {

		Connection connect = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/stock", 
				"root",
				"@KKkedwin2020");
		
		Statement statement = connect.createStatement();
		
		ResultSet result = statement.executeQuery("select id, nombre, descripcion, cantidad from productos");
		
		while(result.next())
		{
			System.out.println(result.getString("nombre"));
		}
		
		connect.close();
		
		return new ArrayList<>();
	}

    public void guardar(Object producto) {
		// TODO
	}

}
