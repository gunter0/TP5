/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controladores;

import java.util.List;
import modelo.Pedido;
import modelo.Producto;

/**
 *
 * @author Fernanda Ponce
 */

public interface IVentanaNuevoPedido {
    void cargarComboRubros(List<String> list);
    void cargarProductos(List<Producto> enumerable);
    void motrarComanda(int idpv,Pedido p);
}
