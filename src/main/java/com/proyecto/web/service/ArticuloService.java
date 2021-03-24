package com.proyecto.web.service;

import com.proyecto.web.model.Articulo;

import java.util.List;

/**
 * Created by jonat on 14/03/2020.
 */
public interface ArticuloService {

    List<Articulo> ListarArticulo(Integer tipoArticulo, String estado, Integer familia, Integer clase, Integer subClase,
                                  Integer categoria, Integer marca, Integer tipo, Integer subTipo, Integer variedad,
                                  Integer presentacion, Integer proveedor, String paginaStart, String paginaLength, String orderby);

    Integer CantidadArticulo(Integer tipoArticulo, String estado, Integer familia, Integer clase, Integer subClase,
                             Integer categoria, Integer marca, Integer tipo, Integer subTipo, Integer variedad,
                             Integer presentacion, Integer proveedor);
}
