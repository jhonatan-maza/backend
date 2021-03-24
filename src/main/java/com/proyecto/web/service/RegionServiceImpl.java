package com.proyecto.web.service;

import com.proyecto.web.dao.RegionDao;
import com.proyecto.web.model.Region;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by jonat on 11/04/2020.
 */
@Service
public class RegionServiceImpl implements RegionService {

    @Autowired
    private RegionDao regionDao;

    public List<Region> ListarRegion(String Descripcion, String Estado, String PaginaStart, String PaginaLength, String Orderby){
        return regionDao.ListarRegion(Descripcion,Estado,PaginaStart,PaginaLength,Orderby);
    }
}
