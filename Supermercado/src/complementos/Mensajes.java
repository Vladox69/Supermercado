/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package complementos;

/**
 *
 * @author ASUS
 */
public class Mensajes {
    
    public enum INGRESAR{
        OPCION ("Opción: "),
        CEDULA ("Cédula:"),
        NOMBRE ("Nombre: "),
        APELLIDO ("Apellido: "),
        SEXO ("Sexo: "),
        EDAD ("Edad: "),
        COD_DEP ("Código: "),
        CONT_DEP ("Contraseña: "),
        CANT ("Cantidad: "),
        FEC_VEN ("Fecha: "),;
        private  String mensaje;
        
        private INGRESAR(String msj){
            this.mensaje=msj;
        }
        
        public String msj(){
            return this.mensaje;
        }
        
    }
    
    
    
}
