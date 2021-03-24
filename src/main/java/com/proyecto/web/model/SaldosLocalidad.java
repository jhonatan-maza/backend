package com.proyecto.web.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by jonat on 19/03/2020.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SaldosLocalidad {

    @JsonProperty("idsaldo")
    private Integer idSaldo;

    @JsonProperty("localidad")
    private Localidad localidad;

    @JsonProperty("articulo")
    private Articulo articulo;

    @JsonProperty("cantidad")
    private Integer cantidad;

    @JsonProperty("cantidadreservada")
    private Integer cantidadReservada;

    @JsonProperty("cantidadxllegar")
    private Integer cantidadXLlegar;

    public Integer getIdSaldo() {
        return idSaldo;
    }

    public void setIdSaldo(Integer idSaldo) {
        this.idSaldo = idSaldo;
    }

    public Localidad getLocalidad() {
        return localidad;
    }

    public void setLocalidad(Localidad localidad) {
        this.localidad = localidad;
    }

    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Integer getCantidadReservada() {
        return cantidadReservada;
    }

    public void setCantidadReservada(Integer cantidadReservada) {
        this.cantidadReservada = cantidadReservada;
    }

    public Integer getCantidadXLlegar() {
        return cantidadXLlegar;
    }

    public void setCantidadXLlegar(Integer cantidadXLlegar) {
        this.cantidadXLlegar = cantidadXLlegar;
    }
}
