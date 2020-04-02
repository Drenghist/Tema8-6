package tema8_6;

import Utilidades.Texto;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Alex
 * @version 2.0 Lectura por registros
 * @since 02/04/2020
 */
public class tema8_6 {
    final static Cliente nulo =new Cliente("nulo", "nulo", "nulo", 0,0);
    static boolean flag = false;
    static Scanner teclado = new Scanner(System.in);
    static int opcion;
    static ClientePersistentHashMap datos = new ClientePersistentHashMap(nulo);
    static ArrayList<String> dnis;
    
    /**
     *
     * @param args
     */
    public static void main (String args[]){
        while (flag == false){
            System.out.println("\nMenú");
            System.out.println("--------------\n");
            System.out.println("1- Crear novo cliente");
            System.out.println("2- Listar clientes");
            System.out.println("3- Buscar clientes");
            System.out.println("4- Borrar cliente");
            System.out.println("5- Borrar ficheiro de clientes");
            System.out.println("6- Sair\n");
            try{
            opcion = Integer.parseInt(teclado.nextLine());
            } catch (Exception ex){
            }
            
            switch (opcion){
                case 1:
                    novoCliente();
                    break;
                case 2:
                    dnis = datos.getKey();
                    System.out.println("Lista de DNIS grabados\n");
                    if (dnis.isEmpty()) Texto.linea("No hay DNIs grabados\n");
                    for (String i:dnis){
                        System.out.print("dni: "+i+" ");
                        Cliente persona = datos.getObject(i);
                        System.out.println("activo: "+persona.isActivo());
                    }    
                    dnis.clear();
                    break;
                case 3: 
                    String abuscar = Texto.getLinea("Introduzca DNI a buscar\n");
                    Cliente c = datos.getObject(abuscar);
                    if (c != null) Texto.linea("El DNI está guardado en el fichero");
                    else Texto.linea("El DNI no existe");
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
    
    /**
     * Crea un novo cliente e o engade na memoria
     */
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

    /**
     * Elimina un cliente, eliminándoo do almacenamento volátil (hashmap) con .delete,
     * e tamén o elimina do almacenamento persistente con .override sobreescribíndoo
     */
    public static void eliminaCliente (){  
    
    String dni;
    
        
        try{
            Texto.linea("Eliminación dun cliente");
            Texto.linea("-------------------------\n");
            dni = Texto.getLinea("Introduzca DNI");

            if (datos.getObject(dni)==null){
                throw new Exception ("DNI non existe");
            }
            
            System.out.println("direccion a borrar: "+datos.getPointer(datos.getObject(dni)));
            datos.override(datos.getPointer(datos.getObject(dni)), nulo); //sobreescribo el campo con el objeto "nulo"
            datos.delete(datos.getObject(dni));
        }
        catch (Exception ex){
            System.out.println("Dato no válido: "+ex.getMessage());
        }
        
        
        

        
    }
    
}

