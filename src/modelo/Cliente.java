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
public class Cliente {
   private String dni;
   private String condicionTributaria;
    
   public Cliente(String dni, String condicionTributaria)
   {
       this.dni = dni;
       this.condicionTributaria = condicionTributaria;
   }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getCondicionTributaria() {
        return condicionTributaria;
    }

    public void setCondicionTributaria(String condicionTributaria) {
        this.condicionTributaria = condicionTributaria;
    }
   
}
