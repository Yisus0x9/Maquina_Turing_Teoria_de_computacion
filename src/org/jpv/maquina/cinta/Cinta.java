package org.jpv.maquina.cinta;


/*La creacion de esta clase sirve para generar una cinta iniciada con B, seguida con una cadena y terminada con B
* ciertamente simula la cinta infinita ya que cuando el cabezal esta en un B , agrega otro a los dos extremos ya que este puede
* modificarse por un simbolo de cinta distinto de B*/
public class Cinta {
    Casilla cabezal;
    Casilla ultimo;
    public int cabezalInicialX;

    public void Cinta2(){

    }

    public void crearCinta(String cadena){
        //AQUI SE CREAN DOS CASILLAS CON EL SIMBOLO DE CINTA B
        ultimo=new Casilla();
        ultimo.simbolo='B';
        Casilla temp=new Casilla();
        temp.simbolo='B';
        temp.ant=ultimo;
        ultimo.sig=temp;
        ultimo=temp;
        cabezal=null;
        cabezalInicialX =2;
        //AHORA SE AGREGA LA CADENA A LA CINTA Y AL FINAL OTRAS DOS CASILLAS CON B
        //EL CABEZAL HACE REFERENCIA AL PRIMER CARACTER DISTINTO DE B EN LA CINTA

        for (int i = 0; i < cadena.length()+2; i++) {
           Casilla temporal = new Casilla();
           temporal.simbolo=(i<cadena.length()) ? cadena.charAt(i): 'B';
            if(i==0) {
                cabezalInicialX++;
                cabezal = temporal;
            }
            temporal.ant=ultimo;
            ultimo.sig=temporal;
            ultimo=temporal;
        }
        System.out.println("el cabezal se encentra en :" + cabezal.simbolo);
    }

    //cabezal se mueve a la derecha
    public void moverR(){
        cabezal= cabezal.sig;
    }

    //cabezal se mueve a la izquierda
    public void moverL(){
        cabezal=cabezal.ant;
    }

    //reemplaza el simbolo
    public void cambiarSimbolo(char caracter){
        cabezal.simbolo=caracter;
    }


    //obtiene el simbolo, si es 'B' puede que se cambie por ello se agrega otro B en ambos lados de la cinta
    public char getCaracter(){
        if(cabezal.simbolo=='B')
            addB();
        return cabezal.simbolo;
    }

    //agrega una casilla con el simbolo de cinta B al final e inicio de la cinta
    public void addB(){
        Casilla temp=new Casilla();
        temp.simbolo='B';
        temp.ant=ultimo;
        ultimo=temp;

        temp=new Casilla();
        temp.simbolo='B';
        Casilla guardar=cabezal;

        while(cabezal.ant!=null){
            cabezal=cabezal.ant;
        }
        temp.sig=cabezal;
        cabezal.ant=temp;
        cabezal=guardar;
    }

    //funcion para verificar que el cabezal se mantiene donde debe
    private void mostrarCabezal(){
        System.out.println("Cabezal en "+ cabezal.simbolo);
    }

    //me regresa la cadena en la cinta
    public String listar(){
        Casilla temp=ultimo;
        StringBuffer cinta=new StringBuffer();
        while(temp!=null){
            cinta.append(temp.simbolo);
            temp=temp.ant;
        }
        cinta=cinta.reverse();
        return  cinta.toString();
    }



    private static class Casilla{
        char simbolo;
        Casilla ant;
        Casilla sig;
    }
}
