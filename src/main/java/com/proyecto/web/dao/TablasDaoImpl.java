package com.proyecto.web.dao;

import com.proyecto.web.model.Tablas;
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
 * Created by jonat on 16/03/2020.
 */
@Repository
public class TablasDaoImpl implements TablasDao {

    private static final Logger log = LoggerFactory.getLogger(TablasDaoImpl.class);
    @Autowired
    private SessionFactory sessionFactory;

    @Value("${paqueteConfiguracion}")
    protected String paqueteConfiguracion;

    @Transactional(readOnly = true)
    public List<Tablas> ListarTablas(final String categoria, final String Descripcion, final String Estado,
                                        final String PaginaStart, final String PaginaLength, final String Orderby) {
        return sessionFactory.getCurrentSession().doReturningWork(new ReturningWork<List<Tablas>>() {
            public List<Tablas> execute(Connection connection) throws SQLException {
                try {
                    CallableStatement cst = connection.prepareCall("{ ? = call "+paqueteConfiguracion+"SQL_TABLAS(?,?,?,?,?,?,?)}");
                    cst.registerOutParameter(1, Types.OTHER);
                    cst.setInt(2,0);
                    cst.setString(3,categoria);
                    cst.setString(4,Descripcion);
                    cst.setString(5,Estado);
                    cst.setString(6,PaginaStart);
                    cst.setString(7,PaginaLength);
                    cst.setString(8,Orderby);
                    cst.execute();
                    List<Tablas> lista = new ArrayList<Tablas>();
                    ResultSet rs = (ResultSet) cst.getObject(1);
                    if (rs != null) {
                        while (rs.next()) {
                            Tablas obj = new Tablas();
                            obj.setIdtabla(rs.getInt(1));
                            obj.setCategoria(rs.getString(2));
                            obj.setDesccategoria(rs.getString(3));
                            obj.setEstado(rs.getString(4));
                            obj.setLlave(rs.getInt(5));
                            obj.setDescripcion1(rs.getString(6));
                            obj.setDescripcion2(rs.getString(7));
                            obj.setDescripcion3(rs.getString(8));
                            obj.setDescripcion4(rs.getString(9));
                            obj.setDescripcion5(rs.getString(10));
                            obj.setNumero1(rs.getInt(11));
                            obj.setNumero2(rs.getInt(12));
                            obj.setNumero3(rs.getInt(13));
                            obj.setNumero4(rs.getInt(14));
                            obj.setNumero5(rs.getInt(15));
                            obj.setFecha1(rs.getString(16));
                            obj.setFecha2(rs.getString(17));
                            obj.setFecha3(rs.getString(18));
                            obj.setFecha4(rs.getString(19));
                            obj.setFecha5(rs.getString(20));
                            obj.setCatrelacion(rs.getString(21));
                            obj.setObservaciones(rs.getString(22));
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
