package com.proyecto.web.service;

import com.proyecto.web.model.*;
import com.proyecto.web.util.RespuestaTransaccion;

import java.util.List;

/**
 * Created by jonat on 14/03/2020.
 */
public interface JerarqArticuloService {

    List<JerarqFamilia> ListarFamilia(String estado, String paginaStart, String paginaLength, String orderby);
    JerarqFamilia viewFamilia(Integer Codigo);
    RespuestaTransaccion mantenimientoFamilia(JerarqFamilia familia, String usuario, Integer operacion);

    List<JerarqClase> ListarClase(String estado, Integer familia, String paginaStart, String paginaLength, String orderby);
    JerarqClase viewClase(Integer Codigo);
    RespuestaTransaccion mantenimientoClase(JerarqClase clase, String usuario, Integer operacion);

    List<JerarqSubClase> ListarSubClase(String estado, Integer familia, Integer clase, String paginaStart, String paginaLength, String orderby);
    JerarqSubClase viewSubClase(Integer Codigo);
    RespuestaTransaccion mantenimientoSubClase(JerarqSubClase subClase, String usuario, Integer operacion);

    List<JerarqCategoria> ListarCategoria(String estado, Integer familia, Integer clase, Integer subClase, String paginaStart, String paginaLength, String orderby);
    JerarqCategoria viewCategoria(Integer Codigo);
    RespuestaTransaccion mantenimientoCategoria(JerarqCategoria categoria, String usuario, Integer operacion);

    List<JerarqMarca> ListarMarca(String estado, String paginaStart, String paginaLength, String orderby);
    JerarqMarca viewMarca(Integer Codigo);
    RespuestaTransaccion mantenimientoMarca(JerarqMarca marca, String usuario, Integer operacion);

    List<JerarqTipo> ListarTipo(String estado, String paginaStart, String paginaLength, String orderby);
    JerarqTipo viewTipo(Integer Codigo);
    RespuestaTransaccion mantenimientoTipo(JerarqTipo tipo, String usuario, Integer operacion);

    List<JerarqSubTipo> ListarSubTipo(String estado, String paginaStart, String paginaLength, String orderby);
    JerarqSubTipo viewSubTipo(Integer Codigo);
    RespuestaTransaccion mantenimientoSubTipo(JerarqSubTipo subTipo, String usuario, Integer operacion);

    List<JerarqVariedad> ListarVariedad(String estado, String paginaStart, String paginaLength, String orderby);
    JerarqVariedad viewVariedad(Integer Codigo);
    RespuestaTransaccion mantenimientoVariedad(JerarqVariedad variedad, String usuario, Integer operacion);

    List<JerarqPresentacion> ListarPresentacion(String estado, Integer envase, Integer cantidad, Integer unidad, String paginaStart, String paginaLength, String orderby);
    JerarqPresentacion viewPresentacion(Integer Codigo);
    RespuestaTransaccion mantenimientoPresentacion(JerarqPresentacion presentacion, String usuario, Integer operacion);

    List<JerarqAtributo> ListarAtributo(String estado, String descripcion, String paginaStart, String paginaLength, String orderby);
    JerarqAtributo viewAtributo(Integer Codigo);
    RespuestaTransaccion mantenimientoAtributo(JerarqAtributo atributo, String usuario, Integer operacion);

}
