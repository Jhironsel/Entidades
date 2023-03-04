package sur.softsurena.entidades;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidarCorreoTel {

    public static boolean correo(String correo) {
        Pattern ptr = Pattern.compile(
                "[\\w\\-\\_\\+]+(\\.[\\w\\-\\_]+)*@([A-za-z0-9-]+\\.)+[A-za-z]{2,4}");
        return ptr.matcher(correo).matches();
    }

    public static boolean telefono(String tel) {
        Pattern pat = Pattern.compile("[+][1][(][8][0|2|4][9][)]\\s\\d{3}-\\d{4}");
        Matcher mat = pat.matcher(tel.trim());
        return mat.matches();
    }

    public static boolean cedula(String cedula) {//012-0000000-0
        // Eliminar guiones y espacios en blanco
        cedula = cedula.replaceAll("[\\s-]+", "");

        final int[] MULTIPLOS = {2, 1, 2, 1, 2, 1, 2, 1, 2};

        // Verificar longitud
        if (cedula.length() != 11) {
            return false;
        }

        // Calcular suma de productos
        int suma = 0;
        for (int i = 0; i < 9; i++) {
            int digito = Character.digit(cedula.charAt(i), 10);
            int producto = digito * MULTIPLOS[i];
            suma += producto > 9 ? producto - 9 : producto;
        }

        // Verificar dígito verificador
        int digitoVerificador = Character.digit(cedula.charAt(10), 10);
        int resto = (10 - (suma % 10)) % 10;
        return digitoVerificador == resto;
    }

    public static String generarCedula() {
        int primerDigito = (int) (Math.random() * 4) + 1; // Los primeros dígitos de la cédula deben ser 1, 2, 3 o 4.
        int segundoDigito = (int) (Math.random() * 5); // El segundo dígito puede ser del 0 al 4.
        int tercerDigito = (int) (Math.random() * 10); // El tercer dígito puede ser cualquier número del 0 al 9.
        int cuartoDigito = (int) (Math.random() * 10); // El cuarto dígito puede ser cualquier número del 0 al 9.
        int quintoDigito = (int) (Math.random() * 10); // El quinto dígito puede ser cualquier número del 0 al 9.
        int sextoDigito = (int) (Math.random() * 10); // El sexto dígito puede ser cualquier número del 0 al 9.
        int septimoDigito = (int) (Math.random() * 10); // El séptimo dígito puede ser cualquier número del 0 al 9.
        int octavoDigito = (int) (Math.random() * 10); // El octavo dígito puede ser cualquier número del 0 al 9.
        int novenoDigito = (int) (Math.random() * 10); // El noveno dígito puede ser cualquier número del 0 al 9.

        int[] multiplicadores = {2, 1, 2, 1, 2, 1, 2, 1, 2};
        int suma = 0;
        for (int i = 0; i < multiplicadores.length; i++) {
            int digito = i == 0 ? primerDigito : i == 1 ? segundoDigito : i == 2 ? tercerDigito
                    : i == 3 ? cuartoDigito : i == 4 ? quintoDigito : i == 5 ? sextoDigito
                                            : i == 6 ? septimoDigito : i == 7 ? octavoDigito : novenoDigito;
            int producto = digito * multiplicadores[i];
            suma += producto > 9 ? producto - 9 : producto;
        }

        int ultimoDigito = (10 - (suma % 10)) % 10;

        return String.format("%d%d%d-0%d%d%d%d%d%d-%d", primerDigito, segundoDigito, tercerDigito, cuartoDigito, quintoDigito,
                sextoDigito, septimoDigito, octavoDigito, novenoDigito, ultimoDigito);
    }
    
    public static String generarTelMovil(){
        StringBuilder telefonoMovil = new StringBuilder();
        String codigoArea[] = {"809", "829", "849"};
        int indexArea = (int) (Math.random() * 3);
        
        int num1 = (int) (Math.random() * 10);
        int num2 = (int) (Math.random() * 10);
        int num3 = (int) (Math.random() * 10);
        int num4 = (int) (Math.random() * 10);
        int num5 = (int) (Math.random() * 10);
        int num6 = (int) (Math.random() * 10);
        int num7 = (int) (Math.random() * 10);
        
        telefonoMovil.
                append("+1(").
                append(codigoArea[indexArea]).
                append(") ").
                append(num1).
                append(num2).
                append(num3).
                append("-").
                append(num4).
                append(num5).
                append(num6).
                append(num7);
        
        
        return telefonoMovil.toString();
    }
    
    public static String generarCorreo(){
        StringBuilder telefonoMovil = new StringBuilder();
        String correoDominio[] = {"@hotmail.com", "@gmail.com", "@outlook.com", "@yahoo.com"};
        int indexArea = (int) (Math.random() * 4);
        
        int num1 = (int) (Math.random() * 10);
        int num2 = (int) (Math.random() * 10);
        int num3 = (int) (Math.random() * 10);
        int num4 = (int) (Math.random() * 10);
        
        telefonoMovil.
                append("correo_prueba_").
                append(num1).
                append(num2).
                append(num3).
                append(num4).
                append(correoDominio[indexArea]);
        
        
        return telefonoMovil.toString();
    }
}
