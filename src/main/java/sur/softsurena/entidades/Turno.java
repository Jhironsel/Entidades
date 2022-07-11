package sur.softsurena.entidades;

import java.sql.Date;
import java.sql.Time;

public class Turno {

    private static int id;
    private static Date fecha_inicio;
    private static Time hora_inicio;
    private static Date fecha_final;
    private static Time hora_final;
    private static String idUsuario;
    private static Boolean estado;

    public Turno(Builder b) {
        Turno.id = b.id;
        Turno.fecha_inicio = b.fecha_inicio;
        Turno.hora_inicio = b.hora_inicio;
        Turno.fecha_final = b.fecha_final;
        Turno.hora_final = b.hora_final;
        Turno.idUsuario = b.idUsuario;
        Turno.estado = b.estado;
    }

    public static class Builder {
        private int id;
        private Date fecha_inicio;
        private Time hora_inicio;
        private Date fecha_final;
        private Time hora_final;
        private String idUsuario;
        private Boolean estado;
        
        public Builder id(int id){
            this.id = id;
            return this;
        }
        
        public Builder fecha_inicio(Date fecha_inicio){
            this.fecha_inicio = fecha_inicio;
            return this;
        }
        
        public Builder hora_inicio(Time hora_inicio){
            this.hora_inicio = hora_inicio;
            return this;
        }
        
        public Builder fecha_final(Date fecha_final){
            this.fecha_final = fecha_final;
            return this;
        }
        
        public Builder hora_final(Time hora_final){
            this.hora_final = hora_final;
            return this;
        }
        
        public Builder idUsuario(String idUsuario){
            this.idUsuario = idUsuario;
            return this;
        }
        
        public Builder estado(Boolean estado){
            this.estado = estado;
            return this;
        }
        
        public Turno build() {
            return new Turno(this);
        }
    }

}
