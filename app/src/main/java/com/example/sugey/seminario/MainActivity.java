package com.example.sugey.seminario;

import android.content.Context;
import android.content.Intent;
import android.hardware.camera2.params.Face;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.MonthDisplayHelper;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sugey.seminario.baseDeDatos.Conexion;
import com.example.sugey.seminario.modelo.Autores;
import com.example.sugey.seminario.modelo.Editoriales;
import com.example.sugey.seminario.modelo.Formato;
import com.example.sugey.seminario.modelo.LibroEstado;
import com.example.sugey.seminario.modelo.Libros;
import com.example.sugey.seminario.modelo.Pais;
import com.example.sugey.seminario.modelo.Rama;
import com.example.sugey.seminario.modelo.Tematica;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import android.content.DialogInterface;


public class MainActivity extends AppCompatActivity  implements OnClickListener{
        //clases
        Autores autores;
        Editoriales editorial;
        Formato formato;
        Pais pais;
        LibroEstado libEstado;
        Rama rama;
        Tematica tematica;
        ArrayAdapter NoCoreAdapter2;
        Libros libro;

    //contexto
         Context contexto = this;
        //botones
            private Button btnGuardar,scanBtn;
        //combos
            private Spinner cmbAutores,cmbEditorial,cmbPais,cmbFormato,cmbEstadoLib,cmbRama,cmbTematica;
        //textview
           private TextView txtIsbn,txtTitulo,txtMateria,txtEdicion,txtAño,txPasillo,txtEstante,txtNivel;
        //checkbox
           private CheckBox chkIlustrado,ExclusivoConsulta;
       //etiquetas
        TextView lblcodigo;

    //variables
    Autores autor;
    Conexion Conexion;
    PreparedStatement stmt;
    ResultSet rs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

            //iniciar elemntos
             iniciaElementos();
            //llenar combos
             llenarCombos();
            //metodo guardar
             escuchaBoton();
            //eventos de spiner
             cambioDeValue();


    }

    @Override
    public void onClick(View v){
        //Se responde al evento click
        if(v.getId()==R.id.scan_button){
            //Se instancia un objeto de la clase IntentIntegrator
            IntentIntegrator scanIntegrator = new IntentIntegrator(this);
            //Se procede con el proceso de scaneo
            scanIntegrator.initiateScan();
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    private void iniciaElementos() {
        lblcodigo=(TextView)findViewById(R.id.lblcodigo);
        scanBtn = (Button)findViewById(R.id.scan_button);
        scanBtn.setOnClickListener(this);
        txtTitulo = (TextView)findViewById(R.id.TxtTitulo);
        cmbAutores = (Spinner)findViewById(R.id.cmbautor);
        txtEdicion = (TextView)findViewById(R.id.Txtedicion);
        cmbEditorial =(Spinner) findViewById(R.id.cmbEditorial);
        cmbRama =(Spinner)findViewById(R.id.cmbRama);
        cmbPais =(Spinner)findViewById(R.id.cmbPais);
        cmbTematica= (Spinner)findViewById(R.id.cmbTematica);
        txtAño =(TextView)findViewById(R.id.TxtAño);
        txPasillo = (TextView)findViewById(R.id.TxtPasillo);
        txtEstante =(TextView)findViewById(R.id.Txtestante);
        txtNivel  =(TextView)findViewById(R.id.Txtnivel);
        txtIsbn = (TextView)findViewById(R.id.TxtIsbn);
        chkIlustrado = (CheckBox)findViewById(R.id.ChkIlustrado);
        ExclusivoConsulta = (CheckBox)findViewById(R.id.ChkExclusivoConsulta);
        txtMateria = (TextView)findViewById(R.id.TxtImateria);
        cmbFormato = (Spinner)findViewById(R.id.cmbFormato);
        cmbEstadoLib=(Spinner)findViewById(R.id.cmbEstado);
    }

    private void llenarCombos(){



        //llenar combo autores
            autores = new Autores();
            NoCoreAdapter2 = new ArrayAdapter(contexto, android.R.layout.simple_spinner_item, autores.obtenerAutores());
            cmbAutores.setAdapter(NoCoreAdapter2);
       //llenar combo de editoriales
            editorial = new Editoriales();

            NoCoreAdapter2 = new ArrayAdapter(contexto, android.R.layout.simple_spinner_item, editorial.obtenerEditorial());
            cmbEditorial.setAdapter(NoCoreAdapter2);
        //llenar combo de paises
           pais = new Pais();

           NoCoreAdapter2 = new ArrayAdapter(contexto, android.R.layout.simple_spinner_item,pais.obtenerPias());
           cmbPais.setAdapter(NoCoreAdapter2);
        //llenar combo de Formato
            formato = new Formato();

            NoCoreAdapter2 = new ArrayAdapter(contexto, android.R.layout.simple_spinner_item, formato.obtenerFormato());
            cmbFormato.setAdapter(NoCoreAdapter2);

          //llenarcombo estadoLibroi
            libEstado = new LibroEstado();

            NoCoreAdapter2 = new ArrayAdapter(contexto, android.R.layout.simple_spinner_item, libEstado.obtenerEstadoLibro());
            cmbEstadoLib.setAdapter(NoCoreAdapter2);

            //llenarcombo ramas
             rama= new Rama();

            NoCoreAdapter2 = new ArrayAdapter(contexto, android.R.layout.simple_spinner_item, rama.obtenerRama());
            cmbRama.setAdapter(NoCoreAdapter2);

            //llenarcombo tematicas
            tematica= new Tematica();

            NoCoreAdapter2 = new ArrayAdapter(contexto, android.R.layout.simple_spinner_item, tematica.obtenerTematica());
            cmbTematica.setAdapter(NoCoreAdapter2);

    }

    private void escuchaBoton() {
        //evento al presionar el boton guardar

        //
        //bono guardar
        btnGuardar =(Button)findViewById(R.id.btnGuardar);
        btnGuardar.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                guardar();
            }

        });
    }

    private void guardar(){
        libro = new Libros();
        try{

            // se obtienen los datos de los campos
            libro.setTitulo(txtTitulo.getText().toString());
            libro.setIdAutor(((Autores)cmbAutores.getSelectedItem()).getId());
            libro.setEdicion(txtEdicion.getText().toString());
            libro.setIdEditorial(((Editoriales)cmbEditorial.getSelectedItem()).getId());
            libro.setIdRama(((Rama)cmbRama.getSelectedItem()).getId());
            libro.setIdTematica(((Tematica)cmbTematica.getSelectedItem()).getId());
            libro.setIdPais(((Pais)cmbPais.getSelectedItem()).getId());
            libro.setAño(Integer.parseInt(txtAño.getText().toString()));
            libro.setPasillo(Integer.parseInt(txPasillo.getText().toString()));
            libro.setEstantent(Integer.parseInt(txtEstante.getText().toString()));
            libro.setNivel(Integer.parseInt(txtNivel.getText().toString()));
            libro.setISBN(txtIsbn.getText().toString());
            if (chkIlustrado.isChecked()){libro.setIlustrado(true);}else{libro.setIlustrado(false);}
            if (ExclusivoConsulta.isChecked()){libro.setExclusivoConsulta(true);}else{libro.setExclusivoConsulta(false);}
            libro.setMateria( txtMateria.getText().toString());
            libro.setFormato(((Formato)cmbFormato.getSelectedItem()).getId());
            libro.setEstatus(((LibroEstado)cmbEstadoLib.getSelectedItem()).getId());

            //si no ecunta con isbn entonses se creara una etiqueta de lo contrario  la etiqueta seria
            //isbn mas consecutivo
            String isb = txtIsbn.getText().toString();
            if (lblcodigo.getText().equals("")){
                if(!isb.equals("")){
                    libro.setNumero(txtIsbn.getText().toString()+libro.cantidadLibros(txtIsbn.getText().toString()));
                }else{

                    int aut = ((Autores)cmbAutores.getSelectedItem()).getId();
                    int edit = ((Editoriales)cmbEditorial.getSelectedItem()).getId();
                    String cant  = Integer.toString(libro.cantidadLibros2(txtTitulo.getText().toString(),aut,edit));
                    String eiqueta = crearEtiqueta();
                    String et = cant+eiqueta;
                    libro.setNumero(et);

                }
            }else{
                libro.setNumero(lblcodigo.getText().toString());
            }

            libro.Guardar();
            Toast.makeText(getApplicationContext(),"se guardo con exito",Toast.LENGTH_LONG).show();
        }catch (Exception ex){
            Toast.makeText(getApplicationContext(),ex.getMessage(),Toast.LENGTH_LONG).show();
        }finally {
            limpiarCampos();
        }
    }

    private void limpiarCampos(){
        txtTitulo.setText("");
        txtEdicion.setText("");
        txtAño.setText("");
        txPasillo.setText("");
        txtEstante.setText("");
        txtNivel.setText("");
        txtIsbn.setText("");
        chkIlustrado.setChecked(false);
        ExclusivoConsulta.setChecked(false);
        txtMateria.setText("");
        lblcodigo.setText("");
        cmbAutores.setSelection(0);
        cmbRama.setSelection(0);
        cmbEditorial.setSelection(0);
        cmbEstadoLib.setSelection(0);
        cmbFormato.setSelection(0);
        cmbPais.setSelection(0);
        cmbTematica.setSelection(0);

    }

    private String crearEtiqueta(){
        String Etiqueta="";

         Etiqueta = Etiqueta = txtTitulo.getText().toString().substring(0,1)+txtTitulo.getText().toString().substring(1,2)+((Autores)cmbAutores.getSelectedItem()).toString().substring(0,1)+((Autores)cmbAutores.getSelectedItem()).toString().substring(1,2)+((Editoriales)cmbEditorial.getSelectedItem()).toString().substring(0,1)+((Editoriales)cmbEditorial.getSelectedItem()).toString().substring(1,2)+((Editoriales)cmbEditorial.getSelectedItem()).toString().substring(2,3)+((Rama)cmbRama.getSelectedItem()).toString().substring(0,1)+((Rama)cmbRama.getSelectedItem()).toString().substring(1,2)+((Rama)cmbRama.getSelectedItem()).toString().substring(2,3)+ txtEdicion.getText().toString();
        return Etiqueta;
    }

    private void cambioDeValue() {

        //  cambio de valor de un valor de item de spiner
         cmbAutores.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
             @Override
             public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                 if (!txtTitulo.getText().toString().equals("") && txtIsbn.getText().toString().equals("")) {
                     comprobarExistencia(1);
                 }
             }

             @Override
             public void onNothingSelected(AdapterView<?> parent) {

             }
         });

        cmbEditorial.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (!txtTitulo.getText().toString().equals("") && txtIsbn.getText().toString().equals("")) {
                    comprobarExistencia(1);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        txtIsbn.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    comprobarExistencia(2);
                }
            }
        });

    }

    private void  comprobarExistencia(int seleccion){
        libro = new Libros();
        boolean flag = false;

        if(seleccion == 1){flag = libro.obtenerLibros(txtTitulo.getText().toString(),((Autores)cmbAutores.getSelectedItem()).getId(),((Editoriales)cmbEditorial.getSelectedItem()).getId());}
        if(seleccion == 2){flag = libro.obtenerLibrosisbn(txtIsbn.getText().toString());}

        if(flag == true){
            ventanaConfirmacion();
            Conexion = new Conexion();
            Connection con = Conexion.CONN();


            if (con == null) {


            } else {
                try {
                    //instruccion
                    String query="";
                    if(seleccion == 1){
                         query = "select top 1 * from BInvLibros where titulo = '"+txtTitulo.getText().toString()+"'  and  idautor = "+((Autores)cmbAutores.getSelectedItem()).getId()+" and ideditorial ="+((Editoriales)cmbEditorial.getSelectedItem()).getId();
                    } else if (seleccion == 2) {
                         query = "select top 1 * from BInvLibros where isbn = "+txtIsbn.getText().toString();

                    }


                    stmt = con.prepareStatement(query);
                    rs = stmt.executeQuery();
                    while(rs.next()){

                        txtIsbn.setText(rs.getString("isbn"));
                        txtTitulo.setText(rs.getString("titulo"));
                        txtMateria.setText(rs.getString("materia"));
                        txtEdicion.setText(rs.getString("edicion"));
                        txtAño.setText(Integer.toString(rs.getInt("año")));
                        txPasillo.setText(Integer.toString(rs.getInt("pasillo")));
                        txtEstante.setText(Integer.toString(rs.getInt("estante")));
                        txtNivel.setText(Integer.toString(rs.getInt("nivel")));
                        chkIlustrado.setChecked(rs.getBoolean("ilustrado"));

                        ExclusivoConsulta.setChecked(rs.getBoolean("ExclusivoConsulta"));

                        for(int i = 0;i< cmbRama.getCount(); i++ ){
                            if(((Rama)cmbRama.getItemAtPosition(i)).getId() == rs.getInt("idrama")){
                                cmbRama.setSelection(i);
                            }
                        }
                        for(int i = 0;i< cmbTematica.getCount(); i++ ){
                            if(((Tematica)cmbTematica.getItemAtPosition(i)).getId() == rs.getInt("idtematica")){
                                cmbTematica.setSelection(i);
                            }
                        }
                        for(int i = 0;i< cmbFormato.getCount(); i++ ){
                            if(((Formato)cmbFormato.getItemAtPosition(i)).getId() == rs.getInt("formato")){
                                cmbFormato.setSelection(i);
                            }
                        }
                        for(int i = 0;i< cmbEstadoLib.getCount(); i++ ){
                            if(((LibroEstado)cmbEstadoLib.getItemAtPosition(i)).getId() == rs.getInt("estatus")){
                                cmbEstadoLib.setSelection(i);
                            }
                        }
                        for(int i = 0;i< cmbPais.getCount(); i++ ){
                            if(((Pais)cmbPais.getItemAtPosition(i)).getId() == rs.getInt("idpais")){
                                cmbPais.setSelection(i);
                            }
                        }
                        for(int i = 0;i< cmbEditorial.getCount(); i++ ){
                            if(((Editoriales)cmbEditorial.getItemAtPosition(i)).getId() == rs.getInt("ideditorial")){
                                cmbEditorial.setSelection(i);
                            }
                        }
                        for(int i = 0;i< cmbAutores.getCount(); i++ ){
                            if(((Autores)cmbAutores.getItemAtPosition(i)).getId() == rs.getInt("idautor")){
                                cmbAutores.setSelection(i);
                            }
                        }



                    }
                } catch (Exception ex) {
                    System.out.println ("El error es  = " + ex.getMessage());
                    ex.printStackTrace();
                }
            }
            try {
                con.close();
            } catch (Exception ex) {

            }
        }

    }

    private void ventanaConfirmacion(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("El libro ya existe desea agregar uno?")
                .setTitle("Advertencia")
                .setCancelable(false)
                .setNegativeButton("No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                limpiarCampos();
                                dialog.cancel();
                            }
                        })
                .setPositiveButton("Si",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                guardar();
                            }
                        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        //Se obtiene el resultado del proceso de scaneo y se parsea
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        if (scanningResult != null) {
            //Quiere decir que se obtuvo resultado pro lo tanto:
            //Desplegamos en pantalla el contenido del código de barra scaneado
            String scanContent = scanningResult.getContents();
            lblcodigo.setText(scanContent);

        }else{
            //Quiere decir que NO se obtuvo resultado
            Toast toast = Toast.makeText(getApplicationContext(),
                    "No se ha recibido datos del scaneo!", Toast.LENGTH_SHORT);
            toast.show();
        }
    }


}
