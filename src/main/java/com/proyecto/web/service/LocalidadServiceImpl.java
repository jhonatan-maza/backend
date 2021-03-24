package com.proyecto.web.service;

import com.proyecto.web.dao.LocalidadDao;
import com.proyecto.web.model.Localidad;
import com.proyecto.web.util.RespuestaTransaccion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by jonat on 4/03/2020.
 */
@Service
public class LocalidadServiceImpl implements LocalidadService {

    @Autowired
    private LocalidadDao localidadDao;

    public List<Localidad> ListarLocalidadXEmpresaXSede(Integer empresa, Integer sede){
        return localidadDao.ListarLocalidadXEmpresaXSede(empresa,sede);
    }

    public List<Localidad> ListarLocalidadXUsuarioCajero(){
        return localidadDao.ListarLocalidadXUsuarioCajero();
    }

    public List<Localidad> ListarLocalidad(String Descripcion, String Estado, String PaginaStart, String PaginaLength, String Orderby){
        return localidadDao.ListarLocalidad(Descripcion,Estado,PaginaStart,PaginaLength,Orderby);
    }

    public Localidad viewLocalidad(Integer Codigo){
        return localidadDao.viewLocalidad(Codigo);
    }

    public RespuestaTransaccion mantenimientoLocalidad(Localidad localidad, String usuario, Integer operacion){
        return localidadDao.mantenimientoLocalidad(localidad,usuario,operacion);
    }

}
