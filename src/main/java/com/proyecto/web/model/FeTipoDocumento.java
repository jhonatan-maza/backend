package com.proyecto.web.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by jonat on 27/02/2020.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FeTipoDocumento {

    @JsonProperty("coddocumento")
    private String codDocumento;

    @JsonProperty("codsunat")
    private String codSunat;

    @JsonProperty("descdocumento")
    private String descDocumento;

    public String getCodDocumento() {
        return codDocumento;
    }

    public void setCodDocumento(String codDocumento) {
        this.codDocumento = codDocumento;
    }

    public String getCodSunat() {
        return codSunat;
    }

    public void setCodSunat(String codSunat) {
        this.codSunat = codSunat;
    }

    public String getDescDocumento() {
        return descDocumento;
    }

    public void setDescDocumento(String descDocumento) {
        this.descDocumento = descDocumento;
    }
}
