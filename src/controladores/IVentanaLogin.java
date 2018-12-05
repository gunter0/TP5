/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controladores;

import modelo.PuntoVenta;

/**
 *
 * @author Fernanda Ponce
 */
public interface IVentanaLogin {
    void logear(String usuario, String contraseña); 
    void abrirVPrincipal(PuntoVenta pv);
}
