package com.proyecto.web.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by jonat on 23/10/2019.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class JerarqVariedad {

    @JsonProperty("idvariedad")
    private Integer idVariedad;

    @JsonProperty("codvariedad")
    private String codVariedad;

    @JsonProperty("descvariedad")
    private String descVariedad;

    @JsonProperty("estado")
    private String estado;

    public Integer getIdVariedad() {
        return idVariedad;
    }

    public void setIdVariedad(Integer idVariedad) {
        this.idVariedad = idVariedad;
    }

    public String getCodVariedad() {
        return codVariedad;
    }

    public void setCodVariedad(String codVariedad) {
        this.codVariedad = codVariedad;
    }

    public String getDescVariedad() {
        return descVariedad;
    }

    public void setDescVariedad(String descVariedad) {
        this.descVariedad = descVariedad;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
