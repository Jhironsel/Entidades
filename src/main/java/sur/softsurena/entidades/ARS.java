package sur.softsurena.entidades;

import java.math.BigDecimal;
import lombok.NonNull;

public class ARS {

    public static String DELETE
            = "DELETE FROM V_ARS WHERE id = ?";

    private final Integer id;
    @NonNull
    private final String descripcion;
    @NonNull
    private final BigDecimal covertura;
    @NonNull
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
