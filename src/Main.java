
import org.jpv.maquina.funciones_transicion.D;
import org.jpv.maquina.funciones_transicion.Funcion;
import org.jpv.maquina.funciones_transicion.Transicion;
import org.jpv.maquina.mt.MT;

import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    private static String []estados={"q0","q1","q2","q3","q4"};
    private static String []estadosF={"q4"};
    private static Funcion tabla=new Funcion();
    private static String cadena=null;
    private static JFrame ventana=new JFrame("MAQUINA DE TURING_L:{0n1n]");
    private static MT maquina;

    public static void main(String[] args) {

        Scanner es=new Scanner(System.in);
        ventana.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ventana.setSize(1300,450);
        ventana.setLocationRelativeTo(null);

        tabla.addfuncion("q0",'0',new Transicion("q1",'X', D.RIGHT));
        tabla.addfuncion("q0",'Y',new Transicion("q3",'Y', D.RIGHT));

        tabla.addfuncion("q1",'0',new Transicion("q1",'0', D.RIGHT));
        tabla.addfuncion("q1",'1',new Transicion("q2",'Y', D.LEFT));
        tabla.addfuncion("q1",'Y',new Transicion("q1",'Y', D.RIGHT));

        tabla.addfuncion("q2",'0',new Transicion("q2",'0', D.LEFT));
        tabla.addfuncion("q2",'X',new Transicion("q0",'X', D.RIGHT));
        tabla.addfuncion("q2",'Y',new Transicion("q2",'Y', D.LEFT));

        tabla.addfuncion("q3",'Y',new Transicion("q3",'Y', D.RIGHT));
        tabla.addfuncion("q3",'B',new Transicion("q4",'B', D.RIGHT));
        maquina = new MT(estados,tabla,"q0",estadosF,"ID.txt");

        int op=0;

        while(op!=3){
            System.out.println("Eliga un opcion:\n\t1.Ingresar cadena\n\t2.Probar cadena random\n\t3.Salir");
            op=new Scanner(System.in).nextInt();
            System.out.println("op = " + op);
            switch (op) {
                case 1:
                    System.out.println("Ingresa la cadena:");
                    cadena=es.nextLine();
                    checarW(cadena);
                    break;
                case 2:
                    checarW(null);
                    break;

                default:
                    break;
            }

            }
    }

    private static void checarW(String cadena){
        boolean aceptada;
        if(cadena==null){
            aceptada=maquina.checarw();
        }else{
            if(cadena.length()<=16) {
                ventana.add(maquina, BorderLayout.CENTER);
                ventana.setVisible(true);
            }
            aceptada=maquina.checarw(cadena);
        }

        if(aceptada){
            System.out.println("Cadena aceptada");
            JOptionPane.showMessageDialog(null,"La cadena pertenece al lenguaje","MT",JOptionPane.INFORMATION_MESSAGE);
        }else{
            System.out.println("Cadena no aceptada");
            JOptionPane.showMessageDialog(null,"La cadena no pertenece al lenguaje","MT",JOptionPane.INFORMATION_MESSAGE);

        }
        maquina.reset();
    }
}