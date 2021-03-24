package com.proyecto.web.service;

import com.proyecto.web.dao.TicketDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by jonat on 25/02/2020.
 */
@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    private TicketDao ticketDao;

    public Integer getCorrelativoResumenBaja(String rucEmpresa, String fechaEmision, String tipoTicket) {
        return ticketDao.getCorrelativoResumenBaja(rucEmpresa,fechaEmision,tipoTicket);
    }

}
