package sur.softsurena.datos.update;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.codec.binary.Base64;
import static sur.softsurena.conexion.Conexion.getCnn;
import sur.softsurena.entidades.ARS;
import sur.softsurena.entidades.Categorias;
import sur.softsurena.entidades.Clientes;
import sur.softsurena.entidades.ContactosEmail;
import sur.softsurena.entidades.ContactosTel;
import sur.softsurena.entidades.Control_Consulta;
import sur.softsurena.entidades.Direcciones;
import sur.softsurena.entidades.Estudiantes;
import sur.softsurena.entidades.Facturas;
import sur.softsurena.entidades.Medicamentos;
import sur.softsurena.entidades.Paciente;
import sur.softsurena.entidades.Padres;
import sur.softsurena.entidades.Producto;
import sur.softsurena.entidades.Proveedores;
import sur.softsurena.entidades.Resultados;
import sur.softsurena.entidades.Tandas;
import sur.softsurena.utilidades.Utilidades;

public class UpdateMetodos {

    private static final Logger LOG = Logger.getLogger(UpdateMetodos.class.getName());
    private static String sql;
    
    private static void rollBack() {
        try {
            getCnn().rollback();
        } catch (SQLException ex1) {
            LOG.log(Level.SEVERE, ex1.getMessage(), ex1);
        }
    }

    /**
     * Metodo que permite modificar a los clientes del sistema de facturacion.
     *
     * @param c Este objeto se almacenan los numeros de contactos telefonicos.
     * 
     * @param cc En este objeto se almacenan los correos electronicos del 
     * cliente.
     * 
     * @return retorna un objecto de la clase resultado los cuales se envian lo
     * que es el mensaje, id y la cantidad de registro afetados. 
     */
    public synchronized static Resultados modificarCliente(Clientes c,
            List<ContactosTel> ct, List<ContactosEmail> ce) {

        Resultados r;
        try ( PreparedStatement ps = getCnn().prepareStatement(Clientes.UPDATE)) {

            ps.setInt(1, c.getId_persona());
            ps.setString(2, String.valueOf(c.getPersona()));
            ps.setString(3, c.getGenerales().getCedula());
            ps.setString(4, c.getPNombre());
            ps.setString(5, c.getSNombre());
            ps.setString(6, c.getApellidos());
            ps.setString(7, String.valueOf(c.getSexo()));
            ps.setDate(8, c.getFecha_nacimiento());
            ps.setBoolean(9, c.getEstado());
            ps.setString(10, String.valueOf(c.getGenerales().getEstado_civil()));

            int cant = ps.executeUpdate();

            if (!modificarContactosTel(c.getId_persona(), ct)) {
                r = Resultados.builder().
                        id(-1).
                        mensaje("Error al modificar contactos telefonico del cliente.").
                        cantidad(-1).build();
                return r;
            }

            if (!modificarContactosEmail(c.getId_persona(), ce)) {
                r = Resultados.builder().
                        id(-1).
                        mensaje("Error al agregar contactos correos electronicos del cliente.").
                        cantidad(-1).build();
                return r;
            }

            if (!modificarDirecciones(c.getId_persona(), c.getDireccion())) {
                r = Resultados.builder().
                        id(-1).
                        mensaje("Error al agregar direcciones del cliente").
                        cantidad(-1).build();
                return r;
            }

            r = Resultados.builder().
                    id(-1).
                    mensaje("Cliente Modificado Correctamente").
                    cantidad(cant).build();

            return r;
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            r = Resultados.builder().
                    id(-1).
                    mensaje("Error al Modificar Cliente...").
                    cantidad(-1).build();
            return r;
        }
    }

    /**
     * Metodo para agregar numeros telefonicos de las personas del sistema.
     *
     * @param id
     * @param contactos
     * @return
     */
    public static boolean modificarContactosTel(int id, List<ContactosTel> contactos) {
        try ( PreparedStatement ps = getCnn().prepareStatement(ContactosTel.UPDATE)) {

            for (ContactosTel c : contactos) {
                ps.setString(1, c.getTelefono());
                ps.setString(2, c.getTipo());
                ps.setInt(3, id);
                ps.addBatch();
            }
            ps.executeBatch();
            return true;
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
        }

        return false;
    }

    public static boolean modificarDirecciones(int id, List<Direcciones> direcciones) {

        try ( PreparedStatement ps = getCnn().prepareStatement(Direcciones.UPDATE)) {

            getCnn().setAutoCommit(false);

            for (Direcciones d : direcciones) {
                ps.setInt(1, d.getProvincia().getId());
                ps.setInt(2, d.getMunicipio().getId());
                ps.setInt(3, d.getDistrito_municipal().getId());
                ps.setString(4, d.getDireccion());
                ps.setInt(5, id);
                ps.addBatch();
            }

            ps.executeBatch();

            getCnn().commit();

            return true;
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            rollBack();
        }
        return false;
    }

    /**
     *
     * @param id
     * @param contactos
     * @return
     */
    public static boolean modificarContactosEmail(int id, List<ContactosEmail> contactos) {
        try ( PreparedStatement ps = getCnn().prepareStatement(ContactosEmail.UPDATE)) {

            for (ContactosEmail c : contactos) {
                ps.setString(1, c.getEmail());
                ps.setInt(2, id);

                ps.addBatch();
            }
            ps.executeBatch();
            return true;
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return false;
    }

    /**
     * Metodo que permite modificar las facturas que se encuentran en el sistema
     *
     * Me cuestiono si campos como idCliente efectivo cambio deberian de estar
     * aqui?
     *
     * Metodo Actualizado el 18/05/2022.
     *
     * @param f Objeto de la clase Factura.
     * @return retorna true si fue modificada y false si hubo un error en la
     * modificacion de la factura.
     */
    public synchronized static boolean modificarFactura(Facturas f) {
        sql = "UPDATE V_FACTURAS a SET "
                + "    a.ID_CLIENTE = ?, "
                + "    a.EFECTIVO = ?, "
                + "    a.CAMBIO = ?, "
                + "    a.ESTADO = ?, "
                + "    a.NOMBRETEMP = ? "
                + "WHERE "
                + "    a.ID = ?";
        try ( PreparedStatement ps = getCnn().prepareStatement(sql)) {

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
        try ( PreparedStatement ps = getCnn().prepareStatement(Categorias.UPDATE)) {

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
        sql = "UPDATE T_PERFIL a "
                + "SET "
                + "    a.DESCRIPCION = ? "
                + "WHERE "
                + "    a.IDPERFIL = " + idPerfil;
        try ( PreparedStatement ps = getCnn().prepareStatement(sql)) {

            ps.setString(1, descripcion);
            ps.setInt(2, idPerfil);

            int r = ps.executeUpdate();
            return "Perfil Modificado Correctamente. {" + r + "}";
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return "Error al Modificar Perfil";
        }
    }

    /**
     * Metodo que permite modificar los productos del sistema como a la
     * categoria a la que pertenece el producto, su codigo de barra, la
     * descripcion, la imagen de este, la nota del producto y su estado.
     *
     * Metodo actualizado el dia 23 de abril, segun la vista productos.
     *
     * @param El obj p perteneciente a la clase Producto, define los productos
     * del sistema.
     * @return
     */
    public synchronized static String modificarProducto(Producto p) {
        try ( PreparedStatement ps = getCnn().prepareStatement(Producto.UPDATE)) {

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

    public synchronized static String modificarPaciente(Paciente p) {
        try ( CallableStatement ps = getCnn().prepareCall(
                Paciente.UPDATE,
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_UPDATABLE,
                ResultSet.CLOSE_CURSORS_AT_COMMIT
        )) {

            ps.setInt(1, p.getId_persona());
            ps.setInt(2, p.getIdPadre());
            ps.setInt(3, p.getIdMadre());
            ps.setString(4, p.getGenerales().getCedula());
            ps.setString(5, p.getPNombre());
            ps.setString(6, p.getSNombre());
            ps.setString(7, p.getApellidos());
            ps.setString(8, "" + p.getSexo());
            ps.setDate(9, p.getFecha_nacimiento());
            ps.setInt(10, p.getGenerales().getId_tipo_sangre());
            ps.setInt(11, p.getAsegurado().getId_ars());
            ps.setString(12, p.getAsegurado().getNo_nss());
            ps.setBoolean(13, p.getEstado());
            ps.setBoolean(14, p.getAsegurado().getEstado());

            ps.executeUpdate();

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

        try ( PreparedStatement ps = getCnn().prepareStatement(sql)) {

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

        try ( PreparedStatement ps = getCnn().prepareStatement(sql)) {
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

        try ( PreparedStatement ps = getCnn().prepareStatement(sql)) {
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
        sql = "EXECUTE PROCEDURE SP_UPDATE_PADRES (?, ?, ?, ?, ?, ?, ?, ?,"
                + " ?, ?, ?, ?);";
        try ( PreparedStatement ps = getCnn().prepareStatement(sql)) {
            ps.setInt(1, p.getId_persona());
            ps.setInt(2, p.getAsegurado().getId_ars());
            ps.setString(3, p.getAsegurado().getNo_nss());
            ps.setInt(4, p.getGenerales().getId_tipo_sangre());
            ps.setString(5, p.getGenerales().getCedula());
            ps.setString(6, p.getPNombre());
            ps.setString(7, p.getSNombre());
            ps.setString(8, p.getApellidos());
            ps.setString(9, "" + p.getSexo());
            ps.setDate(10, p.getFecha_nacimiento());
            ps.setBoolean(11, p.getEstado());
            ps.setString(12, "" + p.getGenerales().getEstado_civil());

            ps.executeUpdate();
            return "Padre modificado correctamente";
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return "Error al modificar padre...";
        }
    }

    public synchronized String modificarControlConsulta(Control_Consulta miCC) {
        sql = "update V_CONTROL_CONSULTA set "
                + "    IDUSUARIO = ? , "
                + "    CANTIDADPACIENTE = ? , "
                + "    DIA = ? , "
                + "    INICIAL = ? , "
                + "    FINAL = ? "
                + "where IDCONTROLCONSULTA = ? ";

        try ( PreparedStatement ps = getCnn().prepareStatement(sql,
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_UPDATABLE,
                ResultSet.CLOSE_CURSORS_AT_COMMIT)) {

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
        sql = "UPDATE V_PROVEEDORES SET "
                + "CODIGO_PROVEEDOR = ? , "
                + "NOMBRE_PROVEEDOR = ? , "
                + "TELEFONO_PROVEEDOR = ? , "
                + "ESTADO = ? "
                + "WHERE ID = ? ";

        try ( PreparedStatement ps = getCnn().prepareStatement(
                sql,
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_UPDATABLE,
                ResultSet.CLOSE_CURSORS_AT_COMMIT)) {

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

        sql = "UPDATE V_PACIENTES "
                + "SET "
                + "    ESTADO = FALSE "
                + "WHERE "
                + "    CEDULA = ?";

        try ( PreparedStatement ps = getCnn().prepareStatement(sql)) {

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

        try ( PreparedStatement ps = getCnn().prepareStatement(Padres.CAMBIAR_ESTADO)) {

            ps.setString(1, cedula);

            ps.executeUpdate();
            return "Borrado o inactivo correctamente";
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return "Error al borrar padre...";
        }
    }

    public String modificarUbicaciones(String[] u) {

        try ( PreparedStatement ps = getCnn().prepareStatement(sql)) {
            sql = "update UBICACION_REPORTES set "
                    + "patch = '" + u[0] + "' where DESCRIPCION LIKE 'ReporteAlumnos';";

            ps.addBatch(sql);

            sql = "update UBICACION_REPORTES set "
                    + "patch = '" + u[1] + "' where DESCRIPCION LIKE 'ReporteAlumnosAgrupados'";

            ps.addBatch(sql);

            sql = "update UBICACION_REPORTES set "
                    + "patch = '" + u[4] + "' where DESCRIPCION LIKE 'Recibo'";

            ps.addBatch(sql);

            sql = "update UBICACION_REPORTES set "
                    + "patch = '" + u[5] + "' where DESCRIPCION LIKE 'Deuda'";

            ps.addBatch(sql);

            sql = "update UBICACION_REPORTES set "
                    + "Dinero = " + u[2] + " where DESCRIPCION LIKE 'Mensualidad'";

            ps.addBatch(sql);

            sql = "update UBICACION_REPORTES set "
                    + "Dinero = " + u[3] + " where DESCRIPCION LIKE 'PagoInscripcion'";

            ps.addBatch(sql);

            ps.executeLargeBatch();

            return "Ubicaciones Actualizada";
        } catch (SQLException ex) {

            return "No se Realizaron las Actualizaciones";
        }
    }

    public String modificarEstudiante(Estudiantes e) {
        /*Metodo para modificar los estudiante del sistema de ballet
        Actualizado el 23 de abril del 2022.
         */
        sql = "EXECUTE PROCEDURE SP_UPDATE_ESTUDIANTE (?, ?, ?, ?, ?, ?, ?,"
                + " ?, ?, ?, ?);";
        try ( PreparedStatement ps = getCnn().prepareStatement(sql)) {

            ps.setInt(1, e.getId_persona());
            ps.setInt(2, e.getIdPadre());
            ps.setInt(3, e.getIdMadre());
            ps.setInt(4, e.getIdTutor());
            ps.setInt(5, e.getJcb_parentesco());
            ps.setString(6, e.getPNombre());
            ps.setString(7, e.getSNombre());
            ps.setString(8, e.getApellidos());
            ps.setString(9, "" + e.getSexo());
            ps.setDate(10, e.getFecha_nacimiento());
            ps.setBoolean(11, e.getEstado());

            ps.executeUpdate();
            return "Estudiante Modificado Correctamente...!!!";
        } catch (SQLException ex) {
            return "Estudiante no pudo ser Modificado, Conctate SoftSureña...!!!";
        }
    }

    public String modificarTanda(Tandas t) {

        try ( PreparedStatement ps = getCnn().prepareStatement(Tandas.UPDATE)) {
            ps.setDate(1, t.getAnno_inicial());
            ps.setDate(2, t.getAnno_final());
            ps.setTime(3, t.getHora_inicial());
            ps.setTime(4, t.getHora_final());
            ps.setBoolean(5, t.getLunes());
            ps.setBoolean(6, t.getMartes());
            ps.setBoolean(7, t.getMiercoles());
            ps.setBoolean(8, t.getJueves());
            ps.setBoolean(9, t.getViernes());
            ps.setBoolean(10, t.getSabados());
            ps.setBoolean(11, t.getDomingos());
            ps.setInt(12, t.getCantidad_estudiantes());
            ps.setInt(13, t.getEdad_minima());
            ps.setInt(14, t.getEdad_maxima());
            ps.setBoolean(15, t.getCon_edad());
            ps.setBoolean(16, t.getEstado());
            ps.setInt(17, t.getId_tanda());

            ps.executeUpdate(sql);
            return "Tanda Modificado Correctamente...!!!";
        } catch (SQLException ex) {

            return "Tanda no pudo ser Modificado, Conctate SoftSureña...!!!" + ex.getMessage();
        }
    }
}
