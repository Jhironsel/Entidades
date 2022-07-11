package sur.softsurena.entidades;

import java.math.BigDecimal;

public class Consultas_aprobadas {

    private final int id;
    private final String codAutorizacion;
    private final BigDecimal costo;
    private final BigDecimal descuento;
    private final String usuario;
    private final BigDecimal totalCosto;

    public Consultas_aprobadas(int id, String codAutorizacion, BigDecimal costo,
            BigDecimal descuento, String usuario, BigDecimal totalCosto) {

        this.id = id;
        this.codAutorizacion = codAutorizacion;
        this.costo = costo;
        this.descuento = descuento;
        this.usuario = usuario;
        this.totalCosto = totalCosto;
    }

    public int getId() {
        return id;
    }

    public String getCodAutorizacion() {
        return codAutorizacion;
    }

    public BigDecimal getCosto() {
        return costo;
    }

    public BigDecimal getDescuento() {
        return descuento;
    }

    public String getUsuario() {
        return usuario;
    }

    public BigDecimal getTotalCosto() {
        return totalCosto;
    }

    @Override
    public String toString() {
        return "ConsultasAprobadas{"
                + "id=" + id
                + ", codAutorizacion=" + codAutorizacion
                + ", costo=" + costo
                + ", descuento=" + descuento
                + ", usuario=" + usuario
                + ", totalCosto=" + totalCosto + '}';
    }

}
