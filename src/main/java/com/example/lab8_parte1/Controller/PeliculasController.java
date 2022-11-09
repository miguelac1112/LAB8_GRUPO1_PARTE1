package com.example.lab8_parte1.Controller;


import com.example.lab8_parte1.Entity.Pelicula;
import com.example.lab8_parte1.Repositories.PeliculaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/peliculas")
public class PeliculasController {
    @Autowired
    PeliculaRepository peliculaRepository;
    @GetMapping("/lista")
    public List<Pelicula> peliculasList(){
        return peliculaRepository.findAll();
    }
}
