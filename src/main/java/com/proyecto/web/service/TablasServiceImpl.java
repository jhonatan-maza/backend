package com.proyecto.web.service;

import com.proyecto.web.dao.TablasDao;
import com.proyecto.web.model.Tablas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by jonat on 16/03/2020.
 */
@Service
public class TablasServiceImpl implements TablasService {

    @Autowired
    private TablasDao tablasDao;

    public List<Tablas> ListarTablas(String categoria, String Descripcion, String Estado, String PaginaStart, String PaginaLength, String Orderby){
        return tablasDao.ListarTablas(categoria,Descripcion,Estado,PaginaStart,PaginaLength,Orderby);
    }

}
