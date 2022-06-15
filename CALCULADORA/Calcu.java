/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CALCULADORA;

/**
 * @author Ricardo Illescas
 * @author Santiago Olvera
 * @author Mauricio Vázquez
 * @author Emmanuel Rosales
 * @author Iván Vázquez
 * 
 * Creacion de la clase Calcu con metodos para el optimo funcionamiento de una calculadora con pilas
 */

public class Calcu {
    private String infijo;
    
     /**
     * Constructor vacio de la clase Calcu
     */
    public Calcu() {
    }

     /**
     * Constructor de la clase Calcu
     * @param infijo de tipo String que recibe para el uso de los siguientes metodos 
     */
    public Calcu(String infijo) {
        this.infijo = infijo;
    }

    public void setInfijo(String infijo) {
        this.infijo = infijo;
    }
    
    /**
     *Metodo, de tipo publico, para la verificacion de que los caracteres operativos, como los parentesis y puntos, esten balanceados y en correcto uso para el funcionamiento de la calculadora.
     * @param no recibe parametros
     * @return
     */	
    public boolean verificacion(){
        PilaA<Character> cad = new PilaA();
        boolean aux= true;
        int i=0;
        
        while(i<infijo.length() && aux){           //Mientras nuestro bandera sea tenga un valor verdadero, el ciclo seguira corriendo
            if(infijo.charAt(i)=='(')             //Si se encuentra parentesis abierto hacia la derecha
                cad.push(infijo.charAt(i));       //se metera en la pila auxiliar 
            else
                if(infijo.charAt(i)==')'){        //En caso de que se encuentre el abierto hacia la izquierda 
                    if(!cad.isEmpty())            //y si esta la pila con algun elemento 
                        cad.pop();                //se sacara de la pila el elemto de arriba
                    else
                        aux=false;                //De no ser de esta manera la bandera auxiliar cerrara el cilo de verificacion 
                }
             if ((operando(infijo.charAt(i)) && (i==0 || operando(infijo.charAt(i-1)) || infijo.charAt(i-1)=='(' || infijo.charAt(i-1)=='.')) || (infijo.charAt(i)=='.' && (i==0 || infijo.charAt(i-1)=='.' || infijo.charAt(i-1)==')' || infijo.charAt(i-1)=='(' || operando(infijo.charAt(i-1)) || infijo.charAt(i-1)=='_')) || (i!=0 && ((infijo.charAt(i)=='_' && (infijo.charAt(i-1)=='_' || Character.isDigit(infijo.charAt(i-1)) || infijo.charAt(i-1)=='.')) || (infijo.charAt(i)=='(' && infijo.charAt(i-1)=='_') || (infijo.charAt(i-1)==')' && Character.isDigit(infijo.charAt(i)))))) {	//Verificando que no se den ciertos errores en la escritura que impliquen un error al evaluarlo en postfijo y tambien las limitaciones
                aux=false;                                                              //en caso de serlo se marcara error y el ciclo de verificacion se cerrara
            } 
            i++;
        }
        if(!cad.isEmpty())                        //En caso de que la pila no se encuentre vacia la verificacion no sera exitosa. esto debido a que la expresion ingrsada es incorrecta
                aux=false;
        return aux;
    }
	
    /**
     *Metodo, de tipo publico, para la verificacion de los operandos
     * @param recibe un parametro de tipo Character
     * @return regresa un boolean que confirma que es un operando
     */
    public boolean operando(Character ch){
        boolean res= false;
        
        if(ch=='+' || ch=='*' || ch=='-' || ch=='/') //En caso de ser un operando se verifcara que si lo es ese Character dado
            res=true;
        return res;
    }
	
    /**
     *Metodo, de tipo publico, para asignarle un valor a los operando, segun su jerarquia. Dicho metodo brindara apoyo para pasar de escritura infija a la postfija.
     * @param recibe un parametro de tipo Character
     * @return un entero con el valor segun la jerarquia del operando
     */
    private static int jerarquia(Character op) {
    int prf = 99;
    if (op.equals('*') || op.equals('/')) prf = 4;  //La multiplicacion y la division tienen la jerarquia y prioridad de uso mas alto
    if (op.equals('+') || op.equals('-')) prf = 3;  //la suma y la resta seguiran la jerarquia de prioridad                   
    if (op.equals('(')) prf = 1;                    //Tiene el valor mas bajo ya que, al momento de la comparacion, los operando (+,-,/,*) imporataran mas al ser pasados a la postfija.
    return prf;
  }
	
    /**
     *Metodo, de tipo publico, para pasar de notacion infija a postfija. Dich metodo sera usaso para la evaluacion de la expresion ingresada en la calculadora.
     * @return regresa una cadena de tipo String
     */
    public String infijoAPostfijo(){
        StringBuilder str= new StringBuilder();
        PilaA<Character> cad = new PilaA();
        str.append("");
        
        if(verificacion()){                                                                         //Tiene que pasar primero por nuestro metodo de la verificacion. Sin emabrgo, este en la interfaz no va a ser relavante, pues ahí se hace otra verificación antes
            for(int i=0;i<infijo.length();i++){                                                     
                if(Character.isDigit(infijo.charAt(i)) || infijo.charAt(i)=='.' || infijo.charAt(i)=='_'){                   //Se verifica que el Character sea un digito, un punto o nuestro indicdor de negativo
                    if(i!=0 && Character.isDigit(infijo.charAt(i-1)) && infijo.charAt(i)!='.' && infijo.charAt(i)!='_'){     //Se verifica estar fuera del primer ciclo, que el Character anterior sea un digito, que no sea un punto y que no sea nuestro indicador de negativo. Esto para dar una indicación especifica al pasarlo a postfijo
                        str.append(" ");                                                            //Se usa un espacio de apoyo en este caso por los numero compuestos de mas de 2 cifras
                        str.append(infijo.charAt(i));                                               //Se inserta en la notacion postfija
                    }
                    else
                        str.append(infijo.charAt(i));                                               //En caso contrario, se inserta sin el apoyo del espacio
                }    
                    
                else                                                                                //En caso de no serlo
                    if(infijo.charAt(i)=='(')                                                       //se verifica que sea un parentesis abierto a la izquierda y en caso de serlo se mete la pila
                        cad.push('(');
                    else
                        if(infijo.charAt(i)==')'){                                                  //Si es un parentesis abierto a la izquierda
                            while(cad.peek()!='('){                                                //mientras el tope no sea parentesis abierto a la derecha
                                str.append(cad.pop());                                              //se insertara a la cadena postfija lo sacado de la pila
                            }
                            cad.pop();
                        }
                        else
                            if(!cad.isEmpty() && jerarquia(infijo.charAt(i))<= jerarquia(cad.peek())){  //En caso de que la pila no este vacia y que la jerarquia del Character de la cadna infija sea menor o igual al tope de la pila
                                str.append(cad.pop());                                                   //se insertara en la cadena postfija lo sacado de la pila
                                cad.push(infijo.charAt(i));                                              //se metera a la pila el Character de la cadena infija
                            }
                            
                            else
                                cad.push(infijo.charAt(i));                                              //En caso contrario, solo se mete a la pila el Character de la infija
                            
                            
            }
            while(!cad.isEmpty())                                                                       //Finalmente, mientras no este vacia la pila se insertara en la cadena postfija lo sacado de la pila
                str.append(cad.pop());
        }
        else
            str.append("ERROR");
        return str.toString();
    }
	
    /**
     *Metodo, de tipo public, cuyo proposito es evaluar la expresion ya dada en terminos postfijos.
     * @return regresa un dato de tipo double
     */
    public double evaluar(){
        double res;
        PilaA<String> cad = new PilaA();
        String str=infijoAPostfijo();                                           //Apoyo en en metodo que nos da la cadena postfija
        boolean aux=true;                                                       //Bandera auxiliar
        String aux2="";
        
        for(int i=0;i<str.length();i++){                                        //Correra hasta el fin de la cadena. Esto a debido que, no olvidemos, la entrada dada en la interfaz ya fue verificada
            if(!aux){                                                           //Si la bandera es falsa
                    if(str.charAt(i-1)==' '){                                   //y si hay un espacio antes de nuestro indice dado por el ciclo for
                        aux2=cad.pop();                                         //nuestro auxiliar String tomara el valor de o sacado de la pila
                        aux2=aux2+str.charAt(i);                                //ademas de agregarle lo que tendramos en ese indice sacado de la cadena postfija
                    }
                    else{                                                       //En caso contrario
		    	if(str.charAt(i-1)=='.'){				//Vemos que si era un punto
                            aux2=cad.pop();					//Nuestro auxiliar String tomara el valor de lo sacado de la pila
                            aux2=aux2+"."+str.charAt(i);			//Además de agregarle un punto mas lo que indique el indice del ciclo for
                        }
                        else{							//De lo contrario será nuesto indicador de negativo
                            aux2="-"+str.charAt(i);				//Y pondrá al numero con el inidcador de negativo
                        }
                    }
                    cad.push(aux2);                                             //Se introduce nuestro auxiliar generado a la pila
                    aux2="";                                                    //Se le regresa su valor inicial
                    aux=true;                                                   //Nuestra bandera auxiliar mantiene su valor inicial
            }
            else                                                                //En caso de que la bandera tenga valor true
                if(Character.isDigit(str.charAt(i)))                            //Si nuestro Character en la posicion es un digito
                    cad.push(Character.toString(str.charAt(i)));                //Se introduce como String el Character la pila
                else
                    if(str.charAt(i)==' ' || str.charAt(i)=='.' || str.charAt(i)=='_')  //En caso contrario a no ser un digito y ser un espacio, un punto o indicador de negativo. 
                        aux=false;                                              //la bandera cambiara de valor 
                    else                                                        //Si no pasa inguna de estas condiciones se pasa a envaluar
                        switch (str.charAt(i)) {                                //Mediante un switch y dependiendo del operando dado se evalua
			case  '+': cad.push(String.valueOf(Double.parseDouble(cad.pop())+Double.parseDouble(cad.pop())));
				break;
			case '-': cad.push(String.valueOf(-Double.parseDouble(cad.pop())+Double.parseDouble(cad.pop())));
				break;
			case  '*': cad.push(String.valueOf(Double.parseDouble(cad.pop())*Double.parseDouble(cad.pop())));
				break;
			case  '/': cad.push(String.valueOf(1/(Double.parseDouble(cad.pop())/Double.parseDouble(cad.pop()))));
				break;
			default:;
			}        
        }
        res=Double.parseDouble(cad.pop());                                      //Finalmente se hace la conversion a un dato de tipo double para regresar el resultado
        return res;
    }
    
}
