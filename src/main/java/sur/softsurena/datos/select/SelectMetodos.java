package sur.softsurena.datos.select;

import RSMaterialComponent.RSComboBox;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;
import static sur.softsurena.conexion.Conexion.getCnn;
import sur.softsurena.entidades.ARS;
import sur.softsurena.entidades.BaseDeDatos;
import sur.softsurena.entidades.Categorias;
import sur.softsurena.entidades.Clientes;
import sur.softsurena.entidades.Codigo_Postal;
import sur.softsurena.entidades.ContactosEmail;
import sur.softsurena.entidades.ContactosTel;
import sur.softsurena.entidades.DetalleFactura;
import sur.softsurena.entidades.Direcciones;
import sur.softsurena.entidades.Distritos_municipales;
import sur.softsurena.entidades.E_S_SYS;
import sur.softsurena.entidades.Generales;
import sur.softsurena.entidades.Municipios;
import sur.softsurena.entidades.Paciente;
import sur.softsurena.entidades.Padres;
import sur.softsurena.entidades.Perfiles;
import sur.softsurena.entidades.Privilegios;
import sur.softsurena.entidades.Producto;
import sur.softsurena.entidades.Provincias;
import sur.softsurena.entidades.Temporales;
import sur.softsurena.entidades.TiposSangres;
import sur.softsurena.entidades.Turnos;
import sur.softsurena.entidades.Usuarios;
import sur.softsurena.utilidades.Utilidades;
import static sur.softsurena.entidades.ContactosEmail.TITULOS_CORREO;
import static sur.softsurena.entidades.ContactosTel.TITULOS_TELEFONO;

public class SelectMetodos {

    private static final Logger LOG = Logger.getLogger(SelectMetodos.class.getName());
    private static String sql;

    public synchronized static String pathBaseDeDatos() {
        try ( PreparedStatement ps1 = getCnn().prepareStatement(
                BaseDeDatos.PATH_BASE_DATOS,
                ResultSet.TYPE_FORWARD_ONLY,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.HOLD_CURSORS_OVER_COMMIT)) {

            try ( ResultSet rs1 = ps1.executeQuery()) {
                rs1.next();
                return rs1.getString(1);
            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, ex.getMessage(), ex);
                return null;
            }

        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        }
    }

    public synchronized static ResultSet metaBaseDatos() {
        try ( PreparedStatement ps1 = getCnn().prepareStatement(
                BaseDeDatos.METADATOS,
                ResultSet.TYPE_FORWARD_ONLY,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.HOLD_CURSORS_OVER_COMMIT)) {
            return ps1.executeQuery();
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        }
    }

    /**
     * Es el metodo que nos devuelve los Roles del sistema, los cuales son asig-
     * nados a los usuarios.
     *
     * @return
     */
    public synchronized static ResultSet getRoles() {
        try ( PreparedStatement ps = getCnn().prepareStatement(
                Privilegios.GET_ROLES,
                ResultSet.TYPE_FORWARD_ONLY,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.HOLD_CURSORS_OVER_COMMIT)) {
            return ps.executeQuery();
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        }
    }

    public synchronized static void getCategirias(JComboBox cbCategoria) {
        //Elimina registros previos.
        cbCategoria.removeAllItems();

        //Agregar primer elemento con id negativo
        Categorias categorias = Categorias.builder().
                id(-1).
                descripcion("Seleccione categoria").build();
        //Lo agregamos al comboBox.
        cbCategoria.addItem(categorias);

        try ( PreparedStatement ps = getCnn().prepareStatement(Categorias.SELECT,
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.HOLD_CURSORS_OVER_COMMIT)) {

            try ( ResultSet rs = ps.executeQuery()) {
                int i = 0;
                while (rs.next()) {
                    categorias = Categorias.builder().
                            id(rs.getInt("ID")).
                            descripcion(rs.getString("Descripcion")).build();
                    cbCategoria.addItem(categorias);
                    i++;
                }
            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, ex.getMessage(), ex);
            }
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }

    /**
     * Nitido. Metodo para consultar cual es el usuario actual del sistema.
     *
     * Una vez iniciada la session del usuario en el sistema, hacemos una
     * consulta a la base de datos, que nos devuelve el usuario y el rol de
     * este.
     *
     * Metodo actualizado el 17/05/2022.
     *
     * Metodo actualizado el 24 oct 2022:
     *
     * Nota:Se cambió el tipo de resultado que devuelve de un ResultSet a una
     * clase Usuarios.
     *
     * @return Retorna un String con el dato de cual es usuario del sistema que
     * ha iniciado sessión actualmente.
     */
    public synchronized static Usuarios getUsuarioActual() {
        try ( PreparedStatement ps = getCnn().prepareStatement(
                BaseDeDatos.USUARIO_ACTUAL,
                ResultSet.TYPE_FORWARD_ONLY,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.CLOSE_CURSORS_AT_COMMIT)) {
            try ( ResultSet rs = ps.executeQuery();) {
                rs.next();
                return Usuarios.builder().
                        user_name(rs.getString(1)).
                        rol(rs.getString(2)).build();
            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, ex.getMessage(), ex);
                return null;
            }
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        }
    }

    /**
     * Metodo que me trae un conjunto de datos de las provincias del pais.
     *
     * @nota Metodo creado el dia 16 de agosto 2022.
     *
     * @return retorna un conjunto de datos encapsulados en un ResultSet.
     */
    public synchronized static void getProvincias(RSComboBox jcbProvincias) {
        try ( PreparedStatement ps1 = getCnn().prepareStatement(
                Provincias.SELECT,
                ResultSet.TYPE_FORWARD_ONLY,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.HOLD_CURSORS_OVER_COMMIT)) {

            ResultSet rs = ps1.executeQuery();
            Provincias p;
            jcbProvincias.removeAllItems();

            try {
                while (rs.next()) {
                    p = Provincias.builder().
                            id(rs.getInt("ID")).
                            nombre(rs.getString("NOMBRE")).build();
                    jcbProvincias.addItem(p);
                }
            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, ex.getMessage(), ex);
            }
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }

    /**
     * Metodo que me trae un conjunto de datos de los municipios del pais.
     *
     * @nota Metodo creado el dia 16 de agosto 2022.
     *
     * @param id_provincia Identificador que permite obtener los municipios de
     * un lugar determinado de la provincia.
     *
     * @return retorna un conjunto de datos encapsulados en un ResultSet.
     */
    public synchronized static void getMunicipio(int id_provincia,
            RSComboBox jcbMunicipios) {

        try ( PreparedStatement ps = getCnn().prepareStatement(
                Municipios.SELECT,
                ResultSet.TYPE_FORWARD_ONLY,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.HOLD_CURSORS_OVER_COMMIT)) {

            ps.setInt(1, id_provincia);

            try ( ResultSet rs = ps.executeQuery()) {
                jcbMunicipios.removeAllItems();

                Municipios m = Municipios.builder().
                        id(0).
                        nombre("Ingrese Municipio").build();

                jcbMunicipios.addItem(m);

                while (rs.next()) {
                    m = Municipios.builder().
                            id(rs.getInt("id")).
                            nombre(rs.getString("nombre")).build();
                    jcbMunicipios.addItem(m);
                }
            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, ex.getMessage(), ex);
            }

        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }

    /**
     * Metodo que me trae un conjunto de datos de los Distritos Municipales del
     * pais.
     *
     * @nota Metodo creado el dia 16 de agosto 2022.
     *
     * @param id_municipio
     *
     * @return
     */
    public synchronized static void getDistritosMunicipales(int id_municipio,
            RSComboBox jcbDistritoMunicipal) {

        try ( PreparedStatement ps1 = getCnn().prepareStatement(
                Distritos_municipales.SELECT,
                ResultSet.TYPE_FORWARD_ONLY,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.HOLD_CURSORS_OVER_COMMIT)) {

            ps1.setInt(1, id_municipio);

            try ( ResultSet rs = ps1.executeQuery();) {

                jcbDistritoMunicipal.removeAllItems();

                Distritos_municipales dm = Distritos_municipales.builder().
                        id(0).
                        nombre("Inserte Distritos").build();

                jcbDistritoMunicipal.addItem(dm);

                while (rs.next()) {
                    dm = Distritos_municipales.builder().
                            id(rs.getInt("id")).
                            nombre(rs.getString("nombre")).build();

                    jcbDistritoMunicipal.addItem(dm);
                }
            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, ex.getMessage(), ex);
            }
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }

    /**
     * Metodo que me permite obtener los codigo postales de la republica
     * dominicana.
     *
     * @param id_provincia
     * @return
     */
    public synchronized static ResultSet getCodigoPostal(int id_provincia) {
        try ( PreparedStatement ps1 = getCnn().prepareStatement(
                Codigo_Postal.SELECT,
                ResultSet.TYPE_FORWARD_ONLY,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.HOLD_CURSORS_OVER_COMMIT)) {
            ps1.setInt(1, id_provincia);
            return ps1.executeQuery();
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        }
    }

    /**
     * Metodo utilizado para verificar si un empleado o cajero cuenta con un
     * turno habilitado por un usuario autorizado que le permita facturar en el
     * sistema.
     *
     * Metodo actualizado el 17/05/2022.
     *
     * @param userName Valor que utilizan los usuario en el sistema para iniciar
     * session.
     * @return Retorna true si el usuario cuenta con un turno habierto y false
     * si no cuenta con un turno abierto.
     */
    public synchronized static boolean usuarioTurnoActivo(String userName) {
        if (idTurnoActivo(userName) > 0) {
            return true;
        }
        return false;
    }

    /**
     * Metodo actualizado el 26 de abril 2022. Este metodo esta combinado con el
     * metodo usuarioTurnoActivo.
     *
     * @param userName Id del usuario a identificar.
     * @return Valor que identifica el turno activo del usuario consultado.
     */
    public synchronized static int idTurnoActivo(String userName) {
        try ( PreparedStatement ps = getCnn().prepareStatement(Turnos.SELECT_IDUSUARIO_ESTADO)) {
            ps.setString(1, userName.trim().toUpperCase());

            try ( ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                } else {
                    return 0;
                }
            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, ex.getMessage(), ex);
                return -1;
            }
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return -1;
        }

    }

    /**
     * Metodo que permite investigar si existe una descripcion de una categoria
     * ya existen.
     *
     * Metodo actualizado, 06 julio 2022.: Se le aplicó una restructuración
     * completa al metodo llevando el sql a la clase categoria.
     *
     * @param descripcion Es la descripcion que se pretende dar a la categoria
     * la cual este metodo verifica de comprobar si existe o no. devolviendo
     * true si existe o false si no existe.
     *
     * @return Retorna un valor boolean indicando si existe o no la descripcion
     * de la categoria que se le pretende dar.
     */
    public synchronized static boolean existeCategoria(String descripcion) {
        try ( PreparedStatement ps = getCnn().prepareStatement(Categorias.SELECT_CATEGORIA_DESCRIPCION)) {
            ps.setString(1, descripcion);
            try ( ResultSet rs = ps.executeQuery()) {
                return rs.next();
            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, ex.getMessage(), ex);
                return false;
            }
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return false;
        }
    }

    /**
     * Metodo utilizado para obtener todas las categorias del sistema.
     *
     * Metodo creado 11 Julio 2022.
     *
     * @return Devuelve un conjunto de datos de la tabla Categoria del sistema,
     * donde contiene todos los campos de la tabla.
     */
    public synchronized static ResultSet getCategorias() {
        try ( PreparedStatement ps = getCnn().prepareStatement(Categorias.SELECT_CATEGORIA)) {
            return ps.executeQuery();
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        }
    }

    /**
     *
     * @param idCategoria
     * @return
     */
    public synchronized static boolean existeCategoriaProductos(int idCategoria) {
        try ( PreparedStatement ps = getCnn().prepareStatement(Producto.EXISTE_CATEGORIA)) {
            ps.setInt(1, idCategoria);
            try ( ResultSet rs = ps.executeQuery()) {
                return rs.next();
            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, ex.getMessage(), ex);
                return false;
            }

        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return false;
        }
    }

    /**
     * Metodo que nos permite tener el conjunto de datos de las categorias que
     * estan activas y con un producto que está activo y enlazado a una
     * categoria.
     *
     * @return Retorna un conjunto de datos de tipo ResultSet.
     */
    public synchronized static ResultSet getCategoriaActivas() {
        try ( PreparedStatement ps = getCnn().prepareStatement(Categorias.SELECT_CATEGORIA_ACTIVAS)) {
            return ps.executeQuery();
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        }
    }

    /*--------------------Fin de las consultas de categorias------------------*/
    /**
     *
     * @return
     */
    public synchronized static int periodoMaquina() {
        try ( PreparedStatement ps = getCnn().prepareStatement(BaseDeDatos.PERIODO)) {

            try ( ResultSet rs = ps.executeQuery()) {
                rs.next();
                return rs.getInt(1);
            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, ex.getMessage(), ex);
                return -1;
            }
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return -1;
        }
    }

    /**
     * Metodo que verifica la identificación del equipo en el sistema, tomando
     * su numero unico de registro.
     *
     * @param idMaquina identificador del equipo.
     *
     * @return Devuelve true si el equipo se encuentra resgistrado, y false si
     * no existe registro en la base de datos.
     */
    public synchronized static boolean existeIdMaquina(String idMaquina) {
        try ( PreparedStatement ps = getCnn().prepareStatement(BaseDeDatos.EXISTE_REGISTRO)) {

            ps.setString(1, idMaquina.trim());

            try ( ResultSet rs = ps.executeQuery()) {
                return rs.next();
            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, ex.getMessage(), ex);
                return false;
            }

        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return false;
        }
    }

    public synchronized static ResultSet getBuscarTemporal(Integer idFactura) {
        try ( PreparedStatement ps = getCnn().prepareStatement(DetalleFactura.GET_DETALLE_FACTURA)) {
            ps.setInt(1, idFactura);
            return ps.executeQuery();
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        }
    }

    /**
     * Metodo utilizado para obtener los productos ya sea por su ID, Codigo o
     * Descripcion de los registros de la tabla productos.
     *
     * @param criterio Este valor puede ser el identificador, codigo o
     * descripcion del producto.
     *
     * @return Devuelve un conjunto de datos con los criterio de la busqueda
     * espeficicada.
     *
     */
    public synchronized static ResultSet buscarProducto(String criterio) {

        try ( PreparedStatement ps = getCnn().prepareStatement(Producto.BUSCAR_PRODUCTO_ID_DESCRIPCION_CODIGO)) {
            ps.setInt(1, Integer.parseInt(criterio));
            ps.setString(2, criterio);
            ps.setString(3, criterio);
            return ps.executeQuery();
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        }
    }

    /**
     * Metodo utilizado para saber si existe un cliente registrado por una
     * cedula de identidad en el sistema.
     *
     * Metodo revisado y actualizado el 26 de abril 2022. Metodo revisado y
     * actualizado el 23 de noviembre 2022.
     *
     *
     * @param cedula cedula del cliente utilizada para consultar la base de
     * datos.
     *
     * @return devuelve el id del cliente si existe la cedula registrada y -1
     * si no se encuentra la cedula en el sistema.
     */
    public synchronized static Integer existeCliente(String cedula) {
        try ( PreparedStatement ps = getCnn().prepareStatement(
                Clientes.GET_ID_CLIENTE_BY_CEDULA,
                ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.CLOSE_CURSORS_AT_COMMIT)) {

            ps.setString(1, cedula.trim());

            try ( ResultSet rs = ps.executeQuery()) {
                return rs.getInt("ID");
            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, ex.getMessage(), ex);
                return null;
            }
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        }
    }

    /**
     * Obtenemos los datos basico de un cliente por una consulta por su ID.
     *
     * @param id identificador del cliente del sistema, la cual ayuda obtener
     * los registros de un usuario en expecifico.
     *
     * @return Retorna un conjunto de datos del tipo resultSet.
     */
    public synchronized static Clientes getClienteByID(int id) {
        try ( PreparedStatement ps = getCnn().prepareStatement(Clientes.GET_CLIENTES_SB_BY_ID)) {

            ps.setInt(1, id);

            Clientes c = null;
            Generales g = null;

            try ( ResultSet rs = ps.executeQuery();) {
                rs.next();
                g = Generales.builder().
                        cedula(rs.getString("CEDULA")).
                        estado_civil(rs.getString("ESTADO_CIVIL").charAt(0)).build();

                c = Clientes.builder().
                        pNombre(rs.getString("PNOMBRE")).
                        sNombre(rs.getString("SNOMBRE")).
                        apellidos(rs.getString("APELLIDOS")).
                        fecha_nacimiento(rs.getDate("FECHA_NACIMIENTO")).
                        estado(rs.getBoolean("ESTADO")).
                        persona(rs.getString("PERSONA").charAt(0)).
                        sexo(rs.getString("SEXO").charAt(0)).
                        generales(g).build();

            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, ex.getMessage(), ex);
                return null;
            }
            return c;
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        }
    }

    /**
     * Obtenemos el historia de direcciones del cliente, lo cual permite tener
     * mejor control de la direcciones de los clientes.
     *
     * @param id_persona identificador del cliente del sistema, la cual ayuda
     * obtener los registros de un usuario en expecifico.
     *
     * @return Retorna un conjunto de datos del tipo resultSet.
     */
    public synchronized static List<Direcciones> getDireccionByID(int id_persona) {
        List<Direcciones> direcciones = new ArrayList<>();

        try ( PreparedStatement ps = getCnn().prepareStatement(Direcciones.SELECT_BY_ID)) {
            ps.setInt(1, id_persona);
            try ( ResultSet rs = ps.executeQuery();) {
                while (rs.next()) {

                    Provincias p = Provincias.builder().
                            id(rs.getInt("ID_PROVINCIA")).
                            nombre(rs.getString("PROVINCIA")).build();

                    Municipios m = Municipios.builder().
                            id(rs.getInt("ID_MUNICIPIO")).
                            nombre(rs.getString("MUNICIPIO")).build();

                    Distritos_municipales d = Distritos_municipales.builder().
                            id(rs.getInt("ID_DISTRITO_MUNICIPAL")).
                            nombre(rs.getString("DISTRITO_MUNICIPAL")).build();

                    Direcciones dd = Direcciones.builder().
                            id(rs.getInt("ID")).
                            provincia(p).
                            municipio(m).
                            distrito_municipal(d).
                            direccion(rs.getString("DIRECCION")).
                            fecha(rs.getDate("FECHA")).build();

                    direcciones.add(dd);
                }
            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, ex.getMessage(), ex);
            }
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return direcciones;
    }

    public synchronized static DefaultTableModel getTelefonoByID(int id) {
        Object registroTel[] = new Object[TITULOS_TELEFONO.length];

        DefaultTableModel dtmTelefono = new DefaultTableModel(null, TITULOS_TELEFONO);

        try ( PreparedStatement ps = getCnn().prepareStatement(ContactosTel.SELECT_BY_ID)) {
            ps.setInt(1, id);
            try ( ResultSet rs = ps.executeQuery();) {
                while (rs.next()) {
                    ContactosTel t = ContactosTel.builder().
                            id(rs.getInt("ID")).
                            telefono(rs.getString("TELEFONO")).
                            tipo(rs.getString("TIPO")).
                            fecha(rs.getDate("FECHA")).build();

                    registroTel[0] = t;
                    registroTel[1] = t.getTipo();
                    registroTel[2] = t.getFecha();

                    dtmTelefono.addRow(registroTel);
                }
            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, ex.getMessage(), ex);
            }
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return dtmTelefono;
    }

    public synchronized static DefaultTableModel getCorreoByID(int id) {
        Object registroCorreo[] = new Object[TITULOS_CORREO.length];

        DefaultTableModel dtmCorreo = new DefaultTableModel(null, TITULOS_CORREO);

        try ( PreparedStatement ps = getCnn().prepareStatement(ContactosEmail.SELECT_BY_ID)) {
            ps.setInt(1, id);

            try ( ResultSet rs = ps.executeQuery();) {
                while (rs.next()) {
                    ContactosEmail c = ContactosEmail.builder().
                            id(rs.getInt("ID")).
                            email(rs.getString("EMAIL")).
                            fecha(rs.getDate("FECHA")).build();

                    registroCorreo[0] = c;
                    registroCorreo[1] = c.getFecha();

                    dtmCorreo.addRow(registroCorreo);
                }
            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, ex.getMessage(), ex);
            }
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return dtmCorreo;
    }

    /**
     * Metodo utilizado para consultar a la base de datos, los roles creado y
     * aquienes fueron asignados esos roles.
     *
     * Metodo Actualizado el 18/05/2022.
     *
     * @param userName Identificador del usuario en el sistema.
     *
     * @return Devuelve una lista array de los roles por usuario del sistema.
     */
    public synchronized static ArrayList<String> comprobandoRol(String userName) {
        ArrayList<String> roles = new ArrayList<>();
        try ( PreparedStatement ps = getCnn().prepareStatement(
                Usuarios.SELECT_ROLES_USUARIOS,
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.CLOSE_CURSORS_AT_COMMIT)) {
            ps.setString(1, userName.trim().toUpperCase());
            try ( ResultSet rs = ps.executeQuery()) {
                String aux;
                while (rs.next()) {
                    aux = rs.getString("ROL");
                    if (aux.equalsIgnoreCase("RDB$ADMIN")) {
                        aux = "ADMINISTRADOR";
                    }
                    roles.add(aux);
                }
            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, ex.getMessage(), ex);
                return null;
            }
            return roles;
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        }
    }

    /**
     * Metodo que verifica la existencia de un producto por su codigo de barra o
     * descripción. Metodo actualizado el 26 de abril 2022
     *
     * @param codigo este valor representa el valor del codigo de barra del
     * producto o nombre de 25 caracteres.
     * @return
     */
    public synchronized static boolean existeProducto(String codigo) {
        try ( PreparedStatement ps = getCnn().prepareStatement(Producto.EXISTE_PRODUCTO)) {
            ps.setString(1, codigo);
            ps.setString(2, codigo);
            try ( ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return false;
        }
    }

    /**
     * Metodo para llamar a los usuarios del sistema, este ejecuta un
     * procedimiento almacenado que realizar el SELECT complejo en la BD.
     *
     * Actualizado 09 Julio 2022, se agregar el campo estatico de la clase
     * Usuario el cual contiene el SQL de la consulta de este metodo.
     *
     * @param userName Es el identificador que utiliza el usuario para iniciar
     * session en el sistema, este campo tambien puede recibir un string con el
     * valor all para obtener todos los usuarios del sistema.
     *
     * @return retorna un conjunto de valores dependiendo del valor pasado por
     * parametro, si se le pasa all recibi un conjunto de datos de todos los
     * usuarios del sistema.
     */
    public synchronized static Usuarios getUsuario(String userName) {
        try ( PreparedStatement ps = getCnn().prepareStatement(
                Usuarios.GET_USER_BY_USER_NAME,
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.CLOSE_CURSORS_AT_COMMIT)) {
            ps.setString(1, userName);
            try ( ResultSet rs = ps.executeQuery();) {
                rs.next();
                return Usuarios.builder().
                        user_name(rs.getString("O_USER_NAME")).
                        pNombre(rs.getString("O_PRIMER_NOMBRE")).
                        sNombre(rs.getString("O_SEGUNDO_NOMBRE")).
                        apellidos(rs.getString("O_APELLIDOS")).
                        estado(rs.getBoolean("O_ESTADO_ACTIVO")).
                        administrador(rs.getBoolean("O_ADMINISTRADOR")).
                        descripcion(rs.getString("O_DESCRIPCION")).build();
            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, ex.getMessage(), ex);
                return null;
            }
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        }
    }

    /**
     * Para obtener las facturas temporales del sistema.
     *
     * @return
     */
    public synchronized static ResultSet getTemporales() {
        try ( PreparedStatement ps = getCnn().prepareStatement(Temporales.GET_TEMPORALES)) {
            return ps.executeQuery();
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        }
    }

    public synchronized static ResultSet getReporteFacturas(String filtro) {
        sql = "SELECT f.idFactura, f.idCliente, (c.nombres||' '||c.apellidos) AS nombreFull, "
                + "        f.fecha, d.idLinea, p.idProducto, p.descripcion, "
                + "        precio,   d.cantidad, precio * d.cantidad AS Valor "
                + "FROM tabla_facturas f "
                + "INNER JOIN tabla_clientes c ON f.idCliente = c.idCliente "
                + "INNER JOIN detalleFactura d ON f.idFactura = d.idFactura "
                + "INNER JOIN tabla_productos p ON p.idproducto = d.idproducto "
                + filtro;
        try ( PreparedStatement ps = getCnn().prepareStatement(sql)) {
            return ps.executeQuery();
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        }
    }

    /**
     *
     * @param idCliente
     * @return
     */
    public synchronized static ResultSet getCobrosClientesFactura(String idCliente) {
        sql = "SELECT r.IDFACTURA, CAST(sum(d.CANTIDAD * d.PRECIO) as DECIMAL(15,2)) as Total, "
                + "       r.FECHA, r.ESTADO "
                + "FROM TABLA_FACTURAS r "
                + "JOIN TABLA_DETALLEFACTURA d ON d.IDFACTURA = r.IDFACTURA "
                + "WHERE r.IDCLIENTE like ? and r.ESTADO in('c', 'a') "
                + "GROUP BY r.IDFACTURA, r.FECHA, r.ESTADO";
        try ( PreparedStatement ps = getCnn().prepareStatement(sql)) {
            ps.setString(1, idCliente);
            return ps.executeQuery();
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        }
    }

    public synchronized static ResultSet getDeudaClientes(String estado) {
        sql = "SELECT SUM(r.MONTO), case r.ESTADO "
                + "when 'i' then 'Inicial:' "
                + "when 'a' then 'Abonado:' "
                + "when 'p' then 'Pagado:' "
                + "when 'n' then 'Nulado:' "
                + "end "
                + "FROM GET_SUMA_DEUDA r "
                + estado;
        try ( PreparedStatement ps = getCnn().prepareStatement(sql)) {
            return ps.executeQuery();
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        }
    }

    public synchronized static ResultSet getDeudaClientesEstado(String estado) {
        sql = "SELECT r.IDDEUDAS, IIF(r.IDCLIENTE = '0', '000-0000000-0', "
                + "r.IDCLIENTE) as IDCLIENTE, c.NOMBRES, c.APELLIDOS, "
                + "r.CONCEPTO, r.MONTO, r.FECHA, "
                + "        (IIF(r.ESTADO = 'i', 'Inicial', "
                + "         IIF(r.ESTADO = 'p', 'Pagada', "
                + "         IIF(r.ESTADO = 'a', 'Abonada', "
                + "         IIF(r.ESTADO = 'n','Nula','No Definida'))))) as ESTADO "
                + "FROM TABLA_DEUDAS r "
                + "LEFT JOIN TABLA_CLIENTES c ON c.IDCLIENTE LIKE r.IDCLIENTE "
                + estado
                + "ORDER by 1";
        try ( PreparedStatement ps = getCnn().prepareStatement(sql)) {
            return ps.executeQuery();
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        }
    }

    public synchronized static ResultSet getDeudaClientes() {
        sql = "SELECT r.IDCLIENTE, (c.NOMBRES||' '||c.APELLIDOS) as nombres "
                + "FROM TABLA_DEUDAS r "
                + "LEFT JOIN TABLA_CLIENTES c"
                + "    ON c.IDCLIENTE LIKE r.IDCLIENTE "
                + "WHERE r.ESTADO IN('i', 'a') "
                + "GROUP BY r.IDCLIENTE, c.NOMBRES, c.APELLIDOS ";
        try ( PreparedStatement ps = getCnn().prepareStatement(sql)) {
            return ps.executeQuery();
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        }
    }

    /*Consultas de las facturas*/
    public synchronized static ResultSet getFacturas() {
        sql = "SELECT idFactura "
                + "FROM V_facturas "
                + "order by 1";
        try {
            PreparedStatement ps = getCnn().prepareStatement(sql);
            return ps.executeQuery();
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        }
    }

    public synchronized static ResultSet getFacturasNombreClientes(int idFactura) {
        try ( PreparedStatement ps = getCnn().prepareStatement(
                "SELECT r.IDCLIENTE, nombreCliente "
                + "FROM TABLA_FACTURAS r "
                + "WHERE r.IDFACTURA = ?"
                + "order by 1")) {
            ps.setInt(1, idFactura);
            return ps.executeQuery();
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        }
    }

    public synchronized static ResultSet getFacturasDetalladas(String idFactura) {
        try ( PreparedStatement ps = getCnn().prepareStatement(
                "SELECT factura.idFactura, factura.idCliente, nombres||' '||apellidos AS nombreFull, "
                + "        fecha, idLinea, (SELECT p.Descripcion "
                + "                            FROM TABLA_PRODUCTOS p "
                + "                            WHERE p.idProducto = DETALLEFACTURA.IDPRODUCTO ) as Descripcion, "
                + "        idProducto, precio, cantidad, precio * cantidad AS Valor "
                + "FROM TABLA_FACTURAS "
                + "INNER JOIN TABLA_CLIENTES ON factura.idCliente = cliente.idCliente "
                + "INNER JOIN TABLA_DETALLEFACTURA ON factura.idFactura = detalleFactura.idFactura "
                + "WHERE factura.idFactura = ? ")) {
            ps.setString(1, idFactura);
            return ps.executeQuery();
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        }
    }

    public synchronized static ResultSet getFacturasDetalladaPorCliente(String idCliente) {
        sql = "SELECT f.idFactura, f.estado , f.fecha, f.USUARIO, "
                + "COALESCE(SUM(d.precio * d.cantidad), 0.00) AS Valor "
                + "FROM TABLA_FACTURAS f "
                + "LEFT JOIN TABLA_CLIENTES c ON f.idCliente = c.idCliente "
                + "LEFT JOIN TABLA_DETALLEFACTURA d ON f.idFactura = d.idFactura "
                + "WHERE f.idCliente = ? "
                + "GROUP BY f.idFactura, f.estado , f.fecha, f.USUARIO "
                + "order by 1";
        try ( PreparedStatement ps = getCnn().prepareStatement(sql)) {
            ps.setString(1, idCliente);
            return ps.executeQuery();
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        }
    }

    public synchronized static ResultSet getFacturasDetalladaPorCliente(
            String idCliente, int idFactura) {
        sql = "SELECT f.idFactura, f.estado , f.fecha, f.USUARIO, "
                + "COALESCE(SUM(d.precio * d.cantidad), 0.00) AS Valor "
                + "FROM TABLA_FACTURAS f "
                + "LEFT JOIN TABLA_CLIENTES c ON f.idCliente = c.idCliente "
                + "LEFT JOIN TABLA_DETALLEFACTURA d ON f.idFactura = d.idFactura "
                + "WHERE f.idCliente = ? and f.idFactura = ? "
                + "GROUP BY f.idFactura, f.estado , f.fecha, f.USUARIO "
                + "order by 1";
        try ( PreparedStatement ps = getCnn().prepareStatement(sql)) {
            ps.setString(1, idCliente);
            ps.setInt(2, idFactura);
            return ps.executeQuery();
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        }
    }

    public synchronized static ResultSet getDeudaCliente(String idCliente) {
        sql = "SELECT r.IDDEUDAS, r.CONCEPTO, r.MONTO, r.FECHA, r.ESTADO "
                + "FROM TABLA_DEUDAS r "
                + "WHERE r.IDCLIENTE LIKE ? AND r.ESTADO NOT IN('n', 'p')";
        try ( PreparedStatement ps = getCnn().prepareStatement(sql)) {
            ps.setString(1, idCliente);
            return ps.executeQuery();
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        }
    }

    public synchronized static ResultSet getDeudaClienteExterna(String idDeuda) {
        try ( PreparedStatement ps = getCnn().prepareStatement(
                "SELECT r.CODIGO, r.FECHA, r.HORA, r.MONTO "
                + "FROM TABLA_PAGO_DEUDAS_EXTERNA r "
                + "WHERE r.IDDEUDA = ?")) {
            ps.setString(1, idDeuda);
            return ps.executeQuery();
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        }
    }

    //!OJO Metodo para analizarlo
    public synchronized static ResultSet getPagoDeudasExterna(int idDeuda) {
        try ( PreparedStatement ps = getCnn().prepareStatement(
                "SELECT r.CODIGO, r.MONTO, r.FECHA, r.HORA "
                + "FROM TABLA_PAGO_DEUDAS_EXTERNA r "
                + "WHERE r.IDDEUDA = ?")) {
            ps.setInt(1, idDeuda);
            return ps.executeQuery();
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        }
    }

    //!OJO Metodo para analizarlo
    public synchronized static ResultSet getPagoDeudas(int idFactura) {
        try ( PreparedStatement ps = getCnn().prepareStatement(
                "SELECT r.IDPAGODEUDA, r.FECHA, r.HORA, r.MONTOPAGO "
                + "FROM TABLA_PAGODEUDA r "
                + "WHERE r.IDFACTURA = ?")) {
            ps.setInt(1, idFactura);
            return ps.executeQuery();
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        }
    }

    public synchronized static ResultSet getEntradaProducto(int mes, int year) {
        try ( PreparedStatement ps = getCnn().prepareStatement(
                "SELECT r.FECHAENTRADA, IIF(r.OP = '+', 'Entrada', 'Salida') as operacion, r.IDUSUARIO "
                + "FROM TABLA_ENTRADAS_PRUDUCTO r "
                + "WHERE EXTRACT(MONTH FROM r.FECHAENTRADA) = ? "
                + "and EXTRACT(YEAR FROM r.FECHAENTRADA) = ? "
                + "GROUP BY r.FECHAENTRADA,  r.OP, r.IDUSUARIO")) {
            ps.setInt(1, mes);
            ps.setInt(2, year);
            return ps.executeQuery();
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        }
    }

    /**
     * Metodo de consulta que es utilizada para obtener el numero de registros
     * de una tabla del sistema.
     *
     * @param tabla nombre de la tabla para obtener las cantidad de registros.
     *
     * @return Devuelve la cantindad de registros que existe en una tabla dada
     * en el parametro.
     */
    public synchronized static int cantidadRegistros(String tabla) {
        try ( PreparedStatement ps = getCnn().prepareStatement(
                "SELECT COALESCE(cantidad, 0) as num "
                + "FROM tabla_reccount "
                + "WHERE tabla = ?;"
        )) {//Conseguir con el conteos de las tablas....
            ps.setString(1, tabla);
            try ( ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("num");
                } else {
                    return 0;
                }
            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, ex.getMessage(), ex);
                return 0;
            }

        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return 0;
        }
    }

    public synchronized static Perfiles getPerfilUsuario(int idPerfil) {
        try ( PreparedStatement ps = getCnn().prepareStatement(Perfiles.SELECT_ID)) {
            ps.setInt(1, idPerfil);
            try ( ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) {
                    return null;
                }

                Perfiles p = Perfiles.builder().
                        userName(rs.getString("USERNAME")).
                        rol(rs.getString("ROL")).
                        CLIENTE_SELECT(rs.getBoolean("CLIENTE_SELECT")).
                        CLIENTE_INSERT(rs.getBoolean("CLIENTE_INSERT")).
                        CLIENTE_UPDATE(rs.getBoolean("CLIENTE_UPDATE")).
                        CLIENTE_DELETE(rs.getBoolean("CLIENTE_DELETE")).
                        PRODUCTO_SELECT(rs.getBoolean("PRODUCTO_SELECT")).
                        PRODUCTO_INSERT(rs.getBoolean("PRODUCTO_INSERT")).
                        PRODUCTO_UPDATE(rs.getBoolean("PRODUCTO_UPDATE")).
                        PRODUCTO_DELETE(rs.getBoolean("PRODUCTO_DELETE")).
                        USUARIO_SELECT(rs.getBoolean("USUARIO_SELECT")).
                        USUARIO_INSERT(rs.getBoolean("USUARIO_INSERT")).
                        USUARIO_UPDATE(rs.getBoolean("USUARIO_UPDATE")).
                        USUARIO_DELETE(rs.getBoolean("USUARIO_DELETE")).
                        USUARIO_CAMBIO_CLAVE(rs.getBoolean("USUARIO_CAMBIO_CLAVE")).
                        FACTURA_SELECT(rs.getBoolean("FACTURA_SELECT")).
                        FACTURA_INSERT(rs.getBoolean("FACTURA_INSERT")).
                        FACTURA_UPDATE(rs.getBoolean("FACTURA_UPDATE")).
                        FACTURA_DELETE(rs.getBoolean("FACTURA_DELETE")).
                        REPORTES_SELECT(rs.getBoolean("REPORTES_SELECT")).
                        INVENTARIOS_SELECT(rs.getBoolean("INVENTARIOS_SELECT")).
                        TURNO_SELECT(rs.getBoolean("TURNO_SELECT")).
                        TURNO_INSERT(rs.getBoolean("TURNO_INSERT")).
                        TURNO_UPDATE(rs.getBoolean("TURNO_UPDATE")).
                        TURNO_DELETE(rs.getBoolean("TURNO_DELETE")).
                        DEUDAS_SELECT(rs.getBoolean("DEUDAS_SELECT")).
                        DEUDAS_INSERT(rs.getBoolean("DEUDAS_INSERT")).
                        DEUDAS_UPDATE(rs.getBoolean("DEUDAS_UPDATE")).
                        DEUDAS_DELETE(rs.getBoolean("DEUDAS_DELETE")).build();

                return p;
            }
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        }
    }

    public synchronized static BigDecimal getPrecioProducto(int idProducto) {

        try ( PreparedStatement ps = getCnn().prepareStatement(
                "SELECT precio "
                + "FROM TABLA_ENTRADAS_PRODUCTO "
                + "WHERE idProducto = ?")) {

            ps.setInt(1, idProducto);

            try ( ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getBigDecimal("precio");
                } else {
                    return new BigDecimal(0);
                }
            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, ex.getMessage(), ex);
                return new BigDecimal(-1);
            }

        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return new BigDecimal(-1);
        }
    }

    public synchronized static BigDecimal getDeudaActual(String idCliente) {
        try ( PreparedStatement ps = getCnn().prepareStatement(
                "SELECT r.DEUDAACTUAL "
                + "FROM TABLA_DEUDAS r "
                + "WHERE r.IDCLIENTE LIKE ?")) {

            ps.setString(1, idCliente);
            try ( ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getBigDecimal("DEUDAACTUAL");
                } else {
                    return new BigDecimal(0);
                }
            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, ex.getMessage(), ex);
                return new BigDecimal(-1);
            }

        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return new BigDecimal(-1);
        }
    }

    public synchronized static int getIdAcceso(int idAcceso) {
        try ( PreparedStatement ps = getCnn().prepareStatement(
                "SELECT r.IDACCESO "
                + "FROM Tabla_ACCESO2 r "
                + "WHERE r.IDPERFIL = ?")) {

            ps.setInt(1, idAcceso);

            try ( ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                } else {
                    return 1;
                }
            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, ex.getMessage(), ex);
                return -1;
            }

        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return -1;
        }
    }

    public synchronized static BigDecimal sumaMontoPagoDeudaExterna(int idDeuda) {
        try ( PreparedStatement ps = getCnn().prepareStatement(
                "SELECT SUM(r.MONTO) "
                + "FROM TABLA_PAGO_DEUDAS_EXTERNA r "
                + "WHERE r.IDDEUDA = ?")) {

            try ( ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getBigDecimal(1);
                } else {
                    return new BigDecimal(0);
                }
            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, ex.getMessage(), ex);
                return new BigDecimal(-1);
            }

        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return new BigDecimal(-1);
        }
    }

    /**
     * Este metodo debe ser investigado pues no fue documentado.
     *
     * @param idUsuario
     * @return
     */
    public synchronized static String getCreadorUsuario(String idUsuario) {
        try ( PreparedStatement ps = getCnn().prepareStatement(
                "SELECT creador "
                + "FROM get_creador "
                + "WHERE TRIM(usuario) like ?;")) {

            ps.setString(1, idUsuario);

            try ( ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("creador");
                } else {
                    return null;
                }
            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, ex.getMessage(), ex);
                return null;
            }
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        }
    }

    /**
     * Metodo que permite recibir imagenes en bits.
     *
     * @param idUsuario
     * @return
     */
    public static synchronized ImageIcon getImagen(String idUsuario) {

        try ( PreparedStatement ps = getCnn().prepareStatement(
                "SELECT imagen "
                + "FROM Tabla_Imagenes "
                + "WHERE TRIM(idUsuario) like ?")) {

            ps.setString(1, idUsuario);

            try ( ResultSet rs = ps.executeQuery()) {
                BufferedImage img = null;

                while (rs.next()) {
                    Blob blob = rs.getBlob("imagen");
                    byte[] data = blob.getBytes(1, (int) blob.length());
                    try {
                        img = ImageIO.read(new ByteArrayInputStream(data));
                    } catch (IOException ex) {

                        LOG.log(Level.SEVERE, ex.getMessage(), ex);
                    }
                }

                rs.close();
                return new ImageIcon(img);
            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, ex.getMessage(), ex);
            }

        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return null;
    }

    public synchronized static String getSexoPaciente(int idPaciente) {
        try ( PreparedStatement ps = getCnn().prepareStatement(Paciente.GET_SEXO_BY_ID,
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.HOLD_CURSORS_OVER_COMMIT)) {

            ps.setInt(1, idPaciente);

            try ( ResultSet rs = ps.executeQuery()) {
                rs.next();
                return rs.getString(1);
            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, ex.getMessage(), ex);
                return "N/A";
            }
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return "N/A";
        }

    }

    public synchronized static String getLogo() {
        try ( PreparedStatement ps = getCnn().prepareStatement(E_S_SYS.SELECT_LOGO,
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.CLOSE_CURSORS_AT_COMMIT)) {
            try ( ResultSet rs = ps.executeQuery()) {
                rs.next();
                return rs.getString(1);
            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, ex.getMessage(), ex);
            }
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return "";
    }

    public synchronized static int numeroPadres(boolean estado) {
        sql = "SELECT CANTIDAD "
                + "FROM V_RECCOUNT "
                + "WHERE ESTADO IS ? AND IDPADRE != 0;";

        try ( PreparedStatement ps = getCnn().prepareStatement(sql,
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.HOLD_CURSORS_OVER_COMMIT)) {

            ps.setBoolean(1, estado);

            try ( ResultSet rs = ps.executeQuery()) {
                rs.next();
                return rs.getInt(1);
            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, ex.getMessage(), ex);
            }

        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return 1;
    }

    /**
     * Verificamos si existe la cedula del paciente antes de realizar un
     * registro a la base de datos.
     *
     * @param cedula Es el identificador unico de cada persona cuando nace.
     * @return boolean si es verdadero el documento existe false puede
     * realizarse el registro a la base de datos.
     */
    public synchronized static boolean existePaciente(String cedula) {
        sql = "SELECT (1) FROM V_PACIENTES WHERE cedula = ?";

        try ( PreparedStatement ps = getCnn().prepareStatement(sql,
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.HOLD_CURSORS_OVER_COMMIT)) {

            ps.setString(1, cedula);

            try ( ResultSet rs = ps.executeQuery()) {
                return rs.next();
            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, ex.getMessage(), ex);
                return false;
            }

        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return false;
        }
    }

    public synchronized static boolean existePadre(String cedula, boolean estado) {
        sql = "SELECT (1) FROM V_PADRES WHERE cedula = ? and ESTADO IS ?";
        try ( PreparedStatement ps = getCnn().prepareStatement(sql,
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.HOLD_CURSORS_OVER_COMMIT)) {

            ps.setString(1, cedula);
            ps.setBoolean(2, estado);

            try ( ResultSet rs = ps.executeQuery()) {
                return rs.next();
            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, ex.getMessage(), ex);
                return false;
            }

        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return false;
        }
    }

    /**
     *
     * @param idUsuario
     * @return
     */
    public synchronized boolean existeMensaje(String idUsuario) {
        sql = "el sql";

        try ( PreparedStatement ps = getCnn().prepareStatement(sql)) {

            try ( ResultSet rs = ps.executeQuery()) {
                return rs.next();
            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, ex.getMessage(), ex);
                return false;
            }

        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return false;
        }
    }

    /**
     * Metodo que permite verificar si existe un usuario en el sistema, esto con
     * el objetivo de permitir a un usuario ser registrado en la base de datos.
     *
     * @param userName Identificador unico de un usuario para ser utilizado como
     * parte de login de inicio en el sistema.
     *
     * Actualizado el dia 09 julio 2022, Nota: se agrega el campo de la clase
     * Usuario, para que tome el SQL de la consulta.
     *
     * @return retorna un valor booleano que nos permite saber si existe "TRUE"
     * o no "FALSE" un usuario en el sistema.
     */
    public synchronized static boolean existeUsuarioByUserName(String userName) {
        try ( PreparedStatement ps = getCnn().prepareStatement(Usuarios.EXISTE_USUARIO_BY_USER_NAME)) {
            ps.setString(1, userName);
            try ( ResultSet rs = ps.executeQuery()) {
                return rs.next();
            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, ex.getMessage(), ex);
                return false;
            }

        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return false;
        }
    }

    /**
     *
     * @param p
     * @return
     */
    public synchronized static boolean privilegioCampo(Privilegios p) {
        try ( PreparedStatement ps = getCnn().prepareStatement(Privilegios.PERMISO_UPDATE_CAMPO)) {
            ps.setString(1, "" + p.getPrivilegio());
            ps.setString(2, p.getNombre_relacion());
            ps.setString(3, p.getNombre_campo());
            try ( ResultSet rs = ps.executeQuery()) {
                return rs.next();
            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, ex.getMessage(), ex);
                return false;
            }
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return false;
        }
    }

    /**
     *
     * @param p
     * @return
     */
    public synchronized static boolean privilegioTabla(Privilegios p) {
        try ( PreparedStatement ps = getCnn().prepareStatement(
                Privilegios.PERMISO_UPDATE_TABLA,
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.HOLD_CURSORS_OVER_COMMIT)) {

            ps.setString(1, "" + p.getPrivilegio());
            ps.setString(2, p.getNombre_relacion());

            try ( ResultSet rs = ps.executeQuery()) {
                return rs.next();
            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, ex.getMessage(), ex);
                return false;
            }

        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return false;
        }
    }

    public synchronized boolean existeFoto(String loginUsuario) {
        sql = "SELECT (1) FROM FOTO_USUARIO WHERE idUsuario Like ?";
        try ( PreparedStatement ps = getCnn().prepareStatement(sql,
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.HOLD_CURSORS_OVER_COMMIT)) {
            try ( ResultSet rs = ps.executeQuery()) {
                return rs.next();
            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, ex.getMessage(), ex);
                return false;
            }
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return false;
        }
    }

    /*
    ---------------------------FIN--------------------------------
     */
    public synchronized boolean getControlConsulta(String fecha) {
        sql = "SELECT (1) FROM V_CONTROLCONSULTA WHERE fecha = ?";
        try ( PreparedStatement ps = getCnn().prepareStatement(sql,
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.HOLD_CURSORS_OVER_COMMIT)) {
            ps.setString(1, fecha);
            try ( ResultSet rs = ps.executeQuery()) {
                return rs.next();
            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, ex.getMessage(), ex);
                return false;
            }
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return false;
        }
    }

    public synchronized int getIdMadrePadre(String cedula) {
        sql = "SELECT IDPADRE FROM V_PADRES WHERE CEDULA LIKE ?";
        try ( PreparedStatement ps = getCnn().prepareStatement(sql,
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.HOLD_CURSORS_OVER_COMMIT)) {

            ps.setString(1, cedula);

            try ( ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("IDPADRE");
                } else {
                    return 0;
                }
            }

        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return -1;
    }

    public synchronized static int getIdPaciente(String cedula) {
        sql = "SELECT IDPACIENTE FROM V_PACIENTES WHERE CEDULA LIKE ?";

        try ( PreparedStatement ps = getCnn().prepareStatement(sql,
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.HOLD_CURSORS_OVER_COMMIT)) {

            ps.setString(1, cedula);

            try ( ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("IDPACIENTE");
                } else {
                    return 0;
                }
            }

        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return -1;
    }

    public synchronized static ResultSet getPacienteActivo(boolean estado) {
        sql = "SELECT IDPACIENTE, IDMADRE, CEDULAMADRE, nombreMadre, IDPADRE, "
                + "CEDULAPADRE, nombrePadre, CEDULAPACIENTE, NOMBRES, APELLIDOS, SEXO, "
                + "IDTIPOSANGRE, IDARS, COALESCE(NONSS, '') as NONSS, ESTADO "
                + "FROM GET_PACIENTES "
                + "WHERE Estado IS ?";

        try ( PreparedStatement ps = getCnn().prepareStatement(sql,
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.HOLD_CURSORS_OVER_COMMIT)) {

            ps.setBoolean(1, estado);

            return ps.executeQuery();
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        }
    }

    public synchronized static ResultSet getPacienteActivo(String filtro, String fecha,
            int idControlConsulta) {
        sql = "SELECT a.IDPACIENTE, a.CEDULAPACIENTE, a.NOMBRES, a.APELLIDOS, a.SEXO, "
                + "a.IDARS, COALESCE(a.NONSS, '') as NONSS "
                + "FROM GET_PACIENTES a "
                + "WHERE IDPACIENTE not in (SELECT IDPACIENTE"
                + "                         FROM V_CONSULTAS C "
                + "                         WHERE C.FECHA = ? and "
                + "                               C.IDCONTROLCONSULTA = ?) and "
                + "a.Estado " + filtro;

        try ( PreparedStatement ps = getCnn().prepareStatement(sql,
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.HOLD_CURSORS_OVER_COMMIT)) {

            ps.setString(1, fecha);
            ps.setInt(2, idControlConsulta);

            return ps.executeQuery();
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        }
    }

    public synchronized static ResultSet getPacienteActivo2(String filtro, String fecha,
            int idControlConsulta) {
        sql = "SELECT p.IDPACIENTE, p.CEDULAPACIENTE, p.NOMBRES, "
                + "p.APELLIDOS, p.SEXO, p.IDARS, p.NONSS, p.COVER, p.ESTADO,"
                + " c.IDCONSULTA,c.TURNO, c.FECHA "
                + "FROM GET_PACIENTES p "
                + "RIGHT JOIN V_CONSULTAS c ON c.IDPACIENTE = p.IDPACIENTE "
                + "left join V_CONSULTAS_APROVADAS A on A.IDCONSULTA = C.IDCONSULTA "
                + "WHERE A.IDCONSULTA is null and "
                + "      c.FECHA = ? and  c.IDCONTROLCONSULTA = ? "
                + filtro + " order by turno";

        try ( PreparedStatement ps = getCnn().prepareStatement(sql,
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.HOLD_CURSORS_OVER_COMMIT)) {

            ps.setString(1, fecha);
            ps.setInt(2, idControlConsulta);

            return ps.executeQuery();

        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        }
    }

    public synchronized static ResultSet getPacienteActivo3(String filtro, String fecha,
            int idControlConsulta) {
        sql = "SELECT C.IDCONSULTA, C.TURNO, P.CEDULAPACIENTE, "
                + "P.NOMBRES, P.APELLIDOS, A.COD_AUTORIZACION, A.COSTO, "
                + "A.DESCUENTO, A.TOTALCOSTO "
                + "FROM GET_PACIENTES P "
                + "right join V_CONSULTAS C on C.IDPACIENTE = P.IDPACIENTE "
                + "right join V_CONSULTAS_APROVADAS A on A.IDCONSULTA = C.IDCONSULTA "
                + "WHERE C.FECHA = ? and "
                + "C.IDCONTROLCONSULTA = ? "
                + " order by TURNO";

        try ( PreparedStatement ps = getCnn().prepareStatement(sql)) {

            ps.setString(1, fecha);
            ps.setInt(2, idControlConsulta);

            return ps.executeQuery();
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        }
    }

    public synchronized static ResultSet getAntecedentes(int idPadre) {
        sql = "SELECT IDANTECEDENTE, DESCRIPCION "
                + "FROM V_ANTECEDENTES "
                + "WHERE IDPACIENTE = ?";

        try ( PreparedStatement ps = getCnn().prepareStatement(sql,
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.HOLD_CURSORS_OVER_COMMIT)) {

            ps.setInt(1, idPadre);

            return ps.executeQuery();
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        }
    }

    public synchronized static ResultSet getConsulta(String fecha) {
        sql = "SELECT idConsulta, TURNO, idPaciente, FINAL, NOMBRES, APELLIDOS, IDARS, NONSS "
                + "FROM GET_CONSULTAS "
                + "WHERE FECHA = ? and ESTADO";

        try ( PreparedStatement ps = getCnn().prepareStatement(sql,
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.HOLD_CURSORS_OVER_COMMIT)) {

            ps.setString(1, fecha);

            return ps.executeQuery();
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        }
    }

    public synchronized static ResultSet getGuiaDesarrollo(int idPaciente, boolean cero) {
        //Esto debe de unirsele otras tablas que traega como resultado
        //que desarrollo a tenido el niño en el tiempo desde el nacimiento
        sql = "SELECT B.ID_GVD, B.EDAD, B.CARACT_DESARR_EVALUAR, "
                + "          COALESCE((SELECT " + (cero ? " a.SI_NO " : " a.FECHA ")
                + "                    FROM V_DETALLE_GUIA_VIGIL A "
                + "                    WHERE A.IDPACIENTE = ? and "
                + "                    A.ID_GVD = B.ID_GVD), "
                + (cero ? "false" : "' '") + " ) as RESULTADO "
                + "FROM V_GUIA_VIGILANCIA_DESARROLLO B "
                + "WHERE b.EDAD " + (cero ? "" : "!") + "= 0";

        try ( PreparedStatement ps = getCnn().prepareStatement(sql,
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.HOLD_CURSORS_OVER_COMMIT)) {

            ps.setInt(1, idPaciente);

            return ps.executeQuery();
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        }
    }

    public synchronized static ResultSet getProveedor() {
        sql = "SELECT IDPROVEEDOR, CODIGO_PROVEEDOR, NOMBRE_PROVEEDOR, "
                + "TELEFONO_PROVEEDOR, ESTADO "
                + "FROM V_PROVEEDORES ";
        try ( PreparedStatement ps = getCnn().prepareStatement(sql,
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.HOLD_CURSORS_OVER_COMMIT)) {
            return ps.executeQuery();
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        }
    }

    public synchronized static ResultSet getMedicamento(boolean estado) {
        sql = "SELECT CODIGO_PROVEEDOR, IDMEDICAMENTO, "
                + "          NOMBREMEDICAMENTO, ESTADO "
                + "   FROM GET_MEDICAMENTO"
                + "   WHERE ESTADO IS ? ORDER BY 1, 3";
        try ( PreparedStatement ps = getCnn().prepareStatement(sql,
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.HOLD_CURSORS_OVER_COMMIT)) {

            ps.setBoolean(1, estado);

            return ps.executeQuery();

        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        }
    }

    public synchronized static ResultSet getMedicamentoFoto(String idMedicamento) {

        sql = "SELECT FOTO FROM V_MEDICAMENTOS "
                + "WHERE idMedicamento = ?";
        try ( PreparedStatement ps = getCnn().prepareStatement(sql,
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.HOLD_CURSORS_OVER_COMMIT)) {
            ps.setString(1, idMedicamento);
            return ps.executeQuery();

        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        }
    }

    public synchronized static ResultSet getHorario(String idUsuario) {
        sql = "SELECT IDCONTROLCONSULTA, CANTIDADPACIENTE,"
                + "          DIA, INICIAL, FINAL "
                + "FROM t_controlconsulta "
                + "WHERE IDUSUARIO = ? "
                + "order by DIA, INICIAL, FINAL";
        try ( PreparedStatement ps = getCnn().prepareStatement(sql,
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.HOLD_CURSORS_OVER_COMMIT)) {
            ps.setString(1, idUsuario);
            return ps.executeQuery();
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        }
    }

    public synchronized static ResultSet getFechaDoctores(String fecha, boolean actual) {
        sql = "SELECT IDCONTROLCONSULTA, loginName, dia, "
                + "INICIAL, FINAL, nombreCompleto, CANTIDAD_PACIENTE "
                + "FROM GET_controlConsulta "
                + "WHERE dia like (SELECT TCNOMBREDIA "
                + "                FROM NOMBRE_DIA_CORTO (?)) " + (actual ? " and CURRENT_DATE <= ? ;" : "");
        try ( PreparedStatement ps = getCnn().prepareStatement(sql)) {

            ps.setString(1, fecha);

            if (actual) {
                ps.setString(2, fecha);
            }

            return ps.executeQuery();

        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        }
    }

    public synchronized static ResultSet getMedicamentoActivo() {
        sql = "SELECT IDMEDICAMENTO, DESCRIPCION FROM V_MEDICAMENTOS WHERE estado ORDER BY 2";
        try ( PreparedStatement ps = getCnn().prepareStatement(sql,
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.HOLD_CURSORS_OVER_COMMIT)) {
            return ps.executeQuery();
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        }
    }

    /**
     *
     * @param estado
     * @return
     */
    public synchronized static ResultSet getPadresActivo(boolean estado) {
        sql = "SELECT IDPADRE, CEDULA, NOMBRES, APELLIDOS, SEXO, "
                + "IDTIPOSANGRE, TIPOSANGRE, IDARS, ARS, NONSS, "
                + "TELEFONO, MOVIL, CORREO, DIRECCION, CIUDAD, FECHANACIMIENTO, "
                + "ESTADO "
                + "FROM GET_PADRES "
                + "WHERE ESTADO IS ? and idPadre != 0";

        try ( PreparedStatement ps = getCnn().prepareStatement(sql,
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.HOLD_CURSORS_OVER_COMMIT)) {

            ps.setBoolean(1, estado);

            return ps.executeQuery();
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        }
    }

    public synchronized static ResultSet getPadresActivo(String cedula, String sexo) {
        sql = "SELECT IDPADRE, CEDULA, NOMBRES, APELLIDOS "
                + "FROM GET_PADRES "
                + "WHERE ESTADO AND CEDULA starting ? AND SEXO LIKE ?;";
        try ( PreparedStatement ps = getCnn().prepareStatement(sql,
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.HOLD_CURSORS_OVER_COMMIT)) {
            ps.setString(1, cedula);
            ps.setString(2, sexo);
            return ps.executeQuery();
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        }
    }

    public synchronized static ResultSet getPadresActivoID(int idPadre) {
        sql = "SELECT NOMBRES, APELLIDOS, ARS, NONSS "
                + "FROM GET_PADRES "
                + "WHERE IDPADRE = ?";

        try ( PreparedStatement ps = getCnn().prepareStatement(sql,
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.HOLD_CURSORS_OVER_COMMIT)) {

            ps.setInt(1, idPadre);

            return ps.executeQuery();
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        }
    }

    /**
     *
     * @param cedula
     * @return
     */
    public synchronized static ResultSet getPadresRecuperar(String cedula) {
        try ( PreparedStatement ps = getCnn().prepareStatement(Padres.RECUPERAR_PADRE,
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.HOLD_CURSORS_OVER_COMMIT)) {

            ps.setString(1, cedula);

            return ps.executeQuery();
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        }
    }

    /**
     *
     * @param idCliente
     * @return
     */
    public synchronized static ResultSet getDireccionesByID(int idCliente) {
        try ( PreparedStatement ps = getCnn().prepareStatement(Direcciones.SELECT_BY_ID,
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.HOLD_CURSORS_OVER_COMMIT)) {
            ps.setInt(1, idCliente);
            return ps.executeQuery();
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        }
    }

    /**
     *
     * @param idPaciente
     * @return
     */
    public synchronized static ResultSet getPacienteActivoID(int idPaciente) {
        try ( PreparedStatement ps = getCnn().prepareStatement(Paciente.GET_PACIENTES,
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.HOLD_CURSORS_OVER_COMMIT)) {
            ps.setInt(1, idPaciente);
            return ps.executeQuery();
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        }
    }

    /**
     * Metodo utilizado para llenar los JCombox del sistema.
     *
     * Metodo verificado el dia 25 de abril, el cual nos permite tener los ID de
     * los tipos de sangre que existe en el mundo, resultset utilizado para
     * llegar los ComboBox.
     *
     * Metodo actualizado el 23 de Junio del 2022, agregamos la clave Conexion y
     * utilizamos el metodo getInstance para tener mejor control.
     *
     * @return
     */
    public synchronized static List<TiposSangres> getTipoSangre() {
        List<TiposSangres> tiposSangresList = new ArrayList<>();
        TiposSangres tiposSangres = null;
        try ( PreparedStatement ps = getCnn().prepareStatement(
                TiposSangres.SELECT,
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.CLOSE_CURSORS_AT_COMMIT)) {

            try ( ResultSet rs = ps.executeQuery();) {
                while (rs.next()) {
                    tiposSangres = TiposSangres.builder().
                            id(rs.getInt("ID")).
                            descripcion(rs.getString("DESCRIPCION")).build();

                    tiposSangresList.add(tiposSangres);
                }
                return tiposSangresList;
            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, ex.getMessage(), ex);
                return null;
            }
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        }
    }

    public synchronized static ResultSet getMotivo() {
        sql = "SELECT IDMCONSULTA, DESCRIPCION, DEFENICION "
                + "FROM V_MOTIVOS_CONSULTA "
                + "WHERE ESTADO "
                + "ORDER BY 1";
        try ( PreparedStatement ps = getCnn().prepareStatement(sql,
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.HOLD_CURSORS_OVER_COMMIT)) {
            return ps.executeQuery();
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        }
    }

    public synchronized static ResultSet getDetalleMotivo(int idConsulta, int turno) {
        sql = "SELECT IDMCONSULTA "
                + "   FROM V_DETALLEMOTIVOCONSULTA d "
                + "   WHERE d.IDCONSULTA = ? and d.TURNO = ?";
        try ( PreparedStatement ps = getCnn().prepareStatement(sql,
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.HOLD_CURSORS_OVER_COMMIT)) {
            ps.setInt(1, idConsulta);
            ps.setInt(2, turno);
            return ps.executeQuery();
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        }
    }

    public synchronized static ResultSet getARS() {
        try ( PreparedStatement ps = getCnn().prepareStatement(ARS.SELECT_CANTIDAD,
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.HOLD_CURSORS_OVER_COMMIT)) {
            return ps.executeQuery();
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        }
    }

    public synchronized static ResultSet getPCefalico(int idPaciente) {
        sql = "SELECT OUT_FECHANACIMIENTO, "
                + "OUT_FECHACONSULTA, "
                + "OUT_DEFERENCIAFECHA, "
                + "OUT_PC "
                + "FROM PRO_PC(?)";
        try ( PreparedStatement ps = getCnn().prepareStatement(sql,
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.HOLD_CURSORS_OVER_COMMIT)) {
            ps.setInt(1, idPaciente);
            return ps.executeQuery();
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        }
    }

    public synchronized static ResultSet getPesoKG(int idPaciente) {
        sql = "SELECT OUT_FECHANACIMIENTO, "
                + "OUT_FECHACONSULTA, "
                + "OUT_DEFERENCIAFECHA, "
                + "OUT_PC "
                + "FROM PRO_PESO_EDAD(?)";
        try ( PreparedStatement ps = getCnn().prepareStatement(sql,
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.HOLD_CURSORS_OVER_COMMIT)) {
            ps.setInt(1, idPaciente);
            return ps.executeQuery();
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        }
    }

    public synchronized static ResultSet getLongitudOEstatura(int idPaciente) {
        sql = "SELECT OUT_FECHANACIMIENTO, OUT_FECHACONSULTA, "
                + "OUT_DEFERENCIAFECHA, OUT_LONGITUD, OUT_ESTATURA "
                + "FROM PRO_LONGITUD_ALTURA_EDAD(?)";
        try ( PreparedStatement ps = getCnn().prepareStatement(sql,
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.HOLD_CURSORS_OVER_COMMIT)) {
            ps.setInt(1, idPaciente);
            return ps.executeQuery();
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        }
    }

    public synchronized static ResultSet getLongitudPeso(int idPaciente) {
        sql = "SELECT OUT_FECHANACIMIENTO, OUT_FECHACONSULTA, "
                + "OUT_DEFERENCIAFECHA, OUT_LONGITUD, OUT_ESTATURA "
                + "FROM PRO_PESO_LONGITUD(?)";

        try ( PreparedStatement ps = getCnn().prepareStatement(sql,
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.HOLD_CURSORS_OVER_COMMIT)) {

            ps.setInt(1, idPaciente);

            return ps.executeQuery();
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        }
    }

    public synchronized static ResultSet getAlturaPeso(int idPaciente) {
        sql = "SELECT OUT_FECHANACIMIENTO, OUT_FECHACONSULTA, "
                + "OUT_DEFERENCIAFECHA, OUT_LONGITUD, OUT_ESTATURA "
                + "FROM PRO_PESO_ALTURA(?)";

        try ( PreparedStatement ps = getCnn().prepareStatement(sql,
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.HOLD_CURSORS_OVER_COMMIT)) {

            ps.setInt(1, idPaciente);

            return ps.executeQuery();
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        }
    }

    /**
     * Este metodo proporciona la información de los seguro que están en estado
     * activo en la base de datos, Llenando asi los comboBox de la aplicacion.
     *
     * @return devuelve la lista de seguro que existe en la base de datos
     */
    public synchronized static ResultSet getTipoSeguro() {
        sql = "SELECT ID, DESCRIPCION "
                + "FROM V_ARS "
                + "WHERE ESTADO";

        try ( PreparedStatement ps = getCnn().prepareStatement(sql,
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.HOLD_CURSORS_OVER_COMMIT)) {

            return ps.executeQuery();
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        }
    }

    public synchronized static ResultSet getDatosNacimiento(int id) {
        sql = "SELECT FECHANACIMIENTO, PESONACIMIENTOKG, ALTURA, MC,"
                + " CESAREA, TIEMPOGESTACION, PC "
                + "FROM V_DATOSNACIMIENTO "
                + "WHERE idPaciente = ?";
        try ( PreparedStatement ps = getCnn().prepareStatement(sql,
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.HOLD_CURSORS_OVER_COMMIT)) {

            ps.setInt(1, id);

            return ps.executeQuery();
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        }
    }

    /**
     *
     * @param buscar
     * @return
     */
    public boolean existeEstudiante(String buscar) {
        sql = "SELECT (1) FROM estudiantes WHERE matricula like '"
                + buscar + "' or nombres like '" + buscar + "' or apellidos like '"
                + buscar + "'";
        try ( PreparedStatement ps = getCnn().prepareStatement(sql);  ResultSet rs = ps.executeQuery()) {
            return rs.next();
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return false;
        }
    }

    /**
     *
     * @param matricula
     * @return
     */
    public boolean estadoEstudiante(String matricula) {
        sql = "SELECT (1) FROM estudiantes e WHERE e.matricula = " + matricula + " and e.estado = 1";
        try ( PreparedStatement ps = getCnn().prepareStatement(sql);  ResultSet rs = ps.executeQuery()) {
            return rs.next();
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return false;
        }
    }

    /**
     *
     * @param cedula
     * @return
     */
    public static boolean validarPadreMadre(String cedula) {
        sql = "SELECT 1 FROM PERSONA WHERE documento like ?";
        try ( PreparedStatement ps = getCnn().prepareStatement(sql);  ResultSet rs = ps.executeQuery()) {
            ps.setString(1, cedula);
            return rs.next();
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return false;
        }
    }

    /**
     *
     * @param patch
     * @return
     */
    public String getPatch(String patch) {
        sql = "SELECT patch FROM UBICACION_REPORTES WHERE Descripcion LIKE '" + patch + "'";
        try ( PreparedStatement ps = getCnn().prepareStatement(sql);  ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                return rs.getString("Patch");
            } else {
                return "C:\\\\";
            }
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return "C:\\\\";
        }
    }

    public static ResultSet getPadreMadres() {
        sql = "SELECT * FROM PADREMADRES";
        try ( PreparedStatement ps = getCnn().prepareStatement(sql)) {
            return ps.executeQuery();
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        }
    }

    /**
     *
     * @param idPadre
     * @return
     */
    public static ResultSet getPadreMadres(int idPadre) {
        sql = "SELECT * FROM PADREMADRES WHERE documento LIKE ?";
        try ( PreparedStatement ps = getCnn().prepareStatement(sql)) {
            ps.setInt(1, idPadre);
            return ps.executeQuery();
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        }
    }

    public ResultSet getHorario() {
        sql = "SELECT * FROM TANDAS order by 1";

        try ( PreparedStatement ps = getCnn().prepareStatement(sql)) {
            return ps.executeQuery();
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        }
    }

    public ResultSet getMensualidad(String matricula, String periodo) {
        sql = "SELECT "
                + "m.consecutivo, m.fecha_pago, "
                + "m.Estado, m.monto, m.pagado, m.total,"
                + " m.fecha_pagado, m.fecha_abono, m.periodo "
                + "FROM Mensualidad m WHERE matricula = " + matricula + " and"
                + " PERIODO like '" + periodo + "'";
        try ( PreparedStatement ps = getCnn().prepareStatement(sql)) {
            return ps.executeQuery();
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        }
    }

    public ResultSet getTandas(String edad) {
        sql = "SELECT t.id_tanda, trim(case lunes "
                + "when 1 then 'Lunes '"
                + "else trim('')"
                + "end||"
                + "case martes "
                + "when 1 then 'Martes '"
                + "else trim('')"
                + "end||"
                + "case miercoles "
                + "when 1 then 'Miercoles '"
                + "else trim('')"
                + "end||"
                + "case jueves "
                + "when 1 then 'Jueves '"
                + "else trim('')"
                + "end||"
                + "case viernes "
                + "when 1 then 'Viernes '"
                + "else trim('')"
                + "end||"
                + "case sabados "
                + "when 1 then 'Sabados '"
                + "else trim('')"
                + "end||"
                + "case domingos "
                + "when 1 then 'Domingos '"
                + "else trim('')"
                + "end)"
                + "||' De '||subString(t.Hora_Inicio FROM 1 for 8)"
                + "||' Hasta '||subString(t.Hora_Final FROM 1 for 8)"
                + "FROM Tandas t WHERE t.edad_minima <= " + edad + " and t.edad_maxima >= " + edad + "";
        try ( PreparedStatement ps = getCnn().prepareStatement(sql)) {
            return ps.executeQuery();
        } catch (SQLException ex) {

            return null;
        }
    }

    public ResultSet getTandas(Integer id_Tanda) {
        sql = "SELECT t.cantidad_estudiantes, t.edad_minima, "
                + "t.edad_maxima, t.con_edad "
                + "FROM Tandas t "
                + "WHERE t.id_tanda =" + id_Tanda + " ";

        try ( PreparedStatement ps = getCnn().prepareStatement(sql)) {
            return ps.executeQuery();
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        }
    }

    public static ResultSet getUbicaciones() {
        sql = "SELECT * FROM UBICACION_REPORTES";
        try ( PreparedStatement ps = getCnn().prepareStatement(sql)) {
            return ps.executeQuery();
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        }
    }

    public ResultSet getEstudiante(String matricula) {
        sql = "SELECT e.MATRICULA, e.CEDULA_PADREMADRE, e.NOMBRES, e.APELLIDOS, "
                + "       e.FECHANACIMIENTO, e.FECHAINGRESO, e.ESTADO, e.PERIODO_ACTUAL, "
                + "       (SELECt trim(case lunes when 1 then 'Lunes ' else trim('') end || "
                + "                    case martes when 1 then 'Martes ' else trim('') end || "
                + "                    case miercoles when 1 then 'Miercoles ' else trim('') end || "
                + "                    case jueves when 1 then 'Jueves ' else trim('') end || "
                + "                    case viernes when 1 then 'Viernes ' else trim('') end || "
                + "                    case sabados when 1 then 'Sabados ' else trim('') end || "
                + "                    case domingos when 1 then 'Domingos ' else trim('') end) ||' De '|| substring(HORA_INICIO FROM 1 for 8) ||' '||' Hasta '||substring(HORA_FINAL FROM 1 for 8) "
                + "        FROM Tandas t  "
                + "        WHERE t.ID_Tanda like e.ID_tanda) as dias, "
                + "        (SELECT p.NOMBRES||' '||p.APELLIDOS "
                + "         FROM PADREMADRES p "
                + "         WHERE p.DOCUMENTO like e.CEDULA_PADREMADRE) as NombrePadre, ID_tanda "
                + "FROM estudiantes e "
                + "WHERE e.MATRICULA = '" + matricula + "'";

        try ( PreparedStatement ps = getCnn().prepareStatement(sql)) {
            return ps.executeQuery();
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        }
    }

    public synchronized static ResultSet getProductosCriterios(int tipo,
            String criterio, boolean image) {
        sql = "SELECT p.IdProducto, p.Descripcion, p.Codigo " + (image ? ", image " : "")
                + "FROM TABLA_PRODUCTOS p ";
        switch (tipo) {
            case 1:
                sql = sql + "WHERE p.Codigo CONTAINING ? and estado order by 1";
                break;
            case 2:
                sql = sql + "WHERE p.Descripcion CONTAINING ? and estado order by 2";
                break;
            case 3:
                sql = sql
                        + "inner join tabla_entradas_Producto e on p.idProducto = e.idProducto "
                        + "WHERE p.idCategoria = ? and e.entrada > 0 and estado";
                break;
            case 4:
                sql = sql + "WHERE idCategoria = ? ";
                break;
            default:
                sql = sql + "WHERE estado order by 1 ";
        }

        try ( PreparedStatement ps = getCnn().prepareStatement(sql)) {
            if (tipo != 0) {
                ps.setString(1, criterio);
            }
            return ps.executeQuery();
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        }
    }

    /**
     * Metodo utilizado para obtener un producto identificado por su ID.
     *
     *
     * <b>Metodo actualizado el 22 abril 2022</b>, Nota: Agregado el campo id.
     *
     * <b>Metodo actualizado el 19 05 2022</b>, Nota: Se ha agregado nueva vista
     * para traer los datos del producto consultado. Esta vista agrega dos
     * campos mas que son DESC_CATEGORIA y IMAGEN_CATEGORIA
     *
     * <b>Actualizado el 09 julio 2022</b>, Nota: He modificado la clase
     * producto la cual ahora se construye utilizando Metodologia Builder.
     * Tambien, se modificó el nombre del metodo de plural a singular.
     *
     * @param id identificador del producto.
     *
     * @return Devuelve un objecto de la clase Producto consultado a la base de
     * datos por su identificador.
     */
    public synchronized static Producto getProducto(int id) {
        sql = "SELECT r.ID, r.IDCATEGORIA, r.DESC_CATEGORIA, r.IMAGEN_CATEGORIA, r.CODIGO, "
                + "     r.DESCRIPCION, r.IMAGEN_TEXTO, r.NOTA, r.FECHA_CREACION, r.ESTADO "
                + "FROM GET_PRODUCTOS r "
                + "WHERE r.ID = ?";
        try ( PreparedStatement ps = getCnn().prepareStatement(sql)) {
            ps.setInt(1, id);
            Producto miProducto = null;
            try ( ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    miProducto = Producto.builder().
                            id(rs.getInt("id")).
                            idCategoria(rs.getInt("idcategoria")).
                            desc_categoria(rs.getString("DESC_CATEGORIA")).
                            imagen_categoria(rs.getString("IMAGEN_CATEGORIA")).
                            codigo(rs.getString("codigo")).
                            descripcion(rs.getString("descripcion")).
                            ImagenTexto(rs.getString("IMAGEN_TEXTO")).
                            nota(rs.getString("nota")).
                            FechaCreacion(rs.getDate("fecha_Creacion")).
                            estado(rs.getBoolean("estado")).build();
                }
            }
            return miProducto;
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        }
    }

    /**
     * Metodo que permite recuperar las propiedades de los productos del
     * sistema. devolviendo asi un resulset con todos estos productos.
     *
     * OJO! Deberia investigar la cantidad o existencia del producto.
     *
     * Fecha de Actualización el 19/05/2022.
     *
     * @return resuelve un conjunto de datos de la tabla de los productos del
     * sistema.
     */
    public synchronized static List<Producto> getProductos() {
        List<Producto> listaProducto = new ArrayList<Producto>();
        Producto producto;
        try ( PreparedStatement ps = getCnn().prepareStatement(Producto.SELECT)) {

            try ( ResultSet rs = ps.executeQuery();) {
                while (rs.next()) {
                    producto = Producto.builder().
                            id(rs.getInt("ID")).
                            descripcion(rs.getString("DESCRIPCION")).
                            idCategoria(rs.getInt("IDCATEGORIA")).
                            desc_categoria(rs.getString("DESC_CATEGORIA")).
                            codigo(rs.getString("CODIGO")).
                            FechaCreacion(rs.getDate("FECHA_CREACION")).
                            nota(rs.getString("NOTA")).
                            estado(rs.getBoolean("ESTADO")).build();

                    listaProducto.add(producto);
                }
            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, ex.getMessage(), ex);
                return null;
            }
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        }
        return listaProducto;
    }

    /**
     *
     * @param criterio
     * @param filtro
     * @return
     */
    public synchronized static ResultSet getClientesFiltrados(String criterio,
            String filtro) {
        sql = "SELECT r.IDCLIENTE, r.NOMBRES, r.APELLIDOS, D.MONTO "
                + "FROM Tabla_Clientes r "
                + "INNER JOIN TABLA_DEUDAS D ON r.IDCLIENTE like D.IDCLIENTE "
                + filtro;
        try ( PreparedStatement ps = getCnn().prepareStatement(sql)) {

            if (criterio != null) {
                ps.setString(1, criterio);
            }

            return ps.executeQuery();
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        }
    }

    /**
     * Listado de clientes de la base de datos, obtenidos de la vista
     * GET_CLIENTES_SB, donde los clientes para mostrarse deben estar activo.
     *
     * @param filtro
     * @param criterio
     * @return
     */
    public synchronized static ResultSet getClientesCombo() {
        try ( PreparedStatement ps = getCnn().prepareStatement(Clientes.GET_CLIENTES_ESTADO_SB)) {
            return ps.executeQuery();
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        }
    }

    public synchronized static ResultSet getClientesCobros() {
        try ( PreparedStatement ps = getCnn().prepareStatement(
                "SELECT r.IDCLIENTE, (r.NOMBRES||' '||r.APELLIDOS) as nombre "
                + "FROM TABLA_CLIENTES r "
                + "WHERE r.DEUDAACTUAL > 0")) {
            return ps.executeQuery();
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        }
    }

    /**
     *
     * @return Devuelve todos los datos realacionado con los clientes en la base
     * de datos.
     */
    public static synchronized DefaultTableModel getClientesTablaSB() {
        String titulos[] = {"Cedulas", "Persona", "Primer Nombre", "Segundo Nombre",
            "Apellidos", "Sexo", "Fecha nacimiento", "Fecha Ingreso", "Estado"};

        try ( PreparedStatement ps = getCnn().prepareStatement(
                Clientes.GET_CLIENTES_SB,
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.HOLD_CURSORS_OVER_COMMIT)) {

            Object registro[] = new Object[titulos.length];
            DefaultTableModel dtmClientes = new DefaultTableModel(null, titulos);

            try ( ResultSet rs = ps.executeQuery();) {
                while (rs.next()) {
                    Generales g = Generales.builder().
                            cedula(rs.getString("cedula")).build();

                    Clientes p = Clientes.builder().
                            id_persona(rs.getInt("id")).
                            generales(g).build();
                    registro[0] = p;
                    registro[1] = rs.getString("persona").equalsIgnoreCase("j") ? "JURÍDICA" : "FÍSICA";
                    registro[2] = rs.getString("pnombre");
                    registro[3] = rs.getString("snombre");
                    registro[4] = rs.getString("apellidos");
                    registro[5] = rs.getString("sexo").equalsIgnoreCase("M") ? "MASCULINO" : "FEMENINO";
                    registro[6] = Utilidades.formatDate(rs.getDate("fecha_nacimiento"), "dd/MM/yyyy");
                    registro[7] = Utilidades.formatDate(rs.getDate("fecha_Ingreso"), "dd/MM/yyyy");
                    registro[8] = rs.getBoolean("Estado");
                    dtmClientes.addRow(registro);
                }

                return dtmClientes;
            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, ex.getMessage(), ex);
                return null;
            }

        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        }
    }

    public static synchronized void getClientesTablaSBCombo(JComboBox<Clientes> cmbCliente) {

        try ( PreparedStatement ps = getCnn().prepareStatement(
                Clientes.GET_CLIENTES_SB_COMBO,
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.CLOSE_CURSORS_AT_COMMIT)) {

            try ( ResultSet rs = ps.executeQuery();) {
                while (rs.next()) {
                    cmbCliente.addItem(Clientes.builder().
                            id_persona(rs.getInt("id")).
                            pNombre(rs.getString("pnombre")).
                            sNombre(rs.getString("snombre")).
                            apellidos(rs.getString("apellidos")).build());

                }

            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, ex.getMessage(), ex);
            }

        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }

    /**
     *
     *
     * Metodo actualizado el dia 25 de abril 2022.
     *
     * @param idDeuda
     * @param op
     * @return
     */
    public synchronized static String modificarDeuda(int idDeuda, String op) {
        try ( PreparedStatement ps = getCnn().prepareStatement(
                "SELECT S_SALIDA "
                + "FROM SP_UPDATE_DEUDA_ESTADO(?,?)")) {
            ps.setInt(1, idDeuda);
            ps.setString(2, op);
            try ( ResultSet rs = ps.executeQuery()) {
                rs.next();
                return rs.getString("S_SALIDA");
            }
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return "Error a ejecutar Operacion";
        }
    }

    /**
     *
     * @param idProducto
     * @param codigo
     * @return
     */
    public synchronized static ResultSet getProductoById(Integer idProducto,
            String codigo) {

        try ( PreparedStatement ps = getCnn().prepareStatement(Producto.SELECT_CODIGO_ID)) {
            ps.setString(1, codigo);

            if (idProducto == null) {
                idProducto = 0;
            }

            ps.setInt(2, idProducto);

            return ps.executeQuery();

        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        }
    }

    /**
     *
     * @return
     */
    public synchronized static ResultSet getCajerosActivos() {
        //Para que sirve este metodo...
        try ( PreparedStatement ps = getCnn().prepareStatement(Usuarios.GET_SELECT_USUARIOS_ACTIVOS)) {
            return ps.executeQuery();
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        }
    }

    /**
     *
     * @param estado
     * @return
     */
//    public synchronized static ResultSet getUsuario(boolean estado) {
//        try ( PreparedStatement ps = getCnn().prepareStatement(
//                Usuarios.GET_SELECT_USUARIOS,
//                ResultSet.TYPE_SCROLL_INSENSITIVE,
//                ResultSet.CONCUR_READ_ONLY,
//                ResultSet.HOLD_CURSORS_OVER_COMMIT)) {
//            ps.setBoolean(1, estado);
//            return ps.executeQuery();
//        } catch (SQLException ex) {
//            LOG.log(Level.SEVERE, ex.getMessage(), ex);
//            return null;
//        }
//    }
    /**
     *
     * @return
     */
//    public synchronized static ResultSet getUsuarioDoctor() {
//        try ( PreparedStatement ps = getCnn().prepareStatement(Usuarios.GET_SELECT_DOCTORES,
//                ResultSet.TYPE_SCROLL_INSENSITIVE,
//                ResultSet.CONCUR_READ_ONLY,
//                ResultSet.HOLD_CURSORS_OVER_COMMIT)) {
//
//            return ps.executeQuery();
//
//        } catch (SQLException ex) {
//            LOG.log(Level.SEVERE, ex.getMessage(), ex);
//            return null;
//        }
//    }
    /**
     *
     * @param idUsuario
     * @return
     */
//    public synchronized static boolean delega(String idUsuario) {
//        try ( PreparedStatement ps = getCnn().prepareStatement(Usuarios.DELEGA)) {
//            ps.setString(1, idUsuario);
//            try ( ResultSet rs = ps.executeQuery()) {
//                return rs.next();
//            } catch (SQLException ex) {
//                LOG.log(Level.SEVERE, ex.getMessage(), ex);
//                return false;
//            }
//        } catch (SQLException ex) {
//            LOG.log(Level.SEVERE, ex.getMessage(), ex);
//            return false;
//        }
//    }
    /**
     * Se está utilizando para llenar los comboBox de los usuarios activos.
     *
     * @return
     */
    public synchronized static ResultSet getUsuariosActivo() {
        try ( PreparedStatement ps = getCnn().prepareStatement("")) {
            return ps.executeQuery();
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        }
    }

    /**
     * Se esta utilizando para llenar la tabla de usuarios.
     *
     * @return
     */
    public synchronized static List<Usuarios> getUsuarios() {
        List<Usuarios> usuarios = new ArrayList<>();
        Usuarios u;
        try ( PreparedStatement ps = getCnn().prepareStatement(
                Usuarios.SELECT,
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.CLOSE_CURSORS_AT_COMMIT)) {
            try ( ResultSet rs = ps.executeQuery();) {
                while (rs.next()) {
                    u = Usuarios.builder().
                            pNombre((rs.getString("PNOMBRE") == null ? "" : rs.getString("PNOMBRE"))).
                            sNombre((rs.getString("SNOMBRE") == null ? "" : rs.getString("SNOMBRE"))).
                            apellidos((rs.getString("APELLIDOS") == null ? "" : rs.getString("APELLIDOS"))).
                            administrador(rs.getBoolean("ADMINISTRADOR")).
                            estado(rs.getBoolean("ESTADO")).
                            user_name(rs.getString("USER_NAME")).
                            descripcion(rs.getString("DESCRIPCION")).build();
                    usuarios.add(u);
                }
            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, ex.getMessage(), ex);
                return null;
            }
            return usuarios;
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        }
    }
}
