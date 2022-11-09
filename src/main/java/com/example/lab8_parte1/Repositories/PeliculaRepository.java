package com.example.lab8_parte1.Repositories;

import com.example.lab8_parte1.Entity.Pelicula;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PeliculaRepository extends JpaRepository<Pelicula, Integer> {
}
