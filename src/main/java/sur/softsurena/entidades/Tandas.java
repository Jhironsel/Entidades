package sur.softsurena.entidades;

import java.sql.Date;
import java.sql.Time;

public class Tandas {

    private final Integer id_tanda;
    private final Date anno_inicial;
    private final Date anno_final;
    private final Time hora_inicial;
    private final Time hora_final;
    private final Boolean lunes;
    private final Boolean martes;
    private final Boolean miercoles;
    private final Boolean jueves;
    private final Boolean viernes;
    private final Boolean sabados;
    private final Boolean domingos;
    private final int cantidad_estudiantes;
    private final int edad_minima;
    private final int edad_maxima;
    private final Boolean con_edad;
    private final Boolean estado;

    public Tandas(Integer id_tanda, Date anno_inicial, Date anno_final,
            Time hora_inicial, Time hora_final, Boolean lunes, Boolean martes,
            Boolean miercoles, Boolean jueves, Boolean viernes, Boolean sabados, 
            Boolean domingos, int cantidad_estudiantes, int edad_minima, 
            int edad_maxima, Boolean con_edad, Boolean estado) {
        this.id_tanda = id_tanda;
        this.anno_inicial = anno_inicial;
        this.anno_final = anno_final;
        this.hora_inicial = hora_inicial;
        this.hora_final = hora_final;
        this.lunes = lunes;
        this.martes = martes;
        this.miercoles = miercoles;
        this.jueves = jueves;
        this.viernes = viernes;
        this.sabados = sabados;
        this.domingos = domingos;
        this.cantidad_estudiantes = cantidad_estudiantes;
        this.edad_minima = edad_minima;
        this.edad_maxima = edad_maxima;
        this.con_edad = con_edad;
        this.estado = estado;
    }

    public Integer getId_tanda() {
        return id_tanda;
    }

    public Date getAnno_inicial() {
        return anno_inicial;
    }

    public Date getAnno_final() {
        return anno_final;
    }

    public Time getHora_inicial() {
        return hora_inicial;
    }
    
    public Time getHora_final() {
        return hora_final;
    }

    public Boolean getLunes() {
        return lunes;
    }

    public Boolean getMartes() {
        return martes;
    }
    
    public Boolean getMiercoles() {
        return miercoles;
    }

    public Boolean getJueves() {
        return jueves;
    }

    public Boolean getViernes() {
        return viernes;
    }

    public Boolean getSabados() {
        return sabados;
    }

    public Boolean getDomingos() {
        return domingos;
    }
    
    public int getCantidad_estudiantes() {
        return cantidad_estudiantes;
    }
    
    public int getEdad_minima() {
        return edad_minima;
    }

    public int getEdad_maxima() {
        return edad_maxima;
    }

    public Boolean getCon_edad() {
        return con_edad;
    }
    
    public Boolean getEstado(){
        return estado;
    }

    @Override
    public String toString() {
        return "Tandas{" + 
                "id_tanda=" + id_tanda + 
                ", anno_inicial=" + anno_inicial + 
                ", anno_final=" + anno_final + 
                ", hora_inicial=" + hora_inicial + 
                ", hora_final=" + hora_final + 
                ", lunes=" + lunes + 
                ", martes=" + martes + 
                ", miercoles=" + miercoles + 
                ", jueves=" + jueves + 
                ", viernes=" + viernes + 
                ", sabados=" + sabados + 
                ", domingos=" + domingos + 
                ", cantidad_estudiantes=" + cantidad_estudiantes + 
                ", edad_minima=" + edad_minima + 
                ", edad_maxima=" + edad_maxima + 
                ", con_edad=" + con_edad + 
                '}';
    }

    
}
