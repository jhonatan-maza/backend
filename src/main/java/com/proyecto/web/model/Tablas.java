package com.proyecto.web.model;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Created by jonat on 28/08/2019.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Tablas {

    private Integer idtabla;
    private String categoria;
    private Integer llave;
    private String descripcion1;
    private String descripcion2;
    private String descripcion3;
    private String descripcion4;
    private String descripcion5;
    private Integer numero1;
    private Integer numero2;
    private Integer numero3;
    private Integer numero4;
    private Integer numero5;
    private String fecha1;
    private String fecha2;
    private String fecha3;
    private String fecha4;
    private String fecha5;
    private String catrelacion;
    private String desccategoria;
    private String estado;
    private String observaciones;

    public Integer getIdtabla() {
        return idtabla;
    }

    public void setIdtabla(Integer idtabla) {
        this.idtabla = idtabla;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Integer getLlave() {
        return llave;
    }

    public void setLlave(Integer llave) {
        this.llave = llave;
    }

    public String getDescripcion1() {
        return descripcion1;
    }

    public void setDescripcion1(String descripcion1) {
        this.descripcion1 = descripcion1;
    }

    public String getDescripcion2() {
        return descripcion2;
    }

    public void setDescripcion2(String descripcion2) {
        this.descripcion2 = descripcion2;
    }

    public String getDescripcion3() {
        return descripcion3;
    }

    public void setDescripcion3(String descripcion3) {
        this.descripcion3 = descripcion3;
    }

    public String getDescripcion4() {
        return descripcion4;
    }

    public void setDescripcion4(String descripcion4) {
        this.descripcion4 = descripcion4;
    }

    public String getDescripcion5() {
        return descripcion5;
    }

    public void setDescripcion5(String descripcion5) {
        this.descripcion5 = descripcion5;
    }

    public Integer getNumero1() {
        return numero1;
    }

    public void setNumero1(Integer numero1) {
        this.numero1 = numero1;
    }

    public Integer getNumero2() {
        return numero2;
    }

    public void setNumero2(Integer numero2) {
        this.numero2 = numero2;
    }

    public Integer getNumero3() {
        return numero3;
    }

    public void setNumero3(Integer numero3) {
        this.numero3 = numero3;
    }

    public Integer getNumero4() {
        return numero4;
    }

    public void setNumero4(Integer numero4) {
        this.numero4 = numero4;
    }

    public Integer getNumero5() {
        return numero5;
    }

    public void setNumero5(Integer numero5) {
        this.numero5 = numero5;
    }

    public String getFecha1() {
        return fecha1;
    }

    public void setFecha1(String fecha1) {
        this.fecha1 = fecha1;
    }

    public String getFecha2() {
        return fecha2;
    }

    public void setFecha2(String fecha2) {
        this.fecha2 = fecha2;
    }

    public String getFecha3() {
        return fecha3;
    }

    public void setFecha3(String fecha3) {
        this.fecha3 = fecha3;
    }

    public String getFecha4() {
        return fecha4;
    }

    public void setFecha4(String fecha4) {
        this.fecha4 = fecha4;
    }

    public String getFecha5() {
        return fecha5;
    }

    public void setFecha5(String fecha5) {
        this.fecha5 = fecha5;
    }

    public String getCatrelacion() {
        return catrelacion;
    }

    public void setCatrelacion(String catrelacion) {
        this.catrelacion = catrelacion;
    }

    public String getDesccategoria() {
        return desccategoria;
    }

    public void setDesccategoria(String desccategoria) {
        this.desccategoria = desccategoria;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
}
