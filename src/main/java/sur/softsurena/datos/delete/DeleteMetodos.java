package sur.softsurena.datos.delete;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import sur.softsurena.conexion.Conexion;
import static sur.softsurena.conexion.Conexion.getCnn;
import sur.softsurena.entidades.ARS;
import sur.softsurena.entidades.Antecedentes;
import sur.softsurena.entidades.Categorias;
import sur.softsurena.entidades.Clientes;
import sur.softsurena.entidades.Control_Consulta;
import sur.softsurena.entidades.DetalleMotivoConsulta;
import sur.softsurena.entidades.Facturas;
import sur.softsurena.entidades.Motivo_Consulta;
import sur.softsurena.entidades.Perfiles;
import sur.softsurena.entidades.Producto;
import sur.softsurena.entidades.Resultados;

public class DeleteMetodos {

    private static final Logger LOG = Logger.getLogger(DeleteMetodos.class.getName());
    private static PreparedStatement ps;
    private static String sql;

    /**
     * 
     * @param idARS
     * @return 
     */
    public synchronized static String borrarSeguro(int idARS) {
        try {
            ps = getCnn().prepareStatement(ARS.DELETE);
            
            ps.setInt(1, idARS);
            
            int r = ps.executeUpdate();
            
            return "Borrado correctamente {"+r+"}";
        } catch (SQLException ex) {
            
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return "Error al borrar...";
        }
    }

    public synchronized static String borrarControlConsulta(int idControlConsulta) {
        try {
            ps = getCnn().prepareStatement(Control_Consulta.DELETE);
            ps.setInt(1, idControlConsulta);
            ps.executeUpdate();
            return "Borrado correctamente";
        } catch (SQLException ex) {
            
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return "Error al borrar control consulta";
        }
    }

    public synchronized static String borrarMotivoConsulta(Motivo_Consulta mc) {
        try {
            
            ps = getCnn().prepareStatement(Motivo_Consulta.DELETE);
            ps.setInt(1, mc.getId());
            
            int cantidad = ps.executeUpdate();
            
            return "Motivo de consulta borrado correctamente.";
        } catch (SQLException ex) {
            
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return "Error al borrar motivo de la consulta.";
        }
    }

    /**
     * Metodo que elimina un detalle de la consulta de los paciente, por x o y 
     * razones.
     * 
     * @param dmc
     * 
     * @return 
     */
    public synchronized String borrarDetalleMotivoConsulta(DetalleMotivoConsulta dmc) {
        try {
            ps = getCnn().prepareStatement(DetalleMotivoConsulta.DELETE);
            
            ps.setInt(1, dmc.getIdConsulta());
            ps.setInt(2, dmc.getTurno());
            ps.setInt(3, dmc.getIdMotivoConsulta());
            
            ps.executeUpdate();
            
            return "Motivo eliminado correctamente.";
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return "Error al eliminar detalle de motivo de la consulta.";
        }
    }

    public synchronized static Resultados borrarCliente(int idCliente, 
            boolean estado) {
        Resultados r;//Resultados obtenidos del metodos.
        try {

            ps = getCnn().prepareStatement(Clientes.DELETE);
            
            ps.setInt(1, idCliente);
            ps.setBoolean(2, estado);
            
            int c = ps.executeUpdate();//Cantidad de registros afectados.
            
            r = Resultados.builder().
                    id(-1).
                    mensaje("Cliente borrado correctamente.").
                    cantidad(c).build();
            
            return r;
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            r = Resultados.builder().
                    id(-1).
                    mensaje("Cliente no puede ser borrado").
                    cantidad(-1).build();
            return r;
        }
    }
    
    /**
     * Metodo utilizado para eliminar los productos del sistema, este solo
     * necesita de su ID para localizarlo en la tabla de PRODUCTOS.
     * 
     * @param id identificador del registro en la tabla de PRODUCTOS
     * 
     * @return Devuelve un mensaje que indica como resultado de la acción. 
     */
    public synchronized static String borrarProductoPorID(int id) {
        try {
            ps = getCnn().prepareStatement(Producto.DELETE);
            
            ps.setInt(1, id);
            
            ps.executeUpdate();
            
            return "Producto Borrado Correctamente.";
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return "Ocurrio un error al intentar borrar el Producto...";
        }
    }
    
    /**
     * Metodo utilizado para eliminar los productos del sistema, este solo
     * necesita de su CODIGO para localizarlo en la tabla de PRODUCTOS.
     * 
     * @param codigo codigo del Producto o codigo de barra del producto.
     * 
     * @return Devuelve un mensaje que indica como resultado de la acción. 
     */
    public synchronized static String borrarProductoPorCodigo(String codigo) {
        try {
            ps = getCnn().prepareStatement(Producto.DELETE_PRODUCTO_CODIGO);
            
            ps.setString(1, codigo);
            
            ps.executeUpdate();
            
            return "Producto Borrado Correctamente.";
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return "Ocurrio un error al intentar borrar el Producto...";
        }
    }
    
    public synchronized static String borrarAntecedente(int idAntecedente) {
        try {
            ps = getCnn().prepareStatement(Antecedentes.DELETE);
            
            ps.setInt(1, idAntecedente);
            
            ps.executeUpdate();
            
            return "Borrado o inactivo correctamente";
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return "Error al borrar paciente...";
        }
    }
    
    /**
     * 
     * @param idPerfil
     * @return 
     */
    public String borrarPerfil(int idPerfil) {
        try {
            ps = Conexion.getCnn().prepareStatement(Perfiles.DELETE);
            
            ps.setInt(1, idPerfil);
            
            ps.executeUpdate(sql);
            return "Perfil Borrado Correctamente";
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return "Ocurrio un error al intentar borrar el Perfil...";
        }
    }
    
    /**
     * Metodo para eliminar las categorias de la tablas V_CATEGORIAS. Para 
     * eliminar una categoria, ningun producto debe estar relacionado con esta 
     * categoria a eliminar.
     * 
     * Actualizado el 17/05/2022.
     * Actualizado el 05/06/2022. Nota: se le agrega la cantidad de registros 
     * afectos al mensaje. 
     * 
     * @param id Es el identificador del registro de la categorias.
     * @return Devuelve un mensaje de la acción realizada.
     */
    public static String borrarCategoria(int idCategoria) {
        try {
            ps = getCnn().prepareStatement(Categorias.DELETE);
            
            ps.setInt(1, idCategoria);
            
            int cant = ps.executeUpdate();
            
            return "Categoria Borrado Correctamente.";
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return "Ocurrio un error al intentar borrar la Categoria...";
        }
    }
    
    
    
    /**
     * Metodo que elimina las facturas del sistema por el identificador 
     * suministrado.
     * 
     * Nota: las facturas pueden eliminarse si el estado es nula. 
     * 
     * Actualizado el 17/05/2022.
     * 
     * @param id Es el identificador del registro de la factura.
     * @return Devuelve un mensaje de la acción
     */
    public synchronized static String borrarFactura(int id) {
        try {
            ps = getCnn().prepareStatement(Facturas.DELETE);
            
            ps.setInt(1, id);
            
            int r = ps.executeUpdate();
            return "Factura Borrada Correctamente. {"+r+"}";
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return "!Ocurrio un error al intentar borrar la Factura...!!!";
        }
    }
    
    
    
    
    
    
    
   
    
    
    
}
