package com.proyecto.web.util;

//import com.legadofact.service.*;
//import com.proyecto.web.JRDataSource.DataSourceReporte;
//import com.proyecto.web.model.Periodo;
//import net.sf.jasperreports.engine.*;
//
//import javax.mail.Message;
//import javax.mail.Multipart;
//import javax.mail.Session;
//import javax.mail.Transport;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeBodyPart;
//import javax.mail.internet.MimeMessage;
//import javax.mail.internet.MimeMultipart;
//import javax.swing.*;
//import javax.xml.ws.WebServiceException;
//import java.io.File;
//import java.io.IOException;
//import java.io.InputStream;
//import java.math.BigDecimal;
//import java.net.MalformedURLException;
//import java.net.URL;
//import java.text.DateFormat;
//import java.text.SimpleDateFormat;
//import java.util.*;
//import java.util.logging.Level;
//import java.util.logging.Logger;

/**
 * Created by jonat on 19/02/2020.
 */
public class UtilCliente {
/*

    public static UsuarioLogin getUsuarioLogin(String Ruc, String descPerfil) {
        UsuarioLogin usuarioLogin = new UsuarioLogin();
        usuarioLogin.setNumeroRuc(Ruc);
        usuarioLogin.setPerfil(descPerfil);
//        if(Ruc.equals("X")){
//            usuarioLogin.setPerfil("PRODUCCION");
//        }else{
//            usuarioLogin.setPerfil("OSEPRODUCCION");
//        }
        return usuarioLogin;
    }

    public static String getUrlServicio(String wsdl) {
        java.io.InputStream is =   UtilCliente.class.getResourceAsStream("/my.properties");
        java.util.Properties p = new Properties();
        try {
            p.load(is);
            String servicio = p.getProperty(wsdl);
            return servicio;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static RespuestaGenerado generarUbl(Comprobante comprobante,String claveServicio,String wsdl) {
        try {
//            FacturaService_Service sf = new FacturaService_Service(new URL("http://172.16.0.19:8080/oseproduccion/FacturaService?wsdl"));
            FacturaService_Service sf = new FacturaService_Service(new URL(getUrlServicio(wsdl)));
            FacturaService port = sf.getFacturaServicePort();
            comprobante.setUsernamepass(claveServicio);
            return port.transformarUblFirmado(comprobante);
        } catch (MalformedURLException ex) {
            RespuestaGenerado respuestaGenerado=new RespuestaGenerado();
            respuestaGenerado.setExcepcion(ex.getMessage());
            return respuestaGenerado;
        } catch (WebServiceException ex) {
            RespuestaGenerado respuestaGenerado=new RespuestaGenerado();
            respuestaGenerado.setExcepcion(ex.getMessage());
            return respuestaGenerado;
        }
    }

    public static RespuestaSunat enviarUbl(Comprobante comprobante, UsuarioLogin usuario, String wsdl) {
        try {
            FacturaService_Service sf = new FacturaService_Service(new URL(getUrlServicio(wsdl)));
            FacturaService port = sf.getFacturaServicePort();
            return port.sendToSunat(comprobante.getDatosgenerales(), usuario);
        } catch (MalformedURLException ex) {
            RespuestaSunat oRespuestaSunat=new RespuestaSunat();
            oRespuestaSunat.setExcepcion(ex.getMessage());
            return oRespuestaSunat;
        }catch (WebServiceException ex) {
            RespuestaSunat oRespuestaSunat=new RespuestaSunat();
            oRespuestaSunat.setExcepcion(ex.getMessage());
            return oRespuestaSunat;
        }
    }

    public static RespuestaGenerado generarUblResumenDiario(ComprobanteResumenDiario comprobante,String wsdl) {
        try {
            FacturaService_Service sf = new FacturaService_Service(new URL(getUrlServicio(wsdl)));
            FacturaService port = sf.getFacturaServicePort();
            comprobante.setUsernamepass("PRU123");
            return port.generarUblResumen(comprobante);
        } catch (MalformedURLException ex) {
            RespuestaGenerado respuestaGenerado=new RespuestaGenerado();
            respuestaGenerado.setExcepcion(ex.getMessage());
            System.out.println(ex.getMessage());
            return respuestaGenerado;
        }catch (WebServiceException ex) {
            RespuestaGenerado respuestaGenerado=new RespuestaGenerado();
            respuestaGenerado.setExcepcion(ex.getMessage());
            System.out.println(ex.getMessage());
            return respuestaGenerado;
        }
    }

    public static RespuestaTicket enviarResumen(ComprobanteResumenDiario comprobanteResumen, String wsdl, String ruc, String descPerfil) {
        try {
            FacturaService_Service sf = new FacturaService_Service(new URL(getUrlServicio(wsdl)));
            FacturaService port = sf.getFacturaServicePort();
            return port.sendBajaYResumen(null, comprobanteResumen, getUsuarioLogin(ruc,descPerfil));
        } catch (MalformedURLException ex) {
            Logger.getLogger(UtilCliente.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }catch (WebServiceException ex) {
            JOptionPane.showMessageDialog(null,"Error en la comunicacion con el servicio");
            return null;
        }

    }

    public static Periodo getPeriodo(String fecha) {
        Periodo periodo = new Periodo();
        String[] fechaSplit = fecha.split("-");
        String mes = fechaSplit[1];
        String anio = fechaSplit[0];
        String textoMes = "";
        if(mes.equals("01")){
            textoMes = "ENERO";
        }else if(mes.equals("02")){
            textoMes = "FEBRERO";
        }else if(mes.equals("03")){
            textoMes = "MARZO";
        }else if(mes.equals("04")){
            textoMes = "ABRIL";
        }else if(mes.equals("05")){
            textoMes = "MAYO";
        }else if(mes.equals("06")){
            textoMes = "JUNIO";
        }else if(mes.equals("07")){
            textoMes = "JULIO";
        }else if(mes.equals("08")){
            textoMes = "AGOSTO";
        }else if(mes.equals("09")){
            textoMes = "SEPTIEMBRE";
        }else if(mes.equals("10")){
            textoMes = "OCTUBRE";
        }else if(mes.equals("11")){
            textoMes = "NOVIEMBRE";
        }else if(mes.equals("12")){
            textoMes = "DICIEMBRE";
        }
        periodo.setMes(textoMes);
        periodo.setAnnio(anio);
        return periodo;
    }

    public static String rutaDirectorios(String ruc, String contenedor, String tipoDocumento, String fechaEmision) {
        String ruta = "D:\\UBLNUEVO";
        File directorioRaiz = new File(ruta);
        directorioRaiz.mkdir();
        ruta = ruta + "\\" + ruc.replace(" ", "");
        File directorioEmpresa = new File(ruta);
        directorioEmpresa.mkdir();
        ruta = ruta + "\\" + contenedor.replace(" ", "");
        File directorioContenedor = new File(ruta);
        directorioContenedor.mkdir();
        ruta = ruta + "\\" + tipoDocumento.replace(" ", "");
        File directorioTipoDoc = new File(ruta);
        directorioTipoDoc.mkdir();
        Periodo periodo=getPeriodo(fechaEmision);
        ruta = ruta + "\\" + periodo.getAnnio().replace(" ", "");
        File directorioAnnio = new File(ruta);
        directorioAnnio.mkdir();
        ruta = ruta + "\\" + periodo.getMes().replace(" ", "");
        File directorioMes = new File(ruta);
        directorioMes.mkdir();
        ruta = ruta + "\\" + fechaEmision.replace(" ", "");
        File directorioFecha = new File(ruta);
        directorioFecha.mkdir();
        return ruta;
    }

    public static String getRutaJasperFactBolFile(String ruc) {
//        if(ruc.equals("72384351123")){
//            return "/reportes/ReporteGeneral.jasper";
//        }else if(ruc.equals("20531321970")){
//            return "/reportes/ReporteGeneralAlse.jasper";
//        }else if (ruc.equals("20353607783")){
//            return "/reportes/ReporteGeneralChess.jasper";
//        }else{
//            return null;
//        }
        return "/reportes/ReporteGeneral.jasper";
    }

    public static List<Object> generarPDF(Comprobante comprobante, String tipo, String ruta, InputStream str, String RucEmpresa) {
        java.util.List<Object> rpta = new ArrayList<Object>();
        try {
            DataSourceReporte oDataSourceRF = new DataSourceReporte();
            Map parameters = new HashMap();
            parameters.put("idfactura", comprobante.getDatosgenerales().getSerie() + "-" + comprobante.getDatosgenerales().getCorrelativo());
            parameters.put("documento", comprobante.getReceptor().getDocCliente());
            parameters.put("cliente", comprobante.getReceptor().getDescCliente());
            parameters.put("nombreempresa", comprobante.getRespuestaGenerado().getEmisor().getNombCom());
            parameters.put("ruc", RucEmpresa);
            parameters.put("direccion", comprobante.getRespuestaGenerado().getEmisor().getDirecc());
            parameters.put("ciudad", comprobante.getRespuestaGenerado().getEmisor().getDitr());
            parameters.put("fecha", comprobante.getDatosgenerales().getFechaEmision());
            parameters.put("exoneradas", comprobante.getDatosgenerales().getExoneradas());
            parameters.put("inafectas", comprobante.getDatosgenerales().getInafectas());
            parameters.put("gratuitas", comprobante.getDatosgenerales().getTotalVentaGratuita());
            parameters.put("gravadas", comprobante.getDatosgenerales().getGravadas());
            parameters.put("textomonto", comprobante.getDatosgenerales().getTextoMonto());
            parameters.put("igv", comprobante.getDatosgenerales().getIgv());
            parameters.put("referencia", comprobante.getRespuestaGenerado().getFileFirmado().getDigestValue());
            parameters.put("serie", comprobante.getDatosgenerales().getSerie());
            parameters.put("numerodoc", comprobante.getDatosgenerales().getCorrelativo());
            parameters.put("tipodoccliente", comprobante.getReceptor().getTipoDoc());
            parameters.put("doccliente", comprobante.getReceptor().getDocCliente());
            parameters.put("firmadigital", comprobante.getRespuestaGenerado().getFileFirmado().getFirmaDigital());
            parameters.put("tipodocsunat", comprobante.getDatosgenerales().getTipoDoc());
            parameters.put("dirFact", comprobante.getReceptor().getDireccion());
            parameters.put("tipomoneda",comprobante.getDatosgenerales().getTipoMonedaSistema());

//            String filePath = new File("").getAbsolutePath();
//            if(RucEmpresa.equals("72384351123")){
//                parameters.put("urlimagen",filePath.concat("\\src\\main\\resources\\static\\images\\alse.jpg"));
//            }

//            if(BaseDatos.equals("CHESS")){
//
//                String filePath = new File("").getAbsolutePath();
//                if(RucEmpresa.equals("20531321970")){
//                    parameters.put("urlimagen",filePath.concat("\\src\\main\\resources\\imagenes\\alse.jpg"));
//                    parameters.put("totalapagar", comprobante.getDatosgenerales().getImporteTotalVenta());
//
//                }else if (RucEmpresa.equals("20353607783")){
//                    parameters.put("urlimagen",filePath.concat("\\src\\main\\resources\\imagenes\\misa.png"));
//                    parameters.put("percepcion", comprobante.getDatosgenerales().getPercepcion());
//                    //BigDecimal montototal=comprobante.getDatosgenerales().getImportePagar().subtract(comprobante.getDatosgenerales().getPercepcion());
//                    parameters.put("total",comprobante.getDatosgenerales().getBaseImponiblePercepcion());
//                    parameters.put("totalapagar", comprobante.getDatosgenerales().getImportePagar());
//                    if(comprobante.getDatosgenerales().getPercepcion().equals(new BigDecimal("0.00"))){
//                        parameters.put("regimen","01");
//                    }else {
//                        // BigDecimal porcentaje=comprobante.getDatosgenerales().getPercepcion().divide(comprobante.getDatosgenerales().getBaseImponiblePercepcion(),2, RoundingMode.HALF_UP);
//                        parameters.put("regimen", comprobante.getDatosgenerales().getCodRegimen());
//                    }
//                }
//
//
//            }
//            else if(BaseDatos.equals("XRAY")){
//                parameters.put("totalapagar", comprobante.getDatosgenerales().getImporteTotalVenta());
//            }
            parameters.put("totalapagar", comprobante.getDatosgenerales().getImporteTotalVenta());

            if (tipo.equals("01") || tipo.equals("03")) {
                parameters.put("totaldctos", comprobante.getDatosgenerales().getTotalDctos());
            }
            if (tipo.equals("07") || tipo.equals("08")) {
                parameters.put("docref", comprobante.getDatosNotas().getSerieRef() + "-" + comprobante.getDatosNotas().getCorrelativoRef());
            }
            parameters.put(JRParameter.IS_IGNORE_PAGINATION, true);
            parameters.put(JRParameter.REPORT_LOCALE, Locale.US);
            String filename = comprobante.getDatosgenerales().getSerie() + "-" + comprobante.getDatosgenerales().getCorrelativo() + ".pdf";
            //Añadiendo los conceptos
            for (int i = 0; i < comprobante.getListaConceptos().size(); i++) {
                oDataSourceRF.addInvoiceLine(comprobante.getListaConceptos().get(i));
            }
            String rutaPdfFile = ruta + "\\" + filename;

            JasperPrint jasperprint = JasperFillManager.fillReport(str, parameters, oDataSourceRF);
            JasperExportManager.exportReportToPdfFile(jasperprint, rutaPdfFile);
            rpta.add(true);
            rpta.add("Se Genero Correctamente el pdf");
            return rpta;
        } catch (JRException ex) {
            rpta.add(false);
            rpta.add(ex.getMessage());
            return rpta;
        } catch (Exception ex) {
            rpta.add(false);
            rpta.add(ex.getMessage());
            return rpta;
        }
    }

    public static void enviarCorreo(String emailDest,String tipoDocumento,String rutaEnvio,String rutaRespuesta,String serie,String correlativo) {
        // El correo gmail de envío
        String correoEnvia = "jonathan.mazav31@gmail.com";
        //  String claveCorreo = "devilsorangels24";
        String claveCorreo="Valeria1011";
        // La configuración para enviar correo
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.starttls.enable", "true");  //TLS
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.user", correoEnvia);
        properties.put("mail.password", claveCorreo);
        // Obtener la sesion
        Session session = Session.getInstance(properties, null);
        try {
            // Crear el cuerpo del mensaje
            MimeMessage mimeMessage = new MimeMessage(session);
            // Agregar quien envía el correo
            mimeMessage.setFrom(new InternetAddress(correoEnvia, "Factura electronica"));
            // Los destinatarios
            InternetAddress[] internetAddresses = { new InternetAddress(emailDest) };
//            InternetAddress[] internetAddresses = { new InternetAddress("jonathan.mazav31@gmail.com") };
//            InternetAddress[] internetAddresses = {
//                    new InternetAddress("alternando24@gmail.com"),
//                    new InternetAddress("correo2@gmail.com"),
//                    new InternetAddress("correo3@gmail.com") };
            // Agregar los destinatarios al mensaje
            mimeMessage.setRecipients(Message.RecipientType.TO, internetAddresses);
            // Agregar el asunto al correo
            mimeMessage.setSubject("Factura electronica");
            // Creo la parte del mensaje
            MimeBodyPart mimeBodyPart = new MimeBodyPart();
            if(tipoDocumento.equals("01")){
                mimeBodyPart.setText("FACTURA ELECTRÓNICA");
            }else if(tipoDocumento.equals("03")){
                mimeBodyPart.setText("BOLETA ELECTRÓNICA");
            }else if(tipoDocumento.equals("07")){
                mimeBodyPart.setText("NOTA DE CRÉDITO ELECTRÓNICO");
            }else if(tipoDocumento.equals("08")){
                mimeBodyPart.setText("NOTA DE CRÉDITO ELECTRÓNICO");
            }else{
                mimeBodyPart.setText("DOCUMENTO ELECTRÓNICO");
            }
            // Crear el multipart para agregar la parte del mensaje anterior
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(mimeBodyPart);
            String filenamePdf = serie + "-" + correlativo + ".pdf";
            String filenameZip = serie + "-" + correlativo + ".zip";
            String rutaPdfFile = rutaEnvio + "\\" + filenamePdf;
            String rutaZipFile=rutaEnvio+"\\"+filenameZip;

            String filenameZipCDR="";
            String rutaZipFileCDR="";
            if(rutaRespuesta!=null){
                filenameZipCDR = "R-" + serie + "-" + correlativo + ".zip";
                rutaZipFileCDR = rutaRespuesta+"\\"+filenameZipCDR;
            }

            // Agregar el multipart al cuerpo del mensaje
            MimeBodyPart mimeBodyPartAdjunto1 = new MimeBodyPart();
            MimeBodyPart mimeBodyPartAdjunto2 = new MimeBodyPart();
            // String adjunto1 = "D:\\UBLNUEVO\\20353607783\\ENVIOS\\08\\2018\\ENERO\\2018-01-30\\F515-1.zip";
            //String adjunto2 = "D:\\UBLNUEVO\\20353607783\\ENVIOS\\08\\2018\\ENERO\\2018-01-30\\F515-1.pdf";
            mimeBodyPartAdjunto1.attachFile(rutaPdfFile);
            mimeBodyPartAdjunto2.attachFile(rutaZipFile);
            multipart.addBodyPart(mimeBodyPartAdjunto1);
            multipart.addBodyPart(mimeBodyPartAdjunto2);
            if(rutaRespuesta!=null){
                MimeBodyPart mimeBodyPartAdjunto3 = new MimeBodyPart();
                mimeBodyPartAdjunto3.attachFile(rutaZipFileCDR);
                multipart.addBodyPart(mimeBodyPartAdjunto3);
            }
            mimeMessage.setContent(multipart);
//            Multipart multipart = new MimeMultipart("mixed");
//           // for (String str : attachment_PathList) {
//                MimeBodyPart messageBodyPart = new MimeBodyPart();
//                DataSource source = new FileDataSource("D:\\UBLNUEVO\\20353607783\\ENVIOS\\08\\2018\\ENERO\\2018-01-30\\F515-1.zip");
//                messageBodyPart.setDataHandler(new DataHandler(source));
//                messageBodyPart.setFileName(source.getName());
//                multipart.addBodyPart(messageBodyPart);
//            // }
//            msg.setContent(multipart);
//            Transport.send(msg);
            // Enviar el mensaje
            Transport transport = session.getTransport("smtp");
            transport.connect(correoEnvia, claveCorreo);
            transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
            transport.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
//        System.out.println("Correo enviado");
    }

    public static String getSumarRestarDias(Integer dias, String formatoDate){
        Date fechaActual = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fechaActual);
        calendar.add(Calendar.DAY_OF_YEAR,dias);
        DateFormat formatoFecha = new SimpleDateFormat(formatoDate);
//        DateFormat formatoFecha = new SimpleDateFormat("dd-MM-yyyy");
        String fechaX = formatoFecha.format(calendar.getTime());
        return fechaX;
    }

    public static String getSumarRestarMeses(Integer meses, String formatoDate){
        Date fechaActual = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fechaActual);
        calendar.add(Calendar.MONTH,meses);
        DateFormat formatoFecha = new SimpleDateFormat(formatoDate);
//        DateFormat formatoFecha = new SimpleDateFormat("dd-MM-yyyy");
        String fechaX = formatoFecha.format(calendar.getTime());
        return fechaX;
    }

    public static String getSumarRestarAnios(Integer anios, String formatoDate){
        Date fechaActual = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fechaActual);
        calendar.add(Calendar.YEAR,anios);
        DateFormat formatoFecha = new SimpleDateFormat(formatoDate);
//        DateFormat formatoFecha = new SimpleDateFormat("dd-MM-yyyy");
        String fechaX = formatoFecha.format(calendar.getTime());
        return fechaX;
    }

    public static String getFechaActual(String formatoDate){
        Date fechaActual = new Date();
        DateFormat formatoFecha = new SimpleDateFormat(formatoDate);
//        DateFormat formatoFecha = new SimpleDateFormat("dd-MM-yyyy");
        String fechaX = formatoFecha.format(fechaActual);
        return fechaX;
    }

    public static String getHoraActual(String formatoTime){
        Date fechaActual = new Date();
        DateFormat formatoHora = new SimpleDateFormat(formatoTime);
//        DateFormat formatoHora = new SimpleDateFormat("HH:mm:ss");
        String horaX = formatoHora.format(fechaActual);
        return horaX;
    }

    public static String getFechaDiasEnteros(){
        Calendar fecha = Calendar.getInstance();
        int año = fecha.get(Calendar.YEAR);
        int mes = fecha.get(Calendar.MONTH) + 1;
        int dia = fecha.get(Calendar.DAY_OF_MONTH);
        int hora = fecha.get(Calendar.HOUR_OF_DAY);
        int minuto = fecha.get(Calendar.MINUTE);
        int segundo = fecha.get(Calendar.SECOND);
        String fechax = dia + "-" + mes + "-" + año;
        return fechax;
    }

 */
}
