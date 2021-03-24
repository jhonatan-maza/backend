package com.proyecto.web.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by jonat on 12/04/2019.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Empresa {

    @JsonProperty("idempresa")
    private Integer idEmpresa;

    @JsonProperty("ubigeo")
    private Distrito ubigeo;

    @JsonProperty("codpais")
    private Tablas codPais;

    @JsonProperty("descempresa")
    private String descEmpresa;

    @JsonProperty("ruc")
    private String ruc;

    @JsonProperty("direccion")
    private String direccion;

    @JsonProperty("estado")
    private String estado;

    public Integer getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Integer idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public Distrito getUbigeo() {
        return ubigeo;
    }

    public void setUbigeo(Distrito ubigeo) {
        this.ubigeo = ubigeo;
    }

    public Tablas getCodPais() {
        return codPais;
    }

    public void setCodPais(Tablas codPais) {
        this.codPais = codPais;
    }

    public String getDescEmpresa() {
        return descEmpresa;
    }

    public void setDescEmpresa(String descEmpresa) {
        this.descEmpresa = descEmpresa;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
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
