package com.proyecto.web.dao;

import com.proyecto.web.model.Departamento;
import com.proyecto.web.model.Distrito;
import com.proyecto.web.model.Provincia;

import java.util.List;

/**
 * Created by jonat on 11/04/2020.
 */
public interface UbigeoDao {

    List<Departamento> ListarDepartamento();
    List<Provincia> ListarProvincia(Integer departamento);
    List<Distrito> ListarDistrito(Integer provincia);

}
