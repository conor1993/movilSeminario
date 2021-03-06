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
public class Pais {

    //variables
    Context ctx;
    Conexion Conexion;
    PreparedStatement stmt;
    ResultSet rs;
    Pais pais;
    //atributos
    int id;
    String nombre;

    //Constructor
    public  Pais(Context ctx){
        this.ctx = ctx;
    }
    public Pais(int id, String nombre,Context ctx) {
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

    public LinkedList<Pais> obtenerPias(){


        Conexion = new Conexion(ctx);
        Connection con = Conexion.CONN();
        LinkedList<Pais> data2 = new LinkedList<Pais>();



        if (con == null) {


        } else {
            try {
                //instruccion

                String query = "select id,nombre from CatPaises";
                stmt = con.prepareStatement(query);
                rs = stmt.executeQuery();

                pais = new Pais(0, "seleccione una opcion",ctx);
                data2.add(pais);
                while (rs.next()) {
                    int id1 = rs.getInt("id");
                    String id2 = rs.getString("nombre");
                    pais = new Pais(id1, id2,ctx);
                    data2.add(pais);
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
