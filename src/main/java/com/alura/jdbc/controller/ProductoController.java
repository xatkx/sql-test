package com.alura.jdbc.controller;

import com.alura.jdbc.factory.connectFactory;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class ProductoController {

    public void modificar(String nombre, String descripcion, Integer id) throws SQLException {
        
        String query = "UPDATE producto SET nombre = ? ,descripcion = ? WHERE id = ? ";
        
        Connection connect = new connectFactory().create();
        
        PreparedStatement s = connect.prepareStatement(query);
        
        s.setString(1, nombre);
        s.setString(2, descripcion);
        s.setInt(3,id);
        
        boolean b = s.execute();
        
        System.out.println(s.getUpdateCount());
        connect.close();
        
    }

    public void eliminar(Integer id) throws SQLException {
        String query = "delete from producto where id = ?";
        
        Connection connect = new connectFactory().create();
        PreparedStatement statement = connect.prepareStatement(query);
        statement.setInt(1,id);
        statement.execute();
//        System.out.println(query);
        connect.close();
    }

    public List<Map<String,String>> listar() throws SQLException {

        List<Map<String,String>> productos = new ArrayList<>();
     
        Connection connect = new connectFactory().create();

        PreparedStatement statement = connect.prepareStatement("select id, nombre, descripcion, cantidad from producto");

        boolean isRead = statement.execute();
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
        
        String query = "insert into producto ( nombre, descripcion, cantidad) values (?,?,?)";
        
        Connection connect = new connectFactory().create();
        
        PreparedStatement statement = connect.prepareStatement(query);
        statement.setString(1, producto.get("nombre"));
        statement.setString(2, producto.get("descripcion"));
        statement.setInt(3, Integer.valueOf(producto.get("cantidad")));
        statement.execute();
        
        connect.close();
        
    }

}
