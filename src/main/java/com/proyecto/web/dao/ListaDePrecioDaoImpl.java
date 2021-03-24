package com.proyecto.web.dao;

import com.proyecto.web.model.Localidad;
import com.proyecto.web.model.PrecioLista;
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
 * Created by jonat on 17/03/2020.
 */
@Repository
public class ListaDePrecioDaoImpl implements ListaDePrecioDao {

    private static final Logger log = LoggerFactory.getLogger(ListaDePrecioDaoImpl.class);
    @Autowired
    private SessionFactory sessionFactory;

    @Value("${paqueteConfiguracion}")
    protected String paqueteConfiguracion;

    @Transactional(readOnly = true)
    public List<PrecioLista> ListaDePrecioXLocalidad(final Integer Localidad) {
        return sessionFactory.getCurrentSession().doReturningWork(new ReturningWork<List<PrecioLista>>() {
            public List<PrecioLista> execute(Connection connection) throws SQLException {
                try {
                    CallableStatement cst = connection.prepareCall("{ ? = call SQL_LISTA_PRECIO(?,?)}");
                    cst.registerOutParameter(1, Types.OTHER);
                    cst.setInt(2,Localidad);
                    cst.setInt(3,1);
                    cst.execute();
                    List<PrecioLista> lista = new ArrayList<PrecioLista>();
                    ResultSet rs = (ResultSet) cst.getObject(1);
                    if (rs != null) {
                        while (rs.next()) {
                            PrecioLista obj = new PrecioLista();
                            obj.setIdListaDePrecio(rs.getInt(1));
                            obj.setDescripcion(rs.getString(2));
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
    public List<PrecioLista> ListarListaPrecio(final Integer localidad, final String Descripcion, final String Estado, final String PaginaStart, final String PaginaLength, final String Orderby) {
        return sessionFactory.getCurrentSession().doReturningWork(new ReturningWork<List<PrecioLista>>() {
            public List<PrecioLista> execute(Connection connection) throws SQLException {
                try {
                    CallableStatement cst = connection.prepareCall("{ ? = call "+paqueteConfiguracion+"SQL_PRECIOS_LISTA(?,?,?,?,?,?,?)}");
                    cst.registerOutParameter(1, Types.OTHER);
                    cst.setInt(2,0);
                    cst.setInt(3,localidad);
                    cst.setString(4,Descripcion);
                    cst.setString(5,Estado);
                    cst.setString(6,PaginaStart);
                    cst.setString(7,PaginaLength);
                    cst.setString(8,Orderby);
                    cst.execute();
                    List<PrecioLista> lista = new ArrayList<PrecioLista>();
                    ResultSet rs = (ResultSet) cst.getObject(1);
                    if (rs != null) {
                        while (rs.next()) {
                            PrecioLista obj = new PrecioLista();
                            obj.setIdListaDePrecio(rs.getInt(1));
                            obj.setDescripcion(rs.getString(2));
                            obj.setEstado(rs.getString(3));
                            obj.setLocalidad(new Localidad());
                            obj.getLocalidad().setIdLocalidad(rs.getInt(4));
                            obj.getLocalidad().setDesclocalidad(rs.getString(5));
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
    public PrecioLista viewListaPrecio(final Integer Codigo) {
        return sessionFactory.getCurrentSession().doReturningWork(new ReturningWork<PrecioLista>() {
            public PrecioLista execute(Connection connection) throws SQLException {
                try {
                    CallableStatement cst = connection.prepareCall("{ ? = call "+paqueteConfiguracion+"SQL_PRECIOS_LISTA(?,?,?,?,?,?,?)}");
                    cst.registerOutParameter(1, Types.OTHER);
                    cst.setInt(2,Codigo);
                    cst.setInt(3,0);
                    cst.setString(4,null);
                    cst.setString(5,null);
                    cst.setString(6,"0");
                    cst.setString(7,"1");
                    cst.setString(8,"1");
                    cst.execute();
                    PrecioLista plista = null;
                    ResultSet rs = (ResultSet) cst.getObject(1);
                    if (rs != null) {
                        while (rs.next()) {
                            plista = new PrecioLista();
                            plista.setIdListaDePrecio(rs.getInt(1));
                            plista.setDescripcion(rs.getString(2));
                            plista.setEstado(rs.getString(3));
                            plista.setLocalidad(new Localidad());
                            plista.getLocalidad().setIdLocalidad(rs.getInt(4));
                        }
                        rs.close();
                        cst.close();
                    }
                    return plista;
                }catch ( Exception e) {
                    log.debug("Ocurrio una excepcion "+e.getMessage());
                    return null;
                }
            }
        });
    }

    @Transactional(readOnly = false)
    public RespuestaTransaccion mantenimientoListaPrecio(final PrecioLista lprecio, final String usuario, final Integer operacion) {
        return  sessionFactory.getCurrentSession().doReturningWork(new ReturningWork<RespuestaTransaccion>() {
            public RespuestaTransaccion execute(Connection connection) throws SQLException {
                RespuestaTransaccion respTransaccion = new RespuestaTransaccion();
                try{
                    CallableStatement cst = connection.prepareCall("{ ? = call "+paqueteConfiguracion+"IUD_PRECIOS_LISTA(?,?,?,?,?,?) }");
                    cst.registerOutParameter(1, Types.INTEGER);
                    cst.setInt(2, lprecio.getIdListaDePrecio());
                    cst.setInt(3, lprecio.getLocalidad().getIdLocalidad());
                    cst.setString(4, lprecio.getDescripcion().toUpperCase());
                    cst.setString(5, lprecio.getEstado());
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
                        respTransaccion.setMensaje("Registro : ( "+ lprecio.getDescripcion().toUpperCase() +" ) ya existe");
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
