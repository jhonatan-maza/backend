package com.proyecto.web.dao;

import com.proyecto.web.model.Articulo;
import com.proyecto.web.model.Cliente;
import com.proyecto.web.model.DataVentaArticulo;

import java.util.List;

/**
 * Created by jonat on 16/03/2020.
 */
public interface VentaDao {

    List<Cliente> ViewCliente(Integer empresa, String tipoDocumento, String documentoIdeCli, Integer operacion);
    List<DataVentaArticulo> ViewArticulo(Integer articulo, Integer localidad, Integer lprecio, Integer operacion);

}
