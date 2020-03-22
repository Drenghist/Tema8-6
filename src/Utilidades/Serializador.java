
package Utilidades;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Base64;

/**
 *
 * @author alex (basado no de xavi)
 */
public class Serializador {


    public static <J> J desSerializar(String string) throws Exception {
        byte[] array = Base64.getDecoder().decode(string);
        ByteArrayInputStream pepe = new ByteArrayInputStream(array);
        ObjectInputStream ois = new ObjectInputStream(pepe);
        J objeto=(J) ois.readObject();
        ois.close();
        return objeto;
   }

    public static String serializar(Serializable objeto) throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream( baos );
        oos.writeObject(objeto);
        oos.close();
        return Base64.getEncoder().encodeToString(baos.toByteArray()); 
    }
}
