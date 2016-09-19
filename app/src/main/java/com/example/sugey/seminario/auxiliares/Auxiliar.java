package com.example.sugey.seminario.auxiliares;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by carlos on 14/09/2016.
 */
public class Auxiliar {

    String [] datos = new String[10];

    Context contexto;

    public Auxiliar(Context ctx){
        this.contexto = ctx;
    }


    public String[] obtenerDatosServidor(){

        try
        {
            BufferedReader fin =
                    new BufferedReader(
                            new InputStreamReader(
                                    contexto.openFileInput("prueba_int.txt")));
            String us;
            int i =0;
            while((us = fin.readLine()) != null){
                System.out.print(us);
                datos[i]=us;
                i++;
            }
            fin.close();
        }
        catch (Exception ex)
        {
            Log.e("Ficheros", "Error al leer fichero desde memoria interna");
        }
        return datos;
    }

}
