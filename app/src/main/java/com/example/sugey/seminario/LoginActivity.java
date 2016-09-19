package com.example.sugey.seminario;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import com.example.sugey.seminario.modelo.Login;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class LoginActivity extends AppCompatActivity{

    TextView pas,usuario ,lblSeguridad;
    Button btnEntrar;
    Login login;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        iniciarCmponentes();
        vistaDeConexion();
        guardar();

    }

    public void iniciarCmponentes(){

        pas= (TextView)findViewById(R.id.password);
        usuario = (TextView)findViewById(R.id.email);
        btnEntrar=(Button)findViewById(R.id.btnGuardar);
        lblSeguridad =(TextView)findViewById(R.id.lblseguridad);

    }

    public void guardar(){
        try
        {
            BufferedReader fin =
                    new BufferedReader(
                            new InputStreamReader(
                                    openFileInput("prueba_int.txt")));

            String us = fin.readLine();
            fin.close();
        }
        catch (Exception ex)
        {
            Log.e("Ficheros", "Error al leer fichero desde memoria interna");
        }


        btnEntrar.setOnClickListener(new OnClickListener() {

            public void onClick(View view) {
                login = new Login(pas.getText().toString(),usuario.getText().toString(),getApplicationContext());
                if(login.logearse() == true) {
                    Intent i = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(i);
                    finish();
                }else{
                    Toast.makeText(getApplicationContext(),"contraseña o usuario incorrecto",Toast.LENGTH_LONG).show();
                }
            }
        });




    }

    private void vistaDeConexion() {
        lblSeguridad.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, ConexionServidor.class);
                startActivity(i);
                finish();
            }
        });

    }

}

