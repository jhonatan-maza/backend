package com.proyecto.web.service;

import com.proyecto.web.dao.UbigeoDao;
import com.proyecto.web.model.Departamento;
import com.proyecto.web.model.Distrito;
import com.proyecto.web.model.Provincia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by jonat on 11/04/2020.
 */
@Service
public class UbigeoServiceImpl implements UbigeoService {

    @Autowired
    private UbigeoDao ubigeoDao;

    public List<Departamento> ListarDepartamento(){
        return ubigeoDao.ListarDepartamento();
    }

    public List<Provincia> ListarProvincia(Integer departamento){
        return ubigeoDao.ListarProvincia(departamento);
    }

    public List<Distrito> ListarDistrito(Integer provincia){
        return ubigeoDao.ListarDistrito(provincia);
    }

}
