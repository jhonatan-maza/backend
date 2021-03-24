package com.proyecto.web.service;

import com.proyecto.web.dao.EmpresaDao;
import com.proyecto.web.model.Empresa;
import com.proyecto.web.util.RespuestaTransaccion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by jonat on 3/03/2020.
 */
@Service
public class EmpresaServiceImpl implements EmpresaService {

    @Autowired
    private EmpresaDao empresaDao;

    public List<Empresa> ListarComboEmpresa(Integer operacion){
        return empresaDao.ListarComboEmpresa(operacion);
    }

    public List<Empresa> ListarEmpresa(String Descripcion, String Estado, String PaginaStart, String PaginaLength, String Orderby){
        return empresaDao.ListarEmpresa(Descripcion,Estado,PaginaStart,PaginaLength,Orderby);
    }

    public Empresa viewEmpresa(Integer Codigo){
        return empresaDao.viewEmpresa(Codigo);
    }

    public RespuestaTransaccion mantenimientoEmpresa(Empresa empresa, String usuario, Integer operacion){
        return empresaDao.mantenimientoEmpresa(empresa,usuario,operacion);
    }

}
