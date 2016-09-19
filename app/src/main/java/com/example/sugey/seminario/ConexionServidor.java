package com.example.sugey.seminario;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import java.io.OutputStreamWriter;

public class ConexionServidor extends AppCompatActivity {

    private Button btnGuardar;
    private EditText txtip,txtpasword,txtusuario;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conexion_servidor);

        iniciarElementos();
        ecuchaBotonGuardar();
    }

    private void iniciarElementos() {
        btnGuardar =(Button)findViewById(R.id.btnGuardar);
        txtip = (EditText)findViewById(R.id.txtip);
        txtusuario=(EditText)findViewById(R.id.txtusuario);
        txtpasword =(EditText)findViewById(R.id.txtpassword);
    }

    private void ecuchaBotonGuardar() {

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ingresarDatos();
                regresar();
            }
        });
    }

    private void regresar(){
        Intent i = new Intent(ConexionServidor.this, LoginActivity.class);
        startActivity(i);
        finish();
    }

    private void ingresarDatos() {

        if(!validar())
            return;

        String datos="";
        String ip = txtip.getText().toString()+"\n";
        String usr = txtusuario.getText().toString()+"\n";
        String pasword = txtpasword.getText().toString()+"\n";
        datos = usr+ip+pasword;


        try{

            OutputStreamWriter fout=
                    new OutputStreamWriter(
                            openFileOutput("prueba_int.txt", Context.MODE_PRIVATE));

            fout.write(datos);
            fout.close();
        }
        catch (Exception ex)
        {
            Log.e("Ficheros", "Error al escribir fichero a memoria interna");
        }



    }

    private boolean validar() {
       boolean flag = true;
       String  mensaje = "";

        if (txtip.getText().toString().equals(""))
            mensaje = mensaje+"   * No se ha capturado la ip.\n";
        if (txtpasword.getText().toString().equals(""))
            mensaje = mensaje+"   * No se ha capturado la contraseña.\n";
        if (txtusuario.getText().toString().equals(""))
            mensaje = mensaje+"   * No se ha capturado el usuario.\n";

        if (!mensaje.equals("")) {
            mensaje = "Antes de guardar debe corregir lo siguiente:\n\n" + mensaje;
            Toast.makeText(getApplicationContext(), mensaje, Toast.LENGTH_LONG).show();
            flag = false;
        }
       return flag;
    }

}
