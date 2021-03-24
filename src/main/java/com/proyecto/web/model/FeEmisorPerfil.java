package com.proyecto.web.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by jonat on 20/02/2020.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FeEmisorPerfil {

    @JsonProperty("idemiper")
    private Integer idEmiPer;

    @JsonProperty("emisor")
    private FeEmisor emisor;

    @JsonProperty("feperfil")
    private FePerfil fePerfil;

    @JsonProperty("usuariosunat")
    private String usuarioSunat;

    @JsonProperty("clavesol")
    private String claveSol;

    @JsonProperty("claveservicio")
    private String claveServicio;

    public Integer getIdEmiPer() {
        return idEmiPer;
    }

    public void setIdEmiPer(Integer idEmiPer) {
        this.idEmiPer = idEmiPer;
    }

    public FeEmisor getEmisor() {
        return emisor;
    }

    public void setEmisor(FeEmisor emisor) {
        this.emisor = emisor;
    }

    public FePerfil getFePerfil() {
        return fePerfil;
    }

    public void setFePerfil(FePerfil fePerfil) {
        this.fePerfil = fePerfil;
    }

    public String getUsuarioSunat() {
        return usuarioSunat;
    }

    public void setUsuarioSunat(String usuarioSunat) {
        this.usuarioSunat = usuarioSunat;
    }

    public String getClaveSol() {
        return claveSol;
    }

    public void setClaveSol(String claveSol) {
        this.claveSol = claveSol;
    }

    public String getClaveServicio() {
        return claveServicio;
    }

    public void setClaveServicio(String claveServicio) {
        this.claveServicio = claveServicio;
    }
}
