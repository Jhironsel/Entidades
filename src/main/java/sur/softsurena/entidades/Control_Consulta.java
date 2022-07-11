package sur.softsurena.entidades;

import java.sql.Time;

public class Control_Consulta {
    private final int id;
    private final String user_name;
    private final int cantidad;
    private final String dia;
    private final Time inicial;
    private final Time finall;
    private final Boolean estado;

    public Control_Consulta(int id, String idUsuario, int cantidad, String dia,
            Time inicial, Time finall, Boolean estado) {
        this.id = id;
        this.user_name = idUsuario;
        this.cantidad = cantidad;
        this.dia = dia;
        this.inicial = inicial;
        this.finall = finall;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public String getUser_Name() {
        return user_name;
    }

    public int getCantidad() {
        return cantidad;
    }

    public String getDia() {
        return dia;
    }

    public Time getInicial() {
        return inicial;
    }

    public Time getFinall() {
        return finall;
    }

    public Boolean isEstado() {
        return estado;
    }

    @Override
    public String toString() {
        return user_name;
    }
}
