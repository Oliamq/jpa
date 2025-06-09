package com.cibertec.sysmovie.model;
import java.time.LocalDate;

import jakarta.persistence.*;



@Entity
public class alquileres {
    @Id // Marca este campo como la clave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Autoincremental
    private Long id_alquiler;

    @Column(nullable = false)
    private LocalDate fecha;

    @ManyToOne
    @JoinColumn(name = "id_cliente",nullable = false)
    private clientes cliente;

    @Column(nullable = false)
    private double total;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoAlquiler estado;

    public alquileres(LocalDate fecha, clientes cliente, double total, EstadoAlquiler estado) {
        this.fecha = fecha;
        this.cliente = cliente;
        this.total = total;
        this.estado = estado;
    }

    public Long getId_alquiler() {return id_alquiler;}

    public void setId_alquiler(Long id_alquiler) {this.id_alquiler = id_alquiler;}

    public LocalDate getFecha() {return fecha;}

    public void setFecha(LocalDate fecha) {this.fecha = fecha;}

    public clientes getCliente() {return cliente;}

    public void setCliente(clientes cliente) {this.cliente = cliente;}

    public double getTotal() {return total;}

    public void setTotal(double total) {this.total = total;}
    
    public EstadoAlquiler getEstado() {
        return estado;
    }

    public void setEstado(EstadoAlquiler estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "alquileres [id_alquiler=" + id_alquiler + ", fecha=" + fecha + ", cliente=" + cliente + ", total="
                + total + "]";
    }

    
    
}
