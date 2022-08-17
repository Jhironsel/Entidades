package sur.softsurena.datos.update;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.codec.binary.Base64;
import static sur.softsurena.conexion.Conexion.getCnn;
import static sur.softsurena.datos.procedure.ProcedureMetodos.pagoCumplido;
import sur.softsurena.entidades.ARS;
import sur.softsurena.entidades.Categorias;
import sur.softsurena.entidades.Clientes;
import sur.softsurena.entidades.ContactosTel;
import sur.softsurena.entidades.Control_Consulta;
import sur.softsurena.entidades.Estudiantes;
import sur.softsurena.entidades.Facturas;
import sur.softsurena.entidades.Medicamentos;
import sur.softsurena.entidades.Paciente;
import sur.softsurena.entidades.Padres;
import sur.softsurena.entidades.Perfiles;
import sur.softsurena.entidades.Producto;
import sur.softsurena.entidades.Proveedores;
import sur.softsurena.entidades.Tandas;
import sur.softsurena.utilidades.Utilidades;

public class UpdateMetodos {

    private static final Logger LOG = Logger.getLogger(UpdateMetodos.class.getName());
    private static String sql;
    private static PreparedStatement ps;
    
    

    /**
     * Crear el procedimiento para modificar los clientes del sistema.
     * @param c
     * @return 
     */
    public synchronized static String modificarCliente(Clientes c, ContactosTel[] cc) {
        try {
            sql = "update Tabla_CLIENTES "
                    + "set NOMBRES = ?, "
                    + "    APELLIDOS = ?, "
                    + "    CIUDAD = ?, "
                    + "    DIRECCION = ?, "
                    + "    TELEFONO = ?, "
                    + "    FECHANACIMIENTO = ?, "
                    + "    ESTADO = ?  "
                    + "where (ID = ?)";

            ps = getCnn().prepareStatement(sql);

            ps.setString(1, c.getNombres());
            ps.setString(2, c.getApellidos());
            ps.setString(4, c.getDireccion());
            ps.setDate(6, Utilidades.javaDateToSqlDate(c.getFecha_Nacimiento()));
            ps.setBoolean(7, c.getEstado());
            ps.setInt(8, c.getId());

            ps.executeUpdate();

            return "Cliente Modificado Correctamente";
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return "Error al Modificar Cliente...";
        }
    }

    /**
     * Metodo que permite modificar las facturas que se encuentran en el sistema
     * 
     * Me cuestiono si campos como idCliente efectivo cambio deberian de estar aqui?
     * 
     * Metodo Actualizado el 18/05/2022.
     * 
     * @param f Objeto de la clase Factura.
     * @return retorna true si fue modificada y false si hubo un error en la
     * modificacion de la factura.
     */
    public synchronized static boolean modificarFactura(Facturas f) {
        try {
            ps = getCnn().prepareStatement(
                    "UPDATE V_FACTURAS a SET "
                    + "    a.ID_CLIENTE = ?, "
                    + "    a.EFECTIVO = ?, "
                    + "    a.CAMBIO = ?, "
                    + "    a.ESTADO = ?, "
                    + "    a.NOMBRETEMP = ? "
                    + "WHERE "
                    + "    a.ID = ?");

            ps.setInt(1, f.getHeaderFactura().getIdCliente());
            ps.setBigDecimal(2, f.getHeaderFactura().getEfectivo());
            ps.setBigDecimal(3, f.getHeaderFactura().getCambio());
            ps.setString(4, "" + f.getHeaderFactura().getEstado());
            ps.setString(5, f.getHeaderFactura().getNombreTemp());
            ps.setInt(5, f.getId());

            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return false;
        }
    }

    /**
     * Este metodo es utilizado para modificar las categorias de los productos. 
     * En este se puede modificar la descripción y la imagen de la categoria.
     * 
     * Actualizacion dia 09 julio 2022: Nota, Este metodo se modifica para que
     * devuelta valores de tipo String que indique si el registro fue modificado
     * o no. 
     * 
     * @param c Este objecto de la clase Categoria del sistema.
     * 
     * @return Retorna un valor de tipo String que indica si la operación se 
     * realizo con exito si o no.
     * 
     */
    public synchronized static String modificarCategoria(Categorias c) {
        try {
            ps = getCnn().prepareStatement(Categorias.UPDATE);

            ps.setString(1, c.getDescripcion());
            ps.setString(2, Utilidades.imagenEncode64(c.getPathImage()));
            ps.setInt(3, c.getId());

            ps.executeUpdate();
            return "Se modificó la categoria correctamente.";
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return "Error al modificar la categoria.";
        }
    }
    
    public String modificarT_Perfil(int idPerfil, String descripcion) {
        try {
            sql = "UPDATE T_PERFIL a "
                    + "SET "
                    + "    a.DESCRIPCION = ? "
                    + "WHERE "
                    + "    a.IDPERFIL = " + idPerfil;
            
            ps = getCnn().prepareStatement(sql);
            
            ps.setString(1, descripcion);
            ps.setInt(2, idPerfil);
            
            int r = ps.executeUpdate();
            return "Perfil Modificado Correctamente. {"+r+"}";
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return "Error al Modificar Perfil";
        }
    }

    public synchronized static String modificarOpcionMensaje(String mensaje,
            String nombreEmpresa, String direccionEmpresa,
            String telefonoEmpresa) {

        try {
            ps = getCnn().prepareStatement(
                    "UPDATE OR INSERT INTO OPCIONES(OPCION, RUTA) "
                    + "VALUES('MensajeTickes', ?) "
                    + "matching(OPCION);");

            ps.setString(1, mensaje);
            ps.executeUpdate();

            ps = getCnn().prepareStatement(
                    "UPDATE OR INSERT INTO OPCIONES(OPCION, RUTA) "
                    + "VALUES('nombreEmpresa', ?) "
                    + "matching(OPCION);");
            ps.setString(1, nombreEmpresa);
            ps.executeUpdate();

            ps = getCnn().prepareStatement(
                    "UPDATE OR INSERT INTO OPCIONES(OPCION, RUTA) "
                    + "VALUES('direccionEmpresa', ?) "
                    + "matching(OPCION);");
            ps.setString(1, direccionEmpresa);
            ps.executeUpdate();

            ps = getCnn().prepareStatement(
                    "UPDATE OR INSERT INTO OPCIONES(OPCION, RUTA) "
                    + "VALUES('telefonoEmpresa', ?) "
                    + "matching(OPCION);");
            ps.setString(1, telefonoEmpresa);
            ps.executeUpdate();

            return "Opciones de mensaje modificada.";
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return "Error al Modificar opciones de mensaje...";
        }
    }
    
    /**
     * Metodo que permite modificar los productos del sistema como a la categoria
     * a la que pertenece el producto, su codigo de barra, la descripcion, 
     * la imagen de este, la nota del producto y su estado. 
     * 
     * Metodo actualizado el dia 23 de abril, segun la vista productos.
     * 
     * @param El obj p perteneciente a la clase Producto, define los productos
     * del sistema.
     * @return 
     */
    public synchronized static String modificarProducto(Producto p) {
        try {
            ps = getCnn().prepareStatement(Producto.UPDATE);

            ps.setInt(1, p.getIdCategoria());
            ps.setString(2, p.getCodigo());
            ps.setString(3, p.getDescripcion());
            ps.setString(4, Utilidades.imagenEncode64(p.getPathImagen()));
            ps.setString(5, p.getNota());
            ps.setBoolean(6, p.getEstado());
            ps.setInt(7, p.getId());
            
            ps.executeUpdate();
            return "Producto Modificado Correctamente";
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return "Error al Modificar Producto...";
    }
    
    
    /**
     * 
     * @param deuda
     * @param idCliente
     * @param Valor
     * @param idFactura
     * @param idTurno 
     */
    public synchronized static void pagarCredito(BigDecimal deuda,
            String idCliente, BigDecimal Valor, int idFactura, int idTurno) {

        BigDecimal deudaActual = deuda.subtract(Valor);

        sql = "update clientes set deudaActual = ? where idCliente = ?;";
        
        try {
            ps = getCnn().prepareStatement(sql);

            ps.setBigDecimal(1, deudaActual);
            ps.setString(2, idCliente);

            ps.executeUpdate();

        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
        }

        sql = "INSERT INTO PAGODEUDA (IDCLIENTE, IDFACTURA, IDTURNO, MONTOPAGO, ESTADO) "
                + "VALUES ( ?, ?, ?, ?, 'A');";
        try {
            ps = getCnn().prepareStatement(sql);

            ps.setString(1, idCliente);
            ps.setInt(2, idFactura);
            ps.setInt(3, idTurno);
            ps.setBigDecimal(4, Valor);

            ps.executeUpdate();
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
        }

        pagoCumplido(idFactura);
    }
    

    public synchronized static String modificarPaciente(Paciente miPaciente) {
        try {
            ps = getCnn().prepareStatement(sql);

            ps.executeUpdate(
                    "UPDATE V_PACIENTES "
                    + "SET "
                    + "    IDPADREMADRE = ? , "
                    + "    IDPADREPADRE = ? , "
                    + "    CEDULA = ? , "
                    + "    NOMBRES = ? , "
                    + "    APELLIDOS = ? , "
                    + "    SEXO = ? , "
                    + "    IDTIPOSANGRE = ? , "
                    + "    IDARS = ? , "
                    + "    NONSS = ? , "
                    + "    ESTADO = ? "
                    + "WHERE"
                    + "    IDPACIENTE = ?");

            ps.setInt(1, miPaciente.getIdPadre());
            ps.setInt(2, miPaciente.getIdMadre());
            ps.setString(3, miPaciente.getCedula());
            ps.setString(4, miPaciente.getNombres());
            ps.setString(5, miPaciente.getApellidos());
            ps.setString(6, "" + miPaciente.getSexo());
            ps.setInt(7, miPaciente.getId_Tipo_Sangre());
            ps.setInt(8, miPaciente.getId_Ars());
            ps.setString(9, miPaciente.getNoNSS());
            ps.setBoolean(10, miPaciente.getEstado());
            ps.setInt(11, miPaciente.getId());

            return "Paciente modificado correctamente";
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return "Error al modificar cliente...";
        }
    }

    public synchronized String modificarAntecedentePadre(int idAntecedente,
            String descrpcion) {
        sql = "update V_ANTEC_PADRES "
                + " set DESCRIPCION = ? "
                + " where idantecedente = ?";

        try {
            ps = getCnn().prepareStatement(sql);

            ps.setString(1, descrpcion);
            ps.setInt(2, idAntecedente);

            ps.executeUpdate();

            return "Paciente modificado correctamente";
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return "Error al modificar cliente...";
        }
    }

    public synchronized String modificarAntecedentePaciente(int idAntecedente,
            String descripcion) {
        sql = "update V_ANTEC_PACIENTES "
                + " set DESCRIPCION = ? "
                + " where idantecedente = ?;";
        try {
            ps = getCnn().prepareStatement(sql);

            ps.setString(1, descripcion);
            ps.setInt(2, idAntecedente);

            ps.executeUpdate();

            return "Paciente modificado correctamente";
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return "Error al modificar cliente...";
        }
    }

    public synchronized static String modificarSeguro(ARS ars) {
        sql = "update V_ARS set "
                + " DESCRIPCION = ? , "
                + " COVERCONSULTAPORC = ?, "
                + " ESTADO = ? "
                + " where idArs = ? ;";

        try {
            ps = getCnn().prepareStatement(sql);

            ps.setString(1, ars.getDescripcion());
            ps.setBigDecimal(2, ars.getCovertura());
            ps.setBoolean(3, ars.getEstado());
            ps.setInt(4, ars.getId());

            ps.executeUpdate();
            return "Seguro modificado correctamente";
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return "Error al modificar seguro...";
        }
    }

    public synchronized static String modificarPadre(Padres p) {
        try {
            sql = "EXECUTE PROCEDURE SP_UPDATE_PADRES (?, ?, ?, ?, ?, ?, ?, ?, ?"
                    + ", ?, ?, ?, ?, ?, ?, ?, ?);";

            ps = getCnn().prepareStatement(sql);

            ps.setInt(1, p.getId());
            ps.setInt(2, p.getAsegurado().getId_ars());
            ps.setString(3, p.getAsegurado().getNo_nss());
            ps.setInt(4, p.getId_Provincia());
            ps.setInt(5, p.getId_Municipio());
            ps.setInt(6, p.getId_Distrito_Municipal());
            ps.setInt(7, p.getId_Codigo_Postal());
            ps.setInt(8, p.getId_Tipo_Sangre());
            ps.setString(9, p.getCedula());
            ps.setString(10, p.getPNombre());
            ps.setString(11, p.getSNombre());
            ps.setString(12, p.getApellidos());
            ps.setString(13, "" + p.getSexo());
            ps.setString(14, p.getDireccion());
            ps.setDate(15, p.getFecha_Nacimiento());
            ps.setBoolean(16, p.getEstado());
            ps.setString(17, "" + p.getEstado_Civil());

            ps.executeUpdate();
            return "Padre modificado correctamente";
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return "Error al modificar padre...";
        }
    }

    public synchronized String modificarControlConsulta(Control_Consulta miCC) {
        try {
            sql = "update V_CONTROL_CONSULTA set "
                    + "    IDUSUARIO = ? , "
                    + "    CANTIDADPACIENTE = ? , "
                    + "    DIA = ? , "
                    + "    INICIAL = ? , "
                    + "    FINAL = ? "
                    + "where IDCONTROLCONSULTA = ? ";

            ps = getCnn().prepareStatement(sql,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY,
                    ResultSet.HOLD_CURSORS_OVER_COMMIT);

            ps.setInt(1, miCC.getId());
            ps.setInt(2, miCC.getCantidad());
            ps.setString(3, miCC.getDia());
            ps.setTime(4, miCC.getInicial());
            ps.setTime(5, miCC.getFinall());
            ps.setInt(6, miCC.getId());

            ps.executeUpdate(sql);
            return "Consulta modificado correctamente";
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return "Error al modificar consulta...";
        }
    }

    public synchronized static String modificarProveedor(Proveedores p) {
        try {
            sql = "UPDATE V_PROVEEDORES SET "
                    + "CODIGO_PROVEEDOR = ? , "
                    + "NOMBRE_PROVEEDOR = ? , "
                    + "TELEFONO_PROVEEDOR = ? , "
                    + "ESTADO = ? "
                    + "WHERE ID = ? ";

            ps = getCnn().prepareStatement(sql);

            ps.setString(1, p.getCodigoProveedor());
            ps.setString(2, p.getPNombre());
            ps.setString(3, p.getSNombre());
            ps.setBoolean(5, p.getEstado());
            ps.setInt(6, p.getId_persona());

            ps.executeUpdate(sql);
            return "Consulta modificado correctamente";
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return "Error al modificar consulta...";
        }
    }

    public synchronized static String modificarMedicamento(Medicamentos m) {
        sql = "UPDATE V_MEDICAMENTOS "
                + "SET "
                + "IDPROVEEDOR = ?, "
                + "DESCRIPCION = ?, "
                + "FOTO = ?, "
                + "ESTADO = ? "
                + "WHERE IDMedicamento = ?";

        try {
            FileInputStream imageInFile = null;
            String imageDataString = null;
            if (m.getPathImagen() != null) {
                imageInFile = new FileInputStream(m.getPathImagen());
                byte imageData[] = new byte[(int) m.getPathImagen().length()];
                imageInFile.read(imageData);
                // Converting Image byte array into Base64 String
                imageDataString = Base64.encodeBase64URLSafeString(imageData);
            }
            PreparedStatement ps = null;

            ps = getCnn().prepareStatement(sql);

            ps.setInt(1, m.getId_proveedor());
            ps.setString(2, m.getDescripcion());
            ps.setString(3, Utilidades.imagenEncode64(m.getPathImagen()));
            ps.setBoolean(4, m.isEstado());
            ps.setInt(5, m.getId());

            ps.executeUpdate();

            return "Medicamento modificado correctamente";
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
        } catch (FileNotFoundException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
        } catch (IOException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return "Error al modificar Medicamento...";
    }

    /**
     * Metodos que permiten borrar registros de la base de datos.
     * 
     */
    public synchronized static String borrarPaciente(String cedula) {
        try {
            sql = "UPDATE V_PACIENTES "
                + "SET "
                + "    ESTADO = FALSE "
                + "WHERE "
                + "    CEDULA = ?";
            
            ps = getCnn().prepareStatement(sql);
            
            ps.setString(1, cedula);
            
            ps.executeUpdate();
            return "Paciente borrado correctamente";
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return "Error al borrar paciente...";
        }
    }

    /**
     * 
     * @param cedula
     * @return 
     */
    public synchronized static String borrarPadre(String cedula) {
        try {
            sql = "UPDATE V_Padres "
                    + "SET "
                    + "    ESTADO = FALSE "
                    + "WHERE "
                    + "    CEDULA = ?";
            
            ps = getCnn().prepareStatement(sql);
            
            ps.setString(1, cedula);
            
            ps.executeUpdate(
                    );
            return "Borrado o inactivo correctamente";
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return "Error al borrar padre...";
        }
    }
    
    public String modificarPerfil(Perfiles p) {
        try {
            
            ps = getCnn().prepareStatement(Perfiles.UPDATE);
            
            ps.setBoolean(1, p.getCLIENTE_SELECT());
            ps.setBoolean(2, p.getCLIENTE_INSERT());
            ps.setBoolean(3, p.getCLIENTE_UPDATE());
            ps.setBoolean(4, p.getCLIENTE_DELETE());
            
            ps.setBoolean(5, p.getPRODUCTO_SELECT());
            ps.setBoolean(6, p.getPRODUCTO_INSERT());
            ps.setBoolean(7, p.getPRODUCTO_UPDATE());
            ps.setBoolean(8, p.getPRODUCTO_DELETE());
            
            ps.setBoolean(9, p.getUSUARIO_SELECT());
            ps.setBoolean(10, p.getUSUARIO_INSERT());
            ps.setBoolean(11, p.getUSUARIO_UPDATE());
            ps.setBoolean(12, p.getUSUARIO_DELETE());
            
            ps.setBoolean(13, p.getCAMBIO_CLAVE());
            
            ps.setBoolean(14, p.getFACTURA_SELECT());
            ps.setBoolean(15, p.getFACTURA_INSERT());
            ps.setBoolean(16, p.getFACTURA_UPDATE());
            ps.setBoolean(17, p.getFACTURA_DELETE());
            
            ps.setBoolean(18, p.getREPORTES_SELECT());
            
            ps.setBoolean(19, p.getINVENTARIOS_SELECT());
            
            ps.setBoolean(20, p.getTURNO_SELECT());
            ps.setBoolean(21, p.getTURNO_INSERT());
            ps.setBoolean(22, p.getTURNO_UPDATE());
            ps.setBoolean(23, p.getTURNO_DELETE());
            
            ps.setBoolean(24, p.getDEUDAS_SELECT());
            ps.setBoolean(25, p.getDEUDAS_INSERT());
            ps.setBoolean(26, p.getDEUDAS_UPDATE());
            ps.setBoolean(27, p.getDEUDAS_DELETE());
            
            ps.setInt(28, p.getId());
            
            
            
            ps.execute(sql);
            return "Perfil Modificado Correctamente.";
        } catch (SQLException ex) {
            
            return "Perfil no puedo ser Modificado.";
        }
    }

    public String modificarUbicaciones(String[] u) {
        try {
            sql = "update UBICACION_REPORTES set "
                    + "patch = '" + u[0] + "' where DESCRIPCION LIKE 'ReporteAlumnos'";
            ps = getCnn().prepareStatement(sql);
            ps.executeUpdate(sql);

            sql = "update UBICACION_REPORTES set "
                    + "patch = '" + u[1] + "' where DESCRIPCION LIKE 'ReporteAlumnosAgrupados'";
            ps = getCnn().prepareStatement(sql);
            ps.executeUpdate(sql);

            sql = "update UBICACION_REPORTES set "
                    + "patch = '" + u[4] + "' where DESCRIPCION LIKE 'Recibo'";
            ps = getCnn().prepareStatement(sql);
            ps.executeUpdate(sql);

            sql = "update UBICACION_REPORTES set "
                    + "patch = '" + u[5] + "' where DESCRIPCION LIKE 'Deuda'";
            ps = getCnn().prepareStatement(sql);
            ps.executeUpdate(sql);

            //----------------------------------------------------------------------
            sql = "update UBICACION_REPORTES set "
                    + "Dinero = " + u[2] + " where DESCRIPCION LIKE 'Mensualidad'";
            ps = getCnn().prepareStatement(sql);
            ps.executeUpdate(sql);

            sql = "update UBICACION_REPORTES set "
                    + "Dinero = " + u[3] + " where DESCRIPCION LIKE 'PagoInscripcion'";
            ps = getCnn().prepareStatement(sql);
            ps.executeUpdate(sql);

            return "Ubicaciones Actualizada";
        } catch (SQLException ex) {
            
            return "No se Realizaron las Actualizaciones";
        }
    }

    public String modificarEstudiante(Estudiantes e) {
        /*Metodo para modificar los estudiante del sistema de ballet
        Actualizado el 23 de abril del 2022.
        */
        try {
            sql = "EXECUTE PROCEDURE SP_UPDATE_ESTUDIANTE (?, ?, ?, ?, ?, ?, ?,"
                    + " ?, ?, ?, ?);";
            
            ps = getCnn().prepareStatement(sql);
            
            ps.setInt(1, e.getId());
            ps.setInt(2, e.getIdPadre());
            ps.setInt(3, e.getIdMadre());
            ps.setInt(4, e.getIdTutor());
            ps.setInt(5, e.getJcb_parentesco());
            ps.setString(6, e.getPNombre());
            ps.setString(7, e.getSNombre());
            ps.setString(8, e.getApellidos());
            ps.setString(9, ""+e.getSexo());
            ps.setDate(10, e.getFecha_Nacimiento());
            ps.setBoolean(11, e.getEstado());
            
            ps.executeUpdate();
            return "Estudiante Modificado Correctamente...!!!";
        } catch (SQLException ex) {
            return "Estudiante no pudo ser Modificado, Conctate SoftSureña...!!!";
        }
    }

    public String modificarTanda(Tandas t) {
        try {
            sql = "update TANDAS set "
                    + "ANNO_INICIAL = '" + t.getAnno_inicial() + "', "
                    + "ANNO_FINAL = '" + t.getAnno_final() + "', "
                    + "HORA_INICIO = '" + t.getHora_inicial() + "', "
                    + "HORA_FINAL = '" + t.getHora_final() + "', "
                    + "LUNES = " + t.getLunes() + ", "
                    + "MARTES = " + t.getMartes() + ", "
                    + "MIERCOLES = " + t.getMiercoles() + ", "
                    + "JUEVES = " + t.getJueves() + ", "
                    + "VIERNES = " + t.getViernes() + ", "
                    + "SABADOS = " + t.getSabados() + ", "
                    + "DOMINGOS = " + t.getDomingos() + ", "
                    + "CANTIDAD_ESTUDIANTES = " + t.getCantidad_estudiantes() + ", "
                    + "EDAD_MINIMA = " + t.getEdad_minima() + ", "
                    + "EDAD_MAXIMA = " + t.getEdad_maxima() + ", "
                    + "CON_EDAD = " + t.getCon_edad() + ", "
                    + "ESTADO = 0 "
                    + "where (ID_TANDA = " + t.getId_tanda() + ") ";
            ps = getCnn().prepareStatement(sql);
            ps.executeUpdate(sql);
            return "Tanda Modificado Correctamente...!!!";
        } catch (SQLException ex) {
            
            return "Tanda no pudo ser Modificado, Conctate SoftSureña...!!!" + ex.getMessage();
        }
    }
    
    
    
    
    
    
    
    
    
    
    
    
    

    
    
    
    
    
    
    
//    public String modificarAcceso(String idAcesso, Perfil miPerfil) {
//        try {
//            sql = "EXECUTE BLOCK AS BEGIN "+
//                    
//                        "UPDATE ACCESO SET " +
//                        "PERMISO = '"+miPerfil.getArchivos()+"'" +
//                        "WHERE idPerfil = '"+idAcesso+"' and nombre_frm like 'Archivos';" +
//                    
//                        "UPDATE ACCESO SET " +
//                        "PERMISO = '"+miPerfil.getClientes()+"'" +
//                        "WHERE idPerfil = '"+idAcesso+"' and nombre_frm like 'Clientes';" +
//                    
//                        "UPDATE ACCESO SET " +
//                        "PERMISO = '"+miPerfil.getProductos()+"'" +
//                        "WHERE idPerfil = '"+idAcesso+"' and nombre_frm like 'Productos';" +
//                    
//                        "UPDATE ACCESO SET " +
//                        "PERMISO = '"+miPerfil.getProveedores()+"'" +
//                        "WHERE idPerfil = '"+idAcesso+"' and nombre_frm like 'Proveedores';" +
//                    
//                        "UPDATE ACCESO SET " +
//                        "PERMISO = '"+miPerfil.getUsuarios()+"'" +
//                        "WHERE idPerfil = '"+idAcesso+"' and nombre_frm like 'Usuarios';" +
//                    
//                        "UPDATE ACCESO SET " +
//                        "PERMISO = '"+miPerfil.getUsuariosCrearMod()+"'" +
//                        "WHERE idPerfil = '"+idAcesso+"' and nombre_frm like 'UsuariosCrear';" +
//                    
//                        "UPDATE ACCESO SET " +
//                        "PERMISO = '"+miPerfil.getPerfil()+"'" +
//                        "WHERE idPerfil = '"+idAcesso+"' and nombre_frm like 'Perfil';" +
//                    
//                        "UPDATE ACCESO SET " +
//                        "PERMISO = '"+miPerfil.getCambioClave()+"'" +
//                        "WHERE idPerfil = '"+idAcesso+"' and nombre_frm like 'CambioClave';" +
//                    
//                        "UPDATE ACCESO SET " +
//                        "PERMISO = '"+miPerfil.getCambioUsuario()+"'" +
//                        "WHERE idPerfil = '"+idAcesso+"' and nombre_frm like 'CambioUsuario';" +
//                    
//                        "UPDATE ACCESO SET " +
//                        "PERMISO = '"+miPerfil.getSalir()+"'" +
//                        "WHERE idPerfil = '"+idAcesso+"' and nombre_frm like 'Salir';" +
//                    
//                        "UPDATE ACCESO SET " +
//                        "PERMISO = '"+miPerfil.getMovimiento()+"'" +
//                        "WHERE idPerfil = '"+idAcesso+"' and nombre_frm like 'Movimiento';" +
//                    
//                        "UPDATE ACCESO SET " +
//                        "PERMISO = '"+miPerfil.getNuevaFactura()+"'" +
//                        "WHERE idPerfil = '"+idAcesso+"' and nombre_frm like 'NuevaFactura';" +
//                    
//                        "UPDATE ACCESO SET " +
//                        "PERMISO = '"+miPerfil.getReporteFactura()+"'" +
//                        "WHERE idPerfil = '"+idAcesso+"' and nombre_frm like 'ReporteFactura';" +
//                    
//                        "UPDATE ACCESO SET " +
//                        "PERMISO = '"+miPerfil.getDatosEmpresa()+"'" +
//                        "WHERE idPerfil = '"+idAcesso+"' and nombre_frm like 'DatosEmpresa'; "
//                    + "END";
//            
//            ps = getCnn().prepareStatement(sql);
//            
//            ps.executeUpdate(sql);
//            
//            return "Cliente Modificado Correctamente";
//        } catch (SQLException ex) {
//            LOG.log(Level.SEVERE, ex.getMessage(), ex);
//            return "Error al Modificar Cliente...";
//        }
//    }
    
    
    

    

    

    
    
}
