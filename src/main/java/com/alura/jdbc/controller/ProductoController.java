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

        final Connection connect = new connectFactory().create();

        try (connect) {
            PreparedStatement s = connect.prepareStatement(query);
            s.setString(1, nombre);
            s.setString(2, descripcion);
            s.setInt(3, id);
            boolean b = s.execute();
        }
    }

    public void eliminar(Integer id) throws SQLException {
        String query = "delete from producto where id = ?";

        final Connection connect = new connectFactory().create();

        try (connect) {
            PreparedStatement statement = connect.prepareStatement(query);
            statement.setInt(1, id);
            statement.execute();
//        System.out.println(query);
        }
    }

    public List<Map<String, String>> listar() throws SQLException {

        List<Map<String, String>> productos = new ArrayList<>();

        final Connection connect = new connectFactory().create();

        try (connect) {
            final PreparedStatement statement = connect.prepareStatement("select id, nombre, descripcion, cantidad from producto");

            try (statement) {
                boolean isRead = statement.execute();
                if (isRead) {

                    ResultSet result = statement.getResultSet();

                    while (result.next()) {
                        Map<String, String> producto = new HashMap<String, String>();

                        producto.put("id", result.getString(1));
                        producto.put("nombre", result.getString(2));
                        producto.put("descripcion", result.getString(3));
                        producto.put("cantidad", result.getString(4));

//                result.
                        productos.add(producto);

//                System.out.println(result.getString(1)+result.getString(1));
                    }
                }

//            System.out.println("klk");
            }
            return productos;
        }
    }

    public void guardar(Map<String, String> producto) throws SQLException {
        int maxCantidad = 50;
        int cantidad = Integer.valueOf(producto.get("cantidad"));
        String query = "insert into producto ( nombre, descripcion, cantidad) values (?,?,?)";

        final Connection connect = new connectFactory().create();
        try (connect) {
            connect.setAutoCommit(false);
            final PreparedStatement statement = connect.prepareStatement(query);

            try (statement) {
                while (cantidad > 0) {

                    int resto = Math.min(cantidad, maxCantidad);
                    guardarDatos(producto, resto, statement);
                    cantidad -= maxCantidad;
                }
                System.out.println("success");
            } catch (Exception e) {

                connect.rollback();
                System.out.println("rollback");
            }
            connect.commit();

        }

    }

    public void guardarDatos(Map<String, String> producto, int resto, PreparedStatement statement) throws SQLException {
//        if(resto < 50)
//        {
//            throw new RuntimeException("esto es un error mmg");
//        }

        statement.setString(1, producto.get("nombre"));
        statement.setString(2, producto.get("descripcion"));
        statement.setInt(3, resto);
        statement.execute();
    }

}
