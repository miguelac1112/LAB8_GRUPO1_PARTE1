package com.example.lab8_parte1.Controller;

import com.example.lab8_parte1.Entity.Reserva;
import com.example.lab8_parte1.Repositories.PeliculaRepository;
import com.example.lab8_parte1.Repositories.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@RestController
public class ReservasController {
    @Autowired
    PeliculaRepository peliculaRepository;
    @Autowired
    ReservaRepository reservaRepository;

    @PostMapping("/crearReserva")
    public ResponseEntity<HashMap<String, String>> guardarReserva(
            @RequestBody Reserva reserva){
        HashMap<String, String> hashMap =new HashMap<>();
        reservaRepository.save(reserva);
        hashMap.put("estado", "creado");
        return ResponseEntity.status(HttpStatus.CREATED).body(hashMap);
    }


    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<HashMap<String, String>> gestionarExcepcion(HttpServletRequest request) {
        HashMap<String, String> hashMap = new HashMap<>();
        if (request.getMethod().equals("POST") || request.getMethod().equals("PUT")) {
            hashMap.put("error", "true");
            hashMap.put("msg", "Debes enviar el registro como json");
        }
        return ResponseEntity.badRequest().body(hashMap);
    }
}
