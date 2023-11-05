package sur.softsurena.enums;

import lombok.Getter;

@Getter
public enum Meses {
    ENERO("ene", "enero", "Enero", 1),
    FEBRERO("feb", "febrero", "Febrero", 2),
    MARZO("mar", "marzo", "Marzo", 3),
    ABRIL("abr", "abril", "Abril", 4),
    MAYO("may", "mayo", "Mayo", 5),
    JUNIO("jun", "junio", "Junio", 6),
    JULIO("jul", "julio", "Julio", 7),
    AGOSTO("ago", "agosto", "Agosto", 8),
    SEPTIEMBRE("sep", "septiembre", "Septiembre", 9),
    OCTUBRE("oct", "octobre", "Octubre", 10),
    NOVIEMBRE("nov", "noviembre", "Noviembre", 11),
    DICIEMBRE("dic", "diciembre", "Diciembre", 12);

    private final String abreviatura;
    private final String minuscula;
    private final String oracion;
    private final int orden;

    private Meses(String abreviatura, String minuscula, String oracion, int orden) {
        this.abreviatura = abreviatura;
        this.minuscula = minuscula;
        this.oracion = oracion;
        this.orden = orden;
    }

    public static void main(String[] args) {
        //        Implementacion de esta clase....
        Meses m = Enum.valueOf(Meses.class, "ENERO");
        System.out.println("Mes: "+m);
        System.out.println("Mes abreviatura: "+m.getAbreviatura());
        System.out.println("Mes minuscula: "+m.getMinuscula());
        System.out.println("Mes Oracion: "+m.getOracion());
        System.out.println("Mes Orden: "+m.getOrden());
    }

}
