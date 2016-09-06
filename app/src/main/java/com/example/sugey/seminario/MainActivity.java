package com.example.sugey.seminario;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.example.sugey.seminario.modelo.Autores;
import com.example.sugey.seminario.modelo.Editoriales;
import com.example.sugey.seminario.modelo.Formato;
import com.example.sugey.seminario.modelo.LibroEstado;
import com.example.sugey.seminario.modelo.Libros;
import com.example.sugey.seminario.modelo.Pais;
import com.example.sugey.seminario.modelo.Rama;
import com.example.sugey.seminario.modelo.Tematica;


public class MainActivity extends AppCompatActivity {
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
            private Button btnGuardar;
        //combos
            private Spinner cmbAutores,cmbEditorial,cmbPais,cmbFormato,cmbEstadoLib,cmbRama,cmbTematica;
        //textview
           private TextView txtIsbn,txtTitulo,txtMateria,txtEdicion,txtAño,txPasillo,txtEstante,txtNivel;
        //checkbox
           private CheckBox chkIlustrado,ExclusivoConsulta;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

            //iniciar elemntos
             iniciaElementos();
            //llenar combos
             llenarCombos();
            //metodo guardar
             GuardarLibro();
            //eventos de spiner
             cambioDeValue();

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    private void iniciaElementos() {
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

    private void GuardarLibro() {
        //evento al presionar el boton guardar

        //
        //bono guardar
        btnGuardar =(Button)findViewById(R.id.btnGuardar);
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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

                    libro.Guardar();
                    Toast.makeText(getApplicationContext(),"se guardo con exito",Toast.LENGTH_LONG).show();
                }catch (Exception ex){
                    Toast.makeText(getApplicationContext(),ex.getMessage(),Toast.LENGTH_LONG).show();
                }finally {
                       limpiarCampos();
                }
            }
        });
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

    }

    private String crearEtiqueta(){
        String Etiqueta="";

         String ti = txtTitulo.getText().toString();
         String au = cmbAutores.getSelectedItem().toString();
         String ed = cmbEditorial.getSelectedItem().toString();
         String ra = cmbRama.getSelectedItem().toString();
         String ei = txtEdicion.getText().toString();
         Etiqueta = ti.substring(0)+ti.substring(1)+au.substring(0)+au.substring(1)+ed.substring(0)+ed.substring(1)+ed.substring(2)+ra.substring(0)+ra.substring(1)+ra.substring(2)+ei;

        return Etiqueta;
    }

    private void cambioDeValue() {

        //  cambio de valor de un valor de item de spiner
         cmbAutores.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
             @Override
             public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                 if (!txtTitulo.getText().toString().equals("")) {
                     comprobarExistencia();
                 }
             }

             @Override
             public void onNothingSelected(AdapterView<?> parent) {

             }
         });

        cmbEditorial.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (!txtTitulo.getText().toString().equals("")) {
                    comprobarExistencia();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private boolean  comprobarExistencia(){
        libro = new Libros();
        boolean flag;

         flag =  libro.obtenerLibros(txtTitulo.getText().toString(),((Autores)cmbAutores.getSelectedItem()).getId(),((Editoriales)cmbEditorial.getSelectedItem()).getId());

          return flag;

    }


}
