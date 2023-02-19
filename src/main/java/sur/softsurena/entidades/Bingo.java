package sur.softsurena.entidades;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import static sur.softsurena.conexion.Conexion.getCnn;
import sur.softsurena.formularios.frmLogin;

@SuperBuilder
@Getter
public class Bingo {

    private static final Logger LOG = Logger.getLogger(Bingo.class.getName());
    
    private final int id;
    private final int carton_hash;
    private final Date fecha_creacion;
    private final List<Integer> matriz_obj;
    private final Boolean estado;
    
    private int[][] card;
    
    private ArrayList<Integer> numeros;

    
    public boolean checkBingo() {
        boolean bingo = false;
        boolean diag1Complete = true;
        boolean diag2Complete = true;
        // Recorre la matriz buscando una línea completa de números marcados
        for (int i = 0; i < card.length; i++) {
            boolean rowComplete = true;
            boolean colComplete = true;
            for (int j = 0; j < card[i].length; j++) {
                if (card[i][j] == 0) {
                    rowComplete = false;
                }
                if (card[j][i] == 0) {
                    colComplete = false;
                }
                if (i == j && card[i][j] == 0) {
                    diag1Complete = false;
                }
                if (i + j == card.length - 1 && card[i][j] == 0) {
                    diag2Complete = false;
                }
            }
            if (rowComplete || colComplete) {
                bingo = true;
                break;
            }
        }
        if (diag1Complete || diag2Complete) {
            bingo = true;
        }
        return bingo;
    }
    
    public static void main(String[] args) {
        frmLogin login = new frmLogin();
        
        login.setLocationRelativeTo(null);
        login.setVisible(true);
        
        if(Bingo.generarCarton(300)){
            JOptionPane.showMessageDialog(null, "Trabajo realiado");
        }
    }
    
    private static Boolean generarCarton(int cantidad){
        final String sql = "EXECUTE PROCEDURE SP_INSERT_CARTON_BINGO (?, ?);";
        
        try {
            getCnn().setAutoCommit(false);
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, 
                    "Error poner metodo setAutoCommit en false", ex);
        }
        
        try (PreparedStatement ps = getCnn().prepareStatement(sql)) {
            
            
            for (int i = 0; i < cantidad; i++) {
                List<Integer> lista = generarCarton();
                
                ps.setInt(1, lista.hashCode());
                ps.setObject(2, lista);
                
            }
            
            ps.addBatch();

            ps.executeBatch();

            return true;
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return false;
        }
    }

    public static List<Integer> generarCarton() {
        List<Integer> numbers = new ArrayList<Integer>();

        Random rand = new Random();
        
        Set<Integer> b = new HashSet<>();
        
        while (b.size() < 5) {
            b.add(rand.nextInt(14) + 1);
        }
                
        Set<Integer> i = new HashSet<>();
        
        while (i.size() < 5) {
            i.add(rand.nextInt(14) + 16);
        }
        
        Set<Integer> n = new HashSet<>();
        
        while (n.size() < 5) {
            n.add(rand.nextInt(14) + 31);
        }
        
        Set<Integer> g = new HashSet<>();
        
        while (g.size() < 5) {
            g.add(rand.nextInt(14) + 46);
        }
        
        Set<Integer> o = new HashSet<>();
        
        while (o.size() < 5) {
            o.add(rand.nextInt(14) + 61);
        }
        
        numbers.addAll(b);
        numbers.addAll(i);
        numbers.addAll(n);
        numbers.addAll(g);
        numbers.addAll(o);
        
        return numbers;
    }
}