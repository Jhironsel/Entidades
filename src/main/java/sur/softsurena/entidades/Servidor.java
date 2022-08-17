package sur.softsurena.entidades;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public class Servidor {
    private final Boolean conServidor;
    private final String uriServidor;
    
    private final Boolean conPuerto;
    private final String puerto;
    
    private final Boolean conIpServidor;
    private final String ipServidor1;
    private final String ipServidor2;
    private final String ipServidor3;
    private final String ipServidor4;
    
    private final String pathBaseDatos;
}

