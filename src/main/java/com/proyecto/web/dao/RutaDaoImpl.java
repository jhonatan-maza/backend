package com.proyecto.web.dao;

import com.proyecto.web.model.Ruta;
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
 * Created by jonat on 4/03/2020.
 */
@Repository
public class RutaDaoImpl implements RutaDao {

    private static final Logger log = LoggerFactory.getLogger(RutaDaoImpl.class);
    @Autowired
    private SessionFactory sessionFactory;

    @Transactional(readOnly = true)
    public List<Ruta> ListarRutaXLocalidad(final Integer localidad,final Integer operacion) {
        return sessionFactory.getCurrentSession().doReturningWork(new ReturningWork<List<Ruta>>() {
            public List<Ruta> execute(Connection connection) throws SQLException {
                try {
                    CallableStatement cst = connection.prepareCall("{ ? = call SQL_RUTAS_X_LOCALIDAD(?,?)}");
                    cst.registerOutParameter(1, Types.OTHER);
                    cst.setInt(2,localidad);
                    cst.setInt(3,operacion);
                    cst.execute();
                    List<Ruta> lista = new ArrayList<Ruta>();
                    ResultSet rs = (ResultSet) cst.getObject(1);
                    if (rs != null) {
                        while (rs.next()) {
                            Ruta obj = new Ruta();
                            obj.setIdRuta(rs.getInt(1));
                            obj.setDescRuta(rs.getString(2));
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
