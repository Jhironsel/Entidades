package sur.softsurena.entidades;

import java.math.BigDecimal;
import java.sql.Date;

public class Inscripcion {
    private final int id;
    private final int id_estudiante;
    private final int id_padre_tutor;
    private final int id_tanda;
    private final BigDecimal pago;
    private final Date fecha_inscripcion;
    private final String idUsuario;

    public Inscripcion(int id, int id_estudiante, int id_padre_tutor, 
            int id_tanda, BigDecimal pago, Date fecha_inscripcion, 
            String idUsuario) {
        this.id = id;
        this.id_estudiante = id_estudiante;
        this.id_padre_tutor = id_padre_tutor;
        this.id_tanda = id_tanda;
        this.pago = pago;
        this.fecha_inscripcion = fecha_inscripcion;
        this.idUsuario = idUsuario;
    }

    public int getId() {
        return id;
    }

    public int getId_estudiante() {
        return id_estudiante;
    }

    public int getId_padre_tutor() {
        return id_padre_tutor;
    }

    public int getId_tanda() {
        return id_tanda;
    }

    public BigDecimal getPago() {
        return pago;
    }

    public Date getFecha_inscripcion() {
        return fecha_inscripcion;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    @Override
    public String toString() {
        return "Inscripcion{" + "id=" + id + ", id_estudiante=" + id_estudiante + ", id_padre_tutor=" + id_padre_tutor + ", id_tanda=" + id_tanda + ", pago=" + pago + ", fecha_inscripcion=" + fecha_inscripcion + ", idUsuario=" + idUsuario + '}';
    }
    
    
    
}
