package sur.softsurena.entidades;

public class DetalleMotivoConsulta {
    private final int id;
    private final int turno;
    private final int idMotivoConsulta;

    public DetalleMotivoConsulta(int id, int turno, int idMotivoConsulta) {
        this.id = id;
        this.turno = turno;
        this.idMotivoConsulta = idMotivoConsulta;
    }

    public int getId() {
        return id;
    }

    public int getTurno() {
        return turno;
    }

    public int getIdMotivoConsulta() {
        return idMotivoConsulta;
    }

    @Override
    public String toString() {
        return "DetalleMotivoConsulta{" + 
                "id=" + id + 
                ", turno=" + turno + 
                ", idMotivoConsulta=" + idMotivoConsulta + '}';
    }

    
}
