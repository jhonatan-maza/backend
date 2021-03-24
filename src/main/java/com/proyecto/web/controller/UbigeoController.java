package com.proyecto.web.controller;

import com.proyecto.web.model.Departamento;
import com.proyecto.web.model.Distrito;
import com.proyecto.web.model.Provincia;
import com.proyecto.web.security.JwtTokenUtil;
import com.proyecto.web.service.UbigeoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by jonat on 11/04/2020.
 */
@RestController
@CrossOrigin(origins = {"*"})
@RequestMapping("/app/ubigeo")
public class UbigeoController {

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UbigeoService ubigeoService;

    @GetMapping("/listar/departamento")
    public ResponseEntity<List<Departamento>> ListarDepartamento(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        if(username != null){
            try{
                List<Departamento> lista = ubigeoService.ListarDepartamento();
                return new ResponseEntity<>(lista, HttpStatus.OK);
            }catch (Exception e){
                return null;
            }
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/listar/provincia/{iddepartamento}")
    public ResponseEntity<List<Provincia>> ListarProvincia(@PathVariable(value = "iddepartamento") Integer iddepartamento,
                                                              HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        if(username != null){
            try{
                List<Provincia> lista = ubigeoService.ListarProvincia(iddepartamento);
                return new ResponseEntity<>(lista, HttpStatus.OK);
            }catch (Exception e){
                return null;
            }
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/listar/distrito/{idprovincia}")
    public ResponseEntity<List<Distrito>> ListarDistrito(@PathVariable(value = "idprovincia") Integer idprovincia,
                                                           HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        if(username != null){
            try{
                List<Distrito> lista = ubigeoService.ListarDistrito(idprovincia);
                return new ResponseEntity<>(lista, HttpStatus.OK);
            }catch (Exception e){
                return null;
            }
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
