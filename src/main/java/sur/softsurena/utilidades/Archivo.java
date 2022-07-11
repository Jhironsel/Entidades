package sur.softsurena.utilidades;

import java.io.*;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Archivo {
    public static Boolean crearArchivo(String ruta){
        File archivo = new File(ruta);
        try {
            PrintWriter salida = new PrintWriter(archivo);
            salida.close();
            return true;
        } catch (FileNotFoundException ex) {
            ex.printStackTrace(System.out);
        }
        return false;
    }
    
    public static Boolean escribirArchivo(String ruta, String contenido){
        File archivo = new File(ruta);
        try {
            PrintWriter salida = new PrintWriter(archivo);
            salida.println(contenido);
            salida.println();//Agregar espacio al final y en blanco.
            salida.close();
            return true;
        } catch (FileNotFoundException ex) {
            ex.printStackTrace(System.out);
        }
        return false;
    }
    
    public static Boolean leerArchivo(String nombreArchivo){
        File archivo = new File(nombreArchivo);
        try {
            BufferedReader entrada = new BufferedReader(new FileReader(archivo));
            String lectura = entrada.readLine();
            while(lectura != null){
                System.out.println(lectura);
                lectura = entrada.readLine();
            }
            entrada.close();
            return true;
        } catch (FileNotFoundException ex) {
            ex.printStackTrace(System.out);
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }
        return false;
    }
    
    public static Boolean anexarArchivo(String ruta, String anexo){
        File archivo = new File(ruta);
        try {
            PrintWriter salida = new PrintWriter(new FileWriter(archivo, true));
            salida.println(anexo);
            salida.println();//Agregar un espacio en blanco al final
            salida.close();
            return true;
        } catch (FileNotFoundException ex) {
            ex.printStackTrace(System.out);
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }
        return false;
    }
}
