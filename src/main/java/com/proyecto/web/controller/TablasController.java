package com.proyecto.web.controller;

import com.proyecto.web.model.Tablas;
import com.proyecto.web.security.JwtTokenUtil;
import com.proyecto.web.service.TablasService;
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
@RequestMapping("/app/tablas/")
public class TablasController {

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private TablasService tablasService;

    @GetMapping("listar/{categoria}/{estado}/{start}/{length}/{orderby}")
    public ResponseEntity<List<Tablas>> ListarTablas(@PathVariable(value = "categoria") String categoria,
                                                          @PathVariable(value = "estado") String estado,
                                                          @PathVariable(value = "start") String pagStart,
                                                          @PathVariable(value = "length") String pagLength,
                                                          @PathVariable(value = "orderby") String orderBy,HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        if(username != null){
            try {
                List<Tablas> lista = tablasService.ListarTablas(categoria.equals("null")?null:categoria,null,estado,pagStart,pagLength,orderBy);
                return new ResponseEntity<>(lista, HttpStatus.OK);
            }catch (Exception e){
                return null;
            }
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
