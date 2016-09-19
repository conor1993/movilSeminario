package com.example.sugey.seminario.baseDeDatos;



import android.annotation.SuppressLint;
import android.content.Context;
import android.os.StrictMode;
import android.util.Log;

import com.example.sugey.seminario.auxiliares.Auxiliar;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;


public class Conexion {

    String [] datos = new String[10];
    String classs = "net.sourceforge.jtds.jdbc.Driver";
    public static String Serguridad="ILSEGURIDADDIOC";
    String db = "bdsemprovoc";

    String ip="";
    String un = "";
    String password = "";


    //clase auxiliar
     Auxiliar aux;

    public Conexion(Context ctx){
        aux = new Auxiliar(ctx);
        datos = aux.obtenerDatosServidor();
        this.un =  datos[0];
        this.ip = datos[1];
        this.password = datos[2];
    }


    @SuppressLint("NewApi")
    public Connection CONN() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection conn = null;
        String ConnURL = null;
        try {
            Class.forName(classs);
            ConnURL = "jdbc:jtds:sqlserver://" + ip + ";"
                    + "databaseName="+db+";user=" + un + ";password="
                    + password + ";";

            conn = DriverManager.getConnection(ConnURL);
        } catch (SQLException se) {
            Log.e("ERRO", se.getMessage());
        } catch (ClassNotFoundException e) {
            Log.e("ERRO", e.getMessage());
        } catch (Exception e) {
            Log.e("ERRO", e.getMessage());

        }

        return conn;
    }

    public Connection connSeguridad() {

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection conn = null;
        String ConnURL = null;
        try {
            Class.forName(classs);
            ConnURL = "jdbc:jtds:sqlserver://" + ip + ";"
                    + "databaseName="+Serguridad+";user=" + un + ";password="
                    + password + ";";

            conn = DriverManager.getConnection(ConnURL);
        } catch (SQLException se) {
            Log.e("ERRO", se.getMessage());
        } catch (ClassNotFoundException e) {
            Log.e("ERRO", e.getMessage());
        } catch (Exception e) {
            Log.e("ERRO", e.getMessage());

        }

        return conn;
    }




}
