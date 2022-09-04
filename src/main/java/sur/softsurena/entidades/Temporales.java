package sur.softsurena.entidades;

public class Temporales {

    public static String GET_TEMPORALES
            = "SELECT r.ID_FACTURA, r.NOMBRE_TEMP, r.PNOMBRE, r.SNOMBRE, r.APELLIDOS, r.FECHA, "
            + "     r.IDUSUARIO, r.HORA, r.ID_TURNO, r.EFECTIVO, r.CAMBIO, r.ESTADO_FACTURA, "
            + "     r.MONTO, r.ID_CLIENTE "
            + "FROM GET_TEMPORALES r"
            + "ORDER BY 1";

}
