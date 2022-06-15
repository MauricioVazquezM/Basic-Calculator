/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CALCULADORA;

import PILAS.*;

/**
 *Mauricio Vazquez Moran
 * 000191686
 * 17/02/2002
 * PILAS
 */
public class ColeccionVaciaExcepcion extends RuntimeException {
    
    public ColeccionVaciaExcepcion(){
        super();
    }
    
    public ColeccionVaciaExcepcion(String mensaje){
        super(mensaje);
    }
}
