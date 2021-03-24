package com.proyecto.web.service;

import com.proyecto.web.model.Menu;
import com.proyecto.web.model.MenuPerfil;

import java.util.List;

/**
 * Created by jonat on 31/08/2019.
 */
public interface MenuService {

    List<MenuPerfil> MenuPerfilxPosicion(Integer perfil);
    List<Menu> ListarMenu(String Descripcion, String Estado, String PaginaStart, String PaginaLength, String Orderby);

}
