package com.proyecto.web.service;

import com.proyecto.web.dao.ComboXOperacionDao;
import com.proyecto.web.model.JerarqCategoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by jonat on 21/04/2020.
 */
@Service
public class ComboXCategoriaServiceImpl implements ComboXCategoriaService {

    @Autowired
    private ComboXOperacionDao comboXOperacionDao;

    public List<JerarqCategoria> ComboXOperacionListarCategoria(Integer operacion){
        return comboXOperacionDao.ComboXOperacionListarCategoria(operacion);
    }

}
