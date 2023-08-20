package com.alura.jdbc.controller;

import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ProductoController {

	public void modificar(String nombre, String descripcion, Integer id) {
		// TODO
	}

	public void eliminar(Integer id) {
		// TODO
	}

	public List<?> listar() throws SQLException {

		Connection connect = DriverManager.getConnection(
				"jdbc:mysql:localhost/Stock", 
				"root",
				"@KKkedwin2020");
		
		connect.close();
		
		return new ArrayList<>();
	}

    public void guardar(Object producto) {
		// TODO
	}

}
