package sur.softsurena.entidades;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public class Generales extends Personas{
    
    private final String cedula;
    private final Integer id_tipo_sangre;
    private final Character estado_civil;
    
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
    
    public static boolean cedula(String cedula) {
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
}
