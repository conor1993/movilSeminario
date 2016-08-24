package com.example.sugey.seminario.modelo;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.sugey.seminario.baseDeDatos.Conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;


/**
 * Created by Sugey on 23/08/2016.
 */
public class Autores {

    //variables

    Conexion Conexion;
    PreparedStatement stmt;
    ResultSet rs;
    LinkedList<Autores> data2 = new LinkedList<Autores>();
    //atributos
    int id;
    String nombre;

    //Constructor
    public  Autores(){

    }
    public Autores(int id, String nombre) {
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

    public LinkedList<Autores> obtenerAutores() {

        Conexion = new Conexion();
        Connection con = Conexion.CONN();




        if (con == null) {


        } else {
            try {
                //instruccion

                String query = "select id,nombre from BCatAutores";
                stmt = con.prepareStatement(query);
                rs = stmt.executeQuery();


                while (rs.next()) {
                    int id1 = rs.getInt("id");
                    String id2 = rs.getString("nombre");
                    Autores autor = new Autores(id1, id2);
                    data2.add(autor);
                }



            } catch (Exception ex) {


            }

        }

        try {
            con.close();
        } catch (Exception ex) {

        }
        return data2;
    }


}
