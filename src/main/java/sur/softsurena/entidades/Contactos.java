package sur.softsurena.entidades;

public class Contactos {
    private final int id;
    private final int id_persona;
    private final String email;
    private final String telefono;

    public Contactos(int id, int id_persona, String email, String telefono) {
        this.id = id;
        this.id_persona = id_persona;
        this.email = email;
        this.telefono = telefono;
    }

    public int getId() {
        return id;
    }

    public int getId_persona() {
        return id_persona;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefono() {
        return telefono;
    }


    @Override
    public String toString() {
        return "Contactos{" + 
                "id=" + id + 
                ", id_persona=" + id_persona + 
                ", email=" + email + 
                ", telefono=" + telefono + 
                '}';
    }
    
    
}
