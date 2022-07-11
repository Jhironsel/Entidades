package sur.softsurena.entidades;

import java.sql.Date;

public class Consultas {
    private final int id;
    private final int id_paciente;
    private final int id_motivo_consulta;
    private final int id_control_consulta;
    private final Date fecha;
    private final int turno;
    private final Boolean estado;
    private final String usuario;

    public Consultas(int id, int id_paciente, int id_motivo_consulta, 
            int id_control_consulta, Date fecha, int turno, Boolean estado, 
            String usuario) {
        this.id = id;
        this.id_paciente = id_paciente;
        this.id_motivo_consulta = id_motivo_consulta;
        this.id_control_consulta = id_control_consulta;
        this.fecha = fecha;
        this.turno = turno;
        this.estado = estado;
        this.usuario = usuario;
    }

    public int getId() {
        return id;
    }

    public int getId_paciente() {
        return id_paciente;
    }

    public int getId_motivo_consulta() {
        return id_motivo_consulta;
    }

    public int getId_control_consulta() {
        return id_control_consulta;
    }

    public Date getFecha() {
        return fecha;
    }

    public int getTurno() {
        return turno;
    }

    public Boolean getEstado() {
        return estado;
    }

    public String getUsuario() {
        return usuario;
    }

    @Override
    public String toString() {
        return "CONSULTAS{" + 
                "id=" + id + 
                ", id_paciente=" + id_paciente + 
                ", id_motivo_consulta=" + id_motivo_consulta + 
                ", id_control_consulta=" + id_control_consulta + 
                ", fecha=" + fecha + 
                ", turno=" + turno + 
                ", estado=" + estado + 
                ", usuario=" + usuario + 
                '}';
    }
    
    
}
