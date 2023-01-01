package sur.softsurena.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public enum Dias {
    LUNES("Lun", "lunes", "Lunes", 1),
    MARTES("Mar", "martes", "Martes", 2),
    MIERCOLES("Mie", "miercoles", "Miercoles", 3),
    JUEVES("Jue", "jueves", "Jueves", 4),
    VIERNES("Vie", "viernes", "Viernes", 5),
    SABADO("Sab", "sabado", "Sabados", 6),
    DOMINGO("Dom", "domingo", "Domingos", 7);
    
    private final String abreviatura;
    private final String minuscula;
    private final String oracion;
    private final int orden;

    private Dias(String abreviatura, String minuscula, String oracion, int orden) {
        this.abreviatura = abreviatura;
        this.minuscula = minuscula;
        this.oracion = oracion;
        this.orden = orden;
    }
}
