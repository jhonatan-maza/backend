package com.proyecto.web.dao;

import com.proyecto.web.model.Tablas;

import java.util.List;

/**
 * Created by jonat on 16/03/2020.
 */
public interface TablasDao {

    List<Tablas> ListarTablas(String categoria, String Descripcion, String Estado, String PaginaStart, String PaginaLength, String Orderby);

}
