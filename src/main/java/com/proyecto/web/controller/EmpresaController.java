package com.proyecto.web.controller;

import com.proyecto.web.model.Empresa;
import com.proyecto.web.security.JwtTokenUtil;
import com.proyecto.web.service.EmpresaService;
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
@RequestMapping("/app/empresa/")
public class EmpresaController {

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private EmpresaService empresaService;

    @GetMapping("listarcombo/{operacion}")
    public ResponseEntity<List<Empresa>> ListarComboEmpresaXOperacion(@PathVariable(value = "operacion") Integer operacion, HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        if(username != null){
            try {
                List<Empresa> lista = empresaService.ListarComboEmpresa(operacion);
                return new ResponseEntity<>(lista, HttpStatus.OK);
            }catch (Exception e){
                return null;
            }
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/listar/{estado}/{start}/{length}/{orderby}")
    public ResponseEntity<List<Empresa>> ListarEmpresa(@PathVariable(value = "estado") String estado,
                                                     @PathVariable(value = "start") String pagStart,
                                                     @PathVariable(value = "length") String pagLength,
                                                     @PathVariable(value = "orderby") String orderBy, HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        if(username != null){
            try{
                List<Empresa> lista = empresaService.ListarEmpresa(null,estado,pagStart,pagLength,orderBy);
                return new ResponseEntity<>(lista, HttpStatus.OK);
            }catch (Exception e){
                return null;
            }
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/view/{idempresa}")
    public ResponseEntity<Empresa> viewEmpresa(@PathVariable(value = "idempresa") Integer idempresa, HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        if(username != null){
            try{
                Empresa view = empresaService.viewEmpresa(idempresa);
                return new ResponseEntity<>(view, HttpStatus.OK);
            }catch (Exception e){
                return null;
            }
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/save")
    public ResponseEntity<RespuestaTransaccion> mantenimientoSaveEmpresa(@RequestBody Empresa empresa, HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        if(username != null){
            try{
                RespuestaTransaccion rpt = empresaService.mantenimientoEmpresa(empresa,username,1);
                return new ResponseEntity<>(rpt, HttpStatus.OK);
            }catch (Exception e){
                return null;
            }
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update/{operacion}")
    public ResponseEntity<RespuestaTransaccion> mantenimientoUpdateEmpresa(@PathVariable(value = "operacion") Integer operacion,
                                                                         @RequestBody Empresa empresa, HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        if(username != null){
            try{
                RespuestaTransaccion rpt = empresaService.mantenimientoEmpresa(empresa,username,operacion);
                return new ResponseEntity<>(rpt, HttpStatus.OK);
            }catch (Exception e){
                return null;
            }
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
