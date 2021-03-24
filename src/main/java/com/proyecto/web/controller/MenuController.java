package com.proyecto.web.controller;

import com.proyecto.web.model.*;
import com.proyecto.web.security.JwtTokenUtil;
import com.proyecto.web.service.MenuService;
import com.proyecto.web.service.UserJwtServiceImpl;
import com.proyecto.web.util.JsonRespuesta;
import com.proyecto.web.util.JsonRespuestaMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by jonat on 31/08/2019.
 */
@RestController
@CrossOrigin(origins = {"*"})
@RequestMapping("/app/menu")
public class MenuController {

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserJwtServiceImpl userJwtService;

    @Autowired
    private MenuService menuService;

    @GetMapping("/listmenu/")
    public ResponseEntity<List<MenuPerfil>> MenuPerfilxPosicion(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        if(username != null){
            try {
                Usuario usuario = userJwtService.findByUsername(username);
                List<MenuPerfil> lista = menuService.MenuPerfilxPosicion(usuario.getIdempleado().getPerfil().getIdPerfil());
                return new ResponseEntity<>(lista, HttpStatus.OK);
            }catch (Exception e){
                return null;
            }
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/listar/{estado}/{start}/{length}/{orderby}")
    public ResponseEntity<List<Menu>> ListarMenu(@PathVariable(value = "estado") String estado,
                                                    @PathVariable(value = "start") String pagStart,
                                                    @PathVariable(value = "length") String pagLength,
                                                    @PathVariable(value = "orderby") String orderBy, HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        if(username != null){
            try{
                List<Menu> lista = menuService.ListarMenu(null,estado,pagStart,pagLength,orderBy);
                return new ResponseEntity<>(lista, HttpStatus.OK);
            }catch (Exception e){
                return null;
            }
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

//    @GetMapping("/listmenu/")
//    public ResponseEntity<JsonRespuestaMenu> articulosOffLine(HttpServletRequest request) throws Exception {
//        String token = request.getHeader(tokenHeader);
//        String username = jwtTokenUtil.getUsernameFromToken(token);
//        JsonRespuestaMenu respuesta = new JsonRespuestaMenu();
//        if(username != null){
//            try {
//                Usuario usuario = userJwtService.findByUsername(username);
//                List<MenuPerfil> lista = menuService.MenuPerfilxPosicion(usuario.getIdempleado().getPerfil().getIdPerfil());
//                String menuHtml = "";
//                List<MenuPerfil> menu = lista;
//                for(int x=0; x<menu.size(); x++){
////                    System.out.println(lista.get(x).getMenu().getIdMenu());
//                    if(x==0){
//                        menuHtml += "<li class='active'>";
//                    }else{
//                        menuHtml += "<li>";
//                    }
//                    menuHtml += "<a href='#' class='has-arrow' aria-expanded='false'>";
//                    menuHtml += "<i class='sidebar-item-icon fa "+menu.get(x).getMenu().getIcono()+"'></i>";
//                    menuHtml += "<span class='nav-label'>"+menu.get(x).getMenu().getDescMenu()+"</span>";
//                    menuHtml += "</a>";
//                    menuHtml += "<ul>";
//                    List<MenuPerfilCuatro> menuCuatro = menu.get(x).getListaMenuPerfilCuatro();
//                    for(int y=0; y<menuCuatro.size(); y++){
////                        System.out.println(menuCuatro.get(y).getMenu().getDescMenu());
//                        menuHtml += "<li>";
//                        menuHtml += "<a href='#'>";
//                        menuHtml += "<span class='nav-label'>"+menuCuatro.get(y).getMenu().getDescMenu()+"</span>";
//                        menuHtml += "<i class='fa fa-angle-left arrow'></i>";
//                        menuHtml += "</a>";
//                        List<MenuPerfilSeis> menuSeis = menuCuatro.get(y).getListaMenuPerfilSeis();
//                        menuHtml += "<ul>";
//                        for(int z=0; z<menuSeis.size(); z++) {
//////                            System.out.println(menuSeis.get(z).getMenu().getDescMenu());
//                            menuHtml += "<li>";
//                            menuHtml += "<a href='/app/nivel' routerLink='/app/"+menuSeis.get(z).getMenu().getLinkSistema()+"' routerLinkActive='active'>";
////                            menuHtml += "<span class='nav-label'>"+menuSeis.get(z).getMenu().getDescMenu()+"</span>";
//                            menuHtml += menuSeis.get(z).getMenu().getDescMenu();
//                            menuHtml += "</a>";
//                            menuHtml += "</li>";
//                        }
//                        menuHtml += "</ul>";
//                        menuHtml += "</li>";
//                    }
//                    menuHtml += "</ul>";
//                    menuHtml += "</li>";
//                }
//
////                for(int x=0; x<menu.size(); x++){
//////                    System.out.println(lista.get(x).getMenu().getIdMenu());
////                    menuHtml += "<li>";
////                    menuHtml += "<a href='javascript:;'>";
////                    menuHtml += "<i class='sidebar-item-icon fa "+menu.get(x).getMenu().getIcono()+"'></i>";
////                    menuHtml += "<span class='nav-label'>"+menu.get(x).getMenu().getDescMenu()+"</span>";
////                    menuHtml += "<i class='fa fa-angle-left arrow'></i>";
////                    menuHtml += "</a>";
////                    menuHtml += "<ul class='nav-2-level collapse'>";
////                    List<MenuPerfilCuatro> menuCuatro = menu.get(x).getListaMenuPerfilCuatro();
////                    for(int y=0; y<menuCuatro.size(); y++){
//////                        System.out.println(menuCuatro.get(y).getMenu().getDescMenu());
////                        menuHtml += "<li>";
////                        menuHtml += "<a href='javascript:;'>";
////                        menuHtml += "<span class='nav-label'>"+menuCuatro.get(y).getMenu().getDescMenu()+"</span>";
////                        menuHtml += "<i class='fa fa-angle-left arrow'></i>";
////                        menuHtml += "</a>";
////                        List<MenuPerfilSeis> menuSeis = menuCuatro.get(y).getListaMenuPerfilSeis();
////                        menuHtml += "<ul class='nav-3-level collapse'>";
////                        for(int z=0; z<menuSeis.size(); z++) {
//////                            System.out.println(menuSeis.get(z).getMenu().getDescMenu());
////                            menuHtml += "<li class='active'>";
////                            menuHtml += "<a routerLink='/app/"+menuSeis.get(z).getMenu().getLinkSistema()+"' routerLinkActive='active'>";
////                            menuHtml += "<span class='nav-label'>"+menuSeis.get(z).getMenu().getDescMenu()+"</span>";
////                            menuHtml += "</a>";
////                            menuHtml += "</li>";
////                        }
////                        menuHtml += "</ul>";
////                        menuHtml += "</li>";
////                    }
////                    menuHtml += "</ul>";
////                    menuHtml += "</li>";
////                }
//
//                respuesta.setEstado(1);
//                respuesta.setMensaje("Menu cargado correctamente");
//                respuesta.setMenu(menuHtml);
//                return new ResponseEntity<>(respuesta, HttpStatus.OK);
//            }catch(Exception e){
//                respuesta.setEstado(0);
//                respuesta.setMensaje("Error al cargar menu");
//                respuesta.setMenu(e.getMessage());
//                return null;
//            }
//        }else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//
//
//
//    }

}
