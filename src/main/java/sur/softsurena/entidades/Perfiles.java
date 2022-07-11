package sur.softsurena.entidades;

public class Perfiles {

    private Integer IdPerfil;
    private String perfil;
    private Boolean frm_Padres;
    private Boolean frm_Padres_Registro;
    private Boolean frm_Padres_Modificar;
    private Boolean frm_Padres_Borrar;
    private Boolean frm_Estudiantes;
    private Boolean frm_Estudiantes_Registro;
    private Boolean frm_Estudiantes_Modificar;
    private Boolean frm_Estudiantes_Borrar;
    private Boolean frm_Usuarios;
    private Boolean frm_Usuarios_Registro;
    private Boolean frm_Usuarios_Modificar;
    private Boolean frm_Usuarios_Borrar;
    private Boolean frm_Pagos;
    private Boolean frm_Abono;
    private Boolean frm_Reportes;
    private Boolean frm_Ajuste_Tanda;
    private Boolean frm_Ajuste_Perfil_Usuario;

    public Perfiles(
            Integer IdPerfil, 
            String perfil, 
            Boolean frm_Padres,
            Boolean frm_Padres_Registro, 
            Boolean frm_Padres_Modificar,
            Boolean frm_Padres_Borrar, 
            Boolean frm_Estudiantes,
            Boolean frm_Estudiantes_Registro, 
            Boolean frm_Estudiantes_Modificar,
            Boolean frm_Estudiantes_Borrar, 
            Boolean frm_Usuarios,
            Boolean frm_Usuarios_Registro, 
            Boolean frm_Usuarios_Modificar,
            Boolean frm_Usuarios_Borrar, 
            Boolean frm_Pagos, 
            Boolean frm_Abono,
            Boolean frm_Reportes, 
            Boolean frm_Ajuste_Tanda,
            Boolean frm_Ajuste_Perfil_Usuario) {
        this.IdPerfil = IdPerfil;
        this.perfil = perfil;
        this.frm_Padres = frm_Padres;
        this.frm_Padres_Registro = frm_Padres_Registro;
        this.frm_Padres_Modificar = frm_Padres_Modificar;
        this.frm_Padres_Borrar = frm_Padres_Borrar;
        this.frm_Estudiantes = frm_Estudiantes;
        this.frm_Estudiantes_Registro = frm_Estudiantes_Registro;
        this.frm_Estudiantes_Modificar = frm_Estudiantes_Modificar;
        this.frm_Estudiantes_Borrar = frm_Estudiantes_Borrar;
        this.frm_Usuarios = frm_Usuarios;
        this.frm_Usuarios_Registro = frm_Usuarios_Registro;
        this.frm_Usuarios_Modificar = frm_Usuarios_Modificar;
        this.frm_Usuarios_Borrar = frm_Usuarios_Borrar;
        this.frm_Pagos = frm_Pagos;
        this.frm_Abono = frm_Abono;
        this.frm_Reportes = frm_Reportes;
        this.frm_Ajuste_Tanda = frm_Ajuste_Tanda;
        this.frm_Ajuste_Perfil_Usuario = frm_Ajuste_Perfil_Usuario;
    }

    public Integer getIdPerfil() {
        return IdPerfil;
    }

    public void setIdPerfil(Integer IdPerfil) {
        this.IdPerfil = IdPerfil;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    public Boolean getFrm_Padres() {
        return frm_Padres;
    }

    public void setFrm_Padres(Boolean frm_Padres) {
        this.frm_Padres = frm_Padres;
    }

    public Boolean getFrm_Padres_Registro() {
        return frm_Padres_Registro;
    }

    public void setFrm_Padres_Registro(Boolean frm_Padres_Registro) {
        this.frm_Padres_Registro = frm_Padres_Registro;
    }

    public Boolean getFrm_Padres_Modificar() {
        return frm_Padres_Modificar;
    }

    public void setFrm_Padres_Modificar(Boolean frm_Padres_Modificar) {
        this.frm_Padres_Modificar = frm_Padres_Modificar;
    }

    public Boolean getFrm_Padres_Borrar() {
        return frm_Padres_Borrar;
    }

    public void setFrm_Padres_Borrar(Boolean frm_Padres_Borrar) {
        this.frm_Padres_Borrar = frm_Padres_Borrar;
    }

    public Boolean getFrm_Estudiantes() {
        return frm_Estudiantes;
    }

    public void setFrm_Estudiantes(Boolean frm_Estudiantes) {
        this.frm_Estudiantes = frm_Estudiantes;
    }

    public Boolean getFrm_Estudiantes_Registro() {
        return frm_Estudiantes_Registro;
    }

    public void setFrm_Estudiantes_Registro(Boolean frm_Estudiantes_Registro) {
        this.frm_Estudiantes_Registro = frm_Estudiantes_Registro;
    }

    public Boolean getFrm_Estudiantes_Modificar() {
        return frm_Estudiantes_Modificar;
    }

    public void setFrm_Estudiantes_Modificar(Boolean frm_Estudiantes_Modificar) {
        this.frm_Estudiantes_Modificar = frm_Estudiantes_Modificar;
    }

    public Boolean getFrm_Estudiantes_Borrar() {
        return frm_Estudiantes_Borrar;
    }

    public void setFrm_Estudiantes_Borrar(Boolean frm_Estudiantes_Borrar) {
        this.frm_Estudiantes_Borrar = frm_Estudiantes_Borrar;
    }

    public Boolean getFrm_Usuarios() {
        return frm_Usuarios;
    }

    public void setFrm_Usuarios(Boolean frm_Usuarios) {
        this.frm_Usuarios = frm_Usuarios;
    }

    public Boolean getFrm_Usuarios_Registro() {
        return frm_Usuarios_Registro;
    }

    public void setFrm_Usuarios_Registro(Boolean frm_Usuarios_Registro) {
        this.frm_Usuarios_Registro = frm_Usuarios_Registro;
    }

    public Boolean getFrm_Usuarios_Modificar() {
        return frm_Usuarios_Modificar;
    }

    public void setFrm_Usuarios_Modificar(Boolean frm_Usuarios_Modificar) {
        this.frm_Usuarios_Modificar = frm_Usuarios_Modificar;
    }

    public Boolean getFrm_Usuarios_Borrar() {
        return frm_Usuarios_Borrar;
    }

    public void setFrm_Usuarios_Borrar(Boolean frm_Usuarios_Borrar) {
        this.frm_Usuarios_Borrar = frm_Usuarios_Borrar;
    }

    public Boolean getFrm_Pagos() {
        return frm_Pagos;
    }

    public void setFrm_Pagos(Boolean frm_Pagos) {
        this.frm_Pagos = frm_Pagos;
    }

    public Boolean getFrm_Abono() {
        return frm_Abono;
    }

    public void setFrm_Abono(Boolean frm_Abono) {
        this.frm_Abono = frm_Abono;
    }

    public Boolean getFrm_Reportes() {
        return frm_Reportes;
    }

    public void setFrm_Reportes(Boolean frm_Reportes) {
        this.frm_Reportes = frm_Reportes;
    }

    public Boolean getFrm_Ajuste_Tanda() {
        return frm_Ajuste_Tanda;
    }

    public void setFrm_Ajuste_Tanda(Boolean frm_Ajuste_Tanda) {
        this.frm_Ajuste_Tanda = frm_Ajuste_Tanda;
    }

    public Boolean getFrm_Ajuste_Perfil_Usuario() {
        return frm_Ajuste_Perfil_Usuario;
    }

    public void setFrm_Ajuste_Perfil_Usuario(Boolean frm_Ajuste_Perfil_Usuario) {
        this.frm_Ajuste_Perfil_Usuario = frm_Ajuste_Perfil_Usuario;
    }
}
