package com.cibertec.sysmovie.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class peliculas {
    @Id // Marca este campo como la clave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Autoincremental
    private Long id_pelicula;

    @Column(nullable = false, length = 50)
    private String titulo;

    @Column(nullable = false, length = 50)
    private String genero;

    @Column(nullable = false)
    private int stock;

    public peliculas(){}

    public peliculas(String titulo, String genero, int stock) {
        this.titulo = titulo;
        this.genero = genero;
        this.stock = stock;
    }

    public Long getId_pelicula() {return id_pelicula;}

    public void setId_pelicula(Long id_pelicula) {this.id_pelicula = id_pelicula;}

    public String getTitulo() {return titulo;}

    public void setTitulo(String titulo) {this.titulo = titulo;}

    public String getGenero() {return genero;}

    public void setGenero(String genero) {this.genero = genero;}

    public int getStock() {return stock;}

    public void setStock(int stock) {this.stock = stock;}

    @Override
    public String toString() {
        return "peliculas [id_pelicula=" + id_pelicula + ", titulo=" + titulo + ", genero=" + genero + ", stock="
                + stock + "]";
    };

    


}
