/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.alura.jdbc.modelo;

/**
 *
 * @author kedwin
 */
public class Categoria {

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return String.format("%s - %s", id,nombre);
    }
    
    
    
    
    private int id;
    private String nombre;
    
    public Categoria(String nombre){
        this.nombre = nombre;
    }
}
