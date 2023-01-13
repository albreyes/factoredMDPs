/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pruebas;
import ia.FMDP;
import utileria.Matrices;
import utileria.tablas;

/**
 *
 * @author Alejandro
 */
public class ImprimeTablas {

    
    
    public static void main(String[] args) {
        String policyFilename="C:\\Users\\Alejandro\\Documents\\Pasantia\\INEEL\\herramienta\\herramientaMDPs\\ejemplos\\Ej6\\fmdp.obj";

        FMDP fmdp=FMDP.retrieveMDP(policyFilename);
        String path="C:\\Users\\Alejandro\\Documents\\Pasantia\\INEEL\\herramienta\\herramientaMDPs\\ejemplos\\Ej6\\Ej6";
        
        double[][] recompensaSXA=new double[fmdp.s.length][fmdp.modelo.length];
      
        for(int a=0; a<fmdp.modelo.length; a++){
        System.out.println("modelo accion "+a);
        Matrices.imprimeTabla(fmdp.modelo[a]);
        tablas.matrixToFile(path+"modeloAccion"+a+".csv", fmdp.modelo[a]);
        
        for(int s=0;s<fmdp.s.length;s++)
        recompensaSXA[s][a]= fmdp.reward[s];
        }
        
        
        tablas.matrixToFile(path+"recompensaSXA.csv", recompensaSXA);
        tablas.matrixToFile(path+"catalogoEstados.csv", fmdp.s);
       
        double[][] resultados=new double[fmdp.s.length][2];
        for(int i=0; i<fmdp.s.length; i++){
          resultados[i][0]=fmdp.politica[i];
          resultados[i][1]=fmdp.utilidad[i];
        }
        tablas.matrixToFile(path+"resultados.csv", resultados);
        
        
    }
    
}
