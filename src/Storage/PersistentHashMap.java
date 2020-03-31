/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Storage;

import Utilidades.Serializador;
import Utilidades.Texto;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    
    
    public long getPointer (J object){
        File f = new File(file()); 
        long adevolver = 0;
        if (f.exists()){
            try{
            ras=new RandomAccessFile(file(),"r");
            ras.seek(0);
            String cadena2 = Serializador.serializar((Serializable) object);
            while (ras.length()>ras.getFilePointer()){
                String cadena1 = ras.readUTF().trim();
                if (cadena1.equals(cadena2)) adevolver=(ras.getFilePointer()-1000);
                
                
                //ctemp = Serializador.desSerializar(ras.readUTF().trim());
                //datos.put((T) ctemp.getkey(),ctemp);
            } 
            ras.close();
            }catch (Exception ex){
                System.out.println("Erro o cargar o ficheiro");
            }
            
        }
        
        return adevolver;
    }
    
    
    public void save (J object) throws Exception{
        String textoFormateado;
        if (datos.get(object.getkey())!= null) throw new Exception("O obxecto xa existe");
        datos.put((T) object.getkey(), object);
        try {
            ras=new RandomAccessFile(file(),"rw");
            Texto.linea("Longitud final fichero= "+ras.length());
            ras.seek(ras.length());            

            textoFormateado = String.format("%-998s", Serializador.serializar((Serializable) object));  
            ras.writeUTF(textoFormateado);
            System.out.println("final del dato grabado= "+ras.getFilePointer());


            ras.close();
        } catch (IOException ex) {
            System.out.println("Erro o grabar o arquivo");
        }
        
        
    }
    
    public void override (long puntero, J object) throws Exception{
        String textoFormateado;

        
        try {
            ras=new RandomAccessFile(file(),"rw");
            ras.seek(puntero);            
            textoFormateado = String.format("%-998s", Serializador.serializar((Serializable) object));  
            ras.writeUTF(textoFormateado);
            System.out.println("final del dato grabado= "+ras.getFilePointer());
            ras.close();
        } catch (IOException ex) {
            System.out.println("Erro o grabar o arquivo");
        }

    }

    
    public void delete (J object) throws Exception{
    if (datos.get(object.getkey())== null) throw new Exception("O cliente non existe");
    datos.remove((T) object.getkey());
}
    
    public J getObject (T key){
        return datos.get(key);
    }
        
    public ArrayList<T> getKey(){
        ArrayList<T> keys= new ArrayList<>();
        for (T i:datos.keySet()){
            keys.add(i);
        }
        return keys;
    }
    
    public void wipe(){
        File f = new File(file()); 
        f.delete();
        datos.clear();
    }
    
        
}
