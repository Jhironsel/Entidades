package sur.softsurena.entidades;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class AnimationClass {

    public void jLabelMove(final String where, final int start,
            final int stop, final int delay, final int increment,
            final JLabel jLabel) {
        new Thread() {
            @Override
            public void run() {
                if ("Up".equals(where) || "Down".equals(where)) {
                    if ("Up".equals(where)) {
                        while (jLabel.getY() > stop) {
                            for (int i = start; i >= stop; i -= increment) {
                                try {
                                    Thread.sleep(delay);
                                    jLabel.setLocation(jLabel.getX(), i);
                                } catch (InterruptedException e) {
                                    //Instalar Logger
                                }
                            }
                        }
                    }
                    if ("Down".equals(where)) {
                        while (jLabel.getY() <= start) {
                            for (int i = start; i <= stop; i += increment) {
                                try {
                                    Thread.sleep(delay);
                                    jLabel.setLocation(jLabel.getX(), i);
                                } catch (InterruptedException e) {
                                    //Instalar Logger
                                }
                            }
                        }
                    }
                    jLabel.setLocation(jLabel.getX(), stop);
                }

                if ("Left".equals(where) || "Right".equals(where)) {
                    if ("Left".equals(where)) {
                        while (jLabel.getX() > stop) {
                            for (int i = start; i >= stop; i -= increment) {
                                try {
                                    Thread.sleep(delay);
                                    jLabel.setLocation(i, jLabel.getY());
                                } catch (InterruptedException e) {
                                    //Instalar Logger
                                }
                            }
                        }
                    }
                    if ("Right".equals(where)) {
                        while (jLabel.getX() <= start) {
                            for (int i = start; i <= stop; i += increment) {
                                try {
                                    Thread.sleep(delay);
                                    jLabel.setLocation(i, jLabel.getY());
                                } catch (InterruptedException e) {
                                    //Instalar Logger
                                }
                            }
                        }
                    }
                    jLabel.setLocation(stop, jLabel.getY());
                }
            }
        }.start();
    }

    public void jPanelMove(final String where, final int start, final int stop,
            final int delay, final int increment, final JPanel jPanel) {
        new Thread() {
            public void run() {
                if ("Right".equals(where) || "Left".equals(where)) {
                    if ("Right".equals(where)) {
                        while (jPanel.getX() <= start) {
                            for (int i = start; i <= stop; i += increment) {
                                try {
                                    Thread.sleep(delay);

                                    jPanel.setLocation(i, jPanel.getY());
                                } catch (InterruptedException e) {
                                    //Instalar Logger
                                }
                            }
                        }
                    }
                    if ("Left".equals(where)) {
                        while (jPanel.getX() > stop) {
                            for (int i = start; i >= stop; i -= increment) {
                                try {
                                    Thread.sleep(delay);
                                    jPanel.setLocation(i, jPanel.getY());
                                } catch (InterruptedException e) {
                                    //Instalar Logger
                                }
                            }
                        }
                    }
                    jPanel.setLocation(stop, jPanel.getY());
                }
                if ("Up".equals(where) || "Down".equals(where)) {
                    if ("Up".equals(where)) {
                        while (jPanel.getY() > stop) {
                            for (int i = start; i >= stop; i -= increment) {
                                try {
                                    Thread.sleep(delay);
                                    jPanel.setLocation(jPanel.getX(), i);
                                } catch (InterruptedException e) {
                                    //Instalar Logger
                                }
                            }
                        }

                    }
                    if ("Down".equals(where)) {
                        while (jPanel.getY() <= start) {
                            for (int i = start; i <= stop; i += increment) {
                                try {
                                    Thread.sleep(delay);

                                    jPanel.setLocation(jPanel.getX(), i);
                                } catch (InterruptedException e) {
                                    //Instalar Logger
                                }
                            }
                        }
                    }
                    jPanel.setLocation(jPanel.getX(), stop);
                }
            }
        }.start();
    }
}
