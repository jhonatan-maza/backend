package com.proyecto.web.service;

import com.proyecto.web.model.Usuario;

import java.util.List;

/**
 * Created by jonat on 28/08/2019.
 */
public interface UserJwtService {

    Usuario findByUsername(String username);
    List<Usuario> ListarUsuario(String Usuario, String Empleado, String Estado, String PaginaStart, String PaginaLength, String Orderby);

}
