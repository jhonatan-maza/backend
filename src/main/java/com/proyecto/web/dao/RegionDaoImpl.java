package com.proyecto.web.dao;

import com.proyecto.web.model.Region;
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
 * Created by jonat on 11/04/2020.
 */
@Repository
public class RegionDaoImpl implements RegionDao {

    private static final Logger log = LoggerFactory.getLogger(RegionDaoImpl.class);
    @Autowired
    private SessionFactory sessionFactory;

    @Value("${paqueteConfiguracion}")
    protected String paqueteConfiguracion;

    @Transactional(readOnly = true)
    public List<Region> ListarRegion(final String Descripcion, final String Estado, final String PaginaStart, final String PaginaLength, final String Orderby) {
        return sessionFactory.getCurrentSession().doReturningWork(new ReturningWork<List<Region>>() {
            public List<Region> execute(Connection connection) throws SQLException {
                try {
                    CallableStatement cst = connection.prepareCall("{ ? = call "+paqueteConfiguracion+"SQL_REGION(?,?,?,?,?,?)}");
                    cst.registerOutParameter(1, Types.OTHER);
                    cst.setInt(2,0);
                    cst.setString(3,Descripcion);
                    cst.setString(4,Estado);
                    cst.setString(5,PaginaStart);
                    cst.setString(6,PaginaLength);
                    cst.setString(7,Orderby);
                    cst.execute();
                    List<Region> lista = new ArrayList<Region>();
                    ResultSet rs = (ResultSet) cst.getObject(1);
                    if (rs != null) {
                        while (rs.next()) {
                            Region obj = new Region();
                            obj.setIdRegion(rs.getInt(1));
                            obj.setDescRegion(rs.getString(2));
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
}
