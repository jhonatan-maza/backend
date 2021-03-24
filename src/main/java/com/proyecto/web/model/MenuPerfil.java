package com.proyecto.web.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jonat on 31/08/2019.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MenuPerfil {

    @JsonProperty("menu")
    private Menu menu;

    @JsonProperty("perfil")
    private Perfil perfil;

    @JsonProperty("idsoperacion")
    private String idsOperacion;

    @JsonProperty("estadomenuperf")
    private String estadoMenuPerf;

    @JsonProperty("menucuatro")
    private List<MenuPerfilCuatro> listaMenuPerfilCuatro;

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

    public List<MenuPerfilCuatro> getListaMenuPerfilCuatro() {
        if (listaMenuPerfilCuatro == null) {
            listaMenuPerfilCuatro = new ArrayList<MenuPerfilCuatro>();
        }
        return this.listaMenuPerfilCuatro;
//        return listaMenuPerfilCuatro;
    }

    public void setListaMenuPerfilCuatro(List<MenuPerfilCuatro> listaMenuPerfilCuatro) {
        this.listaMenuPerfilCuatro = listaMenuPerfilCuatro;
    }
}
