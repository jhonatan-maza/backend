package com.proyecto.web.service;

import com.proyecto.web.dao.TipoComprobanteDao;
import com.proyecto.web.model.FeTipoDocumento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by jonat on 17/03/2020.
 */
@Service
public class TipoComprobanteServiceImpl implements TipoComprobanteService {

    @Autowired
    private TipoComprobanteDao comprobanteDao;

    public List<FeTipoDocumento> ListarTipoDocumentoVenta(Integer operacion){
        return comprobanteDao.ListarTipoDocumentoVenta(operacion);
    }

}
