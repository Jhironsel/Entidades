package sur.softsurena.interfaces;


public interface ICliente {
    /**
     * Es utilizada para definir los cabezales de las tablas de direcciones.
     */
    final String[] TITULOS_DIRECCION = {"Provincia", "Municipio", "Distrito M.", 
        "Calle y No. Casa", "Fecha", "Estado", "Por defecto"};
    
    /**
     * Es utilizada para definir los cabezales de las tablas de correo.
     */
    final String[] TITULOS_CORREO = {"Correo", "Fecha", "Estado", "Por defecto"};
    
    /**
     * Es utilizada para definir los cabezales de las tablas de telefono.
     */
    final String[] TITULOS_TELEFONO = {"Numero", "Tipo", "Fecha", "Estado", "Por defecto"};
    
    
    final String[] TITULOS_CLIENTE  = {"Cedulas", "Persona", "Primer Nombre",
            "Segundo Nombre", "Apellidos", "Sexo", "Fecha nacimiento",
            "Fecha Ingreso", "Estado"
        };
    
}
