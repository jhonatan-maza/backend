package com.proyecto.web.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by jonat on 31/08/2019.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Menu {

    @JsonProperty("idmenu")
    private Integer idMenu;

    @JsonProperty("descmenu")
    private String descMenu;

    @JsonProperty("icono")
    private String icono;

    @JsonProperty("linksistema")
    private String linkSistema;

    @JsonProperty("estado")
    private String estado;

    @JsonProperty("posicion")
    private Integer posicion;

    @JsonProperty("menureferencial")
    private Integer menuReferencial;

    public Integer getIdMenu() {
        return idMenu;
    }

    public void setIdMenu(Integer idMenu) {
        this.idMenu = idMenu;
    }

    public String getDescMenu() {
        return descMenu;
    }

    public void setDescMenu(String descMenu) {
        this.descMenu = descMenu;
    }

    public String getIcono() {
        return icono;
    }

    public void setIcono(String icono) {
        this.icono = icono;
    }

    public String getLinkSistema() {
        return linkSistema;
    }

    public void setLinkSistema(String linkSistema) {
        this.linkSistema = linkSistema;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Integer getPosicion() {
        return posicion;
    }

    public void setPosicion(Integer posicion) {
        this.posicion = posicion;
    }

    public Integer getMenuReferencial() {
        return menuReferencial;
    }

    public void setMenuReferencial(Integer menuReferencial) {
        this.menuReferencial = menuReferencial;
    }
}
