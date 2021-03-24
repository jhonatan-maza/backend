package com.proyecto.web.service;

import com.proyecto.web.dao.SedeDao;
import com.proyecto.web.model.Sede;
import com.proyecto.web.util.RespuestaTransaccion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by jonat on 3/03/2020.
 */
@Service
public class SedeServiceImpl implements SedeService {

    @Autowired
    private SedeDao sedeDao;

    public List<Sede> ListarSedeXEmpresa(Integer empresa){
        return sedeDao.ListarSedeXEmpresa(empresa);
    }

    public List<Sede> ListarSede(String Descripcion, String Estado, String PaginaStart, String PaginaLength, String Orderby){
        return sedeDao.ListarSede(Descripcion,Estado,PaginaStart,PaginaLength,Orderby);
    }

    public Sede viewSede(Integer Codigo){
        return sedeDao.viewSede(Codigo);
    }

    public RespuestaTransaccion mantenimientoSede(Sede sede, String usuario, Integer operacion){
        return sedeDao.mantenimientoSede(sede,usuario,operacion);
    }

}
