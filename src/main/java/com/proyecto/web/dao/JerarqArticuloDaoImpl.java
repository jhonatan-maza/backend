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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jonat on 14/03/2020.
 */
@Repository
public class JerarqArticuloDaoImpl implements JerarqArticuloDao {

    private static final Logger log = LoggerFactory.getLogger(JerarqArticuloDaoImpl.class);
    @Autowired
    private SessionFactory sessionFactory;

    @Value("${paqueteConfiguracion}")
    protected String paqueteConfiguracion;

    @Transactional(readOnly = true)
    public List<JerarqFamilia> ListarFamilia(final String estado, final String paginaStart, final String paginaLength, final String orderby) {
        return sessionFactory.getCurrentSession().doReturningWork(new ReturningWork<List<JerarqFamilia>>() {
            public List<JerarqFamilia> execute(Connection connection) throws SQLException {
                try {
                    CallableStatement cst = connection.prepareCall("{ ? = call "+paqueteConfiguracion+"SQL_ARTICULO(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
                    cst.registerOutParameter(1, Types.OTHER);
                    cst.setInt(2,0);
                    cst.setInt(3,0);
                    cst.setString(4,estado);
                    cst.setString(5,null);
                    cst.setInt(6,0);
                    cst.setInt(7,0);
                    cst.setInt(8,0);
                    cst.setInt(9,0);
                    cst.setInt(10,0);
                    cst.setInt(11,0);
                    cst.setInt(12,0);
                    cst.setInt(13,0);
                    cst.setInt(14,0);
                    cst.setInt(15,0);
                    cst.setInt(16,0);
                    cst.setInt(17,0);
                    cst.setInt(18,0);
                    cst.setInt(19,0);
                    cst.setString(20,paginaStart);
                    cst.setString(21,paginaLength);
                    cst.setString(22,orderby);
                    cst.setInt(23,2);
                    cst.execute();
                    List<JerarqFamilia> lista = new ArrayList<JerarqFamilia>();
                    ResultSet rs = (ResultSet) cst.getObject(1);
                    if (rs != null) {
                        while (rs.next()) {
                            JerarqFamilia obj = new JerarqFamilia();
                            obj.setIdFamilia(rs.getInt(1));
                            obj.setDescFamilia(rs.getString(2));
                            obj.setEstado(rs.getString(3));
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
    public JerarqFamilia viewFamilia(final Integer Codigo) {
        return sessionFactory.getCurrentSession().doReturningWork(new ReturningWork<JerarqFamilia>() {
            public JerarqFamilia execute(Connection connection) throws SQLException {
                try {
                    CallableStatement cst = connection.prepareCall("{ ? = call "+paqueteConfiguracion+"SQL_ARTICULO(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
                    cst.registerOutParameter(1, Types.OTHER);
                    cst.setInt(2,0);
                    cst.setInt(3,0);
                    cst.setString(4,null);
                    cst.setString(5,null);
                    cst.setInt(6,Codigo);
                    cst.setInt(7,0);
                    cst.setInt(8,0);
                    cst.setInt(9,0);
                    cst.setInt(10,0);
                    cst.setInt(11,0);
                    cst.setInt(12,0);
                    cst.setInt(13,0);
                    cst.setInt(14,0);
                    cst.setInt(15,0);
                    cst.setInt(16,0);
                    cst.setInt(17,0);
                    cst.setInt(18,0);
                    cst.setInt(19,0);
                    cst.setString(20,"0");
                    cst.setString(21,"1");
                    cst.setString(22,"1");
                    cst.setInt(23,2);
                    cst.execute();
                    JerarqFamilia familia = null;
                    ResultSet rs = (ResultSet) cst.getObject(1);
                    if (rs != null) {
                        while (rs.next()) {
                            familia = new JerarqFamilia();
                            familia.setIdFamilia(rs.getInt(1));
                            familia.setDescFamilia(rs.getString(2));
                            familia.setEstado(rs.getString(3));
                        }
                        rs.close();
                        cst.close();
                    }
                    return familia;
                }catch ( Exception e) {
                    log.debug("Ocurrio una excepcion "+e.getMessage());
                    return null;
                }
            }
        });
    }

    @Transactional(readOnly = false)
    public RespuestaTransaccion mantenimientoFamilia(final JerarqFamilia familia, final String usuario, final Integer operacion) {
        return  sessionFactory.getCurrentSession().doReturningWork(new ReturningWork<RespuestaTransaccion>() {
            public RespuestaTransaccion execute(Connection connection) throws SQLException {
                RespuestaTransaccion respTransaccion = new RespuestaTransaccion();
                try{
                    CallableStatement cst = connection.prepareCall("{ ? = call "+paqueteConfiguracion+"IUD_JERARQ_FAMILIA(?,?,?,?,?) }");
                    cst.registerOutParameter(1, Types.INTEGER);
                    cst.setInt(2, familia.getIdFamilia());
                    cst.setString(3, familia.getDescFamilia().toUpperCase());
                    cst.setString(4, familia.getEstado());
                    cst.setString(5, usuario);
                    cst.setInt(6, operacion);
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
                        respTransaccion.setMensaje("Registro : ( "+ familia.getDescFamilia().toUpperCase() +" ) ya existe");
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

    @Transactional(readOnly = true)
    public List<JerarqClase> ListarClase(final String estado, final Integer familia, final String paginaStart, final String paginaLength, final String orderby) {
        return sessionFactory.getCurrentSession().doReturningWork(new ReturningWork<List<JerarqClase>>() {
            public List<JerarqClase> execute(Connection connection) throws SQLException {
                try {
                    CallableStatement cst = connection.prepareCall("{ ? = call "+paqueteConfiguracion+"SQL_ARTICULO(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
                    cst.registerOutParameter(1, Types.OTHER);
                    cst.setInt(2,0);
                    cst.setInt(3,0);
                    cst.setString(4,estado);
                    cst.setString(5,null);
                    cst.setInt(6,familia);
                    cst.setInt(7,0);
                    cst.setInt(8,0);
                    cst.setInt(9,0);
                    cst.setInt(10,0);
                    cst.setInt(11,0);
                    cst.setInt(12,0);
                    cst.setInt(13,0);
                    cst.setInt(14,0);
                    cst.setInt(15,0);
                    cst.setInt(16,0);
                    cst.setInt(17,0);
                    cst.setInt(18,0);
                    cst.setInt(19,0);
                    cst.setString(20,paginaStart);
                    cst.setString(21,paginaLength);
                    cst.setString(22,orderby);
                    cst.setInt(23,3);
                    cst.execute();
                    List<JerarqClase> lista = new ArrayList<JerarqClase>();
                    ResultSet rs = (ResultSet) cst.getObject(1);
                    if (rs != null) {
                        while (rs.next()) {
                            JerarqClase obj = new JerarqClase();
                            obj.setIdClase(rs.getInt(1));
                            obj.setDescClase(rs.getString(2));
                            obj.setEstado(rs.getString(3));
                            obj.setFamilia(new JerarqFamilia());
                            obj.getFamilia().setIdFamilia(rs.getInt(4));
                            obj.getFamilia().setDescFamilia(rs.getString(5));
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
    public JerarqClase viewClase(final Integer Codigo) {
        return sessionFactory.getCurrentSession().doReturningWork(new ReturningWork<JerarqClase>() {
            public JerarqClase execute(Connection connection) throws SQLException {
                try {
                    CallableStatement cst = connection.prepareCall("{ ? = call "+paqueteConfiguracion+"SQL_ARTICULO(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
                    cst.registerOutParameter(1, Types.OTHER);
                    cst.setInt(2,0);
                    cst.setInt(3,0);
                    cst.setString(4,null);
                    cst.setString(5,null);
                    cst.setInt(6,0);
                    cst.setInt(7,Codigo);
                    cst.setInt(8,0);
                    cst.setInt(9,0);
                    cst.setInt(10,0);
                    cst.setInt(11,0);
                    cst.setInt(12,0);
                    cst.setInt(13,0);
                    cst.setInt(14,0);
                    cst.setInt(15,0);
                    cst.setInt(16,0);
                    cst.setInt(17,0);
                    cst.setInt(18,0);
                    cst.setInt(19,0);
                    cst.setString(20,"0");
                    cst.setString(21,"1");
                    cst.setString(22,"1");
                    cst.setInt(23,3);
                    cst.execute();
                    JerarqClase clase = null;
                    ResultSet rs = (ResultSet) cst.getObject(1);
                    if (rs != null) {
                        while (rs.next()) {
                            clase = new JerarqClase();
                            clase.setIdClase(rs.getInt(1));
                            clase.setDescClase(rs.getString(2));
                            clase.setEstado(rs.getString(3));
                            clase.setFamilia(new JerarqFamilia());
                            clase.getFamilia().setIdFamilia(rs.getInt(4));
                            clase.getFamilia().setDescFamilia(rs.getString(5));
                        }
                        rs.close();
                        cst.close();
                    }
                    return clase;
                }catch ( Exception e) {
                    log.debug("Ocurrio una excepcion "+e.getMessage());
                    return null;
                }
            }
        });
    }

    @Transactional(readOnly = false)
    public RespuestaTransaccion mantenimientoClase(final JerarqClase clase, final String usuario, final Integer operacion) {
        return  sessionFactory.getCurrentSession().doReturningWork(new ReturningWork<RespuestaTransaccion>() {
            public RespuestaTransaccion execute(Connection connection) throws SQLException {
                RespuestaTransaccion respTransaccion = new RespuestaTransaccion();
                try{
                    CallableStatement cst = connection.prepareCall("{ ? = call "+paqueteConfiguracion+"IUD_JERARQ_CLASE(?,?,?,?,?,?) }");
                    cst.registerOutParameter(1, Types.INTEGER);
                    cst.setInt(2, clase.getIdClase());
                    cst.setInt(3, clase.getFamilia().getIdFamilia());
                    cst.setString(4, clase.getDescClase().toUpperCase());
                    cst.setString(5, clase.getEstado());
                    cst.setString(6, usuario);
                    cst.setInt(7, operacion);
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
                        respTransaccion.setMensaje("Registro : ( "+ clase.getDescClase().toUpperCase() +" ) ya existe");
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

    @Transactional(readOnly = true)
    public List<JerarqSubClase> ListarSubClase(final String estado, final Integer familia, final Integer clase, final String paginaStart, final String paginaLength, final String orderby) {
        return sessionFactory.getCurrentSession().doReturningWork(new ReturningWork<List<JerarqSubClase>>() {
            public List<JerarqSubClase> execute(Connection connection) throws SQLException {
                try {
                    CallableStatement cst = connection.prepareCall("{ ? = call "+paqueteConfiguracion+"SQL_ARTICULO(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
                    cst.registerOutParameter(1, Types.OTHER);
                    cst.setInt(2,0);
                    cst.setInt(3,0);
                    cst.setString(4,estado);
                    cst.setString(5,null);
                    cst.setInt(6,familia);
                    cst.setInt(7,clase);
                    cst.setInt(8,0);
                    cst.setInt(9,0);
                    cst.setInt(10,0);
                    cst.setInt(11,0);
                    cst.setInt(12,0);
                    cst.setInt(13,0);
                    cst.setInt(14,0);
                    cst.setInt(15,0);
                    cst.setInt(16,0);
                    cst.setInt(17,0);
                    cst.setInt(18,0);
                    cst.setInt(19,0);
                    cst.setString(20,paginaStart);
                    cst.setString(21,paginaLength);
                    cst.setString(22,orderby);
                    cst.setInt(23,4);
                    cst.execute();
                    List<JerarqSubClase> lista = new ArrayList<JerarqSubClase>();
                    ResultSet rs = (ResultSet) cst.getObject(1);
                    if (rs != null) {
                        while (rs.next()) {
                            JerarqSubClase obj = new JerarqSubClase();
                            obj.setIdSubClase(rs.getInt(1));
                            obj.setDescSubClase(rs.getString(2));
                            obj.setEstado(rs.getString(3));
                            obj.setArrayPresentacion(rs.getString(4));
                            obj.setClase(new JerarqClase());
                            obj.getClase().setIdClase(rs.getInt(5));
                            obj.getClase().setDescClase(rs.getString(6));
                            obj.getClase().setFamilia(new JerarqFamilia());
                            obj.getClase().getFamilia().setIdFamilia(rs.getInt(7));
                            obj.getClase().getFamilia().setDescFamilia(rs.getString(8));
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
    public JerarqSubClase viewSubClase(final Integer Codigo) {
        return sessionFactory.getCurrentSession().doReturningWork(new ReturningWork<JerarqSubClase>() {
            public JerarqSubClase execute(Connection connection) throws SQLException {
                try {
                    CallableStatement cst = connection.prepareCall("{ ? = call "+paqueteConfiguracion+"SQL_ARTICULO(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
                    cst.registerOutParameter(1, Types.OTHER);
                    cst.setInt(2,0);
                    cst.setInt(3,0);
                    cst.setString(4,null);
                    cst.setString(5,null);
                    cst.setInt(6,0);
                    cst.setInt(7,0);
                    cst.setInt(8,Codigo);
                    cst.setInt(9,0);
                    cst.setInt(10,0);
                    cst.setInt(11,0);
                    cst.setInt(12,0);
                    cst.setInt(13,0);
                    cst.setInt(14,0);
                    cst.setInt(15,0);
                    cst.setInt(16,0);
                    cst.setInt(17,0);
                    cst.setInt(18,0);
                    cst.setInt(19,0);
                    cst.setString(20,"0");
                    cst.setString(21,"1");
                    cst.setString(22,"1");
                    cst.setInt(23,4);
                    cst.execute();
                    JerarqSubClase subclase = null;
                    ResultSet rs = (ResultSet) cst.getObject(1);
                    if (rs != null) {
                        while (rs.next()) {
                            subclase = new JerarqSubClase();
                            subclase.setIdSubClase(rs.getInt(1));
                            subclase.setDescSubClase(rs.getString(2));
                            subclase.setEstado(rs.getString(3));
                            subclase.setArrayPresentacion(rs.getString(4));
                            subclase.setClase(new JerarqClase());
                            subclase.getClase().setIdClase(rs.getInt(5));
                            subclase.getClase().setDescClase(rs.getString(6));
                            subclase.getClase().setFamilia(new JerarqFamilia());
                            subclase.getClase().getFamilia().setIdFamilia(rs.getInt(7));
                            subclase.getClase().getFamilia().setDescFamilia(rs.getString(8));
                        }
                        rs.close();
                        cst.close();
                    }
                    return subclase;
                }catch ( Exception e) {
                    log.debug("Ocurrio una excepcion "+e.getMessage());
                    return null;
                }
            }
        });
    }

    @Transactional(readOnly = false)
    public RespuestaTransaccion mantenimientoSubClase(final JerarqSubClase subClase, final String usuario, final Integer operacion) {
        return  sessionFactory.getCurrentSession().doReturningWork(new ReturningWork<RespuestaTransaccion>() {
            public RespuestaTransaccion execute(Connection connection) throws SQLException {
                RespuestaTransaccion respTransaccion = new RespuestaTransaccion();
                try{
                    CallableStatement cst = connection.prepareCall("{ ? = call "+paqueteConfiguracion+"IUD_JERARQ_SUBCLASE(?,?,?,?,?,?,?) }");
                    cst.registerOutParameter(1, Types.INTEGER);
                    cst.setInt(2, subClase.getIdSubClase());
                    cst.setInt(3, subClase.getClase().getIdClase());
                    cst.setString(4, subClase.getDescSubClase().toUpperCase());
                    cst.setString(5, subClase.getEstado());
                    cst.setObject(6,subClase.getArrayPresentacion(),Types.OTHER);
                    cst.setString(7, usuario);
                    cst.setInt(8, operacion);
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
                        respTransaccion.setMensaje("Registro : ( "+ subClase.getDescSubClase().toUpperCase() +" ) ya existe");
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

    @Transactional(readOnly = true)
    public List<JerarqCategoria> ListarCategoria(final String estado, final Integer familia, final Integer clase, final Integer subClase, final String paginaStart, final String paginaLength, final String orderby) {
        return sessionFactory.getCurrentSession().doReturningWork(new ReturningWork<List<JerarqCategoria>>() {
            public List<JerarqCategoria> execute(Connection connection) throws SQLException {
                try {
                    CallableStatement cst = connection.prepareCall("{ ? = call "+paqueteConfiguracion+"SQL_ARTICULO(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
                    cst.registerOutParameter(1, Types.OTHER);
                    cst.setInt(2,0);
                    cst.setInt(3,0);
                    cst.setString(4,estado);
                    cst.setString(5,null);
                    cst.setInt(6,familia);
                    cst.setInt(7,clase);
                    cst.setInt(8,subClase);
                    cst.setInt(9,0);
                    cst.setInt(10,0);
                    cst.setInt(11,0);
                    cst.setInt(12,0);
                    cst.setInt(13,0);
                    cst.setInt(14,0);
                    cst.setInt(15,0);
                    cst.setInt(16,0);
                    cst.setInt(17,0);
                    cst.setInt(18,0);
                    cst.setInt(19,0);
                    cst.setString(20,paginaStart);
                    cst.setString(21,paginaLength);
                    cst.setString(22,orderby);
                    cst.setInt(23,5);
                    cst.execute();
                    List<JerarqCategoria> lista = new ArrayList<JerarqCategoria>();
                    ResultSet rs = (ResultSet) cst.getObject(1);
                    if (rs != null) {
                        while (rs.next()) {
                            JerarqCategoria obj = new JerarqCategoria();
                            obj.setIdCategoria(rs.getInt(1));
                            obj.setDescCategoria(rs.getString(2));
                            obj.setEstado(rs.getString(3));
                            obj.setArrayMarca(rs.getString(4));
                            obj.setArrayTipo(rs.getString(5));
                            obj.setSubClase(new JerarqSubClase());
                            obj.getSubClase().setIdSubClase(rs.getInt(6));
                            obj.getSubClase().setDescSubClase(rs.getString(7));
                            obj.getSubClase().setClase(new JerarqClase());
                            obj.getSubClase().getClase().setIdClase(rs.getInt(8));
                            obj.getSubClase().getClase().setDescClase(rs.getString(9));
                            obj.getSubClase().getClase().setFamilia(new JerarqFamilia());
                            obj.getSubClase().getClase().getFamilia().setIdFamilia(rs.getInt(10));
                            obj.getSubClase().getClase().getFamilia().setDescFamilia(rs.getString(11));
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
    public JerarqCategoria viewCategoria(final Integer Codigo) {
        return sessionFactory.getCurrentSession().doReturningWork(new ReturningWork<JerarqCategoria>() {
            public JerarqCategoria execute(Connection connection) throws SQLException {
                try {
                    CallableStatement cst = connection.prepareCall("{ ? = call "+paqueteConfiguracion+"SQL_ARTICULO(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
                    cst.registerOutParameter(1, Types.OTHER);
                    cst.setInt(2,0);
                    cst.setInt(3,0);
                    cst.setString(4,null);
                    cst.setString(5,null);
                    cst.setInt(6,0);
                    cst.setInt(7,0);
                    cst.setInt(8,0);
                    cst.setInt(9,Codigo);
                    cst.setInt(10,0);
                    cst.setInt(11,0);
                    cst.setInt(12,0);
                    cst.setInt(13,0);
                    cst.setInt(14,0);
                    cst.setInt(15,0);
                    cst.setInt(16,0);
                    cst.setInt(17,0);
                    cst.setInt(18,0);
                    cst.setInt(19,0);
                    cst.setString(20,"0");
                    cst.setString(21,"1");
                    cst.setString(22,"1");
                    cst.setInt(23,5);
                    cst.execute();
                    JerarqCategoria categoria = null;
                    ResultSet rs = (ResultSet) cst.getObject(1);
                    if (rs != null) {
                        while (rs.next()) {
                            categoria = new JerarqCategoria();
                            categoria.setIdCategoria(rs.getInt(1));
                            categoria.setDescCategoria(rs.getString(2));
                            categoria.setEstado(rs.getString(3));
                            categoria.setArrayMarca(rs.getString(4));
                            categoria.setArrayTipo(rs.getString(5));
                            categoria.setSubClase(new JerarqSubClase());
                            categoria.getSubClase().setIdSubClase(rs.getInt(6));
                            categoria.getSubClase().setDescSubClase(rs.getString(7));
                            categoria.getSubClase().setClase(new JerarqClase());
                            categoria.getSubClase().getClase().setIdClase(rs.getInt(8));
                            categoria.getSubClase().getClase().setDescClase(rs.getString(9));
                            categoria.getSubClase().getClase().setFamilia(new JerarqFamilia());
                            categoria.getSubClase().getClase().getFamilia().setIdFamilia(rs.getInt(10));
                            categoria.getSubClase().getClase().getFamilia().setDescFamilia(rs.getString(11));
                        }
                        rs.close();
                        cst.close();
                    }
                    return categoria;
                }catch ( Exception e) {
                    log.debug("Ocurrio una excepcion "+e.getMessage());
                    return null;
                }
            }
        });
    }

    @Transactional(readOnly = false)
    public RespuestaTransaccion mantenimientoCategoria(final JerarqCategoria categoria, final String usuario, final Integer operacion) {
        return  sessionFactory.getCurrentSession().doReturningWork(new ReturningWork<RespuestaTransaccion>() {
            public RespuestaTransaccion execute(Connection connection) throws SQLException {
                RespuestaTransaccion respTransaccion = new RespuestaTransaccion();
                try{
                    CallableStatement cst = connection.prepareCall("{ ? = call "+paqueteConfiguracion+"IUD_JERARQ_CATEGORIA(?,?,?,?,?,?,?,?) }");
                    cst.registerOutParameter(1, Types.INTEGER);
                    cst.setInt(2, categoria.getIdCategoria());
                    cst.setInt(3, categoria.getSubClase().getIdSubClase());
                    cst.setString(4, categoria.getDescCategoria().toUpperCase());
                    cst.setString(5, categoria.getEstado());
                    cst.setObject(6, categoria.getArrayMarca(),Types.OTHER);
                    cst.setObject(7, categoria.getArrayTipo(),Types.OTHER);
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
                        respTransaccion.setMensaje("Registro : ( "+ categoria.getDescCategoria().toUpperCase() +" ) ya existe");
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

    @Transactional(readOnly = true)
    public List<JerarqMarca> ListarMarca(final String estado,
                                         final String paginaStart, final String paginaLength, final String orderby) {
        return sessionFactory.getCurrentSession().doReturningWork(new ReturningWork<List<JerarqMarca>>() {
            public List<JerarqMarca> execute(Connection connection) throws SQLException {
                try {
                    CallableStatement cst = connection.prepareCall("{ ? = call "+paqueteConfiguracion+"SQL_ARTICULO(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
                    cst.registerOutParameter(1, Types.OTHER);
                    cst.setInt(2,0);
                    cst.setInt(3,0);
                    cst.setString(4,estado);
                    cst.setString(5,null);
                    cst.setInt(6,0);
                    cst.setInt(7,0);
                    cst.setInt(8,0);
                    cst.setInt(9,0);
                    cst.setInt(10,0);
                    cst.setInt(11,0);
                    cst.setInt(12,0);
                    cst.setInt(13,0);
                    cst.setInt(14,0);
                    cst.setInt(15,0);
                    cst.setInt(16,0);
                    cst.setInt(17,0);
                    cst.setInt(18,0);
                    cst.setInt(19,0);
                    cst.setString(20,paginaStart);
                    cst.setString(21,paginaLength);
                    cst.setString(22,orderby);
                    cst.setInt(23,6);
                    cst.execute();
                    List<JerarqMarca> lista = new ArrayList<JerarqMarca>();
                    ResultSet rs = (ResultSet) cst.getObject(1);
                    if (rs != null) {
                        while (rs.next()) {
                            JerarqMarca obj = new JerarqMarca();
                            obj.setIdMarca(rs.getInt(1));
                            obj.setDescMarca(rs.getString(2));
                            obj.setEstado(rs.getString(3));
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
    public JerarqMarca viewMarca(final Integer Codigo) {
        return sessionFactory.getCurrentSession().doReturningWork(new ReturningWork<JerarqMarca>() {
            public JerarqMarca execute(Connection connection) throws SQLException {
                try {
                    CallableStatement cst = connection.prepareCall("{ ? = call "+paqueteConfiguracion+"SQL_ARTICULO(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
                    cst.registerOutParameter(1, Types.OTHER);
                    cst.setInt(2,0);
                    cst.setInt(3,0);
                    cst.setString(4,null);
                    cst.setString(5,null);
                    cst.setInt(6,0);
                    cst.setInt(7,0);
                    cst.setInt(8,0);
                    cst.setInt(9,0);
                    cst.setInt(10,Codigo);
                    cst.setInt(11,0);
                    cst.setInt(12,0);
                    cst.setInt(13,0);
                    cst.setInt(14,0);
                    cst.setInt(15,0);
                    cst.setInt(16,0);
                    cst.setInt(17,0);
                    cst.setInt(18,0);
                    cst.setInt(19,0);
                    cst.setString(20,"0");
                    cst.setString(21,"1");
                    cst.setString(22,"1");
                    cst.setInt(23,6);
                    cst.execute();
                    JerarqMarca marca = null;
                    ResultSet rs = (ResultSet) cst.getObject(1);
                    if (rs != null) {
                        while (rs.next()) {
                            marca = new JerarqMarca();
                            marca.setIdMarca(rs.getInt(1));
                            marca.setDescMarca(rs.getString(2));
                            marca.setEstado(rs.getString(3));
                        }
                        rs.close();
                        cst.close();
                    }
                    return marca;
                }catch ( Exception e) {
                    log.debug("Ocurrio una excepcion "+e.getMessage());
                    return null;
                }
            }
        });
    }

    @Transactional(readOnly = false)
    public RespuestaTransaccion mantenimientoMarca(final JerarqMarca marca, final String usuario, final Integer operacion) {
        return  sessionFactory.getCurrentSession().doReturningWork(new ReturningWork<RespuestaTransaccion>() {
            public RespuestaTransaccion execute(Connection connection) throws SQLException {
                RespuestaTransaccion respTransaccion = new RespuestaTransaccion();
                try{
                    CallableStatement cst = connection.prepareCall("{ ? = call "+paqueteConfiguracion+"IUD_JERARQ_MARCA(?,?,?,?,?) }");
                    cst.registerOutParameter(1, Types.INTEGER);
                    cst.setInt(2, marca.getIdMarca());
                    cst.setString(3, marca.getDescMarca().toUpperCase());
                    cst.setString(4, marca.getEstado());
                    cst.setString(5, usuario);
                    cst.setInt(6, operacion);
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
                        respTransaccion.setMensaje("Registro : ( "+ marca.getDescMarca().toUpperCase() +" ) ya existe");
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

    @Transactional(readOnly = true)
    public List<JerarqTipo> ListarTipo(final String estado, final String paginaStart, final String paginaLength, final String orderby) {
        return sessionFactory.getCurrentSession().doReturningWork(new ReturningWork<List<JerarqTipo>>() {
            public List<JerarqTipo> execute(Connection connection) throws SQLException {
                try {
                    CallableStatement cst = connection.prepareCall("{ ? = call "+paqueteConfiguracion+"SQL_ARTICULO(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
                    cst.registerOutParameter(1, Types.OTHER);
                    cst.setInt(2,0);
                    cst.setInt(3,0);
                    cst.setString(4,estado);
                    cst.setString(5,null);
                    cst.setInt(6,0);
                    cst.setInt(7,0);
                    cst.setInt(8,0);
                    cst.setInt(9,0);
                    cst.setInt(10,0);
                    cst.setInt(11,0);
                    cst.setInt(12,0);
                    cst.setInt(13,0);
                    cst.setInt(14,0);
                    cst.setInt(15,0);
                    cst.setInt(16,0);
                    cst.setInt(17,0);
                    cst.setInt(18,0);
                    cst.setInt(19,0);
                    cst.setString(20,paginaStart);
                    cst.setString(21,paginaLength);
                    cst.setString(22,orderby);
                    cst.setInt(23,7);
                    cst.execute();
                    List<JerarqTipo> lista = new ArrayList<JerarqTipo>();
                    ResultSet rs = (ResultSet) cst.getObject(1);
                    if (rs != null) {
                        while (rs.next()) {
                            JerarqTipo obj = new JerarqTipo();
                            obj.setIdTipo(rs.getInt(1));
                            obj.setDescTipo(rs.getString(2));
                            obj.setEstado(rs.getString(3));
                            obj.setArraySubTipo(rs.getString(4));
                            obj.setArrayVariedad(rs.getString(5));
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
    public JerarqTipo viewTipo(final Integer Codigo) {
        return sessionFactory.getCurrentSession().doReturningWork(new ReturningWork<JerarqTipo>() {
            public JerarqTipo execute(Connection connection) throws SQLException {
                try {
                    CallableStatement cst = connection.prepareCall("{ ? = call "+paqueteConfiguracion+"SQL_ARTICULO(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
                    cst.registerOutParameter(1, Types.OTHER);
                    cst.setInt(2,0);
                    cst.setInt(3,0);
                    cst.setString(4,null);
                    cst.setString(5,null);
                    cst.setInt(6,0);
                    cst.setInt(7,0);
                    cst.setInt(8,0);
                    cst.setInt(9,0);
                    cst.setInt(10,0);
                    cst.setInt(11,Codigo);
                    cst.setInt(12,0);
                    cst.setInt(13,0);
                    cst.setInt(14,0);
                    cst.setInt(15,0);
                    cst.setInt(16,0);
                    cst.setInt(17,0);
                    cst.setInt(18,0);
                    cst.setInt(19,0);
                    cst.setString(20,"0");
                    cst.setString(21,"1");
                    cst.setString(22,"1");
                    cst.setInt(23,7);
                    cst.execute();
                    JerarqTipo tipo = null;
                    ResultSet rs = (ResultSet) cst.getObject(1);
                    if (rs != null) {
                        while (rs.next()) {
                            tipo = new JerarqTipo();
                            tipo.setIdTipo(rs.getInt(1));
                            tipo.setDescTipo(rs.getString(2));
                            tipo.setEstado(rs.getString(3));
                            tipo.setArraySubTipo(rs.getString(4));
                            tipo.setArrayVariedad(rs.getString(5));
                        }
                        rs.close();
                        cst.close();
                    }
                    return tipo;
                }catch ( Exception e) {
                    log.debug("Ocurrio una excepcion "+e.getMessage());
                    return null;
                }
            }
        });
    }

    @Transactional(readOnly = false)
    public RespuestaTransaccion mantenimientoTipo(final JerarqTipo tipo, final String usuario, final Integer operacion) {
        return  sessionFactory.getCurrentSession().doReturningWork(new ReturningWork<RespuestaTransaccion>() {
            public RespuestaTransaccion execute(Connection connection) throws SQLException {
                RespuestaTransaccion respTransaccion = new RespuestaTransaccion();
                try{
                    CallableStatement cst = connection.prepareCall("{ ? = call "+paqueteConfiguracion+"IUD_JERARQ_TIPO(?,?,?,?,?,?,?) }");
                    cst.registerOutParameter(1, Types.INTEGER);
                    cst.setInt(2, tipo.getIdTipo());
                    cst.setString(3, tipo.getDescTipo().toUpperCase());
                    cst.setString(4, tipo.getEstado());
                    cst.setObject(5, tipo.getArraySubTipo(),Types.OTHER);
                    cst.setObject(6, tipo.getArrayVariedad(),Types.OTHER);
                    cst.setString(7, usuario);
                    cst.setInt(8, operacion);
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
                        respTransaccion.setMensaje("Registro : ( "+ tipo.getDescTipo().toUpperCase() +" ) ya existe");
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

    @Transactional(readOnly = true)
    public List<JerarqSubTipo> ListarSubTipo(final String estado, final String paginaStart, final String paginaLength, final String orderby) {
        return sessionFactory.getCurrentSession().doReturningWork(new ReturningWork<List<JerarqSubTipo>>() {
            public List<JerarqSubTipo> execute(Connection connection) throws SQLException {
                try {
                    CallableStatement cst = connection.prepareCall("{ ? = call "+paqueteConfiguracion+"SQL_ARTICULO(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
                    cst.registerOutParameter(1, Types.OTHER);
                    cst.setInt(2,0);
                    cst.setInt(3,0);
                    cst.setString(4,estado);
                    cst.setString(5,null);
                    cst.setInt(6,0);
                    cst.setInt(7,0);
                    cst.setInt(8,0);
                    cst.setInt(9,0);
                    cst.setInt(10,0);
                    cst.setInt(11,0);
                    cst.setInt(12,0);
                    cst.setInt(13,0);
                    cst.setInt(14,0);
                    cst.setInt(15,0);
                    cst.setInt(16,0);
                    cst.setInt(17,0);
                    cst.setInt(18,0);
                    cst.setInt(19,0);
                    cst.setString(20,paginaStart);
                    cst.setString(21,paginaLength);
                    cst.setString(22,orderby);
                    cst.setInt(23,8);
                    cst.execute();
                    List<JerarqSubTipo> lista = new ArrayList<JerarqSubTipo>();
                    ResultSet rs = (ResultSet) cst.getObject(1);
                    if (rs != null) {
                        while (rs.next()) {
                            JerarqSubTipo obj = new JerarqSubTipo();
                            obj.setIdSubTipo(rs.getInt(1));
                            obj.setDescSubTipo(rs.getString(2));
                            obj.setEstado(rs.getString(3));
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
    public JerarqSubTipo viewSubTipo(final Integer Codigo) {
        return sessionFactory.getCurrentSession().doReturningWork(new ReturningWork<JerarqSubTipo>() {
            public JerarqSubTipo execute(Connection connection) throws SQLException {
                try {
                    CallableStatement cst = connection.prepareCall("{ ? = call "+paqueteConfiguracion+"SQL_ARTICULO(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
                    cst.registerOutParameter(1, Types.OTHER);
                    cst.setInt(2,0);
                    cst.setInt(3,0);
                    cst.setString(4,null);
                    cst.setString(5,null);
                    cst.setInt(6,0);
                    cst.setInt(7,0);
                    cst.setInt(8,0);
                    cst.setInt(9,0);
                    cst.setInt(10,0);
                    cst.setInt(11,0);
                    cst.setInt(12,Codigo);
                    cst.setInt(13,0);
                    cst.setInt(14,0);
                    cst.setInt(15,0);
                    cst.setInt(16,0);
                    cst.setInt(17,0);
                    cst.setInt(18,0);
                    cst.setInt(19,0);
                    cst.setString(20,"0");
                    cst.setString(21,"1");
                    cst.setString(22,"1");
                    cst.setInt(23,8);
                    cst.execute();
                    JerarqSubTipo subTipo = null;
                    ResultSet rs = (ResultSet) cst.getObject(1);
                    if (rs != null) {
                        while (rs.next()) {
                            subTipo = new JerarqSubTipo();
                            subTipo.setIdSubTipo(rs.getInt(1));
                            subTipo.setDescSubTipo(rs.getString(2));
                            subTipo.setEstado(rs.getString(3));
                        }
                        rs.close();
                        cst.close();
                    }
                    return subTipo;
                }catch ( Exception e) {
                    log.debug("Ocurrio una excepcion "+e.getMessage());
                    return null;
                }
            }
        });
    }

    @Transactional(readOnly = false)
    public RespuestaTransaccion mantenimientoSubTipo(final JerarqSubTipo subTipo, final String usuario, final Integer operacion) {
        return  sessionFactory.getCurrentSession().doReturningWork(new ReturningWork<RespuestaTransaccion>() {
            public RespuestaTransaccion execute(Connection connection) throws SQLException {
                RespuestaTransaccion respTransaccion = new RespuestaTransaccion();
                try{
                    CallableStatement cst = connection.prepareCall("{ ? = call "+paqueteConfiguracion+"IUD_JERARQ_SUBTIPO(?,?,?,?,?) }");
                    cst.registerOutParameter(1, Types.INTEGER);
                    cst.setInt(2, subTipo.getIdSubTipo());
                    cst.setString(3, subTipo.getDescSubTipo().toUpperCase());
                    cst.setString(4, subTipo.getEstado());
                    cst.setString(5, usuario);
                    cst.setInt(6, operacion);
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
                        respTransaccion.setMensaje("Registro : ( "+ subTipo.getDescSubTipo().toUpperCase() +" ) ya existe");
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

    @Transactional(readOnly = true)
    public List<JerarqVariedad> ListarVariedad(final String estado, final String paginaStart, final String paginaLength, final String orderby) {
        return sessionFactory.getCurrentSession().doReturningWork(new ReturningWork<List<JerarqVariedad>>() {
            public List<JerarqVariedad> execute(Connection connection) throws SQLException {
                try {
                    CallableStatement cst = connection.prepareCall("{ ? = call "+paqueteConfiguracion+"SQL_ARTICULO(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
                    cst.registerOutParameter(1, Types.OTHER);
                    cst.setInt(2,0);
                    cst.setInt(3,0);
                    cst.setString(4,estado);
                    cst.setString(5,null);
                    cst.setInt(6,0);
                    cst.setInt(7,0);
                    cst.setInt(8,0);
                    cst.setInt(9,0);
                    cst.setInt(10,0);
                    cst.setInt(11,0);
                    cst.setInt(12,0);
                    cst.setInt(13,0);
                    cst.setInt(14,0);
                    cst.setInt(15,0);
                    cst.setInt(16,0);
                    cst.setInt(17,0);
                    cst.setInt(18,0);
                    cst.setInt(19,0);
                    cst.setString(20,paginaStart);
                    cst.setString(21,paginaLength);
                    cst.setString(22,orderby);
                    cst.setInt(23,9);
                    cst.execute();
                    List<JerarqVariedad> lista = new ArrayList<JerarqVariedad>();
                    ResultSet rs = (ResultSet) cst.getObject(1);
                    if (rs != null) {
                        while (rs.next()) {
                            JerarqVariedad obj = new JerarqVariedad();
                            obj.setIdVariedad(rs.getInt(1));
                            obj.setDescVariedad(rs.getString(2));
                            obj.setEstado(rs.getString(3));
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
    public JerarqVariedad viewVariedad(final Integer Codigo) {
        return sessionFactory.getCurrentSession().doReturningWork(new ReturningWork<JerarqVariedad>() {
            public JerarqVariedad execute(Connection connection) throws SQLException {
                try {
                    CallableStatement cst = connection.prepareCall("{ ? = call "+paqueteConfiguracion+"SQL_ARTICULO(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
                    cst.registerOutParameter(1, Types.OTHER);
                    cst.setInt(2,0);
                    cst.setInt(3,0);
                    cst.setString(4,null);
                    cst.setString(5,null);
                    cst.setInt(6,0);
                    cst.setInt(7,0);
                    cst.setInt(8,0);
                    cst.setInt(9,0);
                    cst.setInt(10,0);
                    cst.setInt(11,0);
                    cst.setInt(12,0);
                    cst.setInt(13,Codigo);
                    cst.setInt(14,0);
                    cst.setInt(15,0);
                    cst.setInt(16,0);
                    cst.setInt(17,0);
                    cst.setInt(18,0);
                    cst.setInt(19,0);
                    cst.setString(20,"0");
                    cst.setString(21,"1");
                    cst.setString(22,"1");
                    cst.setInt(23,9);
                    cst.execute();
                    JerarqVariedad variedad = null;
                    ResultSet rs = (ResultSet) cst.getObject(1);
                    if (rs != null) {
                        while (rs.next()) {
                            variedad = new JerarqVariedad();
                            variedad.setIdVariedad(rs.getInt(1));
                            variedad.setDescVariedad(rs.getString(2));
                            variedad.setEstado(rs.getString(3));
                        }
                        rs.close();
                        cst.close();
                    }
                    return variedad;
                }catch ( Exception e) {
                    log.debug("Ocurrio una excepcion "+e.getMessage());
                    return null;
                }
            }
        });
    }

    @Transactional(readOnly = false)
    public RespuestaTransaccion mantenimientoVariedad(final JerarqVariedad variedad, final String usuario, final Integer operacion) {
        return  sessionFactory.getCurrentSession().doReturningWork(new ReturningWork<RespuestaTransaccion>() {
            public RespuestaTransaccion execute(Connection connection) throws SQLException {
                RespuestaTransaccion respTransaccion = new RespuestaTransaccion();
                try{
                    CallableStatement cst = connection.prepareCall("{ ? = call "+paqueteConfiguracion+"IUD_JERARQ_VARIEDAD(?,?,?,?,?) }");
                    cst.registerOutParameter(1, Types.INTEGER);
                    cst.setInt(2, variedad.getIdVariedad());
                    cst.setString(3, variedad.getDescVariedad().toUpperCase());
                    cst.setString(4, variedad.getEstado());
                    cst.setString(5, usuario);
                    cst.setInt(6, operacion);
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
                        respTransaccion.setMensaje("Registro : ( "+ variedad.getDescVariedad().toUpperCase() +" ) ya existe");
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

    @Transactional(readOnly = true)
    public List<JerarqPresentacion> ListarPresentacion(final String estado, final Integer envase, final Integer cantidad, final Integer unidad,
                                                       final String paginaStart,
                                                       final String paginaLength,
                                                       final String orderby) {
        return sessionFactory.getCurrentSession().doReturningWork(new ReturningWork<List<JerarqPresentacion>>() {
            public List<JerarqPresentacion> execute(Connection connection) throws SQLException {
                try {
                    CallableStatement cst = connection.prepareCall("{ ? = call "+paqueteConfiguracion+"SQL_ARTICULO(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
                    cst.registerOutParameter(1, Types.OTHER);
                    cst.setInt(2,0);
                    cst.setInt(3,0);
                    cst.setString(4,estado);
                    cst.setString(5,null);
                    cst.setInt(6,0);
                    cst.setInt(7,0);
                    cst.setInt(8,0);
                    cst.setInt(9,0);
                    cst.setInt(10,0);
                    cst.setInt(11,0);
                    cst.setInt(12,0);
                    cst.setInt(13,0);
                    cst.setInt(14,0);
                    cst.setInt(15,0);
                    cst.setInt(16,envase);
                    cst.setInt(17,cantidad);
                    cst.setInt(18,unidad);
                    cst.setInt(19,0);
                    cst.setString(20,paginaStart);
                    cst.setString(21,paginaLength);
                    cst.setString(22,orderby);
                    cst.setInt(23,10);
                    cst.execute();
                    List<JerarqPresentacion> lista = new ArrayList<JerarqPresentacion>();
                    ResultSet rs = (ResultSet) cst.getObject(1);
                    if (rs != null) {
                        while (rs.next()) {
                            JerarqPresentacion obj = new JerarqPresentacion();
                            obj.setIdPresentacion(rs.getInt(1));
                            obj.setCodPresentacion(rs.getString(2));
                            obj.setDescPresentacion(rs.getString(3));
                            obj.setEstado(rs.getString(4));
                            obj.setEnvase(new JerarqAtributo());
                            obj.getEnvase().setIdAtributo(rs.getInt(5));
                            obj.getEnvase().setValor(rs.getString(6));
                            obj.setCantidad(new JerarqAtributo());
                            obj.getCantidad().setIdAtributo(rs.getInt(7));
                            obj.getCantidad().setValor(rs.getString(8));
                            obj.setUnidad(new JerarqAtributo());
                            obj.getUnidad().setIdAtributo(rs.getInt(9));
                            obj.getUnidad().setValor(rs.getString(10));
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
    public JerarqPresentacion viewPresentacion(final Integer Codigo) {
        return sessionFactory.getCurrentSession().doReturningWork(new ReturningWork<JerarqPresentacion>() {
            public JerarqPresentacion execute(Connection connection) throws SQLException {
                try {
                    CallableStatement cst = connection.prepareCall("{ ? = call "+paqueteConfiguracion+"SQL_ARTICULO(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
                    cst.registerOutParameter(1, Types.OTHER);
                    cst.setInt(2,0);
                    cst.setInt(3,0);
                    cst.setString(4,null);
                    cst.setString(5,null);
                    cst.setInt(6,0);
                    cst.setInt(7,0);
                    cst.setInt(8,0);
                    cst.setInt(9,0);
                    cst.setInt(10,0);
                    cst.setInt(11,0);
                    cst.setInt(12,0);
                    cst.setInt(13,0);
                    cst.setInt(14,Codigo);
                    cst.setInt(15,0);
                    cst.setInt(16,0);
                    cst.setInt(17,0);
                    cst.setInt(18,0);
                    cst.setInt(19,0);
                    cst.setString(20,"0");
                    cst.setString(21,"1");
                    cst.setString(22,"1");
                    cst.setInt(23,10);
                    cst.execute();
                    JerarqPresentacion presentacion = null;
                    ResultSet rs = (ResultSet) cst.getObject(1);
                    if (rs != null) {
                        while (rs.next()) {
                            presentacion = new JerarqPresentacion();
                            presentacion.setIdPresentacion(rs.getInt(1));
                            presentacion.setCodPresentacion(rs.getString(2));
                            presentacion.setDescPresentacion(rs.getString(3));
                            presentacion.setEstado(rs.getString(4));
                            presentacion.setEnvase(new JerarqAtributo());
                            presentacion.getEnvase().setIdAtributo(rs.getInt(5));
                            presentacion.getEnvase().setValor(rs.getString(6));
                            presentacion.setCantidad(new JerarqAtributo());
                            presentacion.getCantidad().setIdAtributo(rs.getInt(7));
                            presentacion.getCantidad().setValor(rs.getString(8));
                            presentacion.setUnidad(new JerarqAtributo());
                            presentacion.getUnidad().setIdAtributo(rs.getInt(9));
                            presentacion.getUnidad().setValor(rs.getString(10));
                        }
                        rs.close();
                        cst.close();
                    }
                    return presentacion;
                }catch ( Exception e) {
                    log.debug("Ocurrio una excepcion "+e.getMessage());
                    return null;
                }
            }
        });
    }

    @Transactional(readOnly = false)
    public RespuestaTransaccion mantenimientoPresentacion(final JerarqPresentacion presentacion, final String usuario, final Integer operacion) {
        return  sessionFactory.getCurrentSession().doReturningWork(new ReturningWork<RespuestaTransaccion>() {
            public RespuestaTransaccion execute(Connection connection) throws SQLException {
                RespuestaTransaccion respTransaccion = new RespuestaTransaccion();
                try{
                    CallableStatement cst = connection.prepareCall("{ ? = call "+paqueteConfiguracion+"IUD_JERARQ_PRESENTACION(?,?,?,?,?,?,?) }");
                    cst.registerOutParameter(1, Types.INTEGER);
                    cst.setInt(2, presentacion.getIdPresentacion());
                    cst.setInt(3, presentacion.getEnvase().getIdAtributo());
                    cst.setInt(4, presentacion.getCantidad().getIdAtributo());
                    cst.setInt(5, presentacion.getUnidad().getIdAtributo());
                    cst.setString(6, presentacion.getEstado());
                    cst.setString(7, usuario);
                    cst.setInt(8, operacion);
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
                        respTransaccion.setMensaje("Registro ya existe");
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

    @Transactional(readOnly = true)
    public List<JerarqAtributo> ListarAtributo(final String estado, final String descripcion,
                                                       final String paginaStart,
                                                       final String paginaLength,
                                                       final String orderby) {
        return sessionFactory.getCurrentSession().doReturningWork(new ReturningWork<List<JerarqAtributo>>() {
            public List<JerarqAtributo> execute(Connection connection) throws SQLException {
                try {
                    CallableStatement cst = connection.prepareCall("{ ? = call "+paqueteConfiguracion+"SQL_ARTICULO(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
                    cst.registerOutParameter(1, Types.OTHER);
                    cst.setInt(2,0);
                    cst.setInt(3,0);
                    cst.setString(4,estado);
                    cst.setString(5,descripcion);
                    cst.setInt(6,0);
                    cst.setInt(7,0);
                    cst.setInt(8,0);
                    cst.setInt(9,0);
                    cst.setInt(10,0);
                    cst.setInt(11,0);
                    cst.setInt(12,0);
                    cst.setInt(13,0);
                    cst.setInt(14,0);
                    cst.setInt(15,0);
                    cst.setInt(16,0);
                    cst.setInt(17,0);
                    cst.setInt(18,0);
                    cst.setInt(19,0);
                    cst.setString(20,paginaStart);
                    cst.setString(21,paginaLength);
                    cst.setString(22,orderby);
                    cst.setInt(23,11);
                    cst.execute();
                    List<JerarqAtributo> lista = new ArrayList<JerarqAtributo>();
                    ResultSet rs = (ResultSet) cst.getObject(1);
                    if (rs != null) {
                        while (rs.next()) {
                            JerarqAtributo obj = new JerarqAtributo();
                            obj.setIdAtributo(rs.getInt(1));
                            obj.setTipo(rs.getString(2));
                            obj.setValor(rs.getString(3));
                            obj.setAbrev(rs.getString(4));
                            obj.setEstado(rs.getString(5));
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
    public JerarqAtributo viewAtributo(final Integer Codigo) {
        return sessionFactory.getCurrentSession().doReturningWork(new ReturningWork<JerarqAtributo>() {
            public JerarqAtributo execute(Connection connection) throws SQLException {
                try {
                    CallableStatement cst = connection.prepareCall("{ ? = call "+paqueteConfiguracion+"SQL_ARTICULO(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
                    cst.registerOutParameter(1, Types.OTHER);
                    cst.setInt(2,0);
                    cst.setInt(3,0);
                    cst.setString(4,null);
                    cst.setString(5,null);
                    cst.setInt(6,0);
                    cst.setInt(7,0);
                    cst.setInt(8,0);
                    cst.setInt(9,0);
                    cst.setInt(10,0);
                    cst.setInt(11,0);
                    cst.setInt(12,0);
                    cst.setInt(13,0);
                    cst.setInt(14,0);
                    cst.setInt(15,Codigo);
                    cst.setInt(16,0);
                    cst.setInt(17,0);
                    cst.setInt(18,0);
                    cst.setInt(19,0);
                    cst.setString(20,"0");
                    cst.setString(21,"1");
                    cst.setString(22,"1");
                    cst.setInt(23,11);
                    cst.execute();
                    JerarqAtributo atributo = null;
                    ResultSet rs = (ResultSet) cst.getObject(1);
                    if (rs != null) {
                        while (rs.next()) {
                            atributo = new JerarqAtributo();
                            atributo.setIdAtributo(rs.getInt(1));
                            atributo.setTipo(rs.getString(2));
                            atributo.setValor(rs.getString(3));
                            atributo.setAbrev(rs.getString(4));
                            atributo.setEstado(rs.getString(5));
                        }
                        rs.close();
                        cst.close();
                    }
                    return atributo;
                }catch ( Exception e) {
                    log.debug("Ocurrio una excepcion "+e.getMessage());
                    return null;
                }
            }
        });
    }

    @Transactional(readOnly = false)
    public RespuestaTransaccion mantenimientoAtributo(final JerarqAtributo atributo, final String usuario, final Integer operacion) {
        return  sessionFactory.getCurrentSession().doReturningWork(new ReturningWork<RespuestaTransaccion>() {
            public RespuestaTransaccion execute(Connection connection) throws SQLException {
                RespuestaTransaccion respTransaccion = new RespuestaTransaccion();
                try{
                    CallableStatement cst = connection.prepareCall("{ ? = call "+paqueteConfiguracion+"IUD_JERARQ_ATRIBUTO(?,?,?,?,?,?,?) }");
                    cst.registerOutParameter(1, Types.INTEGER);
                    cst.setInt(2, atributo.getIdAtributo());
                    cst.setString(3, atributo.getTipo().toUpperCase());
                    cst.setString(4, atributo.getValor().toUpperCase());
                    cst.setString(5, atributo.getAbrev().toUpperCase());
                    cst.setString(6, atributo.getEstado());
                    cst.setString(7, usuario);
                    cst.setInt(8, operacion);
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
                        respTransaccion.setMensaje("Registro : ( "+ atributo.getValor().toUpperCase() +" ) ya existe");
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
