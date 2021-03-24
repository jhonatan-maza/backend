package com.proyecto.web.dao;

import org.hibernate.SessionFactory;
import org.hibernate.jdbc.ReturningWork;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.*;

/**
 * Created by jonat on 25/02/2020.
 */
@Repository
public class TicketDaoImpl implements TicketDao {

    private static final Logger log = LoggerFactory.getLogger(TicketDaoImpl.class);
    @Autowired
    private SessionFactory sessionFactory;

    @Value("${nombrePaqueteFACT}")
    protected String nombrePaqueteFACT;

    @Transactional(readOnly = true)
    public Integer getCorrelativoResumenBaja(final String rucEmpresa, final String fechaEmision, final String tipoTicket) {
        return  sessionFactory.getCurrentSession().doReturningWork(new ReturningWork<Integer>() {
            public Integer execute(Connection connection) throws SQLException {
                try{
                    CallableStatement cst = connection.prepareCall("{ ? = call "+nombrePaqueteFACT+"FACT2020_CORRELATIVO_RESUMENBAJA(?,?,?) }");
                    cst.registerOutParameter(1, Types.OTHER);
                    cst.setString(2, rucEmpresa);
                    cst.setString(3, fechaEmision);
                    cst.setString(4, tipoTicket);
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
