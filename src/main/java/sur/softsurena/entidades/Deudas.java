package sur.softsurena.entidades;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;

public class Deudas {

    private final int idDeudas;
    private final String idCliente;
    private final String idUsuario;
    private final String concepto;
    private final BigDecimal monto;
    private final Date fecha;
    private final Time hora;
    private final String estado;

    public Deudas(int idDeudas, String idCliente, String idUsuario,
            String concepto, BigDecimal monto, Date fecha, Time hora,
            String estado) {
        this.idDeudas = idDeudas;
        this.idCliente = idCliente;
        this.idUsuario = idUsuario;
        this.concepto = concepto;
        this.monto = monto;
        this.fecha = fecha;
        this.hora = hora;
        this.estado = estado;
    }

    public int getIdDeudas() {
        return idDeudas;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public String getConcepto() {
        return concepto;
    }

    public BigDecimal getMonto() {
        if (monto == null) {
            return new BigDecimal(0);
        }
        return monto;
    }

    public Date getFecha() {
        return fecha;
    }

    public Time getHora() {
        return hora;
    }

    public String getEstado() {
        return estado;
    }
}
