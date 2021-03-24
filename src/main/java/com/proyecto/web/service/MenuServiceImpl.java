package com.proyecto.web.service;

import com.proyecto.web.dao.MenuDao;
import com.proyecto.web.model.Menu;
import com.proyecto.web.model.MenuPerfil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by jonat on 31/08/2019.
 */
@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuDao menuDao;

    public List<MenuPerfil> MenuPerfilxPosicion(Integer perfil){
        return menuDao.MenuPerfilxPosicion(perfil);
    }

    public List<Menu> ListarMenu(String Descripcion, String Estado, String PaginaStart, String PaginaLength, String Orderby){
        return menuDao.ListarMenu(Descripcion,Estado,PaginaStart,PaginaLength,Orderby);
    }

}
