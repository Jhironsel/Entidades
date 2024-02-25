package sur.softsurena.entidades;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public class D_MotivoConsulta {

    private final int idConsulta;
    private final int turno;
    private final int idMotivoConsulta;

    @Override
    public String toString() {
        return "D_MotivoConsulta{" + 
                    "idConsulta=" + idConsulta + 
                    ", turno=" + turno + 
                    ", idMotivoConsulta=" + idMotivoConsulta + 
                '}';
    }
    
    
}
