package com.proyecto.web.dao;

import com.proyecto.web.model.Empresa;
import com.proyecto.web.model.Localidad;
import com.proyecto.web.model.Sede;
import com.proyecto.web.util.RespuestaTransaccion;
import org.hibernate.SessionFactory;
import org.hibernate.jdbc.ReturningWork;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jonat on 4/03/2020.
 */
@Repository
public class LocalidadDaoImpl implements LocalidadDao {

    private static final Logger log = LoggerFactory.getLogger(LocalidadDaoImpl.class);
    @Autowired
    private SessionFactory sessionFactory;

    @Value("${paqueteConfiguracion}")
    protected String paqueteConfiguracion;

    @Transactional(readOnly = true)
    public List<Localidad> ListarLocalidadXEmpresaXSede(final Integer empresa, final Integer sede) {
        return sessionFactory.getCurrentSession().doReturningWork(new ReturningWork<List<Localidad>>() {
            public List<Localidad> execute(Connection connection) throws SQLException {
                try {
                    CallableStatement cst = connection.prepareCall("{ ? = call SQL_LOCALIDAD(?,?,?)}");
                    cst.registerOutParameter(1, Types.OTHER);
                    cst.setInt(2,empresa);
                    cst.setInt(3,sede);
                    cst.setInt(4,1);
                    cst.execute();
                    List<Localidad> lista = new ArrayList<Localidad>();
                    ResultSet rs = (ResultSet) cst.getObject(1);
                    if (rs != null) {
                        while (rs.next()) {
                            Localidad obj = new Localidad();
                            obj.setIdLocalidad(rs.getInt(1));
                            obj.setDesclocalidad(rs.getString(2));
                            lista.add(obj);
                        }
                        rs.close();
                        cst.close();
                    }
                    return lista;
                }catch ( Exception e) {
                    log.debug("Ocurrio una excepcion "+e.getMessage());
                    return null;
                }
            }
        });
    }

    @Transactional(readOnly = true)
    public List<Localidad> ListarLocalidadXUsuarioCajero() {
        return sessionFactory.getCurrentSession().doReturningWork(new ReturningWork<List<Localidad>>() {
            public List<Localidad> execute(Connection connection) throws SQLException {
                try {
                    CallableStatement cst = connection.prepareCall("{ ? = call SQL_LOCALIDAD(?,?,?)}");
                    cst.registerOutParameter(1, Types.OTHER);
                    cst.setInt(2,0);
                    cst.setInt(3,0);
                    cst.setInt(4,2);
                    cst.execute();
                    List<Localidad> lista = new ArrayList<Localidad>();
                    ResultSet rs = (ResultSet) cst.getObject(1);
                    if (rs != null) {
                        while (rs.next()) {
                            Localidad obj = new Localidad();
                            obj.setIdLocalidad(rs.getInt(1));
                            obj.setDesclocalidad(rs.getString(2));
                            lista.add(obj);
                        }
                        rs.close();
                        cst.close();
                    }
                    return lista;
                }catch ( Exception e) {
                    log.debug("Ocurrio una excepcion "+e.getMessage());
                    return null;
                }
            }
        });
    }

    @Transactional(readOnly = true)
    public List<Localidad> ListarLocalidad(final String Descripcion, final String Estado, final String PaginaStart, final String PaginaLength, final String Orderby) {
        return sessionFactory.getCurrentSession().doReturningWork(new ReturningWork<List<Localidad>>() {
            public List<Localidad> execute(Connection connection) throws SQLException {
                try {
                    CallableStatement cst = connection.prepareCall("{ ? = call "+paqueteConfiguracion+"SQL_LOCALIDAD(?,?,?,?,?,?)}");
                    cst.registerOutParameter(1, Types.OTHER);
                    cst.setInt(2,0);
                    cst.setString(3,Descripcion);
                    cst.setString(4,Estado);
                    cst.setString(5,PaginaStart);
                    cst.setString(6,PaginaLength);
                    cst.setString(7,Orderby);
                    cst.execute();
                    List<Localidad> lista = new ArrayList<Localidad>();
                    ResultSet rs = (ResultSet) cst.getObject(1);
                    if (rs != null) {
                        while (rs.next()) {
                            Localidad obj = new Localidad();
                            obj.setIdLocalidad(rs.getInt(1));
                            obj.setDesclocalidad(rs.getString(2));
                            obj.setDireccion(rs.getString(3));
                            obj.setEstado(rs.getString(4));
                            obj.setEmpresa(new Empresa());
                            obj.getEmpresa().setIdEmpresa(rs.getInt(5));
                            obj.getEmpresa().setDescEmpresa(rs.getString(6));
                            obj.setSede(new Sede());
                            obj.getSede().setIdSede(rs.getInt(7));
                            obj.getSede().setDescSede(rs.getString(8));
                            lista.add(obj);
                        }
                        rs.close();
                        cst.close();
                    }
                    return lista;
                }catch ( Exception e) {
                    log.debug("Ocurrio una excepcion "+e.getMessage());
                    return null;
                }
            }
        });
    }

    @Transactional(readOnly = true)
    public Localidad viewLocalidad(final Integer Codigo) {
        return sessionFactory.getCurrentSession().doReturningWork(new ReturningWork<Localidad>() {
            public Localidad execute(Connection connection) throws SQLException {
                try {
                    CallableStatement cst = connection.prepareCall("{ ? = call "+paqueteConfiguracion+"SQL_LOCALIDAD(?,?,?,?,?,?)}");
                    cst.registerOutParameter(1, Types.OTHER);
                    cst.setInt(2,Codigo);
                    cst.setString(3,null);
                    cst.setString(4,null);
                    cst.setString(5,"0");
                    cst.setString(6,"1");
                    cst.setString(7,"1");
                    cst.execute();
                    Localidad localidad = null;
                    ResultSet rs = (ResultSet) cst.getObject(1);
                    if (rs != null) {
                        while (rs.next()) {
                            localidad = new Localidad();
                            localidad.setIdLocalidad(rs.getInt(1));
                            localidad.setDesclocalidad(rs.getString(2));
                            localidad.setDireccion(rs.getString(3));
                            localidad.setEstado(rs.getString(4));
                            localidad.setEmpresa(new Empresa());
                            localidad.getEmpresa().setIdEmpresa(rs.getInt(5));
                            localidad.getEmpresa().setDescEmpresa(rs.getString(6));
                            localidad.setSede(new Sede());
                            localidad.getSede().setIdSede(rs.getInt(7));
                            localidad.getSede().setDescSede(rs.getString(8));
                        }
                        rs.close();
                        cst.close();
                    }
                    return localidad;
                }catch ( Exception e) {
                    log.debug("Ocurrio una excepcion "+e.getMessage());
                    return null;
                }
            }
        });
    }

    @Transactional(readOnly = false)
    public RespuestaTransaccion mantenimientoLocalidad(final Localidad localidad, final String usuario, final Integer operacion) {
        return  sessionFactory.getCurrentSession().doReturningWork(new ReturningWork<RespuestaTransaccion>() {
            public RespuestaTransaccion execute(Connection connection) throws SQLException {
                RespuestaTransaccion respTransaccion = new RespuestaTransaccion();
                try{
                    CallableStatement cst = connection.prepareCall("{ ? = call "+paqueteConfiguracion+"IUD_LOCALIDAD(?,?,?,?,?,?,?,?) }");
                    cst.registerOutParameter(1, Types.INTEGER);
                    cst.setInt(2, localidad.getIdLocalidad());
                    cst.setInt(3, localidad.getEmpresa().getIdEmpresa());
                    cst.setInt(4, localidad.getSede().getIdSede());
                    cst.setString(5, localidad.getDesclocalidad().toUpperCase());
                    cst.setString(6, localidad.getDireccion().toUpperCase());
                    cst.setString(7, localidad.getEstado());
                    cst.setString(8, usuario);
                    cst.setInt(9, operacion);
                    cst.execute();

                    Integer respuesta = cst.getInt(1);
                    if(respuesta.equals(1)){
                        respTransaccion.setEstado(1); //EXITO
                        respTransaccion.setMensaje("Éxito al guardar");
                        respTransaccion.setMensajeBaseDatos(null);
                    }
                    else if(respuesta.equals(2)){
                        respTransaccion.setEstado(1); //EXITO
                        respTransaccion.setMensaje("Éxito al actualizar");
                        respTransaccion.setMensajeBaseDatos(null);
                    }
                    else if(respuesta.equals(3)){
                        respTransaccion.setEstado(1); //EXITO
                        respTransaccion.setMensaje("Éxito al cambiar estado");
                        respTransaccion.setMensajeBaseDatos(null);
                    }
                    else if(respuesta.equals(100)){
                        respTransaccion.setEstado(5); //ADVERTENCIA
                        respTransaccion.setMensaje("Registro : ( "+ localidad.getDesclocalidad().toUpperCase() +" ) ya existe");
                        respTransaccion.setMensajeBaseDatos(null);
                    }
                    else{
                        respTransaccion.setEstado(0); //ERROR
                        respTransaccion.setMensaje("Transacción Errónea");
                        respTransaccion.setMensajeBaseDatos("Transacción Errónea");
                    }
                    cst.close();
                    return respTransaccion;
                }catch ( SQLException e) {
                    log.debug("Ocurrio una excepcion "+e.getMessage());
                    respTransaccion.setEstado(0);
                    respTransaccion.setMensaje("Transacción Errónea");
                    respTransaccion.setMensajeBaseDatos(e.getMessage());
                    return respTransaccion;
                }
            }
        });
    }

}
