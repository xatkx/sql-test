package com.alura.jdbc.controller;

import com.alura.jdbc.factory.connectionF;
import com.alura.jdbc.modelo.Producto;
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

    public void modificar(Producto producto) throws SQLException {

        String query = "UPDATE producto SET nombre = ? ,descripcion = ?, cantidad = ? WHERE id = ? ";

        final Connection connect = new connectionF().create();

        try (connect) {
            PreparedStatement s = connect.prepareStatement(query);
            s.setString(1, producto.getNombre());
            s.setString(2, producto.getDescripcion());
            s.setInt(3, producto.getCantidad());
            s.setInt(4, producto.getId());
            boolean b = s.execute();
        }
    }

    public void eliminar(Integer id) throws SQLException {
        String query = "delete from producto where id = ?";

        final Connection connect = new connectionF().create();

        try (connect) {
            PreparedStatement statement = connect.prepareStatement(query);
            statement.setInt(1, id);
            statement.execute();
//        System.out.println(query);
        }
    }

    public List<Producto> listar() throws SQLException {

        List<Producto> productos = new ArrayList<>();

        final Connection connect = new connectionF().create();

        try (connect) {
            final PreparedStatement statement = connect.prepareStatement("select id, nombre, descripcion, cantidad from producto");

            try (statement) {
                boolean isRead = statement.execute();
                if (isRead) {

                    ResultSet result = statement.getResultSet();

                    while (result.next()) {
                       var producto = new Producto(
                               result.getString(2),
                               result.getString(3),
                               result.getInt(4)
                               );
                       producto.setId(result.getInt(1));
                        productos.add(producto);

//                System.out.println(result.getString(1)+result.getString(1));
                    }
                }

//            System.out.println("klk");
            }
            return productos;
        }
    }

    public void guardar(Producto producto) throws SQLException {
        int maxCantidad = 50;
        int cantidad = producto.getCantidad();
        String query = "insert into producto ( nombre, descripcion, cantidad) values (?,?,?)";

        final Connection connect = new connectionF().create();
        try (connect) {
            connect.setAutoCommit(false);
            final PreparedStatement statement = connect.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);

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

    public void guardarDatos(Producto producto, int resto, PreparedStatement statement) throws SQLException {
//        if(resto < 50)
//        {
//            throw new RuntimeException("esto es un error mmg");
//        }

        statement.setString(1, producto.getNombre());
        statement.setString(2, producto.getDescripcion());
        statement.setInt(3, resto);
        
        statement.execute();
        
        final ResultSet result = statement.getGeneratedKeys();

        try (result) {

            while (result.next()) {
                int id = result.getInt(1);
                producto.setId(id);
                System.out.println(producto);
            }
        }
    }

}
