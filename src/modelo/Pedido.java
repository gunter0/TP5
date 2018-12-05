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
public class Pedido {
        private List<LineaDePedido> listaLineaPedido;

    public List<LineaDePedido> getListaLineaPedido() {
        return listaLineaPedido;
    }

    public void setListaLineaPedido(List<LineaDePedido> listaLineaPedido) {
        this.listaLineaPedido = listaLineaPedido;
    }
        
        
        public Pedido()
        {
            listaLineaPedido = new ArrayList<LineaDePedido>();
        }
        public Pedido(List<LineaDePedido> listaLineaPedido)
        {
            this.listaLineaPedido = listaLineaPedido;
        }


        public void addLineaPedido(LineaDePedido li)
        {
            if(li != null)
                listaLineaPedido.add(li);
        }

        public double getTotal()
        {
            double total = 0;
            for(LineaDePedido li : listaLineaPedido)
            {
                total += li.getTotal();
            }
            return total;
        }
}
