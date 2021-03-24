package com.proyecto.web.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by jonat on 8/11/2019.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class JerarqSubClase {

    @JsonProperty("idsubclase")
    private Integer idSubClase;

    @JsonProperty("clase")
    private JerarqClase clase;

    @JsonProperty("descsubclase")
    private String descSubClase;

    @JsonProperty("estado")
    private String estado;

    @JsonProperty("arraypresentacion")
    private String arrayPresentacion;

    public Integer getIdSubClase() {
        return idSubClase;
    }

    public void setIdSubClase(Integer idSubClase) {
        this.idSubClase = idSubClase;
    }

    public JerarqClase getClase() {
        return clase;
    }

    public void setClase(JerarqClase clase) {
        this.clase = clase;
    }

    public String getDescSubClase() {
        return descSubClase;
    }

    public void setDescSubClase(String descSubClase) {
        this.descSubClase = descSubClase;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getArrayPresentacion() {
        return arrayPresentacion;
    }

    public void setArrayPresentacion(String arrayPresentacion) {
        this.arrayPresentacion = arrayPresentacion;
    }
}
