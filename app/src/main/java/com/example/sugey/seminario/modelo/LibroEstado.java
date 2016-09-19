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
public class LibroEstado {
    //atributos
    Context ctx;
    Conexion Conexion;
    PreparedStatement stmt;
    ResultSet rs;
    LibroEstado formt;
    int id;
    String nombre;


    //Constructor
    public  LibroEstado(Context ctx){
            this.ctx = ctx;
    }
    public LibroEstado(int id, String nombre,Context ctx) {
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

    public LinkedList<LibroEstado> obtenerEstadoLibro(){


        Conexion = new Conexion(ctx);
        Connection con = Conexion.CONN();
        LinkedList<LibroEstado> data2 = new LinkedList<LibroEstado>();



        if (con == null) {


        } else {
            try {
                //instruccion

                String query = "select id , Estado as nombre from bcatestatus";
                stmt = con.prepareStatement(query);
                rs = stmt.executeQuery();

                formt = new LibroEstado(0, "seleccione una opcion",ctx);
                data2.add(formt);
                while (rs.next()) {
                    int id1 = rs.getInt("id");
                    String id2 = rs.getString("nombre");
                    formt = new LibroEstado(id1, id2,ctx);
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
