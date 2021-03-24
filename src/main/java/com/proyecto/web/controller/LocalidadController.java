package com.proyecto.web.controller;

import com.proyecto.web.model.Localidad;
import com.proyecto.web.security.JwtTokenUtil;
import com.proyecto.web.service.LocalidadService;
import com.proyecto.web.util.RespuestaTransaccion;
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
@RequestMapping("/app/localidad/")
public class LocalidadController {

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private LocalidadService localidadService;

    @GetMapping("{empresa}/{sede}")
    public ResponseEntity<List<Localidad>> ListarLocalidadXEmpresaXSede(@PathVariable(value = "empresa") Integer empresa,@PathVariable(value = "sede") Integer sede, HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        if(username != null){
            try {
                List<Localidad> lista = localidadService.ListarLocalidadXEmpresaXSede(empresa,sede);
                return new ResponseEntity<>(lista, HttpStatus.OK);
            }catch (Exception e){
                return null;
            }
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("localxcajero/")
    public ResponseEntity<List<Localidad>> ListarLocalidadXUsuarioCajero(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        if(username != null){
            try {
                List<Localidad> lista = localidadService.ListarLocalidadXUsuarioCajero();
                return new ResponseEntity<>(lista, HttpStatus.OK);
            }catch (Exception e){
                return null;
            }
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/listar/{estado}/{start}/{length}/{orderby}")
    public ResponseEntity<List<Localidad>> ListarLocalidad(@PathVariable(value = "estado") String estado,
                                                     @PathVariable(value = "start") String pagStart,
                                                     @PathVariable(value = "length") String pagLength,
                                                     @PathVariable(value = "orderby") String orderBy, HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        if(username != null){
            try{
                List<Localidad> lista = localidadService.ListarLocalidad(null,estado,pagStart,pagLength,orderBy);
                return new ResponseEntity<>(lista, HttpStatus.OK);
            }catch (Exception e){
                return null;
            }
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/view/{idlocalidad}")
    public ResponseEntity<Localidad> viewLocalidad(@PathVariable(value = "idlocalidad") Integer idlocalidad, HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        if(username != null){
            try{
                Localidad view = localidadService.viewLocalidad(idlocalidad);
                return new ResponseEntity<>(view, HttpStatus.OK);
            }catch (Exception e){
                return null;
            }
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/save")
    public ResponseEntity<RespuestaTransaccion> mantenimientoSaveLocalidad(@RequestBody Localidad localidad, HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        if(username != null){
            try{
                RespuestaTransaccion rpt = localidadService.mantenimientoLocalidad(localidad,username,1);
                return new ResponseEntity<>(rpt, HttpStatus.OK);
            }catch (Exception e){
                return null;
            }
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update/{operacion}")
    public ResponseEntity<RespuestaTransaccion> mantenimientoUpdateLocalidad(@PathVariable(value = "operacion") Integer operacion,
                                                                         @RequestBody Localidad localidad, HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        if(username != null){
            try{
                RespuestaTransaccion rpt = localidadService.mantenimientoLocalidad(localidad,username,operacion);
                return new ResponseEntity<>(rpt, HttpStatus.OK);
            }catch (Exception e){
                return null;
            }
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
