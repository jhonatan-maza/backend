package com.proyecto.web.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by jonat on 12/04/2019.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Localidad {

    @JsonProperty("idlocalidad")
    private Integer idLocalidad;

    @JsonProperty("empresa")
    private Empresa empresa;

    @JsonProperty("sede")
    private Sede sede;

    @JsonProperty("tipolocal")
    private Tablas tipoLocal;

    @JsonProperty("desclocalidad")
    private String desclocalidad;

    @JsonProperty("direccion")
    private String direccion;

    @JsonProperty("estado")
    private String estado;

    public Integer getIdLocalidad() {
        return idLocalidad;
    }

    public void setIdLocalidad(Integer idLocalidad) {
        this.idLocalidad = idLocalidad;
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

    public String getDesclocalidad() {
        return desclocalidad;
    }

    public void setDesclocalidad(String desclocalidad) {
        this.desclocalidad = desclocalidad;
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
