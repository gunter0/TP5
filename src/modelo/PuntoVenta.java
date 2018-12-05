/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.List;

/**
 *
 * @author Fernanda Ponce
 */
public class PuntoVenta {
    private int id;
    private Cajero cajero;

    public PuntoVenta(int id)
    {
        this.id = id;
    }

    public Pedido nuevoPedido(List<LineaDePedido> listaProductos)
    {
        return cajero.nuevoPedido(listaProductos);
    }

    public double finalizarTurno()
    {
        return cajero.finalizarTurno();
    }

    public void registrarFactura(Factura factura)
    {
        cajero.registrarFactura(factura);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cajero getCajero() {
        return cajero;
    }

    public void setCajero(Cajero cajero) {
        this.cajero = cajero;
    }
    
    
}
