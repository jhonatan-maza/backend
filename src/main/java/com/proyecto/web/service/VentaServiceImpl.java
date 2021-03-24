package com.proyecto.web.service;

import com.proyecto.web.dao.VentaDao;
import com.proyecto.web.model.Cliente;
import com.proyecto.web.model.DataVentaArticulo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by jonat on 16/03/2020.
 */
@Service
public class VentaServiceImpl implements VentaService {

    @Autowired
    private VentaDao ventaDao;

    public List<Cliente> ViewCliente(Integer empresa, String tipoDocumento, String documentoIdeCli, Integer operacion){
        return ventaDao.ViewCliente(empresa,tipoDocumento,documentoIdeCli,operacion);
    }

    public List<DataVentaArticulo> ViewArticulo(Integer articulo, Integer localidad, Integer lprecio, Integer operacion){
        return ventaDao.ViewArticulo(articulo,localidad,lprecio,operacion);
    }

}
