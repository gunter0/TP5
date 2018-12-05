
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controladores;

import fev1.dif.afip.gov.ar.ArrayOfFECAEDetRequest;
import fev1.dif.afip.gov.ar.FEAuthRequest;
import fev1.dif.afip.gov.ar.FECAEAGetResponse;
import fev1.dif.afip.gov.ar.FECAECabRequest;
import fev1.dif.afip.gov.ar.FECAEDetRequest;
import fev1.dif.afip.gov.ar.FECAERequest;
import fev1.dif.afip.gov.ar.FECAEResponse;
import fev1.dif.afip.gov.ar.FERecuperaLastCbteResponse;
import fev1.dif.afip.gov.ar.Service;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.swing.JOptionPane;
import modelo.Cliente;
import modelo.CondicionTributaria;
import modelo.Configuracion;
import modelo.Factura;
import modelo.Negocio;
import modelo.Pedido;
import modelo.PuntoVenta;
import org.datacontract.schemas._2004._07.sge_service_contracts.Autorizacion;

/**
 *
 * @author Fernanda Ponce
 */

public class controladorComanda {
    
    private IVentanaComanda ventanaComanda;
    private Date fecha = new Date();
    
    public controladorComanda(IVentanaComanda ventanaComanda)
    {
        this.ventanaComanda = ventanaComanda;
    }
    
    public String getFechaActual()
    {
        Date date = new Date();
        DateFormat df = new SimpleDateFormat("yyyyMMdd");
        String fec = df.format(date);              
        return fec;
    }
    
    public int getTipoFactura(String condicionTributariaCliente, String condicionTributariaNegocio)
    {
        if (condicionTributariaCliente.equalsIgnoreCase("RESPONSABLE INSCRIPTO")
         && Integer.parseInt(condicionTributariaNegocio)  == CondicionTributaria.RESPONSABLE_INSCRIPTO)
        {
            return 1;
        }
        else if (Integer.parseInt(condicionTributariaNegocio) == CondicionTributaria.RESPONSABLE_INSCRIPTO &&
                (condicionTributariaCliente.equalsIgnoreCase("MONOTRIBUTISTA") ||
                 condicionTributariaCliente.equalsIgnoreCase("CONSUMIDOR FINAL")))
        {
            return 6;
        }
        else
            return 11;
    }
    public void registrarFactura(int idpv,Factura factura)
    {
        for(PuntoVenta pv : Negocio.instancia.getPuntosDeVenta())
        {
            if (pv.getId() == idpv)
                pv.registrarFactura(factura);
        }
    }
    public void cerrarVentana()
    {
        ventanaComanda.cerrarVentana();
    }
    public void facturar(int idpv,Pedido pedido, String dniCliente, String textoTributarioCliente)
    {
        Cliente cliente = new Cliente(dniCliente, textoTributarioCliente);
        Autorizacion auth = null;
        FEAuthRequest authAFIP = new FEAuthRequest();
        FERecuperaLastCbteResponse ultConect = null;
        FECAEDetRequest DETCae = new FECAEDetRequest();
        FECAECabRequest CABCae = new FECAECabRequest();
        FECAERequest REQCae = new FECAERequest();
        FECAEResponse RESPCae = null;
        ArrayOfFECAEDetRequest ArrayDETCae = new ArrayOfFECAEDetRequest();
        List<FECAEDetRequest> listDETCae = ArrayDETCae.getFECAEDetRequest();
        String authErrServ = "";
               
        try{
            auth = Negocio.instancia.GetAutorizacion();
            authErrServ = auth.getError().getValue();
            
            if (authErrServ != null){
                System.out.println("ERROR EN LA INVOCACIÓN DEL SERVICIO");
                System.out.println("[DETALLE] - " + authErrServ);                
            }
            
            authAFIP.setCuit(auth.getCuit());
            authAFIP.setSign(auth.getSign().getValue());
            authAFIP.setToken(auth.getToken().getValue());
            
            Service servAFIP = new Service();
            int tipoCondTrib = getTipoFactura(textoTributarioCliente, Negocio.instancia.getCondicionTributaria().toString()); 
            ultConect = servAFIP.getServiceSoap().feCompUltimoAutorizado(authAFIP, idpv, tipoCondTrib);
            int ultTran = ultConect.getCbteNro();
            
            DETCae.setDocTipo(Configuracion.docTipoComprador);
            DETCae.setConcepto(Configuracion.concepto);
            DETCae.setMonId(servAFIP.getServiceSoap().feParamGetTiposMonedas(authAFIP).getResultGet().getMoneda().get(0).getId());
            DETCae.setMonCotiz(Configuracion.monedaCotizacion);
            DETCae.setDocNro(Integer.parseInt(dniCliente));
            DETCae.setCbteDesde(ultTran + 1);
            DETCae.setCbteHasta(ultTran + 1);
            DETCae.setCbteFch(getFechaActual());
            DETCae.setImpOpEx(0);
            DETCae.setImpTotConc(0);
            DETCae.setImpTrib(0);
            DETCae.setImpNeto(pedido.getTotal());
            DETCae.setImpTotal(pedido.getTotal());
            DETCae.setImpIVA(0);
            
            CABCae.setCantReg(1);
            CABCae.setCbteTipo(tipoCondTrib);
            CABCae.setPtoVta(idpv);
            
            
            listDETCae.add(DETCae);
            ArrayDETCae.setFecaeDetRequest(listDETCae);
            REQCae.setFeCabReq(CABCae);
            REQCae.setFeDetReq(ArrayDETCae);
                        
            RESPCae = servAFIP.getServiceSoap().fecaeSolicitar(authAFIP, REQCae);
            
            String CAE = RESPCae.getFeDetResp().getFECAEDetResponse().get(0).getCAE();
            Factura factura = new Factura(cliente,pedido,CAE);
            registrarFactura(idpv,factura);
            
            if(!CAE.isEmpty()){
                JOptionPane.showMessageDialog(null, "FACTURA GENERADA : " + CAE + "\n"
                                                   + "IMPORTE : $" + pedido.getTotal(), "FACTURACIÓN",JOptionPane.INFORMATION_MESSAGE );
            }else{
                JOptionPane.showMessageDialog(null,"NO SE PUDO GENERAR LA FACTURA.","ERROR",JOptionPane.ERROR_MESSAGE );
            }
            cerrarVentana();
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        
    }
    
    
}

