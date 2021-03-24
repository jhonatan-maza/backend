package com.proyecto.web.controller;

import com.proyecto.web.model.PrecioLista;
import com.proyecto.web.security.JwtTokenUtil;
import com.proyecto.web.service.ListaDePrecioService;
import com.proyecto.web.util.RespuestaTransaccion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by jonat on 17/03/2020.
 */
@RestController
@CrossOrigin(origins = {"*"})
@RequestMapping("/app/lprecio/")
public class ListaDePrecioController {

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private ListaDePrecioService listaDePrecioService;

    @GetMapping("{localidad}")
    public ResponseEntity<List<PrecioLista>> ListaDePrecioXLocalidad(@PathVariable(value = "localidad") Integer localidad, HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        if(username != null){
            try {
                List<PrecioLista> lista = listaDePrecioService.ListaDePrecioXLocalidad(localidad);
                return new ResponseEntity<>(lista, HttpStatus.OK);
            }catch (Exception e){
                return null;
            }
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/listar/{idlocalidad}/{estado}/{start}/{length}/{orderby}")
    public ResponseEntity<List<PrecioLista>> ListarListaPrecio(@PathVariable(value = "idlocalidad") Integer idlocalidad,
                                                       @PathVariable(value = "estado") String estado,
                                                       @PathVariable(value = "start") String pagStart,
                                                       @PathVariable(value = "length") String pagLength,
                                                       @PathVariable(value = "orderby") String orderBy, HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        if(username != null){
            try{
                List<PrecioLista> lista = listaDePrecioService.ListarListaPrecio(idlocalidad,null,estado,pagStart,pagLength,orderBy);
                return new ResponseEntity<>(lista, HttpStatus.OK);
            }catch (Exception e){
                return null;
            }
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/view/{idlprecio}")
    public ResponseEntity<PrecioLista> viewListaPrecio(@PathVariable(value = "idlprecio") Integer idlprecio, HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        if(username != null){
            try{
                PrecioLista view = listaDePrecioService.viewListaPrecio(idlprecio);
                return new ResponseEntity<>(view, HttpStatus.OK);
            }catch (Exception e){
                return null;
            }
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/save")
    public ResponseEntity<RespuestaTransaccion> mantenimientoSaveListaPrecio(@RequestBody PrecioLista lprecio, HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        if(username != null){
            try{
                RespuestaTransaccion rpt = listaDePrecioService.mantenimientoListaPrecio(lprecio,username,1);
                return new ResponseEntity<>(rpt, HttpStatus.OK);
            }catch (Exception e){
                return null;
            }
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update/{operacion}")
    public ResponseEntity<RespuestaTransaccion> mantenimientoUpdateListaPrecio(@PathVariable(value = "operacion") Integer operacion,
                                                                          @RequestBody PrecioLista lprecio, HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        if(username != null){
            try{
                RespuestaTransaccion rpt = listaDePrecioService.mantenimientoListaPrecio(lprecio,username,operacion);
                return new ResponseEntity<>(rpt, HttpStatus.OK);
            }catch (Exception e){
                return null;
            }
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
