package sur.softsurena.entidades;

public class EstadoCivil {
    private int id;
    private char letra;
    private String descripcion;

    public EstadoCivil(int id, char letra, String descripcion) {
        this.id = id;
        this.letra = letra;
        this.descripcion = descripcion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public char getLetra() {
        return letra;
    }

    public void setLetra(char letra) {
        this.letra = letra;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return descripcion;
    }

}
