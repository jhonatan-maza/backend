package com.proyecto.web.controller;

import com.proyecto.web.model.Cliente;
import com.proyecto.web.model.DataVentaArticulo;
import com.proyecto.web.security.JwtTokenUtil;
import com.proyecto.web.service.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by jonat on 16/03/2020.
 */
@RestController
@CrossOrigin(origins = {"*"})
@RequestMapping("/app/venta/")
public class VentaController {

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private VentaService ventaService;

    @GetMapping("cliente/{empresa}/{tipodoc}")
    public ResponseEntity<List<Cliente>> VentaCliente(@PathVariable(value = "empresa") Integer empresa,
                                                                 @PathVariable(value = "tipodoc") String tipodoc,
                                                                 HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        if(username != null){
            try {
                List<Cliente> lista = ventaService.ViewCliente(empresa,tipodoc,null,1);
                return new ResponseEntity<>(lista, HttpStatus.OK);
            }catch (Exception e){
                return null;
            }
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("articulo/{articulo}/{localidad}/{lprecio}")
    public ResponseEntity<List<DataVentaArticulo>> VentaArticulo(@PathVariable(value = "articulo") Integer articulo,
                                                                @PathVariable(value = "localidad") Integer localidad,
                                                                @PathVariable(value = "lprecio") Integer lprecio,
                                                                HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        if(username != null){
            try {

//                String host = request.getServerName();
//                String server = request.getRemoteUser();
//                String server = request.getRequestURL().toString();
//                Integer puerto = request.getServerPort();
//                System.out.println(  server);

                List<DataVentaArticulo> lista = ventaService.ViewArticulo(
                        articulo,localidad,lprecio,2);
                return new ResponseEntity<>(lista, HttpStatus.OK);
            }catch (Exception e){
                return null;
            }
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
