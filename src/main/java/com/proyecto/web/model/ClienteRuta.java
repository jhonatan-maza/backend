package com.proyecto.web.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by jonat on 4/03/2020.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClienteRuta {

    @JsonProperty("cliente")
    private Cliente cliente;

    @JsonProperty("ruta")
    private Ruta ruta;

    @JsonProperty("direccion")
    private String direccion;

    @JsonProperty("estado")
    private String estado;

    @JsonProperty("flagprincipal")
    private Integer flagPrincipal;

    @JsonProperty("coordenadax")
    private Double coordenadaX;

    @JsonProperty("coordenaday")
    private Double coordenadaY;

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Ruta getRuta() {
        return ruta;
    }

    public void setRuta(Ruta ruta) {
        this.ruta = ruta;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Integer getFlagPrincipal() {
        return flagPrincipal;
    }

    public void setFlagPrincipal(Integer flagPrincipal) {
        this.flagPrincipal = flagPrincipal;
    }

    public Double getCoordenadaX() {
        return coordenadaX;
    }

    public void setCoordenadaX(Double coordenadaX) {
        this.coordenadaX = coordenadaX;
    }

    public Double getCoordenadaY() {
        return coordenadaY;
    }

    public void setCoordenadaY(Double coordenadaY) {
        this.coordenadaY = coordenadaY;
    }
}
