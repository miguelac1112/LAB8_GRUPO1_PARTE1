package com.example.lab8_parte1.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value = "/peliculas")
public class PeliculasController {
    @Autowired
    SupplierRepository supplierRepository;
    @GetMapping("/supplier")
    public List<Supplier> supplierList(){
        return supplierRepository.findAll();
    }
}
