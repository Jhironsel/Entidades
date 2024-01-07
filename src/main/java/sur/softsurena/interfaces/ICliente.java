package sur.softsurena.interfaces;

public interface ICliente {
    /**
     * Es utilizada para definir los cabezales de las tablas de direcciones.
     */
    String[] TITULOS_DIRECCION = {"Provincia", "Municipio", "Distrito M.", 
        "Calle y No. Casa", "Fecha", "Estado", "Por defecto"};
    
    /**
     * Es utilizada para definir los cabezales de las tablas de correo.
     */
    String[] TITULOS_CORREO = {"Correo", "Fecha"};
    
    /**
     * Es utilizada para definir los cabezales de las tablas de telefono.
     */
    String[] TITULOS_TELEFONO = {"Numero", "Tipo", "Fecha"};
    
}
