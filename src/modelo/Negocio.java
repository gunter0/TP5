/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.ArrayList;
import java.util.List;
import org.datacontract.schemas._2004._07.sge_service_contracts.Autorizacion;
import org.tempuri.ILoginService;
import org.tempuri.LoginService;
import vistas.VentanaLogin;
/**
 *
 * @author Fernanda Ponce
 */

public class Negocio {
    public static Negocio instancia = new Negocio();
    private List<Rubro> rubros;
    private List<PuntoVenta> puntosDeVenta;
    private int condicionTributaria;
    private String cuit;
    
    
    private Negocio()
    {
        rubros = new ArrayList<Rubro>();
        puntosDeVenta = new ArrayList<PuntoVenta>();
        condicionTributaria = CondicionTributaria.MONOTRIBUTISTA;
        inicializarPuntoDeVenta();
        inicializarRubros();
    }
   
    public void agregarRubro(Rubro r)
    {
        rubros.add(r);
    }
    public void agregarPuntoDeVenta(PuntoVenta pv)
    {
        puntosDeVenta.add(pv);
    }

    public List<Cajero> GetCajeros()
    {
        List<Cajero> lst = new ArrayList<Cajero>();
        for(PuntoVenta pv : puntosDeVenta)
        {
            lst.add(pv.getCajero());
        }
        return lst;
    }

    public void inicializarPuntoDeVenta()
    {
        Cajero cajero1 = new Cajero("Gunter","Gunter");
        PuntoVenta pv = new PuntoVenta(0001);
        pv.setCajero(cajero1);

        puntosDeVenta.add(pv);
    }

    public void inicializarRubros()
    {
        Rubro rubro0 = new Rubro(0, "BEBIDAS CON ALCOHOL");
        Rubro rubro1 = new Rubro(1, "BEBIDAS SIN ALCOHOL");
        Rubro rubro2 = new Rubro(2, "SANGUCHES");
        Rubro rubro3 = new Rubro(3, "AGREGADOS");

        Producto ep0 = new Producto(0,"COCA-COLA",50,false);
        Producto ep1 = new Producto(1,"FANTA", 50, false);
        Producto ep2 = new Producto(2,"AGUA", 40, false);

        rubro1.agregarProductos(ep0,40);
        rubro1.agregarProductos(ep1,40);
        rubro1.agregarProductos(ep2,40);

        Producto ep3 = new Producto(3, "QUILMES", 40, false);
        Producto ep4 = new Producto(4, "BUDWEISER", 50, false);
        Producto ep5 = new Producto(5, "CORONA", 30, false);

        rubro0.agregarProductos(ep3,40);
        rubro0.agregarProductos(ep4,40);
        rubro0.agregarProductos(ep5,40);

        Producto ep6 = new Producto(6, "MILANESA", 50, false);
        Producto ep7 = new Producto(7, "LOMITO", 40, false);
        Producto ep8 = new Producto(8, "HAMBURGUESA", 30, false);

        rubro2.agregarProductos(ep6,40);
        rubro2.agregarProductos(ep7,40);
        rubro2.agregarProductos(ep8,40);

        Producto ep9 = new Producto(9, "LECHUGA", 0, true);
        Producto ep10 = new Producto(10, "TOMATE", 0, true);
        Producto ep11 = new Producto(11, "CEBOLLA", 0, true);
        Producto ep12 = new Producto(12, "JAMON", 15, true);
        Producto ep13 = new Producto(13, "QUESO", 15, true);
        Producto ep14 = new Producto(14, "HUEVO", 10, true);

        rubro3.agregarProductos(ep9, 9999);
        rubro3.agregarProductos(ep10, 9999);
        rubro3.agregarProductos(ep11, 9999);
        rubro3.agregarProductos(ep12, 9999);
        rubro3.agregarProductos(ep13, 9999);
        rubro3.agregarProductos(ep14, 9999);

        rubros.add(rubro0);
        rubros.add(rubro1);
        rubros.add(rubro2);
        rubros.add(rubro3);
    }

    public Producto buscarProducto(String concepto)
    {
        Producto p = null;
        for(Rubro r : rubros)
        {
            Producto auxiliar = r.buscarProducto(concepto);
            if (auxiliar != null)
                p = auxiliar;
        }
        return p;
    }

    public List<String> getConceptoRubros()
    {
        List<String> listaConceptos = new ArrayList<String>();
        for(Rubro r : rubros)
        {
            if (r != null)
                listaConceptos.add(r.getConcepto());
        }
        return listaConceptos;
    }

    public List<Producto> getEspecificacionesPorRubro(String concepto)
    {
        for(Rubro r : rubros)
        {
            if (r.getConcepto() == concepto)
                return r.getConceptoEspecificaciones();
        }
        return null;
    }
    
    public List<Producto> getEspecificacionesPorRubro(int id)
    {
        for(Rubro r : rubros)
        {
            if (r.getId() == id)
                return r.getConceptoEspecificaciones();
        }
        return null;
    }

    public int getIdRubroPorConcepto(String concepto)
    {
        int id = 0000;
        for(Rubro r : rubros)
        {
            if(r.getConcepto().toUpperCase() == concepto.toUpperCase())
            {
                id = r.getId();
            }
        }
        return id;
    }

    public Autorizacion GetAutorizacion()
    {
        LoginService service = new LoginService();
        ILoginService port = service.getSGEAuthService();
        
        return port.solicitarAutorizacion("6FE40DE4-DAEC-4DBA-9B7A-9609732E9B38");
    }


    public List<Rubro> getRubros() {
        return rubros;
    }

    public void setRubros(List<Rubro> rubros) {
        this.rubros = rubros;
    }

    public List<PuntoVenta> getPuntosDeVenta() {
        return puntosDeVenta;
    }

    public void setPuntosDeVenta(List<PuntoVenta> puntosDeVenta) {
        this.puntosDeVenta = puntosDeVenta;
    }

    public String getCuit() {
        return cuit;
    }

    public void setCuit(String cuit) {
        this.cuit = cuit;
    }

    public Integer getCondicionTributaria() {
        return condicionTributaria;
    }

    public void setCondicionTributaria(int condicionTributaria) {
        this.condicionTributaria = condicionTributaria;
    }

    private static Autorizacion solicitarAutorizacion(java.lang.String codigo) {
        org.tempuri.LoginService service = new org.tempuri.LoginService();
        org.tempuri.ILoginService port = service.getSGEAuthService();
        return port.solicitarAutorizacion(codigo);
    }
    
}
