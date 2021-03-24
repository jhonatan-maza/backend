package com.proyecto.web.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by jonat on 27/02/2020.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Cliente {

    @JsonProperty("idcliente")
    private Integer idCliente;

    @JsonProperty("ubigeo")
    private Distrito ubigeo;

    @JsonProperty("tipodocumento")
    private Tablas tipoDocumento;

    @JsonProperty("documentoide")
    private String documentoIde;

    @JsonProperty("estadocli")
    private Tablas estadoCli;

    @JsonProperty("nombrecomercial")
    private String nombreComercial;

    @JsonProperty("nombre")
    private String nombre;

    @JsonProperty("appaterno")
    private String apPaterno;

    @JsonProperty("apmaterno")
    private String apMaterno;

    @JsonProperty("sexo")
    private String sexo;

    @JsonProperty("fechanacimiento")
    private String fechaNacimiento;

    @JsonProperty("telefono1")
    private String telefono1;

    @JsonProperty("telefono2")
    private String telefono2;

    @JsonProperty("direccion")
    private String direccion;

    @JsonProperty("referencia")
    private String referencia;

    @JsonProperty("email")
    private String email;

    @JsonProperty("usuario")
    private String usuario;

    @JsonProperty("clave")
    private String clave;

    @JsonProperty("tipocliente")
    private Tablas tipoCliente;

    @JsonProperty("gironegocio")
    private Tablas giroNegocio;

    @JsonProperty("codpais")
    private Tablas codpais;

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public Distrito getUbigeo() {
        return ubigeo;
    }

    public void setUbigeo(Distrito ubigeo) {
        this.ubigeo = ubigeo;
    }

    public Tablas getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(Tablas tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getDocumentoIde() {
        return documentoIde;
    }

    public void setDocumentoIde(String documentoIde) {
        this.documentoIde = documentoIde;
    }

    public Tablas getEstadoCli() {
        return estadoCli;
    }

    public void setEstadoCli(Tablas estadoCli) {
        this.estadoCli = estadoCli;
    }

    public String getNombreComercial() {
        return nombreComercial;
    }

    public void setNombreComercial(String nombreComercial) {
        this.nombreComercial = nombreComercial;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApPaterno() {
        return apPaterno;
    }

    public void setApPaterno(String apPaterno) {
        this.apPaterno = apPaterno;
    }

    public String getApMaterno() {
        return apMaterno;
    }

    public void setApMaterno(String apMaterno) {
        this.apMaterno = apMaterno;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getTelefono1() {
        return telefono1;
    }

    public void setTelefono1(String telefono1) {
        this.telefono1 = telefono1;
    }

    public String getTelefono2() {
        return telefono2;
    }

    public void setTelefono2(String telefono2) {
        this.telefono2 = telefono2;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public Tablas getTipoCliente() {
        return tipoCliente;
    }

    public void setTipoCliente(Tablas tipoCliente) {
        this.tipoCliente = tipoCliente;
    }

    public Tablas getGiroNegocio() {
        return giroNegocio;
    }

    public void setGiroNegocio(Tablas giroNegocio) {
        this.giroNegocio = giroNegocio;
    }

    public Tablas getCodpais() {
        return codpais;
    }

    public void setCodpais(Tablas codpais) {
        this.codpais = codpais;
    }
}
