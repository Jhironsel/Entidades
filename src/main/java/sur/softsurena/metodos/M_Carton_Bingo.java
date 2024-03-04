package sur.softsurena.metodos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.logging.Level;
import static sur.softsurena.conexion.Conexion.getCnn;
import static sur.softsurena.utilidades.Utilidades.LOG;

/**
 *
 * @author jhironsel
 */
public class M_Carton_Bingo {

    private static int[][] card;

    private ArrayList<Integer> numeros;

    public static synchronized boolean checkBingo() {
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

    public static Boolean generarCarton(int cantidad) {
        final String sql = "EXECUTE PROCEDURE SP_INSERT_CARTON_BINGO (?, ?);";

        try (PreparedStatement ps = getCnn().prepareStatement(
                sql, 
                ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.CLOSE_CURSORS_AT_COMMIT
        )) {
            getCnn().setAutoCommit(false);
            for (int i = 0; i < cantidad; i++) {
                List<Integer> lista = generarCarton();

                ps.setInt(1, lista.hashCode());
                ps.setObject(2, lista);

            }

            ps.addBatch();

            ps.executeBatch();

            getCnn().setAutoCommit(true);

            return true;
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return false;
        }
    }
}
