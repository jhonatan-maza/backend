package com.proyecto.web.dao;

import com.proyecto.web.model.*;
import org.hibernate.SessionFactory;
import org.hibernate.jdbc.ReturningWork;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jonat on 14/03/2020.
 */
@Repository
public class ArticuloDaoImpl implements ArticuloDao {

    private static final Logger log = LoggerFactory.getLogger(ArticuloDaoImpl.class);
    @Autowired
    private SessionFactory sessionFactory;

    @Transactional(readOnly = true)
    public List<Articulo> ListarArticulo(final Integer tipoArticulo, final String estado, final Integer familia, final Integer clase,
                                         final Integer subClase, final Integer categoria, final Integer marca, final Integer tipo,
                                         final Integer subTipo, final Integer variedad, final Integer presentacion,
                                         final Integer proveedor, final String paginaStart, final String paginaLength, final String orderby) {
        return sessionFactory.getCurrentSession().doReturningWork(new ReturningWork<List<Articulo>>() {
            public List<Articulo> execute(Connection connection) throws SQLException {
                try {
                    CallableStatement cst = connection.prepareCall("{ ? = call SQL_ARTICULO(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
                    cst.registerOutParameter(1, Types.OTHER);
                    cst.setInt(2,0);
                    cst.setInt(3,tipoArticulo);
                    cst.setString(4,estado);
                    cst.setInt(5,familia);
                    cst.setInt(6,clase);
                    cst.setInt(7,subClase);
                    cst.setInt(8,categoria);
                    cst.setInt(9,marca);
                    cst.setInt(10,tipo);
                    cst.setInt(11,subTipo);
                    cst.setInt(12,variedad);
                    cst.setInt(13,presentacion);
                    cst.setInt(14,proveedor);
                    cst.setInt(15,0); //localidad
                    cst.setInt(16,0); //lista de precio
                    cst.setString(17,paginaStart);
                    cst.setString(18,paginaLength);
                    cst.setString(19,orderby);
                    cst.setInt(20,1);
                    cst.execute();
                    List<Articulo> lista = new ArrayList<Articulo>();
                    ResultSet rs = (ResultSet) cst.getObject(1);
                    if (rs != null) {
                        while (rs.next()) {
                            Articulo obj = new Articulo();
                            obj.setIdArticulo(rs.getInt(1));
                            obj.setDescArticulo(rs.getString(2));
                            obj.setProveedor(new Proveedor());
                            obj.getProveedor().setNombreComercial(rs.getString(3));
                            obj.setFamilia(new JerarqFamilia());
                            obj.getFamilia().setDescFamilia(rs.getString(4));
                            obj.setClase(new JerarqClase());
                            obj.getClase().setDescClase(rs.getString(5));
                            obj.setSubClase(new JerarqSubClase());
                            obj.getSubClase().setDescSubClase(rs.getString(6));
                            obj.setCategoria(new JerarqCategoria());
                            obj.getCategoria().setDescCategoria(rs.getString(7));
                            obj.setTipo(new JerarqTipo());
                            obj.getTipo().setDescTipo(rs.getString(8));
                            obj.setSubTipo(new JerarqSubTipo());
                            obj.getSubTipo().setDescSubTipo(rs.getString(9));
                            obj.setVariedad(new JerarqVariedad());
                            obj.getVariedad().setDescVariedad(rs.getString(10));
                            obj.setMarca(new JerarqMarca());
                            obj.getMarca().setDescMarca(rs.getString(11));
                            obj.setPresentacion(new JerarqPresentacion());
                            obj.getPresentacion().setDescPresentacion(rs.getString(12));
                            obj.setEstado(rs.getString(13));
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
    public Integer CantidadArticulo(final Integer tipoArticulo,final String estado,
                                    final Integer familia,final Integer clase,final Integer subClase,
                                    final Integer categoria,final Integer marca,final Integer tipo,final Integer subTipo,
                                    final Integer variedad, final Integer presentacion,final Integer proveedor) {
        return  sessionFactory.getCurrentSession().doReturningWork(new ReturningWork<Integer>() {
            public Integer execute(Connection connection) throws SQLException {
                try{
                    CallableStatement cst = connection.prepareCall(" { ? = call SQL_ARTICULO(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
                    cst.registerOutParameter(1, Types.OTHER);
                    cst.setInt(2,0);
                    cst.setInt(3,tipoArticulo);
                    cst.setString(4,estado);
                    cst.setInt(5,familia);
                    cst.setInt(6,clase);
                    cst.setInt(7,subClase);
                    cst.setInt(8,categoria);
                    cst.setInt(9,marca);
                    cst.setInt(10,tipo);
                    cst.setInt(11,subTipo);
                    cst.setInt(12,variedad);
                    cst.setInt(13,presentacion);
                    cst.setInt(14,proveedor);
                    cst.setInt(15,0); //localidad
                    cst.setInt(16,0); //lista de precio
                    cst.setString(17,null);
                    cst.setString(18,null);
                    cst.setString(19,null);
                    cst.setInt(20,1);
                    cst.execute();
                    Integer cantidad = 0;
                    ResultSet rs = (ResultSet) cst.getObject(1);
                    if (rs != null) {
                        while (rs.next()) {
                            cantidad = rs.getInt(1);
                        }
                        rs.close();
                        cst.close();
                    }
                    return cantidad;
                }catch ( Exception e) {
                    log.debug("Ocurrio una excepcion "+e.getMessage());
                    return null;
                }
            }
        });
    }

}
