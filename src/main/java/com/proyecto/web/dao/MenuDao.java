package com.proyecto.web.dao;

import com.proyecto.web.model.Menu;
import com.proyecto.web.model.MenuPerfil;
import com.proyecto.web.model.MenuPerfilCuatro;
import com.proyecto.web.model.MenuPerfilSeis;

import java.util.List;

/**
 * Created by jonat on 31/08/2019.
 */
public interface MenuDao {

    List<MenuPerfil> MenuPerfilxPosicion(Integer perfil);
    List<MenuPerfilCuatro> MenuPerfilxPosicion_CUATRO(Integer perfil, Integer posicion, Integer menuRef);
    List<MenuPerfilSeis> MenuPerfilxPosicion_SEIS(Integer perfil, Integer posicion, Integer menuRef);
    List<Menu> ListarMenu(String Descripcion, String Estado, String PaginaStart, String PaginaLength, String Orderby);

}
