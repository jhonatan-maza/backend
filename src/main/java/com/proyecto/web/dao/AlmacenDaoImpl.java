package com.proyecto.web.dao;

import com.proyecto.web.model.Almacen;
import com.proyecto.web.model.Empresa;
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
 * Created by jonat on 13/04/2020.
 */
@Repository
public class AlmacenDaoImpl implements AlmacenDao {

    private static final Logger log = LoggerFactory.getLogger(LocalidadDaoImpl.class);
    @Autowired
    private SessionFactory sessionFactory;

    @Value("${paqueteConfiguracion}")
    protected String paqueteConfiguracion;

    @Transactional(readOnly = true)
    public List<Almacen> ListarAlmacen(final String Descripcion, final String Estado, final String PaginaStart, final String PaginaLength, final String Orderby) {
        return sessionFactory.getCurrentSession().doReturningWork(new ReturningWork<List<Almacen>>() {
            public List<Almacen> execute(Connection connection) throws SQLException {
                try {
                    CallableStatement cst = connection.prepareCall("{ ? = call "+paqueteConfiguracion+"SQL_ALMACEN(?,?,?,?,?,?)}");
                    cst.registerOutParameter(1, Types.OTHER);
                    cst.setInt(2,0);
                    cst.setString(3,Descripcion);
                    cst.setString(4,Estado);
                    cst.setString(5,PaginaStart);
                    cst.setString(6,PaginaLength);
                    cst.setString(7,Orderby);
                    cst.execute();
                    List<Almacen> lista = new ArrayList<Almacen>();
                    ResultSet rs = (ResultSet) cst.getObject(1);
                    if (rs != null) {
                        while (rs.next()) {
                            Almacen obj = new Almacen();
                            obj.setIdAlmacen(rs.getInt(1));
                            obj.setDescalmacen(rs.getString(2));
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
    public Almacen viewAlmacen(final Integer Codigo) {
        return sessionFactory.getCurrentSession().doReturningWork(new ReturningWork<Almacen>() {
            public Almacen execute(Connection connection) throws SQLException {
                try {
                    CallableStatement cst = connection.prepareCall("{ ? = call "+paqueteConfiguracion+"SQL_ALMACEN(?,?,?,?,?,?)}");
                    cst.registerOutParameter(1, Types.OTHER);
                    cst.setInt(2,Codigo);
                    cst.setString(3,null);
                    cst.setString(4,null);
                    cst.setString(5,"0");
                    cst.setString(6,"1");
                    cst.setString(7,"1");
                    cst.execute();
                    Almacen almacen = null;
                    ResultSet rs = (ResultSet) cst.getObject(1);
                    if (rs != null) {
                        while (rs.next()) {
                            almacen = new Almacen();
                            almacen.setIdAlmacen(rs.getInt(1));
                            almacen.setDescalmacen(rs.getString(2));
                            almacen.setDireccion(rs.getString(3));
                            almacen.setEstado(rs.getString(4));
                            almacen.setEmpresa(new Empresa());
                            almacen.getEmpresa().setIdEmpresa(rs.getInt(5));
                            almacen.getEmpresa().setDescEmpresa(rs.getString(6));
                            almacen.setSede(new Sede());
                            almacen.getSede().setIdSede(rs.getInt(7));
                            almacen.getSede().setDescSede(rs.getString(8));
                        }
                        rs.close();
                        cst.close();
                    }
                    return almacen;
                }catch ( Exception e) {
                    log.debug("Ocurrio una excepcion "+e.getMessage());
                    return null;
                }
            }
        });
    }

    @Transactional(readOnly = false)
    public RespuestaTransaccion mantenimientoAlmacen(final Almacen almacen, final String usuario, final Integer operacion) {
        return  sessionFactory.getCurrentSession().doReturningWork(new ReturningWork<RespuestaTransaccion>() {
            public RespuestaTransaccion execute(Connection connection) throws SQLException {
                RespuestaTransaccion respTransaccion = new RespuestaTransaccion();
                try{
                    CallableStatement cst = connection.prepareCall("{ ? = call "+paqueteConfiguracion+"IUD_ALMACEN(?,?,?,?,?,?,?,?) }");
                    cst.registerOutParameter(1, Types.INTEGER);
                    cst.setInt(2, almacen.getIdAlmacen());
                    cst.setInt(3, almacen.getEmpresa().getIdEmpresa());
                    cst.setInt(4, almacen.getSede().getIdSede());
                    cst.setString(5, almacen.getDescalmacen().toUpperCase());
                    cst.setString(6, almacen.getDireccion().toUpperCase());
                    cst.setString(7, almacen.getEstado());
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
                        respTransaccion.setMensaje("Registro : ( "+ almacen.getDescalmacen().toUpperCase() +" ) ya existe");
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
