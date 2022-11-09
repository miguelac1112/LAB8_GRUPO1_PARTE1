package com.example.lab8_parte1.Repositories;

import com.example.lab8_parte1.Entity.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservaRepository extends JpaRepository<Reserva, Integer> {
}
