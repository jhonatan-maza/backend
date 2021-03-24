package com.proyecto.web.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by jonat on 4/03/2020.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Ruta {

    @JsonProperty("idruta")
    private Integer idRuta;

    @JsonProperty("ubigeo")
    private Distrito ubigeo;

    @JsonProperty("descruta")
    private String descRuta;

    @JsonProperty("estado")
    private String estado;

    public Integer getIdRuta() {
        return idRuta;
    }

    public void setIdRuta(Integer idRuta) {
        this.idRuta = idRuta;
    }

    public Distrito getUbigeo() {
        return ubigeo;
    }

    public void setUbigeo(Distrito ubigeo) {
        this.ubigeo = ubigeo;
    }

    public String getDescRuta() {
        return descRuta;
    }

    public void setDescRuta(String descRuta) {
        this.descRuta = descRuta;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
