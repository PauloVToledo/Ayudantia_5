package com.example.practica_ayudantia.modelo;

import java.util.Date;

public class Prestamo {
    // Atributos
    private Usuario usuario;
    private Libro libro;
    private Date fechaInicio;
    private Date fechaDevolucion;

    // Constructor
    public Prestamo(Usuario usuario, Libro libro, Date fechaInicio) {
        this.usuario = usuario;
        this.libro = libro;
        this.fechaInicio = fechaInicio;
        // La fecha de devolución se inicializa como null, ya que aún no se ha devuelto el libro
        this.fechaDevolucion = null;
    }

    // Métodos de acceso (getters y setters)
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(Date fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    // Otros métodos, si es necesario
    // ...

    @Override
    public String toString() {
        return "Prestamo{" +
                "usuario=" + usuario +
                ", libro=" + libro +
                ", fechaInicio=" + fechaInicio +
                ", fechaDevolucion=" + fechaDevolucion +
                '}';
    }
}
