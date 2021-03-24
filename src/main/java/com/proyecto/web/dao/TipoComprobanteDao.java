package com.proyecto.web.dao;

import com.proyecto.web.model.FeTipoDocumento;

import java.util.List;

/**
 * Created by jonat on 16/03/2020.
 */
public interface TipoComprobanteDao {

    List<FeTipoDocumento> ListarTipoDocumentoVenta(Integer operacion);

}
