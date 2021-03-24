package com.proyecto.web.controller;

import com.proyecto.web.model.ClienteRuta;
import com.proyecto.web.security.JwtTokenUtil;
import com.proyecto.web.service.ClienteService;
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
@RequestMapping("/app/cliente/")
public class ClienteController {

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private ClienteService clienteService;

    @GetMapping("clientexruta/{idlocalidad}/{idusuario}")
    public ResponseEntity<List<ClienteRuta>> ListarClienteXRuta(@PathVariable(value = "idlocalidad") Integer idlocalidad,
                                                                @PathVariable(value = "idusuario") Integer idusuario,
                                                                HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        if(username != null){
            try {
                List<ClienteRuta> lista = clienteService.ListarClienteXRuta(idlocalidad,idusuario,null,null,null,1);
                return new ResponseEntity<>(lista, HttpStatus.OK);
            }catch (Exception e){
                return null;
            }
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
