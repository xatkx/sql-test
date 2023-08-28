package com.alura.jdbc.controller;

import com.alura.jdbc.DAO.ProductoDAO;
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

    ProductoDAO CRUD;
    public ProductoController(){
        CRUD = new ProductoDAO(new connectionF().create());
    }
    public void modificar(Producto producto) {
        CRUD.Update(producto);

    }

    public void eliminar(Integer id) {
        CRUD.Delete(id);
    }

    public List<Producto> listar()  {
        return CRUD.Read();
    }

    public void guardar(Producto producto){
        CRUD.Create(producto);
    }

}
