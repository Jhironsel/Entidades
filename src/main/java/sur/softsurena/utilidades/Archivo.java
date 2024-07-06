package sur.softsurena.utilidades;

import java.io.*;
import java.util.logging.Level;
import lombok.experimental.UtilityClass;
import static sur.softsurena.utilidades.Utilidades.LOG;

@UtilityClass
public class Archivo {
    public static Boolean crearArchivo(String ruta){
        File archivo = new File(ruta);
        try {
            PrintWriter salida = new PrintWriter(archivo);
            salida.close();
            return true;
        } catch (FileNotFoundException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return false;
    }
    
    public static Boolean escribirArchivo(String ruta, String contenido){
        File archivo = new File(ruta);
        try (PrintWriter salida = new PrintWriter(archivo);){
            salida.println(contenido);
            salida.println();//Agregar espacio al final y en blanco.
            salida.close();
            return true;
        } catch (FileNotFoundException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return false;
    }
    
    public static Boolean leerArchivo(String nombreArchivo){
        File archivo = new File(nombreArchivo);
        try (BufferedReader entrada = new BufferedReader(new FileReader(archivo));){
            String lectura = entrada.readLine();
            while(lectura != null){
                LOG.info(lectura);
                lectura = entrada.readLine();
            }
            entrada.close();
            return true;
        } catch (FileNotFoundException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
        } catch (IOException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return false;
    }
    
    public static Boolean anexarArchivo(String ruta, String anexo){
        File archivo = new File(ruta);
        try (PrintWriter salida = new PrintWriter(new FileWriter(archivo, true));){
            salida.println(anexo);
            salida.println();//Agregar un espacio en blanco al final
            salida.close();
            return true;
        } catch (FileNotFoundException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
        } catch (IOException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return false;
    }
}
