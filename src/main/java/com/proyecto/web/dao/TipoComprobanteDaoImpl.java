package com.proyecto.web.dao;

import com.proyecto.web.model.FeTipoDocumento;
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
 * Created by jonat on 16/03/2020.
 */
@Repository
public class TipoComprobanteDaoImpl implements TipoComprobanteDao {

    private static final Logger log = LoggerFactory.getLogger(TipoComprobanteDaoImpl.class);
    @Autowired
    private SessionFactory sessionFactory;

    @Transactional(readOnly = true)
    public List<FeTipoDocumento> ListarTipoDocumentoVenta(final Integer operacion) {
        return sessionFactory.getCurrentSession().doReturningWork(new ReturningWork<List<FeTipoDocumento>>() {
            public List<FeTipoDocumento> execute(Connection connection) throws SQLException {
                try {
                    CallableStatement cst = connection.prepareCall("{ ? = call SQL_TIPO_COMPROBANTE(?,?)}");
                    cst.registerOutParameter(1, Types.OTHER);
                    cst.setString(2,null);
                    cst.setInt(3,operacion);
                    cst.execute();
                    List<FeTipoDocumento> lista = new ArrayList<FeTipoDocumento>();
                    ResultSet rs = (ResultSet) cst.getObject(1);
                    if (rs != null) {
                        while (rs.next()) {
                            FeTipoDocumento obj = new FeTipoDocumento();
                            obj.setCodDocumento(rs.getString(1));
                            obj.setCodSunat(rs.getString(2));
                            obj.setDescDocumento(rs.getString(3));
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
