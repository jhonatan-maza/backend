package com.proyecto.web.dao;

import com.proyecto.web.model.Ruta;

import java.util.List;

/**
 * Created by jonat on 4/03/2020.
 */
public interface RutaDao {

    List<Ruta> ListarRutaXLocalidad(Integer localidad, Integer operacion);

}
