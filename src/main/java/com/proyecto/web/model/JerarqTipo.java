package com.proyecto.web.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by jonat on 23/10/2019.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class JerarqTipo {

    @JsonProperty("idtipo")
    private Integer idTipo;

    @JsonProperty("codtipo")
    private String codTipo;

    @JsonProperty("desctipo")
    private String descTipo;

    @JsonProperty("estado")
    private String estado;

    @JsonProperty("arraysubtipo")
    private String arraySubTipo;

    @JsonProperty("arrayvariedad")
    private String arrayVariedad;

    public Integer getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(Integer idTipo) {
        this.idTipo = idTipo;
    }

    public String getCodTipo() {
        return codTipo;
    }

    public void setCodTipo(String codTipo) {
        this.codTipo = codTipo;
    }

    public String getDescTipo() {
        return descTipo;
    }

    public void setDescTipo(String descTipo) {
        this.descTipo = descTipo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getArraySubTipo() {
        return arraySubTipo;
    }

    public void setArraySubTipo(String arraySubTipo) {
        this.arraySubTipo = arraySubTipo;
    }

    public String getArrayVariedad() {
        return arrayVariedad;
    }

    public void setArrayVariedad(String arrayVariedad) {
        this.arrayVariedad = arrayVariedad;
    }
}
