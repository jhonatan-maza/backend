package com.proyecto.web.controller;

import com.proyecto.web.model.Almacen;
import com.proyecto.web.security.JwtTokenUtil;
import com.proyecto.web.service.AlmacenService;
import com.proyecto.web.util.RespuestaTransaccion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by jonat on 13/04/2020.
 */
@RestController
@CrossOrigin(origins = {"*"})
@RequestMapping("/app/almacen/")
public class AlmacenController {

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private AlmacenService almacenService;

    @GetMapping("/listar/{estado}/{start}/{length}/{orderby}")
    public ResponseEntity<List<Almacen>> ListarAlmacen(@PathVariable(value = "estado") String estado,
                                                         @PathVariable(value = "start") String pagStart,
                                                         @PathVariable(value = "length") String pagLength,
                                                         @PathVariable(value = "orderby") String orderBy, HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        if(username != null){
            try{
                List<Almacen> lista = almacenService.ListarAlmacen(null,estado,pagStart,pagLength,orderBy);
                return new ResponseEntity<>(lista, HttpStatus.OK);
            }catch (Exception e){
                return null;
            }
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/view/{idalmacen}")
    public ResponseEntity<Almacen> viewAlmacen(@PathVariable(value = "idalmacen") Integer idalmacen, HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        if(username != null){
            try{
                Almacen view = almacenService.viewAlmacen(idalmacen);
                return new ResponseEntity<>(view, HttpStatus.OK);
            }catch (Exception e){
                return null;
            }
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/save")
    public ResponseEntity<RespuestaTransaccion> mantenimientoSaveAlmacen(@RequestBody Almacen almacen, HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        if(username != null){
            try{
                RespuestaTransaccion rpt = almacenService.mantenimientoAlmacen(almacen,username,1);
                return new ResponseEntity<>(rpt, HttpStatus.OK);
            }catch (Exception e){
                return null;
            }
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update/{operacion}")
    public ResponseEntity<RespuestaTransaccion> mantenimientoUpdateAlmacen(@PathVariable(value = "operacion") Integer operacion,
                                                                             @RequestBody Almacen almacen, HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        if(username != null){
            try{
                RespuestaTransaccion rpt = almacenService.mantenimientoAlmacen(almacen,username,operacion);
                return new ResponseEntity<>(rpt, HttpStatus.OK);
            }catch (Exception e){
                return null;
            }
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
}
