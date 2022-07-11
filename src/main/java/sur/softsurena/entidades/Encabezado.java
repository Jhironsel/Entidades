package sur.softsurena.entidades;

public class Encabezado {
    private final String nombreEmpresa;
    private final String direccionEmpresa;
    private final String telefonoEmpresa;
    private final String mensajeEmpresa;

    public Encabezado(String nombreEmpresa, String direccionEmpresa, 
            String telefonoEmpresa, String mensajeEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
        this.direccionEmpresa = direccionEmpresa;
        this.telefonoEmpresa = telefonoEmpresa;
        this.mensajeEmpresa = mensajeEmpresa;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public String getDireccionEmpresa() {
        return direccionEmpresa;
    }

    public String getTelefonoEmpresa() {
        return telefonoEmpresa;
    }

    public String getMensajeEmpresa() {
        return mensajeEmpresa;
    }

    @Override
    public String toString() {
        return "Encabezado{" + "nombreEmpresa=" + nombreEmpresa + ", direccionEmpresa=" + direccionEmpresa + ", telefonoEmpresa=" + telefonoEmpresa + ", mensajeEmpresa=" + mensajeEmpresa + '}';
    }
}
