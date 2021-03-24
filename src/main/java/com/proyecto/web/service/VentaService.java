package com.proyecto.web.service;

import com.proyecto.web.model.Cliente;
import com.proyecto.web.model.DataVentaArticulo;
import com.proyecto.web.model.FeTipoDocumento;
import com.proyecto.web.model.Tablas;

import java.util.List;

/**
 * Created by jonat on 16/03/2020.
 */
public interface VentaService {

    List<Cliente> ViewCliente(Integer empresa, String tipoDocumento, String documentoIdeCli, Integer operacion);
    List<DataVentaArticulo> ViewArticulo(Integer articulo, Integer localidad, Integer lprecio, Integer operacion);

}
