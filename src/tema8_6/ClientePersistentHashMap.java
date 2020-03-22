/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tema8_6;

import Storage.PersistentHashMap;

/**
 *
 * @author Alex
 */
public class ClientePersistentHashMap extends PersistentHashMap <String, Cliente> {
    
    @Override
    public String file(){
        return "clientes.dat";
    }
    

}
