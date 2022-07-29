package sur.softsurena.datos.insert;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import lombok.extern.java.Log;
import org.apache.commons.codec.binary.Base64;
import static sur.softsurena.conexion.Conexion.getCnn;
import sur.softsurena.entidades.ARS;
import sur.softsurena.entidades.Categoria;
import sur.softsurena.entidades.Cliente;
import sur.softsurena.entidades.Consultas_aprobadas;
import sur.softsurena.entidades.ContactosEmail;
import sur.softsurena.entidades.ContactosTel;
import sur.softsurena.entidades.Control_Consulta;
import sur.softsurena.entidades.D_Recetas;
import sur.softsurena.entidades.DetalleFactura;
import sur.softsurena.entidades.EntradaProducto;
import sur.softsurena.entidades.Estudiantes;
import sur.softsurena.entidades.Factura;
import sur.softsurena.entidades.Metricas;
import sur.softsurena.entidades.Paciente;
import sur.softsurena.entidades.Padre;
import sur.softsurena.entidades.Producto;
import sur.softsurena.entidades.Proveedores;
import sur.softsurena.entidades.Tandas;
import sur.softsurena.entidades.Usuario;
import sur.softsurena.utilidades.Utilidades;

@Log
public class InsertMetodos {

    private static PreparedStatement ps;
    private static String sql;
    private static ResultSet rs;

    /**
     * Agregar las categorias de los productos a la base de datos en la tabla
     * Categoria.
     *
     * Actualizado el dia 13 de abril del 2022.
     *
     * Actualizado el dia 06 de Julio del 2022: Se agrega el campo static
     * INSERT_CATEGORIA.
     *
     * Actualizado el dia 09 de Julio del 2022: Se cambia el tipo de datos
     * devueltode boolean a String, que permite mostrar mensaje del estado del
     * metodo despues de hacer las operaciones.
     * 
     * @test agregarCategoria(), metodo creado para realizar pruebas al metodo.
     *
     * @param c Es un objeto de la clase Categoria que contiene los metodos y
     * atributos.
     *
     * @return Retorna un mensaje que permite saber si la categoria fue agregada
     * o no.
     */
    public synchronized static String agregarCategoria(Categoria c) {
        try {
            ps = getCnn().prepareStatement(Categoria.INSERT_CATEGORIA);

            ps.setString(1, c.getDescripcion());
            ps.setString(2, Utilidades.imagenEncode64(c.getPathImage()));
            ps.setBoolean(3, c.getEstado());

            ps.executeUpdate();

            return "Categoria agregada con exito.";
        } catch (SQLException ex) {
            //Instalar Logger
            log.log(Level.SEVERE, ex.getMessage());
            return "Error al insertar categoria.";
        }
    }

    /**
     * Agregar producto a la base de datos en la tabla productos.
     *
     * Actualizado el dia 13 Abril del 2022.
     *
     * Actualizado el dia 09 Julio del 2022, Nota: se ha llevado el SQL a la
     * clase producto y por lo tanto se utiliza el nombre del campo que contiene
     * el SQL de la consulta.
     *
     * @param p Objecto de la clase producto que permite obtener los valos de
     * del producto agregar.
     * 
     * @test agregarProducto() metodo que realiza la prueba unitaria del metodo. 
     *
     * @return Devuelve un mensaje que notifica si el producto fue agregado
     * correctamente o no.
     */
    public synchronized static String agregarProducto(Producto p) {
        try {
            ps = getCnn().prepareStatement(Producto.INSERT_PRODUCTO);

            ps.setInt(1, p.getIdCategoria());
            ps.setString(2, p.getCodigo());
            ps.setString(3, p.getDescripcion());
            ps.setString(4, Utilidades.imagenEncode64(p.getPathImagen()));
            ps.setString(5, p.getNota());
            ps.setBoolean(6, p.getEstado());

            ps.executeUpdate();
            return "Producto agregado correctamente.";
        } catch (SQLException ex) {
            //Instalar Logger
            log.log(Level.SEVERE, ex.getMessage());
            return "Error al Insertar Producto.";
        }
    }

    /**
     * Agregar entrada de producto a la base de datos en la tabla de entrada de
     * productos.
     *
     * Actualizado el dia 13 de abril del 2022.
     *
     * @param e Es un objeto de la clase EntradaProducto
     *
     * @return Devuelve un valor booleano que indica si el registro fue exitoso.
     */
    public synchronized static boolean agregarProductoEntrada(EntradaProducto e) {
        try {
            ps = getCnn().prepareStatement(EntradaProducto.INSERT);

            ps.setInt(1, e.getIdProvedor());
            ps.setString(2, e.getCod_factura());
            ps.setInt(3, e.getLinea());
            ps.setInt(4, e.getIdProducto());
            ps.setBigDecimal(5, e.getEntrada());
            ps.setDate(6, e.getFechaVecimiento());

            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            //Instalar Logger
            return false;
        }
    }

    /**
     * Metodos utilizado para agregar los clientes en el sistema, el cual es
     * utilizado para agregar los contactos de este.
     *
     * @param c Es el objecto de la clase cliente que contiene los metodos
     * necesario para obtener los datos del cliente.
     *
     * @param ct Es el objecto que nos permite agregar los tipos de contactos de
     * los clientes.
     *
     * @return
     */
    public synchronized static String agregarCliente(Cliente c, ContactosTel[] ct, ContactosEmail[] ce) {
        try {

            ps = getCnn().prepareStatement(Cliente.INSERT_CLIENTE);

            ps.setInt(1, c.getId_Provincia());
            ps.setInt(2, c.getId_Municipio());
            ps.setInt(3, c.getId_Distrito_Municipal());
            ps.setString(4, "" + c.getPersona());
            ps.setString(5, c.getCedula());
            ps.setString(6, c.getPNombre());
            ps.setString(7, c.getSNombre());
            ps.setString(8, c.getApellidos());
            ps.setString(9, "" + c.getSexo());
            ps.setString(10, c.getDireccion());
            ps.setDate(11, c.getFecha_Nacimiento());
            ps.setBoolean(12, c.getEstado());
            ps.setString(13, "" + c.getEstado_Civil());

            rs = ps.executeQuery();

            rs.next();

            int id = rs.getInt(1);
            //Tenemos el ID del Cliente vamos agregar los contactos

            if (!agregarContactosTel(id, ct)) {
                return "Error al agregar contactos telefonico del cliente.";
            }
            
            if (!agregarContactosEmail(id, ce)) {
                return "Error al agregar contactos correos electronicos del cliente.";
            }

            return "Cliente Agregado Correctamente";
        } catch (SQLException ex) {
            //Instalar Logger
            return "Error al insertar Cliente...";
        }
    }

    /**
     * Metodo para agregar numeros telefonicos de las personas del sistema.
     *
     * @param id
     * @param contactos
     * @return
     */
    public static boolean agregarContactosTel(int id, ContactosTel[] contactos) {
        try {
            ps = getCnn().prepareStatement(ContactosTel.INSERT);

            for (ContactosTel c : contactos) {
                ps.setInt(1, id);
                ps.setString(2, c.getTelefono());
                ps.addBatch();
            }

            return ps.execute();
        } catch (SQLException ex) {
            //Instalar Logger
        }

        return false;
    }
    
    /**
     * 
     * @param id
     * @param contactos
     * @return 
     */
    public static boolean agregarContactosEmail(int id, ContactosEmail[] contactos) {
        try {
            ps = getCnn().prepareStatement(ContactosEmail.INSERT);

            for (ContactosEmail c : contactos) {
                ps.setInt(1, c.getId_persona());
                ps.setString(2, c.getEmail());
                
                ps.addBatch();
            }

            return ps.execute();
        } catch (SQLException ex) {
            //Instalar Logger
        }

        return false;
    }

    /**
     *
     * @param u
     * @param c
     * @return
     */
    public synchronized static String agregarDoctor(Usuario u, ContactosTel[] c) {
        try {

            sql = "SELECT p.O_SQL "
                    + "FROM SP_INSERT_USUARIOS (?, ?, ?, ?, ?, ?, ?, ?, ?, ?) p;";

            ps = getCnn().prepareStatement(sql);

            ps.setString(1, u.getUserName());
            ps.setString(2, u.getClave());
            ps.setString(3, u.getPNombre());
            ps.setString(4, u.getSNombre());
            ps.setString(5, u.getApellidos());
            ps.setString(7, u.getRol());
            ps.setString(8, u.getCod_Exequatur());
            ps.setString(9, u.getEspecialidad());
            ps.setBoolean(10, u.getEstado());
            ps.setBoolean(11, u.getAdministrador());

            rs = ps.executeQuery();
            rs.next();
            int id = rs.getInt(1);

            /*Ya tenemos el ID del Usuario para agregar valores a la tabla de 
            contactos.*/
            agregarContactosTel(id, c);

            sql = "GRANT ? TO ?";

            ps = getCnn().prepareStatement(sql);

            ps.setString(1, u.getRol());
            ps.setString(2, u.getUserName());

            ps.executeUpdate();

            return "Usuario agregado correctamente";
        } catch (SQLException ex) {
            //Instalar Logger
            return "Error al insertar usuario...";
        }
    }

    /**
     * Metodo que permite agregar un paciente al sisteme. Primer metodo
     * testeado.
     *
     * nota: Metodo actualizado 22 Junio del 2022.
     *
     * @Test agregarPaciente(), metodo de la prueba del funcionamiento.
     *
     * @param p objecto de la clase Paciente, con los campos requerido para
     * agregar un pacient.
     *
     * @return Retorna un mensaje que indica si el registro fue completado o no.
     *
     */
    public synchronized static String agregarPaciente(Paciente p) {
        /*Proceso de agregar paciente revizado y actualizado el 22 abril 2022*/
        try {
            sql = "SELECT p.V_ID "
                    + "FROM SP_INSERT_PACIENTE (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) p;";

            ps = getCnn().prepareStatement(sql);

            ps.setInt(1, p.getIdPadre());
            ps.setInt(2, p.getIdMadre());
            ps.setString(3, p.getCedula().trim());
            ps.setString(4, p.getPNombre().trim());
            ps.setString(5, p.getSNombre().trim());
            ps.setString(6, p.getApellidos().trim());
            ps.setString(7, "" + p.getSexo());
            ps.setDate(8, p.getFecha_Nacimiento());
            ps.setInt(9, p.getId_Tipo_Sangre());
            ps.setInt(10, p.getId_Ars());
            ps.setString(11, p.getNoNSS().trim());
            ps.setBoolean(12, p.getEstado());

            rs = ps.executeQuery();
            /*Me quedo con la duda que si un paciente necesita numero de contacto
            Ya que los padres si deberian de tenerlo*/

            return "Paciente agregado correctamente";
        } catch (SQLException ex) {
            //Instalar Logger
            return "Error al insertar paciente...";
        }
    }

    public synchronized static String agregarSeguro(ARS a) {
        try {//Insertamos en la tablas no a la vista...
            sql = "EXECUTE PROCEDURE SP_INSERT_ARS (?, ?, ?);";

            ps = getCnn().prepareStatement(sql);

            ps.setString(1, a.getDescripcion());
            ps.setBigDecimal(2, a.getCovertura());
            ps.setBoolean(3, a.getEstado());

            ps.executeUpdate();

            return "Seguro agregado correctamente";
        } catch (SQLException ex) {
            //Instalar Logger
            return "Error al insertar Seguro...";
        }
    }

    public synchronized static boolean agregarMotivo(String m) {
        try {
            sql = "insert into V_MOTIVOS_CONSULTA (DESCRIPCION) values (?)";

            ps = getCnn().prepareStatement(sql);

            ps.setString(1, m);

            ps.executeUpdate();

            return true;
        } catch (SQLException ex) {
            //Instalar Logger
            return false;
        }
    }

    public synchronized static String agregarProveedor(Proveedores p) {
        try {
            
            ps = getCnn().prepareStatement(Proveedores.INSERT_PROVEEDOR);

            ps.setString(1, p.getCodigoProveedor());
            ps.setString(2, p.getPNombre());
            ps.setString(2, p.getSNombre());
            ps.setBoolean(4, p.getEstado());

            ps.executeUpdate();
            return "Proveedor agregado correctamente";
        } catch (SQLException ex) {
            //Instalar Logger
            return "Error al insertar Proveedor...";
        }
    }

    public synchronized static String agregarAntecedente(int id, String antecedente) {
        try {
            sql = "insert into V_ANTECEDENTES (IDPACIENTE, DESCRIPCION) values (?, ?)";

            ps = getCnn().prepareStatement(sql);

            ps.setInt(1, id);
            ps.setString(2, antecedente);

            ps.executeUpdate();
            return "Antecedente agregado correctamente";
        } catch (SQLException ex) {
            //Instalar Logger
            return "Error al insertar Antecedentes...";
        }
    }

    public synchronized String agregarConsulta(int idControlConsulta,
            String fecha, int turno, int idPaciente) {

        sql = "insert into V_CONSULTAS (IDCONTROLCONSULTA, "
                + "FECHA, TURNO, IDPACIENTE, ESTADO) "
                + "values (" + idControlConsulta + ", '" + fecha + "', "
                + turno + ", " + idPaciente + ", 0)";
        try {
            ps = getCnn().prepareStatement(sql);

            ps.executeUpdate(sql);
            return "Consulta agregada correctamente";
        } catch (SQLException ex) {
            //Instalar Logger
            return "Error al insertar consulta";
        }
    }

    public synchronized static int agregarReceta(int idPaciente, int idConsulta) {

        try {
            sql = "INSERT INTO V_RECETAS (IDPACIENTE, IDCONSULTA) VALUES (?, ?)";

            ps = getCnn().prepareStatement(sql);

            ps.setInt(1, idPaciente);
            ps.setInt(2, idConsulta);

            ps.executeUpdate();

            sql = "SELECT IDRECETAS "
                    + "FROM V_RECETAS "
                    + "WHERE IDPACIENTE = ? and IDCONSULTA = ?";

            ps = getCnn().prepareStatement(sql);

            ps.setInt(1, idPaciente);
            ps.setInt(2, idConsulta);

            rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt("IDRECETAS");
            } else {
                return -1;
            }

        } catch (SQLException ex) {
            //Instalar Logger
            return -1;
        }
    }

    public synchronized static void agregarRecetaDetalle(D_Recetas dr) {
        try {
            sql = "insert into V_DETALLERECETAS (IDRECETAS, LINEA, "
                    + "IDMEDICAMENTO, CANTIDAD, DETALLEDOSIS) "
                    + "values (?, ?, ?, ?, ?)";

            ps = getCnn().prepareStatement(sql);

            ps.setInt(1, dr.getId_receta());
            ps.setInt(2, dr.getLinea());
            ps.setInt(3, dr.getId_Medicamento());
            ps.setBigDecimal(4, dr.getCantidad());
            ps.setString(5, dr.getDetalleDosis());

            ps.executeUpdate(sql);
        } catch (SQLException ex) {
            //Instalar Logger
        }
    }

    public synchronized static String guardarImagen(File file, String id, String query) {
        try {
            if (file == null) {
                return "Proceso cancelado";
            }

            FileInputStream imageInFile = new FileInputStream(file);

            byte imageData[] = new byte[(int) file.length()];

            imageInFile.read(imageData);

            // Converting Image byte array into Base64 String
            String imageDataString = Base64.encodeBase64URLSafeString(imageData);

            ps = getCnn().prepareStatement(query);

            ps.setString(1, id);
            ps.setString(2, imageDataString);

            ps.executeUpdate();

            return "Foto Insertada";
        } catch (FileNotFoundException ex) {
            //Instalar Logger
        } catch (IOException ex) {
            //Instalar Logger
        } catch (SQLException ex) {
            //Instalar Logger
        }
        return "Foto NO Insertada";
    }

    public synchronized static void agregarMetricas(Metricas m) {
        try {
            sql = "insert into V_METRICAS (IDCONSULTA, PESOKG, ESTATURAMETRO, "
                    + "ESCEFALO, ENF_DETECT, HALLAZGOS_POS, ID, TX, COMPLEMENTO) "
                    + "values (?, ?, ?, ?, );";

            ps = getCnn().prepareStatement(sql);
            ps.setInt(1, m.getIdConsulta());
            ps.setBigDecimal(2, m.getPesoKG());
            ps.setBigDecimal(3, m.getEstaturaM());
            ps.setBigDecimal(4, m.getEscefalo());
            ps.setString(5, m.getEnf_detect());
            ps.setString(6, m.getHallazgosPositivo());
            ps.setString(7, m.getIdDiagnostico());
            ps.setString(8, m.getTx());
            ps.setString(9, m.getComplemento());
            ps.setString(10, Utilidades.imagenEncode64(m.getImagenPath()));

            ps.executeUpdate(sql);
        } catch (SQLException ex) {
            //Instalar Logger
        }
    }

    public synchronized static String agregarConsultaVerificada(Consultas_aprobadas ca) {
        try {
            sql = "insert into V_CONSULTAS_APROBADAS (ID, COD_AUTORIZACION, "
                    + "COSTO, DESCUENTO) "
                    + "values (?, ?, ?, ?);";

            ps = getCnn().prepareStatement(sql);

            ps.setInt(1, ca.getId());
            ps.setString(2, ca.getCodAutorizacion());
            ps.setBigDecimal(3, ca.getCosto());
            ps.setBigDecimal(4, ca.getDescuento());

            ps.executeUpdate();
            return "Consulta Aprobada correctamente";
        } catch (SQLException ex) {
            //Instalar Logger
            return "Error al insertar registro";
        }
    }

    public synchronized static String agregarControlConsulta(Control_Consulta cc) {
        try {
            sql = "insert into CONTROLCONSULTA (IDUSUARIO, CANTIDADPACIENTE, DIA, INICIAL, FINAL) "
                    + "values (?, ?, ?, ?, ?)";

            ps = getCnn().prepareStatement(sql);

            ps.setInt(1, cc.getId());
            ps.setInt(2, cc.getCantidad());
            ps.setString(3, cc.getDia());
            ps.setTime(4, cc.getInicial());
            ps.setTime(5, cc.getFinall());

            ps.executeUpdate();
            return "Cambios Guardados";
        } catch (SQLException ex) {
            //Instalar Logger
            return "No se pueden guardar los cambios";
        }
    }

    public static String agregarPadreMadre(Padre p) {
        try {
            sql = "SELECT p.O_ID "
                    + "FROM SP_INSERT_PADRES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,"
                    + " ?, ?, ?) p;";

            ps = getCnn().prepareStatement(sql);

            ps.setInt(1, p.getId_Ars());
            ps.setString(2, p.getNoNSS());
            ps.setInt(3, p.getId_Provincia());
            ps.setInt(4, p.getId_Municipio());
            ps.setInt(5, p.getId_Distrito_Municipal());
            ps.setInt(6, p.getId_Codigo_Postal());
            ps.setInt(7, p.getId_Tipo_Sangre());
            ps.setString(8, p.getCedula());
            ps.setString(9, p.getPNombre());
            ps.setString(10, p.getSNombre());
            ps.setString(11, p.getApellidos());
            ps.setString(12, "" + p.getSexo());
            ps.setString(13, p.getDireccion());
            ps.setDate(14, p.getFecha_Nacimiento());
            ps.setBoolean(15, p.getEstado());
            ps.setString(16, "" + p.getEstado_Civil());

            ResultSet rs = ps.executeQuery();

            rs.next();

            int id = rs.getInt(1);

            return "Padre Agregado Exitosamente...! id {" + id + "}";
        } catch (SQLException ex) {
            //Instalar Logger
            return "Padre no Agregado :( ...!";
        }
    }

    public String agregarPerfil(String Perfil) {
        try {
            sql = "insert into Perfiles(perfil) values(?)";

            ps = getCnn().prepareStatement(sql);

            ps.setString(1, Perfil);

            ps.executeUpdate(sql);
            return "Perfil Creado";
        } catch (SQLException ex) {
            //Instalar Logger
            return "Perfil no Creado:( ...! \n" + ex.toString();
        }
    }

    /**
     * Metodo que permite agregar un estudiante al sistema de ballet, el cual 
     * ejecuta un procedimiento almacenado en la base de datos. 
     * 
     * @param e Objecto de la clase estudiante que capsula los atributos de un 
     * estudiantes. 
     * 
     * @return Retorna un mensaje que indica si el estudiantes ha sido registrado
     * si o no. 
     */
    public String agregarEstudiante(Estudiantes e) {
        try {
            
            ps = getCnn().prepareStatement(Estudiantes.INSERT_ESTUDIANTE);

            ps.setInt(1, e.getId());
            ps.setString(2, e.getMatricula());
            ps.setInt(3, e.getIdPadre());
            ps.setInt(4, e.getIdMadre());

            ps.executeUpdate();
            return "Estudiante Agregado Correctamente";
        } catch (SQLException ex) {
            //Instalar Logger
            return "Error al agregar estudiante";
        }
    }

    public String agregarTanda(Tandas t) {
        try {
            ps = getCnn().prepareStatement(Tandas.INSERT_TANDA);

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

            ps.executeUpdate();
            return "Tanda Agregada Correctamente";
        } catch (SQLException ex) {
            //Instalar Logger
            return "Existe Problemas al agregar Tanda, Contactar SoftSureña :( ...! \n" + ex.toString();
        }
    }

    /**
     * Metodo para agregar las facturas al temporar del sistema.
     *
     * Este metodo fue actualizado el dia 24 abril del 2022. Este metodo fue
     * actualizado el 19 05 2022: Se agrego un parametro al Insert que le
     * faltaba.
     *
     * @param f Un objecto de Factura que recibe la funcion.
     * @return Retorna un valor booleando que indica si la factura fue inserta
     * true y false si hubo un error.
     */
    public synchronized static Integer agregarFacturaNombre(Factura f) {

        try {
            ps = getCnn().prepareStatement(Factura.INSERT_FACTURA);

            ps.setInt(1, f.getHeaderFactura().getIdCliente());
            ps.setInt(2, f.getHeaderFactura().getIdTurno());
            ps.setBigDecimal(3, f.getHeaderFactura().getEfectivo());
            ps.setBigDecimal(4, f.getHeaderFactura().getCambio());
            ps.setString(5, String.valueOf(f.getHeaderFactura().getEstado()));
            ps.setString(6, f.getHeaderFactura().getNombreTemp());

            Integer cantidad = ps.executeUpdate();

            return cantidad + agregarDetalleFactura(f);
        } catch (SQLException ex) {
            //Instalar Logger
            return -1;
        }

    }
    
    /**
     * Metodo utilizado para agregar los datos de los detalle de la factura del
     * sistema.
     *
     * Metodo actualizado el dia 19 05 2022, Nota: se actualizó el nombre de la
     * consulta por V_DETALLE_FACTURA. Se agrega el metodo execute() para que se
     * devuelto como true o false.
     *
     * @param d Es un objecto que tipo DetalleFactura que define el detalle de
     * las facturas el sistema.
     * 
     * @return Ahora devuelve un entero que indica las  cantidades de registros 
     * que fueron afectadas en la inserción del registro. 
     * 
     * Antes: Devuelve un valor booleano que indica true si el registro se hizo
     * con exito y false si hubo un error al insertarla.
     */
    public static synchronized Integer agregarDetalleFactura(Factura f) {
        List<DetalleFactura> d = f.getDetalleFactura();
        try {
            ps = getCnn().prepareStatement(DetalleFactura.INSERT_DETALLE_FACTURA);

            for (Iterator<DetalleFactura> i = d.iterator(); i.hasNext();) {
                DetalleFactura next = i.next();
                
                ps.setInt(1, f.getId());
                ps.setInt(2, next.getIdLinea());
                ps.setInt(3, next.getIdProducto());
                ps.setBigDecimal(4, next.getPrecio());
                ps.setBigDecimal(5, next.getCantidad());
                
                ps.addBatch();
                
            }

            return ps.executeUpdate();

        } catch (SQLException ex) {
            //Instalar Logger
            return -1;
        }
    }

    public boolean agregarProductoSalida(int IDENTRADA_PRODUCTO, int numero,
            String cencepto, String idProducto, double entrada, String idUsuario) {
        try {
            sql = "INSERT INTO ENTRADAS_PRODUCTO (IDENTRADA_PRODUCTO, CONCEPTO, "
                    + "IDPRODUCTO, ENTRADA, OP, IDUSUARIO, NUMERO)"
                    + "VALUES (?, ?, ?, ?, '-', ?, ? );";

            ps = getCnn().prepareStatement(sql);
            
//IDENTRADA_PRODUCTO
//cencepto
//idProducto
//entrada
//idUsuario
//numero

            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            //Instalar Logger
            return false;
        }
    }
}
