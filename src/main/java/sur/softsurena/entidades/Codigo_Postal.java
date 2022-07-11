package sur.softsurena.entidades;

public class Codigo_Postal {
    private int id;
    private int idProvincia;
    private String localidad;
    private int codigo_postal;

    public Codigo_Postal(int id, int idProvincia, String localidad, int codigo_postal) {
        this.id = id;
        this.idProvincia = idProvincia;
        this.localidad = localidad;
        this.codigo_postal = codigo_postal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdProvincia() {
        return idProvincia;
    }

    public void setIdProvincia(int idProvincia) {
        this.idProvincia = idProvincia;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public int getCodigo_postal() {
        return codigo_postal;
    }

    public void setCodigo_postal(int codigo_postal) {
        this.codigo_postal = codigo_postal;
    }

    @Override
    public String toString() {
        return localidad;
    }
}
