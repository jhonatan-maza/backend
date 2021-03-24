package com.proyecto.web.dao;

import com.proyecto.web.model.Departamento;
import com.proyecto.web.model.Distrito;
import com.proyecto.web.model.Provincia;
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
public class UbigeoDaoImpl implements UbigeoDao {

    private static final Logger log = LoggerFactory.getLogger(SedeDaoImpl.class);
    @Autowired
    private SessionFactory sessionFactory;

    @Value("${paqueteCombo}")
    protected String paqueteCombo;

    @Transactional(readOnly = true)
    public List<Departamento> ListarDepartamento() {
        return sessionFactory.getCurrentSession().doReturningWork(new ReturningWork<List<Departamento>>() {
            public List<Departamento> execute(Connection connection) throws SQLException {
                try {
                    CallableStatement cst = connection.prepareCall("{ ? = call "+paqueteCombo+"SQL_UBIGEO(?,?,?,?)}");
                    cst.registerOutParameter(1, Types.OTHER);
                    cst.setInt(2,0);
                    cst.setInt(3,0);
                    cst.setInt(4,0);
                    cst.setInt(5,1);
                    cst.execute();
                    List<Departamento> lista = new ArrayList<Departamento>();
                    ResultSet rs = (ResultSet) cst.getObject(1);
                    if (rs != null) {
                        while (rs.next()) {
                            Departamento obj = new Departamento();
                            obj.setIdDepartamento(rs.getInt(1));
                            obj.setDescDepartamento(rs.getString(2));
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
    public List<Provincia> ListarProvincia(final Integer departamento) {
        return sessionFactory.getCurrentSession().doReturningWork(new ReturningWork<List<Provincia>>() {
            public List<Provincia> execute(Connection connection) throws SQLException {
                try {
                    CallableStatement cst = connection.prepareCall("{ ? = call "+paqueteCombo+"SQL_UBIGEO(?,?,?,?)}");
                    cst.registerOutParameter(1, Types.OTHER);
                    cst.setInt(2,departamento);
                    cst.setInt(3,0);
                    cst.setInt(4,0);
                    cst.setInt(5,2);
                    cst.execute();
                    List<Provincia> lista = new ArrayList<Provincia>();
                    ResultSet rs = (ResultSet) cst.getObject(1);
                    if (rs != null) {
                        while (rs.next()) {
                            Provincia obj = new Provincia();
                            obj.setIdProvincia(rs.getInt(1));
                            obj.setDescProvincia(rs.getString(2));
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
    public List<Distrito> ListarDistrito(final Integer provincia) {
        return sessionFactory.getCurrentSession().doReturningWork(new ReturningWork<List<Distrito>>() {
            public List<Distrito> execute(Connection connection) throws SQLException {
                try {
                    CallableStatement cst = connection.prepareCall("{ ? = call "+paqueteCombo+"SQL_UBIGEO(?,?,?,?)}");
                    cst.registerOutParameter(1, Types.OTHER);
                    cst.setInt(2,0);
                    cst.setInt(3,provincia);
                    cst.setInt(4,0);
                    cst.setInt(5,3);
                    cst.execute();
                    List<Distrito> lista = new ArrayList<Distrito>();
                    ResultSet rs = (ResultSet) cst.getObject(1);
                    if (rs != null) {
                        while (rs.next()) {
                            Distrito obj = new Distrito();
                            obj.setIdDistrito(rs.getInt(1));
                            obj.setDescDistrito(rs.getString(2));
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
