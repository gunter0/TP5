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
public class LineaDePedido {
        public Producto producto;
        public int cantidad;
        private double total;
        
        public String Concepto ;
        public double Precio ;

        public LineaDePedido(Producto producto, int cantidad, double total)
        {
            this.producto = producto;
            this.cantidad = cantidad;
            this.total = total;
            if(producto != null){
                Concepto = producto.getConcepto();
                Precio = producto.GetPrecio();
            }
        }

        public double getTotal()
        {
            return producto.GetPrecio() * cantidad;
        } 

        public Producto getProducto() {
            return producto;
        }

        public void setProducto(Producto producto) {
            this.producto = producto;
        }

        public int getCantidad() {
            return cantidad;
        }

        public void setCantidad(int cantidad) {
            this.cantidad = cantidad;
        }

        public double getPrecio() {
            return Precio;
        }

        public void setPrecio(double Precio) {
            this.Precio = Precio;
        }
        
        
}
