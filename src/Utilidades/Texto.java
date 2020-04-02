package Utilidades;

import java.util.Scanner;

/**
 *
 * @author Alex
 * @version 2.0 Lectura por registros
 * @since 02/04/2020
 */
public class Texto {
    
    /**
     *
     * @param string Mostra unha liña por pantalla
     */
    public static void linea(String string){
        System.out.println(string);
    }
    
    /**
     *
     * @return recibe unha liña do teclado
     */
    public static String getLinea(){
        Scanner teclado = new Scanner(System.in);
        return teclado.nextLine();
    }
    
    /**
     *
     * @param string Mensaxe a mostrar por pantalla
     * @return recibe unha liña do teclado
     */
    public static String getLinea(String string){
        System.out.println(string);  
        Scanner teclado = new Scanner(System.in);
        return teclado.nextLine();
    }
}
