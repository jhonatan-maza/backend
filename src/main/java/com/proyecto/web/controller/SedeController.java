package com.proyecto.web.controller;

import com.proyecto.web.model.Sede;
import com.proyecto.web.security.JwtTokenUtil;
import com.proyecto.web.service.SedeService;
import com.proyecto.web.util.RespuestaTransaccion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by jonat on 3/03/2020.
 */
@RestController
@CrossOrigin(origins = {"*"})
@RequestMapping("/app/sede/")
public class SedeController {

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private SedeService sedeService;

    @GetMapping("empresa/{empresa}")
    public ResponseEntity<List<Sede>> ListarSedeXEmpresa(@PathVariable(value = "empresa") Integer empresa, HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        if(username != null){
            try {
                List<Sede> lista = sedeService.ListarSedeXEmpresa(empresa);
                return new ResponseEntity<>(lista, HttpStatus.OK);
            }catch (Exception e){
                return null;
            }
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/listar/{estado}/{start}/{length}/{orderby}")
    public ResponseEntity<List<Sede>> ListarSedes(@PathVariable(value = "estado") String estado,
                                                     @PathVariable(value = "start") String pagStart,
                                                     @PathVariable(value = "length") String pagLength,
                                                     @PathVariable(value = "orderby") String orderBy, HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        if(username != null){
            try{
                List<Sede> lista = sedeService.ListarSede(null,estado,pagStart,pagLength,orderBy);
                return new ResponseEntity<>(lista, HttpStatus.OK);
            }catch (Exception e){
                return null;
            }
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/view/{idsede}")
    public ResponseEntity<Sede> viewSede(@PathVariable(value = "idsede") Integer idSede, HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        if(username != null){
            try{
                Sede view = sedeService.viewSede(idSede);
                return new ResponseEntity<>(view, HttpStatus.OK);
            }catch (Exception e){
                return null;
            }
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/save")
    public ResponseEntity<RespuestaTransaccion> mantenimientoSaveSede(@RequestBody Sede sede, HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        if(username != null){
            try{
                RespuestaTransaccion rpt = sedeService.mantenimientoSede(sede,username,1);
                return new ResponseEntity<>(rpt, HttpStatus.OK);
            }catch (Exception e){
                return null;
            }
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update/{operacion}")
    public ResponseEntity<RespuestaTransaccion> mantenimientoUpdateSede(@PathVariable(value = "operacion") Integer operacion,
                                                                          @RequestBody Sede sede, HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        if(username != null){
            try{
                RespuestaTransaccion rpt = sedeService.mantenimientoSede(sede,username,operacion);
                return new ResponseEntity<>(rpt, HttpStatus.OK);
            }catch (Exception e){
                return null;
            }
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
