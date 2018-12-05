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
public class Propietario {
    private String nombre;
    private String categoria;
    private String _CUIT;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getCUIT() {
        return _CUIT;
    }

    public void setCUIT(String _CUIT) {
        this._CUIT = _CUIT;
    }
    
}
