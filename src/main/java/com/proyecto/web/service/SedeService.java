package com.proyecto.web.service;

import com.proyecto.web.model.Sede;
import com.proyecto.web.util.RespuestaTransaccion;

import java.util.List;

/**
 * Created by jonat on 3/03/2020.
 */
public interface SedeService {

    List<Sede> ListarSedeXEmpresa(Integer empresa);
    List<Sede> ListarSede(String Descripcion, String Estado, String PaginaStart, String PaginaLength, String Orderby);
    Sede viewSede(Integer Codigo);
    RespuestaTransaccion mantenimientoSede(Sede sede, String usuario, Integer operacion);

}
