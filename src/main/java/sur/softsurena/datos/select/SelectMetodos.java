package sur.softsurena.datos.select;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import org.apache.commons.codec.binary.Base64;
import sur.softsurena.conexion.Conexion;
import static sur.softsurena.conexion.Conexion.getCnn;
import sur.softsurena.entidades.Categoria;
import sur.softsurena.entidades.Cliente;
import sur.softsurena.entidades.Imagen;
import sur.softsurena.entidades.Perfiles;
import sur.softsurena.entidades.Producto;
import sur.softsurena.entidades.Usuario;

public class SelectMetodos {

    private static PreparedStatement ps;
    private static ResultSet rs;
    private static String sql;

    public synchronized static String pathBaseDeDatos() {
        try {
            ps = getCnn().prepareStatement(
                    "SELECT MON$DATABASE_NAME FROM MON$DATABASE");
            rs = ps.executeQuery();
            rs.next();
            return rs.getString(1);
        } catch (SQLException ex) {
            //Instalar Logger
            return null;
        }
    }
    
    public synchronized static ResultSet metaBaseDatos(){
        try {
            ps = getCnn().prepareStatement(
                    "SELECT CURRENT_CONNECTION, CURRENT_DATE, CURRENT_ROLE, CURRENT_TIME, " +
                    "          CURRENT_TIMESTAMP, CURRENT_TRANSACTION, CURRENT_USER " +
                    "FROM RDB$DATABASE");
            rs = ps.executeQuery();
            return rs;
        } catch (SQLException ex) {
            //Instalar Logger
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
        try {
            ps = getCnn().prepareStatement(
                    "SELECT id FROM v_turnos WHERE TRIM(idUsuario) like ? and estado"
            );

            ps.setString(1, userName.trim().toUpperCase());

            rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt(1);
            } else {
                return 0;
            }

        } catch (SQLException ex) {
            //Instalar Logger
            return -1;
        }

    }

    public synchronized static ImageIcon getImagenes(String sql) {
        try {
            ps = getCnn().prepareStatement(sql);
            rs = ps.executeQuery();
            byte[] data = null;

            if (rs.next()) {
                data = Base64.decodeBase64(rs.getString("FOTO"));
            } else {
                if (rs.next()) {
                    data = Base64.decodeBase64(rs.getString("FOTO"));
                }
            }

            if (data == null) {
                return null;
            }

            BufferedImage img = null;
            try {
                img = ImageIO.read(new ByteArrayInputStream(data));
            } catch (IOException ex) {
                //Instalar Logger
            }
            return new ImageIcon(img);
        } catch (SQLException ex) {
            //Instalar Logger
        }
        return null;
    }

    public static synchronized ArrayList<Imagen> getImagenes() {
        ArrayList<Imagen> lista = new ArrayList<>();
        try {
            ps = getCnn().prepareStatement("SELECT imagen, nombre FROM tabla_Imagenes");
            rs = ps.executeQuery();

            while (rs.next()) {
                Blob blob = rs.getBlob("imagen");

                String nombre = rs.getObject("nombre").toString();

                byte[] data = blob.getBytes(1, (int) blob.length());

                BufferedImage img = null;

                try {
                    img = ImageIO.read(new ByteArrayInputStream(data));
                } catch (IOException ex) {
                    //Instalar Logger
                }

                lista.add(new Imagen(new ImageIcon(img), nombre));
            }
            rs.close();
        } catch (SQLException ex) {
            //Instalar Logger
        }
        return lista;
    }

    /**
     * Metodo que permite investigar si existe una descripcion de una categoria
     * ya existen.
     * 
     * Metodo actualizado, 06 julio 2022.: Se le aplic?? una restructuraci??n 
     * completa al metodo llevando el sql a la clase categoria.
     * 
     * @param descripcion Es la descripcion que se pretende dar a la categoria la
     * cual este metodo verifica de comprobar si existe o no. devolviendo true 
     * si existe o false si no existe. 
     * 
     * @return Retorna un valor boolean indicando si existe o no la descripcion
     * de la categoria que se le pretende dar. 
     */
    public synchronized static boolean existeCategoria(String descripcion) {
        try {
            ps = getCnn().prepareStatement(Categoria.SELECT_CATEGORIA_DESCRIPCION);

            ps.setString(1, descripcion);

            rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException ex) {
            //Instalar Logger
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
        try {
            ps = getCnn().prepareStatement(Categoria.SELECT_CATEGORIA);
            return ps.executeQuery();
        } catch (SQLException ex) {
            //Instalar Logger
            return null;
        }
    }

    /**
     * 
     * @param idCategoria
     * @return 
     */
    public synchronized static boolean existeCategoriaProductos(int idCategoria) {
        try {
            ps = getCnn().prepareStatement(Producto.EXISTE_CATEGORIA);
            ps.setInt(1, idCategoria);
            rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException ex) {
            //Instalar Logger
            return false;
        }
    }

    /**
     * Metodo que nos permite tener el conjunto de datos de las categorias que 
     * estan activas y con un producto que est?? activo y enlazado a una 
     * categoria. 
     * 
     * @return Retorna un conjunto de datos de tipo ResultSet.
     */
    public synchronized static ResultSet getCategoriaActivas() {
        try {
            ps = getCnn().prepareStatement(Categoria.CATEGORIA_ACTIVAS);
            return ps.executeQuery();
        } catch (SQLException ex) {
            //Instalar Logger
            return null;
        }
    }

    /*--------------------Fin de las consultas de categorias------------------*/

    public synchronized static int periodoMaquina() {
        sql = "SELECT r.D FROM V_TIME_LIC r";
        try {
            ps = getCnn().prepareStatement(sql);

            rs = ps.executeQuery();

            rs.next();
            return rs.getInt(1);
        } catch (SQLException ex) {
            //Instalar Logger
            return -1;
        }
    }

    /**
     * Metodo para consultar cual es el usuario actual del sistema.
     *
     * Metodo actualizado el 17/05/2022.
     *
     * @return Retorna un String con el dato de cual es usuario del sistema que
     * ha iniciado sessi??n actualmente.
     */
    public synchronized static String getUsuarioActual() {
        try {
            ps = getCnn().prepareStatement(
                    "SELECT TRIM(CURRENT_USER) FROM MON$DATABASE");

            rs = ps.executeQuery();

            rs.next();

            return rs.getString(1);
        } catch (SQLException ex) {
            //Instalar Logger
            return null;
        }
    }

    /**
     * Metodo que verifica la identificaci??n del equipo en el sistema, tomando 
     * su numero unico de registro.
     * 
     * @param idMaquina identificador del equipo.
     * 
     * @return Devuelve true si el equipo se encuentra resgistrado, y false si 
     * no existe registro en la base de datos. 
     */
    public synchronized static boolean existeIdMaquina(String idMaquina) {
        try {
            ps = getCnn().prepareStatement(
                    "SELECT (1) "
                    + "FROM V_FCH_LC a "
                    + "WHERE a.ID = ?");
            
            ps.setString(1, idMaquina.trim());
            
            rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException ex) {
            //Instalar Logger
            return false;
        }
    }

    public synchronized static ResultSet getBuscarTemporal(Integer idFactura) {
        sql = "SELECT IDFACTURA, IDLINEA, IDPRODUCTO, DESCRIPCION, CANTIDAD, PRECIO "
                + "FROM GET_DETALLEFACTURA "
                + "WHERE IDFACTURA = ? "
                + "order by 1 2";
        try {
            ps = getCnn().prepareStatement(sql);
            ps.setInt(1, idFactura);

            return ps.executeQuery();
        } catch (SQLException ex) {
            //Instalar Logger
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
        
        try {
            ps = getCnn().prepareStatement(Producto.BUSCAR_PRODUCTO_ID_DESCRIPCION_CODIGO);
            
            ps.setInt(1, Integer.parseInt(criterio));
            ps.setString(2, criterio);
            ps.setString(3, criterio);

            return ps.executeQuery();
            
        } catch (SQLException ex) {
            //Instalar Logger
            return null;
        }
    }

    public synchronized static ResultSet getMensajes() {
        try {
            ps = getCnn().prepareStatement(
                    "SELECT MENSAJE, NOMBREEMPRESA, DIRECCIONEMPRESA, TELEFONOEMPRESA "
                    + "FROM GET_MENSAJES "
            );
            return ps.executeQuery();
        } catch (SQLException ex) {
            //Instalar Logger
            return null;
        }
    }

    /**
     * Metodo revisado y actualizado el 26 de abril 2022.
     *
     * @param criterio
     * @return
     */
    public synchronized static boolean existeCliente(String criterio) {
        try {
            ps = getCnn().prepareStatement(Cliente.GET_CLIENTES);

            ps.setString(1, criterio.trim());
            ps.setString(2, criterio.trim());
            ps.setString(3, criterio.trim());
            ps.setString(4, criterio.trim());
            ps.setString(5, criterio.trim());

            rs = ps.executeQuery();

            return rs.next();
        } catch (SQLException ex) {
            //Instalar Logger
            return false;
        }
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
        try {
            ps = getCnn().prepareStatement("SELECT r.ROL "
                    + "FROM GET_USER_ROLES r "
                    + "WHERE r.USER_NAME = ?");

            ps.setString(1, userName.trim().toUpperCase());

            rs = ps.executeQuery();

            String aux;

            while (rs.next()) {
                aux = rs.getString("ROL");
                if (aux.equalsIgnoreCase("RDB$ADMIN")) {
                    aux = "ADMINISTRADOR";
                }
                roles.add(aux);
            }

            return roles;

        } catch (SQLException ex) {
            //Instalar Logger
            return null;
        }
    }

    /**
     * Metodo que verifica la existencia de un producto por su codigo de barra o
     * descripci??n. Metodo actualizado el 26 de abril 2022
     *
     * @param codigo este valor representa el valor del codigo de barra del
     * producto o nombre de 25 caracteres.
     * @return
     */
    public synchronized static boolean existeProducto(String codigo) {
        try {
            ps = getCnn().prepareStatement(Producto.EXISTE_PRODUCTO);

            ps.setString(1, codigo);
            ps.setString(2, codigo);

            rs = ps.executeQuery();

            return rs.next();
        } catch (SQLException ex) {
            //Instalar Logger
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
    public synchronized static ResultSet getUsuarios(String userName) {
        try {
            ps = getCnn().prepareStatement(Usuario.GET_ALL_USER);

            ps.setString(1, userName);
            return ps.executeQuery();
        } catch (SQLException ex) {
            //Instalar Logger
            return null;
        }
    }

    public synchronized static ResultSet getRoles() {
        try {
            ps = getCnn().prepareStatement(
                    "SELECT ROL FROM GET_ROLES");
            return ps.executeQuery();
        } catch (SQLException ex) {
            //Instalar Logger
            return null;
        }
    }

    public synchronized static ResultSet getTemporales() {
        try {
            ps = getCnn().prepareStatement(
                    "SELECT r.ID, r.NOMBRETEMP, r.PNOMBRE, r.SNOMBRE, "
                    + "r.APELLIDOS, r.ID_CLIENTE, r.FECHA,  r.IDUSUARIO, r.HORA, "
                    + "r.MONTO "
                    + "FROM GET_TEMPORALES r "
                    + "ORDER BY 1"
            );
            return ps.executeQuery();
        } catch (SQLException ex) {
            //Instalar Logger
            return null;
        }
    }

    public synchronized static ResultSet getReporteFacturas(String filtro) {
        try {
            sql = "SELECT f.idFactura, f.idCliente, (c.nombres||' '||c.apellidos) AS nombreFull, "
                    + "        f.fecha, d.idLinea, p.idProducto, p.descripcion, "
                    + "        precio,   d.cantidad, precio * d.cantidad AS Valor "
                    + "FROM tabla_facturas f "
                    + "INNER JOIN tabla_clientes c ON f.idCliente = c.idCliente "
                    + "INNER JOIN detalleFactura d ON f.idFactura = d.idFactura "
                    + "INNER JOIN tabla_productos p ON p.idproducto = d.idproducto "
                    + filtro;

            ps = getCnn().prepareStatement(sql);
            return ps.executeQuery();
        } catch (SQLException ex) {
            //Instalar Logger
            return null;
        }
    }

    /**
     * 
     * @param idCliente
     * @return 
     */
    public synchronized static ResultSet getCobrosClientesFactura(String idCliente) {
        try {
            ps = getCnn().prepareStatement(
                    "SELECT r.IDFACTURA, CAST(sum(d.CANTIDAD * d.PRECIO) as DECIMAL(15,2)) as Total, "
                    + "       r.FECHA, r.ESTADO "
                    + "FROM TABLA_FACTURAS r "
                    + "JOIN TABLA_DETALLEFACTURA d ON d.IDFACTURA = r.IDFACTURA "
                    + "WHERE r.IDCLIENTE like ? and r.ESTADO in('c', 'a') "
                    + "GROUP BY r.IDFACTURA, r.FECHA, r.ESTADO"
            );
            ps.setString(1, idCliente);
            return ps.executeQuery();
        } catch (SQLException ex) {
            //Instalar Logger
            return null;
        }
    }

    public synchronized static ResultSet getDeudaClientes(String estado) {
        try {
            ps = getCnn().prepareStatement(
                    "SELECT SUM(r.MONTO), case r.ESTADO "
                    + "when 'i' then 'Inicial:' "
                    + "when 'a' then 'Abonado:' "
                    + "when 'p' then 'Pagado:' "
                    + "when 'n' then 'Nulado:' "
                    + "end "
                    + "FROM GET_SUMA_DEUDA r "
                    + estado);
            return ps.executeQuery();
        } catch (SQLException ex) {
            //Instalar Logger
            return null;
        }
    }

    public synchronized static ResultSet getDeudaClientesEstado(String estado) {
        try {
            ps = getCnn().prepareStatement(
                    "SELECT r.IDDEUDAS, IIF(r.IDCLIENTE = '0', '000-0000000-0', "
                    + "r.IDCLIENTE) as IDCLIENTE, c.NOMBRES, c.APELLIDOS, "
                    + "r.CONCEPTO, r.MONTO, r.FECHA, "
                    + "        (IIF(r.ESTADO = 'i', 'Inicial', "
                    + "         IIF(r.ESTADO = 'p', 'Pagada', "
                    + "         IIF(r.ESTADO = 'a', 'Abonada', "
                    + "         IIF(r.ESTADO = 'n','Nula','No Definida'))))) as ESTADO "
                    + "FROM TABLA_DEUDAS r "
                    + "LEFT JOIN TABLA_CLIENTES c"
                    + "    ON c.IDCLIENTE LIKE r.IDCLIENTE "
                    + estado
                    + "ORDER by 1");
            return ps.executeQuery();
        } catch (SQLException ex) {
            //Instalar Logger
            return null;
        }
    }

    public synchronized static ResultSet getDeudaClientes() {
        try {
            ps = getCnn().prepareStatement(
                    "SELECT r.IDCLIENTE, (c.NOMBRES||' '||c.APELLIDOS) as nombres "
                    + "FROM TABLA_DEUDAS r "
                    + "LEFT JOIN TABLA_CLIENTES c"
                    + "    ON c.IDCLIENTE LIKE r.IDCLIENTE "
                    + "WHERE r.ESTADO IN('i', 'a') "
                    + "GROUP BY r.IDCLIENTE, c.NOMBRES, c.APELLIDOS ");
            return ps.executeQuery();
        } catch (SQLException ex) {
            //Instalar Logger
            return null;
        }
    }

    /*Consultas de las facturas*/
    public synchronized static ResultSet getFacturas() {
        try {
            ps = getCnn().prepareStatement(
                    "SELECT idFactura "
                    + "FROM V_facturas "
                    + "order by 1");
            return ps.executeQuery();
        } catch (SQLException ex) {
            //Instalar Logger
            return null;
        }
    }
    

    public synchronized static ResultSet getFacturasNombreClientes(int idFactura) {
        try {
            ps = getCnn().prepareStatement(
                    "SELECT r.IDCLIENTE, nombreCliente "
                    + "FROM TABLA_FACTURAS r "
                    + "WHERE r.IDFACTURA = ?"
                    + "order by 1");
            ps.setInt(1, idFactura);
            return ps.executeQuery();
        } catch (SQLException ex) {
            //Instalar Logger
            return null;
        }
    }

    public synchronized static ResultSet getFacturasDetalladas(String idFactura) {
        try {
            ps = getCnn().prepareStatement(
                    "SELECT factura.idFactura, factura.idCliente, nombres||' '||apellidos AS nombreFull, "
                    + "        fecha, idLinea, (SELECT p.Descripcion "
                    + "                            FROM TABLA_PRODUCTOS p "
                    + "                            WHERE p.idProducto = DETALLEFACTURA.IDPRODUCTO ) as Descripcion, "
                    + "        idProducto, precio, cantidad, precio * cantidad AS Valor "
                    + "FROM TABLA_FACTURAS "
                    + "INNER JOIN TABLA_CLIENTES ON factura.idCliente = cliente.idCliente "
                    + "INNER JOIN TABLA_DETALLEFACTURA ON factura.idFactura = detalleFactura.idFactura "
                    + "WHERE factura.idFactura = ? ");
            ps.setString(1, idFactura);
            return ps.executeQuery();
        } catch (SQLException ex) {
            //Instalar Logger
            return null;
        }
    }

    public synchronized static ResultSet getFacturasDetalladaPorCliente(String idCliente) {
        try {
            ps = getCnn().prepareStatement(
                    "SELECT f.idFactura, f.estado , f.fecha, f.USUARIO, "
                    + "COALESCE(SUM(d.precio * d.cantidad), 0.00) AS Valor "
                    + "FROM TABLA_FACTURAS f "
                    + "LEFT JOIN TABLA_CLIENTES c "
                    + "    ON f.idCliente = c.idCliente "
                    + "LEFT JOIN TABLA_DETALLEFACTURA d "
                    + "    ON f.idFactura = d.idFactura "
                    + "WHERE f.idCliente = ? "
                    + "GROUP BY f.idFactura, f.estado , f.fecha, f.USUARIO "
                    + "order by 1"
            );
            ps.setString(1, idCliente);
            return ps.executeQuery();
        } catch (SQLException ex) {
            //Instalar Logger
            return null;
        }
    }

    public synchronized static ResultSet getFacturasDetalladaPorCliente(
            String idCliente, int idFactura) {
        try {
            ps = getCnn().prepareStatement(
                    "SELECT f.idFactura, f.estado , f.fecha, f.USUARIO, "
                    + "COALESCE(SUM(d.precio * d.cantidad), 0.00) AS Valor "
                    + "FROM TABLA_FACTURAS f "
                    + "LEFT JOIN TABLA_CLIENTES c "
                    + "    ON f.idCliente = c.idCliente "
                    + "LEFT JOIN TABLA_DETALLEFACTURA d "
                    + "    ON f.idFactura = d.idFactura "
                    + "WHERE f.idCliente = ? and f.idFactura = ? "
                    + "GROUP BY f.idFactura, f.estado , f.fecha, f.USUARIO "
                    + "order by 1"
            );
            ps.setString(1, idCliente);
            ps.setInt(2, idFactura);
            return ps.executeQuery();
        } catch (SQLException ex) {
            //Instalar Logger
            return null;
        }
    }

    public synchronized static ResultSet getDeudaCliente(String idCliente) {
        try {
            ps = getCnn().prepareStatement(
                    "SELECT r.IDDEUDAS, r.CONCEPTO, r.MONTO, r.FECHA, r.ESTADO "
                    + "FROM TABLA_DEUDAS r "
                    + "WHERE r.IDCLIENTE LIKE ? AND r.ESTADO NOT IN('n', 'p')"
            );
            ps.setString(1, idCliente);

            return ps.executeQuery();
        } catch (SQLException ex) {
            //Instalar Logger
            return null;
        }
    }

    public synchronized static ResultSet getDeudaClienteExterna(String idDeuda) {
        try {
            ps = getCnn().prepareStatement(
                    "SELECT r.CODIGO, r.FECHA, r.HORA, r.MONTO "
                    + "FROM TABLA_PAGO_DEUDAS_EXTERNA r "
                    + "WHERE r.IDDEUDA = ?"
            );
            ps.setString(1, idDeuda);

            return ps.executeQuery();
        } catch (SQLException ex) {
            //Instalar Logger
            return null;
        }
    }

    //!OJO Metodo para analizarlo
    public synchronized static ResultSet getPagoDeudasExterna(int idDeuda) {
        try {
            ps = getCnn().prepareStatement(
                    "SELECT r.CODIGO, r.MONTO, r.FECHA, r.HORA "
                    + "FROM TABLA_PAGO_DEUDAS_EXTERNA r "
                    + "WHERE r.IDDEUDA = ?");
            ps.setInt(1, idDeuda);
            return ps.executeQuery();
        } catch (SQLException ex) {
            //Instalar Logger
            return null;
        }
    }

    //!OJO Metodo para analizarlo
    public synchronized static ResultSet getPagoDeudas(int idFactura) {
        try {
            ps = getCnn().prepareStatement(
                    "SELECT r.IDPAGODEUDA, r.FECHA, r.HORA, r.MONTOPAGO "
                    + "FROM TABLA_PAGODEUDA r "
                    + "WHERE r.IDFACTURA = ?");
            ps.setInt(1, idFactura);
            return ps.executeQuery();
        } catch (SQLException ex) {
            //Instalar Logger
            return null;
        }
    }

    public synchronized static ResultSet getEntradaProducto(int mes, int year) {
        try {
            ps = getCnn().prepareStatement(
                    "SELECT r.FECHAENTRADA, IIF(r.OP = '+', 'Entrada', 'Salida') as operacion, r.IDUSUARIO "
                    + "FROM TABLA_ENTRADAS_PRUDUCTO r "
                    + "WHERE EXTRACT(MONTH FROM r.FECHAENTRADA) = ? "
                    + "and EXTRACT(YEAR FROM r.FECHAENTRADA) = ? "
                    + "GROUP BY r.FECHAENTRADA,  r.OP, r.IDUSUARIO");

            ps.setInt(1, mes);
            ps.setInt(2, year);

            return ps.executeQuery();
        } catch (SQLException ex) {
            //Instalar Logger
            return null;
        }
    }

    /**
     * Metodo de consulta que es utilizada para obtener el numero de registros
     * de una tabla del sistema.
     * @param tabla nombre de la tabla para obtener las cantidad de registros.
     * 
     * @return Devuelve la cantindad de registros que existe en una tabla dada
     * en el parametro. 
     */
    public synchronized static int cantidadRegistros(String tabla) {
        try {//Conseguir con el conteos de las tablas....
            ps = getCnn().prepareStatement(
                    "SELECT COALESCE(cantidad, 0) as num "
                    + "FROM tabla_reccount "
                    + "WHERE tabla = ?;"
            );
            ps.setString(1, tabla);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("num");
            } else {
                return 0;
            }
        } catch (SQLException ex) {
            //Instalar Logger
            return 0;
        }
    }

    public synchronized static Perfiles getPerfilUsuario(int idPerfil) {
        try {

            ps = getCnn().prepareStatement(Perfiles.SELECT_ID);

            ps.setInt(1, idPerfil);

            rs = ps.executeQuery();

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
                    CAMBIO_CLAVE(rs.getBoolean("CAMBIO_CLAVE")).
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

        } catch (SQLException ex) {
            //Instalar Logger
            return null;
        }
    }
    
    /**
     * 
     * @param idTurno
     * @return 
     */
    public synchronized static Integer getNumFac(int idTurno) {
        try {
            ps = getCnn().prepareStatement("EXECUTE PROCEDURE SYSTEM_FACTURA(?)");
            ps.setInt(1, idTurno);

            if (ps.execute()) {
                ps = getCnn().prepareStatement("EXECUTE PROCEDURE SYSTEM_FACTURA(?)");

                ps.setInt(1, idTurno);

                rs = ps.executeQuery();
                if (rs.next()) {
                    return rs.getInt("S_RESULTADO");
                } else {
                    return null;
                }
            }
            return -1;
        } catch (SQLException ex) {
            //Instalar Logger
            return -1;
        }
    }

    public synchronized static BigDecimal getPrecioProducto(int idProducto) {

        try {
            ps = getCnn().prepareStatement(
                    "SELECT precio "
                    + "FROM TABLA_ENTRADAS_PRODUCTO "
                    + "WHERE idProducto = ?");

            ps.setInt(1, idProducto);

            rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getBigDecimal("precio");
            } else {
                return new BigDecimal(0);
            }
        } catch (SQLException ex) {
            //Instalar Logger
            return new BigDecimal(-1);
        }
    }

    public synchronized static BigDecimal getDeudaActual(String idCliente) {
        try {
            ps = getCnn().prepareStatement(
                    "SELECT r.DEUDAACTUAL "
                    + "FROM TABLA_DEUDAS r "
                    + "WHERE r.IDCLIENTE LIKE ?");
            ps.setString(1, idCliente);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getBigDecimal("DEUDAACTUAL");
            } else {
                return new BigDecimal(0);
            }
        } catch (SQLException ex) {
            //Instalar Logger
            return new BigDecimal(-1);
        }
    }

    public synchronized static int getIdAcceso(int idAcceso) {
        try {
            ps = getCnn().prepareStatement(
                    "SELECT r.IDACCESO "
                    + "FROM Tabla_ACCESO2 r "
                    + "WHERE r.IDPERFIL = ?");
            ps.setInt(1, idAcceso);

            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            } else {
                return 1;
            }
        } catch (SQLException ex) {
            //Instalar Logger
            return -1;
        }
    }

    public synchronized static BigDecimal sumaMontoPagoDeudaExterna(int idDeuda) {
        try {
            ps = getCnn().prepareStatement(
                    "SELECT SUM(r.MONTO) "
                    + "FROM TABLA_PAGO_DEUDAS_EXTERNA r "
                    + "WHERE r.IDDEUDA = ?");
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getBigDecimal(1);
            } else {
                return new BigDecimal(0);
            }
        } catch (SQLException ex) {
            //Instalar Logger
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
        try {
            ps = getCnn().prepareStatement(
                    "SELECT creador "
                    + "FROM get_creador "
                    + "WHERE TRIM(usuario) like ?;"
            );
            ps.setString(1, idUsuario);

            rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getString("creador");
            } else {
                return null;
            }

        } catch (SQLException ex) {
            //Instalar Logger
            return null;
        }
    }

    public static synchronized ImageIcon getImagen(String idUsuario) {
        ps = null;
        try {
            ps = getCnn().prepareStatement(
                    "SELECT imagen "
                    + "FROM Tabla_Imagenes "
                    + "WHERE TRIM(idUsuario) like ?");
            
            ps.setString(1, idUsuario);

            rs = ps.executeQuery();

            BufferedImage img = null;

            while (rs.next()) {
                Blob blob = rs.getBlob("imagen");
                byte[] data = blob.getBytes(1, (int) blob.length());
                try {
                    img = ImageIO.read(new ByteArrayInputStream(data));
                } catch (IOException ex) {

                    //Instalar Logger
                }
            }

            rs.close();

            return new ImageIcon(img);
        } catch (SQLException ex) {
//            //Instalar Logger
        }
        return null;
    }


    public synchronized static String getSexoPaciente(int idPaciente) {
        try {
            sql = "SELECT sexo "
                    + "FROM V_PACIENTES "
                    + "WHERE idPaciente = ?";

            ps = getCnn().prepareStatement(sql,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY,
                    ResultSet.HOLD_CURSORS_OVER_COMMIT);

            ps.setInt(1, idPaciente);

            rs = ps.executeQuery();

            rs.next();

            return rs.getString(1);
        } catch (SQLException ex) {

            //Instalar Logger
            return "N/A";
        }

    }

    public synchronized static int numeroPadres(boolean estado) {
        try {
            sql = "SELECT CANTIDAD "
                    + "FROM V_RECCOUNT "
                    + "WHERE ESTADO IS ? AND IDPADRE != 0;";
            ps = getCnn().prepareStatement(sql,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY,
                    ResultSet.HOLD_CURSORS_OVER_COMMIT);

            rs = ps.executeQuery();

            ps.setBoolean(1, estado);

            rs.next();
            return rs.getInt(1);
        } catch (SQLException ex) {

            //Instalar Logger
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

        try {
            ps = getCnn().prepareStatement(sql,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY,
                    ResultSet.HOLD_CURSORS_OVER_COMMIT);

            ps.setString(1, cedula);

            rs = ps.executeQuery();

            return rs.next();

        } catch (SQLException ex) {

            //Instalar Logger
            return false;
        }
    }

    public synchronized static boolean existePadre(String cedula, boolean estado) {
        sql = "SELECT (1) FROM V_PADRES WHERE cedula = ? and ESTADO IS ?";
        try {

            ps = getCnn().prepareStatement(sql,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY,
                    ResultSet.HOLD_CURSORS_OVER_COMMIT);

            ps.setString(1, cedula);
            ps.setBoolean(2, estado);

            rs = ps.executeQuery();

            return rs.next();
        } catch (SQLException ex) {

            //Instalar Logger
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

        try {
            ps = getCnn().prepareStatement(sql);

            rs = ps.executeQuery();

            return rs.next();

        } catch (SQLException ex) {

            //Instalar Logger
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
     * @return retorna un valor booleano que nos permite saber si existe "TRUE" o
     * no "FALSE" un usuario en el sistema.
     */
    public synchronized static boolean existeUsuario(String userName) {
        try {
            ps = getCnn().prepareStatement(Usuario.EXISTE_USUARIO);
            ps.setString(1, userName);
            rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException ex) {
            //Instalar Logger
            return false;
        }
    }

    public synchronized boolean existeFoto(String loginUsuario) {
        sql = "SELECT (1) FROM FOTO_USUARIO WHERE idUsuario Like ?";
        try {
            ps = getCnn().prepareStatement(sql,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY,
                    ResultSet.HOLD_CURSORS_OVER_COMMIT);

            rs = ps.executeQuery();

            return rs.next();
        } catch (SQLException ex) {

            //Instalar Logger
            return false;
        }
    }

    /*
    ---------------------------FIN--------------------------------
     */
    public synchronized boolean getControlConsulta(String fecha) {
        sql = "SELECT (1) FROM V_CONTROLCONSULTA WHERE fecha = ?";
        try {
            ps = getCnn().prepareStatement(sql,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY,
                    ResultSet.HOLD_CURSORS_OVER_COMMIT);

            ps.setString(1, fecha);

            rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException ex) {

            //Instalar Logger
            return false;
        }
    }

    public synchronized int getIdMadrePadre(String cedula) {
        sql = "SELECT IDPADRE FROM V_PADRES WHERE CEDULA LIKE ?";
        try {
            ps = getCnn().prepareStatement(sql,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY,
                    ResultSet.HOLD_CURSORS_OVER_COMMIT);

            rs = ps.executeQuery();
            ps.setString(1, cedula);

            if (rs.next()) {
                return rs.getInt("IDPADRE");
            } else {
                return 0;
            }

        } catch (SQLException ex) {

            //Instalar Logger
        }
        return -1;
    }

    public synchronized static int getIdPaciente(String cedula) {
        sql = "SELECT IDPACIENTE FROM V_PACIENTES WHERE CEDULA LIKE ?";

        try {
            ps = getCnn().prepareStatement(sql,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY,
                    ResultSet.HOLD_CURSORS_OVER_COMMIT);

            ps.setString(1, cedula);

            rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt("IDPACIENTE");
            } else {
                return 0;
            }

        } catch (SQLException ex) {

            //Instalar Logger
        }
        return -1;
    }

    public synchronized static ResultSet getPacienteActivo(boolean estado) {
        try {
            sql = "SELECT IDPACIENTE, IDMADRE, CEDULAMADRE, nombreMadre, IDPADRE, "
                    + "CEDULAPADRE, nombrePadre, CEDULAPACIENTE, NOMBRES, APELLIDOS, SEXO, "
                    + "IDTIPOSANGRE, IDARS, COALESCE(NONSS, '') as NONSS, ESTADO "
                    + "FROM GET_PACIENTES "
                    + "WHERE Estado IS ?";

            ps = getCnn().prepareStatement(sql,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY,
                    ResultSet.HOLD_CURSORS_OVER_COMMIT);

            ps.setBoolean(1, estado);

            return ps.executeQuery();
        } catch (SQLException ex) {

            //Instalar Logger
            return null;
        }
    }

    public synchronized static ResultSet getPacienteActivo(String filtro, String fecha,
            int idControlConsulta) {
        try {
            sql = "SELECT a.IDPACIENTE, a.CEDULAPACIENTE, a.NOMBRES, a.APELLIDOS, a.SEXO, "
                    + "a.IDARS, COALESCE(a.NONSS, '') as NONSS "
                    + "FROM GET_PACIENTES a "
                    + "WHERE IDPACIENTE not in (SELECT IDPACIENTE"
                    + "                         FROM V_CONSULTAS C "
                    + "                         WHERE C.FECHA = ? and "
                    + "                               C.IDCONTROLCONSULTA = ?) and "
                    + "a.Estado " + filtro;

            ps = getCnn().prepareStatement(sql,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY,
                    ResultSet.HOLD_CURSORS_OVER_COMMIT);

            ps.setString(1, fecha);
            ps.setInt(2, idControlConsulta);

            return ps.executeQuery();
        } catch (SQLException ex) {

            //Instalar Logger
            return null;
        }
    }

    public synchronized static ResultSet getPacienteActivo2(String filtro, String fecha,
            int idControlConsulta) {
        try {
            sql = "SELECT p.IDPACIENTE, p.CEDULAPACIENTE, p.NOMBRES, "
                    + "p.APELLIDOS, p.SEXO, p.IDARS, p.NONSS, p.COVER, p.ESTADO,"
                    + " c.IDCONSULTA,c.TURNO, c.FECHA "
                    + "FROM GET_PACIENTES p "
                    + "RIGHT JOIN V_CONSULTAS c ON c.IDPACIENTE = p.IDPACIENTE "
                    + "left join V_CONSULTAS_APROVADAS A on A.IDCONSULTA = C.IDCONSULTA "
                    + "WHERE A.IDCONSULTA is null and "
                    + "      c.FECHA = ? and  c.IDCONTROLCONSULTA = ? "
                    + filtro + " order by turno";

            ps = getCnn().prepareStatement(sql,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY,
                    ResultSet.HOLD_CURSORS_OVER_COMMIT);

            ps.setString(1, fecha);
            ps.setInt(2, idControlConsulta);

            return ps.executeQuery();

        } catch (SQLException ex) {

            //Instalar Logger
            return null;
        }
    }

    public synchronized static ResultSet getPacienteActivo3(String filtro, String fecha,
            int idControlConsulta) {
        try {
            sql = "SELECT C.IDCONSULTA, C.TURNO, P.CEDULAPACIENTE, "
                    + "P.NOMBRES, P.APELLIDOS, A.COD_AUTORIZACION, A.COSTO, "
                    + "A.DESCUENTO, A.TOTALCOSTO "
                    + "FROM GET_PACIENTES P "
                    + "right join V_CONSULTAS C on C.IDPACIENTE = P.IDPACIENTE "
                    + "right join V_CONSULTAS_APROVADAS A on A.IDCONSULTA = C.IDCONSULTA "
                    + "WHERE C.FECHA = ? and "
                    + "C.IDCONTROLCONSULTA = ? "
                    + " order by TURNO";

            ps = getCnn().prepareStatement(sql);

            ps.setString(1, fecha);
            ps.setInt(2, idControlConsulta);

            return ps.executeQuery();
        } catch (SQLException ex) {

            //Instalar Logger
            return null;
        }
    }

    public synchronized static ResultSet getAntecedentes(int idPadre) {
        try {
            sql = "SELECT IDANTECEDENTE, DESCRIPCION "
                    + "FROM V_ANTECEDENTES "
                    + "WHERE IDPACIENTE = ?";

            ps = getCnn().prepareStatement(sql,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY,
                    ResultSet.HOLD_CURSORS_OVER_COMMIT);

            ps.setInt(1, idPadre);

            return ps.executeQuery();
        } catch (SQLException ex) {
            //Instalar Logger
            return null;
        }
    }

    public synchronized static ResultSet getUsuario(boolean estado) {
        try {
            sql = "SELECT LOGINUSER, P_NOMBRE, S_NOMBRE, APELLIDOS, Movil,"
                    + "TELEFONO, Correo, Especialidad, rol, sq, SuperUsuario, "
                    + "ESTADO "
                    + "FROM GET_USUARIOS "
                    + "WHERE ESTADO IS ?";

            ps = getCnn().prepareStatement(sql,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY,
                    ResultSet.HOLD_CURSORS_OVER_COMMIT);

            ps.setBoolean(1, estado);

            return ps.executeQuery();
        } catch (SQLException ex) {

            //Instalar Logger
            return null;
        }
    }

    public synchronized static ResultSet getConsulta(String fecha) {
        try {
            sql = "SELECT idConsulta, TURNO, idPaciente, FINAL, NOMBRES, APELLIDOS, IDARS, NONSS "
                    + "FROM GET_CONSULTAS "
                    + "WHERE FECHA = ? and ESTADO";

            ps = getCnn().prepareStatement(sql,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY,
                    ResultSet.HOLD_CURSORS_OVER_COMMIT);

            ps.setString(1, fecha);

            return ps.executeQuery();
        } catch (SQLException ex) {

            //Instalar Logger
            return null;
        }
    }

    public synchronized static ResultSet getGuiaDesarrollo(int idPaciente, boolean cero) {
        try {
            //Esto debe de unirsele otras tablas que traega como resultado
            //que desarrollo a tenido el ni??o en el tiempo desde el nacimiento
            sql = "SELECT B.ID_GVD, B.EDAD, B.CARACT_DESARR_EVALUAR, "
                    + "          COALESCE((SELECT " + (cero ? " a.SI_NO " : " a.FECHA ")
                    + "                    FROM V_DETALLE_GUIA_VIGIL A "
                    + "                    WHERE A.IDPACIENTE = ? and "
                    + "                    A.ID_GVD = B.ID_GVD), "
                    + (cero ? "false" : "' '") + " ) as RESULTADO "
                    + "FROM V_GUIA_VIGILANCIA_DESARROLLO B "
                    + "WHERE b.EDAD " + (cero ? "" : "!") + "= 0";

            ps = getCnn().prepareStatement(sql,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY,
                    ResultSet.HOLD_CURSORS_OVER_COMMIT);

            ps.setInt(1, idPaciente);

            return ps.executeQuery();
        } catch (SQLException ex) {

            //Instalar Logger
            return null;
        }
    }

    public synchronized static ResultSet getProveedor() {
        try {
            sql = "SELECT IDPROVEEDOR, CODIGO_PROVEEDOR, NOMBRE_PROVEEDOR, "
                    + "TELEFONO_PROVEEDOR, ESTADO "
                    + "FROM V_PROVEEDORES ";

            ps = getCnn().prepareStatement(sql,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY,
                    ResultSet.HOLD_CURSORS_OVER_COMMIT);

            return ps.executeQuery();

        } catch (SQLException ex) {

            //Instalar Logger
            return null;
        }
    }

    public synchronized static ResultSet getMedicamento(boolean estado) {
        try {
            sql = "SELECT CODIGO_PROVEEDOR, IDMEDICAMENTO, "
                    + "          NOMBREMEDICAMENTO, ESTADO "
                    + "   FROM GET_MEDICAMENTO"
                    + "   WHERE ESTADO IS ? ORDER BY 1, 3";

            ps = getCnn().prepareStatement(sql,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY,
                    ResultSet.HOLD_CURSORS_OVER_COMMIT);

            ps.setBoolean(1, estado);

            return ps.executeQuery();

        } catch (SQLException ex) {

            //Instalar Logger
            return null;
        }
    }

    public synchronized static ResultSet getMedicamentoFoto(String idMedicamento) {

        try {
            sql = "SELECT FOTO FROM V_MEDICAMENTOS "
                    + "WHERE idMedicamento = ?";

            ps = getCnn().prepareStatement(sql,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY,
                    ResultSet.HOLD_CURSORS_OVER_COMMIT);

            ps.setString(1, idMedicamento);
            return ps.executeQuery();

        } catch (SQLException ex) {

            //Instalar Logger
            return null;
        }
    }

    public synchronized static ResultSet getUsuarioDoctor() {
        try {
            sql = "SELECT LOGINUSER, P_NOMBRE, S_NOMBRE, APELLIDOS "
                    + "FROM GET_USUARIOS "
                    + "WHERE ESTADO and rol like 'DOCTOR'";

            ps = getCnn().prepareStatement(sql,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY,
                    ResultSet.HOLD_CURSORS_OVER_COMMIT);

            return ps.executeQuery();

        } catch (SQLException ex) {

            //Instalar Logger
            return null;
        }
    }

    public synchronized static ResultSet getHorario(String idUsuario) {
        try {
            sql = "SELECT IDCONTROLCONSULTA, CANTIDADPACIENTE,"
                    + "          DIA, INICIAL, FINAL "
                    + "FROM t_controlconsulta "
                    + "WHERE IDUSUARIO = ? "
                    + "order by DIA, INICIAL, FINAL";

            ps = getCnn().prepareStatement(sql,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY,
                    ResultSet.HOLD_CURSORS_OVER_COMMIT);

            ps.setString(1, idUsuario);

            return ps.executeQuery();

        } catch (SQLException ex) {

            //Instalar Logger
            return null;
        }
    }

    public synchronized static ResultSet getFechaDoctores(String fecha, boolean actual) {
        try {
            sql = "SELECT IDCONTROLCONSULTA, loginName, dia, "
                    + "INICIAL, FINAL, nombreCompleto, CANTIDAD_PACIENTE "
                    + "FROM GET_controlConsulta "
                    + "WHERE dia like (SELECT TCNOMBREDIA "
                    + "                FROM NOMBRE_DIA_CORTO (?)) " + (actual ? " and CURRENT_DATE <= ? ;" : "");

            ps = getCnn().prepareStatement(sql);

            ps.setString(1, fecha);

            if (actual) {
                ps.setString(2, fecha);
            }

            return ps.executeQuery();

        } catch (SQLException ex) {

            //Instalar Logger
            return null;
        }
    }

    public synchronized static ResultSet getMedicamentoActivo() {
        sql = "SELECT IDMEDICAMENTO, DESCRIPCION FROM V_MEDICAMENTOS WHERE estado ORDER BY 2";
        try {
            ps = getCnn().prepareStatement(sql,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY,
                    ResultSet.HOLD_CURSORS_OVER_COMMIT);
            return ps.executeQuery();
        } catch (SQLException ex) {

            //Instalar Logger
            return null;
        }
    }

    /**
     * 
     * @param estado
     * @return 
     */
    public synchronized static ResultSet getPadresActivo(boolean estado) {
        try {
            sql = "SELECT IDPADRE, CEDULA, NOMBRES, APELLIDOS, SEXO, "
                    + "IDTIPOSANGRE, TIPOSANGRE, IDARS, ARS, NONSS, "
                    + "TELEFONO, MOVIL, CORREO, DIRECCION, CIUDAD, FECHANACIMIENTO, "
                    + "ESTADO "
                    + "FROM GET_PADRES "
                    + "WHERE ESTADO IS ? and idPadre != 0";

            ps = getCnn().prepareStatement(sql,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY,
                    ResultSet.HOLD_CURSORS_OVER_COMMIT);

            ps.setBoolean(1, estado);

            return ps.executeQuery();
        } catch (SQLException ex) {

            //Instalar Logger
            return null;
        }
    }

    public synchronized static ResultSet getPadresActivo(String cedula, String sexo) {
        try {
            sql = "SELECT IDPADRE, CEDULA, NOMBRES, APELLIDOS "
                    + "FROM GET_PADRES "
                    + "WHERE ESTADO AND CEDULA starting ? AND SEXO LIKE ?;";

            ps = getCnn().prepareStatement(sql,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY,
                    ResultSet.HOLD_CURSORS_OVER_COMMIT);

            ps.setString(1, cedula);
            ps.setString(2, sexo);

            return ps.executeQuery();
        } catch (SQLException ex) {

            //Instalar Logger
            return null;
        }
    }

    public synchronized static ResultSet getPadresActivoID(int idPadre) {
        try {
            sql = "SELECT NOMBRES, APELLIDOS, ARS, NONSS "
                    + "FROM GET_PADRES "
                    + "WHERE IDPADRE = ?";

            ps = getCnn().prepareStatement(sql,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY,
                    ResultSet.HOLD_CURSORS_OVER_COMMIT);

            ps.setInt(1, idPadre);

            return ps.executeQuery();
        } catch (SQLException ex) {

            //Instalar Logger
            return null;
        }
    }

    public synchronized static ResultSet getPadresRecuperar(String cedula) {
        try {
            sql = "SELECT NOMBRES, APELLIDOS, SEXO, IDTIPOSANGRE, IDARS, "
                    + "NONSS, TELEFONO, MOVIL, CORREO, DIRECCION, CIUDAD, "
                    + "FECHANACIMIENTO "
                    + "FROM V_PADRES "
                    + "WHERE CEDULA = ? AND ESTADO IS FALSE;";

            ps = getCnn().prepareStatement(sql,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY,
                    ResultSet.HOLD_CURSORS_OVER_COMMIT);

            ps.setString(1, cedula);

            return ps.executeQuery();
        } catch (SQLException ex) {

            //Instalar Logger
            return null;
        }
    }

    public synchronized static ResultSet getPacienteActivoID(int idPaciente) {
        try {
            sql = "SELECT NOMBRES, APELLIDOS, IDARS, NONSS "
                    + "FROM GET_PACIENTES "
                    + "WHERE IDPACIENTE = " + idPaciente;

            ps = getCnn().prepareStatement(sql,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY,
                    ResultSet.HOLD_CURSORS_OVER_COMMIT);

            ps.setInt(1, idPaciente);

            return ps.executeQuery();

        } catch (SQLException ex) {

            //Instalar Logger
            return null;
        }
    }

    /**
     * Metodo utilizado para llenar los JCombox del sistema. 
     * 
     * Metodo verificado el dia 25 de abril, el cual nos permite tener los ID 
     * de los tipos de sangre que existe en el mundo, resultset utilizado para 
     * llegar los ComboBox.
     * 
     * Metodo actualizado el 23 de Junio del 2022, agregamos la clave Conexion y 
     * utilizamos el metodo getInstance para tener mejor control. 
     * @return 
     */
    public synchronized static ResultSet getTipoSangre() {
        try {
            ps = Conexion.getInstance().getCnn().prepareStatement(
                    "SELECT ID, DESCRIPCION "
                    + "FROM V_TIPOS_SANGRE order by 1");

            return ps.executeQuery();

        } catch (SQLException ex) {
            //Instalar Logger
            return null;
        }
    }

    public synchronized static ResultSet getMotivo() {
        try {

            sql = "SELECT IDMCONSULTA, DESCRIPCION, DEFENICION "
                    + "FROM V_MOTIVOS_CONSULTA "
                    + "WHERE ESTADO "
                    + "ORDER BY 1";

            ps = getCnn().prepareStatement(sql,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY,
                    ResultSet.HOLD_CURSORS_OVER_COMMIT);

            return ps.executeQuery();

        } catch (SQLException ex) {

            //Instalar Logger
            return null;
        }
    }

    public synchronized static ResultSet getDetalleMotivo(int idConsulta, int turno) {
        try {
            sql = "SELECT IDMCONSULTA "
                    + "   FROM V_DETALLEMOTIVOCONSULTA d "
                    + "   WHERE d.IDCONSULTA = ? and d.TURNO = ?";

            ps = getCnn().prepareStatement(sql,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY,
                    ResultSet.HOLD_CURSORS_OVER_COMMIT);

            ps.setInt(1, idConsulta);
            ps.setInt(2, turno);

            return ps.executeQuery();
        } catch (SQLException ex) {

            //Instalar Logger
            return null;
        }
    }

    public synchronized static ResultSet getARS() {
        try {
            sql = "SELECT IDARS, DESCRIPCION, COVERCONSULTAPORC, ESTADO, CANTIDAD "
                    + "FROM GET_ARS "
                    + "WHERE idArs != 0";

            ps = getCnn().prepareStatement(sql,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY,
                    ResultSet.HOLD_CURSORS_OVER_COMMIT);

            return ps.executeQuery();

        } catch (SQLException ex) {

            //Instalar Logger
            return null;
        }
    }

    public synchronized static ResultSet getPCefalico(int idPaciente) {
        try {
            sql = "SELECT OUT_FECHANACIMIENTO, "
                    + "OUT_FECHACONSULTA, "
                    + "OUT_DEFERENCIAFECHA, "
                    + "OUT_PC "
                    + "FROM PRO_PC(?)";

            ps = getCnn().prepareStatement(sql,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY,
                    ResultSet.HOLD_CURSORS_OVER_COMMIT);

            ps.setInt(1, idPaciente);

            return ps.executeQuery();

        } catch (SQLException ex) {

            //Instalar Logger
            return null;
        }
    }

    public synchronized static ResultSet getPesoKG(int idPaciente) {
        try {
            sql = "SELECT OUT_FECHANACIMIENTO, "
                    + "OUT_FECHACONSULTA, "
                    + "OUT_DEFERENCIAFECHA, "
                    + "OUT_PC "
                    + "FROM PRO_PESO_EDAD(?)";

            ps = getCnn().prepareStatement(sql,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY,
                    ResultSet.HOLD_CURSORS_OVER_COMMIT);

            ps.setInt(1, idPaciente);

            return ps.executeQuery();
        } catch (SQLException ex) {

            //Instalar Logger
            return null;
        }
    }

    public synchronized static ResultSet getLongitudOEstatura(int idPaciente) {
        try {
            sql = "SELECT OUT_FECHANACIMIENTO, OUT_FECHACONSULTA, "
                    + "OUT_DEFERENCIAFECHA, OUT_LONGITUD, OUT_ESTATURA "
                    + "FROM PRO_LONGITUD_ALTURA_EDAD(?)";

            ps = getCnn().prepareStatement(sql,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY,
                    ResultSet.HOLD_CURSORS_OVER_COMMIT);

            ps.setInt(1, idPaciente);

            return ps.executeQuery();
        } catch (SQLException ex) {

            //Instalar Logger
            return null;
        }
    }

    public synchronized static ResultSet getLongitudPeso(int idPaciente) {
        try {
            sql = "SELECT OUT_FECHANACIMIENTO, OUT_FECHACONSULTA, "
                    + "OUT_DEFERENCIAFECHA, OUT_LONGITUD, OUT_ESTATURA "
                    + "FROM PRO_PESO_LONGITUD(?)";

            ps = getCnn().prepareStatement(sql,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY,
                    ResultSet.HOLD_CURSORS_OVER_COMMIT);

            ps.setInt(1, idPaciente);

            return ps.executeQuery();
        } catch (SQLException ex) {

            //Instalar Logger
            return null;
        }
    }

    public synchronized static ResultSet getAlturaPeso(int idPaciente) {
        try {
            sql = "SELECT OUT_FECHANACIMIENTO, OUT_FECHACONSULTA, "
                    + "OUT_DEFERENCIAFECHA, OUT_LONGITUD, OUT_ESTATURA "
                    + "FROM PRO_PESO_ALTURA(?)";

            ps = getCnn().prepareStatement(sql,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY,
                    ResultSet.HOLD_CURSORS_OVER_COMMIT);

            ps.setInt(1, idPaciente);

            return ps.executeQuery();
        } catch (SQLException ex) {

            //Instalar Logger
            return null;
        }
    }

    /**
     * Este metodo proporciona la informaci??n de los seguro que est??n en estado
     * activo en la base de datos, Llenando asi los comboBox de la aplicacion.
     *
     * @return devuelve la lista de seguro que existe en la base de datos
     */
    public synchronized static ResultSet getTipoSeguro() {
        try {
            sql = "SELECT ID, DESCRIPCION "
                    + "FROM V_ARS "
                    + "WHERE ESTADO";

            ps = getCnn().prepareStatement(sql,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY,
                    ResultSet.HOLD_CURSORS_OVER_COMMIT);

            return ps.executeQuery();
        } catch (SQLException ex) {

            //Instalar Logger
            return null;
        }
    }

    public synchronized static ResultSet getDatosNacimiento(int id) {
        try {
            sql = "SELECT FECHANACIMIENTO, PESONACIMIENTOKG, ALTURA, MC,"
                    + " CESAREA, TIEMPOGESTACION, PC "
                    + "FROM V_DATOSNACIMIENTO "
                    + "WHERE idPaciente = ?";

            ps = getCnn().prepareStatement(sql,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY,
                    ResultSet.HOLD_CURSORS_OVER_COMMIT);

            ps.setInt(1, id);

            return ps.executeQuery();

        } catch (SQLException ex) {

            //Instalar Logger
            return null;
        }
    }

    /*S O P H I A  S T U D I O*/
    public boolean existeEstudiante(String buscar) {
        try {
            sql = "SELECT (1) FROM estudiantes WHERE matricula like '"
                    + buscar + "' or nombres like '" + buscar + "' or apellidos like '"
                    + buscar + "'";
            ps = getCnn().prepareStatement(sql);
            rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException ex) {
            //Instalar Logger
            return false;
        }
    }

    public boolean estadoEstudiante(String matricula) {
        try {
            sql = "SELECT (1) FROM estudiantes e WHERE e.matricula = " + matricula + " and e.estado = 1";
            ps = getCnn().prepareStatement(sql);
            rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException ex) {
            //Instalar Logger
            return false;
        }
    }

    public static boolean validarPadreMadre(String cedula) {
        try {
            sql = "SELECT 1 FROM PERSONA WHERE documento like ?";
            ps = getCnn().prepareStatement(sql);

            ps.setString(1, cedula);

            rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException ex) {
            //Instalar Logger
            return false;
        }
    }

    public String getPatch(String patch) {
        try {
            sql = "SELECT patch FROM UBICACION_REPORTES WHERE Descripcion LIKE '" + patch + "'";
            ps = getCnn().prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("Patch");
            } else {
                return "C:\\\\";
            }
        } catch (SQLException ex) {
            //Instalar Logger
            return "C:\\\\";
        }
    }

    public static ResultSet getPadreMadres() {
        try {
            sql = "SELECT * FROM PADREMADRES";
            ps = getCnn().prepareStatement(sql);
            return ps.executeQuery();
        } catch (SQLException ex) {

            return null;
        }
    }

    public static ResultSet getPadreMadres(String Padre) {
        try {
            sql = "SELECT * FROM PADREMADRES WHERE documento LIKE '" + Padre + "'";
            ps = getCnn().prepareStatement(sql);
            return ps.executeQuery();
        } catch (SQLException ex) {

            return null;
        }
    }

    public ResultSet getHorario() {
        try {
            sql = "SELECT * FROM TANDAS order by 1";
            ps = getCnn().prepareStatement(sql);
            return ps.executeQuery();
        } catch (SQLException ex) {

            return null;
        }
    }

    public ResultSet getMensualidad(String matricula, String periodo) {
        try {
            sql = "SELECT "
                    + "m.consecutivo, m.fecha_pago, "
                    + "m.Estado, m.monto, m.pagado, m.total,"
                    + " m.fecha_pagado, m.fecha_abono, m.periodo "
                    + "FROM Mensualidad m WHERE matricula = " + matricula + " and"
                    + " PERIODO like '" + periodo + "'";
            ps = getCnn().prepareStatement(sql);
            return ps.executeQuery();
        } catch (SQLException ex) {

            return null;
        }
    }

    public ResultSet getTandas(String edad) {
        try {
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
            ps = getCnn().prepareStatement(sql);
            return ps.executeQuery();
        } catch (SQLException ex) {

            return null;
        }
    }

    public ResultSet getTandas(Integer id_Tanda) {
        try {
            sql = "SELECT t.cantidad_estudiantes, t.edad_minima, "
                    + "t.edad_maxima, t.con_edad "
                    + "FROM Tandas t "
                    + "WHERE t.id_tanda =" + id_Tanda + " ";
            ps = getCnn().prepareStatement(sql);
            return ps.executeQuery();
        } catch (SQLException ex) {

            return null;
        }
    }

    public static ResultSet getUbicaciones() {
        try {
            sql = "SELECT * FROM UBICACION_REPORTES";
            ps = getCnn().prepareStatement(sql);
            return ps.executeQuery();
        } catch (SQLException ex) {

            return null;
        }
    }

    public ResultSet getEstudiante(String matricula) {
        try {
            sql
                    = "SELECT e.MATRICULA, e.CEDULA_PADREMADRE, e.NOMBRES, e.APELLIDOS, "
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
            ps = getCnn().prepareStatement(sql);
            return ps.executeQuery();
        } catch (SQLException ex) {

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

        try {
            ps = getCnn().prepareStatement(sql);
            if (tipo != 0) {
                ps.setString(1, criterio);
            }
            return ps.executeQuery();
        } catch (SQLException ex) {
            //Instalar Logger
            return null;
        }
    }

    /**
     * Metodo utilizado para obtener un producto identificado por su ID.
     *
     *
     * <b>Metodo actualizado el 22 abril 2022</b>, Nota: Agregado el campo id.
     *
     * <b>Metodo actualizado el 19 05 2022</b>, Nota: Se ha agregado nueva vista para
     * traer los datos del producto consultado. Esta vista agrega dos campos mas
     * que son DESC_CATEGORIA y IMAGEN_CATEGORIA
     *
     * <b>Actualizado el 09 julio 2022</b>, Nota: He modificado la clase producto la
     * cual ahora se construye utilizando Metodologia Builder. Tambien, se
     * modific?? el nombre del metodo de plural a singular.
     *
     * @param id identificador del producto.
     * 
     * @return Devuelve un objecto de la clase Producto consultado a la base de
     * datos por su identificador.
     */
    public synchronized static Producto getProducto(int id) {
        sql = "SELECT r.ID, r.IDCATEGORIA, r.DESC_CATEGORIA, r.IMAGEN_CATEGORIA, r.CODIGO, "
                + "     r.DESCRIPCION, r.IMAGEN_TEXTO, r.NOTA, r.FECHA_CREACION, r.ESTADO, "
                + "FROM GET_PRODUCTOS r "
                + "WHERE r.ID = ?";
        try {
            ps = getCnn().prepareStatement(sql);

            ps.setInt(1, id);

            rs = ps.executeQuery();

            Producto miProducto = null;
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
            return miProducto;
        } catch (SQLException ex) {
            //Instalar Logger
            return null;
        }
    }

    /**
     * Metodo que permite recuperar las propiedades de los productos del
     * sistema. devolviendo asi un resulset con todos estos productos.
     *
     * OJO! Deberia investigar la cantidad o existencia del producto.
     *
     * Fecha de Actualizaci??n el 19/05/2022.
     *
     * @return resuelve un conjunto de datos de la tabla de los productos del
     * sistema.
     */
    public synchronized static ResultSet getProductos() {
        try {
            ps = getCnn().prepareStatement(
                    "SELECT r.ID, r.IDCATEGORIA, r.DESC_CATEGORIA, r.IMAGEN_CATEGORIA, r.CODIGO, "
                    + "     r.DESCRIPCION, r.IMAGEN_TEXTO, r.NOTA, r.FECHA_CREACION, r.ESTADO, "
                    + "     r.IDUSUARIO, r.ROL "
                    + "FROM GET_PRODUCTOS r "
            );
            return ps.executeQuery();
        } catch (SQLException ex) {
            //Instalar Logger
            return null;
        }
    }

    /**
     *
     * @param criterio
     * @param filtro
     * @return
     */
    public synchronized static ResultSet getClientesFiltrados(String criterio,
            String filtro) {
        try {
            sql = "SELECT r.IDCLIENTE, r.NOMBRES, r.APELLIDOS, D.MONTO "
                    + "FROM Tabla_Clientes r "
                    + "INNER JOIN TABLA_DEUDAS D ON r.IDCLIENTE like D.IDCLIENTE "
                    + filtro;

            ps = getCnn().prepareStatement(sql);

            if (criterio != null) {
                ps.setString(1, criterio);
            }

            return ps.executeQuery();
        } catch (SQLException ex) {
            //Instalar Logger
            return null;
        }
    }

    /**
     * Listado de clientes de la base de datos, obtenidos de la vista 
     * GET_CLIENTES_SB, donde los clientes para mostrarse deben estar activo.
     * @param filtro
     * @param criterio
     * @return
     */
    public synchronized static ResultSet getClientesCombo() {
        try {
            ps = getCnn().prepareStatement(Cliente.GET_CLIENTES_ESTAD);
            
            return ps.executeQuery();
            
        } catch (SQLException ex) {
            //Instalar Logger
            return null;
        }
    }

    public synchronized static ResultSet getClientesCobros() {
        try {
            ps = getCnn().prepareStatement(
                    "SELECT r.IDCLIENTE, (r.NOMBRES||' '||r.APELLIDOS) as nombre "
                    + "FROM TABLA_CLIENTES r "
                    + "WHERE r.DEUDAACTUAL > 0");

            return ps.executeQuery();
        } catch (SQLException ex) {
            //Instalar Logger
            return null;
        }
    }

    public static synchronized ResultSet getClientes(String idCliente) {
        try {
            sql = "SELECT idCliente, c.nombres ||' '||c.apellidos as NombreCompleto "
                    + "FROM tabla_clientes c "
                    + "WHERE c.idcliente = ?";

            ps = getCnn().prepareStatement(sql);
            ps.setString(1, idCliente);

            return ps.executeQuery(sql);
        } catch (SQLException ex) {
            //Instalar Logger
            return null;
        }
    }

    /**
     * 
     * @return Devuelve todos los datos realacionado con los clientes en la base
     * de datos.
     */
    public static synchronized ResultSet getClientes() {
        sql = "";
        try {
            ps = getCnn().prepareStatement(sql);
            return ps.executeQuery();
        } catch (SQLException ex) {
            //Instalar Logger
            return null;
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
        try {
            ps = getCnn().prepareStatement(
                    "SELECT S_SALIDA "
                    + "FROM SP_UPDATE_DEUDA_ESTADO(?,?)");

            ps.setInt(1, idDeuda);
            ps.setString(2, op);

            rs = ps.executeQuery();
            rs.next();

            return rs.getString("S_SALIDA");

        } catch (SQLException ex) {
            //Instalar Logger
            return "Error a ejecutar Operacion";
        }
    }

    /**
     * 
     * @param idUsuario
     * @return 
     */
    public synchronized static boolean delega(String idUsuario) {

        try {
            ps = getCnn().prepareStatement(
                    "SELECT (1) "
                    + "FROM GET_usuarios u "
                    + "WHERE trim(u.idusuario) = upper(trim(?)) and "
                    + "u.autorizado");
            
            ps.setString(1, idUsuario);
            
            rs = ps.executeQuery();
            
            return rs.next();
        } catch (SQLException ex) {
            //Instalar Logger
            return false;
        }
    }

    /**
     * 
     * @return 
     */
    public synchronized static ResultSet getUsuariosActivo() {
        //Se est?? utilizando para llenar los comboBox de los usuarios activos.
        try {
            ps = getCnn().prepareStatement(
                    "SELECT u.idUsuario, u.NombreUNO, u.Apellidos "
                    + "FROM GET_Usuarios u "
                    + "left JOIN tabla_TURNOS t on t.IDUSUARIO like u.IDUSUARIO "
                    + "WHERE TRIM(u.Rol) like 'CAJERO' and u.ESTADO"
            );
            
            return ps.executeQuery();
            
        } catch (SQLException ex) {
            //Instalar Logger
            return null;
        }
    }

    public synchronized static ResultSet getUsuariosActivos() {
        //Para que sirve este metodo...
        try {
            ps = getCnn().prepareStatement(
                    "SELECT r.IDUSUARIO, (r.NOMBREUNO||' '||r.APELLIDOS) AS Nombre "
                    + "FROM GET_USUARIOS r "
                    + "WHERE r.ESTADO");
            
            return ps.executeQuery();
            
        } catch (SQLException ex) {
            //Instalar Logger
            return null;
        }
    }

    /**
     * 
     * @return 
     */
    public synchronized static ResultSet getCajerosActivos() {
        //Para que sirve este metodo...
        try {
            ps = getCnn().prepareStatement(
                    "SELECT r.IDUSUARIO, r.FECHA, r.HORA "
                    + "FROM GET_USUARIO_ACTIVO r"
            );
            
            return ps.executeQuery();
            
        } catch (SQLException ex) {
            //Instalar Logger
            return null;
        }
    }

    /**
     * 
     * @return 
     */
    public synchronized static ResultSet getUsuarios() {
        //Se esta utilizando para llenar la tabla de usuarios
        sql = "SELECT r.IDUSUARIO, r.NOMBREUNO, r.NOMBREDOS, "
                + "       r.APELLIDOS, r.ESTADO, r.AUTORIZADO, r.ROL "
                + "FROM GET_USUARIOS r";
        try {

            ps = getCnn().prepareStatement(sql);

            return ps.executeQuery();

        } catch (SQLException ex) {
            //Instalar Logger
            return null;
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
        
        try {
            
            ps = getCnn().prepareStatement(
                    "select idProducto, codigo, descripcion, estado, image, "
                    + "idCategoria, Cantidad "
                    + "from tabla_Productos "
                    + "where codigo = ? or IDPRODUCTO = ?"
            );

            ps.setString(1, codigo);
        
            if (idProducto == null) {
                idProducto = 0;
            }
            
            ps.setInt(2, idProducto);
            
            return ps.executeQuery();
            
        } catch (SQLException ex) {
            //Instalar Logger
            return null;
        }
    }

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}


/**
 * Nota sobre esta clase....
 * 
 * 
 * 
 * 
 */
