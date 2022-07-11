package sur.softsurena.entidades;

import javax.swing.ImageIcon;


public class Imagen {

    private final ImageIcon imagen;
    private final String nombre;

    public Imagen(ImageIcon imagen, String nombre) {
        this.imagen = imagen;
        this.nombre = nombre;
    }

    public ImageIcon getImagen() {
        return imagen;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return nombre;
    }
    
    
}
