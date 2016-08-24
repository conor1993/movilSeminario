package com.example.sugey.seminario.modelo;

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
    Conexion Conexion;
    PreparedStatement stmt;
    ResultSet rs;
    //atributos
    int id;
    String nombre;

    //Constructor
    public  Pais(){

    }
    public Pais(int id, String nombre) {
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

    public LinkedList<Pais> obtenerPias(){


        Conexion = new Conexion();
        Connection con = Conexion.CONN();
        LinkedList<Pais> data2 = new LinkedList<Pais>();



        if (con == null) {


        } else {
            try {
                //instruccion

                String query = "select id,nombre from CatPaises";
                stmt = con.prepareStatement(query);
                rs = stmt.executeQuery();


                while (rs.next()) {
                    int id1 = rs.getInt("id");
                    String id2 = rs.getString("nombre");
                    Pais pais = new Pais(id1, id2);
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
