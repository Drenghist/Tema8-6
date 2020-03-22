/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tema8_6;

import Storage.Storable;
import java.io.Serializable;

/**
 *
 * @author Alex
 */
public class Cliente implements Serializable, Storable {
    private String dni;
    private String nome;
    private String direccion;
    private double telefono;
    private boolean activo;
    private int deuda;
    
    public Cliente (String dni, String nome, String direccion, double telefono, int deuda){
        this.dni=dni;
        this.nome=nome;
        this.direccion=direccion;
        this.telefono=telefono;
        this.activo = true;   
        this.deuda=deuda;
        
    }

    /**
     * @return the dni
     */
    public String getDni() {
        return dni;
    }

    /**
     * @param dni the dni to set
     */
    public void setDni(String dni) {
        this.dni = dni;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the direccion
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * @param direccion the direccion to set
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * @return the telefono
     */
    public double getTelefono() {
        return telefono;
    }

    /**
     * @param telefono the telefono to set
     */
    public void setTelefono(double telefono) {
        this.telefono = telefono;
    }
    
    @Override
    public String getkey(){
        return this.dni;
    }

    /**
     * @return the activo
     */
    public boolean isActivo() {
        return activo;
    }

    /**
     * @param activo the activo to set
     */
    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    /**
     * @return the deuda
     */
    public int getDeuda() {
        return deuda;
    }

    /**
     * @param deuda the deuda to set
     */
    public void setDeuda(int deuda) {
        this.deuda = deuda;
    }
    
   
    
    
    
}
