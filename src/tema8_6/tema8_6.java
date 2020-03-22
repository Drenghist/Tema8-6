/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tema8_6;

import Utilidades.Texto;
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
                    if (dnis.isEmpty()) Texto.linea("No hay DNIs grabados\n");
                    for (String i:dnis){
                        System.out.print("dni: "+i+" ");
                        Cliente persona = datos.getObject(i);
                        System.out.println("activo: "+persona.isActivo()+"\n");
                    }    
                    dnis.clear();
                    break;
                case 3: 
                    ArrayList <String> ALClientes = new ArrayList<>();
                    ALClientes = datos.getKey();
                    String abuscar = Texto.getLinea("Introduzca DNI a buscar\n");
                    boolean tag = false;
                    for (String i:ALClientes){
                        if (i.equals(abuscar)){
                            tag = true;
                            Texto.linea("El DNI "+i+" está guardado");
                        }
                    }
                    if (tag == false) Texto.linea("El DNI no está guardado\n");
                    break;
                case 4:
                    eliminaCliente();
                    break;
                case 5:
                    datos.wipe();
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
    public static void eliminaCliente (){  
    String temp;
    String dni;
    
        
        try{
            Texto.linea("Eliminación dun cliente");
            Texto.linea("-------------------------\n");
            dni = Texto.getLinea("Introduzca DNI");

            if (datos.getObject(dni)==null){
                throw new Exception ("DNI non existe");
            }
            datos.delete(datos.getObject(dni));
        }
        catch (Exception ex){
            System.out.println("Dato no válido: "+ex.getMessage());
        }
        
        
        

        
    }
    
}

