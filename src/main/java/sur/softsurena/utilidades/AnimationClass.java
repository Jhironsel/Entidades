package sur.softsurena.utilidades;

import java.util.logging.Level;
import javax.swing.JLabel;
import javax.swing.JPanel;
import static sur.softsurena.utilidades.Utilidades.LOG;

public class AnimationClass {

    public static void jLabelMove(final String where, final int start,
            final int stop, final int delay, final int increment,
            final JLabel jLabel) {
        new Thread() {
            @Override
            public void run() {
                if ("Up".equals(where) || "Down".equals(where)) {
                    if ("Up".equals(where)) {
                        while (jLabel.getY() > stop) {
                            for (int i = start; i >= stop; i -= increment) {
                                dormir(delay);
                                jLabel.setLocation(jLabel.getX(), i);
                            }
                        }
                    }
                    if ("Down".equals(where)) {
                        while (jLabel.getY() <= start) {
                            for (int i = start; i <= stop; i += increment) {
                                dormir(delay);
                                jLabel.setLocation(jLabel.getX(), i);
                            }
                        }
                    }
                    jLabel.setLocation(jLabel.getX(), stop);
                }

                if ("Left".equals(where) || "Right".equals(where)) {
                    if ("Left".equals(where)) {
                        while (jLabel.getX() > stop) {
                            for (int i = start; i >= stop; i -= increment) {
                                dormir(delay);
                                jLabel.setLocation(i, jLabel.getY());
                            }
                        }
                    }
                    if ("Right".equals(where)) {
                        while (jLabel.getX() <= start) {
                            for (int i = start; i <= stop; i += increment) {
                                dormir(delay);
                                jLabel.setLocation(i, jLabel.getY());
                            }
                        }
                    }
                    jLabel.setLocation(stop, jLabel.getY());
                }
            }
        }.start();
    }

    public static void jPanelMove(final String where, final int start, final int stop,
            final int delay, final int increment, final JPanel jPanel) {
        new Thread() {
            @Override
            public void run() {
                if ("Right".equals(where) || "Left".equals(where)) {
                    if ("Right".equals(where)) {
                        for (int i = start; i <= stop; i += increment) {
                            dormir(delay);
                            jPanel.setLocation(i, jPanel.getY());
                        }
                    }
                    if ("Left".equals(where)) {
                        for (int i = start; i >= stop; i -= increment) {
                            dormir(delay);
                            jPanel.setLocation(i, jPanel.getY());
                        }
                    }
                }
                if ("Up".equals(where) || "Down".equals(where)) {
                    if ("Up".equals(where)) {
                        for (int i = start; i >= stop; i -= increment) {
                            dormir(delay);
                            jPanel.setLocation(jPanel.getX(), i);
                        }

                    }
                    if ("Down".equals(where)) {
                        for (int i = start; i <= stop; i += increment) {
                            dormir(delay);
                            jPanel.setLocation(jPanel.getX(), i);
                        }
                    }
                    jPanel.setLocation(jPanel.getX(), stop);
                }
            }
        }.start();
    }

    private static void dormir(int valor) {
        try {
            Thread.sleep(valor);
        } catch (InterruptedException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }
}
