package com.proyecto.web.service;

import com.proyecto.web.model.Region;

import java.util.List;

/**
 * Created by jonat on 11/04/2020.
 */
public interface RegionService {

    List<Region> ListarRegion(String Descripcion, String Estado, String PaginaStart, String PaginaLength, String Orderby);

}
