package sur.softsurena.datos.delete;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import sur.softsurena.conexion.Conexion;
import static sur.softsurena.conexion.Conexion.getCnn;
import sur.softsurena.entidades.Categoria;
import sur.softsurena.entidades.DetalleMotivoConsulta;

public class DeleteMetodos {

    private static PreparedStatement ps;
    private static String sql;

    /**
     * 
     * @param idARS
     * @return 
     */
    public synchronized static String borrarSeguro(int idARS) {
        sql = "DELETE FROM V_ARS WHERE id = ?";
        try {
            ps = getCnn().prepareStatement(sql);
            
            ps.setInt(1, idARS);
            
            int r = ps.executeUpdate();
            
            return "Borrado correctamente {"+r+"}";
        } catch (SQLException ex) {
            
            //Instalar Logger
            return "Error al borrar...";
        }
    }

    public synchronized static String borrarControlConsulta(int idControlConsulta) {
        sql = "DELETE FROM V_CONTROLCONSULTA a WHERE a.idcontrolconsulta = ?";
        try {
            ps = getCnn().prepareStatement(sql);
            ps.setInt(1, idControlConsulta);
            ps.executeUpdate();
            return "Borrado correctamente";
        } catch (SQLException ex) {
            
            //Instalar Logger
            return "Error al borrar control consulta";
        }
    }

    public synchronized static String borrarMotivoConsulta(DetalleMotivoConsulta dmc) {
        sql = "DELETE FROM V_Motivos_Consulta WHERE IDMConsulta = ?";
        try {
            
            ps = getCnn().prepareStatement(sql);
            
//            ps.setInt(1, idMConsulta);
            
            int cantidad = ps.executeUpdate();
            
            return "Borrado o inactivo correctamente { " + cantidad + " }";
        } catch (SQLException ex) {
            
            //Instalar Logger
            return "Error al borrar motivo de la consulta...";
        }
    }

    public synchronized String borrarMotivoConsulta(int idConsulta, int turno,
            String idMConsulta) {
        sql = "DELETE FROM V_DETALLEMOTIVOCONSULTA d "
                + "WHERE d.IDCONSULTA = ? and d.TURNO = ? and d.IDMCONSULTA = ? ;";
        try {
            ps = getCnn().prepareStatement(sql);
            ps.setInt(1, idConsulta);
            ps.setInt(2, turno);
            ps.setString(3, idMConsulta);
            ps.executeUpdate();
            return "Motivo eliminado correctamente.";
        } catch (SQLException ex) {
            
            //Instalar Logger
            return "Error al eliminar motivo.";
        }
    }

    public synchronized static String borrarCliente(int idCliente) {
        try {
            sql = "DELETE FROM V_CLIENTES WHERE ID = ?";

            ps = getCnn().prepareStatement(sql);
            
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
     * necesita de su id para localizarlo en la tabla de V_PRODUCTOS
     * @param id identificador del registro en la tabla de V_PRODUCTOS
     * @return Devuelve un mensaje que indica como resultado de la acción. 
     */
    public synchronized static String borrarProducto(int id) {
        try {
            ps = getCnn().prepareStatement("delete from V_PRODUCTOS where id = ? ");
            
            ps.setInt(1, id);
            
            int r = ps.executeUpdate();
            
            return "Producto Borrado Correctamente. {"+r+"}";
        } catch (SQLException ex) {
            //Instalar Logger
            return "Ocurrio un error al intentar borrar el Producto...";
        }
    }
    public synchronized static String borrarProducto(String idProducto) {
        try {
            PreparedStatement st = getCnn().prepareStatement(
                    "delete from TABLA_PRODUCTOS "
                    + "where idproducto = ? "
            );
            st.setString(1, idProducto);
            st.executeUpdate();
            return "Producto Borrado Correctamente";
        } catch (SQLException ex) {
            //Instalar Logger
            return "Ocurrio un error al intentar borrar el Producto...";
        }
    }
    
    public synchronized static String borrarAntecedente(int idAntecedente) {
        sql = "delete from V_ANTECEDENTES WHERE IDANTECEDENTE = ?";
        try {
            ps = getCnn().prepareStatement(sql);
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
            sql = "delete from T_Perfil where idPerfil = ?";
            ps = Conexion.getCnn().prepareStatement(sql);
            
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
     * Actualizado el 17/05/2022.
     * 
     * @param id Es el identificador del registro de la factura.
     * @return Devuelve un mensaje de la acción
     */
    public synchronized static String borrarFactura(int id) {
        sql = "delete from V_FACTURAS where id = ?";
        try {
            ps = getCnn().prepareStatement(sql);
            
            ps.setInt(1, id);
            
            int r = ps.executeUpdate();
            return "Factura Borrada Correctamente. {"+r+"}";
        } catch (SQLException ex) {
            //Instalar Logger
            return "!Ocurrio un error al intentar borrar la Factura...!!!";
        }
    }
    
    
    
    
    
    
    
   
    
    
    
}
