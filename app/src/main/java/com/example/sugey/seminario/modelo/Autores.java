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
    Context ctx;
    Autores autor;
    Conexion Conexion;
    PreparedStatement stmt;
    ResultSet rs;
    LinkedList<Autores> data2 = new LinkedList<Autores>();
    //atributos
    int id;
    String nombre;

    //Constructor
    public  Autores(Context ctx){
      this.ctx = ctx;
    }

    public Autores(int id, String nombre,Context ctx) {
        super();
        this.id = id;
        this.nombre = nombre;
        this.ctx = ctx;
    }

    @Override
    public String toString() {
        return nombre;
    }

    public int getId() {
        return id;
    }

    public LinkedList<Autores> obtenerAutores() {

        Conexion = new Conexion(ctx);
        Connection con = Conexion.CONN();




        if (con == null) {


        } else {
            try {
                //instruccion

                String query = "select id,nombre from BCatAutores";
                stmt = con.prepareStatement(query);
                rs = stmt.executeQuery();

                autor = new Autores(0, "seleccione una opcion",ctx);
                data2.add(autor);
                while (rs.next()) {
                    int id1 = rs.getInt("id");
                    String id2 = rs.getString("nombre");
                    autor = new Autores(id1, id2,ctx);
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
