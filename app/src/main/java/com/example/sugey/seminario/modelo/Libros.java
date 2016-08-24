package com.example.sugey.seminario.modelo;

import android.media.Image;
import android.util.Log;
import android.widget.Toast;

import com.example.sugey.seminario.baseDeDatos.Conexion;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;

/**
 * Created by Sugey on 19/08/2016.
 */
public class Libros {

    //variables

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

    public void setNumero(String numero) {
        this.numero = numero;
    }



    //Constructor
    public Libros(){

    }

   public boolean Guardar(){
       boolean guardo = false;

       Conexion = new Conexion();
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

           } catch (Exception ex) {

               System.out.println ("El error es: " + ex.getMessage());
               ex.printStackTrace();

           }
       }
       return guardo;
   }



}
