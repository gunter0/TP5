/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.Date;

/**
 *
 * @author Fernanda Ponce
 */

public class Turno {
    private Date tiempoDesde;
    private Date tiempoHasta;

    public Turno()
    {

    }

    public Date getTiempoDesde() {
        return tiempoDesde;
    }

    public void setTiempoDesde(Date tiempoDesde) {
        this.tiempoDesde = tiempoDesde;
    }

    public Date getTiempoHasta() {
        return tiempoHasta;
    }

    public void setTiempoHasta(Date tiempoHasta) {
        this.tiempoHasta = tiempoHasta;
    }
    
    
}
