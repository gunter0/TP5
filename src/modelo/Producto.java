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

public class Producto {
        private int id;
        private String concepto;
        private boolean esAgregado;
        private List<Producto> listaAgregados;
        


        public Producto(int id, String concepto, double precio, boolean esAgregado)
        {
            this.esAgregado = esAgregado;
            this.id = id;
            this.concepto = concepto;
            this.SetPrecio(precio);
            listaAgregados = new ArrayList<Producto>();
        }

        public Producto(boolean esAgregado)
        {
            this.esAgregado = esAgregado;
        }

        public void addAgregado(Producto p)
        {
            if (p.esAgregado)
                listaAgregados.add(p);
        }
        public void quitarAgregado(Producto p)
        {
            if (p.esAgregado)
                listaAgregados.remove(p);
        }

        private double precio;

        public double GetPrecio()
        {
            double aux = 0;
            for (Producto p : listaAgregados) {
                aux += p.GetPrecio();
            }
            return precio + aux;
        }

        public void SetPrecio(double value)
        {
            precio = value;
        }
        
            public String getConcepto() {
        return concepto;
        }

        public void setConcepto(String concepto) {
            this.concepto = concepto;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

    public List<Producto> getListaAgregados() {
        return listaAgregados;
    }

    public void setListaAgregados(List<Producto> listaAgregados) {
        this.listaAgregados = listaAgregados;
    }
        
        
        
}
