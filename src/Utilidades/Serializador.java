package Utilidades;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Base64;

/**
 *
 * @author Alex (basado no de Xavi)
 * @version 2.0 Lectura por registros
 * @since 02/04/2020
 */
public class Serializador {

    /**
     *
     * @param string cadea a desserializar
     * @return retorna o obxecto serializado anteriormente
     * @throws Exception
     */
    public static <J> J desSerializar(String string) throws Exception {
        byte[] array = Base64.getDecoder().decode(string);
        ByteArrayInputStream pepe = new ByteArrayInputStream(array);
        ObjectInputStream ois = new ObjectInputStream(pepe);
        J objeto=(J) ois.readObject();
        ois.close();
        return objeto;
   }

    /**
     *
     * @param objeto obxecto a serializar
     * @return retorna o string do obxecto serializado
     * @throws Exception
     */
    public static String serializar(Serializable objeto) throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream( baos );
        oos.writeObject(objeto);
        oos.close();
        return Base64.getEncoder().encodeToString(baos.toByteArray()); 
    }
}
