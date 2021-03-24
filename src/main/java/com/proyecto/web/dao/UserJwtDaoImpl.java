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
 * Created by jonat on 25/08/2019.
 */
@Repository("UserJwtDao")
public class UserJwtDaoImpl implements UserJwtDao {

    private static final Logger log = LoggerFactory.getLogger(UserJwtDaoImpl.class);
    @Autowired
    private SessionFactory sessionFactory;

    @Value("${paqueteConfiguracion}")
    protected String paqueteConfiguracion;

    @Transactional(readOnly = true)
    public Usuario findByUsername(final String usuario) {
        return sessionFactory.getCurrentSession().doReturningWork(new ReturningWork<Usuario>() {
            public Usuario execute(Connection connection) throws SQLException {
                try {
                    CallableStatement cst = connection.prepareCall("{ ? = call ACCESO_LOGIN(?)}");
                    cst.registerOutParameter(1, Types.OTHER);
                    cst.setString(2,usuario);
                    cst.execute();
                    Usuario usuario = null;
                    ResultSet rs = (ResultSet)cst.getObject(1);
                    if (rs != null) {
                        while (rs.next()) {
                            usuario = new Usuario();
                            usuario.setIdusuario(rs.getInt(1));
                            usuario.setUsername(rs.getString(2));
                            usuario.setPassword(rs.getString(3));
                            usuario.setEstado(rs.getBoolean(4));
                            usuario.setIdempleado(new Empleado());
                            usuario.getIdempleado().setIdEmpleado(rs.getObject(5)!=null?rs.getInt(5) : null);
                            usuario.getIdempleado().setNombre(rs.getObject(6)!=null?rs.getString(6) : null);
                            usuario.getIdempleado().setApPaterno(rs.getObject(7)!=null?rs.getString(7) : null);
                            usuario.getIdempleado().setApMaterno(rs.getObject(8)!=null?rs.getString(8) : null);
                            usuario.getIdempleado().setPerfil(new Perfil());
                            usuario.getIdempleado().getPerfil().setIdPerfil(rs.getObject(9)!=null?rs.getInt(9) : null);
                            usuario.getIdempleado().getPerfil().setDescPerfil(rs.getObject(10)!=null?rs.getString(10) : null);
                            usuario.getIdempleado().getPerfil().setNivel(new Nivel());
                            usuario.getIdempleado().getPerfil().getNivel().setIdNivel(rs.getObject(11)!=null?rs.getInt(11) : null);
                            usuario.getIdempleado().getPerfil().getNivel().setDescNivel(rs.getObject(12)!=null?rs.getString(12):null);
                            usuario.getIdempleado().setLocalidad(new Localidad());
                            usuario.getIdempleado().getLocalidad().setIdLocalidad(rs.getObject(13)!=null?rs.getInt(13) : null);
                            usuario.getIdempleado().getLocalidad().setDesclocalidad(rs.getObject(14)!=null?rs.getString(14) : null);
                            usuario.getIdempleado().getLocalidad().setEmpresa(new Empresa());
                            usuario.getIdempleado().getLocalidad().getEmpresa().setIdEmpresa(rs.getObject(15)!=null?rs.getInt(15) : null);
                            usuario.getIdempleado().getLocalidad().getEmpresa().setDescEmpresa(rs.getObject(16)!=null?rs.getString(16) : null);
                            usuario.getIdempleado().getLocalidad().setSede(new Sede());
                            usuario.getIdempleado().getLocalidad().getSede().setIdSede(rs.getObject(17)!=null?rs.getInt(17) : null);
                            usuario.getIdempleado().getLocalidad().getSede().setDescSede(rs.getObject(18)!=null?rs.getString(18) : null);
                        }
                        rs.close();
                        cst.close();
                    }
                    return usuario;
                }catch ( Exception e) {
                    log.debug("Ocurrio una excepcion "+e.getMessage());
                    return null;
                }
            }
        });
    }

    @Transactional(readOnly = true)
    public List<Usuario> ListarUsuario(final String Usuario, final String Empleado, final String Estado, final String PaginaStart, final String PaginaLength, final String Orderby) {
        return sessionFactory.getCurrentSession().doReturningWork(new ReturningWork<List<Usuario>>() {
            public List<Usuario> execute(Connection connection) throws SQLException {
                try {
                    CallableStatement cst = connection.prepareCall("{ ? = call "+paqueteConfiguracion+"SQL_USUARIO(?,?,?,?,?,?,?)}");
                    cst.registerOutParameter(1, Types.OTHER);
                    cst.setInt(2,0);
                    cst.setString(3,Usuario);
                    cst.setString(4,Empleado);
                    cst.setString(5,Estado);
                    cst.setString(6,PaginaStart);
                    cst.setString(7,PaginaLength);
                    cst.setString(8,Orderby);
                    cst.execute();
                    List<Usuario> lista = new ArrayList<Usuario>();
                    ResultSet rs = (ResultSet) cst.getObject(1);
                    if (rs != null) {
                        while (rs.next()) {
                            Usuario obj = new Usuario();
                            obj.setIdusuario(rs.getInt(1));
                            obj.setUsername(rs.getString(2));
                            obj.setPassword(rs.getString(3));
                            obj.setEstado(rs.getBoolean(4));
                            obj.setIdempleado(new Empleado());
                            obj.getIdempleado().setIdEmpleado(rs.getInt(5));
                            obj.getIdempleado().setNombre(rs.getString(6));
                            obj.getIdempleado().setApMaterno(rs.getString(7));
                            obj.getIdempleado().setApMaterno(rs.getString(8));
                            obj.getIdempleado().setPerfil(new Perfil());
                            obj.getIdempleado().getPerfil().setIdPerfil(rs.getInt(9));
                            obj.getIdempleado().getPerfil().setDescPerfil(rs.getString(10));
                            obj.getIdempleado().getPerfil().setNivel(new Nivel());
                            obj.getIdempleado().getPerfil().getNivel().setIdNivel(rs.getInt(11));
                            obj.getIdempleado().getPerfil().getNivel().setDescNivel(rs.getString(12));
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
