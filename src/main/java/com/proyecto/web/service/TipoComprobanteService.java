package com.proyecto.web.service;

import com.proyecto.web.model.FeTipoDocumento;

import java.util.List;

/**
 * Created by jonat on 17/03/2020.
 */
public interface TipoComprobanteService {

    List<FeTipoDocumento> ListarTipoDocumentoVenta(Integer operacion);

}
