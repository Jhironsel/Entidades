package sur.softsurena.entidades;

import java.math.BigDecimal;

public class ARS {
    private final int id;
    private final String descripcion;
    private final BigDecimal covertura;
    private final Boolean estado;

    public ARS(int id, String descripcion, BigDecimal covertura, 
            Boolean estado) {
        this.id = id;
        this.descripcion = descripcion;
        this.covertura = covertura;
        this.estado = estado;
    }
    
    public ARS(int id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
        this.covertura = BigDecimal.ZERO;
        this.estado = true;
    }

    public int getId() {
        return id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public BigDecimal getCovertura() {
        return covertura;
    }

    public Boolean getEstado() {
        return estado;
    }    

    @Override
    public String toString() {
        return descripcion;
    }
}
