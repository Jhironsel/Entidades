package sur.softsurena.entidades;

import java.math.BigDecimal;
import java.util.Objects;

public class DetalleFactura {

    public static String INSERT_DETALLE_FACTURA
        = "INSERT INTO V_DETALLE_FACTURA (IDFACTURA, IDLINEA, IDPRODUCTO, PRECIO, CANTIDAD) "
        + "VALUES (?, ?, ?, ?, ?);";
    
    private final int idLinea;
    private final int idProducto;
    private final String descripcion;
    private final BigDecimal precio;
    private final BigDecimal cantidad;
    private final BigDecimal total;

    public DetalleFactura(Integer idFactura, int idLinea, int idProducto,
            String descripcion, BigDecimal precio, BigDecimal cantidad) {//Fin
        this.idLinea = idLinea;
        this.idProducto = idProducto;
        this.descripcion = descripcion;
        this.precio = precio;
        this.cantidad = cantidad;
        this.total = precio.multiply(cantidad);
    }

    public int getIdLinea() {
        return idLinea;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public BigDecimal getCantidad() {
        return cantidad;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + this.idLinea;
        hash = 59 * hash + this.idProducto;
        hash = 59 * hash + Objects.hashCode(this.descripcion);
        hash = 59 * hash + Objects.hashCode(this.precio);
        hash = 59 * hash + Objects.hashCode(this.cantidad);
        hash = 59 * hash + Objects.hashCode(this.total);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DetalleFactura other = (DetalleFactura) obj;
        if (this.idLinea != other.idLinea) {
            return false;
        }
        if (this.idProducto != other.idProducto) {
            return false;
        }
        if (!Objects.equals(this.descripcion, other.descripcion)) {
            return false;
        }
        if (!Objects.equals(this.precio, other.precio)) {
            return false;
        }
        if (!Objects.equals(this.cantidad, other.cantidad)) {
            return false;
        }
        return Objects.equals(this.total, other.total);
    }

    @Override
    public String toString() {
        return "DetalleFactura{"
                + "idLinea=" + idLinea
                + ", idProducto=" + idProducto
                + ", descripcion=" + descripcion
                + ", precio=" + precio
                + ", cantidad=" + cantidad
                + ", total=" + total
                + '}';
    }
}
