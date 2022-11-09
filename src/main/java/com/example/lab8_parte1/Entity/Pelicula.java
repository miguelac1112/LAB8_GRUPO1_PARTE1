package com.example.lab8_parte1.Entity;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "pelicula")
public class Pelicula {
    @Id
    @Column(name = "idPelicula", nullable = false)
    private Integer id;

    @Column(name = "nombrePeli", nullable = false, length = 45)
    private String nombrePeli;

    @OneToMany(mappedBy = "peliculaIdpelicula")
    private Set<Reserva> reservas = new LinkedHashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombrePeli() {
        return nombrePeli;
    }

    public void setNombrePeli(String nombrePeli) {
        this.nombrePeli = nombrePeli;
    }

    public Set<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(Set<Reserva> reservas) {
        this.reservas = reservas;
    }

}