package sur.softsurena.entidades;

public class TipoSangre {
    private final int id;
    private final String descripcion;

    public TipoSangre(int id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
    }

    public int getId() {
        return id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    @Override
    public String toString() {
        return descripcion;
    }
    
}
