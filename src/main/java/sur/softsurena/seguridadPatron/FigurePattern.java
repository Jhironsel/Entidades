package sur.softsurena.seguridadPatron;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
public class FigurePattern {
    //identificador/valor de objeto (NO debe repetirse)
    private int key;
    //coordenadas
    private int x               =   0;
    private int y               =   0;  
    //bandera 
    private boolean selected    =   false;
    //area sensible a eventos del raton
    private Rectangle area      =   new Rectangle();
    
    /**Constructor de clase */
    public FigurePattern(){}
    
    /**
     * Constructor de clase
     * @param k clave de celda
     * @param x coordenada X de objeto
     * @param y coordenada Y de objeto
     */
    public FigurePattern(int k, int x, int y){
        this.key = k;
        this.x = x;
        this.y = y;
        //area interna del circulo
        area.setBounds(x+10, y+10 , 60, 60);
    }
    
    public void draw(Graphics2D g2){
        //fondo
        g2.setColor( new Color(0,0,0) );
        g2.fill( new Ellipse2D.Double(x+10, y+10 , 60, 60 ) );
        //borde exterior
        g2.setStroke(new BasicStroke( 4 ));        
        g2.setColor( (selected)?new Color(0,204,0):new Color(153,153,153) );        
        g2.draw( new Ellipse2D.Double(x+10, y+10 , 60, 60 ) );                
        //circulo interno
        g2.setColor( new Color(255,255,255) );
        g2.fill( new Ellipse2D.Double(x+30, y+30 , 20, 20 ) );        
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }
    
    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public Rectangle getArea() {
        return area;
    }

    public void setArea(Rectangle area) {
        this.area = area;
    }
    
    /**
     * retorna el punto medio de la figura
     * @return Point
     */
    public Point getCenterPoint(){
        Point p = new Point( x+40, y+40);
        return p;
    }
    
    /**
     * Metodo statico que determina su un punto se encuentra dentro un area rectangular
     * @param r rectangle
     * @param p point
     * @return boolean
     */
    public static boolean insideArea(Rectangle r,Point p) {            
        return r.contains(p);            
    }
    
}//end:FigurePattern
