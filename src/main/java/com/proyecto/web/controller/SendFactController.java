package com.proyecto.web.controller;

//import com.legadofact.service.*;
import com.proyecto.web.model.ClienteEmpresaFactDiasPlazo;
import com.proyecto.web.model.FeEmisorPerfil;
import com.proyecto.web.model.TIPODOCSUNAT;
import com.proyecto.web.model.Usuario;
import com.proyecto.web.security.JwtTokenUtil;
import com.proyecto.web.service.SendFactService;
import com.proyecto.web.service.TicketService;
import com.proyecto.web.service.UserJwtServiceImpl;
import com.proyecto.web.util.UtilCliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by jonat on 17/02/2020.
 */
@RestController
public class SendFactController {

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserJwtServiceImpl userJwtService;

    @Autowired
    private SendFactService sendFactService;

    @Autowired
    private TicketService ticketService;
/*
    List<FeEmisorPerfil> listaEmisoresLocales;
    List<Comprobante> listaFacturas;
    List<Comprobante> listaBoletas;
    List<Comprobante> listaNCFacturas;
    List<Comprobante> listaNCBoletas;

//    private TicketService ticketService;
    List<ComprobanteResumenDiario>  comprobantesResumen;
    List<DetalleResumenDiario> listaResumenDiario;

    @Scheduled(cron = "${cron.factura}")
    public void EnvioFacturas() {
        listaEmisoresLocales = sendFactService.ListarEmisores();

        List<Comprobante> listaTotalDoc = new ArrayList<Comprobante>();
        List<Comprobante> listaGeneradoError = new ArrayList<>();
        List<Comprobante> listaGeneradoExito = new ArrayList<>();
        List<Comprobante> listaEnviarError = new ArrayList<Comprobante>();
        List<Comprobante> listaEnviadosExito = new ArrayList<Comprobante>();
        List<Comprobante> listaAceptados = new ArrayList<Comprobante>();
        List<Comprobante> listaAceptadosObservaciones = new ArrayList<Comprobante>();
        List<Comprobante> listaRechazados = new ArrayList<Comprobante>();

        for (int a = 0; a < listaEmisoresLocales.size(); a++){

            ClienteEmpresaFactDiasPlazo diasPlazo = sendFactService.ViewDiasEnvioXEmisor(listaEmisoresLocales.get(a).getEmisor().getCliEmp().getCliente().getDocumentoIde(),TIPODOCSUNAT.FACTURASUNAT.getCodigo());

            listaFacturas = sendFactService.listarCabeceraFacturas(listaEmisoresLocales.get(a).getEmisor().getCliEmp().getCliente().getIdCliente(),listaEmisoresLocales.get(a).getEmisor().getCliEmp().getCliente().getDocumentoIde(),UtilCliente.getSumarRestarDias(diasPlazo.getInicioDias(),"yyyy-MM-dd"),UtilCliente.getSumarRestarDias(diasPlazo.getFinalDias(),"yyyy-MM-dd"),"NOGENERADO");
            for (int i = 0; i < listaFacturas.size() -1; i++){

                String fechaEmision = listaFacturas.get(i).getDatosgenerales().getFechaEmision();
                String rutaArchivosPdf = UtilCliente.rutaDirectorios(listaEmisoresLocales.get(a).getEmisor().getCliEmp().getCliente().getDocumentoIde(),"ENVIOS",TIPODOCSUNAT.FACTURASUNAT.getCodigo(),fechaEmision);
                String rutaArchivosRespuesta = UtilCliente.rutaDirectorios(listaEmisoresLocales.get(a).getEmisor().getCliEmp().getCliente().getDocumentoIde(),"RESPUESTA",TIPODOCSUNAT.FACTURASUNAT.getCodigo(),fechaEmision);
                RespuestaGenerado rptaGenerado = UtilCliente.generarUbl(listaFacturas.get(i),listaEmisoresLocales.get(a).getClaveServicio(),listaEmisoresLocales.get(a).getFePerfil().getWsdl());

                listaTotalDoc.add(listaFacturas.get(i));
                if (rptaGenerado.getExcepcion() != null) {
                    listaGeneradoError.add(listaFacturas.get(i));
                    String mensaje = "XML/ " + listaFacturas.get(i).getDatosgenerales().getSerie() + "-" + listaFacturas.get(i).getDatosgenerales().getCorrelativo() + " /Excepcion : " + rptaGenerado.getExcepcion();
                    //llenar mensaje porque no se genero XML
                    sendFactService.mantenimientoEnvio(Integer.parseInt(listaFacturas.get(i).getDatosgenerales().getSecuencia()),"PENDIENTE",null,mensaje);
                }
                else{
                    listaFacturas.get(i).setRespuestaGenerado(rptaGenerado);

                    InputStream is = this.getClass().getResourceAsStream(UtilCliente.getRutaJasperFactBolFile(listaEmisoresLocales.get(a).getEmisor().getCliEmp().getCliente().getDocumentoIde()));
                    List<Object> rptaPdf = UtilCliente.generarPDF(listaFacturas.get(i),TIPODOCSUNAT.FACTURASUNAT.getCodigo(),rutaArchivosPdf,is,listaEmisoresLocales.get(a).getEmisor().getCliEmp().getCliente().getDocumentoIde());
                    boolean pdfgenerado = (boolean) rptaPdf.get(0);
                    String pdfmensaje = (String) rptaPdf.get(1);
                    if(pdfgenerado == true){
                        FileOutputStream fosxml = null;
                        try{
                            listaGeneradoExito.add(listaFacturas.get(i));
                            fosxml = new FileOutputStream(rutaArchivosPdf + "\\" + listaFacturas.get(i).getDatosgenerales().getSerie() + "-" + listaFacturas.get(i).getDatosgenerales().getCorrelativo() + ".zip");
                            fosxml.write(rptaGenerado.getArchivo());
                            fosxml.close();
                            String digestValue = listaFacturas.get(i).getRespuestaGenerado().getFileFirmado().getDigestValue();

                            RespuestaSunat rptaSunat = UtilCliente.enviarUbl(listaFacturas.get(i),UtilCliente.getUsuarioLogin(listaEmisoresLocales.get(a).getEmisor().getCliEmp().getCliente().getDocumentoIde(),listaEmisoresLocales.get(a).getFePerfil().getDescripcion()),listaEmisoresLocales.get(a).getFePerfil().getWsdl());
                            if (rptaSunat.getExcepcion() != null) {
                                listaEnviarError.add(listaFacturas.get(i));
                                String mensaje = listaFacturas.get(i).getDatosgenerales().getSerie() + "-" + listaFacturas.get(i).getDatosgenerales().getCorrelativo() + " /Excepcion : " + rptaSunat.getExcepcion();
                                //llenar mensaje porque no se envio
                                sendFactService.mantenimientoEnvio(Integer.parseInt(listaFacturas.get(i).getDatosgenerales().getSecuencia()),"PENDIENTE",null,mensaje);
                            }
                            else{
                                FileOutputStream fos = new FileOutputStream(rutaArchivosRespuesta + "\\R-" + listaFacturas.get(i).getDatosgenerales().getSerie() + "-" + listaFacturas.get(i).getDatosgenerales().getCorrelativo() + ".zip");
                                fos.write(rptaSunat.getArchivozip());
                                fos.close();
                                String estadoSunat = "";
                                if (rptaSunat.getListaAlertas().size() != 0) {
                                    if (rptaSunat.getCodigorpta().equals("0")) {
                                        estadoSunat = "APROBADO(OBS)-" + rptaSunat.getDescripcion();
                                        listaAceptadosObservaciones.add(listaFacturas.get(i));
                                        if(listaFacturas.get(i).getReceptor().getEmail()!=null) {
                                            UtilCliente.enviarCorreo(listaFacturas.get(i).getReceptor().getEmail(),TIPODOCSUNAT.FACTURASUNAT.getCodigo(),rutaArchivosPdf,rutaArchivosRespuesta,listaFacturas.get(i).getDatosgenerales().getSerie(),listaFacturas.get(i).getDatosgenerales().getCorrelativo());
                                        }
                                    }else {
                                        estadoSunat = "RECHAZADO(OBS)-" + rptaSunat.getCodigorpta() + "-" + rptaSunat.getDescripcion();
                                        listaRechazados.add(listaFacturas.get(i));
                                    }
                                }else {
                                    if (rptaSunat.getCodigorpta().equals("0")) {
                                        estadoSunat = "APROBADO-" + rptaSunat.getDescripcion();
                                        listaAceptados.add(listaFacturas.get(i));
                                        if(listaFacturas.get(i).getReceptor().getEmail()!=null) {
                                            UtilCliente.enviarCorreo(listaFacturas.get(i).getReceptor().getEmail(),TIPODOCSUNAT.FACTURASUNAT.getCodigo(),rutaArchivosPdf,rutaArchivosRespuesta,listaFacturas.get(i).getDatosgenerales().getSerie(),listaFacturas.get(i).getDatosgenerales().getCorrelativo());
                                        }
                                    } else {
                                        estadoSunat = "RECHAZADO-" + rptaSunat.getCodigorpta() + "-" + rptaSunat.getDescripcion();
                                        listaRechazados.add(listaFacturas.get(i));
                                    }
                                }
                                sendFactService.mantenimientoEnvio(Integer.parseInt(listaFacturas.get(i).getDatosgenerales().getSecuencia()),"ENVIADO",digestValue,estadoSunat);
                                listaEnviadosExito.add(listaFacturas.get(i));
                            }

                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    else{
                        listaGeneradoError.add(listaFacturas.get(i));
                        String mensaje = "PDF/ " + listaFacturas.get(i).getDatosgenerales().getSerie() + "-" + listaFacturas.get(i).getDatosgenerales().getCorrelativo() + " /Excepcion : " + pdfmensaje;
                        //llenar mensaje porque no se genero PDF
                        sendFactService.mantenimientoEnvio(Integer.parseInt(listaFacturas.get(i).getDatosgenerales().getSecuencia()),"PENDIENTE",null,mensaje);
                    }
                }

            }

            if(listaFacturas.size()>1){
                sendFactService.mantenimientoTotalEnvios(listaEmisoresLocales.get(a).getEmisor().getCliEmp().getCliente().getDocumentoIde(),UtilCliente.getSumarRestarDias(diasPlazo.getInicioDias(),"yyyy-MM-dd"),UtilCliente.getSumarRestarDias(diasPlazo.getFinalDias(),"yyyy-MM-dd"),TIPODOCSUNAT.FACTURASUNAT.getCodigo(),listaTotalDoc.size(),listaGeneradoExito.size(),listaGeneradoError.size(),listaEnviadosExito.size(),listaEnviarError.size(),listaAceptados.size(),listaAceptadosObservaciones.size(),listaRechazados.size(),"ENVIADOS");
            }else{
                sendFactService.mantenimientoTotalEnvios(listaEmisoresLocales.get(a).getEmisor().getCliEmp().getCliente().getDocumentoIde(),UtilCliente.getSumarRestarDias(diasPlazo.getInicioDias(),"yyyy-MM-dd"),UtilCliente.getSumarRestarDias(diasPlazo.getFinalDias(),"yyyy-MM-dd"),TIPODOCSUNAT.FACTURASUNAT.getCodigo(),listaTotalDoc.size(),listaGeneradoExito.size(),listaGeneradoError.size(),listaEnviadosExito.size(),listaEnviarError.size(),listaAceptados.size(),listaAceptadosObservaciones.size(),listaRechazados.size(),"SIN DATA");
            }
            listaTotalDoc.clear();
            listaGeneradoError.clear();
            listaGeneradoExito.clear();
            listaEnviarError.clear();
            listaEnviadosExito.clear();
            listaAceptados.clear();
            listaAceptadosObservaciones.clear();
            listaRechazados.clear();

        }

    }

    @Scheduled(cron = "${cron.gboleta}")
    public void GenerarBoletas(){
        listaEmisoresLocales = sendFactService.ListarEmisores();

        List<Comprobante> listaTotalDoc = new ArrayList<Comprobante>();
        List<Comprobante> listaGeneradoError = new ArrayList<>();
        List<Comprobante> listaGeneradoExito = new ArrayList<>();

        for (int a = 0; a < listaEmisoresLocales.size(); a++){

            ClienteEmpresaFactDiasPlazo diasPlazo = sendFactService.ViewDiasEnvioXEmisor(listaEmisoresLocales.get(a).getEmisor().getCliEmp().getCliente().getDocumentoIde(),TIPODOCSUNAT.BOLETASUNAT.getCodigo());

            listaBoletas = sendFactService.listarCabeceraBoletas(listaEmisoresLocales.get(a).getEmisor().getCliEmp().getCliente().getIdCliente(),listaEmisoresLocales.get(a).getEmisor().getCliEmp().getCliente().getDocumentoIde(),UtilCliente.getSumarRestarDias(diasPlazo.getInicioDias(),"yyyy-MM-dd"),UtilCliente.getSumarRestarDias(diasPlazo.getFinalDias(),"yyyy-MM-dd"),"NOGENERADO");
            for (int i = 0; i < listaBoletas.size() -1; i++){

                String fechaEmision = listaBoletas.get(i).getDatosgenerales().getFechaEmision();
                String rutaArchivosPdf = UtilCliente.rutaDirectorios(listaEmisoresLocales.get(a).getEmisor().getCliEmp().getCliente().getDocumentoIde(),"ENVIOS",TIPODOCSUNAT.BOLETASUNAT.getCodigo(),fechaEmision);
                RespuestaGenerado rptaGenerado = UtilCliente.generarUbl(listaBoletas.get(i),listaEmisoresLocales.get(a).getClaveServicio(),listaEmisoresLocales.get(a).getFePerfil().getWsdl());

                listaTotalDoc.add(listaBoletas.get(i));
                if (rptaGenerado.getExcepcion() != null) {
                    listaGeneradoError.add(listaBoletas.get(i));
                    String mensaje = "XML/ " + listaBoletas.get(i).getDatosgenerales().getSerie() + "-" + listaBoletas.get(i).getDatosgenerales().getCorrelativo() + " /Excepcion : " + rptaGenerado.getExcepcion();
                    //llenar mensaje porque no se genero XML
                    sendFactService.mantenimientoEnvio(Integer.parseInt(listaBoletas.get(i).getDatosgenerales().getSecuencia()),"PENDIENTE",null,mensaje);
                }
                else{
                    listaBoletas.get(i).setRespuestaGenerado(rptaGenerado);

                    InputStream is = this.getClass().getResourceAsStream(UtilCliente.getRutaJasperFactBolFile(listaEmisoresLocales.get(a).getEmisor().getCliEmp().getCliente().getDocumentoIde()));
                    List<Object> rptaPdf = UtilCliente.generarPDF(listaBoletas.get(i),TIPODOCSUNAT.BOLETASUNAT.getCodigo(),rutaArchivosPdf,is,listaEmisoresLocales.get(a).getEmisor().getCliEmp().getCliente().getDocumentoIde());
                    boolean pdfgenerado = (boolean) rptaPdf.get(0);
                    String pdfmensaje = (String) rptaPdf.get(1);
                    if(pdfgenerado == true){
                        FileOutputStream fosxml = null;
                        try{
                            listaGeneradoExito.add(listaBoletas.get(i));
                            fosxml = new FileOutputStream(rutaArchivosPdf + "\\" + listaBoletas.get(i).getDatosgenerales().getSerie() + "-" + listaBoletas.get(i).getDatosgenerales().getCorrelativo() + ".zip");
                            fosxml.write(rptaGenerado.getArchivo());
                            fosxml.close();
                            String digestValue = listaBoletas.get(i).getRespuestaGenerado().getFileFirmado().getDigestValue();

                            if(listaBoletas.get(i).getReceptor().getEmail()!=null) {
                                UtilCliente.enviarCorreo(listaBoletas.get(i).getReceptor().getEmail(),TIPODOCSUNAT.BOLETASUNAT.getCodigo(),rutaArchivosPdf,null,listaBoletas.get(i).getDatosgenerales().getSerie(),listaBoletas.get(i).getDatosgenerales().getCorrelativo());
                            }
                            sendFactService.mantenimientoEnvio(Integer.parseInt(listaBoletas.get(i).getDatosgenerales().getSecuencia()),"GENERADO",digestValue,null);

                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    else{
                        listaGeneradoError.add(listaBoletas.get(i));
                        String mensaje = "PDF/ " + listaBoletas.get(i).getDatosgenerales().getSerie() + "-" + listaBoletas.get(i).getDatosgenerales().getCorrelativo() + " /Excepcion : " + pdfmensaje;
                        //llenar mensaje porque no se genero PDF
                        sendFactService.mantenimientoEnvio(Integer.parseInt(listaBoletas.get(i).getDatosgenerales().getSecuencia()),"PENDIENTE",null,mensaje);
                    }
                }

            }

            if(listaBoletas.size()>1){
                sendFactService.mantenimientoTotalEnvios(listaEmisoresLocales.get(a).getEmisor().getCliEmp().getCliente().getDocumentoIde(),UtilCliente.getSumarRestarDias(diasPlazo.getInicioDias(),"yyyy-MM-dd"),UtilCliente.getSumarRestarDias(diasPlazo.getFinalDias(),"yyyy-MM-dd"),TIPODOCSUNAT.BOLETASUNAT.getCodigo(),listaTotalDoc.size(),listaGeneradoExito.size(),listaGeneradoError.size(),0,0,0,0,0,"ENVIADOS");
            }else{
                sendFactService.mantenimientoTotalEnvios(listaEmisoresLocales.get(a).getEmisor().getCliEmp().getCliente().getDocumentoIde(),UtilCliente.getSumarRestarDias(diasPlazo.getInicioDias(),"yyyy-MM-dd"),UtilCliente.getSumarRestarDias(diasPlazo.getFinalDias(),"yyyy-MM-dd"),TIPODOCSUNAT.BOLETASUNAT.getCodigo(),listaTotalDoc.size(),listaGeneradoExito.size(),listaGeneradoError.size(),0,0,0,0,0,"SIN DATA");
            }
            listaTotalDoc.clear();
            listaGeneradoError.clear();
            listaGeneradoExito.clear();

        }

    }

//    @Scheduled(cron = "${cron.notacfactura}")
//    public void EnvioNotasCreditoFacturas() {
//        listaEmisoresLocales = sendFactService.ListarEmisores();
//
//        List<Comprobante> listaTotalDoc = new ArrayList<Comprobante>();
//        List<Comprobante> listaGeneradoError = new ArrayList<>();
//        List<Comprobante> listaGeneradoExito = new ArrayList<>();
//        List<Comprobante> listaEnviarError = new ArrayList<Comprobante>();
//        List<Comprobante> listaEnviadosExito = new ArrayList<Comprobante>();
//        List<Comprobante> listaAceptados = new ArrayList<Comprobante>();
//        List<Comprobante> listaAceptadosObservaciones = new ArrayList<Comprobante>();
//        List<Comprobante> listaRechazados = new ArrayList<Comprobante>();
//
//        for (int a = 0; a < listaEmisoresLocales.size(); a++){
//
//            listaNCFacturas = sendFactService.listarCabeceraNCFacturas(listaEmisoresLocales.get(a).getIdemisor().getIdempresa().getRuc(),"19-02-2020","19-02-2020","NOGENERADO");
//            for (int i = 0; i < listaNCFacturas.size() -1; i++){
//
//                String fechaEmision = listaNCFacturas.get(i).getDatosgenerales().getFechaEmision();
//                String rutaArchivosPdf = UtilCliente.rutaDirectorios(listaEmisoresLocales.get(a).getIdemisor().getIdempresa().getRuc(),"ENVIOS","05",fechaEmision);
//                String rutaArchivosRespuesta = UtilCliente.rutaDirectorios(listaEmisoresLocales.get(a).getIdemisor().getIdempresa().getRuc(), "RESPUESTA", "05", fechaEmision);
//                RespuestaGenerado rptaGenerado = UtilCliente.generarUbl(listaNCFacturas.get(i),listaEmisoresLocales.get(a).getClaveservicio(),listaEmisoresLocales.get(a).getIdperfil().getWsdl());
//
//                listaTotalDoc.add(listaNCFacturas.get(i));
//                if (rptaGenerado.getExcepcion() != null) {
//                    listaGeneradoError.add(listaNCFacturas.get(i));
//                    String mensaje = listaNCFacturas.get(i).getDatosgenerales().getSerie() + "-" + listaNCFacturas.get(i).getDatosgenerales().getCorrelativo() + " /Excepcion :" + rptaGenerado.getExcepcion();
//                    //llenar mensaje porque no se genero
//                    System.out.println(mensaje);
//                }
//                else{
//                    listaGeneradoExito.add(listaNCFacturas.get(i));
//                    listaNCFacturas.get(i).setRespuestaGenerado(rptaGenerado);
//
//                    InputStream is = this.getClass().getResourceAsStream(UtilCliente.getRutaJasperFactBolFile(listaEmisoresLocales.get(a).getIdemisor().getIdempresa().getRuc()));
//                    List<Object> rptaPdf = UtilCliente.generarPDF(listaNCFacturas.get(i), "NOTACREDITO", rutaArchivosPdf, is,listaEmisoresLocales.get(a).getIdemisor().getIdempresa().getRuc());
//                    boolean pdfgenerado = (boolean) rptaPdf.get(0);
//                    String pdfmensaje = (String) rptaPdf.get(1);
//                    if(pdfgenerado == true){
//                        FileOutputStream fosxml = null;
//                        try{
//                            listaGeneradoExito.add(listaNCFacturas.get(i));
//                            fosxml = new FileOutputStream(rutaArchivosPdf + "\\" + listaNCFacturas.get(i).getDatosgenerales().getSerie() + "-" + listaNCFacturas.get(i).getDatosgenerales().getCorrelativo() + ".zip");
//                            fosxml.write(rptaGenerado.getArchivo());
//                            fosxml.close();
////                        String digestValue = comp.getRespuestaGenerado().getFileFirmado().getDigestValue();
////                        comprobanteService.mantenimientoGeneracion(PanelLogin.usuarioSistema.getCodEmpresa(), erp.equals(ERP.XRAY.getDescripcion()) ? TIPODOCXRAY.FACTURA.getCodigo() : TIPODOCCHESS.FACTURA.getCodigo(), lista.get(i).getDatosgenerales().getSecuencia(), ESTADODOC.GENERADO.getDescripcion(), digestValue);
//
//
////                        RespuestaSunat rptaSunat = UtilCliente.enviarUbl(comp,UtilCliente.getUsuarioLogin("72384351123"));
////                        if (rptaSunat.getExcepcion() != null) {
////                            listaEnviarError.add(comp);
////                            String mensaje = comp.getDatosgenerales().getSerie() + "-" + comp.getDatosgenerales().getCorrelativo() + "/Excepcion :" + rptaSunat.getExcepcion();
////                            //llenar mensaje porque no se envio
////                        }
////                        else{
////                            FileOutputStream fos = new FileOutputStream(rutaArchivosRespuesta + "\\R-" + comp.getDatosgenerales().getSerie() + "-" + comp.getDatosgenerales().getCorrelativo() + ".zip");
////                            fos.write(rptaSunat.getArchivozip());
////                            fos.close();
////                            String estadoSunat = "";
////                            if (rptaSunat.getListaAlertas().size() != 0) {
////                                if (rptaSunat.getCodigorpta().equals("0")) {
////                                    estadoSunat = "APROBADO(OBS)-" + rptaSunat.getDescripcion();
////                                    listaAceptadosObservaciones.add(comp);
////                                    if(comp.getReceptor().getEmail()!=null) {
////                                        UtilCliente.enviarCorreo(comp.getReceptor().getEmail(),rutaArchivosPdf,comp.getDatosgenerales().getSerie(),comp.getDatosgenerales().getCorrelativo());
////                                    }
////                                }else {
////                                    estadoSunat = "RECHAZADO(OBS)-" + rptaSunat.getCodigorpta() + "-" + rptaSunat.getDescripcion();
////                                    listaRechazados.add(comp);
////                                }
////                            }else {
////                                if (rptaSunat.getCodigorpta().equals("0")) {
////                                    estadoSunat = "APROBADO-" + rptaSunat.getDescripcion();
////                                    listaAceptados.add(comp);
////                                    if(comp.getReceptor().getEmail()!=null) {
////                                        UtilCliente.enviarCorreo(comp.getReceptor().getEmail(),rutaArchivosPdf,comp.getDatosgenerales().getSerie(),comp.getDatosgenerales().getCorrelativo());
////                                    }
////                                } else {
////                                    estadoSunat = "RECHAZADO-" + rptaSunat.getCodigorpta() + "-" + rptaSunat.getDescripcion();
////                                    listaRechazados.add(comp);
////                                }
////                            }
////                            System.out.println(estadoSunat);
//////                            listaDocumentoSendService.mantenimientoEnvio(codEmp,tipoDocumento,documentosEnviar.get(i).getDatosgenerales().getSecuencia(),"ENVIADO",estadoSunat,BaseDatos);
////                            listaEnviadosExito.add(comp);
////                        }
//
//                        } catch (FileNotFoundException e) {
//                            e.printStackTrace();
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                    else{
//                        listaGeneradoError.add(listaNCFacturas.get(i));
//                        String mensaje = listaNCFacturas.get(i).getDatosgenerales().getSerie() + "-" + listaNCFacturas.get(i).getDatosgenerales().getCorrelativo() + "/Excepcion :" + pdfmensaje;
//                        //llenar mensaje porque no se genero
//                        System.out.println(mensaje);
//                    }
//                }
//
//            }
//
//        }
//
//    }

//    @Scheduled(cron = "${cron.gnotacboleta}")
//    public void GenerarNotasCreditoBoletas(){
//        listaEmisoresLocales = sendFactService.ListarEmisores();
//
//        List<Comprobante> listaTotalDoc = new ArrayList<Comprobante>();
//        List<Comprobante> listaGeneradoError = new ArrayList<>();
//        List<Comprobante> listaGeneradoExito = new ArrayList<>();
//
//        for (int a = 0; a < listaEmisoresLocales.size(); a++){
//            listaNCBoletas = sendFactService.listarCabeceraNCBoletas(listaEmisoresLocales.get(a).getIdemisor().getIdempresa().getRuc(),"19-02-2020","19-02-2020","NOGENERADO");
//            for (int i = 0; i < listaNCBoletas.size() -1; i++){
//
//                String fechaEmision = listaNCBoletas.get(i).getDatosgenerales().getFechaEmision();
//                String rutaArchivosPdf = UtilCliente.rutaDirectorios(listaEmisoresLocales.get(a).getIdemisor().getIdempresa().getRuc(),"ENVIOS","05",fechaEmision);
//                RespuestaGenerado rptaGenerado = UtilCliente.generarUbl(listaNCBoletas.get(i),listaEmisoresLocales.get(a).getClaveservicio(),listaEmisoresLocales.get(a).getIdperfil().getWsdl());
//
//                listaTotalDoc.add(listaNCBoletas.get(i));
//                if (rptaGenerado.getExcepcion() != null) {
//                    listaGeneradoError.add(listaNCBoletas.get(i));
//                    String mensaje = listaNCBoletas.get(i).getDatosgenerales().getSerie() + "-" + listaNCBoletas.get(i).getDatosgenerales().getCorrelativo() + " /Excepcion :" + rptaGenerado.getExcepcion();
//                    //llenar mensaje porque no se genero
//                    System.out.println(mensaje);
//                }
//                else{
//                    listaGeneradoExito.add(listaNCBoletas.get(i));
//                    listaNCBoletas.get(i).setRespuestaGenerado(rptaGenerado);
//
//                    InputStream is = this.getClass().getResourceAsStream(UtilCliente.getRutaJasperFactBolFile(listaEmisoresLocales.get(a).getIdemisor().getIdempresa().getRuc()));
//                    List<Object> rptaPdf = UtilCliente.generarPDF(listaNCBoletas.get(i), "NOTACREDITO", rutaArchivosPdf, is,listaEmisoresLocales.get(a).getIdemisor().getIdempresa().getRuc());
//                    boolean pdfgenerado = (boolean) rptaPdf.get(0);
//                    String pdfmensaje = (String) rptaPdf.get(1);
//                    if(pdfgenerado == true){
//                        FileOutputStream fosxml = null;
//                        try{
//                            listaGeneradoExito.add(listaNCBoletas.get(i));
//                            fosxml = new FileOutputStream(rutaArchivosPdf + "\\" + listaNCBoletas.get(i).getDatosgenerales().getSerie() + "-" + listaNCBoletas.get(i).getDatosgenerales().getCorrelativo() + ".zip");
//                            fosxml.write(rptaGenerado.getArchivo());
//                            fosxml.close();
//                        } catch (FileNotFoundException e) {
//                            e.printStackTrace();
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                    else{
//                        listaGeneradoError.add(listaNCBoletas.get(i));
//                        String mensaje = listaNCBoletas.get(i).getDatosgenerales().getSerie() + "-" + listaNCBoletas.get(i).getDatosgenerales().getCorrelativo() + "/Excepcion :" + pdfmensaje;
//                        //llenar mensaje porque no se genero
//                        System.out.println(mensaje);
//                    }
//                }
//
//            }
//        }
//    }

    @Scheduled(cron = "${cron.resumenbajaboleta}")
    public void EnviarResumenDiarioBoletas(){

        comprobantesResumen = sendFactService.obtenerResumenDiario(1,"20271522950", "2020-02-24");
        listaResumenDiario = comprobantesResumen.get(0).getListaComprobanteResumen();

        for (int i = 1; i < comprobantesResumen.size(); i++) {
            generarEnviarXmlResumen(comprobantesResumen.get(i),i);
        }


    }

    private void generarEnviarXmlResumen(ComprobanteResumenDiario comprobanteResumen, int i) {

        Integer correlativo = ticketService.getCorrelativoResumenBaja("20271522950","24-02-2020","R");
        String idResumen=comprobanteResumen.getIdResumen().concat("-"+correlativo);
        comprobanteResumen.setIdResumen(idResumen);
        RespuestaGenerado ubl = UtilCliente.generarUblResumenDiario(comprobanteResumen,"wsdl.servicio_sunat_pruebas");
        String rutaArchivos = UtilCliente.rutaDirectorios("20271522950","ENVIOS",TIPODOCSUNAT.RESUMENSUNAT.getCodigo(),"24-02-2020");
        if(ubl.getExcepcion()==null){
            try{
                FileOutputStream fos = new FileOutputStream(rutaArchivos + "\\RESUMEN-" + comprobanteResumen.getIdResumen() + ".zip");
                fos.write(ubl.getArchivo());
                fos.close();

                RespuestaTicket respuestaTicket = UtilCliente.enviarResumen(comprobanteResumen,"wsdl.servicio_sunat_pruebas","20271522950","BETA SUNAT");
                if (respuestaTicket.getExcepcion() != null) {
                    String mensaje = "/Excepcion : " + respuestaTicket.getExcepcion();

                }else{
                    System.out.println("ok");

                }

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            System.out.println("y");
        }

    }
*/
}
