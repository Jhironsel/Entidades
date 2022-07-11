package sur.softsurena.entidades;
public class IdCedula {
    private int id;
    private String cedula;

    public IdCedula(int id, String cedula) {
        this.id = id;
        this.cedula = cedula;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }    

    @Override
    public String toString() {
        return cedula;
    }
    
    
}
