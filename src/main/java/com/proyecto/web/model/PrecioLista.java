package com.proyecto.web.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by jonat on 16/03/2020.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PrecioLista {

    @JsonProperty("idpreciolista")
    private Integer idListaDePrecio;

    @JsonProperty("localidad")
    private Localidad localidad;

    @JsonProperty("descripcion")
    private String descripcion;

    @JsonProperty("estado")
    private String estado;

    public Integer getIdListaDePrecio() {
        return idListaDePrecio;
    }

    public void setIdListaDePrecio(Integer idListaDePrecio) {
        this.idListaDePrecio = idListaDePrecio;
    }

    public Localidad getLocalidad() {
        return localidad;
    }

    public void setLocalidad(Localidad localidad) {
        this.localidad = localidad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
