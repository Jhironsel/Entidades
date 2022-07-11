package sur.softsurena.entidades;

import java.sql.Timestamp;

public class Recetas {
    private final int id;
    private final int idConsulta;
    private final Timestamp fecha;
    private final String usuario;

    public Recetas(int id, int idConsulta, Timestamp fecha, String usuario) {
        this.id = id;
        this.idConsulta = idConsulta;
        this.fecha = fecha;
        this.usuario = usuario;
    }

    public int getId() {
        return id;
    }

    public int getIdConsulta() {
        return idConsulta;
    }

    public Timestamp getFecha() {
        return fecha;
    }

    public String getUsuario() {
        return usuario;
    }

    @Override
    public String toString() {
        return "Recetas{" + "id=" + id + ", idConsulta=" + idConsulta + ", fecha=" + fecha + ", usuario=" + usuario + '}';
    }
    
    
}
