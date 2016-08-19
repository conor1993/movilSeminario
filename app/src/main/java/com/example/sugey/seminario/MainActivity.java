package com.example.sugey.seminario;

import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Button btnGuardar;


    private Spinner cmbAutores;
    Consultas consulta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //inicia clase que contiene todas las consultas
         consulta = new Consultas();
        //se inician lementos de la actividad que se usaran
         cmbAutores = (Spinner)findViewById(R.id.cmbautor);

       //se crea un adaptador con los datos resultantes del metodo de la consulta contenida en consultas - se llena el combo
          ArrayAdapter NoCoreAdapter2 = new ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1, consulta.buscarAutores());
          cmbAutores.setAdapter(NoCoreAdapter2);

    }


    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }




}
