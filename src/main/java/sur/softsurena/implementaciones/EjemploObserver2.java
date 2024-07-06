package sur.softsurena.implementaciones;

import sur.softsurena.interfaces.Observer;

/**
 * Estas clases implementan los Observer para definir el metodo update().
 * 
 * @author jhironsel
 */
public class EjemploObserver2 implements Observer {

    @Override
    public void update() {
        System.out.println("sur.softsurena.implementaciones.EjemploObserver2.update()");
    }
    
}
