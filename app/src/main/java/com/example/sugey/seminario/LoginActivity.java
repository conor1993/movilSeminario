package com.example.sugey.seminario;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import com.example.sugey.seminario.modelo.Login;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity{


    TextView pas,usuario;
    Button btnEntrar;
    Login login;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        iniciarCmponentes();
        guardar();

    }

    public void iniciarCmponentes(){

        pas= (TextView)findViewById(R.id.password);
        usuario = (TextView)findViewById(R.id.email);
        btnEntrar=(Button)findViewById(R.id.btnGuardar);
    }

    public void guardar(){
        btnEntrar.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                login = new Login(pas.getText().toString(),usuario.getText().toString());
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

}

