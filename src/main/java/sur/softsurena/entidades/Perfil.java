package sur.softsurena.entidades;

public class Perfil {

    private final int idAcceso;
    private final String Rol;
    private final Boolean archivos;
    private final Boolean archivosClientes;
    private final Boolean archivosProductos;
    private final Boolean archivosUsuarios;
    private final Boolean archivosCambioClave;
    private final Boolean archivosCambioUsuario;
    private final Boolean archivosSalir;
    private final Boolean movimientos;
    private final Boolean movimientosNuevaFactura;
    private final Boolean movimientosReporteFactura;
    private final Boolean movimientosInventarios;
    private final Boolean movimientosAbrirTurno;
    private final Boolean movimientosCerrarTurno;
    private final Boolean movimientosDeuda;

    public Perfil(int idAcceso, String Rol, Boolean archivos,
            Boolean archivosClientes, Boolean archivosProductos,
            Boolean archivosUsuarios, Boolean archivosCambioClave,
            Boolean archivosCambioUsuario, Boolean archivosSalir,
            Boolean movimientos, Boolean movimientosNuevaFactura,
            Boolean movimientosReporteFactura, Boolean movimientosInventarios,
            Boolean movimientosAbrirTurno, Boolean movimientosCerrarTurno,
            Boolean movimientosDeuda) {
        this.idAcceso = idAcceso;
        this.Rol = Rol;
        this.archivos = archivos;
        this.archivosClientes = archivosClientes;
        this.archivosProductos = archivosProductos;
        this.archivosUsuarios = archivosUsuarios;
        this.archivosCambioClave = archivosCambioClave;
        this.archivosCambioUsuario = archivosCambioUsuario;
        this.archivosSalir = archivosSalir;
        this.movimientos = movimientos;
        this.movimientosNuevaFactura = movimientosNuevaFactura;
        this.movimientosReporteFactura = movimientosReporteFactura;
        this.movimientosInventarios = movimientosInventarios;
        this.movimientosAbrirTurno = movimientosAbrirTurno;
        this.movimientosCerrarTurno = movimientosCerrarTurno;
        this.movimientosDeuda = movimientosDeuda;
    }

    public int getIdAcceso() {
        return idAcceso;
    }

    public String getRol() {
        return Rol;
    }

    public Boolean getArchivos() {
        return archivos;
    }

    public Boolean getArchivosClientes() {
        return archivosClientes;
    }

    public Boolean getArchivosProductos() {
        return archivosProductos;
    }

    public Boolean getArchivosUsuarios() {
        return archivosUsuarios;
    }

    public Boolean getArchivosCambioClave() {
        return archivosCambioClave;
    }

    public Boolean getArchivosCambioUsuario() {
        return archivosCambioUsuario;
    }

    public Boolean getArchivosSalir() {
        return archivosSalir;
    }

    public Boolean getMovimientos() {
        return movimientos;
    }

    public Boolean getMovimientosNuevaFactura() {
        return movimientosNuevaFactura;
    }

    public Boolean getMovimientosReporteFactura() {
        return movimientosReporteFactura;
    }

    public Boolean getMovimientosInventarios() {
        return movimientosInventarios;
    }

    public Boolean getMovimientosAbrirTurno() {
        return movimientosAbrirTurno;
    }

    public Boolean getMovimientosCerrarTurno() {
        return movimientosCerrarTurno;
    }

    public Boolean getMovimientosDeuda() {
        return movimientosDeuda;
    }

}
