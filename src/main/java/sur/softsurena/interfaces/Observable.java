package sur.softsurena.interfaces;

/**
 * Esta interface contienen los metodos necesario para administrar los observer.
 * 
 * @author jhironsel
 */
public interface Observable {
    void addObserver(Observer observer);
    
    void deleteObserver(Observer observer);
    
    void notifyObservers();
}
