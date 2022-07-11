package sur.softsurena.entidades;

public class Provincia {
    private int id;
    private String nombre;
    private String zona;

    public Provincia(int id, String nombre, String zona) {
        this.id = id;
        this.nombre = nombre;
        this.zona = zona;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getZona() {
        return zona;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }

    @Override
    public String toString() {
        return nombre;
    }    
}
