/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebas;

import ia.FMDP;

/**
 *
 * @author Home
 */
public class PruebaFMDP {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
            // un ejemplo de russell con datos continuos
/*
    String ejemRussellCont="../ejemplos/russell/20Metas/exploracion.dat";
    String atribRussellCont="../ejemplos/russell/20Metas/atributos.txt";

    FMDP russellCont = FMDP.aprendeResuelveFMDPCont_Cualitativos(ejemRussellCont,
        atribRussellCont, atribRussellCont,
        10, 4, 0.9, 500, 1e-5);
    */
    String path="../ejemplos/russell/clasico/";
    String ejemplosFile=path+"ejemplos.dat";
    String atributosFile=path+"atributos.txt";
    String atributosDisFile=path+"atributosDis.txt";
    int noMaxPadresRed=10;
    int noAcciones=5;
    double fd=0.9;
    int maxIteraciones=500;
    double epsilon=1e-5;
    
    FMDP clasico =FMDP.aprendeResuelveFMDPCont_Dis(ejemplosFile,
                                           atributosFile,
                                           atributosDisFile,
                                           noMaxPadresRed,
                                           noAcciones,
                                           fd,
                                           maxIteraciones,
                                           epsilon);

    FMDP.imprimeResultados(clasico,path);
    
    
        // un ejemplo simple de russell con la red hecha a mano
String path1="../ejemplos/russell/manualmente/";
    String[] redes0 = {
        path1+"a0.elv",
        path1+"a1.elv",
        path1+"a2.elv",
        path1+"a3.elv"};

    String atbts0 = path1+"atributos.txt";
    String arbol = path1+"dTree.arff";

    FMDP fmdp0 = FMDP.resuelveFMDP(redes0,
                              atbts0,
                              arbol,
                              0.9,
                              500,
                              1e-5);

    FMDP.imprimeResultados(fmdp0, path1);

    
     path="../ejemplos/russell/robot/";
     ejemplosFile=path+"ejemplos.dat";
     atributosFile=path+"atributos.txt";
     atributosDisFile=path+"atributosDis.txt";
     noMaxPadresRed=10;
     noAcciones=5;
     fd=0.9;
     maxIteraciones=500;
     epsilon=1e-5;
    
    FMDP robot =FMDP.aprendeResuelveFMDPCont_Dis(ejemplosFile,
                                           atributosFile,
                                           atributosDisFile,
                                           noMaxPadresRed,
                                           noAcciones,
                                           fd,
                                           maxIteraciones,
                                           epsilon);

    FMDP.imprimeResultados(robot,path);
    
    
    }
    
}
