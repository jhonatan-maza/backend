package com.proyecto.web.controller;

import com.proyecto.web.model.Ruta;
import com.proyecto.web.security.JwtTokenUtil;
import com.proyecto.web.service.RutaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by jonat on 4/03/2020.
 */
@RestController
@CrossOrigin(origins = {"*"})
@RequestMapping("/app/ruta/")
public class RutaController {

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private RutaService rutaService;

    @GetMapping("{localidad}/{operacion}")
    public ResponseEntity<List<Ruta>> ListarRutaXLocalidad(@PathVariable(value = "localidad") Integer localidad, @PathVariable(value = "operacion") Integer operacion, HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        if(username != null){
            try {
                List<Ruta> lista = rutaService.ListarRutaXLocalidad(localidad,operacion);
                return new ResponseEntity<>(lista, HttpStatus.OK);
            }catch (Exception e){
                return null;
            }
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
