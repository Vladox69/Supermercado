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
public class validacion {
    
    public static boolean validarTexto(String cadenas) {
        if (!(cadenas.matches("^[A-Za-z ]*$"))) {
            System.out.println("Debes ingresar texto");
            return true;
        } else {
            return false;
        }

    }
    
    public static boolean validarGenero(char genero) {
        if (!(genero == 'F' || genero == 'M')) {
             System.out.println("Solo F (femenino) o M (masculino)");
            return true;
        } else {
           
            return false;
        }
    }
    
    public static boolean validarEdadDependiente(int edad){
        if(!(edad>=18 && edad <=70)){
            System.out.println("No puedes emplear a alguien con esta edad");
            return true;
        }else{
            return false;
        }
    }
    
    public static boolean validarEdad(int edad){
        if(!(edad>=8&&edad<=99)){
            System.out.println("Tu edad esta por debajo o sobre los limites permitidos");
            return true;
        }else{
            return false;
        }
    }
    
    public static boolean validarDouble(double numero){
        if(!(numero>0)){
            System.out.println("No puedes ingresar valores negativos");
            return true;
        }else{
            return false;
        }
    }
    
    public static boolean validarEntero(int numero){
        if(!(numero>0)){
            System.out.println("No puedes ingresar valores negativos");
            return true;
        }else{
            return false;
        }
    }
    
}
