package org.jpv.maquina;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ArchivoServicio {
    File archivo;
    public  ArchivoServicio(){

    }

    public  ArchivoServicio(File archivo){
        if(archivo.exists())
            archivo.delete();
        this.archivo=archivo;
    }

    public void escribir(String cadena){
        try(BufferedWriter input=new BufferedWriter(new FileWriter(archivo,true))){
            input.append(cadena);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
