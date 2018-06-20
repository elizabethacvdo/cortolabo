/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pelicula;

/**
 *
 * @author LN710Q
 */
public class Pelicula {
    private int id;
    private String nombre;
    private String director;
    private String pais;
    private String clasificacion;
    private String año;
    private boolean proyeccion;

    public Pelicula(int id, String nombre, String director, String pais, String clasificacion, String año, boolean proyeccion) {
        this.id = id;
        this.nombre = nombre;
        this.director = director;
        this.pais = pais;
        this.clasificacion = clasificacion;
        this.año = año;
        this.proyeccion = proyeccion;
    }

    public Pelicula(String nombre, String director, String pais, String clasificacion, String año, boolean proyeccion) {
        this.nombre = nombre;
        this.director = director;
        this.pais = pais;
        this.clasificacion = clasificacion;
        this.año = año;
        this.proyeccion = proyeccion;
    }

    public Pelicula(String director, String pais, String clasificacion, String año, boolean proyeccion) {
        this.director = director;
        this.pais = pais;
        this.clasificacion = clasificacion;
        this.año = año;
        this.proyeccion = proyeccion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(String clasificacion) {
        this.clasificacion = clasificacion;
    }

    public String getAño() {
        return año;
    }

    public void setAño(String año) {
        this.año = año;
    }

    public boolean getProyeccion() {
        return proyeccion;
    }

    public void setProyeccion(boolean proyeccion) {
        this.proyeccion = proyeccion;
    }
    
    
    
    
    
}
