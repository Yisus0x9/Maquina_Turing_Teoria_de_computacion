package org.jpv.maquina.mt;

import org.jpv.maquina.ArchivoServicio;
import org.jpv.maquina.cinta.Cinta;
import org.jpv.maquina.funciones_transicion.*;


import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.Arrays;
import java.util.List;

public class MT extends JPanel {

    private static final String simbId="⊦";
    private static ArchivoServicio archivo;
    private String[] estados;
    private char[] alfabeto={'0','1'};
    //private char [] simbolos={'X','Y','B'};
    private Funcion funcion;
    private String cadena;
    private StringBuffer cadID;
    private String estadoInicial;
    private String[] estadosFinales;
    private Cinta cinta;
    private String estadoActual;
    private int cabezalX;
    private boolean ref=false;
    private String estadoCinta;
    private String estado;

    //Para crear la maquina de tuirng, necesitamos pasar los estados,el estado inicial, los estados finales
    //y las funciones de transicion

    public MT(String[] estados, Funcion funcion, String estadoInicial, String[] estadosFinales,String nombreArch) {
        archivo=new ArchivoServicio(new File(nombreArch));
        setLayout(new BorderLayout());
        setBackground(Color.BLACK);
        this.estados = estados;
        this.funcion = funcion;
        this.estadoInicial = estadoInicial;
        this.estadosFinales = estadosFinales;
        cinta=new Cinta();
        estado=estadoInicial;
    }


    public boolean checarw(){
        cinta.crearCinta(randomCadena());
        cabezalX=cinta.cabezalInicialX;
        estadoCinta=cinta.listar();
        return evaluar();
    }

    public boolean checarw(String cadena){

        this.cadena=cadena;
        if(cadena.length()<=16)
            ref=true;
        cinta.crearCinta(cadena);
        cabezalX=cinta.cabezalInicialX;
        estadoCinta=cinta.listar();
        return evaluar();
    }


    //funcion que hara la logica en la cinta
    private boolean evaluar(){

        this.estadoActual=estadoInicial;
        List<String> finales= Arrays.asList(estadosFinales);

        //mientras el estado actual no sea un final
        while(!finales.contains(estadoActual)){

            //se obtiene la S para el estado y simbolo actual
            Transicion transicion=funcion.getTransicion(estadoActual,cinta.getCaracter());

            if(transicion==null){
                //no existe la S para tal estado y simbolo
                archivo.escribir("\n");
                return false;
            }
            estadoCinta=cinta.listar();
            pause();
            escribirID();
            estado=transicion.getEstado();
            System.out.println("el estado de la cinta es:" + cinta.listar());
            cinta.cambiarSimbolo(transicion.getCaracter());

            if(transicion.getDireccion()== D.LEFT){
                cabezalX--;
                cinta.moverL();
            }

            if(transicion.getDireccion()==D.RIGHT){
                cabezalX++;
                cinta.moverR();
            }

            estadoActual=transicion.getEstado();
        }
        pause();
        escribirID();
        archivo.escribir("\n");
        return  true;
    }

    public String estadoCinta(){
        return cinta.listar();
    }


    private String randomCadena(){
        int caso=(int)(Math.random()*10+1);
        int largo=(int)(Math.random()*500+1);
        int ref=0;
        StringBuffer cad=new StringBuffer();
        if(caso%2==0){
            while(ref<largo){cad.append("0"); ref++;}
            ref=0;
            while(ref<largo){cad.append("1"); ref++;}
            //System.out.println("La Cadena generada es: "+cad);

        }else{
            while(ref<largo/(int)((Math.random()*5)+2)){cad.append("0"); ref++;}
            ref=0;
            while(ref<largo/(int)((Math.random()*5)+2)){cad.append("1"); ref++;}
            //System.out.println("La Cadena generada es: "+cad);
        }
        System.out.println("La cadena es:" +cad.toString());
        this.cadena=cad.toString();
        return  cad.toString();

    }

    private void pause(){
        if (ref) {
            repaint();
            try {
                Thread.sleep(1000
                );
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void escribirID(){
        cadID=new StringBuffer(estadoCinta);
        cadID=cadID.replace(cabezalX-1,cabezalX,estado);
        cadID=new StringBuffer(cadID.toString().replaceAll("B",""));
        archivo.escribir("("+cadID.toString()+")"+simbId);
    }

    public void reset(){
        ref=false;
        estado=estadoInicial;
        archivo.escribir("\n\n");
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        int i=1;
        g.setFont(new Font("Hack Nerd Font",3,30));
        g.setColor(Color.WHITE);
        g.drawString("La cadena es:" + cadena,100,70);
        g.setFont(new Font("Hack Nerd Font",1,14));
        g.setColor(new Color(255, 119, 0));
        g.fillOval(70+cabezalX*50,200,50,50);
        g.setColor(Color.WHITE);
        g.drawOval(70+cabezalX*50,200,50,50);
        g.setColor(Color.RED);
        g.drawString(estado,70+cabezalX*50+15,200+25);

        while(i<=estadoCinta.length()){
            g.setColor(new Color(255, 119, 0));
            g.fillRect(70+i*50,270,50,50);
            g.setColor(new Color(0, 158, 134));
            g.drawRect(70+i*50,270,50,50);
            g.setColor(Color.WHITE);
            g.drawString("  "+estadoCinta.charAt(i-1),70+i*50,310);
            i++;
        }
        g.drawLine(70+cabezalX*50+25,250,70+cabezalX*50+25,275);
        g.fillOval(70+cabezalX*50+20,275,10,10);
    }
}
