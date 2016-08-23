package com.example.sugey.seminario.modelo;

/**
 * Created by Sugey on 22/08/2016.
 */
public class comidas {

    int id;
    String nombre;
    //Constructor
    public comidas(int id, String nombre) {
        super();
        this.id = id;
        this.nombre = nombre;
    }
    @Override
    public String toString() {
        return nombre;
    }
    public int getId() {
        return id;
    }
}