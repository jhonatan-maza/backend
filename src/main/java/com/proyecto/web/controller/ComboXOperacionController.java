package com.proyecto.web.controller;

import com.proyecto.web.model.JerarqCategoria;
import com.proyecto.web.security.JwtTokenUtil;
import com.proyecto.web.service.ComboXCategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by jonat on 21/04/2020.
 */
@RestController
@CrossOrigin(origins = {"*"})
@RequestMapping("/app/combo/")
public class ComboXOperacionController {

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private ComboXCategoriaService comboXCategoriaService;

    @GetMapping("jerarqcategoria")
    public ResponseEntity<List<JerarqCategoria>> ListarComboCategoria(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        if(username != null){
            try {
                List<JerarqCategoria> lista = comboXCategoriaService.ComboXOperacionListarCategoria(1);
                return new ResponseEntity<>(lista, HttpStatus.OK);
            }catch (Exception e){
                return null;
            }
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
