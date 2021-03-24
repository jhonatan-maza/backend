package com.proyecto.web.JRDataSource;

//import com.legadofact.service.Concepto;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jonat on 20/02/2020.
 */
//implements JRDataSource
public class DataSourceReporte  {
/*
    private List<Concepto> listaInvoiceLine = new ArrayList<Concepto>();
    private int indiceinvoiceLineActual = -1;

    public List<Concepto> getListaInvoiceLine() {
        return listaInvoiceLine;
    }

    public void setListaInvoiceLine(List<Concepto> listaInvoiceLine) {
        this.listaInvoiceLine = listaInvoiceLine;
    }

    public void addInvoiceLine(Concepto oInvoiceLine) {

        listaInvoiceLine.add(oInvoiceLine);
    }

    public boolean next() throws JRException {
        return ++indiceinvoiceLineActual < listaInvoiceLine.size();
    }

    public Object getFieldValue(JRField jrf) throws JRException {
        Object valor = null;

        if ("codigo".equals(jrf.getName())) {

            valor = listaInvoiceLine.get(indiceinvoiceLineActual).getCodProducto();

        } else if ("descripcion".equals(jrf.getName())) {

            valor = listaInvoiceLine.get(indiceinvoiceLineActual).getDescripcion();

        }else if ("um".equals(jrf.getName())) {

            valor = listaInvoiceLine.get(indiceinvoiceLineActual).getUnidadMedidaSistema();

        }else if ("dctoitem".equals(jrf.getName())) {

            valor = listaInvoiceLine.get(indiceinvoiceLineActual).getDctosItem();

        }
        else if ("cantidad".equals(jrf.getName())) {

            valor = listaInvoiceLine.get(indiceinvoiceLineActual).getCantidad().intValue();

        } else if ("preciounitario".equals(jrf.getName())) {


            valor = listaInvoiceLine.get(indiceinvoiceLineActual).getValorUnitxItem();
        }  else if ("dcto".equals(jrf.getName())) {


            valor = listaInvoiceLine.get(indiceinvoiceLineActual).getDctosItem();

        }else if ("total".equals(jrf.getName())) {

            valor =listaInvoiceLine.get(indiceinvoiceLineActual).getValorVentaItem();
        }

        return valor;
    }
*/
}
