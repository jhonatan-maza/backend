package com.proyecto.web.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by jonat on 31/08/2019.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MenuOperacion {

    @JsonProperty("menu")
    private Menu menu;

    @JsonProperty("operacion")
    private Operacion operacion;

    @JsonProperty("estadomenuope")
    private String estadoMenuOpe;

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public Operacion getOperacion() {
        return operacion;
    }

    public void setOperacion(Operacion operacion) {
        this.operacion = operacion;
    }

    public String getEstadoMenuOpe() {
        return estadoMenuOpe;
    }

    public void setEstadoMenuOpe(String estadoMenuOpe) {
        this.estadoMenuOpe = estadoMenuOpe;
    }
}
