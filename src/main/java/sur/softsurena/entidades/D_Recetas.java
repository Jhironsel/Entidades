package sur.softsurena.entidades;

import java.math.BigDecimal;

public class D_Recetas {
    private final int id_receta;
    private final int linea;
    private final int id_Medicamento;
    private final BigDecimal cantidad;
    private final String detalleDosis;

    public D_Recetas(int id_receta, int linea, int id_Medicamento, 
            BigDecimal cantidad, String detalleDosis) {
        this.id_receta = id_receta;
        this.linea = linea;
        this.id_Medicamento = id_Medicamento;
        this.cantidad = cantidad;
        this.detalleDosis = detalleDosis;
    }

    public int getId_receta() {
        return id_receta;
    }

    public int getLinea() {
        return linea;
    }

    public int getId_Medicamento() {
        return id_Medicamento;
    }

    public BigDecimal getCantidad() {
        return cantidad;
    }

    public String getDetalleDosis() {
        return detalleDosis;
    }

    @Override
    public String toString() {
        return "D_Recetas{" + "id_receta=" + id_receta + ", linea=" + linea + ", id_Medicamento=" + id_Medicamento + ", cantidad=" + cantidad + ", detalleDosis=" + detalleDosis + '}';
    }
    
}
