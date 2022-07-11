package sur.softsurena.entidades;

public class Permisos {

    private final Boolean consultar;
    private final Boolean insertar;
    private final Boolean actualizar;
    private final Boolean borrar;
    private final String vista;
    private final String loginUser;

    public Permisos(Boolean consultar, Boolean insertar, Boolean actualizar, 
            Boolean borrar, String vista, String loginUser) {
        this.consultar = consultar;
        this.insertar = insertar;
        this.actualizar = actualizar;
        this.borrar = borrar;
        this.vista = vista;
        this.loginUser = loginUser;
    }

    public Boolean getConsultar() {
        return consultar;
    }

    public Boolean getInsertar() {
        return insertar;
    }

    public Boolean getActualizar() {
        return actualizar;
    }

    public Boolean getBorrar() {
        return borrar;
    }

    public String getVista() {
        return vista;
    }

    public String getLoginUser() {
        return loginUser;
    }

    @Override
    public String toString() {
        return "Permisos{" + "consultar=" + consultar + ", insertar=" + insertar + ", actualizar=" + actualizar + ", borrar=" + borrar + ", vista=" + vista + ", loginUser=" + loginUser + '}';
    }
}
