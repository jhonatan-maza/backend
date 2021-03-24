package com.proyecto.web.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by jonat on 23/10/2019.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class JerarqClase {

    @JsonProperty("idclase")
    private Integer idClase;

    @JsonProperty("familia")
    private JerarqFamilia familia;

    @JsonProperty("descclase")
    private String descClase;

    @JsonProperty("estado")
    private String estado;

    public Integer getIdClase() {
        return idClase;
    }

    public void setIdClase(Integer idClase) {
        this.idClase = idClase;
    }

    public JerarqFamilia getFamilia() {
        return familia;
    }

    public void setFamilia(JerarqFamilia familia) {
        this.familia = familia;
    }

    public String getDescClase() {
        return descClase;
    }

    public void setDescClase(String descClase) {
        this.descClase = descClase;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
