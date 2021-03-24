package com.proyecto.web.service;

import com.proyecto.web.dao.RutaDao;
import com.proyecto.web.model.Ruta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by jonat on 4/03/2020.
 */
@Service
public class RutaServiceImpl implements RutaService {

    @Autowired
    private RutaDao rutaDao;

    public List<Ruta> ListarRutaXLocalidad(Integer localidad, Integer operacion){
        return rutaDao.ListarRutaXLocalidad(localidad,operacion);
    }
}
