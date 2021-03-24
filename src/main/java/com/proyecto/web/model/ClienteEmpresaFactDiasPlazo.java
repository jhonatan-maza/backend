package com.proyecto.web.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by jonat on 27/02/2020.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClienteEmpresaFactDiasPlazo {

    @JsonProperty("idcliemp")
    private ClienteEmpresaFact idCliEmp;

    @JsonProperty("tipodoc")
    private FeTipoDocumento tipoDoc;

    @JsonProperty("iniciodias")
    private Integer inicioDias;

    @JsonProperty("finaldias")
    private Integer finalDias;

    public ClienteEmpresaFact getIdCliEmp() {
        return idCliEmp;
    }

    public void setIdCliEmp(ClienteEmpresaFact idCliEmp) {
        this.idCliEmp = idCliEmp;
    }

    public FeTipoDocumento getTipoDoc() {
        return tipoDoc;
    }

    public void setTipoDoc(FeTipoDocumento tipoDoc) {
        this.tipoDoc = tipoDoc;
    }

    public Integer getInicioDias() {
        return inicioDias;
    }

    public void setInicioDias(Integer inicioDias) {
        this.inicioDias = inicioDias;
    }

    public Integer getFinalDias() {
        return finalDias;
    }

    public void setFinalDias(Integer finalDias) {
        this.finalDias = finalDias;
    }
}
