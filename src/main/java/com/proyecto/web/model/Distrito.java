package com.proyecto.web.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by jonat on 12/04/2019.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Distrito {

    @JsonProperty("iddistrito")
    private Integer idDistrito;

    @JsonProperty("provincia")
    private Provincia provincia;

    @JsonProperty("descdistrito")
    private String descDistrito;

    public Integer getIdDistrito() {
        return idDistrito;
    }

    public void setIdDistrito(Integer idDistrito) {
        this.idDistrito = idDistrito;
    }

    public Provincia getProvincia() {
        return provincia;
    }

    public void setProvincia(Provincia provincia) {
        this.provincia = provincia;
    }

    public String getDescDistrito() {
        return descDistrito;
    }

    public void setDescDistrito(String descDistrito) {
        this.descDistrito = descDistrito;
    }
}
