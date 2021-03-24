package com.proyecto.web.controller;

import com.proyecto.web.model.Region;
import com.proyecto.web.security.JwtTokenUtil;
import com.proyecto.web.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by jonat on 11/04/2020.
 */@RestController
@CrossOrigin(origins = {"*"})
@RequestMapping("/app/region")
public class RegionController {

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private RegionService regionService;

    @GetMapping("/listar/{estado}/{start}/{length}/{orderby}")
    public ResponseEntity<List<Region>> ListarRegiones(@PathVariable(value = "estado") String estado,
                                                       @PathVariable(value = "start") String pagStart,
                                                       @PathVariable(value = "length") String pagLength,
                                                       @PathVariable(value = "orderby") String orderBy, HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        if(username != null){
            try{
                List<Region> lista = regionService.ListarRegion(null,estado,pagStart,pagLength,orderBy);
                return new ResponseEntity<>(lista, HttpStatus.OK);
            }catch (Exception e){
                return null;
            }
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
