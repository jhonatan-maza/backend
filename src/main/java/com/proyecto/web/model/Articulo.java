package com.proyecto.web.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by jonat on 23/10/2019.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Articulo {

    @JsonProperty("idarticulo")
    private Integer idArticulo;

    @JsonProperty("proveedor")
    private Proveedor proveedor;

    @JsonProperty("descarticulo")
    private String descArticulo;

    @JsonProperty("descfactura")
    private String descFactura;

    @JsonProperty("umcompra")
    private UnidadMedida umCompra;

    @JsonProperty("umventa")
    private UnidadMedida umVenta;

    @JsonProperty("umstock")
    private UnidadMedida umStock;

    @JsonProperty("familia")
    private JerarqFamilia familia;

    @JsonProperty("clase")
    private JerarqClase clase;

    @JsonProperty("subclase")
    private JerarqSubClase subClase;

    @JsonProperty("categoria")
    private JerarqCategoria categoria;

    @JsonProperty("marca")
    private JerarqMarca marca;

    @JsonProperty("tipo")
    private JerarqTipo tipo;

    @JsonProperty("subtipo")
    private JerarqSubTipo subTipo;

    @JsonProperty("variedad")
    private JerarqVariedad variedad;

    @JsonProperty("presentacion")
    private JerarqPresentacion presentacion;

    @JsonProperty("vidautil")
    private Integer vidaUtil;

    @JsonProperty("codbarras")
    private String codBarras;

    @JsonProperty("isc")
    private Double isc;

    @JsonProperty("igv")
    private Double igv;

    @JsonProperty("percepcion")
    private Double percepcion;

    @JsonProperty("ivap")
    private Double ivap;

    @JsonProperty("tiporotacion")
    private Tablas tipoRotacion;

    @JsonProperty("tipoarticulo")
    private Tablas tipoArticulo;

    @JsonProperty("almacenalto")
    private Integer almacenAlto;

    @JsonProperty("almacenlargo")
    private Integer almacenLargo;

    @JsonProperty("almacenancho")
    private Integer almacenAncho;

    @JsonProperty("almacenpeso")
    private Integer almacenPeso;

    @JsonProperty("distribuiralto")
    private Integer distribuirAlto;

    @JsonProperty("distribuirlargo")
    private Integer distribuirLargo;

    @JsonProperty("distribuirancho")
    private Integer distribuirAncho;

    @JsonProperty("distribuirpeso")
    private Integer distribuirPeso;

    @JsonProperty("apilarbase")
    private Integer apilarBase;

    @JsonProperty("apilaralto")
    private Integer apilarAlto;

    @JsonProperty("metroscubbase")
    private Integer metrosCubBase;

    @JsonProperty("metroscubalto")
    private Integer metrosCubAlto;

    @JsonProperty("observaciones")
    private String observaciones;

    @JsonProperty("estado")
    private String estado;

    public Integer getIdArticulo() {
        return idArticulo;
    }

    public void setIdArticulo(Integer idArticulo) {
        this.idArticulo = idArticulo;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public String getDescArticulo() {
        return descArticulo;
    }

    public void setDescArticulo(String descArticulo) {
        this.descArticulo = descArticulo;
    }

    public String getDescFactura() {
        return descFactura;
    }

    public void setDescFactura(String descFactura) {
        this.descFactura = descFactura;
    }

    public UnidadMedida getUmCompra() {
        return umCompra;
    }

    public void setUmCompra(UnidadMedida umCompra) {
        this.umCompra = umCompra;
    }

    public UnidadMedida getUmVenta() {
        return umVenta;
    }

    public void setUmVenta(UnidadMedida umVenta) {
        this.umVenta = umVenta;
    }

    public UnidadMedida getUmStock() {
        return umStock;
    }

    public void setUmStock(UnidadMedida umStock) {
        this.umStock = umStock;
    }

    public JerarqFamilia getFamilia() {
        return familia;
    }

    public void setFamilia(JerarqFamilia familia) {
        this.familia = familia;
    }

    public JerarqClase getClase() {
        return clase;
    }

    public void setClase(JerarqClase clase) {
        this.clase = clase;
    }

    public JerarqSubClase getSubClase() {
        return subClase;
    }

    public void setSubClase(JerarqSubClase subClase) {
        this.subClase = subClase;
    }

    public JerarqCategoria getCategoria() {
        return categoria;
    }

    public void setCategoria(JerarqCategoria categoria) {
        this.categoria = categoria;
    }

    public JerarqMarca getMarca() {
        return marca;
    }

    public void setMarca(JerarqMarca marca) {
        this.marca = marca;
    }

    public JerarqTipo getTipo() {
        return tipo;
    }

    public void setTipo(JerarqTipo tipo) {
        this.tipo = tipo;
    }

    public JerarqSubTipo getSubTipo() {
        return subTipo;
    }

    public void setSubTipo(JerarqSubTipo subTipo) {
        this.subTipo = subTipo;
    }

    public JerarqVariedad getVariedad() {
        return variedad;
    }

    public void setVariedad(JerarqVariedad variedad) {
        this.variedad = variedad;
    }

    public JerarqPresentacion getPresentacion() {
        return presentacion;
    }

    public void setPresentacion(JerarqPresentacion presentacion) {
        this.presentacion = presentacion;
    }

    public Integer getVidaUtil() {
        return vidaUtil;
    }

    public void setVidaUtil(Integer vidaUtil) {
        this.vidaUtil = vidaUtil;
    }

    public String getCodBarras() {
        return codBarras;
    }

    public void setCodBarras(String codBarras) {
        this.codBarras = codBarras;
    }

    public Double getIsc() {
        return isc;
    }

    public void setIsc(Double isc) {
        this.isc = isc;
    }

    public Double getIgv() {
        return igv;
    }

    public void setIgv(Double igv) {
        this.igv = igv;
    }

    public Double getPercepcion() {
        return percepcion;
    }

    public void setPercepcion(Double percepcion) {
        this.percepcion = percepcion;
    }

    public Double getIvap() {
        return ivap;
    }

    public void setIvap(Double ivap) {
        this.ivap = ivap;
    }

    public Tablas getTipoRotacion() {
        return tipoRotacion;
    }

    public void setTipoRotacion(Tablas tipoRotacion) {
        this.tipoRotacion = tipoRotacion;
    }

    public Tablas getTipoArticulo() {
        return tipoArticulo;
    }

    public void setTipoArticulo(Tablas tipoArticulo) {
        this.tipoArticulo = tipoArticulo;
    }

    public Integer getAlmacenAlto() {
        return almacenAlto;
    }

    public void setAlmacenAlto(Integer almacenAlto) {
        this.almacenAlto = almacenAlto;
    }

    public Integer getAlmacenLargo() {
        return almacenLargo;
    }

    public void setAlmacenLargo(Integer almacenLargo) {
        this.almacenLargo = almacenLargo;
    }

    public Integer getAlmacenAncho() {
        return almacenAncho;
    }

    public void setAlmacenAncho(Integer almacenAncho) {
        this.almacenAncho = almacenAncho;
    }

    public Integer getAlmacenPeso() {
        return almacenPeso;
    }

    public void setAlmacenPeso(Integer almacenPeso) {
        this.almacenPeso = almacenPeso;
    }

    public Integer getDistribuirAlto() {
        return distribuirAlto;
    }

    public void setDistribuirAlto(Integer distribuirAlto) {
        this.distribuirAlto = distribuirAlto;
    }

    public Integer getDistribuirLargo() {
        return distribuirLargo;
    }

    public void setDistribuirLargo(Integer distribuirLargo) {
        this.distribuirLargo = distribuirLargo;
    }

    public Integer getDistribuirAncho() {
        return distribuirAncho;
    }

    public void setDistribuirAncho(Integer distribuirAncho) {
        this.distribuirAncho = distribuirAncho;
    }

    public Integer getDistribuirPeso() {
        return distribuirPeso;
    }

    public void setDistribuirPeso(Integer distribuirPeso) {
        this.distribuirPeso = distribuirPeso;
    }

    public Integer getApilarBase() {
        return apilarBase;
    }

    public void setApilarBase(Integer apilarBase) {
        this.apilarBase = apilarBase;
    }

    public Integer getApilarAlto() {
        return apilarAlto;
    }

    public void setApilarAlto(Integer apilarAlto) {
        this.apilarAlto = apilarAlto;
    }

    public Integer getMetrosCubBase() {
        return metrosCubBase;
    }

    public void setMetrosCubBase(Integer metrosCubBase) {
        this.metrosCubBase = metrosCubBase;
    }

    public Integer getMetrosCubAlto() {
        return metrosCubAlto;
    }

    public void setMetrosCubAlto(Integer metrosCubAlto) {
        this.metrosCubAlto = metrosCubAlto;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
