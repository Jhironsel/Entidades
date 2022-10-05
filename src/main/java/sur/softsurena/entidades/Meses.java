package sur.softsurena.entidades;

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
    
    private final String abrev;
    private final String min;
    private final String ora;
    private int orden = 0;
    
    private Meses(String abrev, String min, String ora, int orden){
        this.abrev = abrev;
        this.min = min;
        this.ora = ora;
        this.orden = orden;
    }
    
    private Meses(String abrev, String min, String ora){
        this.abrev = abrev;
        this.min = min;
        this.ora = ora;
    }
    
    public String getAbreviatura(){
        return abrev;
    }
    public String getMinuscula(){
        return min;
    }
    public String getOracion(){
        return ora;
    }
    public int getOrden(){
        return orden;
    }
}

//        Implementacion de esta clase....
//        Meses m = Enum.valueOf(Meses.class, "ENERO");
//        System.out.println("Mes: "+m);
//        System.out.println("Mes abreviatura: "+m.getAbreviatura());
//        System.out.println("Mes minuscula: "+m.getMinuscula());
//        System.out.println("Mes Oracion: "+m.getOracion());
//        System.out.println("Mes Orden: "+m.getOrden());