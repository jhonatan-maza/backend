package com.proyecto.web.service;

//import com.legadofact.service.*;
import com.proyecto.web.dao.SendFactDao;
import com.proyecto.web.model.ClienteEmpresaFactDiasPlazo;
import com.proyecto.web.model.FeEmisorPerfil;
import com.proyecto.web.model.TIPODOCSUNAT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jonat on 19/02/2020.
 */
@Service
public class SendFactServiceImpl implements SendFactService {
/*
    @Autowired
    private SendFactDao sendFactDao;

    public List<FeEmisorPerfil> ListarEmisores(){
        return sendFactDao.ListarEmisores();
    }

    public ClienteEmpresaFactDiasPlazo ViewDiasEnvioXEmisor(String ruc, String tipoDocumento){
        return sendFactDao.ViewDiasEnvioXEmisor(ruc,tipoDocumento);
    }

    public List<Comprobante> listarCabeceraFacturas(Integer idEmpresa, String ruc, String fechaIni, String fechaFin, String estado){
        return sendFactDao.listarCabeceraFacturas(idEmpresa,ruc,fechaIni,fechaFin,estado);
    }
    public List<Comprobante> listarCabeceraBoletas(Integer idEmpresa, String ruc, String fechaIni, String fechaFin, String estadoDoc){
        return sendFactDao.listarCabeceraBoletas(idEmpresa,ruc,fechaIni,fechaFin,estadoDoc);
    }
    public List<Comprobante> listarCabeceraNCFacturas(String ruc, String fechaIni, String fechaFin, String estado){
        return sendFactDao.listarCabeceraNCFacturas(ruc,fechaIni,fechaFin,estado);
    }
    public List<Comprobante> listarCabeceraNCBoletas(String ruc, String fechaIni, String fechaFin, String estado){
        return sendFactDao.listarCabeceraNCBoletas(ruc,fechaIni,fechaFin,estado);
    }
    public List<ComprobanteResumenDiario> obtenerResumenDiario(Integer idEmpresa, String ruc, String fechaEmision){
        List<ComprobanteResumenDiario> comprobantesDiarios=new ArrayList<>();
        List<DetalleResumenDiario> detalleResumenDiario=new ArrayList<>();

        detalleResumenDiario = sendFactDao.listaDetallesResumenDiario(idEmpresa, fechaEmision);

        ComprobanteResumenDiario total=new ComprobanteResumenDiario();
        total.getListaComprobanteResumen().addAll(detalleResumenDiario);
        comprobantesDiarios.add(total);//PRIMERO EN LA LISTA

        int contador=0;
        List<DetalleResumenDiario>  detalleResumenDiarioLimit=new ArrayList<>();
        for (int i = 0; i <detalleResumenDiario.size() ; i++) {
            contador++;
            if(contador<500 && i!=(detalleResumenDiario.size()-1)){
                DetalleResumenDiario detalleResumenDiarioCop=detalleResumenDiario.get(i);
                detalleResumenDiarioCop.setNumeroOrden(contador);
                detalleResumenDiarioCop.setEstadoItem("1");
                detalleResumenDiarioLimit.add(detalleResumenDiarioCop);
            }else{
                DetalleResumenDiario detalleResumenDiarioCop=detalleResumenDiario.get(i);
                detalleResumenDiarioCop.setNumeroOrden(contador);
                detalleResumenDiarioCop.setEstadoItem("1");
                detalleResumenDiarioLimit.add(detalleResumenDiarioCop);

                contador=0;
                ComprobanteResumenDiario comprobanteResumenDiario=new ComprobanteResumenDiario();
                comprobanteResumenDiario.setRucEmpresa(ruc);
                comprobanteResumenDiario.setFechaResumen(fechaEmision);
                comprobanteResumenDiario.setFechaReferencia(fechaEmision);
                comprobanteResumenDiario.setTipoDoc(TIPODOCSUNAT.RESUMENSUNAT.getCodigo());
                comprobanteResumenDiario.setTipoMoneda("PEN");
                comprobanteResumenDiario.setIdResumen(TIPODOCSUNAT.RESUMENSUNAT.getCodigo()+"-"+fechaEmision.replace("-", ""));
                comprobanteResumenDiario.getListaComprobanteResumen().addAll(detalleResumenDiarioLimit);
                comprobantesDiarios.add(comprobanteResumenDiario);
                detalleResumenDiarioLimit=new ArrayList<>();
            }
        }
        return comprobantesDiarios;

    }

    public List<ComprobanteResumenDiario>  obtenerResumenDiarioBaja(String ruc, String fechaEmision){
        List<ComprobanteResumenDiario> comprobantesDiarios=new ArrayList<>();
        List<DetalleResumenDiario> detalleResumenDiarioBaja=new ArrayList<>();

        detalleResumenDiarioBaja = sendFactDao.listaDetallesResumenDiarioBajas(ruc, fechaEmision);

        ComprobanteResumenDiario total=new ComprobanteResumenDiario();
        total.getListaComprobanteResumen().addAll(detalleResumenDiarioBaja);
        comprobantesDiarios.add(total);//PRIMERO EN LA LISTA

        int contador=0;
        List<DetalleResumenDiario>  detalleResumenDiarioLimit=new ArrayList<>();
        for (int i = 0; i <detalleResumenDiarioBaja.size() ; i++) {
            contador++;

            if(contador<500 && i!=(detalleResumenDiarioBaja.size()-1)){
                DetalleResumenDiario detalleResumenDiarioAnul=new DetalleResumenDiario();
                detalleResumenDiarioAnul.setCodEmpresa(detalleResumenDiarioBaja.get(i).getCodEmpresa());
                detalleResumenDiarioAnul.setErp(detalleResumenDiarioBaja.get(i).getErp());
                detalleResumenDiarioAnul.setSecuencia(detalleResumenDiarioBaja.get(i).getSecuencia());
                detalleResumenDiarioAnul.setTipoDoc(detalleResumenDiarioBaja.get(i).getTipoDoc());
                detalleResumenDiarioAnul.setSerie(detalleResumenDiarioBaja.get(i).getSerie());
                detalleResumenDiarioAnul.setCorrelativo(detalleResumenDiarioBaja.get(i).getCorrelativo());
                detalleResumenDiarioAnul.setDniCliente(detalleResumenDiarioBaja.get(i).getDniCliente());
                detalleResumenDiarioAnul.setTipoDocCliente(detalleResumenDiarioBaja.get(i).getTipoDocCliente());
                detalleResumenDiarioAnul.setMontoVenta(detalleResumenDiarioBaja.get(i).getMontoVenta());
                detalleResumenDiarioAnul.setIgv(detalleResumenDiarioBaja.get(i).getIgv());
                detalleResumenDiarioAnul.setGravadas(detalleResumenDiarioBaja.get(i).getGravadas());
                detalleResumenDiarioAnul.setInafectas(detalleResumenDiarioBaja.get(i).getInafectas());
                detalleResumenDiarioAnul.setExoneradas(detalleResumenDiarioBaja.get(i).getExoneradas());
                detalleResumenDiarioAnul.setOtrosImpuestos(detalleResumenDiarioBaja.get(i).getOtrosImpuestos());
                detalleResumenDiarioAnul.setEstadoItem("3");
                detalleResumenDiarioAnul.setOtrosCargos(BigDecimal.ZERO);
                detalleResumenDiarioAnul.setIsc(BigDecimal.ZERO);
                detalleResumenDiarioAnul.setFuenteDatos(detalleResumenDiarioBaja.get(i).getFuenteDatos());
                detalleResumenDiarioAnul.setNumeroOrden(contador);
                detalleResumenDiarioLimit.add(detalleResumenDiarioAnul);
            } else{
                DetalleResumenDiario detalleResumenDiarioCop=detalleResumenDiarioBaja.get(i);
                detalleResumenDiarioCop.setNumeroOrden(contador);
                detalleResumenDiarioCop.setEstadoItem("3");
                detalleResumenDiarioLimit.add(detalleResumenDiarioCop);

                contador=0;
                ComprobanteResumenDiario comprobanteResumenDiario=new ComprobanteResumenDiario();
                comprobanteResumenDiario.setRucEmpresa(ruc);
                comprobanteResumenDiario.setFechaResumen(fechaEmision);
                comprobanteResumenDiario.setFechaReferencia(fechaEmision);
                comprobanteResumenDiario.setTipoDoc(TIPODOCSUNAT.RESUMENSUNAT.getCodigo());
                comprobanteResumenDiario.setTipoMoneda("PEN");
                comprobanteResumenDiario.setIdResumen(TIPODOCSUNAT.RESUMENSUNAT.getCodigo()+"-"+fechaEmision.replace("-", ""));
                comprobanteResumenDiario.getListaComprobanteResumen().addAll(detalleResumenDiarioLimit);
                comprobantesDiarios.add(comprobanteResumenDiario);
                detalleResumenDiarioLimit=new ArrayList<>();
            }
        }
        return comprobantesDiarios;

    }

    public ComprobanteBaja obtenerBaja(String ruc, String fechaAnulacion, String fechaEmision) {
        return null;
    }

    public boolean mantenimientoEnvio(Integer secuencia, String estadoDoc, String digestValue, String respuestaSunat){
        return sendFactDao.mantenimientoEnvio(secuencia,estadoDoc,digestValue,respuestaSunat);
    }

    public boolean mantenimientoTotalEnvios(String ruc, String fechaEmisionIni, String fechaEmisionFin, String tipoDoc, Integer totalDoc, Integer genExito, Integer genError, Integer envExito, Integer envError, Integer aceptado, Integer aceptadoObs, Integer rechazado, String descripcion){
        return sendFactDao.mantenimientoTotalEnvios(ruc,fechaEmisionIni,fechaEmisionFin,tipoDoc,totalDoc,genExito,genError,envExito,envError,aceptado,aceptadoObs,rechazado,descripcion);
    }
*/
}
