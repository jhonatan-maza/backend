package com.proyecto.web.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by jonat on 12/04/2019.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Region {

    @JsonProperty("idregion")
    private Integer idRegion;

    @JsonProperty("descregion")
    private String descRegion;

    @JsonProperty("estado")
    private String estado;

    public Integer getIdRegion() {
        return idRegion;
    }

    public void setIdRegion(Integer idRegion) {
        this.idRegion = idRegion;
    }

    public String getDescRegion() {
        return descRegion;
    }

    public void setDescRegion(String descRegion) {
        this.descRegion = descRegion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
