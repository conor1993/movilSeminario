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

import com.example.sugey.seminario.modelo.Libros;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //elementos
        //botones
        private Button btnGuardar;
        //combos
        private Spinner cmbAutores,cmbEditorial,cmbPais;
        Libros libro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         //llenar combos
          llenarCombos();

    }




    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    private void llenarCombos(){
        libro = new Libros();
        ArrayAdapter NoCoreAdapter2;


        //llenar combo autores
        cmbAutores =   (Spinner)findViewById(R.id.cmbautor);
        NoCoreAdapter2 = new ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1, libro.obtenerAutores());
        cmbAutores.setAdapter(NoCoreAdapter2);

        //llenar combo editorial
        cmbEditorial = (Spinner)findViewById(R.id.cmbEditorial);
        NoCoreAdapter2 = new ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1, libro.obtenerEditorial());
        cmbEditorial.setAdapter(NoCoreAdapter2);

        //llenar cmb pais
        cmbPais = (Spinner)findViewById(R.id.cmbPais);
        NoCoreAdapter2 = new ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1, libro.obtenerPias());
        cmbPais.setAdapter(NoCoreAdapter2);

    }




}
