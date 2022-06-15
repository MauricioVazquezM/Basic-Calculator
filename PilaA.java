/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CALCULADORA;

import PILAS.ColeccionVaciaExcepcion;
import PILAS.PilaADT;
import java.util.ArrayList;


public class PilaA <T> implements PilaADT<T>{
    private T[] pila;
    private int tope;
    private final int MAX=20;

    public PilaA() {
        pila = (T[]) new Object[MAX];
        tope = -1;
    }
    
    public boolean isEmpty(){
        return tope==-1; //La maquina compara el valor de toope para darnos el resultado
    }
    
    public void push (T dato){
        if(tope==pila.length-1){
            expande ();
        }
        tope++;
        pila[tope] = dato;
    }
    
    //Metodo auxiliar para generar una pila mas grande que la actual
    private void expande(){
        T [] nuevo;
        
        nuevo = (T[])new Object[pila.length*2];
        for(int i=0; i<=tope; i++){
            nuevo[i]=pila[i];
        }
        pila = nuevo;
    }
    
    public T peek(){
        if(this.isEmpty()){
            throw new ColeccionVaciaExcepcion("PILA VACIA");
        }
        return pila[tope];
    }
    
    public T pop(){
        T resultado;
        
        if(this.isEmpty()){
            throw new ColeccionVaciaExcepcion("PILA VACIA");
        }
        resultado= pila[tope];
        pila[tope] = null;
        tope--;
        return resultado;
    }
    
    public String toString(){
        StringBuilder cad;
        
        cad= new StringBuilder();
        for(int i=0; i<=tope; i++){
            cad.append(pila[i]).append("\n");
        }
        return cad.toString();
    }
    
    public void multiPop(int n){
        
        if(isEmpty()){
            throw new ColeccionVaciaExcepcion("PILA VACIA");
        }else{
            if(tope>=n-1){
                for(int i =0; i<n; i++){
                    pila[tope]=null;
                    tope--;
                }
            }
        }
    }
    
    public ArrayList<T> enArreglo(PilaADT<T> a, PilaADT<T> b){
        ArrayList<T> res;
        int posF;
        
        posF=0;
        res=new ArrayList<>();
        while(!a.isEmpty()){
            res.add(a.pop());
            posF++;
        }
        while(!b.isEmpty()){
            res.add(posF, b.pop());
            posF++;
        }
        return res;
    }
    
    
}

