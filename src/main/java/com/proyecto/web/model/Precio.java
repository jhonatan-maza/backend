package com.proyecto.web.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

/**
 * Created by jonat on 19/03/2020.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Precio {

    @JsonProperty("idprecio")
    private Integer idPrecio;

    @JsonProperty("preciolista")
    private PrecioLista precioLista;

    @JsonProperty("tipomoneda")
    private Tablas tipoMoneda;

    @JsonProperty("articulo")
    private Articulo articulo;

    @JsonProperty("preciobaseventa")
    private BigDecimal precioBaseVenta;

    @JsonProperty("preciobasecompra")
    private BigDecimal precioBaseCompra;

    @JsonProperty("estado")
    private Tablas estado;

    public Integer getIdPrecio() {
        return idPrecio;
    }

    public void setIdPrecio(Integer idPrecio) {
        this.idPrecio = idPrecio;
    }

    public PrecioLista getPrecioLista() {
        return precioLista;
    }

    public void setPrecioLista(PrecioLista precioLista) {
        this.precioLista = precioLista;
    }

    public Tablas getTipoMoneda() {
        return tipoMoneda;
    }

    public void setTipoMoneda(Tablas tipoMoneda) {
        this.tipoMoneda = tipoMoneda;
    }

    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }

    public BigDecimal getPrecioBaseVenta() {
        return precioBaseVenta;
    }

    public void setPrecioBaseVenta(BigDecimal precioBaseVenta) {
        this.precioBaseVenta = precioBaseVenta;
    }

    public BigDecimal getPrecioBaseCompra() {
        return precioBaseCompra;
    }

    public void setPrecioBaseCompra(BigDecimal precioBaseCompra) {
        this.precioBaseCompra = precioBaseCompra;
    }

    public Tablas getEstado() {
        return estado;
    }

    public void setEstado(Tablas estado) {
        this.estado = estado;
    }
}
