package com.example.sugey.seminario.modelo;

import com.example.sugey.seminario.baseDeDatos.Conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;

/**
 * Created by Sugey on 23/08/2016.
 */
public class Editoriales {

    //variables

    Conexion Conexion;
    PreparedStatement stmt;
    ResultSet rs;
    Editoriales edit;
    //atributos
    int id;
    String nombre;

    //Constructor
    public  Editoriales(){

    }
    public Editoriales(int id, String nombre) {
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

    public LinkedList<Editoriales> obtenerEditorial(){

        Conexion = new Conexion();
        Connection con = Conexion.CONN();
        LinkedList<Editoriales> data2 = new LinkedList<Editoriales>();


        if (con == null) {


        }else{
            try {
                //instruccion

                String query = "select id,nombre from BCatEditoriales ";
                stmt = con.prepareStatement(query);
                rs = stmt.executeQuery();

                edit = new Editoriales(0, "seleccione una opcion");
                data2.add(edit);
                while (rs.next()) {

                    int id1 = rs.getInt("id");
                    String id2 = rs.getString("nombre");
                    edit = new Editoriales(id1, id2);
                    data2.add(edit);
                }



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
