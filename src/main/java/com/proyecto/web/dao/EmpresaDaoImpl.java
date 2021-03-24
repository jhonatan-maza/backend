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
public class EmpresaDaoImpl implements EmpresaDao {

    private static final Logger log = LoggerFactory.getLogger(EmpresaDaoImpl.class);
    @Autowired
    private SessionFactory sessionFactory;

    @Value("${paqueteConfiguracion}")
    protected String paqueteConfiguracion;

    @Transactional(readOnly = true)
    public List<Empresa> ListarComboEmpresa(final Integer operacion) {
        return sessionFactory.getCurrentSession().doReturningWork(new ReturningWork<List<Empresa>>() {
            public List<Empresa> execute(Connection connection) throws SQLException {
                try {
                    CallableStatement cst = connection.prepareCall("{ ? = call SQL_EMPRESA(?)}");
                    cst.registerOutParameter(1, Types.OTHER);
                    cst.setInt(2,operacion);
                    cst.execute();
                    List<Empresa> lista = new ArrayList<Empresa>();
                    ResultSet rs = (ResultSet) cst.getObject(1);
                    if (rs != null) {
                        while (rs.next()) {
                            Empresa obj = new Empresa();
                            obj.setIdEmpresa(rs.getInt(1));
                            obj.setDescEmpresa(rs.getString(2));
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
    public List<Empresa> ListarEmpresa(final String Descripcion, final String Estado, final String PaginaStart, final String PaginaLength, final String Orderby) {
        return sessionFactory.getCurrentSession().doReturningWork(new ReturningWork<List<Empresa>>() {
            public List<Empresa> execute(Connection connection) throws SQLException {
                try {
                    CallableStatement cst = connection.prepareCall("{ ? = call "+paqueteConfiguracion+"SQL_EMPRESA(?,?,?,?,?,?)}");
                    cst.registerOutParameter(1, Types.OTHER);
                    cst.setInt(2,0);
                    cst.setString(3,Descripcion);
                    cst.setString(4,Estado);
                    cst.setString(5,PaginaStart);
                    cst.setString(6,PaginaLength);
                    cst.setString(7,Orderby);
                    cst.execute();
                    List<Empresa> lista = new ArrayList<Empresa>();
                    ResultSet rs = (ResultSet) cst.getObject(1);
                    if (rs != null) {
                        while (rs.next()) {
                            Empresa obj = new Empresa();
                            obj.setIdEmpresa(rs.getInt(1));
                            obj.setDescEmpresa(rs.getString(2));
                            obj.setRuc(rs.getString(3));
                            obj.setDireccion(rs.getString(4));
                            obj.setEstado(rs.getString(5));
                            obj.setCodPais(new Tablas());
                            obj.getCodPais().setIdtabla(rs.getInt(6));
                            obj.getCodPais().setDescripcion2(rs.getString(7));
                            obj.setUbigeo(new Distrito());
                            obj.getUbigeo().setIdDistrito(rs.getInt(8));
                            obj.getUbigeo().setDescDistrito(rs.getString(9));
                            obj.getUbigeo().setProvincia(new Provincia());
                            obj.getUbigeo().getProvincia().setIdProvincia(rs.getInt(10));
                            obj.getUbigeo().getProvincia().setDescProvincia(rs.getString(11));
                            obj.getUbigeo().getProvincia().setDepartamento(new Departamento());
                            obj.getUbigeo().getProvincia().getDepartamento().setIdDepartamento(rs.getInt(12));
                            obj.getUbigeo().getProvincia().getDepartamento().setDescDepartamento(rs.getString(13));
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
    public Empresa viewEmpresa(final Integer Codigo) {
        return sessionFactory.getCurrentSession().doReturningWork(new ReturningWork<Empresa>() {
            public Empresa execute(Connection connection) throws SQLException {
                try {
                    CallableStatement cst = connection.prepareCall("{ ? = call "+paqueteConfiguracion+"SQL_EMPRESA(?,?,?,?,?,?)}");
                    cst.registerOutParameter(1, Types.OTHER);
                    cst.setInt(2,Codigo);
                    cst.setString(3,null);
                    cst.setString(4,null);
                    cst.setString(5,"0");
                    cst.setString(6,"1");
                    cst.setString(7,"1");
                    cst.execute();
                    Empresa empresa = null;
                    ResultSet rs = (ResultSet) cst.getObject(1);
                    if (rs != null) {
                        while (rs.next()) {
                            empresa = new Empresa();
                            empresa.setIdEmpresa(rs.getInt(1));
                            empresa.setDescEmpresa(rs.getString(2));
                            empresa.setRuc(rs.getString(3));
                            empresa.setDireccion(rs.getString(4));
                            empresa.setEstado(rs.getString(5));
                            empresa.setCodPais(new Tablas());
                            empresa.getCodPais().setIdtabla(rs.getInt(6));
                            empresa.getCodPais().setDescripcion2(rs.getString(7));
                            empresa.setUbigeo(new Distrito());
                            empresa.getUbigeo().setIdDistrito(rs.getInt(8));
                            empresa.getUbigeo().setDescDistrito(rs.getString(9));
                            empresa.getUbigeo().setProvincia(new Provincia());
                            empresa.getUbigeo().getProvincia().setIdProvincia(rs.getInt(10));
                            empresa.getUbigeo().getProvincia().setDescProvincia(rs.getString(11));
                            empresa.getUbigeo().getProvincia().setDepartamento(new Departamento());
                            empresa.getUbigeo().getProvincia().getDepartamento().setIdDepartamento(rs.getInt(12));
                            empresa.getUbigeo().getProvincia().getDepartamento().setDescDepartamento(rs.getString(13));
                        }
                        rs.close();
                        cst.close();
                    }
                    return empresa;
                }catch ( Exception e) {
                    log.debug("Ocurrio una excepcion "+e.getMessage());
                    return null;
                }
            }
        });
    }

    @Transactional(readOnly = false)
    public RespuestaTransaccion mantenimientoEmpresa(final Empresa empresa, final String usuario, final Integer operacion) {
        return  sessionFactory.getCurrentSession().doReturningWork(new ReturningWork<RespuestaTransaccion>() {
            public RespuestaTransaccion execute(Connection connection) throws SQLException {
                RespuestaTransaccion respTransaccion = new RespuestaTransaccion();
                try{
                    CallableStatement cst = connection.prepareCall("{ ? = call "+paqueteConfiguracion+"IUD_EMPRESA(?,?,?,?,?,?,?,?,?) }");
                    cst.registerOutParameter(1, Types.INTEGER);
                    cst.setInt(2, empresa.getIdEmpresa());
                    cst.setInt(3, empresa.getUbigeo().getIdDistrito());
                    cst.setInt(4, empresa.getCodPais().getIdtabla());
                    cst.setString(5, empresa.getDescEmpresa().toUpperCase());
                    cst.setString(6, empresa.getRuc());
                    cst.setString(7, empresa.getDireccion().toUpperCase());
                    cst.setString(8, empresa.getEstado());
                    cst.setString(9, usuario);
                    cst.setInt(10, operacion);
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
                        respTransaccion.setMensaje("Registro : ( "+ empresa.getDescEmpresa().toUpperCase() +" ) ya existe");
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
