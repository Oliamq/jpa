package com.cibertec.sysmovie.model;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class DetalleAlquilerId implements Serializable {

    private Long id_alquiler;
    private Long id_pelicula;

    public DetalleAlquilerId() {}

    public DetalleAlquilerId(Long id_alquiler, Long id_pelicula) {
        this.id_alquiler = id_alquiler;
        this.id_pelicula = id_pelicula;
    }

    public Long getId_alquiler() {
        return id_alquiler;
    }

    public void setId_alquiler(Long id_alquiler) {
        this.id_alquiler = id_alquiler;
    }

    public Long getId_pelicula() {
        return id_pelicula;
    }

    public void setId_pelicula(Long id_pelicula) {
        this.id_pelicula = id_pelicula;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DetalleAlquilerId)) return false;
        DetalleAlquilerId that = (DetalleAlquilerId) o;
        return Objects.equals(id_alquiler, that.id_alquiler) &&
               Objects.equals(id_pelicula, that.id_pelicula);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_alquiler, id_pelicula);
    }
}
