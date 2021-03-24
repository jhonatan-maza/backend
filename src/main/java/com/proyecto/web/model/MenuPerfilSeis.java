package com.proyecto.web.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by jonat on 25/03/2020.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MenuPerfilSeis {

    @JsonProperty("menuseis")
    private Menu menu;

    @JsonProperty("perfil")
    private Perfil perfil;

    @JsonProperty("idsoperacion")
    private String idsOperacion;

    @JsonProperty("estadomenuperf")
    private String estadoMenuPerf;

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    public String getIdsOperacion() {
        return idsOperacion;
    }

    public void setIdsOperacion(String idsOperacion) {
        this.idsOperacion = idsOperacion;
    }

    public String getEstadoMenuPerf() {
        return estadoMenuPerf;
    }

    public void setEstadoMenuPerf(String estadoMenuPerf) {
        this.estadoMenuPerf = estadoMenuPerf;
    }
}
