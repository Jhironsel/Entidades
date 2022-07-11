package sur.softsurena.seguridadPatron;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
public class UnlockPanel extends JPanel implements MouseListener, MouseMotionListener {        
    private JFrame parent;
    private String myKey="125895";//patron de desbloqueo
    //array para las 9 figuras que forman la matriz
    private ArrayList<FigurePattern> figurePatternArr = new ArrayList();
    //array para las figuras que conforman el patron desbloqueo
    private ArrayList<FigurePattern> pattern= new ArrayList();
    //String para guardar el patron generado por el usuario
    private String password = "";
    //para mostrar imagen de "acceso denegado"
    private final Image image = new ImageIcon("src/images/access_denied.png").getImage();
    private boolean showError=false;

    public String getMyKey() {
        return myKey;
    }

    public void setMyKey(String myKey) {
        this.myKey = myKey;
    }
    
    public UnlockPanel(JFrame parent){                
        this.parent = parent;
        //se agregan 9 circulos al contenedor
        figurePatternArr.add(new FigurePattern(1,0,0));
        figurePatternArr.add(new FigurePattern(2,80,0));
        figurePatternArr.add(new FigurePattern(3,160,0));
        
        figurePatternArr.add(new FigurePattern(4,0,80));
        figurePatternArr.add(new FigurePattern(5,80,80));
        figurePatternArr.add(new FigurePattern(6,160,80));
        
        figurePatternArr.add(new FigurePattern(7,0,160));
        figurePatternArr.add(new FigurePattern(8,80,160));
        figurePatternArr.add(new FigurePattern(9,160,160));        
        //Eventos del raton        
        UnlockPanel.this.addMouseListener( UnlockPanel.this );        
        UnlockPanel.this.addMouseMotionListener( UnlockPanel.this);        
    }
    
   @Override
    public void paintComponent(Graphics g){    
        Graphics2D g2 =(Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);            
        g2.setColor( new Color(0,0,0) );
        g2.fill(new Rectangle2D.Double(0,0,getWidth(),getHeight()));        
        //dibuja las lineas del patron de desbloqueo entre los circulos
        Point point = null;
        for(FigurePattern figurePattern: pattern){
            g2.setStroke( new BasicStroke( 20 ) );
            g2.setColor( new Color(204,204,204) );
            if(point!=null)
                g2.drawLine(point.x, point.y, figurePattern.getCenterPoint().x, figurePattern.getCenterPoint().y);  
             point = figurePattern.getCenterPoint();            
        }        
        //dibuja los circulos para los patrones de desbloqueo       
        if(figurePatternArr!=null)
//            figurePatternArr.stream().forEach((b) -> {
//                b.draw(g2);
//        });        
        //muestra error 
        if(showError){
            g2.drawImage(image, (getWidth()-220)/2, (getHeight()-93)/2, 220, 93, null);         
        }        
    }

    @Override
    public void mouseClicked(MouseEvent e) {}
    @Override
    public void mousePressed(MouseEvent e) {}
    @Override
    public void mouseReleased(MouseEvent e) {      
        checkUnlockPattern();
    }
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}
    @Override
    public void mouseDragged(MouseEvent e) {
        //cuando se arrastra el cursor del mouse
        figurePatternArr.stream().filter((p) -> ( 
            FigurePattern.insideArea(p.getArea(),e.getPoint()) )).forEach((p) -> {
            if(this.pattern.isEmpty()){//Si esta vacio se añade objeto
                pattern.add(p);//añade a array
                p.setSelected(true);//cambia color
                password +=p.getKey();//concatena valor de figura
            }else{//Si ya existen objetos en el array
                //se comprueba que objeto nuevo no se repita con el ultimo añadido
                if(pattern.get(pattern.size()-1).getKey() != p.getKey()){
                    pattern.add(p);//añade a array
                    p.setSelected(true);//cambia color
                    password +=p.getKey(); //concatena valor de figura
                }
            }
        });
        repaint();
    }
    @Override
    public void mouseMoved(MouseEvent e) { showError=false; repaint();}    
    /**
     * metodo para comprobar que patron de desbloqueo es correcto
     * de ser asi -> abre nuevo formulario
     * caso contrario -> muestra mensaje de error
     */
    public void checkUnlockPattern(){
        if(password.equals(myKey)){//si patron de desbloqueo es correcto
            parent.setVisible(false);//se oculta 
            //se muestra aplicacion cliente
//            frmLogin app = new frmLogin();
//            app.setVisible(true);
        }else{            
            showError=true;
        }
        pattern.clear();//limpia movimientos antiguos
        password="";//resetea password
        //reinicia valor para objetos
        figurePatternArr.stream().forEach((b) -> {
            b.setSelected(false);
        });
        repaint();
    }
}//end