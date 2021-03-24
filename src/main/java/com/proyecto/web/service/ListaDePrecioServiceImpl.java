package com.proyecto.web.service;

import com.proyecto.web.dao.ListaDePrecioDao;
import com.proyecto.web.model.PrecioLista;
import com.proyecto.web.util.RespuestaTransaccion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by jonat on 17/03/2020.
 */
@Repository
public class ListaDePrecioServiceImpl implements ListaDePrecioService {

    @Autowired
    private ListaDePrecioDao precioDao;

    public List<PrecioLista> ListaDePrecioXLocalidad(Integer Localidad){
        return precioDao.ListaDePrecioXLocalidad(Localidad);
    }

    public List<PrecioLista> ListarListaPrecio(Integer localidad, String Descripcion, String Estado, String PaginaStart, String PaginaLength, String Orderby){
        return precioDao.ListarListaPrecio(localidad,Descripcion,Estado,PaginaStart,PaginaLength,Orderby);
    }

    public PrecioLista viewListaPrecio(Integer Codigo){
        return precioDao.viewListaPrecio(Codigo);
    }

    public RespuestaTransaccion mantenimientoListaPrecio(PrecioLista lprecio, String usuario, Integer operacion){
        return precioDao.mantenimientoListaPrecio(lprecio,usuario,operacion);
    }

}
