package com.proyecto.web.dao;

import com.proyecto.web.model.*;
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
 * Created by jonat on 21/02/2020.
 */
@Repository
public class SendFactExternoDaoImpl implements SendFactExternoDao {

    private static final Logger log = LoggerFactory.getLogger(SendFactExternoDaoImpl.class);
    @Autowired
    private SessionFactory sessionFactory;

    @Value("${nombrePaqueteFACT}")
    protected String nombrePaqueteFACT;

    @Transactional(readOnly = true)
    public List<FeEmisorPerfil> ListarEmisoresExternos() {
        return sessionFactory.getCurrentSession().doReturningWork(new ReturningWork<List<FeEmisorPerfil>>() {
            public List<FeEmisorPerfil> execute(Connection connection) throws SQLException {
                try {
                    CallableStatement cst = connection.prepareCall("{ ? = call "+nombrePaqueteFACT+"SERVICIOWEB_EMISOR_EXTERNO() }");
                    cst.registerOutParameter(1, Types.OTHER);
                    cst.execute();
                    List<FeEmisorPerfil> lista = new ArrayList<FeEmisorPerfil>();
                    ResultSet rs = (ResultSet) cst.getObject(1);
                    if (rs != null) {
                        while (rs.next()) {
                            FeEmisorPerfil obj = new FeEmisorPerfil();
                            obj.setEmisor(new FeEmisor());
                            obj.getEmisor().setCliEmp(new ClienteEmpresaFact());
                            obj.getEmisor().getCliEmp().getCliente().setDocumentoIde(rs.getString(1));
                            obj.setClaveServicio(rs.getString(2));
                            obj.setFePerfil(new FePerfil());
                            obj.getFePerfil().setDescripcion(rs.getString(3));
                            obj.getFePerfil().setWsdl(rs.getString(4));
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
