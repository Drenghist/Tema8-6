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
 * @author Alex (basado no de Xavi)
 * @version 2.0 Lectura por registros
 * @since 02/04/2020
 * @param <T> Tipo de dato principal a gardar
 * @param <J> chave do dato gardado
 */
public abstract class  PersistentHashMap <T, J extends Storable> {

    /**
     * créase o HashMap (memoria volátil) onde gardar os datos
     */
    protected HashMap <T, J> datos = new HashMap <>();
    RandomAccessFile ras;
    J ctemp;
    
    /**
     * Constructor por defecto. Chama á función load sen argumentos
     */
    public PersistentHashMap (){
        //--------------------
        load();
        
        //--------------------
        
    }
    
    /**
     * Construtor con parámetros. Chama á función load con filtros
     * @param object obxecto a ignorar na carga de datos no HashMap
     */
    public PersistentHashMap (J object){
        //--------------------
        load(object);
        
        //--------------------
        
    }
    
    /**
     *
     * @return O nome do ficheiro
     */
    public String file(){
        return "file.dat";
    }
    
    /**
     * Carga os datos do ficheiro e os introduce no HashMap (memoria volátil)
     */
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
    
    /**
     * Carga os datos do ficheiro e os introduce no HashMap (memoria volátil)
     * @param object obxecto a ignorar no proceso de carga
     */
    public void load (J object){
        File f = new File(file()); 
        if (f.exists()){
            try{
            ras=new RandomAccessFile(file(),"r");
            ras.seek(0);
            while (ras.length()>ras.getFilePointer()){
                ctemp = Serializador.desSerializar(ras.readUTF().trim());
                if (!ctemp.getkey().equals("nulo"))
                datos.put((T) ctemp.getkey(),ctemp);
            } 
            ras.close();
            }catch (Exception ex){
                System.out.println("Erro o cargar o ficheiro");
            }
            
        }
        
        //--------------------
    }
    
    /**
     * Localiza a posición en memoria dun ficheiro
     * @param object Obxecto proporcionado para ser localizado
     * @return retorna a posición en memoria do comezo dese obxecto
     */
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
    
    /**
     *
     * @param object graba o obxecto no HashMap e no ficheiro
     * @throws Exception
     */
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
    
    /**
     *
     * @param puntero Indica a posición onde sobreescribir o obxecto
     * @param object Indica o obxecto a escribir nesa posición
     * @throws Exception
     */
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

    /**
     * Elimina un obxecto do HashMap
     * @param object Obxecto a eliminar do HashMap
     * @throws Exception
     */
    public void delete (J object) throws Exception{
    if (datos.get(object.getkey())== null) throw new Exception("O cliente non existe");
    datos.remove((T) object.getkey());
}
    
    /**
     * A partir dunha chave, retorna o obxecto asociado
     * @param key chave (identificador) do obxecto a buscar
     * @return Retorna o obxecto buscado
     */
    public J getObject (T key){
        return datos.get(key);
    }
        
    /**
     * 
     * @return Retorna un ArrayList das chaves dos obxectos
     */
    public ArrayList<T> getKey(){
        ArrayList<T> keys= new ArrayList<>();
        for (T i:datos.keySet()){
            keys.add(i);
        }
        return keys;
    }
    
    /**
     * Borra o ficheiro
     */
    public void wipe(){
        File f = new File(file()); 
        f.delete();
        datos.clear();
    }
    
        
}
