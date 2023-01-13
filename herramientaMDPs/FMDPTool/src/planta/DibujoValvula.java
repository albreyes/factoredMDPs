
package planta;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import javax.swing.*;

class DibujoValvula extends JPanel {

    private static final int   espacio = 10;
    private static final float rad = (float)(Math.PI / 55.0);

    private int tamano;
    private int xCentro;
    private int yCentro;
    private BufferedImage muestra;
    private int valor = 0;
    private String nombre;
    private Color colorFondo;
    private Color colorBola;
    private Color colorNum;
    private Color colorLetras;

    public DibujoValvula(String nombre,Color fondo,Color circulo,Color numeros,Color letras) {
        this.setPreferredSize(new Dimension(130,130));
        this.nombre=nombre;
        this.colorFondo = fondo;
        this.colorBola = circulo;
        this.colorNum = numeros;
        this.colorLetras = letras;
    }

    public void update() {
        this.repaint();
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int ancho = getWidth();
        int alto = getHeight();
        tamano = ((ancho < alto) ? ancho : alto) - 2*espacio;
        xCentro = tamano/2 + espacio;
        yCentro = tamano/2 + espacio;

        if (muestra == null
                || muestra.getWidth() != ancho
                || muestra.getHeight() != alto) {

            muestra = (BufferedImage)(this.createImage(ancho, alto));
            Graphics2D gc = muestra.createGraphics();
            gc.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                                RenderingHints.VALUE_ANTIALIAS_ON);
            caraReloj(gc);
        }


        g2.drawImage(muestra, null, 0, 0);

        Manecillas(g);
    }

    private void caraReloj(Graphics g) {
        g.setColor(colorFondo);
        g.fillRect(0, 0, getHeight(),getWidth());
        g.setColor(colorBola);
        g.fillOval(espacio, espacio, tamano, tamano);
        g.setColor(Color.black);
        g.drawOval(espacio, espacio, tamano, tamano);

        for (int seg = 0; seg<110; seg++) {
            int inicio = 0;
            if(!( (seg>100 && seg<=110))){
                if (seg % 10 == 0) {
                    inicio = tamano/2-10;
                } else {
                    inicio = tamano/2-5;
                }
                diseno(g, xCentro, yCentro, rad * seg, inicio , tamano/2, Color.BLACK);
            }

        }
    }

        private void Manecillas(Graphics g) {
        int radioSegundero = tamano/2;

        float fsegundos = valor;
        diseno(g, xCentro, yCentro, (rad*fsegundos), 0, radioSegundero,Color.red);

	Font font = new Font("Arial", Font.BOLD, 10);
        g.setColor(colorNum);
	g.setFont(font);
	g.drawString( "50", 70, 32 );
	g.drawString( "40", 88, 45 );
	g.drawString( "30", 96, 63 );
	g.drawString( "20", 92, 85 );
	g.drawString( "10", 78, 101 );
	g.drawString( "0",  63, 108 );
	g.drawString( "100", 36, 100 );
	g.drawString( "90", 27, 85 );
	g.drawString( "80", 25, 63 );
	g.drawString( "70", 32, 45 );
	g.drawString( "60", 51, 32 );
	Font font1 = new Font("Arial", Font.BOLD, 9);
	g.setFont(font1);
        g.setColor(colorLetras);
	g.drawString( "VALVE", 45, 58 );
	g.drawString(nombre, 53, 90 );
    }

    private void diseno(Graphics g, int x, int y, double angulo, int minRadius, int maxRadius, Color c) {
        float sine   = (float)Math.sin(angulo);
        float cosine = (float)Math.cos(angulo);

        int dxmin = (int)(minRadius * sine);
        int dymin = (int)(minRadius * cosine);

        int dxmax = (int)(maxRadius * sine);
        int dymax = (int)(maxRadius * cosine);
        g.setColor(c);
        g.drawLine( x+dxmin, y+dymin, x+dxmax, y+dymax);
    }

    /**
     * @return the valor
     */
    public int getValor() {
        return valor;
    }

    /**
     * @param valor the valor to set
     */
    public void setValor(int valor) {
        this.valor = valor;
        repaint();
    }
}
