package com.proyecto.web.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by jonat on 13/04/2020.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Almacen {

    @JsonProperty("idalmacen")
    private Integer idAlmacen;

    @JsonProperty("empresa")
    private Empresa empresa;

    @JsonProperty("sede")
    private Sede sede;

    @JsonProperty("tipolocal")
    private Tablas tipoLocal;

    @JsonProperty("descalmacen")
    private String descalmacen;

    @JsonProperty("direccion")
    private String direccion;

    @JsonProperty("estado")
    private String estado;

    public Integer getIdAlmacen() {
        return idAlmacen;
    }

    public void setIdAlmacen(Integer idAlmacen) {
        this.idAlmacen = idAlmacen;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public Sede getSede() {
        return sede;
    }

    public void setSede(Sede sede) {
        this.sede = sede;
    }

    public Tablas getTipoLocal() {
        return tipoLocal;
    }

    public void setTipoLocal(Tablas tipoLocal) {
        this.tipoLocal = tipoLocal;
    }

    public String getDescalmacen() {
        return descalmacen;
    }

    public void setDescalmacen(String descalmacen) {
        this.descalmacen = descalmacen;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
