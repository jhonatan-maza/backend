package com.proyecto.web.service;

import com.proyecto.web.model.JerarqCategoria;

import java.util.List;

/**
 * Created by jonat on 21/04/2020.
 */
public interface ComboXCategoriaService {

    List<JerarqCategoria> ComboXOperacionListarCategoria(Integer operacion);
}
