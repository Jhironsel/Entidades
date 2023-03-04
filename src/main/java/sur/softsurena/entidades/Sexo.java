package sur.softsurena.entidades;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public class Sexo {
    private final char abreviatura;
    private final String sexo;
    
    public static List<Sexo> getSexoList(){
        List<Sexo> sexoList = new ArrayList<>();
        sexoList.add( Sexo.builder().abreviatura('X').sexo("Seleccione sexo").build() );
        sexoList.add( Sexo.builder().abreviatura('F').sexo("FEMENINA").build());
        sexoList.add( Sexo.builder().abreviatura('M').sexo("MASCULINO").build() );
        return sexoList;
    }

    @Override
    public String toString() {
        return sexo;
    }
}
