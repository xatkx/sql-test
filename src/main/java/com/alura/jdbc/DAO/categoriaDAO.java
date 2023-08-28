/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.alura.jdbc.DAO;

import com.alura.jdbc.modelo.Categoria;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kedwin
 */
public class categoriaDAO {
    
    Connection con;
    
    public categoriaDAO(Connection connection){
        this.con = connection;
    }
    
    public List<Categoria> Read(){
        List<Categoria> list = new ArrayList<>();
        String query = "select id, nombre from categoria";
        try {
            final var statement = con.prepareStatement(query);
            
            try(statement)
            {
                
                statement.execute();
                ResultSet result = statement.getResultSet();
                
                while(result.next()){
                    var categoria = new Categoria(result.getNString(2));
                    categoria.setId(result.getInt(1));
                    
                    list.add(categoria);
                }
                
            }
            
        } catch (Exception e) {
            System.out.println("com.alura.jdbc.DAO.categoriaDAO.Read()");
        }
        
        return list;
    }
}
