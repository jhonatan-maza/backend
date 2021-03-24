package com.proyecto.web.controller;

import com.proyecto.web.model.*;
import com.proyecto.web.security.JwtTokenUtil;
import com.proyecto.web.service.JerarqArticuloService;
import com.proyecto.web.util.RespuestaTransaccion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by jonat on 14/03/2020.
 */
@RestController
@CrossOrigin(origins = {"*"})
@RequestMapping("/app/jerarqarticulo")
public class JerarqArticuloController {

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JerarqArticuloService jerarqArticuloService;

    @GetMapping("/familia/listar/{estado}/{start}/{length}/{orderby}")
    public ResponseEntity<List<JerarqFamilia>> ListarFamilia(@PathVariable(value = "estado") String estado,
                                                             @PathVariable(value = "start") String pagStart,
                                                             @PathVariable(value = "length") String pagLength,
                                                             @PathVariable(value = "orderby") String orderBy, HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        if(username != null){
            try{
                List<JerarqFamilia> lista = jerarqArticuloService.ListarFamilia(estado,pagStart,pagLength,orderBy);
                return new ResponseEntity<>(lista, HttpStatus.OK);
            }catch (Exception e){
                return null;
            }
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/familia/view/{idfamilia}")
    public ResponseEntity<JerarqFamilia> viewFamilia(@PathVariable(value = "idfamilia") Integer idfamilia, HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        if(username != null){
            try{
                JerarqFamilia view = jerarqArticuloService.viewFamilia(idfamilia);
                return new ResponseEntity<>(view, HttpStatus.OK);
            }catch (Exception e){
                return null;
            }
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/familia/save")
    public ResponseEntity<RespuestaTransaccion> mantenimientoSaveFamilia(@RequestBody JerarqFamilia familia, HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        if(username != null){
            try{
                RespuestaTransaccion rpt = jerarqArticuloService.mantenimientoFamilia(familia,username,1);
                return new ResponseEntity<>(rpt, HttpStatus.OK);
            }catch (Exception e){
                return null;
            }
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/familia/update/{operacion}")
    public ResponseEntity<RespuestaTransaccion> mantenimientoUpdateFamilia(@PathVariable(value = "operacion") Integer operacion,
                                                                         @RequestBody JerarqFamilia familia, HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        if(username != null){
            try{
                RespuestaTransaccion rpt = jerarqArticuloService.mantenimientoFamilia(familia,username,operacion);
                return new ResponseEntity<>(rpt, HttpStatus.OK);
            }catch (Exception e){
                return null;
            }
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/clase/listar/{estado}/{idfamilia}/{start}/{length}/{orderby}")
    public ResponseEntity<List<JerarqClase>> ListarClase(@PathVariable(value = "estado") String estado,
                                                         @PathVariable(value = "idfamilia") Integer familia,
                                                         @PathVariable(value = "start") String pagStart,
                                                         @PathVariable(value = "length") String pagLength,
                                                         @PathVariable(value = "orderby") String orderBy, HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        if(username != null){
            try{
                List<JerarqClase> lista = jerarqArticuloService.ListarClase(estado,familia,pagStart,pagLength,orderBy);
                return new ResponseEntity<>(lista, HttpStatus.OK);
            }catch (Exception e){
                return null;
            }
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/clase/view/{idclase}")
    public ResponseEntity<JerarqClase> viewClase(@PathVariable(value = "idclase") Integer idclase, HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        if(username != null){
            try{
                JerarqClase view = jerarqArticuloService.viewClase(idclase);
                return new ResponseEntity<>(view, HttpStatus.OK);
            }catch (Exception e){
                return null;
            }
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/clase/save")
    public ResponseEntity<RespuestaTransaccion> mantenimientoSaveClase(@RequestBody JerarqClase clase, HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        if(username != null){
            try{
                RespuestaTransaccion rpt = jerarqArticuloService.mantenimientoClase(clase,username,1);
                return new ResponseEntity<>(rpt, HttpStatus.OK);
            }catch (Exception e){
                return null;
            }
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/clase/update/{operacion}")
    public ResponseEntity<RespuestaTransaccion> mantenimientoUpdateClase(@PathVariable(value = "operacion") Integer operacion,
                                                                           @RequestBody JerarqClase clase, HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        if(username != null){
            try{
                RespuestaTransaccion rpt = jerarqArticuloService.mantenimientoClase(clase,username,operacion);
                return new ResponseEntity<>(rpt, HttpStatus.OK);
            }catch (Exception e){
                return null;
            }
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/subclase/listar/{estado}/{idfamilia}/{idclase}/{start}/{length}/{orderby}")
    public ResponseEntity<List<JerarqSubClase>> ListarSubClase(@PathVariable(value = "estado") String estado,
                                                               @PathVariable(value = "idfamilia") Integer familia,
                                                               @PathVariable(value = "idclase") Integer clase,
                                                               @PathVariable(value = "start") String pagStart,
                                                               @PathVariable(value = "length") String pagLength,
                                                               @PathVariable(value = "orderby") String orderBy, HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        if(username != null){
            try{
                List<JerarqSubClase> lista = jerarqArticuloService.ListarSubClase(estado,familia,clase,pagStart,pagLength,orderBy);
                return new ResponseEntity<>(lista, HttpStatus.OK);
            }catch (Exception e){
                return null;
            }
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/subclase/view/{idsubclase}")
    public ResponseEntity<JerarqSubClase> viewSubClase(@PathVariable(value = "idsubclase") Integer idsubclase, HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        if(username != null){
            try{
                JerarqSubClase view = jerarqArticuloService.viewSubClase(idsubclase);
                return new ResponseEntity<>(view, HttpStatus.OK);
            }catch (Exception e){
                return null;
            }
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/subclase/save")
    public ResponseEntity<RespuestaTransaccion> mantenimientoSaveSubClase(@RequestBody JerarqSubClase subClase, HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        if(username != null){
            try{
                RespuestaTransaccion rpt = jerarqArticuloService.mantenimientoSubClase(subClase,username,1);
                return new ResponseEntity<>(rpt, HttpStatus.OK);
            }catch (Exception e){
                return null;
            }
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/subclase/update/{operacion}")
    public ResponseEntity<RespuestaTransaccion> mantenimientoUpdateSubClase(@PathVariable(value = "operacion") Integer operacion,
                                                                         @RequestBody JerarqSubClase subClase, HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        if(username != null){
            try{
                RespuestaTransaccion rpt = jerarqArticuloService.mantenimientoSubClase(subClase,username,operacion);
                return new ResponseEntity<>(rpt, HttpStatus.OK);
            }catch (Exception e){
                return null;
            }
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/categoria/listar/{estado}/{idfamilia}/{idclase}/{idsubclase}/{start}/{length}/{orderby}")
    public ResponseEntity<List<JerarqCategoria>> ListarCategoria(@PathVariable(value = "estado") String estado,
                                                                 @PathVariable(value = "idfamilia") Integer familia,
                                                                 @PathVariable(value = "idclase") Integer clase,
                                                                 @PathVariable(value = "idsubclase") Integer subClase,
                                                                 @PathVariable(value = "start") String pagStart,
                                                                 @PathVariable(value = "length") String pagLength,
                                                                 @PathVariable(value = "orderby") String orderBy, HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        if(username != null){
            try{
                List<JerarqCategoria> lista = jerarqArticuloService.ListarCategoria(estado,familia,clase,subClase,pagStart,pagLength,orderBy);
                return new ResponseEntity<>(lista, HttpStatus.OK);
            }catch (Exception e){
                return null;
            }
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/categoria/view/{idcategoria}")
    public ResponseEntity<JerarqCategoria> viewCategoria(@PathVariable(value = "idcategoria") Integer idcategoria,
                                                         HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        if(username != null){
            try{
                JerarqCategoria view = jerarqArticuloService.viewCategoria(idcategoria);
                return new ResponseEntity<>(view, HttpStatus.OK);
            }catch (Exception e){
                return null;
            }
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/categoria/save")
    public ResponseEntity<RespuestaTransaccion> mantenimientoSaveCategoria(@RequestBody JerarqCategoria categoria,
                                                                           HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        if(username != null){
            try{
                RespuestaTransaccion rpt = jerarqArticuloService.mantenimientoCategoria(categoria,username,1);
                return new ResponseEntity<>(rpt, HttpStatus.OK);
            }catch (Exception e){
                return null;
            }
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/categoria/update/{operacion}")
    public ResponseEntity<RespuestaTransaccion> mantenimientoUpdateCategoria(@PathVariable(value = "operacion") Integer operacion,
                                                                            @RequestBody JerarqCategoria categoria, HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        if(username != null){
            try{
                RespuestaTransaccion rpt = jerarqArticuloService.mantenimientoCategoria(categoria,username,operacion);
                return new ResponseEntity<>(rpt, HttpStatus.OK);
            }catch (Exception e){
                return null;
            }
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/marca/listar/{estado}/{start}/{length}/{orderby}")
    public ResponseEntity<List<JerarqMarca>> ListarMarca(@PathVariable(value = "estado") String estado,
                                                         @PathVariable(value = "start") String pagStart,
                                                         @PathVariable(value = "length") String pagLength,
                                                         @PathVariable(value = "orderby") String orderBy, HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        if(username != null){
            try{
                List<JerarqMarca> lista = jerarqArticuloService.ListarMarca(estado,pagStart,pagLength,orderBy);
                return new ResponseEntity<>(lista, HttpStatus.OK);
            }catch (Exception e){
                return null;
            }
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/marca/view/{idmarca}")
    public ResponseEntity<JerarqMarca> viewMarca(@PathVariable(value = "idmarca") Integer idmarca, HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        if(username != null){
            try{
                JerarqMarca view = jerarqArticuloService.viewMarca(idmarca);
                return new ResponseEntity<>(view, HttpStatus.OK);
            }catch (Exception e){
                return null;
            }
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/marca/save")
    public ResponseEntity<RespuestaTransaccion> mantenimientoSaveMarca(@RequestBody JerarqMarca marca, HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        if(username != null){
            try{
                RespuestaTransaccion rpt = jerarqArticuloService.mantenimientoMarca(marca,username,1);
                return new ResponseEntity<>(rpt, HttpStatus.OK);
            }catch (Exception e){
                return null;
            }
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/marca/update/{operacion}")
    public ResponseEntity<RespuestaTransaccion> mantenimientoUpdateMarca(@PathVariable(value = "operacion") Integer operacion,
                                                                             @RequestBody JerarqMarca marca, HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        if(username != null){
            try{
                RespuestaTransaccion rpt = jerarqArticuloService.mantenimientoMarca(marca,username,operacion);
                return new ResponseEntity<>(rpt, HttpStatus.OK);
            }catch (Exception e){
                return null;
            }
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/tipo/listar/{estado}/{start}/{length}/{orderby}")
    public ResponseEntity<List<JerarqTipo>> ListarTipo(@PathVariable(value = "estado") String estado,
                                                       @PathVariable(value = "start") String pagStart,
                                                       @PathVariable(value = "length") String pagLength,
                                                       @PathVariable(value = "orderby") String orderBy, HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        if(username != null){
            try{
                List<JerarqTipo> lista = jerarqArticuloService.ListarTipo(estado,pagStart,pagLength,orderBy);
                return new ResponseEntity<>(lista, HttpStatus.OK);
            }catch (Exception e){
                return null;
            }
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/tipo/view/{idtipo}")
    public ResponseEntity<JerarqTipo> viewTipo(@PathVariable(value = "idtipo") Integer idtipo, HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        if(username != null){
            try{
                JerarqTipo view = jerarqArticuloService.viewTipo(idtipo);
                return new ResponseEntity<>(view, HttpStatus.OK);
            }catch (Exception e){
                return null;
            }
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/tipo/save")
    public ResponseEntity<RespuestaTransaccion> mantenimientoSaveTipo(@RequestBody JerarqTipo tipo, HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        if(username != null){
            try{
                RespuestaTransaccion rpt = jerarqArticuloService.mantenimientoTipo(tipo,username,1);
                return new ResponseEntity<>(rpt, HttpStatus.OK);
            }catch (Exception e){
                return null;
            }
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/tipo/update/{operacion}")
    public ResponseEntity<RespuestaTransaccion> mantenimientoUpdateTipo(@PathVariable(value = "operacion") Integer operacion,
                                                                         @RequestBody JerarqTipo tipo, HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        if(username != null){
            try{
                RespuestaTransaccion rpt = jerarqArticuloService.mantenimientoTipo(tipo,username,operacion);
                return new ResponseEntity<>(rpt, HttpStatus.OK);
            }catch (Exception e){
                return null;
            }
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/subtipo/listar/{estado}/{start}/{length}/{orderby}")
    public ResponseEntity<List<JerarqSubTipo>> ListarSubTipo(@PathVariable(value = "estado") String estado,
                                                             @PathVariable(value = "start") String pagStart,
                                                             @PathVariable(value = "length") String pagLength,
                                                             @PathVariable(value = "orderby") String orderBy, HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        if(username != null){
            try{
                List<JerarqSubTipo> lista = jerarqArticuloService.ListarSubTipo(estado,pagStart,pagLength,orderBy);
                return new ResponseEntity<>(lista, HttpStatus.OK);
            }catch (Exception e){
                return null;
            }
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/subtipo/view/{idsubtipo}")
    public ResponseEntity<JerarqSubTipo> viewSubTipo(@PathVariable(value = "idsubtipo") Integer idsubtipo, HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        if(username != null){
            try{
                JerarqSubTipo view = jerarqArticuloService.viewSubTipo(idsubtipo);
                return new ResponseEntity<>(view, HttpStatus.OK);
            }catch (Exception e){
                return null;
            }
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/subtipo/save")
    public ResponseEntity<RespuestaTransaccion> mantenimientoSaveSubTipo(@RequestBody JerarqSubTipo subTipo, HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        if(username != null){
            try{
                RespuestaTransaccion rpt = jerarqArticuloService.mantenimientoSubTipo(subTipo,username,1);
                return new ResponseEntity<>(rpt, HttpStatus.OK);
            }catch (Exception e){
                return null;
            }
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/subtipo/update/{operacion}")
    public ResponseEntity<RespuestaTransaccion> mantenimientoUpdateSubTipo(@PathVariable(value = "operacion") Integer operacion,
                                                                        @RequestBody JerarqSubTipo subTipo, HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        if(username != null){
            try{
                RespuestaTransaccion rpt = jerarqArticuloService.mantenimientoSubTipo(subTipo,username,operacion);
                return new ResponseEntity<>(rpt, HttpStatus.OK);
            }catch (Exception e){
                return null;
            }
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/variedad/listar/{estado}/{start}/{length}/{orderby}")
    public ResponseEntity<List<JerarqVariedad>> ListarVariedad(@PathVariable(value = "estado") String estado,
                                                               @PathVariable(value = "start") String pagStart,
                                                               @PathVariable(value = "length") String pagLength,
                                                               @PathVariable(value = "orderby") String orderBy, HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        if(username != null){
            try{
                List<JerarqVariedad> lista = jerarqArticuloService.ListarVariedad(estado,pagStart,pagLength,orderBy);
                return new ResponseEntity<>(lista, HttpStatus.OK);
            }catch (Exception e){
                return null;
            }
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/variedad/view/{idvariedad}")
    public ResponseEntity<JerarqVariedad> viewVariedad(@PathVariable(value = "idvariedad") Integer idvariedad, HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        if(username != null){
            try{
                JerarqVariedad view = jerarqArticuloService.viewVariedad(idvariedad);
                return new ResponseEntity<>(view, HttpStatus.OK);
            }catch (Exception e){
                return null;
            }
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/variedad/save")
    public ResponseEntity<RespuestaTransaccion> mantenimientoSaveVariedad(@RequestBody JerarqVariedad variedad, HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        if(username != null){
            try{
                RespuestaTransaccion rpt = jerarqArticuloService.mantenimientoVariedad(variedad,username,1);
                return new ResponseEntity<>(rpt, HttpStatus.OK);
            }catch (Exception e){
                return null;
            }
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/variedad/update/{operacion}")
    public ResponseEntity<RespuestaTransaccion> mantenimientoUpdateVariedad(@PathVariable(value = "operacion") Integer operacion,
                                                                           @RequestBody JerarqVariedad variedad, HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        if(username != null){
            try{
                RespuestaTransaccion rpt = jerarqArticuloService.mantenimientoVariedad(variedad,username,operacion);
                return new ResponseEntity<>(rpt, HttpStatus.OK);
            }catch (Exception e){
                return null;
            }
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/presentacion/listar/{estado}/{idenvase}/{idcantidad}/{idunidad}/{start}/{length}/{orderby}")
    public ResponseEntity<List<JerarqPresentacion>> ListarPresentacion(@PathVariable(value = "estado") String estado,
                                                                       @PathVariable(value = "idenvase") Integer idenvase,
                                                                       @PathVariable(value = "idcantidad") Integer idcantidad,
                                                                       @PathVariable(value = "idunidad") Integer idunidad,
                                                                       @PathVariable(value = "start") String pagStart,
                                                                       @PathVariable(value = "length") String pagLength,
                                                                       @PathVariable(value = "orderby") String orderBy, HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        if(username != null){
            try{
                List<JerarqPresentacion> lista = jerarqArticuloService.ListarPresentacion(estado,idenvase,idcantidad,idunidad,pagStart,pagLength,orderBy);
                return new ResponseEntity<>(lista, HttpStatus.OK);
            }catch (Exception e){
                return null;
            }
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/presentacion/view/{idpresentacion}")
    public ResponseEntity<JerarqPresentacion> viewPresentacion(@PathVariable(value = "idpresentacion") Integer idpresentacion, HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        if(username != null){
            try{
                JerarqPresentacion view = jerarqArticuloService.viewPresentacion(idpresentacion);
                return new ResponseEntity<>(view, HttpStatus.OK);
            }catch (Exception e){
                return null;
            }
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/presentacion/save")
    public ResponseEntity<RespuestaTransaccion> mantenimientoSavePresentacion(@RequestBody JerarqPresentacion presentacion, HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        if(username != null){
            try{
                RespuestaTransaccion rpt = jerarqArticuloService.mantenimientoPresentacion(presentacion,username,1);
                return new ResponseEntity<>(rpt, HttpStatus.OK);
            }catch (Exception e){
                return null;
            }
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/presentacion/update/{operacion}")
    public ResponseEntity<RespuestaTransaccion> mantenimientoUpdatePresentacion(@PathVariable(value = "operacion") Integer operacion,
                                                                            @RequestBody JerarqPresentacion presentacion, HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        if(username != null){
            try{
                RespuestaTransaccion rpt = jerarqArticuloService.mantenimientoPresentacion(presentacion,username,operacion);
                return new ResponseEntity<>(rpt, HttpStatus.OK);
            }catch (Exception e){
                return null;
            }
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/atributo/listar/{estado}/{descripcion}/{start}/{length}/{orderby}")
    public ResponseEntity<List<JerarqAtributo>> ListarAtributo(@PathVariable(value = "estado") String estado,
                                                                       @PathVariable(value = "descripcion") String descripcion,
                                                                       @PathVariable(value = "start") String pagStart,
                                                                       @PathVariable(value = "length") String pagLength,
                                                                       @PathVariable(value = "orderby") String orderBy, HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        if(username != null){
            try{
                List<JerarqAtributo> lista = jerarqArticuloService.ListarAtributo(estado,descripcion,pagStart,pagLength,orderBy);
                return new ResponseEntity<>(lista, HttpStatus.OK);
            }catch (Exception e){
                return null;
            }
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/atributo/view/{idatributo}")
    public ResponseEntity<JerarqAtributo> viewAtributo(@PathVariable(value = "idatributo") Integer idatributo, HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        if(username != null){
            try{
                JerarqAtributo view = jerarqArticuloService.viewAtributo(idatributo);
                return new ResponseEntity<>(view, HttpStatus.OK);
            }catch (Exception e){
                return null;
            }
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/atributo/save")
    public ResponseEntity<RespuestaTransaccion> mantenimientoSaveAtributo(@RequestBody JerarqAtributo atributo, HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        if(username != null){
            try{
                RespuestaTransaccion rpt = jerarqArticuloService.mantenimientoAtributo(atributo,username,1);
                return new ResponseEntity<>(rpt, HttpStatus.OK);
            }catch (Exception e){
                return null;
            }
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/atributo/update/{operacion}")
    public ResponseEntity<RespuestaTransaccion> mantenimientoUpdateAtributo(@PathVariable(value = "operacion") Integer operacion,
                                                                            @RequestBody JerarqAtributo atributo, HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        if(username != null){
            try{
                RespuestaTransaccion rpt = jerarqArticuloService.mantenimientoAtributo(atributo,username,operacion);
                return new ResponseEntity<>(rpt, HttpStatus.OK);
            }catch (Exception e){
                return null;
            }
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
