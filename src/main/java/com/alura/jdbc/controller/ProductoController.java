package com.alura.jdbc.controller;

import com.alura.jdbc.factory.connectFactory;
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

    public void modificar(String nombre, String descripcion, Integer id) throws SQLException {
        
        String query = "update producto set "+
                "nombre = '"+nombre+
                "', descripcion = '"+descripcion+
                "' where id = "+id;
        
        Connection connect = new connectFactory().create();
        
        Statement statement = connect.createStatement();
        
        statement.execute(query);
        
        connect.close();
        
    }

    public void eliminar(Integer id) throws SQLException {
        String query = "delete from producto where id = " + id.toString();
        Connection connect = new connectFactory().create();
        Statement statement = connect.createStatement();
        statement.execute(query);
//        System.out.println(query);
        connect.close();
    }

    public List<Map<String,String>> listar() throws SQLException {

        List<Map<String,String>> productos = new ArrayList<>();
     
        Connection connect = new connectFactory().create();

        Statement statement = connect.createStatement();

        boolean isRead = statement.execute("select id, nombre, descripcion, cantidad from producto");

        if (isRead) {
            
            ResultSet result = statement.getResultSet();

            while (result.next()) {
                Map<String,String> producto = new HashMap<String,String>();
                
                producto.put("id", result.getString(1));
                producto.put("nombre", result.getString(2));
                producto.put("descripcion", result.getString(3));
                producto.put("cantidad", result.getString(4));
                
//                result.
                productos.add(producto);
                
//                System.out.println(result.getString(1)+result.getString(1));
            }
            
//            System.out.println("klk");
        }
        connect.close();

        return productos;
    }

    public void guardar(Map<String, String> producto) throws SQLException {
        
        String query = "insert into producto ( nombre, descripcion, cantidad) values ("
                + "'"+producto.get("nombre")+"', "+
                "'"+producto.get("descripcion")+"', "+
                producto.get("cantidad")+" )";
        Connection connect = new connectFactory().create();
        
        Statement statement = connect.createStatement();
        statement.execute(query);
        
        connect.close();
        
    }

}
