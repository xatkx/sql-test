package com.alura.jdbc.controller;

import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class ProductoController {

    public void modificar(String nombre, String descripcion, Integer id) {
        // TODO
    }

    public void eliminar(Integer id) {
        // TODO
    }

    public List<Map<String,String>> listar() throws SQLException {

        List<Map<String,String>> productos = new ArrayList<>();
     
        Connection connect = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/controlStock",
                "root",
                "1212");

        Statement statement = connect.createStatement();

        boolean isRead = statement.execute("select id, nombre, descripcion, cantidad from producto");

        if (isRead) {
            
            ResultSet result = statement.getResultSet();

            while (result.next()) {
                Map<String,String> producto = new HashMap<String,String>();
                
                producto.put("id", String.valueOf(result.getInt(1)));
                producto.put("nombre", result.getString(2));
                producto.put("descripcion", result.getString(3));
                producto.put("cantidad", String.valueOf(result.getString(4)));
                

                productos.add(producto);
                //System.out.println(result.getString(2));
            }
        }
        connect.close();

        return productos;
    }

    public void guardar(Object producto) {
        // TODO
    }

}
