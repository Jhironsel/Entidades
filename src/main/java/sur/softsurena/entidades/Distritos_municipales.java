package sur.softsurena.entidades;

public class Distritos_municipales {

    private int id;
    private String nombre;
    private int idMunicipio;

    public Distritos_municipales(int id, String nombre, int idMunicipio) {
        this.id = id;
        this.nombre = nombre;
        this.idMunicipio = idMunicipio;
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

    public int getIdMunicipio() {
        return idMunicipio;
    }

    public void setIdMunicipio(int idMunicipio) {
        this.idMunicipio = idMunicipio;
    }

    @Override
    public String toString() {
        return nombre;
    }

}
