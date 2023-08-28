/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.alura.jdbc.factory;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.sql.DataSource;

/**
 *
 * @author kedwin
 */
public class connectionF{
    
    public connectionF(){
        var pooldatasource = new ComboPooledDataSource();
        
        pooldatasource.setJdbcUrl("jdbc:mysql://localhost/controlStock");
        pooldatasource.setUser("root");
        pooldatasource.setPassword("1212");
        pooldatasource.setMinPoolSize(2);
        pooldatasource.setMaxPoolSize(10);
        
        this.datasource = pooldatasource;
    }
    

    public Connection create(){
        try {
            return  this.datasource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    private DataSource datasource;
}
