package sur.softsurena.entidades;

public class Antecedente {
    private int idAntecedente;
    private int idPaciente;
    private String descripcion;

    public Antecedente(int idAntecedente, int idPaciente, String descripcion) {
        this.idAntecedente = idAntecedente;
        this.idPaciente = idPaciente;
        this.descripcion = descripcion;
    }

    public Antecedente(int idAntecedente, String descripcion) {
        this.idAntecedente = idAntecedente;
        this.descripcion = descripcion;
    }
    
    

    public int getIdAntecedente() {
        return idAntecedente;
    }

    public void setIdAntecedente(int idAntecedente) {
        this.idAntecedente = idAntecedente;
    }

    public int getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(int idPaciente) {
        this.idPaciente = idPaciente;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return descripcion.trim();
    }
    
    
}
