package com.proyecto.web.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by jonat on 8/11/2019.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class JerarqCategoria {

    @JsonProperty("idcategoria")
    private Integer idCategoria;

    @JsonProperty("subclase")
    private JerarqSubClase subClase;

    @JsonProperty("desccategoria")
    private String descCategoria;

    @JsonProperty("estado")
    private String estado;

    @JsonProperty("arraymarca")
    private String arrayMarca;

    @JsonProperty("arraytipo")
    private String arrayTipo;

    public Integer getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }

    public JerarqSubClase getSubClase() {
        return subClase;
    }

    public void setSubClase(JerarqSubClase subClase) {
        this.subClase = subClase;
    }

    public String getDescCategoria() {
        return descCategoria;
    }

    public void setDescCategoria(String descCategoria) {
        this.descCategoria = descCategoria;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getArrayMarca() {
        return arrayMarca;
    }

    public void setArrayMarca(String arrayMarca) {
        this.arrayMarca = arrayMarca;
    }

    public String getArrayTipo() {
        return arrayTipo;
    }

    public void setArrayTipo(String arrayTipo) {
        this.arrayTipo = arrayTipo;
    }
}
