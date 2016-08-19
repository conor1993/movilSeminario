package com.example.sugey.seminario;

import android.widget.Spinner;

import com.example.sugey.seminario.baseDeDatos.Conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * Created by Sugey on 19/08/2016.
 */

public class Consultas {
    private String[] datos;
    com.example.sugey.seminario.baseDeDatos.Conexion Conexion;
    PreparedStatement stmt;
    ResultSet rs;
    private Spinner cmbAutores;

    public ArrayList<String> buscarAutores(){

        Conexion = new Conexion();
        Connection con = Conexion.CONN();
        ArrayList<String> data2 = new ArrayList<String>();
        data2.add(0,"Seleccionar Autor..");

        if (con == null) {


        }else{
            try {
                //instruccion

                String query = "select nombre from BCatAutores";
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
        return data2;
    }
}
