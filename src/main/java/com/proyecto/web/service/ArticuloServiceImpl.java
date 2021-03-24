package com.proyecto.web.service;

import com.proyecto.web.dao.ArticuloDao;
import com.proyecto.web.model.Articulo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by jonat on 14/03/2020.
 */
@Service
public class ArticuloServiceImpl implements ArticuloService {

    @Autowired
    private ArticuloDao articuloDao;

    public List<Articulo> ListarArticulo(Integer tipoArticulo, String estado, Integer familia, Integer clase, Integer subClase,
                                  Integer categoria, Integer marca, Integer tipo, Integer subTipo, Integer variedad,
                                  Integer presentacion, Integer proveedor, String paginaStart, String paginaLength, String orderby){
        return articuloDao.ListarArticulo(tipoArticulo,estado,familia,clase,subClase,categoria,marca,tipo,subTipo,variedad,
                presentacion,proveedor,paginaStart,paginaLength,orderby);
    }

    public Integer CantidadArticulo(Integer tipoArticulo, String estado, Integer familia, Integer clase, Integer subClase,
                                    Integer categoria, Integer marca, Integer tipo, Integer subTipo, Integer variedad,
                                    Integer presentacion, Integer proveedor){
        return articuloDao.CantidadArticulo(tipoArticulo,estado,familia,clase,subClase,categoria,marca,tipo,subTipo,
                variedad,presentacion,proveedor);
    }
}
