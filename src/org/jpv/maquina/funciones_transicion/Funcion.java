package org.jpv.maquina.funciones_transicion;

import java.util.HashMap;


/*Esta clase se encarga de simular la tabla de transiciones, ya que tiene un estado, un caracter y que debe de hacer en ese caso*/


public class Funcion {
    private HashMap<String,Transicion> funciones=new HashMap<>();

    public void addfuncion(String estado, char caracter, Transicion transicion){
        if(!funciones.containsKey(estado+","+caracter))
            funciones.put(estado + "," +caracter, transicion);
    }

    public Transicion getTransicion(String estado, char caracter){
        return funciones.get(estado +","+ caracter);
    }
}
