package sur.softsurena.entidades;

import java.util.*;
import java.text.SimpleDateFormat;

public class FechaHora {

    private final Date curDate;
    private final SimpleDateFormat dateFormat;
    private SimpleDateFormat dateFormat1;

    public FechaHora() {
        GregorianCalendar currentDate = new GregorianCalendar();
        curDate = (Date) currentDate.getTime();
        dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        dateFormat1 = new SimpleDateFormat("HH:mm:ss");
    }
    
    public FechaHora(Date fecha){        
        curDate = fecha;
        dateFormat = new SimpleDateFormat("dd.MM.yyyy");
    }
    
    public String getFecha() {
        return dateFormat.format(curDate);
    }

    public String getHora() {
        return dateFormat1.format(curDate);
    }
    
    
}
