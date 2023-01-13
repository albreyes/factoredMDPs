/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebas;

/**
 *
 * @author Home
 */
public class TestDiscretizacion {

    /**
     * @param args the command line arguments
     */
    
    public static int getValorDiscretoInt(  double min, 
                                            double max, 
                                            int noPartes, 
                                            double valorContinuo) {

        int s = -1;

        if (valorContinuo < min) {
            valorContinuo = min;
        } else if (valorContinuo > max) {
            valorContinuo = max;
        }

        double dif = (max - min) / (double) noPartes;
        double val = min;
        for (int i = 0; i < noPartes; i++, val += dif) {
            if (i < noPartes - 1) {
                if (valorContinuo >= val && valorContinuo <= val + dif) {
                    s = i;
                }
            } else {
                if (valorContinuo >= val && valorContinuo <= max) {
                    s = i;
                }
            }
        }
        return s;
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        
        // ejemplo de discretizacion
        double min=200;
        double max= 2000;
        int noPartes=10;
        double valorContinuo=800;
        
        int vd= getValorDiscretoInt( min,  max,  noPartes,  valorContinuo);
        System.out.println("El valor disreto de "+valorContinuo+ " es "+vd);
    }
    
}
