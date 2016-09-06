package com.example.sugey.seminario.modelo;

import com.example.sugey.seminario.baseDeDatos.Conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;

/**
 * Created by Sugey on 23/08/2016.
 */
public class Formato {

    //atributos
    Conexion Conexion;
    PreparedStatement stmt;
    ResultSet rs;
    Formato formt;
    int id;
    String nombre;


    //Constructor
    public  Formato(){

    }
    public Formato(int id, String nombre) {
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

    public LinkedList<Formato> obtenerFormato(){


        Conexion = new Conexion();
        Connection con = Conexion.CONN();
        LinkedList<Formato> data2 = new LinkedList<Formato>();



        if (con == null) {


        } else {
            try {
                //instruccion

                String query = "select id , FORMATO AS nombre  from bcatformato";
                stmt = con.prepareStatement(query);
                rs = stmt.executeQuery();

                formt = new Formato(0, "seleccione una opcion");
                data2.add(formt);
                while (rs.next()) {
                    int id1 = rs.getInt("id");
                    String id2 = rs.getString("nombre");
                    formt = new Formato(id1, id2);
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
