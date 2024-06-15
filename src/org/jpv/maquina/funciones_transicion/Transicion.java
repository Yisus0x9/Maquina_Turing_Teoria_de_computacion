package org.jpv.maquina.funciones_transicion;
/*funcion de transicion , es lo que debe de realizar respecto al estado y caracter en el que esta el cabezal*/
public class Transicion {
    private final String estado;
    private final char caracter;
    private final D direccion;

    public Transicion(String estado , char caracter, D direccion){
        this.estado=estado;
        this.caracter=caracter;
        this.direccion=direccion;
    }

    public char getCaracter() {
        return caracter;
    }

    public String getEstado() {
        return estado;
    }

    public D getDireccion() {
        return direccion;
    }
}
