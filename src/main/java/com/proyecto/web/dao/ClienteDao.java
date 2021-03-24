package com.proyecto.web.dao;

import com.proyecto.web.model.ClienteRuta;

import java.util.List;

/**
 * Created by jonat on 1/03/2020.
 */
public interface ClienteDao {

    boolean mantenimientoCliente(Integer idCliente, Integer ubigeo, Integer tipoDoc, String documentoId, String estado, String nombreComercial,
                                 String nombre, String apPaterno, String apMaterno, String sexo, String fechaNac, String celular,String telefono,
                                 String direccion, String referencia, String email, Integer tipoCliente, Integer giroNegocio, Integer codPais,
                                 String usuario, Integer operacion);

    List<ClienteRuta> ListarClienteXRuta(Integer idLocalidad, Integer idUsuario, String start, String lenght, String orderBy, Integer operacion);

}
