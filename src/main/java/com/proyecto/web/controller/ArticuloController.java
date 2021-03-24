package com.proyecto.web.controller;

import com.proyecto.web.model.Articulo;
import com.proyecto.web.security.JwtTokenUtil;
import com.proyecto.web.service.ArticuloService;
import com.proyecto.web.util.JsonRespuestaCant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

/**
 * Created by jonat on 14/03/2020.
 */
@RestController
@CrossOrigin(origins = {"*"})
@RequestMapping("/app/articulo")
public class ArticuloController {

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private ArticuloService articuloService;

    @GetMapping("/listar/{tipo_articulo}/{estado}/{familia}/{clase}/{subclase}/{categoria}/{marca}/{tipo}/{subtipo}/{variedad}/{presentacion}/{proveedor}/{start}/{length}/{orderby}")
    public ResponseEntity<List<Articulo>> ListarArticulo(@PathVariable(value = "tipo_articulo") Integer tipoArticulo, @PathVariable(value = "estado") String estado,
                                                         @PathVariable(value = "familia") Integer familia, @PathVariable(value = "clase") Integer clase,
                                                         @PathVariable(value = "subclase") Integer subClase, @PathVariable(value = "categoria") Integer categoria,
                                                         @PathVariable(value = "marca") Integer marca, @PathVariable(value = "tipo") Integer tipo,
                                                         @PathVariable(value = "subtipo") Integer subtipo, @PathVariable(value = "variedad") Integer variedad,
                                                         @PathVariable(value = "presentacion") Integer presentacion, @PathVariable(value = "proveedor") Integer proveedor,
                                                         @PathVariable(value = "start") String pagStart, @PathVariable(value = "length") String pagLength, @PathVariable(value = "orderby") String orderBy, HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        if(username != null){
            try{
                List<Articulo> lista = articuloService.ListarArticulo(tipoArticulo,estado,familia,clase,subClase,categoria,marca,tipo,subtipo,variedad,presentacion,proveedor,pagStart,pagLength,orderBy);
                return new ResponseEntity<>(lista, HttpStatus.OK);
            }catch (Exception e){
                return null;
            }
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/cantidad/{tipo_articulo}/{estado}/{familia}/{clase}/{subclase}/{categoria}/{marca}/{tipo}/{subtipo}/{variedad}/{presentacion}/{proveedor}")
    public ResponseEntity<JsonRespuestaCant> CantidadArticulo(@PathVariable(value = "tipo_articulo") Integer tipoArticulo, @PathVariable(value = "estado") String estado,
                                                           @PathVariable(value = "familia") Integer familia, @PathVariable(value = "clase") Integer clase,
                                                           @PathVariable(value = "subclase") Integer subClase, @PathVariable(value = "categoria") Integer categoria,
                                                           @PathVariable(value = "marca") Integer marca, @PathVariable(value = "tipo") Integer tipo,
                                                           @PathVariable(value = "subtipo") Integer subtipo, @PathVariable(value = "variedad") Integer variedad,
                                                           @PathVariable(value = "presentacion") Integer presentacion, @PathVariable(value = "proveedor") Integer proveedor,
                                                           HttpServletRequest request) {
        JsonRespuestaCant respuesta = new JsonRespuestaCant();
        String token = request.getHeader(tokenHeader);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        if(username != null){
            try{
                Integer cantidad = articuloService.CantidadArticulo(tipoArticulo,estado,familia,clase,subClase,categoria,marca,tipo,subtipo,variedad,presentacion,proveedor);
                respuesta.setCantidad(cantidad);
                return new ResponseEntity<>(respuesta, HttpStatus.OK);
            }catch (Exception e){
                return null;
            }
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value="/imagen/{articuloext}", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<InputStreamResource> ImagenArticulo(@PathVariable(value = "articuloext") String articuloMasExtension,
                                                              HttpServletRequest request) throws IOException {
//        String token = request.getHeader(tokenHeader);
//        String username = jwtTokenUtil.getUsernameFromToken(token);
//        if(username != null){
//
//        }else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
        try {
            Resource resource = new ClassPathResource("static/images/articulo/"+articuloMasExtension);
            return ResponseEntity
                    .ok()
                    .contentType(MediaType.IMAGE_JPEG)
                    .body(new InputStreamResource(resource.getInputStream()));
        }catch (Exception e){
            return null;
        }
    }

}
