/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.alura.jdbc.DAO;

import com.alura.jdbc.factory.connectionF;
import com.alura.jdbc.modelo.Producto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kedwin
 */
public class ProductoDAO {

    public ProductoDAO(Connection con) {
        this.connection = con;
    }

    public void Create(Producto producto) {
        int maxCantidad = 50;
        int cantidad = producto.getCantidad();
        String query = "insert into producto ( nombre, descripcion, cantidad) values (?,?,?)";

        try (connection) {
            connection.setAutoCommit(false);
            final PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            try (statement) {
                while (cantidad > 0) {

                    int resto = Math.min(cantidad, maxCantidad);
                    guardarDatos(producto, resto, statement);
                    cantidad -= maxCantidad;
                }
                System.out.println("success");
            } catch (Exception e) {

                connection.rollback();
                System.out.println("rollback");
            }
            connection.commit();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void guardarDatos(Producto producto, int resto, PreparedStatement statement) {
        try {
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
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Producto> Read(){
        List<Producto> productos = new ArrayList<>();

        try (connection) {
            final PreparedStatement statement = connection.prepareStatement("select id, nombre, descripcion, cantidad from producto");

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

        }catch(SQLException e){
            throw new RuntimeException(e);
        }
        
        return productos;
    }

    public void Update(Producto producto){
        String query = "UPDATE producto SET nombre = ? ,descripcion = ?, cantidad = ? WHERE id = ? ";

        try (connection) {
            PreparedStatement s = connection.prepareStatement(query);
            s.setString(1, producto.getNombre());
            s.setString(2, producto.getDescripcion());
            s.setInt(3, producto.getCantidad());
            s.setInt(4, producto.getId());
            boolean b = s.execute();
        }catch(SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

    ;
   
   public void Delete(Integer id){
        String query = "delete from producto where id = ?";

        try (connection) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            statement.execute();
        }catch(SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

    final private Connection connection;
}
