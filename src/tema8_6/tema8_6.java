/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tema8_6;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Alex
 */
public class tema8_6 {
    static boolean flag = false;
    static Scanner teclado = new Scanner(System.in);
    static int opcion;
    static ClientePersistentHashMap datos = new ClientePersistentHashMap();
    static ArrayList<String> dnis;
    
    public static void main (String args[]){
        while (flag == false){
            System.out.println("Menú");
            System.out.println("--------------\n");
            System.out.println("1- Crear novo cliente");
            System.out.println("2- Listar clientes");
            System.out.println("3- Buscar clientes");
            System.out.println("4- Borrar cliente");
            System.out.println("5- Borrar ficheiro de clientes");
            System.out.println("6- Sair\n");
            try{
            opcion = Integer.parseInt(teclado.nextLine());
            } finally{
            }
            
            switch (opcion){
                case 1:
                    novoCliente();
                    break;
                case 2:
                                        dnis = datos.getKey();
                    System.out.println("Lista de DNIS grabados");
                    for (String i:dnis){
                        System.out.print("dni: "+i+" ");
                        Cliente persona = datos.getObject(i);
                        System.out.println("activo: "+persona.isActivo());
                    }    
                    dnis.clear();
                    break;
                case 3: 
                    break;
                case 6:
                    flag = true;                 
                    break;
                default:
                    System.out.println("Opción non válida");
            }
            
            
            
            
        }
        
        
        
    }
    
    public static void novoCliente (){

    String dni, nombre, direccion;
    boolean bucle = false;    
    double telefono;
    int deuda;
                
        while(bucle == false){
            try{
                System.out.println("Creación de un nuevo cliente");
                System.out.println("-------------------------\n");
                System.out.println("Introduzca DNI");
                dni = teclado.nextLine(); 
                System.out.println("Introduza nome");
                nombre = teclado.nextLine(); 
                System.out.println("Introduza enderezo");
                direccion = teclado.nextLine(); 
                System.out.println("Introduza teléfono");
                telefono = Double.parseDouble(teclado.nextLine()); 
                System.out.println("Introduza deuda");
                deuda = Integer.parseInt(teclado.nextLine()); 
                datos.save(new Cliente(dni, nombre, direccion, telefono, deuda));
                bucle = true;
            }
            catch (Exception ex){
                System.out.println("Dato no válido: "+ex.getMessage());
            }
        }
        
        

        
    }
    
    
}

