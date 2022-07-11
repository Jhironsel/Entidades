package sur.softsurena.entidades;

import java.math.BigDecimal;
import java.sql.Date;

public class EntradaProducto {
    private final Integer id;
    private final Integer idProvedor; //Identificador del proveedor
    private final String cod_factura; //Encabezado de la factura.
    private final int linea; //Linea consecutiva de la entrada de producto
    private final int idProducto; //Identificador del producto. 
    private final BigDecimal entrada;
    private final Date fechaVecimiento;
    private final Boolean estado;
    private final String idUsuairo;
    private final String rol;

    public EntradaProducto(Integer id, Integer idProvedor, String cod_factura, int linea, 
            int idProducto, BigDecimal entrada, Date fechaVecimiento, 
            Boolean estado, String idUsuairo, String rol) {
        this.id = id;
        this.idProvedor = idProvedor;
        this.cod_factura = cod_factura;
        this.linea = linea;
        this.idProducto = idProducto;
        this.entrada = entrada;
        this.fechaVecimiento = fechaVecimiento;
        this.estado = estado;
        this.idUsuairo = idUsuairo;
        this.rol = rol;
    }

    

    public Integer getId() {
        return id;
    }

    public Integer getIdProvedor() {
        return idProvedor;
    }

    public int getLinea() {
        return linea;
    }

    public String getCod_factura() {
        return cod_factura;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public BigDecimal getEntrada() {
        return entrada;
    }

    public Date getFechaVecimiento() {
        return fechaVecimiento;
    }

    public Boolean getEstado() {
        return estado;
    }

    public String getIdUsuairo() {
        return idUsuairo;
    }

    public String getRol() {
        return rol;
    }

    @Override
    public String toString() {
        return "EntradaProducto{" + 
                    "id=" + id + 
                    ", idProvedor=" + idProvedor + 
                    ", linea=" + linea + 
                    ", cod_factura=" + cod_factura + 
                    ", idProducto=" + idProducto + 
                    ", entrada=" + entrada + 
                    ", fechaVecimiento=" + fechaVecimiento + 
                    ", estado=" + estado + 
                    ", idUsuairo=" + idUsuairo + 
                    ", rol=" + rol + 
                '}';
    }
    
    
    
    
}
