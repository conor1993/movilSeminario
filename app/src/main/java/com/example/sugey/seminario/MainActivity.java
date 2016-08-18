package com.example.sugey.seminario;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class MainActivity extends AppCompatActivity {

    private Button btnGuardar;
    Conexion Conexion;
    private String[] datos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //metodo para llenar los combos


        Conexion = new Conexion();
        btnGuardar = (Button)findViewById(R.id.btnGuardar);
        btnGuardar.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Connection con = Conexion.CONN();
                 if (con == null) {
                     Toast.makeText(getApplicationContext(),
                             "Sin Conexion a Base de Datos",
                             Toast.LENGTH_LONG).show();
                 }
             }
         });


    }


}
