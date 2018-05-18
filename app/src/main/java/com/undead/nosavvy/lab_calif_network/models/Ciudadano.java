package com.undead.nosavvy.lab_calif_network.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by nosavvy on 5/11/18.
 */

public class Ciudadano {
    private Integer idciudadano;
    private String nombre;



    public Ciudadano(Integer idciudadano, String nombre, String paterno, String materno, String dni, String usuario, String password) {
        this.idciudadano = idciudadano;
        this.nombre = nombre;
        this.paterno = paterno;
        this.materno = materno;
        this.dni = dni;
        this.usuario = usuario;
        this.password = password;
    }

    public Ciudadano(String usuario, String password){
        this.usuario = usuario;
        this.password = password;
    }

    private String paterno;
    private String materno;
    private String dni;
    @SerializedName("username")
    private String usuario;
    @SerializedName("password")
    private String password;
    public Integer getIdciudadano() {
        return idciudadano;
    }

    public void setIdciudadano(Integer idciudadano) {
        this.idciudadano = idciudadano;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPaterno() {
        return paterno;
    }

    public void setPaterno(String paterno) {
        this.paterno = paterno;
    }

    public String getMaterno() {
        return materno;
    }

    public void setMaterno(String materno) {
        this.materno = materno;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



}
