package tema8_6;

import Storage.Storable;
import java.io.Serializable;

/**
 *
 * @author Alex
 * @version 2.0 Lectura por registros
 * @since 02/04/2020
 */
public class Cliente implements Serializable, Storable {
    private String dni;
    private String nome;
    private String direccion;
    private double telefono;
    private boolean activo;
    private int deuda;
    
    /**
     *
     * @param dni Define o DNI do cliente
     * @param nome Define o nome do cliente
     * @param direccion Define o enderezo do cliente
     * @param telefono Define o teléfono do cliente (só díxitos)
     * @param deuda Define a deuda pendente do cliente
     */
    public Cliente (String dni, String nome, String direccion, double telefono, int deuda){
        this.dni=dni;
        this.nome=nome;
        this.direccion=direccion;
        this.telefono=telefono;
        this.activo = true;   
        this.deuda=deuda;
        
    }

    /**
     * @return retorna o dni
     */
    public String getDni() {
        return dni;
    }

    /**
     * @param dni modifica o valor do DNI
     */
    public void setDni(String dni) {
        this.dni = dni;
    }

    /**
     * @return retorna o nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome modifica o valor do nome
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return retorna o enderezo
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * @param direccion modifica o valor do enderezo
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * @return retorna o teléfono
     */
    public double getTelefono() {
        return telefono;
    }

    /**
     * @param telefono modifica o valor do teléfono (só díxitos)
     */
    public void setTelefono(double telefono) {
        this.telefono = telefono;
    }
    
    /**
     *
     * @return retorna o valor chave (o DNI)
     */
    @Override
    public String getkey(){
        return this.dni;
    }

    /**
     * @return retorna se o cliente está ou non activo
     */
    public boolean isActivo() {
        return activo;
    }

    /**
     * @param activo modifica o valor de activo do cliente
     */
    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    /**
     * @return retornar a dauda pendente do cliente
     */
    public int getDeuda() {
        return deuda;
    }

    /**
     * @param deuda modifica o valor da deuda
     */
    public void setDeuda(int deuda) {
        this.deuda = deuda;
    }
    
   
    
    
    
}
