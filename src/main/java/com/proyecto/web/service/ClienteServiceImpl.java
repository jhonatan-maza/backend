package com.proyecto.web.service;

import com.proyecto.web.dao.ClienteDao;
import com.proyecto.web.model.ClienteRuta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by jonat on 1/03/2020.
 */
@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteDao clienteDao;

    public boolean mantenimientoCliente(Integer idCliente, Integer ubigeo, Integer tipoDoc, String documentoId, String estado, String nombreComercial,
                                        String nombre, String apPaterno, String apMaterno, String sexo, String fechaNac, String celular,String telefono,
                                        String direccion, String referencia, String email, Integer tipoCliente, Integer giroNegocio, Integer codPais,
                                        String usuario, Integer operacion){
        return clienteDao.mantenimientoCliente(idCliente,ubigeo,tipoDoc,documentoId,estado,nombreComercial,
                nombre,apPaterno,apMaterno,sexo,fechaNac,celular,telefono,
                direccion,referencia,email,tipoCliente,giroNegocio,codPais,
                usuario,operacion);
    }

    public List<ClienteRuta> ListarClienteXRuta(Integer idLocalidad, Integer idUsuario, String start, String lenght, String orderBy, Integer operacion){
        return clienteDao.ListarClienteXRuta(idLocalidad,idUsuario,start,lenght,orderBy,operacion);
    }

}
