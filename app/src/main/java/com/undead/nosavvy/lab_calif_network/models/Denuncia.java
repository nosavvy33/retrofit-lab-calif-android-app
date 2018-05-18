package com.undead.nosavvy.lab_calif_network.models;

/**
 * Created by nosavvy on 5/15/18.
 */

public class Denuncia {
    private String titulo;
    private String descripcion;
    private String ubicacion;
    private Integer iddenuncia;
    private Integer ciudadano_idciudadano;
    private String foto;
    public Denuncia(String titulo, String descripcion, String ubicacion, Integer iddenuncia, Integer ciudadano_idciudadano, String foto) {
        this.setTitulo(titulo);
        this.setDescripcion(descripcion);
        this.setUbicacion(ubicacion);
        this.setIddenuncia(iddenuncia);
        this.setCiudadano_idciudadano(ciudadano_idciudadano);
        this.foto = foto;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public Integer getIddenuncia() {
        return iddenuncia;
    }

    public void setIddenuncia(Integer iddenuncia) {
        this.iddenuncia = iddenuncia;
    }

    public Integer getCiudadano_idciudadano() {
        return ciudadano_idciudadano;
    }

    public void setCiudadano_idciudadano(Integer ciudadano_idciudadano) {
        this.ciudadano_idciudadano = ciudadano_idciudadano;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}
