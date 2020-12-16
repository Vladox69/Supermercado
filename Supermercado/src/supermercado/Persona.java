/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package supermercado;

/**
 *
 * @author ASUS
 */
public class Persona {
    final String cedPer;
    String nomPer;
    String apePer;
    int edadPer;
    char sexPer;
    
    Persona(String cedPer,String nomPer,String apePer,int edadPer,char sexPer){
        this.cedPer=cedPer;
        this.nomPer=nomPer;
        this.apePer=apePer;
        this.edadPer=edadPer;
        this.sexPer=sexPer;
    }
    
    @Override
    public boolean equals(Object cedPer) {
        return (this.cedPer.equals(cedPer));
    }
    
    @Override
    public String toString() {
        return this.cedPer + "     " + this.nomPer + "     " + 
                this.apePer+"    "+this.edadPer+"    "+this.sexPer;
    }
}
