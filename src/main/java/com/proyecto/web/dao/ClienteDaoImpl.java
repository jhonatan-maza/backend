package com.proyecto.web.dao;

import com.proyecto.web.model.Cliente;
import com.proyecto.web.model.ClienteRuta;
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
 * Created by jonat on 1/03/2020.
 */
@Repository
public class ClienteDaoImpl implements ClienteDao {

    private static final Logger log = LoggerFactory.getLogger(ClienteDaoImpl.class);
    @Autowired
    private SessionFactory sessionFactory;

    @Transactional(readOnly = false)
    public boolean mantenimientoCliente(final Integer idCliente,final Integer ubigeo,final Integer tipoDoc,final String documentoId,final String estado,final String nombreComercial,
                                        final String nombre,final String apPaterno,final String apMaterno,final String sexo,final String fechaNac,final String celular,final String telefono,
                                        final String direccion,final String referencia,final String email, Integer tipoCliente, Integer giroNegocio, Integer codPais,
                                        final String usuario,final Integer operacion) {
        return  sessionFactory.getCurrentSession().doReturningWork(new ReturningWork<Boolean>() {
            public Boolean execute(Connection connection) throws SQLException {
                try{
                    CallableStatement cst = connection.prepareCall("{ ? = call FACT2020_UPDATEENVIO(?,?,?,?) }");
                    cst.registerOutParameter(1, Types.BOOLEAN);
                    cst.setInt(2, idCliente);
                    cst.setInt(3, ubigeo);
                    cst.setInt(4, tipoDoc);
                    cst.setString(5, documentoId);
                    cst.setString(6, estado);
                    cst.setString(7, nombreComercial);
                    cst.setString(8, nombre);
                    cst.setString(9, apPaterno);
                    cst.setString(10, apMaterno);
                    cst.setString(11, sexo);
                    cst.setObject(12, fechaNac, Types.DATE);
                    cst.setString(13, sexo);
                    cst.setString(14, celular);
                    cst.setString(15, telefono);
                    cst.setString(16, direccion);
                    cst.setString(17, referencia);
                    cst.setInt(18, tipoCliente);
                    cst.setInt(19, giroNegocio);
                    cst.setInt(20, codPais);
                    cst.setString(21, usuario);
                    cst.setInt(22, operacion);
                    cst.execute();

                    boolean respuesta = cst.getBoolean(1);
                    cst.close();
                    return respuesta;

                }catch ( Exception e) {
                    log.debug("Ocurrio una excepcion "+e.getMessage());
                    return null;
                }
            }
        });
    }

    @Transactional(readOnly = true)
    public List<ClienteRuta> ListarClienteXRuta(final Integer idLocalidad, final Integer idUsuario,
                                                final String start, final String lenght, final String orderBy, final Integer operacion) {
        return sessionFactory.getCurrentSession().doReturningWork(new ReturningWork<List<ClienteRuta>>() {
            public List<ClienteRuta> execute(Connection connection) throws SQLException {
                try {
                    CallableStatement cst = connection.prepareCall("{ ? = call SQL_CLIENTE_X_RUTA(?,?,?,?,?,?,?,?,?,?)}");
                    cst.registerOutParameter(1, Types.OTHER);
                    cst.setInt(2,0);
                    cst.setInt(3,0);
                    cst.setInt(4,0);
                    cst.setInt(5,idLocalidad);
                    cst.setInt(6,0);
                    cst.setInt(7,idUsuario);
                    cst.setString(8,lenght);
                    cst.setString(9,start);
                    cst.setString(10,orderBy);
                    cst.setInt(11,operacion);
                    cst.execute();
                    List<ClienteRuta> lista = new ArrayList<ClienteRuta>();
                    ResultSet rs = (ResultSet) cst.getObject(1);
                    if (rs != null) {
                        while (rs.next()) {
                            ClienteRuta obj = new ClienteRuta();
                            obj.setCliente(new Cliente());
                            obj.getCliente().setIdCliente(rs.getInt(1));
                            obj.getCliente().setDocumentoIde(rs.getString(2));
                            obj.getCliente().setNombreComercial(rs.getString(3)); //Remplazo Nombre en general
                            obj.getCliente().setDireccion(rs.getString(4));
                            obj.setRuta(new Ruta());
                            obj.getRuta().setDescRuta(rs.getString(5));
                            obj.setCoordenadaX(rs.getDouble(6));
                            obj.setCoordenadaY(rs.getDouble(7));
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
