package com.proyecto.web.service;

import com.proyecto.web.model.Empresa;
import com.proyecto.web.util.RespuestaTransaccion;

import java.util.List;

/**
 * Created by jonat on 3/03/2020.
 */
public interface EmpresaService {

    List<Empresa> ListarComboEmpresa(Integer operacion);
    List<Empresa> ListarEmpresa(String Descripcion, String Estado, String PaginaStart, String PaginaLength, String Orderby);
    Empresa viewEmpresa(Integer Codigo);
    RespuestaTransaccion mantenimientoEmpresa(Empresa empresa, String usuario, Integer operacion);

}
