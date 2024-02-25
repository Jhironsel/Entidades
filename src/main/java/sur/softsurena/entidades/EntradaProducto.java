package sur.softsurena.entidades;

import java.math.BigDecimal;
import java.sql.Date;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
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
}
