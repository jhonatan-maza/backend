package com.proyecto.web.dao;

import com.proyecto.web.model.JerarqCategoria;
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
 * Created by jonat on 21/04/2020.
 */
@Repository
public class ComboXOperacionDaoImpl implements ComboXOperacionDao {

    private static final Logger log = LoggerFactory.getLogger(ComboXOperacionDaoImpl.class);
    @Autowired
    private SessionFactory sessionFactory;

    @Value("${paqueteCombo}")
    protected String paqueteCombo;

    @Transactional(readOnly = true)
    public List<JerarqCategoria> ComboXOperacionListarCategoria(final Integer operacion) {
        return sessionFactory.getCurrentSession().doReturningWork(new ReturningWork<List<JerarqCategoria>>() {
            public List<JerarqCategoria> execute(Connection connection) throws SQLException {
                try {
                    CallableStatement cst = connection.prepareCall("{ ? = call "+paqueteCombo+"SQL_X_OPERACION(?)}");
                    cst.registerOutParameter(1, Types.OTHER);
                    cst.setInt(2,operacion);
                    cst.execute();
                    List<JerarqCategoria> lista = new ArrayList<JerarqCategoria>();
                    ResultSet rs = (ResultSet) cst.getObject(1);
                    if (rs != null) {
                        while (rs.next()) {
                            JerarqCategoria obj = new JerarqCategoria();
                            obj.setIdCategoria(rs.getInt(1));
                            obj.setDescCategoria(rs.getString(2));
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
