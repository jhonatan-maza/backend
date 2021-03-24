package com.proyecto.web.dao;

import com.proyecto.web.model.*;
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
 * Created by jonat on 3/03/2020.
 */
@Repository
public class SedeDaoImpl implements SedeDao {

    private static final Logger log = LoggerFactory.getLogger(SedeDaoImpl.class);
    @Autowired
    private SessionFactory sessionFactory;

    @Value("${paqueteConfiguracion}")
    protected String paqueteConfiguracion;

    @Transactional(readOnly = true)
    public List<Sede> ListarSedeXEmpresa(final Integer empresa) {
        return sessionFactory.getCurrentSession().doReturningWork(new ReturningWork<List<Sede>>() {
            public List<Sede> execute(Connection connection) throws SQLException {
                try {
                    CallableStatement cst = connection.prepareCall("{ ? = call SQL_SEDE(?)}");
                    cst.registerOutParameter(1, Types.OTHER);
                    cst.setInt(2,empresa);
                    cst.execute();
                    List<Sede> lista = new ArrayList<Sede>();
                    ResultSet rs = (ResultSet) cst.getObject(1);
                    if (rs != null) {
                        while (rs.next()) {
                            Sede obj = new Sede();
                            obj.setIdSede(rs.getInt(1));
                            obj.setDescSede(rs.getString(2));
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
    public List<Sede> ListarSede(final String Descripcion, final String Estado, final String PaginaStart, final String PaginaLength, final String Orderby) {
        return sessionFactory.getCurrentSession().doReturningWork(new ReturningWork<List<Sede>>() {
            public List<Sede> execute(Connection connection) throws SQLException {
                try {
                    CallableStatement cst = connection.prepareCall("{ ? = call "+paqueteConfiguracion+"SQL_SEDE(?,?,?,?,?,?)}");
                    cst.registerOutParameter(1, Types.OTHER);
                    cst.setInt(2,0);
                    cst.setString(3,Descripcion);
                    cst.setString(4,Estado);
                    cst.setString(5,PaginaStart);
                    cst.setString(6,PaginaLength);
                    cst.setString(7,Orderby);
                    cst.execute();
                    List<Sede> lista = new ArrayList<Sede>();
                    ResultSet rs = (ResultSet) cst.getObject(1);
                    if (rs != null) {
                        while (rs.next()) {
                            Sede obj = new Sede();
                            obj.setIdSede(rs.getInt(1));
                            obj.setDescSede(rs.getString(2));
                            obj.setAbreSede(rs.getString(3));
                            obj.setEstado(rs.getString(4));
                            obj.setRegion(new Region());
                            obj.getRegion().setIdRegion(rs.getInt(5));
                            obj.getRegion().setDescRegion(rs.getString(6));
                            obj.setDistrito(new Distrito());
                            obj.getDistrito().setIdDistrito(rs.getInt(7));
                            obj.getDistrito().setDescDistrito(rs.getString(8));
                            obj.getDistrito().setProvincia(new Provincia());
                            obj.getDistrito().getProvincia().setIdProvincia(rs.getInt(9));
                            obj.getDistrito().getProvincia().setDescProvincia(rs.getString(10));
                            obj.getDistrito().getProvincia().setDepartamento(new Departamento());
                            obj.getDistrito().getProvincia().getDepartamento().setIdDepartamento(rs.getInt(11));
                            obj.getDistrito().getProvincia().getDepartamento().setDescDepartamento(rs.getString(12));
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
    public Sede viewSede(final Integer Codigo) {
        return sessionFactory.getCurrentSession().doReturningWork(new ReturningWork<Sede>() {
            public Sede execute(Connection connection) throws SQLException {
                try {
                    CallableStatement cst = connection.prepareCall("{ ? = call "+paqueteConfiguracion+"SQL_SEDE(?,?,?,?,?,?)}");
                    cst.registerOutParameter(1, Types.OTHER);
                    cst.setInt(2,Codigo);
                    cst.setString(3,null);
                    cst.setString(4,null);
                    cst.setString(5,"0");
                    cst.setString(6,"1");
                    cst.setString(7,"1");
                    cst.execute();
                    Sede sede = null;
                    ResultSet rs = (ResultSet) cst.getObject(1);
                    if (rs != null) {
                        while (rs.next()) {
                            sede = new Sede();
                            sede.setIdSede(rs.getInt(1));
                            sede.setDescSede(rs.getString(2));
                            sede.setAbreSede(rs.getString(3));
                            sede.setEstado(rs.getString(4));
                            sede.setRegion(new Region());
                            sede.getRegion().setIdRegion(rs.getInt(5));
                            sede.getRegion().setDescRegion(rs.getString(6));
                            sede.setDistrito(new Distrito());
                            sede.getDistrito().setIdDistrito(rs.getInt(7));
                            sede.getDistrito().setDescDistrito(rs.getString(8));
                            sede.getDistrito().setProvincia(new Provincia());
                            sede.getDistrito().getProvincia().setIdProvincia(rs.getInt(9));
                            sede.getDistrito().getProvincia().setDescProvincia(rs.getString(10));
                            sede.getDistrito().getProvincia().setDepartamento(new Departamento());
                            sede.getDistrito().getProvincia().getDepartamento().setIdDepartamento(rs.getInt(11));
                            sede.getDistrito().getProvincia().getDepartamento().setDescDepartamento(rs.getString(12));

                        }
                        rs.close();
                        cst.close();
                    }
                    return sede;
                }catch ( Exception e) {
                    log.debug("Ocurrio una excepcion "+e.getMessage());
                    return null;
                }
            }
        });
    }

    @Transactional(readOnly = false)
    public RespuestaTransaccion mantenimientoSede(final Sede sede, final String usuario, final Integer operacion) {
        return  sessionFactory.getCurrentSession().doReturningWork(new ReturningWork<RespuestaTransaccion>() {
            public RespuestaTransaccion execute(Connection connection) throws SQLException {
                RespuestaTransaccion respTransaccion = new RespuestaTransaccion();
                try{
                    CallableStatement cst = connection.prepareCall("{ ? = call "+paqueteConfiguracion+"IUD_SEDE(?,?,?,?,?,?,?,?) }");
                    cst.registerOutParameter(1, Types.INTEGER);
                    cst.setInt(2, sede.getIdSede());
                    cst.setInt(3, sede.getRegion().getIdRegion());
                    cst.setInt(4, sede.getDistrito().getIdDistrito());
                    cst.setString(5, sede.getDescSede().toUpperCase());
                    cst.setString(6, sede.getAbreSede().toUpperCase());
                    cst.setString(7, sede.getEstado());
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
                        respTransaccion.setMensaje("Registro : ( "+ sede.getDescSede().toUpperCase() +" ) ya existe");
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
