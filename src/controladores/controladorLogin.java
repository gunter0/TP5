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
public class controladorLogin {
    IVentanaLogin ventanaLogin;

    public controladorLogin(IVentanaLogin ventanaLogin) {
        this.ventanaLogin = ventanaLogin;
    }
    
    
    public void logear(String usuario, String contraseña)
    {
        for(PuntoVenta pv : Negocio.instancia.getPuntosDeVenta())
        {
            Cajero c = pv.getCajero();
            String c_usu = c.getUsuario();
            String c_pass = c.getContraseña();
            
            if(c_usu.equalsIgnoreCase(usuario) && c_pass.equalsIgnoreCase(contraseña))
            {
                ventanaLogin.abrirVPrincipal(pv);
            }
        }
    }
}
