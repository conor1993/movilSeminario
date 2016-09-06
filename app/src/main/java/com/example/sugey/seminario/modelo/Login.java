package com.example.sugey.seminario.modelo;

import com.example.sugey.seminario.baseDeDatos.Conexion;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;

/**
 * Created by Sugey on 25/08/2016.
 */
public class Login {

    //
    Conexion Conexion;
    int log;
    PreparedStatement stmt;
    ResultSet rs;
    CallableStatement cst;
    //propiedades
    String password;
    String usuario;

    public Login(String password, String usuario) {
        this.password = password;
        this.usuario = usuario;
    }

    public Login(){

    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }


    public boolean logearse() {
        Boolean logeo = false;
        log=0;
         //llamamos a un procedimiento almacenado que nose debuelbe 1 o 0 dependiendo si es correcta lña contraseña y el usuario y retoornamos ese valor
        Conexion = new Conexion();
        Connection con = Conexion.connSeguridad();
        if (con == null) {


        } else {
            try {


                cst = con.prepareCall("{call verificarContrasena(?,?,?)}");
                cst.setString(1,usuario);
                cst.setString(2,password);
                cst.registerOutParameter (3, Types.INTEGER);
                cst.executeUpdate();
                log = cst.getInt(3);
                cst.close();
            } catch (Exception ex) {

                System.out.println ("El error es  = " + ex.getMessage());
                ex.printStackTrace();

            }finally {
                try {
                    con.close();
                }catch (Exception ex){

                }
            }
        }


        if (log == 1){

              logeo=true;
          }

        return logeo;
    }

}
