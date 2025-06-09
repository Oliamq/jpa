package com.cibertec.sysmovie.model;

import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.*;

@Entity
public class detalle_alquiler {

    @EmbeddedId
    private DetalleAlquilerId id;

    @ManyToOne
    @MapsId("id_alquiler")
    @JoinColumn(name = "id_alquiler")
    private alquileres alquiler;

    @ManyToOne
    @MapsId("id_pelicula")
    @JoinColumn(name = "id_pelicula")
    private peliculas pelicula;

    @Column(nullable = false)
    private int cantidad;

    public detalle_alquiler() {}

    public detalle_alquiler(alquileres alquiler, peliculas pelicula, int cantidad) {
        this.alquiler = alquiler;
        this.pelicula = pelicula;
        this.cantidad = cantidad;
        this.id = new DetalleAlquilerId(
            alquiler.getId_alquiler(),
            pelicula.getId_pelicula()
        );
    }

    public DetalleAlquilerId getId() {
        return id;
    }

    public void setId(DetalleAlquilerId id) {
        this.id = id;
    }

    public alquileres getAlquiler() {
        return alquiler;
    }

    public void setAlquiler(alquileres alquiler) {
        this.alquiler = alquiler;
    }

    public peliculas getPelicula() {
        return pelicula;
    }

    public void setPelicula(peliculas pelicula) {
        this.pelicula = pelicula;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
