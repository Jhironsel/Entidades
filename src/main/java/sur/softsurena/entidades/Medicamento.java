package sur.softsurena.entidades;

import java.io.File;
import javax.swing.ImageIcon;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public class Medicamento {
    private final int id;
    private final int id_proveedor;
    private final String descripcion;
    private final File pathImagen;
    private final ImageIcon imagen;
    private final boolean estado;
    private final String usuario;    
}
