package com.example.sugey.seminario.modelo;

import android.content.Context;

import com.example.sugey.seminario.baseDeDatos.Conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;

/**
 * Created by Sugey on 23/08/2016.
 */
public class Rama {

    //atributos
    Context ctx;
    com.example.sugey.seminario.baseDeDatos.Conexion Conexion;
    PreparedStatement stmt;
    ResultSet rs;
    Rama formt;
    int id;
    String nombre;


    //Constructor
    public  Rama(Context ctx){
        this.ctx =ctx;
    }
    public Rama(int id, String nombre,Context ctx) {
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

    public LinkedList<Rama> obtenerRama(){


        Conexion = new Conexion(ctx);
        Connection con = Conexion.CONN();
        LinkedList<Rama> data2 = new LinkedList<Rama>();



        if (con == null) {


        } else {
            try {
                //instruccion

                String query = "select id , nombre from BCatRamas";
                stmt = con.prepareStatement(query);
                rs = stmt.executeQuery();

                formt = new Rama(0, "seleccione opcion",ctx);
                data2.add(formt);
                while (rs.next()) {
                    int id1 = rs.getInt("id");
                    String id2 = rs.getString("nombre");
                    formt = new Rama(id1, id2,ctx);
                    data2.add(formt);
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