/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Fernanda Ponce
 */
public class Cajero {
        private int id;
        private String usuario;
        private String contraseña;
        private List<Pedido> listaPedido;
        private List<Factura> listaFacturas;
        private Turno turno;

        public Cajero(String usuario, String contraseña)
        {
            this.usuario = usuario;
            this.contraseña = contraseña;
            listaPedido = new ArrayList<Pedido>();
            listaFacturas = new ArrayList<Factura>();
        }

        public void registrarFactura(Factura factura)
        {
            listaFacturas.add(factura);
        }

        public Pedido nuevoPedido(List<LineaDePedido> listaProductos)
        {
            Pedido p = new Pedido(listaProductos);
            return p;
        }

        public double finalizarTurno()
        {
            double total = 0;
            for(Factura f : listaFacturas)
            {
                total += f.getTotal();
            }
            return total;
        }

        public String getUsuario() {
            return usuario;
        }

        public void setUsuario(String usuario) {
            this.usuario = usuario;
        }

        public String getContraseña() {
            return contraseña;
        }

        public void setContraseña(String contraseña) {
            this.contraseña = contraseña;
        }    
}
