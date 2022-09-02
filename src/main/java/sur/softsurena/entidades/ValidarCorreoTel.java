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
    
    public static boolean cedula(String tel) {//012-0000000-0
        Pattern pat = Pattern.compile("\\d{3}-\\d{7}-\\d{1}");
        Matcher mat = pat.matcher(tel.trim());
        return mat.matches();
    }
}
