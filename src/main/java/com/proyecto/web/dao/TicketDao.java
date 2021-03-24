package com.proyecto.web.dao;

/**
 * Created by jonat on 25/02/2020.
 */
public interface TicketDao {

    Integer getCorrelativoResumenBaja(String rucEmpresa,String fechaEmision,String tipoTicket);

}
