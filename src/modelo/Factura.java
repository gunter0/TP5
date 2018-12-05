/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author Fernanda Ponce
 */
public class Factura {
    private Cliente cliente;
    private Pedido pedido;
    private String cae;

    public Factura(Cliente cliente, Pedido pedido, String cae)
    {
        this.cliente = cliente;
        this.pedido = pedido;
        this.cae = cae;
    }

    public double getTotal()
    {
        return pedido.getTotal();
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public String getCae() {
        return cae;
    }

    public void setCae(String cae) {
        this.cae = cae;
    }
    
}
