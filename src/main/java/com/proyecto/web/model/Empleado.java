package com.proyecto.web.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by jonat on 25/08/2019.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Empleado {

    @JsonProperty("idempleado")
    private Integer idEmpleado;

    @JsonProperty("perfil")
    private Perfil perfil;

    @JsonProperty("localidad")
    private Localidad localidad;

    @JsonProperty("distrito")
    private Distrito Distrito;

    @JsonProperty("estado")
    private String estado;

    @JsonProperty("nombre")
    private String nombre;

    @JsonProperty("appaterno")
    private String apPaterno;

    @JsonProperty("apmaterno")
    private String apMaterno;

    @JsonProperty("sexo")
    private String sexo;

    @JsonProperty("dni")
    private String dni;

    @JsonProperty("fechanacimiento")
    private String fechaNacimiento;

    @JsonProperty("telefono1")
    private String telefono1;

    @JsonProperty("telefono2")
    private String telefono2;

    @JsonProperty("direccion")
    private String direccion;

    @JsonProperty("email")
    private String email;

    @JsonProperty("imagen")
    private String imagen;

    public Integer getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Integer idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    public Localidad getLocalidad() {
        return localidad;
    }

    public void setLocalidad(Localidad localidad) {
        this.localidad = localidad;
    }

    public com.proyecto.web.model.Distrito getDistrito() {
        return Distrito;
    }

    public void setDistrito(com.proyecto.web.model.Distrito distrito) {
        Distrito = distrito;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
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

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
}
