package sur.softsurena.implementaciones;

import java.util.HashSet;
import java.util.Set;
import sur.softsurena.interfaces.Observable;
import sur.softsurena.interfaces.Observer;

/**
 * Implementando los metodos de la clase Observable en esta clase.
 * 
 * Se utiliza en objectos Set para almacenar los Observer. 
 * 
 * @author jhironsel
 */
public class EjemploObservable implements Observable{

    private final Set<Observer> observerSet = new HashSet<>();
    
    @Override
    public void addObserver(Observer observer) {
        this.observerSet.add(observer);
    }

    @Override
    public void deleteObserver(Observer observer) {
        this.observerSet.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observerSet) {
            observer.update();
        }
    }
    
}
