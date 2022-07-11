package sur.softsurena.entidades;

import java.sql.Timestamp;

public class D_guia_vigilancia {
    private final int id;
    private final int id_paciente;
    private final Timestamp fecha;

    public D_guia_vigilancia(int id, int id_paciente, Timestamp fecha) {
        this.id = id;
        this.id_paciente = id_paciente;
        this.fecha = fecha;
    }

    public int getId() {
        return id;
    }

    public int getId_paciente() {
        return id_paciente;
    }

    public Timestamp getFecha() {
        return fecha;
    }

    @Override
    public String toString() {
        return "D_guia_vigilancia{" + 
                " id=" + id + 
                ", id_paciente=" + id_paciente + 
                ", fecha=" + fecha + 
                '}';
    }
    
    
}
