/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package CALCULADORA;

import java.util.ArrayList;


public interface PilaADT <T> {
    
    public void push(T dato);
    
    public T pop();
    
    public boolean isEmpty();
    
    public T peek();
    
    public void multiPop(int n);
    
    public ArrayList<T> enArreglo(PILAS.PilaADT<T> a, PILAS.PilaADT<T> b);
    
}
