package com.example.sugey.seminario.modelo;

import android.content.Context;
import android.media.Image;
import android.util.Log;
import android.widget.Toast;

import com.example.sugey.seminario.baseDeDatos.Conexion;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.IllegalFormatCodePointException;
import java.util.LinkedHashMap;
import java.util.LinkedList;

/**
 * Created by Sugey on 19/08/2016.
 */
public class Libros {

    //variables
    Context ctx;
    Conexion Conexion;
    PreparedStatement stmt;
    ResultSet rs;
    CallableStatement cst;
    //atributos
    String Titulo;
    int IdAutor;
    String Edicion;
    int IdEditorial;
    int IdRama;
    int IdTematica;
    int IdPais;
    int Año;
    Boolean ExclusivoConsulta;
    int Pasillo;
    int Estantent;
    int Nivel;
    String ISBN ;
    Boolean Ilustrado;
    Image Portada;
    Image ContraPortada;
    String  Materia;
    int Formato;
    int Estatus;
    String numero;


    public Libros(Context ctx){
        this.ctx = ctx;
    }

    //Constructor
    public void setNumero(String numero) {
        this.numero = numero;
    }


  //propiedades
    public String getTitulo() {
        return Titulo;
    }

    public void setTitulo(String titulo) {
        Titulo = titulo;
    }

    public int getIdAutor() {
        return IdAutor;
    }

    public void setIdAutor(int idAutor) {
        IdAutor = idAutor;
    }

    public String getEdicion() {
        return Edicion;
    }

    public void setEdicion(String edicion) {
        Edicion = edicion;
    }

    public int getIdEditorial() {
        return IdEditorial;
    }

    public void setIdEditorial(int idEditorial) {
        IdEditorial = idEditorial;
    }


    public int getIdRama() {
        return IdRama;
    }

    public void setIdRama(int idRama) {
        IdRama = idRama;
    }

    public int getIdTematica() {
        return IdTematica;
    }

    public void setIdTematica(int idTematica) {
        IdTematica = idTematica;
    }

    public int getIdPais() {
        return IdPais;
    }

    public void setIdPais(int idPais) {
        IdPais = idPais;
    }

    public int getAño() {
        return Año;
    }

    public void setAño(int año) {
        Año = año;
    }

    public Boolean getExclusivoConsulta() {
        return ExclusivoConsulta;
    }

    public void setExclusivoConsulta(Boolean exclusivoConsulta) {
        ExclusivoConsulta = exclusivoConsulta;
    }

    public int getPasillo() {
        return Pasillo;
    }

    public void setPasillo(int pasillo) {
        Pasillo = pasillo;
    }

    public int getEstantent() {
        return Estantent;
    }

    public void setEstantent(int estantent) {
        Estantent = estantent;
    }

    public int getNivel() {
        return Nivel;
    }

    public void setNivel(int nivel) {
        Nivel = nivel;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public Boolean getIlustrado() {
        return Ilustrado;
    }

    public void setIlustrado(Boolean ilustrado) {
        Ilustrado = ilustrado;
    }

    public Image getPortada() {
        return Portada;
    }

    public void setPortada(Image portada) {
        Portada = portada;
    }

    public Image getContraPortada() {
        return ContraPortada;
    }

    public void setContraPortada(Image contraPortada) {
        ContraPortada = contraPortada;
    }

    public String getMateria() {
        return Materia;
    }

    public void setMateria(String materia) {
        Materia = materia;
    }

    public int getFormato() {
        return Formato;
    }

    public void setFormato(int formato) {
        Formato = formato;
    }

    public int getEstatus() {
        return Estatus;
    }

    public void setEstatus(int estatus) {
        Estatus = estatus;
    }

    public String getNumero() {
        return numero;
    }


   //metodos

    public boolean Guardar(){
       boolean guardo = false;

       Conexion = new Conexion(ctx);
       Connection con = Conexion.CONN();
       if (con == null) {


       } else {
           try {

               cst = con.prepareCall("{call guardarLibro(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
               cst.setString(1,Titulo);
               cst.setInt(2,IdAutor);
               cst.setString(3,Edicion);
               cst.setInt(4,IdEditorial);
               cst.setInt(5,IdRama);
               cst.setInt(6,IdTematica);
               cst.setInt(7,IdPais);
               cst.setInt(8,Año);
               cst.setInt(9,0);
               cst.setBoolean(10,ExclusivoConsulta);
               cst.setInt(11,Pasillo);
               cst.setInt(12,Estantent);
               cst.setInt(13,Nivel);
               cst.setString(14,ISBN);
               cst.setString(15,"");
               cst.setString(16,"");
               cst.setBoolean(17,Ilustrado);
               cst.setString(18,"");

               byte[] arr= new byte[] { (byte)0xe0, 0x4f };
               cst.setBytes(19,arr);
               cst.setBytes(20,arr);

               cst.setString(21,Materia);
               cst.setInt(22,Formato);
               cst.setInt(23,Estatus);
               cst.setString(24,numero);
               cst.executeUpdate();
               cst.close();
           } catch (Exception ex) {

               System.out.println ("El error es: " + ex.getMessage());
               ex.printStackTrace();

           }
       }
       return guardo;
   }

    public int cantidadLibros(String isbn){
        //cantidad de libros correspondientes a un isb en especifico
        int cont =1;
        Conexion = new Conexion(ctx);
        Connection con = Conexion.CONN();
        if (con == null) {


        } else {
            try {

                cst = con.prepareCall("{call binCantidadLibros(?,?)}");
                cst.setString(1,isbn);
                cst.registerOutParameter (2, Types.INTEGER);
                cst.executeUpdate();
                cont = cst.getInt(2);
                cst.close();
            } catch (Exception ex) {

                System.out.println ("El error es  = " + ex.getMessage());
                ex.printStackTrace();

            }
        }

        if (cont == 0){
            cont=1;
        }else{
            cont = cont+1;
        }

        return cont;
    }

    public int cantidadLibros2(String titulo,int autor, int editorial){
        //cantidad de libros correspondientes a titulo editorial autor
        int cont =1;
        Conexion = new Conexion(ctx);
        Connection con = Conexion.CONN();
        if (con == null) {


        } else {
            try {

                cst = con.prepareCall("{call binCantidadLibros2(?,?,?,?)}");
                cst.setString(1,titulo);
                cst.setInt(2,autor);
                cst.setInt(3,editorial);
                cst.registerOutParameter (4, Types.INTEGER);
                cst.executeUpdate();
                cont = cst.getInt(4);
                cst.close();
            } catch (Exception ex) {

                System.out.println ("El error es  = " + ex.getMessage());
                ex.printStackTrace();

            }
        }

        if (cont == 0){
            cont=1;
        }else{
            cont = cont+1;
        }

        return cont;
    }

    public boolean obtenerLibros(String tit,int idaut,int edit){
        boolean retorno = false;
        String  titulo;
        int   cont =0;
        Conexion = new Conexion(ctx);
        Connection con = Conexion.CONN();


        if (con == null) {


        } else {
            try {
                //instruccion

                String query = "select * from BInvLibros where titulo = '"+tit+"'  and  idautor = "+idaut +" and ideditorial ="+edit;
                stmt = con.prepareStatement(query);
                rs = stmt.executeQuery();
                //Libros lib = new Libros();
              while(rs.next()){

                  cont++;
              }

                if (cont >0){
                    retorno = true;
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


        return retorno;
    }

    public boolean obtenerLibrosisbn(String isbn){
        boolean retorno = false;
        String  titulo;
        int   cont =0;
        Conexion = new Conexion(ctx);
        Connection con = Conexion.CONN();


        if (con == null) {


        } else {
            try {
                //instruccion

                String query = "select * from BInvLibros where isbn = '"+isbn+"'";
                stmt = con.prepareStatement(query);
                rs = stmt.executeQuery();
               // Libros lib = new Libros();
                while(rs.next()){

                    cont++;
                }

                if (cont >0){
                    retorno = true;
                }


            } catch (Exception ex) {
                System.out.println ("El error en buyscar por isbn es   = " + ex.getMessage());
                ex.printStackTrace();

            }

        }

        try {
            con.close();
        } catch (Exception ex) {

        }


        return retorno;
    }

}
