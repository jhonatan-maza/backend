package com.proyecto.web.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by jonat on 23/10/2019.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class JerarqFamilia {

    @JsonProperty("idfamilia")
    private Integer idFamilia;

    @JsonProperty("descfamilia")
    private String descFamilia;

    @JsonProperty("estado")
    private String estado;

    public Integer getIdFamilia() {
        return idFamilia;
    }

    public void setIdFamilia(Integer idFamilia) {
        this.idFamilia = idFamilia;
    }

    public String getDescFamilia() {
        return descFamilia;
    }

    public void setDescFamilia(String descFamilia) {
        this.descFamilia = descFamilia;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
