/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.alura.jdbc.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author kedwin
 */
public class connectFactory{

    public Connection create() throws SQLException{
        return DriverManager.getConnection("jdbc:mysql://localhost/controlStock","root","1212");  
    }
}
