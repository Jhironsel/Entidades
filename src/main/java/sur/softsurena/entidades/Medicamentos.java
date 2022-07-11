package sur.softsurena.entidades;

import java.io.File;
import javax.swing.ImageIcon;

public class Medicamentos {
    private final int id;
    private final int id_proveedor;
    private final String descripcion;
    private final File pathImagen;
    private final ImageIcon imagen;
    private final boolean estado;
    private final String usuario;

    public Medicamentos(int id, int id_proveedor, String descripcion, 
            File pathImagen, ImageIcon imagen, boolean estado, 
            String usuario) {
        this.id = id;
        this.id_proveedor = id_proveedor;
        this.descripcion = descripcion;
        this.pathImagen = pathImagen;
        this.imagen = imagen;
        this.estado = estado;
        this.usuario = usuario;
    }

    public int getId() {
        return id;
    }

    public int getId_proveedor() {
        return id_proveedor;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public File getPathImagen() {
        return pathImagen;
    }

    public ImageIcon getImagen() {
        return imagen;
    }

    public boolean isEstado() {
        return estado;
    }

    public String getUsuario() {
        return usuario;
    }

    
    
    
}
