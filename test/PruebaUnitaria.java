/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import modelo.Cajero;
import modelo.LineaDePedido;
import modelo.Negocio;
import modelo.Producto;
import modelo.PuntoVenta;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import vistas.VentanaNuevoPedido;

/**
 *
 * @author Gunter
 */
public class PruebaUnitaria {
    
    public PruebaUnitaria() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    import vistas.VentanaNuevoPedido;
    import modelo.Negocio;
    
    @Test 
    public void calcularTotalDecimales(){
        
        //Definicion
        double totalInicial = 0.0;
        double precioUprod = 10.2;
        int cantidadProd = 8;
        double prueba;
        
        //Ejecucion
        totalInicial = 0.0 + 10.2 * 8;
        prueba = VentanaNuevoPedido.calcTotal(totalInicial, precioUprod, cantidadProd);
        
        //Comprobacion
        assertEquals(totalInicial, prueba);
    }
    
    @Test 
    public void calcularTotalEnteros(){
        
        //Definicion
        int totalInicial = 0;
        int precioUprod = 10;
        int cantidadProd = 8;
        double prueba;
        
        //Ejecucion
        totalInicial = 0 + 10 * 8;
        prueba = VentanaNuevoPedido.calcTotal(totalInicial, precioUprod, cantidadProd);
        
        //Comprobacion
        assertEquals(totalInicial, prueba);
    }
    
    @Test
    public void logeo(){
        
        //Definicion
        String usuario = "Gunter";
        String contraseña = "Gunter";
        
        //Ejecucion
        for(PuntoVenta pv : Negocio.instancia.getPuntosDeVenta())
        {
            Cajero c = pv.getCajero();
            String c_usu = c.getUsuario();
            String c_pass = c.getContraseña();
                        
            
        }
        
        //Comprobacion
        assertEquals(usuario, c_usu);
        assertEquals(contraseña, c_pass);
        
        
    }

    @Test
    public void logeoUsuInvalido(){
        
        //Definicion
        String usuario = "usuarioFalso";
        String contraseña = "UsuarioFalso";
        boolean prueba;
        
        //Ejecucion
        for(PuntoVenta pv : Negocio.instancia.getPuntosDeVenta())
        {
            Cajero c = pv.getCajero();
            String c_usu = c.getUsuario();
            String c_pass = c.getContraseña();
                        
            if(c_usu.equalsIgnoreCase(usuario) && c_pass.equalsIgnoreCase(contraseña))
            {
                prueba = true;
            }
        }
        
        //Comprobacion
        assertTrue(prueba == false);
        
        
    }
    
    @Test
    public void hola(){
        //Definicion
        Producto ep6 = new Producto(6, "MILANESA", 50, false);
        Producto ep7 = new Producto(7, "LOMITO", 40, false);
        Producto ep8 = new Producto(8, "HAMBURGUESA", 30, false);
        
        //Ejecucion
        double pruebaFc = VentanaNuevoPedido.calcTotal(3, ep6.GetPrecio());
        double prueba = 3*50;
        
        //Comprobacion
        assertEquals(prueba, pruebaFc);
            
    }
    
}
