package com.proyecto.web.service;

import com.proyecto.web.dao.AlmacenDao;
import com.proyecto.web.model.Almacen;
import com.proyecto.web.util.RespuestaTransaccion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by jonat on 13/04/2020.
 */
@Service
public class AlmacenServiceImpl implements AlmacenService {

    @Autowired
    private AlmacenDao almacenDao;

    public List<Almacen> ListarAlmacen(String Descripcion, String Estado, String PaginaStart, String PaginaLength, String Orderby){
        return almacenDao.ListarAlmacen(Descripcion,Estado,PaginaStart,PaginaLength,Orderby);
    }

    public Almacen viewAlmacen(Integer Codigo){
        return almacenDao.viewAlmacen(Codigo);
    }

    public RespuestaTransaccion mantenimientoAlmacen(Almacen almacen, String usuario, Integer operacion){
        return almacenDao.mantenimientoAlmacen(almacen,usuario,operacion);
    }

}
