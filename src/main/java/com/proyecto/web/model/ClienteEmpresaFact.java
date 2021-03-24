package com.proyecto.web.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by jonat on 27/02/2020.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClienteEmpresaFact {

    @JsonProperty("idcliemp")
    private Integer idCliEmp;

    @JsonProperty("cliente")
    private Cliente cliente;

    @JsonProperty("fetipocliente")
    private Tablas feTipoCliente;

    @JsonProperty("estado")
    private String estado;

    public Integer getIdCliEmp() {
        return idCliEmp;
    }

    public void setIdCliEmp(Integer idCliEmp) {
        this.idCliEmp = idCliEmp;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Tablas getFeTipoCliente() {
        return feTipoCliente;
    }

    public void setFeTipoCliente(Tablas feTipoCliente) {
        this.feTipoCliente = feTipoCliente;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
