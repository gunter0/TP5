/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;


/**
 *
 * @author Fernanda Ponce
 */

public class Rubro {
    private int id;
    private String concepto;
    private List<Producto> pilaProductos;


    public Rubro(int id, String concepto)
    {
        this.id = id;
        this.concepto = concepto;
        pilaProductos = new ArrayList<Producto>();
    }

    public void agregarProductos(Producto ep, int cantidad)
    {
        for (int i = 0; i < cantidad; i++)
        {
            pilaProductos.add(ep);
        }

    }

    public Producto buscarProducto(String concepto)
    {
        Producto producto = null;
        for(Producto ep : pilaProductos)
        {
            if(ep.getConcepto() == concepto)
            {
                //producto = pilaProductos.pop();
            }
        }
        return producto;
    }

    public Producto buscarProducto(int id)
    {
        Producto producto = null;
        for(Producto ep : pilaProductos)
        {
            if (ep.getId() == id)
            {
                //producto = pilaProductos.pop();
            }
        }
        return producto;
    }

    public List<Producto> getConceptoEspecificaciones()
    {
        return pilaProductos;
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }
    
    
}
