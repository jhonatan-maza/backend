package com.proyecto.web.controller;

import com.proyecto.web.model.FeTipoDocumento;
import com.proyecto.web.security.JwtTokenUtil;
import com.proyecto.web.service.TipoComprobanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by jonat on 17/03/2020.
 */
@RestController
@CrossOrigin(origins = {"*"})
@RequestMapping("/app/tipocomprobante/")
public class TipoComprobanteController {

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private TipoComprobanteService comprobanteService;

    @GetMapping("documentoventa/")
    public ResponseEntity<List<FeTipoDocumento>> ListarTipoDocumentoVenta(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        if(username != null){
            try {
                List<FeTipoDocumento> lista = comprobanteService.ListarTipoDocumentoVenta(1);
                return new ResponseEntity<>(lista, HttpStatus.OK);
            }catch (Exception e){
                return null;
            }
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
