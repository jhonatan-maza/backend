package com.proyecto.web.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by jonat on 20/03/2020.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DataVentaArticulo {

    @JsonProperty("articulo")
    private Articulo articulo;

    @JsonProperty("precio")
    private Precio precio;

    @JsonProperty("saldoslocalidad")
    private SaldosLocalidad saldosLocalidad;

    @JsonProperty("urlimagen")
    private String urlImagen;

    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }

    public Precio getPrecio() {
        return precio;
    }

    public void setPrecio(Precio precio) {
        this.precio = precio;
    }

    public SaldosLocalidad getSaldosLocalidad() {
        return saldosLocalidad;
    }

    public void setSaldosLocalidad(SaldosLocalidad saldosLocalidad) {
        this.saldosLocalidad = saldosLocalidad;
    }

    public String getUrlImagen() {
        return urlImagen;
    }

    public void setUrlImagen(String urlImagen) {
        this.urlImagen = urlImagen;
    }
}
