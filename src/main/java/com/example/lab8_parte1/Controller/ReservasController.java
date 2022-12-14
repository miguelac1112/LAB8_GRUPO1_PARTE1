package com.example.lab8_parte1.Controller;

import com.example.lab8_parte1.Entity.Pelicula;
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
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/reservas")
public class ReservasController {
    @Autowired
    PeliculaRepository peliculaRepository;
    @Autowired
    ReservaRepository reservaRepository;

    @PostMapping("/crearReserva")
    public ResponseEntity<HashMap<String, String>> guardarReserva(
            @RequestBody Reserva reserva){
        HashMap<String, String> hashMap =new HashMap<>();
        /*
        String[] fecha = reserva.getFecha().split(".");
        String dia=null;
        String mes=null;
        String anho=null;
        for(String caracteres:fecha){
            if(caracteres.charAt(1) == 'd'){
                dia=caracteres.sl
            }
        }
        String horaInicio = reserva.getHoraInicio();
        if( horaInicio.contains("pm") ){

        }else if (horaInicio.contains("am")){

        }else{
            hashMap.put("error", "horaInicio Incorrecta");
        }
        String horaFin = reserva.getHoraFin();
*/
        reservaRepository.save(reserva);
        hashMap.put("estado", "creado");
        return ResponseEntity.status(HttpStatus.CREATED).body(hashMap);
    }

    @GetMapping("/lista")
    public List<Reserva> reservaList(){
        return reservaRepository.findAll();
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

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<HashMap<String,Object>> actualizarReservaValidandoId(@RequestBody Reserva reserva,
                                                                                      @RequestParam("id") String idStr){
        HashMap<String,Object> hashMap = new HashMap<>();

        try{
            int id = Integer.parseInt(idStr);
            Optional<Reserva> optionalProduct = reservaRepository.findById(id);
            if(optionalProduct.isPresent()){
                Reserva reservaOriginal = optionalProduct.get();

                if(reserva.getIdPelicula() != null)
                    reservaOriginal.setIdPelicula(reserva.getIdPelicula());
                if(reserva.getFecha() != null)
                    reservaOriginal.setFecha(reserva.getFecha());
                if(reserva.getHoraInicio() != null)
                    reservaOriginal.setHoraInicio(reserva.getHoraInicio());
                if(reserva.getHoraFin() != null)
                    reservaOriginal.setHoraFin(reserva.getHoraFin());

                reservaRepository.save(reservaOriginal);
                hashMap.put("status", "actualizado");
                return ResponseEntity.status(HttpStatus.CREATED).body(hashMap);
            }else{
                hashMap.put("status","error");
                hashMap.put("msg","la reserva para actualizar no existe");
                return ResponseEntity.ok(hashMap);
            }
        }catch (NumberFormatException e){
            hashMap.put("existe",false);
            hashMap.put("msg","El id no es un n??mero!!!");
            return ResponseEntity.badRequest().body(hashMap);
        }
    }

    @DeleteMapping("")
    public ResponseEntity<HashMap<String,String>> borrarReserva(@RequestParam("id") int id){
        HashMap<String,String> hashMap = new HashMap<>();
        Optional<Reserva> optionalProduct = reservaRepository.findById(id);
        if(optionalProduct.isPresent()){
            try {
                reservaRepository.deleteById(id);
                hashMap.put("status","ok");
            }catch (Exception e){
                hashMap.put("status", "error-4000 - Ha ocurrido un problema al borrar el producto");
            }
        }else{
            hashMap.put("status", "error-3000 - El id que desea borrar no existe");
        }
        return ResponseEntity.ok(hashMap);
    }

}
