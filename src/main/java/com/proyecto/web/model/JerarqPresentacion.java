package com.proyecto.web.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by jonat on 23/10/2019.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class JerarqPresentacion {

    @JsonProperty("idpresentacion")
    private Integer idPresentacion;

    @JsonProperty("codpresentacion")
    private String codPresentacion;

    @JsonProperty("descpresentacion")
    private String descPresentacion;

    @JsonProperty("estado")
    private String estado;

    @JsonProperty("envase")
    private JerarqAtributo envase;

    @JsonProperty("cantidad")
    private JerarqAtributo cantidad;

    @JsonProperty("unidad")
    private JerarqAtributo unidad;

    public Integer getIdPresentacion() {
        return idPresentacion;
    }

    public void setIdPresentacion(Integer idPresentacion) {
        this.idPresentacion = idPresentacion;
    }

    public String getCodPresentacion() {
        return codPresentacion;
    }

    public void setCodPresentacion(String codPresentacion) {
        this.codPresentacion = codPresentacion;
    }

    public String getDescPresentacion() {
        return descPresentacion;
    }

    public void setDescPresentacion(String descPresentacion) {
        this.descPresentacion = descPresentacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public JerarqAtributo getEnvase() {
        return envase;
    }

    public void setEnvase(JerarqAtributo envase) {
        this.envase = envase;
    }

    public JerarqAtributo getCantidad() {
        return cantidad;
    }

    public void setCantidad(JerarqAtributo cantidad) {
        this.cantidad = cantidad;
    }

    public JerarqAtributo getUnidad() {
        return unidad;
    }

    public void setUnidad(JerarqAtributo unidad) {
        this.unidad = unidad;
    }
}
