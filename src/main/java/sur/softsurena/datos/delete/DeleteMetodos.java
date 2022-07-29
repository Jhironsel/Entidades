package sur.softsurena.datos.delete;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import sur.softsurena.conexion.Conexion;
import static sur.softsurena.conexion.Conexion.getCnn;
import sur.softsurena.entidades.ARS;
import sur.softsurena.entidades.Antecedente;
import sur.softsurena.entidades.Categoria;
import sur.softsurena.entidades.Cliente;
import sur.softsurena.entidades.Control_Consulta;
import sur.softsurena.entidades.DetalleMotivoConsulta;
import sur.softsurena.entidades.Factura;
import sur.softsurena.entidades.Motivo_Consulta;
import sur.softsurena.entidades.Perfiles;
import sur.softsurena.entidades.Producto;

public class DeleteMetodos {

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
            
            //Instalar Logger
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
            
            //Instalar Logger
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
            
            //Instalar Logger
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
            //Instalar Logger
            return "Error al eliminar detalle de motivo de la consulta.";
        }
    }

    public synchronized static String borrarCliente(int idCliente) {
        try {

            ps = getCnn().prepareStatement(Cliente.DELETE);
            
            ps.setInt(1, idCliente);
            
            int r = ps.executeUpdate();
            return "Cliente borrado correctamente. {"+r+"}.";
        } catch (SQLException ex) {
            //Instalar Logger
            return "Cliente no puede ser borrado";
        }
    }
    
    
    

    /**
     * Metodo utilizado para eliminar los productos del sistema, este solo
     * necesita de su id para localizarlo en la tabla de PRODUCTOS
     * @param id identificador del registro en la tabla de PRODUCTOS
     * @return Devuelve un mensaje que indica como resultado de la acción. 
     */
    public synchronized static String borrarProducto(int id) {
        try {
            ps = getCnn().prepareStatement(Producto.DELETE_PRODUCTO);
            
            ps.setInt(1, id);
            
            ps.executeUpdate();
            
            return "Producto Borrado Correctamente.";
        } catch (SQLException ex) {
            //Instalar Logger
            return "Ocurrio un error al intentar borrar el Producto...";
        }
    }
    
    public synchronized static String borrarAntecedente(int idAntecedente) {
        try {
            ps = getCnn().prepareStatement(Antecedente.DELETE);
            
            ps.setInt(1, idAntecedente);
            
            ps.executeUpdate();
            
            return "Borrado o inactivo correctamente";
        } catch (SQLException ex) {
            //Instalar Logger
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
            //Instalar Logger
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
            ps = getCnn().prepareStatement(Categoria.DELETE_CATEGORIA);
            
            ps.setInt(1, idCategoria);
            
            int cant = ps.executeUpdate();
            
            return "Categoria Borrado Correctamente. {"+cant+"}";
        } catch (SQLException ex) {
            //Instalar Logger
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
            ps = getCnn().prepareStatement(Factura.DELETE);
            
            ps.setInt(1, id);
            
            int r = ps.executeUpdate();
            return "Factura Borrada Correctamente. {"+r+"}";
        } catch (SQLException ex) {
            //Instalar Logger
            return "!Ocurrio un error al intentar borrar la Factura...!!!";
        }
    }
    
    
    
    
    
    
    
   
    
    
    
}
