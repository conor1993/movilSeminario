package com.example.sugey.seminario.modelo;

import com.example.sugey.seminario.baseDeDatos.Conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;

/**
 * Created by Sugey on 23/08/2016.
 */
public class Tematica {

    //atributos
    com.example.sugey.seminario.baseDeDatos.Conexion Conexion;
    PreparedStatement stmt;
    ResultSet rs;
    Tematica formt;
    int id;
    String nombre;


    //Constructor
    public  Tematica(){

    }
    public Tematica(int id, String nombre) {
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

    public LinkedList<Tematica> obtenerTematica(){


        Conexion = new Conexion();
        Connection con = Conexion.CONN();
        LinkedList<Tematica> data2 = new LinkedList<Tematica>();



        if (con == null) {


        } else {
            try {
                //instruccion

                String query = "select id , nombre from BCatTematicas";
                stmt = con.prepareStatement(query);
                rs = stmt.executeQuery();

                formt = new Tematica(0, "seleccione una opcion");
                data2.add(formt);
                while (rs.next()) {
                    int id1 = rs.getInt("id");
                    String id2 = rs.getString("nombre");
                    formt = new Tematica(id1, id2);
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