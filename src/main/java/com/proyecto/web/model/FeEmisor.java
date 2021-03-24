package com.proyecto.web.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by jonat on 20/02/2020.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FeEmisor {

    @JsonProperty("idemisor")
    private Integer idemisor;

    @JsonProperty("cliemp")
    private ClienteEmpresaFact cliEmp;

    @JsonProperty("rutacertificado")
    private String rutacertificado;

    @JsonProperty("keystorepass")
    private String keystorepass;

    @JsonProperty("privatekeypass")
    private String privatekeypass;

    @JsonProperty("privatekeyalias")
    private String privatekeyalias;

    public Integer getIdemisor() {
        return idemisor;
    }

    public void setIdemisor(Integer idemisor) {
        this.idemisor = idemisor;
    }

    public ClienteEmpresaFact getCliEmp() {
        return cliEmp;
    }

    public void setCliEmp(ClienteEmpresaFact cliEmp) {
        this.cliEmp = cliEmp;
    }

    public String getRutacertificado() {
        return rutacertificado;
    }

    public void setRutacertificado(String rutacertificado) {
        this.rutacertificado = rutacertificado;
    }

    public String getKeystorepass() {
        return keystorepass;
    }

    public void setKeystorepass(String keystorepass) {
        this.keystorepass = keystorepass;
    }

    public String getPrivatekeypass() {
        return privatekeypass;
    }

    public void setPrivatekeypass(String privatekeypass) {
        this.privatekeypass = privatekeypass;
    }

    public String getPrivatekeyalias() {
        return privatekeyalias;
    }

    public void setPrivatekeyalias(String privatekeyalias) {
        this.privatekeyalias = privatekeyalias;
    }
}
