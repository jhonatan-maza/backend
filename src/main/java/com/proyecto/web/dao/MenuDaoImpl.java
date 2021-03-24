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
import java.util.*;

/**
 * Created by jonat on 31/08/2019.
 */
@Repository
public class MenuDaoImpl implements MenuDao {

    private static final Logger log = LoggerFactory.getLogger(MenuDaoImpl.class);
    @Autowired
    private SessionFactory sessionFactory;

    @Value("${paqueteConfiguracion}")
    protected String paqueteConfiguracion;

    @Transactional(readOnly = true)
    public List<MenuPerfil> MenuPerfilxPosicion(final Integer perfil) {
        return sessionFactory.getCurrentSession().doReturningWork(new ReturningWork<List<MenuPerfil>>() {
            public List<MenuPerfil> execute(Connection connection) throws SQLException {
                try {
                    CallableStatement cst = connection.prepareCall("{ ? = call ACCESO_MENU_PERFILXPOSICION(?,?,?)}");
                    cst.registerOutParameter(1, Types.OTHER);
                    cst.setInt(2,perfil);
                    cst.setInt(3,2);
                    cst.setInt(4,0);
                    cst.execute();
                    List<MenuPerfil> lista = new ArrayList<MenuPerfil>();
                    ResultSet rs = (ResultSet) cst.getObject(1);
                    if (rs != null) {
                        while (rs.next()) {
                            MenuPerfil obj = new MenuPerfil();
                            obj.setMenu(new Menu());
                            obj.getMenu().setIdMenu(rs.getInt(1));
                            obj.getMenu().setDescMenu(rs.getString(2));
                            obj.getMenu().setIcono(rs.getString(3));
                            obj.getMenu().setLinkSistema(rs.getString(4));
                            obj.getMenu().setEstado(rs.getString(5));
                            obj.setIdsOperacion(rs.getString(6));
                            List<MenuPerfilCuatro> menu_cuatro = MenuPerfilxPosicion_CUATRO(perfil,4,obj.getMenu().getIdMenu());
                            for (int i = 0; i <menu_cuatro.size() ; i++) {
                                obj.getListaMenuPerfilCuatro().add(menu_cuatro.get(i));
                            }
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
    public List<MenuPerfilCuatro> MenuPerfilxPosicion_CUATRO(final Integer perfil, final Integer posicion, final Integer menuRef) {
        return sessionFactory.getCurrentSession().doReturningWork(new ReturningWork<List<MenuPerfilCuatro>>() {
            public List<MenuPerfilCuatro> execute(Connection connection) throws SQLException {
                try {
                    CallableStatement cst = connection.prepareCall("{ ? = call ACCESO_MENU_PERFILXPOSICION(?,?,?)}");
                    cst.registerOutParameter(1, Types.OTHER);
                    cst.setInt(2,perfil);
                    cst.setInt(3,posicion);
                    cst.setInt(4,menuRef);
                    cst.execute();
                    List<MenuPerfilCuatro> lista = new ArrayList<MenuPerfilCuatro>();
                    ResultSet rs = (ResultSet) cst.getObject(1);
                    if (rs != null) {
                        while (rs.next()) {
                            MenuPerfilCuatro obj = new MenuPerfilCuatro();
                            obj.setMenu(new Menu());
                            obj.getMenu().setIdMenu(rs.getInt(1));
                            obj.getMenu().setDescMenu(rs.getString(2));
                            obj.getMenu().setIcono(rs.getString(3));
                            obj.getMenu().setLinkSistema(rs.getString(4));
                            obj.getMenu().setEstado(rs.getString(5));
                            obj.setIdsOperacion(rs.getString(6));
                            List<MenuPerfilSeis> menu_seis = MenuPerfilxPosicion_SEIS(perfil,6,obj.getMenu().getIdMenu());
                            for (int i = 0; i <menu_seis.size() ; i++) {
                                obj.getListaMenuPerfilSeis().add(menu_seis.get(i));
                            }
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
    public List<MenuPerfilSeis> MenuPerfilxPosicion_SEIS(final Integer perfil, final Integer posicion, final Integer menuRef) {
        return sessionFactory.getCurrentSession().doReturningWork(new ReturningWork<List<MenuPerfilSeis>>() {
            public List<MenuPerfilSeis> execute(Connection connection) throws SQLException {
                try {
                    CallableStatement cst = connection.prepareCall("{ ? = call ACCESO_MENU_PERFILXPOSICION(?,?,?)}");
                    cst.registerOutParameter(1, Types.OTHER);
                    cst.setInt(2,perfil);
                    cst.setInt(3,posicion);
                    cst.setInt(4,menuRef);
                    cst.execute();
                    List<MenuPerfilSeis> lista = new ArrayList<MenuPerfilSeis>();
                    ResultSet rs = (ResultSet) cst.getObject(1);
                    if (rs != null) {
                        while (rs.next()) {
                            MenuPerfilSeis obj = new MenuPerfilSeis();
                            obj.setMenu(new Menu());
                            obj.getMenu().setIdMenu(rs.getInt(1));
                            obj.getMenu().setDescMenu(rs.getString(2));
                            obj.getMenu().setIcono(rs.getString(3));
                            obj.getMenu().setLinkSistema(rs.getString(4));
                            obj.getMenu().setEstado(rs.getString(5));
                            obj.setIdsOperacion(rs.getString(6));
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
    public List<Menu> ListarMenu(final String Descripcion, final String Estado, final String PaginaStart, final String PaginaLength, final String Orderby) {
        return sessionFactory.getCurrentSession().doReturningWork(new ReturningWork<List<Menu>>() {
            public List<Menu> execute(Connection connection) throws SQLException {
                try {
                    CallableStatement cst = connection.prepareCall("{ ? = call "+paqueteConfiguracion+"SQL_MENU(?,?,?,?,?,?)}");
                    cst.registerOutParameter(1, Types.OTHER);
                    cst.setInt(2,0);
                    cst.setString(3,Descripcion);
                    cst.setString(4,Estado);
                    cst.setString(5,PaginaStart);
                    cst.setString(6,PaginaLength);
                    cst.setString(7,Orderby);
                    cst.execute();
                    List<Menu> lista = new ArrayList<Menu>();
                    ResultSet rs = (ResultSet) cst.getObject(1);
                    if (rs != null) {
                        while (rs.next()) {
                            Menu obj = new Menu();
                            obj.setIdMenu(rs.getInt(1));
                            obj.setDescMenu(rs.getString(2));
                            obj.setEstado(rs.getString(3));
                            obj.setIcono(rs.getString(4));
                            obj.setLinkSistema(rs.getString(5));
                            obj.setPosicion(rs.getInt(6));
                            obj.setMenuReferencial(rs.getInt(7));
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
