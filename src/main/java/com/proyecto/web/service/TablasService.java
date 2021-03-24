package com.proyecto.web.service;

import com.proyecto.web.model.Tablas;

import java.util.List;

/**
 * Created by jonat on 16/03/2020.
 */
public interface TablasService {

    List<Tablas> ListarTablas(String categoria, String Descripcion, String Estado, String PaginaStart, String PaginaLength, String Orderby);

}
