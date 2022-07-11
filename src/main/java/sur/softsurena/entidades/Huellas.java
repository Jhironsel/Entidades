package sur.softsurena.entidades;

import java.io.File;
import javax.swing.ImageIcon;

public class Huellas {
    private final int id;
    private final String tipoDedo;
    private final File pathImagen;
    private final ImageIcon imagen;

    public Huellas(int id, String tipoDedo, File pathImagen, 
            ImageIcon imagen) {
        this.id = id;
        this.tipoDedo = tipoDedo;
        this.pathImagen = pathImagen;
        this.imagen = imagen;
    }

    public int getId() {
        return id;
    }

    public String getTipoDedo() {
        return tipoDedo;
    }

    public File getPathImagen() {
        return pathImagen;
    }

    public ImageIcon getImagen() {
        return imagen;
    }

    @Override
    public String toString() {
        return pathImagen.getPath();
    }
    
    
}
