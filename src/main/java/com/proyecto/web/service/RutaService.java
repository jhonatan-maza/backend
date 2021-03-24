package com.proyecto.web.service;

import com.proyecto.web.model.Ruta;

import java.util.List;

/**
 * Created by jonat on 4/03/2020.
 */
public interface RutaService {

    List<Ruta> ListarRutaXLocalidad(Integer localidad, Integer operacion);

}
