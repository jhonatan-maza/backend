package com.proyecto.web.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by jonat on 20/02/2020.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FePerfil {

    @JsonProperty("idfeperfil")
    private Integer idFePerfil;

    @JsonProperty("tipoperfil")
    private Tablas tipoPerfil;

    @JsonProperty("descripcion")
    private String descripcion;

    @JsonProperty("serviciourl")
    private String servicioUrl;

    @JsonProperty("wsdl")
    private String wsdl;

    @JsonProperty("estado")
    private String estado;

    public Integer getIdFePerfil() {
        return idFePerfil;
    }

    public void setIdFePerfil(Integer idFePerfil) {
        this.idFePerfil = idFePerfil;
    }

    public Tablas getTipoPerfil() {
        return tipoPerfil;
    }

    public void setTipoPerfil(Tablas tipoPerfil) {
        this.tipoPerfil = tipoPerfil;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getServicioUrl() {
        return servicioUrl;
    }

    public void setServicioUrl(String servicioUrl) {
        this.servicioUrl = servicioUrl;
    }

    public String getWsdl() {
        return wsdl;
    }

    public void setWsdl(String wsdl) {
        this.wsdl = wsdl;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
