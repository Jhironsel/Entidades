package sur.softsurena.implementaciones;

import sur.softsurena.interfaces.Observer;

/**
 * Como usar este patron de diseño. 
 * 
 * Se crea un objecto de la clase EjemploObservable la cual es la que se encarga 
 * de administrar las clases observadas.
 * 
 * @author jhironsel
 */
public class Main {
    public static void main(String[] args) {
        Observer obj1 = new EjemploObserver1();
        Observer obj2 = new EjemploObserver2();
        Observer obj3 = new EjemploObserver3();
        EjemploObservable observable = new EjemploObservable();
        
        observable.addObserver(obj1);
        observable.addObserver(obj2);
        observable.notifyObservers();
        
        observable.addObserver(obj3);        
        observable.notifyObservers();
        
        observable.deleteObserver(obj2);
        observable.notifyObservers();
    }
}
