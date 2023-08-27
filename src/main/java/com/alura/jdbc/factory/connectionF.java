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
        
        this.datasource = pooldatasource;
    }
    

    public Connection create() throws SQLException{
        return  this.datasource.getConnection();
    }
    
    private DataSource datasource;
}
