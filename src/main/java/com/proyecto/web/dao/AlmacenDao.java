package com.proyecto.web.dao;

import com.proyecto.web.model.Almacen;
import com.proyecto.web.util.RespuestaTransaccion;

import java.util.List;

/**
 * Created by jonat on 13/04/2020.
 */
public interface AlmacenDao {

    List<Almacen> ListarAlmacen(String Descripcion, String Estado, String PaginaStart, String PaginaLength, String Orderby);
    Almacen viewAlmacen(Integer Codigo);
    RespuestaTransaccion mantenimientoAlmacen(Almacen almacen, String usuario, Integer operacion);

}
