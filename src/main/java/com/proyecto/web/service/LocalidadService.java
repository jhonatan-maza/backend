package com.proyecto.web.service;

import com.proyecto.web.model.Localidad;
import com.proyecto.web.util.RespuestaTransaccion;

import java.util.List;

/**
 * Created by jonat on 4/03/2020.
 */
public interface LocalidadService {

    List<Localidad> ListarLocalidadXEmpresaXSede(Integer empresa, Integer sede);
    List<Localidad> ListarLocalidadXUsuarioCajero();
    List<Localidad> ListarLocalidad(String Descripcion, String Estado, String PaginaStart, String PaginaLength, String Orderby);
    Localidad viewLocalidad(Integer Codigo);
    RespuestaTransaccion mantenimientoLocalidad(Localidad localidad, String usuario, Integer operacion);

}
