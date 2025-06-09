package com.cibertec.sysmovie.model;
//Estos son los imports que requiere  La entidad
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class clientes {
    @Id // Marca este campo como la clave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Autoincremental
    private Long id_cliente;

    @Column(nullable = false, length = 50)
    private String nombre;

    @Column(nullable = false, length = 50)
    private String email;

    public clientes(){}

    public clientes(String nombre, String email) {
        this.nombre = nombre;
        this.email = email;
    }

    public Long getId_cliente() {return id_cliente;}

    public void setId_cliente(Long id_cliente) {this.id_cliente = id_cliente;}

    public String getNombre() {return nombre;}

    public void setNombre(String nombre) {this.nombre = nombre;}

    public String getEmail() {return email;}

    public void setEmail(String email) {this.email = email;}

    @Override
    public String toString() {
        return "clientes [id_cliente=" + id_cliente + ", nombre=" + nombre + ", email=" + email + "]";
    }

    
    
}
