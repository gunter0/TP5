/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controladores;

import modelo.Cajero;
import modelo.Negocio;
import modelo.PuntoVenta;

/**
 *
 * @author Fernanda Ponce
 */
public class controladorPrincipal {
    IVentanaPrincipal ventanaPrincipal;
    
    public controladorPrincipal(IVentanaPrincipal ventanaPrincipal)
    {
        this.ventanaPrincipal = ventanaPrincipal;
    }
    
    public void finalizarTurno(int id)
    {
        for(PuntoVenta pv : Negocio.instancia.getPuntosDeVenta())
        {
            if (pv.getId() == id)
                 ventanaPrincipal.terminarTurno(pv.getCajero().finalizarTurno());
        }
    }
     public Cajero getCajero(int id)
    {
        for (PuntoVenta pv : Negocio.instancia.getPuntosDeVenta())
        {
            if (pv.getId() == id)
                return pv.getCajero();
        }
        return null;
    }
}
