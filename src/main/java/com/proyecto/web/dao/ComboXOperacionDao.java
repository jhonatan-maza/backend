package com.proyecto.web.dao;

import com.proyecto.web.model.JerarqCategoria;

import java.util.List;

/**
 * Created by jonat on 21/04/2020.
 */
public interface ComboXOperacionDao {

    List<JerarqCategoria> ComboXOperacionListarCategoria(Integer operacion);
}
