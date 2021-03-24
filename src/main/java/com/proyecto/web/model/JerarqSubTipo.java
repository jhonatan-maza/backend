package com.proyecto.web.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by jonat on 23/10/2019.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class JerarqSubTipo {

    @JsonProperty("idsubtipo")
    private Integer idSubTipo;

    @JsonProperty("codsubtipo")
    private String codSubTipo;

    @JsonProperty("descsubtipo")
    private String descSubTipo;

    @JsonProperty("estado")
    private String estado;

    public Integer getIdSubTipo() {
        return idSubTipo;
    }

    public void setIdSubTipo(Integer idSubTipo) {
        this.idSubTipo = idSubTipo;
    }

    public String getCodSubTipo() {
        return codSubTipo;
    }

    public void setCodSubTipo(String codSubTipo) {
        this.codSubTipo = codSubTipo;
    }

    public String getDescSubTipo() {
        return descSubTipo;
    }

    public void setDescSubTipo(String descSubTipo) {
        this.descSubTipo = descSubTipo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
