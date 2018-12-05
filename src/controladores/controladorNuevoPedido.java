/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

/**
 *
 * @author Fernanda Ponce
 */

import modelo.Producto;
import modelo.LineaDePedido;
import modelo.Pedido;
import modelo.Negocio;
import modelo.PuntoVenta;
import java.util.HashSet;

public class controladorNuevoPedido {

    
    IVentanaNuevoPedido ventanaNuevoPedido;

    public controladorNuevoPedido(IVentanaNuevoPedido ventanaNuevoPedido) {
        this.ventanaNuevoPedido = ventanaNuevoPedido;
    }
    
        public void cargarComboRubros()
        {
            ventanaNuevoPedido.cargarComboRubros(Negocio.instancia.getConceptoRubros());
        }

        public void mostrarComanda(int idPv, Pedido p)
        {
            ventanaNuevoPedido.motrarComanda(idPv,p);
        }

        public void cargarTablaProductos(String conceptoRubro)
        {
            int idRubro = Negocio.instancia.getIdRubroPorConcepto(conceptoRubro);

            java.util.List<Producto> listaProductos = 
                    new java.util.ArrayList<Producto>();
            
            listaProductos = Negocio.instancia.getEspecificacionesPorRubro(conceptoRubro);
           
            ventanaNuevoPedido.cargarProductos(listaProductos);
        }
        
        public void cargarComboBoxProductos(String conceptoRubro)
        {
            int idRubro = Negocio.instancia.getIdRubroPorConcepto(conceptoRubro);
            
            java.util.List<Producto> listaProductos = new java.util.ArrayList<Producto>();
            listaProductos = Negocio.instancia.getEspecificacionesPorRubro(conceptoRubro);
            
                    
            
        }
        
        public Pedido nuevoPedido(int IDpv, java.util.ArrayList<LineaDePedido> lineasPedido)
        {
            for (PuntoVenta pv : Negocio.instancia.getPuntosDeVenta())
            {
                if (pv.getId() == IDpv)
                    return pv.nuevoPedido(lineasPedido);
            }
            return null;
        }

        public double calcularSubTotal( java.util.ArrayList<LineaDePedido> lineasPedido)
        {
            double subtotal = 0;
            for (LineaDePedido li : lineasPedido)
            {
                subtotal += li.getTotal();
            }
            return subtotal;
        }

        public double finalizarTurno(int IDpv)
        {
            for (PuntoVenta pv : Negocio.instancia.getPuntosDeVenta())
            {
                if (pv.getId() == IDpv)
                    return pv.finalizarTurno();
            }
            return 0;
        }

}
    

 

