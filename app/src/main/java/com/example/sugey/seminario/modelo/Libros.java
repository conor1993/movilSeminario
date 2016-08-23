package com.example.sugey.seminario.modelo;

import com.example.sugey.seminario.baseDeDatos.Conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * Created by Sugey on 19/08/2016.
 */
public class Libros {

    //variables

    Conexion Conexion;
    PreparedStatement stmt;
    ResultSet rs;

    //atributos
    int id;
    String nombre;
    //Constructor
    public Libros(int id, String nombre) {
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

    public ArrayList<String> obtenerAutores(){

        Conexion = new Conexion();
        Connection con = Conexion.CONN();
        ArrayList<String> data2 = new ArrayList<String>();
        LinkedHashMap<String, String> mapData = new LinkedHashMap<String, String>();
        data2.add(0,"Seleccionar Autor..");

        if (con == null) {


        }else{
            try {
                //instruccion

                String query = "select id,nombre from BCatAutores";
                stmt = con.prepareStatement(query);
                rs = stmt.executeQuery();


                while (rs.next()) {
                    int   id1 =  rs.getInt("id");
                    String id2 = rs.getString("nombre");
                    data2.add(id1,id2);
                }
                String[] array2 = data2.toArray(new String[0]);


            }catch(Exception ex){



            }

        }

        try {
            con.close();
        }catch (Exception ex){

        }
        return data2;
    }

    public ArrayList<String> obtenerEditorial(){

        Conexion = new Conexion();
        Connection con = Conexion.CONN();
        ArrayList<String> data2 = new ArrayList<String>();
        data2.add(0,"Seleccionar Editorial..");

        if (con == null) {


        }else{
            try {
                //instruccion

                String query = "select id,nombre from BCatEditoriales ";
                stmt = con.prepareStatement(query);
                rs = stmt.executeQuery();


                while (rs.next()) {

                    int   id1 =  rs.getInt("id");
                    String id2 = rs.getString("nombre");
                    data2.add(id1,id2);
                }
                String[] array2 = data2.toArray(new String[0]);


            }catch(Exception ex){



            }

        }

        try {
            con.close();
        }catch (Exception ex){

        }
        return data2;
    }

    public ArrayList<String> obtenerPias(){

        Conexion = new Conexion();
        Connection con = Conexion.CONN();
        ArrayList<String> data2 = new ArrayList<String>();
        data2.add(0,"Seleccionar Autor..");

        if (con == null) {


        }else{
            try {
                //instruccion

                String query = "select nombre from CatPaises ";
                stmt = con.prepareStatement(query);
                rs = stmt.executeQuery();


                while (rs.next()) {
                    String id2 = rs.getString("nombre");
                    data2.add(id2);
                }
                String[] array2 = data2.toArray(new String[0]);


            }catch(Exception ex){



            }

        }
        try {
            con.close();
        }catch (Exception ex){

        }
        return data2;
    }

}
