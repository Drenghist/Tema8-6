package tema8_6;

import Storage.PersistentHashMap;

/**
 *
 * @author Alex
 * @version 2.0 Lectura por registros
 * @since 02/04/2020
 */
public class ClientePersistentHashMap extends PersistentHashMap <String, Cliente> {
    
    /**
     *
     * @param object Pasa o obxecto cliente ó construtor de PersistentHashMap
     * para que ignore este obxecto á hora de cargar os datos na memoria volatil
     */
    public ClientePersistentHashMap (Cliente object){
        super(object);
    }
    
    /**
     *
     * Non ten parámetros, chama ó constructor por defecto
     */
    public ClientePersistentHashMap (){

    }
    
    /**
     *
     * @return retorna o nome do ficheiro a grabar
     */
    @Override
    public String file(){
        return "clientes.dat";
    }
    

}
