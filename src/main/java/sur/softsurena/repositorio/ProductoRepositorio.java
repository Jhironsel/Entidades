package sur.softsurena.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import sur.softsurena.entidades.Producto;

/**
 *
 * @author jhironsel
 */
public interface ProductoRepositorio extends JpaRepository<Producto, Integer> {
    
}
