package com.proyecto.web.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by jonat on 23/10/2019.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UnidadMedida {

    @JsonProperty("idum")
    private Integer idUm;

    @JsonProperty("codum")
    private String codUm;

    @JsonProperty("descum")
    private String descUm;

    @JsonProperty("estado")
    private String estado;

    public Integer getIdUm() {
        return idUm;
    }

    public void setIdUm(Integer idUm) {
        this.idUm = idUm;
    }

    public String getCodUm() {
        return codUm;
    }

    public void setCodUm(String codUm) {
        this.codUm = codUm;
    }

    public String getDescUm() {
        return descUm;
    }

    public void setDescUm(String descUm) {
        this.descUm = descUm;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
