package com.proyecto.web.dao;

import com.proyecto.web.model.*;
import com.sun.webkit.network.URLs;
import jdk.nashorn.api.scripting.URLReader;
import org.apache.commons.httpclient.HttpURL;
import org.apache.commons.httpclient.URI;
import org.hibernate.SessionFactory;
import org.hibernate.jdbc.ReturningWork;
import org.hibernate.type.UrlType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import sun.awt.image.URLImageSource;
import sun.misc.URLClassPath;

import javax.mail.URLName;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jonat on 16/03/2020.
 */
@Repository
public class VentaDaoImpl implements VentaDao {

    private static final Logger log = LoggerFactory.getLogger(VentaDaoImpl.class);
    @Autowired
    private SessionFactory sessionFactory;

    @Transactional(readOnly = true)
    public List<Cliente> ViewCliente(final Integer empresa,final String tipoDocumento,final String documentoIdeCli,final Integer operacion) {
        return sessionFactory.getCurrentSession().doReturningWork(new ReturningWork<List<Cliente>>() {
            public List<Cliente> execute(Connection connection) throws SQLException {
                try {
                    CallableStatement cst = connection.prepareCall("{ ? = call SQL_VENTA(?,?,?,?,?,?,?)}");
                    cst.registerOutParameter(1, Types.OTHER);
                    cst.setInt(2,empresa);
                    cst.setInt(3,0);
                    cst.setString(4,tipoDocumento);
                    cst.setString(5,documentoIdeCli);
                    cst.setInt(6,0);
                    cst.setInt(7,0);
                    cst.setInt(8,operacion);
                    cst.execute();
                    List<Cliente> lista = new ArrayList<Cliente>();
                    ResultSet rs = (ResultSet) cst.getObject(1);
                    if (rs != null) {
                        while (rs.next()) {
                            Cliente obj = new Cliente();
                            obj.setIdCliente(rs.getInt(1));
                            obj.setDocumentoIde(rs.getString(2));
                            obj.setNombreComercial(rs.getString(3));
                            obj.setDireccion(rs.getString(4));
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
    public List<DataVentaArticulo> ViewArticulo(final Integer articulo,final Integer localidad,final Integer lprecio,final Integer operacion) {
        return sessionFactory.getCurrentSession().doReturningWork(new ReturningWork<List<DataVentaArticulo>>() {
            public List<DataVentaArticulo> execute(Connection connection) throws SQLException {
                try {
                    CallableStatement cst = connection.prepareCall("{ ? = call SQL_VENTA(?,?,?,?,?,?,?)}");
                    cst.registerOutParameter(1, Types.OTHER);
                    cst.setInt(2,0);
                    cst.setInt(3,localidad);
                    cst.setString(4,null);
                    cst.setString(5,null);
                    cst.setInt(6,lprecio);
                    cst.setInt(7,articulo);
                    cst.setInt(8,operacion);
                    cst.execute();
                    List<DataVentaArticulo> lista = new ArrayList<DataVentaArticulo>();
                    ResultSet rs = (ResultSet) cst.getObject(1);
                    if (rs != null) {
                        while (rs.next()) {
                            DataVentaArticulo obj = new DataVentaArticulo();
                            obj.setArticulo(new Articulo());
                            obj.getArticulo().setIdArticulo(rs.getInt(1));
                            obj.getArticulo().setDescArticulo(rs.getString(2));
                            obj.getArticulo().setUmVenta(new UnidadMedida());
                            obj.getArticulo().getUmVenta().setDescUm(rs.getString(3));
                            obj.setPrecio(new Precio());
                            obj.getPrecio().setPrecioBaseVenta(rs.getBigDecimal(4));
                            obj.setSaldosLocalidad(new SaldosLocalidad());
                            obj.getSaldosLocalidad().setCantidad(rs.getInt(5));
                            obj.setUrlImagen("http://192.168.0.103:8081/app/articulo/imagen/" + rs.getInt(1) + ".jpg");
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

}
