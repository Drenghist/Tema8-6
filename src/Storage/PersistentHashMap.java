/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Storage;

import Utilidades.Serializador;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Alex
 */
public abstract class  PersistentHashMap <T, J extends Storable> {
    protected HashMap <T, J> datos = new HashMap <>();
    RandomAccessFile ras;
    J ctemp;
    
    public PersistentHashMap (){
        //--------------------
        load();
        
        //--------------------
        
    }
    
    public String file(){
        return "file.dat";
    }
    
    public void load (){
        File f = new File(file()); 
        if (f.exists()){
            try{
            ras=new RandomAccessFile(file(),"r");
            ras.seek(0);
            while (ras.length()>ras.getFilePointer()){
                ctemp = Serializador.desSerializar(ras.readUTF().trim());
                datos.put((T) ctemp.getkey(),ctemp);
            } 
            ras.close();
            }catch (Exception ex){
                System.out.println("Erro o cargar o ficheiro");
            }
            
        }
        
        //--------------------
    }
    
    
    public void save (J object) throws Exception{
        String textoFormateado;
        if (datos.get(object.getkey())!= null) throw new Exception("O obxecto xa existe");
        datos.put((T) object.getkey(), object);
        try {
            ras=new RandomAccessFile(file(),"rw");
            ras.setLength(0);
            
            for (T llave:datos.keySet()){
                textoFormateado = String.format("%-1000s", Serializador.serializar((Serializable) datos.get(llave)));  
                System.out.println(textoFormateado.length());
                    ras.writeUTF(textoFormateado);

            }
            ras.close();
        } catch (IOException ex) {
            System.out.println("Erro o grabar o arquivo");
        }
        
        
    }
    
    
        public void update (J object) throws Exception{
        throw new Exception("Non implementado");
    
    }
    
    public J getObject (String key){
        return datos.get(key);
    }
        
    public ArrayList<T> getKey(){
        ArrayList<T> keys= new ArrayList<>();
        for (T i:datos.keySet()){
            keys.add(i);
        }
        return keys;
    }
    

    
        
}
