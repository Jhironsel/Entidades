package sur.softsurena.entidades;

public class Datos_nacimiento {

    private int idPaciente;
    private String fecha;
    private String pesoNacimiento;
    private String altura;
    private String PC;
    private boolean cesarea;
    private String tiempoGestacion;

    public Datos_nacimiento(int idPaciente, String fecha, String pesoNacimiento,
            String altura, boolean cesarea, String tiempoGestacion, String PC) {
        this.idPaciente = idPaciente;
        this.fecha = fecha;
        this.pesoNacimiento = pesoNacimiento;
        this.altura = altura;
        this.cesarea = cesarea;
        this.tiempoGestacion = tiempoGestacion;
        this.PC = PC;
    }

    public int getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(int idPaciente) {
        this.idPaciente = idPaciente;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getPesoNacimiento() {
        return pesoNacimiento;
    }

    public void setPesoNacimiento(String pesoNacimiento) {
        this.pesoNacimiento = pesoNacimiento;
    }

    public String getAltura() {
        return "" + (Double.parseDouble(altura) / 100);
    }

    public void setAltura(String altura) {
        this.altura = altura;
    }

    public boolean isCesarea() {
        return cesarea;
    }

    public void setCesarea(boolean cesarea) {
        this.cesarea = cesarea;
    }

    public String getTiempoGestacion() {
        return tiempoGestacion;
    }

    public void setTiempoGestacion(String tiempoGestacion) {
        this.tiempoGestacion = tiempoGestacion;
    }

    public String getPC() {
        return PC;
    }

    public void setPC(String PC) {
        this.PC = PC;
    }
}
