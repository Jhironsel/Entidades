package sur.softsurena.datos.updateInsert;

import java.io.File;
import java.io.FileInputStream;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import static sur.softsurena.conexion.Conexion.getCnn;
import sur.softsurena.entidades.Datos_nacimiento;
import sur.softsurena.entidades.DetalleMotivoConsulta;
import sur.softsurena.entidades.Padre;

public class UpdateInsertMetodos {

    private static PreparedStatement ps;
    private static String sql;

    public synchronized String agregarDatosNacimiento(Datos_nacimiento dato) {
        try {
            sql = "UPDATE OR INSERT INTO V_DATOSNACIMIENTO "
                    + "(IDPACIENTE, FECHANACIMIENTO, PESONACIMIENTOKG, ALTURA, "
                    + "CESAREA, TIEMPOGESTACION, PC) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?);";

            ps = getCnn().prepareStatement(sql);

            ps.setInt(1, dato.getIdPaciente());
            ps.setString(2, dato.getFecha());
            ps.setString(3, dato.getPesoNacimiento());
            ps.setString(4, dato.getAltura());
            ps.setBoolean(5, dato.isCesarea());
            ps.setString(6, dato.getTiempoGestacion());
            ps.setString(7, dato.getPC());

            ps.executeUpdate();

            return "Datos guardado correctamente";

        } catch (SQLException ex) {
            //Instalar Logger
            return "Error al insertar datos de Nacimiento de: " + dato.getIdPaciente();
        }
    }
    
//    public String agregarOrModificarAcesso(int idAcceso, int idPerfil, Perfil miPerfil) {
//        try {
//            Statement st = cnn.createStatement();
//            st.execute(
//                    "UPDATE OR INSERT INTO Tabla_ACCESO2 (IDACCESO, IDPERFIL, ARCHIVOS, ARCHIVOSCLIENTES,"
//                    + "                    ARCHIVOSPRODUCTOS, ARCHIVOSUSUARIOS, ARCHIVOSCAMBIOCLAVE,"
//                    + "                    ARCHIVOSCAMBIOUSUARIO, ARCHIVOSSALIR, MOVIMIENTOS, "
//                    + "                    MOVIMIENTOSNUEVAFACTURA,MOVIMIENTOSREPORTEFACTURA, "
//                    + "                    MOVIMIENTOSINVENTARIO, MOVIMIENTOSABRILTURNO, "
//                    + "                    MOVIMIENTOSCERRARTURNO, MOVIMIENTOSDEUDA) "
//                    + "    VALUES (" + idAcceso + ", " + idPerfil + ", '"
//                    + miPerfil.getArchivos() + "', '"
//                    + miPerfil.getArchivosClientes() + "', '"
//                    + miPerfil.getArchivosProductos() + "', '"
//                    + miPerfil.getArchivosUsuarios() + "', '"
//                    + miPerfil.getArchivosCambioClave() + "', '"
//                    + miPerfil.getArchivosCambioUsuario() + "', '"
//                    + miPerfil.getArchivosSalir() + "', '"
//                    + miPerfil.getMovimientos() + "', '"
//                    + miPerfil.getMovimientosNuevaFactura() + "', '"
//                    + miPerfil.getMovimientosReporteFactura() + "', '"
//                    + miPerfil.getMovimientosInventarios() + "', '"
//                    + miPerfil.getMovimientosAbrirTurno() + "', '"
//                    + miPerfil.getMovimientosCerrarTurno() + "', '"
//                    + miPerfil.getMovimientosDeuda() + "') matching(IDACCESO);");
//            return "Acesso Agregado Correctamente";
//        } catch (SQLException ex) {
//            //Instalar Logger
//            return "Error al Insertar Acesso...";
//        }
//    }

    public synchronized static String agregarPadre(Padre padre) {
        try {
            sql = "UPDATE OR INSERT INTO V_PADRES (CEDULA, NOMBRES, APELLIDOS, "
                    + "SEXO, IDTIPOSANGRE, IDARS, NONSS, TELEFONO, MOVIL, "
                    + "CORREO, DIRECCION, CIUDAD, FECHANACIMIENTO, ESTADO) "
                    + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?) matching(CEDULA)";

            ps = getCnn().prepareStatement(sql);

            ps.setString(1, padre.getCedula().trim());
            ps.setString(2, padre.getNombres().trim());
            ps.setString(3, padre.getApellidos().trim());
            ps.setString(4, "" + padre.getSexo());
            ps.setInt(5, padre.getId_Tipo_Sangre());
            ps.setInt(6, padre.getId_Ars());
            ps.setString(7, (padre.getNoNSS().trim().isEmpty() ? null : padre.getNoNSS().trim()));
            ps.setString(11, padre.getDireccion().trim());
            ps.setDate(13, padre.getFecha_Nacimiento());
            ps.setBoolean(14, padre.getEstado());

            ps.executeUpdate();

            return "Padre agregado correctamente";
        } catch (SQLException ex) {
            //Instalar Logger
            return "Error al insertar Padre...";
        }
    }

    public synchronized static String agregarGuiaVigilancia(int idGVD, int idPaciente) {
        sql = "UPDATE OR INSERT INTO V_DETALLE_GUIA_VIGIL (ID_GVD,  IDPACIENTE) VALUES (?, ?)";
        try {
            ps = getCnn().prepareStatement(sql);
            
            ps.setInt(1, idGVD);
            ps.setInt(2, idPaciente);

            ps.executeUpdate();

            return "Guia de Desarrollo agregada correctamente";
        } catch (SQLException ex) {
            //Instalar Logger
            return "Error al insertar Guia de Vigilancia...";
        }
    }

    
    public synchronized static String agregarDetallleConsulta(DetalleMotivoConsulta dmc) {
        sql = "update or insert into T_DETALLEMOTIVOCONSULTA "
                            + "(IDCONSULTA, TURNO, IDMCONSULTA) "
                            + "values (?, ?, ?);";
        try {
            ps = getCnn().prepareStatement(sql);
            
            ps.setInt(1, dmc.getIdConsulta());
            ps.setInt(2, dmc.getTurno());
            ps.setInt(3, dmc.getIdMotivoConsulta());

            ps.executeUpdate();
            return "Detalles agregados correctamente";
        } catch (SQLException ex) {
            //Instalar Logger
            return "Error al insertar Detalle Consulta...";
        }
    }
    
}
