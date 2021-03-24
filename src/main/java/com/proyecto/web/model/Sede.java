package com.proyecto.web.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by jonat on 12/04/2019.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Sede {

    @JsonProperty("idsede")
    private Integer idSede;

    @JsonProperty("region")
    private Region region;

    @JsonProperty("distrito")
    private Distrito distrito;

    @JsonProperty("descsede")
    private String descSede;

    @JsonProperty("abresede")
    private String abreSede;

    @JsonProperty("numgestion")
    private String numGestion;

    @JsonProperty("estado")
    private String estado;

    public Integer getIdSede() {
        return idSede;
    }

    public void setIdSede(Integer idSede) {
        this.idSede = idSede;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public Distrito getDistrito() {
        return distrito;
    }

    public void setDistrito(Distrito distrito) {
        this.distrito = distrito;
    }

    public String getDescSede() {
        return descSede;
    }

    public void setDescSede(String descSede) {
        this.descSede = descSede;
    }

    public String getAbreSede() {
        return abreSede;
    }

    public void setAbreSede(String abreSede) {
        this.abreSede = abreSede;
    }

    public String getNumGestion() {
        return numGestion;
    }

    public void setNumGestion(String numGestion) {
        this.numGestion = numGestion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
