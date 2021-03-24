package com.proyecto.web.service;

import com.proyecto.web.dao.JerarqArticuloDao;
import com.proyecto.web.model.*;
import com.proyecto.web.util.RespuestaTransaccion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by jonat on 14/03/2020.
 */
@Service
public class JerarqArticuloServiceImpl implements JerarqArticuloService {

    @Autowired
    private JerarqArticuloDao jerarqArticuloDao;

    public List<JerarqFamilia> ListarFamilia(String estado, String paginaStart, String paginaLength, String orderby){
        return jerarqArticuloDao.ListarFamilia(estado,paginaStart,paginaLength,orderby);
    }

    public JerarqFamilia viewFamilia(Integer Codigo){
        return jerarqArticuloDao.viewFamilia(Codigo);
    }

    public RespuestaTransaccion mantenimientoFamilia(JerarqFamilia familia, String usuario, Integer operacion){
        return jerarqArticuloDao.mantenimientoFamilia(familia,usuario,operacion);
    }

    public List<JerarqClase> ListarClase(String estado, Integer familia, String paginaStart, String paginaLength, String orderby){
        return jerarqArticuloDao.ListarClase(estado,familia,paginaStart,paginaLength,orderby);
    }

    public JerarqClase viewClase(Integer Codigo){
        return jerarqArticuloDao.viewClase(Codigo);
    }

    public RespuestaTransaccion mantenimientoClase(JerarqClase clase, String usuario, Integer operacion){
        return jerarqArticuloDao.mantenimientoClase(clase,usuario,operacion);
    }

    public List<JerarqSubClase> ListarSubClase(String estado, Integer familia, Integer clase, String paginaStart, String paginaLength, String orderby){
        return jerarqArticuloDao.ListarSubClase(estado,familia,clase,paginaStart,paginaLength,orderby);
    }

    public JerarqSubClase viewSubClase(Integer Codigo){
        return jerarqArticuloDao.viewSubClase(Codigo);
    }

    public RespuestaTransaccion mantenimientoSubClase(JerarqSubClase subClase, String usuario, Integer operacion){
        return jerarqArticuloDao.mantenimientoSubClase(subClase,usuario,operacion);
    }

    public List<JerarqCategoria> ListarCategoria(String estado, Integer familia, Integer clase, Integer subClase, String paginaStart, String paginaLength, String orderby){
        return jerarqArticuloDao.ListarCategoria(estado,familia,clase,subClase,paginaStart,paginaLength,orderby);
    }

    public JerarqCategoria viewCategoria(Integer Codigo){
        return jerarqArticuloDao.viewCategoria(Codigo);
    }

    public RespuestaTransaccion mantenimientoCategoria(JerarqCategoria categoria, String usuario, Integer operacion){
        return jerarqArticuloDao.mantenimientoCategoria(categoria,usuario,operacion);
    }

    public List<JerarqMarca> ListarMarca(String estado, String paginaStart, String paginaLength, String orderby){
        return jerarqArticuloDao.ListarMarca(estado,paginaStart,paginaLength,orderby);
    }

    public JerarqMarca viewMarca(Integer Codigo){
        return jerarqArticuloDao.viewMarca(Codigo);
    }

    public RespuestaTransaccion mantenimientoMarca(JerarqMarca marca, String usuario, Integer operacion){
        return jerarqArticuloDao.mantenimientoMarca(marca,usuario,operacion);
    }

    public List<JerarqTipo> ListarTipo(String estado, String paginaStart, String paginaLength, String orderby){
        return jerarqArticuloDao.ListarTipo(estado,paginaStart,paginaLength,orderby);
    }

    public JerarqTipo viewTipo(Integer Codigo){
        return jerarqArticuloDao.viewTipo(Codigo);
    }

    public RespuestaTransaccion mantenimientoTipo(JerarqTipo tipo, String usuario, Integer operacion){
        return jerarqArticuloDao.mantenimientoTipo(tipo,usuario,operacion);
    }

    public List<JerarqSubTipo> ListarSubTipo(String estado, String paginaStart, String paginaLength, String orderby){
        return jerarqArticuloDao.ListarSubTipo(estado,paginaStart,paginaLength,orderby);
    }

    public JerarqSubTipo viewSubTipo(Integer Codigo){
        return jerarqArticuloDao.viewSubTipo(Codigo);
    }

    public RespuestaTransaccion mantenimientoSubTipo(JerarqSubTipo subTipo, String usuario, Integer operacion){
        return jerarqArticuloDao.mantenimientoSubTipo(subTipo,usuario,operacion);
    }

    public List<JerarqVariedad> ListarVariedad(String estado, String paginaStart, String paginaLength, String orderby){
        return jerarqArticuloDao.ListarVariedad(estado,paginaStart,paginaLength,orderby);
    }

    public JerarqVariedad viewVariedad(Integer Codigo){
        return jerarqArticuloDao.viewVariedad(Codigo);
    }

    public RespuestaTransaccion mantenimientoVariedad(JerarqVariedad variedad, String usuario, Integer operacion){
        return jerarqArticuloDao.mantenimientoVariedad(variedad,usuario,operacion);
    }

    public List<JerarqPresentacion> ListarPresentacion(String estado, Integer envase, Integer cantidad, Integer unidad, String paginaStart, String paginaLength, String orderby){
        return jerarqArticuloDao.ListarPresentacion(estado,envase,cantidad,unidad,paginaStart,paginaLength,orderby);
    }

    public JerarqPresentacion viewPresentacion(Integer Codigo){
        return jerarqArticuloDao.viewPresentacion(Codigo);
    }

    public RespuestaTransaccion mantenimientoPresentacion(JerarqPresentacion presentacion, String usuario, Integer operacion){
        return jerarqArticuloDao.mantenimientoPresentacion(presentacion,usuario,operacion);
    }

    public List<JerarqAtributo> ListarAtributo(String estado, String descripcion, String paginaStart, String paginaLength, String orderby){
        return jerarqArticuloDao.ListarAtributo(estado,descripcion,paginaStart,paginaLength,orderby);
    }

    public JerarqAtributo viewAtributo(Integer Codigo){
        return jerarqArticuloDao.viewAtributo(Codigo);
    }

    public RespuestaTransaccion mantenimientoAtributo(JerarqAtributo atributo, String usuario, Integer operacion){
        return jerarqArticuloDao.mantenimientoAtributo(atributo,usuario,operacion);
    }

}
