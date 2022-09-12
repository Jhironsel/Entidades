package sur.softsurena.datos.updateInsert;

import java.io.File;
import java.io.FileInputStream;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import static sur.softsurena.conexion.Conexion.getCnn;
import sur.softsurena.entidades.Datos_nacimiento;
import sur.softsurena.entidades.DetalleMotivoConsulta;
import sur.softsurena.entidades.E_S_SYS;
import sur.softsurena.entidades.Padres;
import sur.softsurena.utilidades.Utilidades;

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
    

    public synchronized static String agregarPadre(Padres p) {
        try {
            sql = "UPDATE OR INSERT INTO V_PADRES (CEDULA, NOMBRES, APELLIDOS, "
                    + "SEXO, IDTIPOSANGRE, IDARS, NONSS, TELEFONO, MOVIL, "
                    + "CORREO, CIUDAD, FECHANACIMIENTO, ESTADO) "
                    + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?) matching(CEDULA)";

            ps = getCnn().prepareStatement(sql);

            ps.setString(1, p.getGenerales().getCedula().trim());
            ps.setString(2, p.getGenerales().getPNombre().trim());
            ps.setString(3, p.getApellidos().trim());
            ps.setString(4, "" + p.getSexo());
            ps.setInt(5, p.getGenerales().getId_tipo_sangre());
            ps.setInt(6, p.getAsegurado().getId_ars());
            ps.setString(7, (p.getAsegurado().getNo_nss().trim().isEmpty() ? null : p.getAsegurado().getNo_nss().trim()));
            ps.setDate(8, p.getFecha_nacimiento());
            ps.setBoolean(9, p.getEstado());

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
    
    public synchronized static boolean insertLogo(File file){
        
        try {
            ps = getCnn().prepareStatement(E_S_SYS.INSERT_LOGO);
            ps.setString(1, Utilidades.imagenEncode64(file));
            return ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(UpdateInsertMetodos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
}