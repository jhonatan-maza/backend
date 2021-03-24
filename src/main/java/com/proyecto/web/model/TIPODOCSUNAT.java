package com.proyecto.web.model;

/**
 * Created by __Adrian__ on 3/10/2017.
 */
public enum TIPODOCSUNAT {
    FACTURASUNAT("01","FACTURA"),
    BOLETASUNAT("03","BOLETA"),
    NOTACREDITOSUNAT("05","NOTA DE CREDITO"), //05 erp
    NOTADEBITOSUNAT("08","NOTA DE DEBITO"), //06 erp
    RESUMENSUNAT("RC","RESUMEN"),
    BAJASUNAT("RA", "BAJA");
    private String codigo;
    private String descripcion;
    TIPODOCSUNAT(String codigo, String descripcion) {
        this.codigo=codigo;
        this.descripcion=descripcion;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
