package com.proyecto.web.dao;

//import com.legadofact.service.*;
import com.proyecto.web.model.*;
import org.hibernate.SessionFactory;
import org.hibernate.jdbc.ReturningWork;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jonat on 17/02/2020.
 */
@Repository
public class SendFactDaoImpl implements SendFactDao {
/*
    private static final Logger log = LoggerFactory.getLogger(SendFactDaoImpl.class);
    @Autowired
    private SessionFactory sessionFactory;

    @Value("${nombrePaqueteFACT}")
    protected String nombrePaqueteFACT;

    @Transactional(readOnly = true)
    public List<FeEmisorPerfil> ListarEmisores() {
        return sessionFactory.getCurrentSession().doReturningWork(new ReturningWork<List<FeEmisorPerfil>>() {
            public List<FeEmisorPerfil> execute(Connection connection) throws SQLException {
                try {
                    CallableStatement cst = connection.prepareCall("{ ? = call "+nombrePaqueteFACT+"SERVICIOWEB_EMISOR_LOCAL() }");
                    cst.registerOutParameter(1, Types.OTHER);
                    cst.execute();
                    List<FeEmisorPerfil> lista = new ArrayList<FeEmisorPerfil>();
                    ResultSet rs = (ResultSet) cst.getObject(1);
                    if (rs != null) {
                        while (rs.next()) {
                            FeEmisorPerfil obj = new FeEmisorPerfil();
                            obj.setEmisor(new FeEmisor());
                            obj.getEmisor().setCliEmp(new ClienteEmpresaFact());
                            obj.getEmisor().getCliEmp().setCliente(new Cliente());
                            obj.getEmisor().getCliEmp().getCliente().setIdCliente(rs.getInt(1)); //ID EMPRESA ESTABLEZCO QUERY
                            obj.getEmisor().getCliEmp().getCliente().setDocumentoIde(rs.getString(2));
                            obj.setClaveServicio(rs.getString(3));
                            obj.setFePerfil(new FePerfil());
                            obj.getFePerfil().setDescripcion(rs.getString(4));
                            obj.getFePerfil().setWsdl(rs.getString(5));
                            lista.add(obj);
                        }
                        rs.close();
                        cst.close();
                    }
                    return lista;
                }catch ( Exception e) {
                    log.debug("Ocurrio una excepcion "+e.getMessage());
                    return null;
                }
            }
        });
    }

    @Transactional(readOnly = true)
    public ClienteEmpresaFactDiasPlazo ViewDiasEnvioXEmisor(final String ruc,final String tipoDocumento) {
        return sessionFactory.getCurrentSession().doReturningWork(new ReturningWork<ClienteEmpresaFactDiasPlazo>() {
            public ClienteEmpresaFactDiasPlazo execute(Connection connection) throws SQLException {
                try {
                    CallableStatement cst = connection.prepareCall("{ ? = call "+nombrePaqueteFACT+"DIASPLAZO_X_EMPRESA_TIPODOCUMENTO(?,?) }");
                    cst.registerOutParameter(1, Types.OTHER);
                    cst.setString(2, ruc);
                    cst.setString(3, tipoDocumento);
                    cst.execute();
                    ClienteEmpresaFactDiasPlazo diasPlazo = null;
                    ResultSet rs = (ResultSet) cst.getObject(1);
                    if (rs != null) {
                        while (rs.next()) {
                            diasPlazo = new ClienteEmpresaFactDiasPlazo();
                            diasPlazo.setInicioDias(rs.getInt(1));
                            diasPlazo.setFinalDias(rs.getInt(2));
                        }
                        rs.close();
                        cst.close();
                    }
                    return diasPlazo;
                }catch ( Exception e) {
                    log.debug("Ocurrio una excepcion "+e.getMessage());
                    return null;
                }
            }
        });
    }

    @Transactional(readOnly = true)
    public List<Comprobante> listarCabeceraFacturas(final Integer idEmpresa, final String ruc, final String fechaIni, final String fechaFin, final String estado) {
        return sessionFactory.getCurrentSession().doReturningWork(new ReturningWork<List<Comprobante>>() {
            public List<Comprobante> execute(Connection connection) throws SQLException {
                try {
                    CallableStatement cst = connection.prepareCall("{ ? = call "+nombrePaqueteFACT+"FACT2020_CABECERAFACTURA(?,?,?,?) }");
                    cst.registerOutParameter(1, Types.OTHER);
                    cst.setInt(2,idEmpresa);
                    cst.setObject(3,fechaIni, Types.DATE);
                    cst.setObject(4,fechaFin, Types.DATE);
                    cst.setString(5,estado);
                    cst.execute();

                    Comprobante compSum = new Comprobante();
                    DatosGenerales dgSum=new DatosGenerales();
                    dgSum.setTotalVentaBruta(BigDecimal.ZERO);
                    dgSum.setIgv(BigDecimal.ZERO);
                    dgSum.setGravadas(BigDecimal.ZERO);
                    dgSum.setExoneradas(BigDecimal.ZERO);
                    dgSum.setInafectas(BigDecimal.ZERO);
                    dgSum.setImporteTotalVenta(BigDecimal.ZERO);
                    dgSum.setIvap(BigDecimal.ZERO);

                    ResultSet rs = (ResultSet)cst.getObject(1);
                    List<Comprobante> lista = new ArrayList<Comprobante>();
                    if (rs != null) {
                        while (rs.next()) {
                            Comprobante comprobante = new Comprobante();
                            DatosGenerales dg=new DatosGenerales();

                            comprobante.setRucEmpresa(ruc);
                            Receptor receptor=new Receptor();
                            receptor.setDocCliente(rs.getString("DOC_CLIENTE"));
                            receptor.setTipoDoc(rs.getString("TIPO_DOCCLIENTE"));
                            receptor.setDescCliente(rs.getString("DESC_CLIENTE"));
                            receptor.setDireccion(rs.getString("DIRECCION_CLIENTE"));
                            receptor.setEmail(rs.getString("EMAIL"));
                            comprobante.setReceptor(receptor);

                            dg.setSecuencia(rs.getString("SECUENCIA"));
                            dg.setSerie(rs.getString("SERIE"));
                            dg.setCorrelativo(rs.getString("CORRELATIVO"));
                            dg.setFechaEmision(rs.getString("FECHA_EMISION"));
                            dg.setCodigoOperacion(rs.getString("CODIGO_OPERACION"));
                            dg.setTipoDoc(rs.getString("TIPO_DOCUMENTO"));
                            dg.setTipoMoneda(rs.getString("MONEDA"));
                            dg.setTotalVentaBruta(rs.getBigDecimal("VENTA_BRUTA"));
                            dg.setTotalDctos(rs.getBigDecimal("DCTO_TOTAL"));
                            dg.setIgv(rs.getBigDecimal("IGV_TOTAL"));
                            dg.setGravadas(rs.getBigDecimal("GRAVADAS"));
                            dg.setInafectas(rs.getBigDecimal("INAFECTAS"));
                            dg.setExoneradas(rs.getBigDecimal("EXONERADAS"));
                            dg.setTotalVentaGratuita(rs.getBigDecimal("GRATUITAS"));
                            dg.setImporteTotalVenta(rs.getBigDecimal("MONTO_TOTAL"));
                            dg.setIvap(rs.getBigDecimal("IVAP"));
                            dg.setTipoMonedaSistema(rs.getString("TIPOMONEDA").equals("S/.")?rs.getString("TIPOMONEDA").replace(".",""):rs.getString("TIPOMONEDA"));
                            comprobante.setEstadoSunat(rs.getString("ESTADO_SUNAT"));
                            dg.setTextoMonto(rs.getString("IMPORTEENLETRAS"));

                            dg.setTotalValorVenta(dg.getGravadas().add(dg.getExoneradas()).add(dg.getInafectas()));
                            List<Concepto> conceptos=listaDetallesFacturas(idEmpresa,dg.getSecuencia());
                            for (int i = 0; i <conceptos.size() ; i++) {
                                comprobante.getListaConceptos().add(conceptos.get(i));
                            }
                            dgSum.setTotalVentaBruta(dgSum.getTotalVentaBruta().add(dg.getTotalVentaBruta()));
                            dgSum.setIgv(dgSum.getIgv().add(dg.getIgv()));
                            dgSum.setGravadas(dgSum.getGravadas().add(dg.getGravadas()));
                            dgSum.setExoneradas(dgSum.getExoneradas().add(dg.getExoneradas()));
                            dgSum.setInafectas(dgSum.getInafectas().add(dg.getInafectas()));
                            dgSum.setImporteTotalVenta(dgSum.getImporteTotalVenta().add(dg.getImporteTotalVenta()));
                            dgSum.setIvap(dgSum.getIvap().add(dg.getIvap()));
                            comprobante.setDatosgenerales(dg);
                            lista.add(comprobante);
                        }
                    }

                    compSum.setDatosgenerales(dgSum);
                    lista.add(compSum);
                    rs.close();
                    cst.close();
                    return lista;
                }catch ( Exception e) {
                    log.debug("Ocurrio una excepcion "+e.getMessage());
                    return null;
                }
            }
        });
    }

    @Transactional(readOnly = true)
    public List<Concepto> listaDetallesFacturas(final Integer idEmpresa, final String secuencia) {
        return sessionFactory.getCurrentSession().doReturningWork(new ReturningWork<List<Concepto>>() {
            public List<Concepto> execute(Connection connection) throws SQLException {
                try {
                    CallableStatement cst = connection.prepareCall("{ ? = call "+nombrePaqueteFACT+"FACT2020_DETALLES(?,?,?) }");
                    cst.registerOutParameter(1, Types.OTHER);
                    cst.setInt(2,idEmpresa);
                    cst.setString(3,TIPODOCSUNAT.FACTURASUNAT.getCodigo());
                    cst.setObject(4,secuencia,Types.INTEGER);
                    cst.execute();

                    ResultSet rs = (ResultSet)cst.getObject(1);
                    List<Concepto> lista = new ArrayList<Concepto>();
                    if (rs != null) {
                        while (rs.next()) {
                            Concepto concepto=new Concepto();
                            concepto.setNumeroOrden(rs.getBigDecimal("ORDEN"));
                            concepto.setCodProducto(rs.getString("ID_ARTICULO"));
                            concepto.setUnidadMedida(rs.getString("UNIDAD_MEDIDA"));
                            concepto.setDescripcion(rs.getString("DESCARTICULO"));
                            concepto.setAfectacion(rs.getString("AFECTACION"));
                            concepto.setBonif(rs.getBoolean("BONIF"));
                            concepto.setCantidad(rs.getBigDecimal("CANTIDAD"));
                            concepto.setPrecioVtaUnit(rs.getBigDecimal("PRECIO_VENTA"));
                            concepto.setValorUnitxItem(rs.getBigDecimal("VALOR_UNIT_ITEM"));
                            concepto.setValorVentaBrutaItem(rs.getBigDecimal("VALOR_VTABRUTA"));
                            concepto.setDctosItem(rs.getBigDecimal("DSCTO"));
                            concepto.setValorVentaItem(rs.getBigDecimal("VALOR_VTA_ITEM"));
                            concepto.setIgv(rs.getBigDecimal("IGV"));
                            concepto.setIvap(rs.getBigDecimal("IVAP"));
                            concepto.setTipoIgv(rs.getString("TIPOAFECTACIONIGV"));
                            concepto.setUnidadMedidaSistema(rs.getString("UM_SISTEMA"));
                            lista.add(concepto);
                        }
                        rs.close();
                        cst.close();
                    }
                    return lista;
                }catch ( Exception e) {
                    log.debug("Ocurrio una excepcion "+e.getMessage());
                    return null;
                }
            }
        });
    }

    @Transactional(readOnly = true)
    public List<Comprobante> listarCabeceraBoletas(final Integer idEmpresa, String ruc, final String fechaIni, final String fechaFin, final String estado) {
        return sessionFactory.getCurrentSession().doReturningWork(new ReturningWork<List<Comprobante>>() {
            public List<Comprobante> execute(Connection connection) throws SQLException {
                try {
                    CallableStatement cst = connection.prepareCall("{ ? = call "+nombrePaqueteFACT+"FACT2020_CABECERABOLETA(?,?,?,?) }");
                    cst.registerOutParameter(1, Types.OTHER);
                    cst.setInt(2,idEmpresa);
                    cst.setObject(3,fechaIni, Types.DATE);
                    cst.setObject(4,fechaFin, Types.DATE);
                    cst.setString(5,estado);
                    cst.execute();

                    Comprobante compSum = new Comprobante();
                    DatosGenerales dgSum=new DatosGenerales();
                    dgSum.setTotalVentaBruta(BigDecimal.ZERO);
                    dgSum.setIgv(BigDecimal.ZERO);
                    dgSum.setGravadas(BigDecimal.ZERO);
                    dgSum.setExoneradas(BigDecimal.ZERO);
                    dgSum.setInafectas(BigDecimal.ZERO);
                    dgSum.setImporteTotalVenta(BigDecimal.ZERO);
                    dgSum.setIvap(BigDecimal.ZERO);

                    ResultSet rs = (ResultSet)cst.getObject(1);
                    List<Comprobante> lista = new ArrayList<Comprobante>();
                    if (rs != null) {
                        while (rs.next()) {
                            Comprobante comprobante = new Comprobante();

                            DatosGenerales dg=new DatosGenerales();
                            comprobante.setRucEmpresa(ruc);
                            Receptor receptor=new Receptor();
                            receptor.setDocCliente(rs.getString("DOC_CLIENTE"));
                            receptor.setTipoDoc(rs.getString("TIPO_DOCCLIENTE"));
                            receptor.setDescCliente(rs.getString("DESC_CLIENTE"));
                            receptor.setDireccion(rs.getString("DIRECCION_CLIENTE"));
                            receptor.setEmail(rs.getString("EMAIL"));
                            comprobante.setReceptor(receptor);

                            dg.setSecuencia(rs.getString("SECUENCIA"));
                            dg.setSerie(rs.getString("SERIE"));
                            dg.setCorrelativo(rs.getString("CORRELATIVO"));
                            dg.setFechaEmision(rs.getString("FECHA_EMISION"));

                            dg.setTipoDoc(rs.getString("TIPO_DOCUMENTO"));
                            dg.setTipoMoneda(rs.getString("MONEDA"));
                            dg.setTotalVentaBruta(rs.getBigDecimal("VENTA_BRUTA"));
                            dg.setTotalDctos(rs.getBigDecimal("DCTO_TOTAL"));
                            dg.setIgv(rs.getBigDecimal("IGV_TOTAL"));
                            dg.setGravadas(rs.getBigDecimal("GRAVADAS"));
                            dg.setInafectas(rs.getBigDecimal("INAFECTAS"));
                            dg.setExoneradas(rs.getBigDecimal("EXONERADAS"));
                            dg.setTotalVentaGratuita(rs.getBigDecimal("GRATUITAS"));
                            dg.setImporteTotalVenta(rs.getBigDecimal("MONTO_TOTAL"));
                            dg.setIvap(rs.getBigDecimal("IVAP"));

                            dg.setTipoMonedaSistema(rs.getString("TIPOMONEDA").equals("S/.")?rs.getString("TIPOMONEDA").replace(".",""):rs.getString("TIPOMONEDA"));
                            dg.setTextoMonto(rs.getString("IMPORTEENLETRAS"));

                            dg.setTotalValorVenta(dg.getGravadas().add(dg.getExoneradas()).add(dg.getInafectas()));
                            List<Concepto> conceptos=listaDetallesBoletas(idEmpresa,dg.getSecuencia());
                            for (int i = 0; i <conceptos.size() ; i++) {
                                comprobante.getListaConceptos().add(conceptos.get(i));
                            }
                            dgSum.setTotalVentaBruta(dgSum.getTotalVentaBruta().add(dg.getTotalVentaBruta()));
                            dgSum.setIgv(dgSum.getIgv().add(dg.getIgv()));
                            dgSum.setGravadas(dgSum.getGravadas().add(dg.getGravadas()));
                            dgSum.setExoneradas(dgSum.getExoneradas().add(dg.getExoneradas()));
                            dgSum.setInafectas(dgSum.getInafectas().add(dg.getInafectas()));
                            dgSum.setImporteTotalVenta(dgSum.getImporteTotalVenta().add(dg.getImporteTotalVenta()));
                            dgSum.setIvap(dgSum.getIvap().add(dg.getIvap()));
                            comprobante.setDatosgenerales(dg);
                            lista.add(comprobante);
                        }
                    }
                    compSum.setDatosgenerales(dgSum);
                    lista.add(compSum);
                    rs.close();
                    cst.close();
                    return lista;
                }catch ( Exception e) {
                    log.debug("Ocurrio una excepcion "+e.getMessage());
                    return null;
                }
            }
        });
    }

    @Transactional(readOnly = true)
    public List<Concepto> listaDetallesBoletas(final Integer idEmpresa, final String secuencia) {
        return sessionFactory.getCurrentSession().doReturningWork(new ReturningWork<List<Concepto>>() {
            public List<Concepto> execute(Connection connection) throws SQLException {
                try {
                    CallableStatement cst = connection.prepareCall("{ ? = call "+nombrePaqueteFACT+"FACT2020_DETALLES(?,?,?) }");
                    cst.registerOutParameter(1, Types.OTHER);
                    cst.setInt(2,idEmpresa);
                    cst.setString(3,TIPODOCSUNAT.BOLETASUNAT.getCodigo());
                    cst.setObject(4,secuencia,Types.INTEGER);
                    cst.execute();

                    ResultSet rs = (ResultSet)cst.getObject(1);
                    List<Concepto> lista = new ArrayList<Concepto>();
                    if (rs != null) {
                        while (rs.next()) {
                            Concepto concepto=new Concepto();
                            concepto.setNumeroOrden(rs.getBigDecimal("ORDEN"));
                            concepto.setCodProducto(rs.getString("ID_ARTICULO"));
                            concepto.setUnidadMedida(rs.getString("UNIDAD_MEDIDA"));
                            concepto.setDescripcion(rs.getString("DESCARTICULO"));
                            concepto.setAfectacion(rs.getString("AFECTACION"));
                            concepto.setBonif(rs.getBoolean("BONIF"));
                            concepto.setCantidad(rs.getBigDecimal("CANTIDAD"));
                            concepto.setPrecioVtaUnit(rs.getBigDecimal("PRECIO_VENTA"));
                            concepto.setValorUnitxItem(rs.getBigDecimal("VALOR_UNIT_ITEM"));
                            concepto.setValorVentaBrutaItem(rs.getBigDecimal("VALOR_VTABRUTA"));
                            concepto.setDctosItem(rs.getBigDecimal("DSCTO"));
                            concepto.setValorVentaItem(rs.getBigDecimal("VALOR_VTA_ITEM"));
                            concepto.setIgv(rs.getBigDecimal("IGV"));
                            concepto.setIvap(rs.getBigDecimal("IVAP"));
                            concepto.setTipoIgv(rs.getString("TIPOAFECTACIONIGV"));
                            concepto.setUnidadMedidaSistema(rs.getString("UM_SISTEMA"));
                            lista.add(concepto);
                        }
                        rs.close();
                        cst.close();
                    }
                    return lista;
                }catch ( Exception e) {
                    log.debug("Ocurrio una excepcion "+e.getMessage());
                    return null;
                }
            }
        });
    }

    @Transactional(readOnly = true)
    public List<Comprobante> listarCabeceraNCFacturas(final String ruc, final String fechaIni, final String fechaFin, final String estado) {
        return sessionFactory.getCurrentSession().doReturningWork(new ReturningWork<List<Comprobante>>() {
            public List<Comprobante> execute(Connection connection) throws SQLException {
                try {
                    CallableStatement cst = connection.prepareCall("{ ? = call "+nombrePaqueteFACT+"FACT2020_CABECERAFACTURA(?,?,?,?) }");
                    cst.registerOutParameter(1, Types.OTHER);
                    cst.setString(2,ruc);
                    cst.setString(3,fechaIni);
                    cst.setString(4,fechaFin);
                    cst.setString(5,estado);
                    cst.execute();

                    Comprobante compSum = new Comprobante();
                    DatosGenerales dgSum=new DatosGenerales();
                    dgSum.setTotalVentaBruta(BigDecimal.ZERO);
                    dgSum.setIgv(BigDecimal.ZERO);
                    dgSum.setGravadas(BigDecimal.ZERO);
                    dgSum.setExoneradas(BigDecimal.ZERO);
                    dgSum.setInafectas(BigDecimal.ZERO);
                    dgSum.setImporteTotalVenta(BigDecimal.ZERO);
                    dgSum.setIvap(BigDecimal.ZERO);
                    ResultSet rs = (ResultSet)cst.getObject(1);
                    List<Comprobante> lista = new ArrayList<Comprobante>();
                    if (rs != null) {
                        while (rs.next()) {
                            Comprobante comprobante = new Comprobante();
                            DatosGenerales dg=new DatosGenerales();

                            comprobante.setRucEmpresa(ruc);
                            Receptor receptor=new Receptor();
                            receptor.setDocCliente(rs.getString("DOC_CLIENTE"));
                            receptor.setTipoDoc(rs.getString("TIPO_DOCCLIENTE"));
                            receptor.setDescCliente(rs.getString("DESC_CLIENTE"));
                            receptor.setDireccion(rs.getString("DIRECCION_CLIENTE"));
                            receptor.setEmail(rs.getString("EMAIL"));
                            comprobante.setReceptor(receptor);

                            dg.setSecuencia(rs.getString("SECUENCIA"));
                            dg.setSerie(rs.getString("SERIE"));
                            dg.setCorrelativo(rs.getString("CORRELATIVO"));
                            dg.setFechaEmision(rs.getString("FECHA_EMISION"));
                            dg.setTipoDoc(rs.getString("TIPO_DOCUMENTO"));
                            dg.setTipoMoneda(rs.getString("MONEDA"));
                            dg.setTotalVentaBruta(rs.getBigDecimal("VENTA_BRUTA"));
                            dg.setTotalDctos(rs.getBigDecimal("DCTO_TOTAL"));
                            dg.setIgv(rs.getBigDecimal("IGV_TOTAL"));
                            dg.setGravadas(rs.getBigDecimal("GRAVADAS"));
                            dg.setInafectas(rs.getBigDecimal("INAFECTAS"));
                            dg.setExoneradas(rs.getBigDecimal("EXONERADAS"));
                            dg.setTotalVentaGratuita(rs.getBigDecimal("GRATUITAS"));
                            dg.setImporteTotalVenta(rs.getBigDecimal("MONTOTOTAL"));
                            dg.setIvap(rs.getBigDecimal("IVAP"));
                            dg.setPercepcion(BigDecimal.ZERO);

                            dg.setTipoMonedaSistema(rs.getString("TIPOMONEDA").equals("S/.")?rs.getString("TIPOMONEDA").replace(".",""):rs.getString("TIPOMONEDA"));
                            dg.setTextoMonto(rs.getString("IMPORTEENLETRAS"));

                            DatosNotas datosNotas=new DatosNotas();
                            datosNotas.setSerieRef(rs.getString("SERIE_REF"));
                            datosNotas.setCorrelativoRef(rs.getString("PREIMPRESO_REF"));
                            datosNotas.setTipoDocRef(rs.getString("TIPODOC_REF"));
                            datosNotas.setResponseCode(rs.getString("RESPONSECODE"));
                            datosNotas.setDescMotivo(rs.getString("DESC_MOTIVO"));
                            comprobante.setEstadoSunat(rs.getString("ESTADO_SUNAT"));
                            dg.setTotalValorVenta(dg.getGravadas().add(dg.getExoneradas()).add(dg.getInafectas()));

                            List<Concepto> conceptos=listaDetallesNCFacturas(ruc,dg.getSecuencia());
                            for (int i = 0; i <conceptos.size() ; i++) {
                                comprobante.getListaConceptos().add(conceptos.get(i));
                            }
                            dgSum.setIgv(dgSum.getIgv().add(dg.getIgv()));
                            dgSum.setGravadas(dgSum.getGravadas().add(dg.getGravadas()));
                            dgSum.setExoneradas(dgSum.getExoneradas().add(dg.getExoneradas()));
                            dgSum.setInafectas(dgSum.getInafectas().add(dg.getInafectas()));
                            dgSum.setImporteTotalVenta(dgSum.getImporteTotalVenta().add(dg.getImporteTotalVenta()));
                            dgSum.setTotalVentaBruta(dgSum.getTotalVentaBruta().add(dg.getTotalVentaBruta()));
                            dgSum.setIvap(dgSum.getIvap().add(dg.getIvap()));
                            comprobante.setDatosgenerales(dg);
                            comprobante.setDatosNotas(datosNotas);

                            lista.add(comprobante);

                        }
                    }

                    compSum.setDatosgenerales(dgSum);
                    lista.add(compSum);
                    rs.close();
                    cst.close();
                    return lista;
                }catch ( Exception e) {
                    log.debug("Ocurrio una excepcion "+e.getMessage());
                    return null;
                }
            }
        });
    }

    @Transactional(readOnly = true)
    public List<Concepto> listaDetallesNCFacturas(final String ruc, final String secuencia) {
        return sessionFactory.getCurrentSession().doReturningWork(new ReturningWork<List<Concepto>>() {
            public List<Concepto> execute(Connection connection) throws SQLException {
                try {
                    CallableStatement cst = connection.prepareCall("{ ? = call "+nombrePaqueteFACT+"FACT2020_DETALLES(?,?,?) }");
                    cst.registerOutParameter(1, Types.OTHER);
                    cst.setString(2,ruc);
                    cst.setObject(3,secuencia,Types.INTEGER);
                    cst.setString(4,"05");
                    cst.execute();

                    ResultSet rs = (ResultSet)cst.getObject(1);
                    List<Concepto> lista = new ArrayList<Concepto>();
                    if (rs != null) {
                        while (rs.next()) {
                            Concepto concepto=new Concepto();
                            concepto.setNumeroOrden(rs.getBigDecimal("ORDEN"));
                            concepto.setCodProducto(rs.getString("ID_ARTICULO"));
                            concepto.setUnidadMedida(rs.getString("UNIDAD_MEDIDA"));
                            concepto.setDescripcion(rs.getString("DESCARTICULO"));
                            concepto.setAfectacion(rs.getString("AFECTACION"));
                            concepto.setBonif(rs.getBoolean("BONIF"));
                            concepto.setCantidad(rs.getBigDecimal("CANTIDAD"));
                            concepto.setPrecioVtaUnit(rs.getBigDecimal("PRECIO_VENTA"));
                            concepto.setValorUnitxItem(rs.getBigDecimal("VALOR_UNIT_ITEM"));
                            concepto.setValorVentaBrutaItem(rs.getBigDecimal("VALOR_VTABRUTA"));
                            concepto.setDctosItem(rs.getBigDecimal("DSCTO"));
                            concepto.setValorVentaItem(rs.getBigDecimal("VALOR_VTA_ITEM"));
                            concepto.setIgv(rs.getBigDecimal("IGV"));
                            concepto.setIvap(rs.getBigDecimal("IVAP"));
                            concepto.setTipoIgv(rs.getString("TIPOAFECTACIONIGV"));
                            concepto.setUnidadMedidaSistema(rs.getString("UM_SISTEMA"));
                            lista.add(concepto);
                        }
                        rs.close();
                        cst.close();
                    }
                    return lista;
                }catch ( Exception e) {
                    log.debug("Ocurrio una excepcion "+e.getMessage());
                    return null;
                }
            }
        });
    }

    @Transactional(readOnly = true)
    public List<Comprobante> listarCabeceraNCBoletas(final String ruc, final String fechaIni, final String fechaFin, final String estado) {
        return sessionFactory.getCurrentSession().doReturningWork(new ReturningWork<List<Comprobante>>() {
            public List<Comprobante> execute(Connection connection) throws SQLException {
                try {
                    CallableStatement cst = connection.prepareCall("{ ? = call "+nombrePaqueteFACT+"FACT2020_CABECERAFACTURA(?,?,?,?) }");
                    cst.registerOutParameter(1, Types.OTHER);
                    cst.setString(2,ruc);
                    cst.setString(3,fechaIni);
                    cst.setString(4,fechaFin);
                    cst.setString(5,estado);
                    cst.execute();

                    Comprobante compSum = new Comprobante();
                    DatosGenerales dgSum=new DatosGenerales();
                    dgSum.setTotalVentaBruta(BigDecimal.ZERO);
                    dgSum.setIgv(BigDecimal.ZERO);
                    dgSum.setGravadas(BigDecimal.ZERO);
                    dgSum.setExoneradas(BigDecimal.ZERO);
                    dgSum.setInafectas(BigDecimal.ZERO);
                    dgSum.setImporteTotalVenta(BigDecimal.ZERO);
                    dgSum.setIvap(BigDecimal.ZERO);
                    ResultSet rs = (ResultSet)cst.getObject(1);
                    List<Comprobante> lista = new ArrayList<Comprobante>();
                    if (rs != null) {
                        while (rs.next()) {
                            Comprobante comprobante = new Comprobante();
                            DatosGenerales dg=new DatosGenerales();

                            comprobante.setRucEmpresa(ruc);
                            Receptor receptor=new Receptor();
                            receptor.setDocCliente(rs.getString("DOC_CLIENTE"));
                            receptor.setTipoDoc(rs.getString("TIPO_DOCCLIENTE"));
                            receptor.setDescCliente(rs.getString("DESC_CLIENTE"));
                            receptor.setDireccion(rs.getString("DIRECCION_CLIENTE"));
                            receptor.setEmail(rs.getString("EMAIL"));
                            comprobante.setReceptor(receptor);

                            dg.setSecuencia(rs.getString("SECUENCIA"));
                            dg.setSerie(rs.getString("SERIE"));
                            dg.setCorrelativo(rs.getString("CORRELATIVO"));
                            dg.setFechaEmision(rs.getString("FECHA_EMISION"));
                            dg.setTipoDoc(rs.getString("TIPO_DOCUMENTO"));
                            dg.setTipoMoneda(rs.getString("MONEDA"));
                            dg.setTotalVentaBruta(rs.getBigDecimal("VENTA_BRUTA"));
                            dg.setTotalDctos(rs.getBigDecimal("DCTO_TOTAL"));
                            dg.setIgv(rs.getBigDecimal("IGV_TOTAL"));
                            dg.setGravadas(rs.getBigDecimal("GRAVADAS"));
                            dg.setInafectas(rs.getBigDecimal("INAFECTAS"));
                            dg.setExoneradas(rs.getBigDecimal("EXONERADAS"));
                            dg.setTotalVentaGratuita(rs.getBigDecimal("GRATUITAS"));
                            dg.setImporteTotalVenta(rs.getBigDecimal("MONTOTOTAL"));
                            dg.setIvap(rs.getBigDecimal("IVAP"));
                            dg.setPercepcion(BigDecimal.ZERO);

                            dg.setTipoMonedaSistema(rs.getString("TIPOMONEDA").equals("S/.")?rs.getString("TIPOMONEDA").replace(".",""):rs.getString("TIPOMONEDA"));
                            dg.setTextoMonto(rs.getString("IMPORTEENLETRAS"));

                            DatosNotas datosNotas=new DatosNotas();
                            datosNotas.setSerieRef(rs.getString("SERIE_REF"));
                            datosNotas.setCorrelativoRef(rs.getString("PREIMPRESO_REF"));
                            datosNotas.setTipoDocRef(rs.getString("TIPODOC_REF"));
                            datosNotas.setResponseCode(rs.getString("RESPONSECODE"));
                            datosNotas.setDescMotivo(rs.getString("DESC_MOTIVO"));
                            comprobante.setEstadoSunat(rs.getString("ESTADO_SUNAT"));
                            dg.setTotalValorVenta(dg.getGravadas().add(dg.getExoneradas()).add(dg.getInafectas()));

                            List<Concepto> conceptos=listaDetallesNCBoletas(ruc,dg.getSecuencia());
                            for (int i = 0; i <conceptos.size() ; i++) {
                                comprobante.getListaConceptos().add(conceptos.get(i));
                            }
                            dgSum.setIgv(dgSum.getIgv().add(dg.getIgv()));
                            dgSum.setGravadas(dgSum.getGravadas().add(dg.getGravadas()));
                            dgSum.setExoneradas(dgSum.getExoneradas().add(dg.getExoneradas()));
                            dgSum.setInafectas(dgSum.getInafectas().add(dg.getInafectas()));
                            dgSum.setImporteTotalVenta(dgSum.getImporteTotalVenta().add(dg.getImporteTotalVenta()));
                            dgSum.setTotalVentaBruta(dgSum.getTotalVentaBruta().add(dg.getTotalVentaBruta()));
                            dgSum.setIvap(dgSum.getIvap().add(dg.getIvap()));
                            comprobante.setDatosgenerales(dg);
                            comprobante.setDatosNotas(datosNotas);

                            lista.add(comprobante);

                        }
                    }

                    compSum.setDatosgenerales(dgSum);
                    lista.add(compSum);
                    rs.close();
                    cst.close();
                    return lista;
                }catch ( Exception e) {
                    log.debug("Ocurrio una excepcion "+e.getMessage());
                    return null;
                }
            }
        });
    }

    @Transactional(readOnly = true)
    public List<Concepto> listaDetallesNCBoletas(final String ruc, final String secuencia) {
        return sessionFactory.getCurrentSession().doReturningWork(new ReturningWork<List<Concepto>>() {
            public List<Concepto> execute(Connection connection) throws SQLException {
                try {
                    CallableStatement cst = connection.prepareCall("{ ? = call "+nombrePaqueteFACT+"FACT2020_DETALLES(?,?,?) }");
                    cst.registerOutParameter(1, Types.OTHER);
                    cst.setString(2,ruc);
                    cst.setObject(3,secuencia,Types.INTEGER);
                    cst.setString(4,"05");
                    cst.execute();

                    ResultSet rs = (ResultSet)cst.getObject(1);
                    List<Concepto> lista = new ArrayList<Concepto>();
                    if (rs != null) {
                        while (rs.next()) {
                            Concepto concepto=new Concepto();
                            concepto.setNumeroOrden(rs.getBigDecimal("ORDEN"));
                            concepto.setCodProducto(rs.getString("ID_ARTICULO"));
                            concepto.setUnidadMedida(rs.getString("UNIDAD_MEDIDA"));
                            concepto.setDescripcion(rs.getString("DESCARTICULO"));
                            concepto.setAfectacion(rs.getString("AFECTACION"));
                            concepto.setBonif(rs.getBoolean("BONIF"));
                            concepto.setCantidad(rs.getBigDecimal("CANTIDAD"));
                            concepto.setPrecioVtaUnit(rs.getBigDecimal("PRECIO_VENTA"));
                            concepto.setValorUnitxItem(rs.getBigDecimal("VALOR_UNIT_ITEM"));
                            concepto.setValorVentaBrutaItem(rs.getBigDecimal("VALOR_VTABRUTA"));
                            concepto.setDctosItem(rs.getBigDecimal("DSCTO"));
                            concepto.setValorVentaItem(rs.getBigDecimal("VALOR_VTA_ITEM"));
                            concepto.setIgv(rs.getBigDecimal("IGV"));
                            concepto.setIvap(rs.getBigDecimal("IVAP"));
                            concepto.setTipoIgv(rs.getString("TIPOAFECTACIONIGV"));
                            concepto.setUnidadMedidaSistema(rs.getString("UM_SISTEMA"));
                            lista.add(concepto);
                        }
                        rs.close();
                        cst.close();
                    }
                    return lista;
                }catch ( Exception e) {
                    log.debug("Ocurrio una excepcion "+e.getMessage());
                    return null;
                }
            }
        });
    }

    @Transactional(readOnly = true)
    public List<DetalleResumenDiario> listaDetallesResumenDiario(final Integer idEmpresa, final String fechaEmision) {
        return sessionFactory.getCurrentSession().doReturningWork(new ReturningWork<List<DetalleResumenDiario>>() {
            public List<DetalleResumenDiario> execute(Connection connection) throws SQLException {
                try {
                    CallableStatement cst = connection.prepareCall("{ ? = call "+nombrePaqueteFACT+"FACT2020_CABECERA_BOLETARESUMEN(?,?) }");
                    cst.registerOutParameter(1, Types.OTHER);
                    cst.setInt(2,idEmpresa);
                    cst.setObject(3,fechaEmision, Types.DATE);
                    cst.execute();

                    ResultSet rs = (ResultSet)cst.getObject(1);
                    List<DetalleResumenDiario> lista = new ArrayList<DetalleResumenDiario>();
                    if (rs != null) {
                        while (rs.next()) {
                            DetalleResumenDiario detalleResumenDiario=new DetalleResumenDiario();
                            detalleResumenDiario.setCodEmpresa(idEmpresa.toString());
//                            detalleResumenDiario.setErp(ERP.XRAY.getDescripcion());
                            detalleResumenDiario.setSecuencia(rs.getString("SECUENCIA"));
                            detalleResumenDiario.setTipoDoc(rs.getString("TIPO_DOCUMENTO"));
                            detalleResumenDiario.setSerie(rs.getString("SERIE"));
                            detalleResumenDiario.setCorrelativo(rs.getString("CORRELATIVO"));
                            detalleResumenDiario.setDniCliente(rs.getString("DOC_CLIENTE"));
                            detalleResumenDiario.setTipoDocCliente(rs.getString("TIPO_DOCCLIENTE"));
                            detalleResumenDiario.setMontoVenta(rs.getBigDecimal("MONTO_TOTAL"));
                            detalleResumenDiario.setIgv(rs.getBigDecimal("IGV_TOTAL"));
                            detalleResumenDiario.setGravadas(rs.getBigDecimal("GRAVADAS"));
                            detalleResumenDiario.setInafectas(rs.getBigDecimal("INAFECTAS"));
                            detalleResumenDiario.setExoneradas(rs.getBigDecimal("EXONERADAS"));
                            detalleResumenDiario.setOtrosImpuestos(rs.getBigDecimal("IVAP"));
                            detalleResumenDiario.setEstadoItem(rs.getString("ESTADOITEM"));
                            detalleResumenDiario.setStatusActivo(rs.getString("ESTADOITEM"));
                            if(rs.getString("SERIEREF")=="0"){
                                detalleResumenDiario.setSerieref(null);
                            } else{
                                detalleResumenDiario.setSerieref(rs.getString("SERIEREF"));
                            }
                            if(rs.getString("DOCUMENTOREF")=="0"){
                                detalleResumenDiario.setDocumentoref(null);
                            } else{
                                detalleResumenDiario.setDocumentoref(rs.getString("DOCUMENTOREF"));
                            }
                            detalleResumenDiario.setOtrosCargos(BigDecimal.ZERO);
                            detalleResumenDiario.setIsc(BigDecimal.ZERO);
//                            detalleResumenDiario.setFuenteDatos(ERP.XRAY.getDescripcion());
                            lista.add(detalleResumenDiario);
                        }
                    }
                    rs.close();
                    cst.close();
                    return lista;
                }catch ( Exception e) {
                    log.debug("Ocurrio una excepcion "+e.getMessage());
                    return null;
                }
            }
        });
    }

    @Transactional(readOnly = true)
    public List<DetalleResumenDiario> listaDetallesResumenDiarioBajas(final String ruc, final String fechaEmision) {
        return sessionFactory.getCurrentSession().doReturningWork(new ReturningWork<List<DetalleResumenDiario>>() {
            public List<DetalleResumenDiario> execute(Connection connection) throws SQLException {
                try {
                    CallableStatement cst = connection.prepareCall("{ ? = call "+nombrePaqueteFACT+"FACT2020_CABECERABOLETA(?,?,?,?) }");
                    cst.registerOutParameter(1, Types.OTHER);
                    cst.setString(2,ruc);
                    cst.setString(3,fechaEmision);
                    cst.execute();

                    ResultSet rs = (ResultSet)cst.getObject(1);
                    List<DetalleResumenDiario> lista = new ArrayList<DetalleResumenDiario>();
                    if (rs != null) {
                        while (rs.next()) {
                            DetalleResumenDiario detalleResumenDiarioBaja =new DetalleResumenDiario();
//                            detalleResumenDiarioBaja.setCodEmpresa(codEmpresa);
//                            detalleResumenDiarioBaja.setErp(ERP.XRAY.getDescripcion());
                            detalleResumenDiarioBaja.setSecuencia(rs.getString("SECUENCIA"));
                            detalleResumenDiarioBaja.setTipoDoc(rs.getString("TIPODOC"));
                            detalleResumenDiarioBaja.setSerie(rs.getString("SERIE"));
                            detalleResumenDiarioBaja.setCorrelativo(rs.getString("CORRELATIVO"));
                            detalleResumenDiarioBaja.setDniCliente(rs.getString("DOCCLIENTE"));
                            detalleResumenDiarioBaja.setTipoDocCliente(rs.getString("TIPODOCCLIENTE"));
                            detalleResumenDiarioBaja.setMontoVenta(rs.getBigDecimal("MONTOTOTAL"));
                            detalleResumenDiarioBaja.setIgv(rs.getBigDecimal("IGVTOTAL"));
                            detalleResumenDiarioBaja.setGravadas(rs.getBigDecimal("GRAVADAS"));
                            detalleResumenDiarioBaja.setInafectas(rs.getBigDecimal("INAFECTAS"));
                            detalleResumenDiarioBaja.setExoneradas(rs.getBigDecimal("EXONERADAS"));
                            detalleResumenDiarioBaja.setOtrosImpuestos(rs.getBigDecimal("IVAP"));
                            detalleResumenDiarioBaja.setEstadoItem(rs.getString("ESTADOITEM"));
                            detalleResumenDiarioBaja.setStatusActivo(rs.getString("ESTADOITEM"));
                            detalleResumenDiarioBaja.setSerieref(rs.getString("SERIEREF"));
                            detalleResumenDiarioBaja.setDocumentoref(rs.getString("DOCUMENTOREF"));
                            detalleResumenDiarioBaja.setOtrosCargos(BigDecimal.ZERO);
                            detalleResumenDiarioBaja.setIsc(BigDecimal.ZERO);
//                            detalleResumenDiarioBaja.setFuenteDatos(ERP.XRAY.getDescripcion());
                            lista.add(detalleResumenDiarioBaja);
                        }
                    }
                    rs.close();
                    cst.close();
                    return lista;
                }catch ( Exception e) {
                    log.debug("Ocurrio una excepcion "+e.getMessage());
                    return null;
                }
            }
        });
    }

    @Transactional(readOnly = true)
    public List<DetalleBaja> listaDetallesBajaFacturas(final String ruc, final String fechaAnulacion, final String fechaEmision) {
        return sessionFactory.getCurrentSession().doReturningWork(new ReturningWork<List<DetalleBaja>>() {
            public List<DetalleBaja> execute(Connection connection) throws SQLException {
                try {
                    CallableStatement cst = connection.prepareCall("{ ? = call "+nombrePaqueteFACT+"FACT2020_CABECERABOLETA(?,?,?,?) }");
                    cst.registerOutParameter(1, Types.OTHER);
                    cst.setString(2, ruc);
                    cst.setString(3, fechaAnulacion);
                    cst.setString(4, fechaEmision);
                    cst.execute();

                    ResultSet rs = (ResultSet)cst.getObject(1);
                    List<DetalleBaja> lista = new ArrayList<DetalleBaja>();
                    if (rs != null) {
                        while (rs.next()) {
                            DetalleBaja detalleBaja=new DetalleBaja();
//                            detalleBaja.setCodEmpresa(codEmpresa);
//                            detalleBaja.setErp(ERP.XRAY.getDescripcion());
                            detalleBaja.setNumeroOrden(rs.getInt("ORDEN"));
                            detalleBaja.setTipoDoc(rs.getString("TIPODOC"));
                            detalleBaja.setSerie(rs.getString("SERIE"));
                            detalleBaja.setFechaEmision(rs.getString("FECHAEMISION"));
                            detalleBaja.setSecuencia(rs.getString("SECUENCIA"));
                            detalleBaja.setMotivoBaja(rs.getString("MOTIVO"));
                            detalleBaja.setCorrelativo(rs.getString("CORRELATIVO"));
                            detalleBaja.setTicket(rs.getString("TICKET"));
                            lista.add(detalleBaja);

                        }
                    }

                    rs.close();
                    cst.close();
                    return lista;
                }catch ( Exception e) {
                    log.debug("Ocurrio una excepcion "+e.getMessage());
                    return null;
                }
            }
        });
    }

    @Transactional(readOnly = false)
    public boolean mantenimientoEnvio(final Integer secuencia, final String estadoDoc, final String digestValue, final String respuestaSunat) {
        return  sessionFactory.getCurrentSession().doReturningWork(new ReturningWork<Boolean>() {
            public Boolean execute(Connection connection) throws SQLException {
                try{
                    CallableStatement cst = connection.prepareCall("{ ? = call "+nombrePaqueteFACT+"FACT2020_UPDATEENVIO(?,?,?,?) }");
                    cst.registerOutParameter(1, Types.BOOLEAN);
                    cst.setInt(2, secuencia);
                    cst.setString(3, estadoDoc);
                    cst.setString(4, digestValue);
                    cst.setString(5, respuestaSunat);
                    cst.execute();

                    boolean respuesta = cst.getBoolean(1);
                    cst.close();
                    return respuesta;

                }catch ( Exception e) {
                    log.debug("Ocurrio una excepcion "+e.getMessage());
                    return null;
                }
            }
        });
    }

    @Transactional(readOnly = false)
    public boolean mantenimientoTotalEnvios(final String ruc,final String fechaEmisionIni,final String fechaEmisionFin,final String tipoDoc,final Integer totalDoc,final Integer genExito,final Integer genError,final Integer envExito,final Integer envError,final Integer aceptado,final Integer aceptadoObs,final Integer rechazado,final String descripcion) {
        return  sessionFactory.getCurrentSession().doReturningWork(new ReturningWork<Boolean>() {
            public Boolean execute(Connection connection) throws SQLException {
                try{
                    CallableStatement cst = connection.prepareCall("{ ? = call "+nombrePaqueteFACT+"FACT2020_TOTALENVIOS(?,?,?,?,?,?,?,?,?,?,?,?,?) }");
                    cst.registerOutParameter(1, Types.BOOLEAN);
                    cst.setString(2, ruc);
                    cst.setObject(3, fechaEmisionIni, Types.DATE);
                    cst.setObject(4, fechaEmisionFin, Types.DATE);
                    cst.setString(5, tipoDoc);
                    cst.setInt(6, totalDoc);
                    cst.setInt(7, genExito);
                    cst.setInt(8, genError);
                    cst.setInt(9, envExito);
                    cst.setInt(10, envError);
                    cst.setInt(11, aceptado);
                    cst.setInt(12, aceptadoObs);
                    cst.setInt(13, rechazado);
                    cst.setString(14, descripcion);
                    cst.execute();

                    boolean respuesta = cst.getBoolean(1);
                    cst.close();
                    return respuesta;

                }catch ( Exception e) {
                    log.debug("Ocurrio una excepcion "+e.getMessage());
                    return null;
                }
            }
        });
    }
*/
}
