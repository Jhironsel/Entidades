package sur.softsurena.entidades;

public class Sexo {
    private final char letra;
    private final String nombre;

    public Sexo(char letra, String nombre) {
        this.letra = letra;
        this.nombre = nombre;
    }

    public char getLetra() {
        return letra;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return nombre;
    }
}
