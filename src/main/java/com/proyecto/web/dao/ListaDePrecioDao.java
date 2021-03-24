package com.proyecto.web.dao;

import com.proyecto.web.model.PrecioLista;
import com.proyecto.web.util.RespuestaTransaccion;

import java.util.List;

/**
 * Created by jonat on 17/03/2020.
 */
public interface ListaDePrecioDao {

    List<PrecioLista> ListaDePrecioXLocalidad(Integer Localidad);
    List<PrecioLista> ListarListaPrecio(Integer localidad, String Descripcion, String Estado, String PaginaStart, String PaginaLength, String Orderby);
    PrecioLista viewListaPrecio(Integer Codigo);
    RespuestaTransaccion mantenimientoListaPrecio(PrecioLista lprecio, String usuario, Integer operacion);
}
