/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilidades;

import java.util.Scanner;

/**
 *
 * @author Alex
 */
public class Texto {
    
    public static void linea(String string){
        System.out.println(string);
    }
    
    public static String getLinea(){
        Scanner teclado = new Scanner(System.in);
        return teclado.nextLine();
    }
    
        public static String getLinea(String string){
        System.out.println(string);  
        Scanner teclado = new Scanner(System.in);
        return teclado.nextLine();
    }
}
