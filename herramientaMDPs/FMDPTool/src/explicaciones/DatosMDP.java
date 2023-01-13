package explicaciones;

import java.util.ArrayList;
import utileria.tablas;

/**
 *
 * @author liliana.sanchez
 */

public class DatosMDP {

    //ruta del MDP resuelto
    public String fileDatosMDP = "../ejemplos/powerPlant/5acc5vars2%/discreto/datosMDPPaco.txt";
    //conjunto de estados para cada variable del MDP resuelto
    private ArrayList<ArrayList> conjuntoEstados;
    private int[] valoresDis;
    private String[][] tablaDatosMDP = null;

    public DatosMDP(){ 
        AllDatosMDP();
        setConjuntoEstados();
    }

    public DatosMDP(String rutaMDP){
        fileDatosMDP = rutaMDP;
        AllDatosMDP();
    }

    /**
     * @param estadosDis[]
     * @descripcion: valores discretizados de las variables:
     *               msf, fwf, d, pd, g
     */
    public void setValoresDis(int [] estadosDis){
        valoresDis = new int[estadosDis.length];
        
        for(int i = 0; i < valoresDis.length;i++){
            valoresDis[i] = estadosDis[i];
        }

    }

    /**
     * @return tablaDatosMDP
     * @descripcion tablaDatosMDP contiene todos los valores del MDP resuelto
     *              fms, ffw, d, pd, g, recompensa, utilidad y politica
     */


    private String[][] AllDatosMDP(){
        tablaDatosMDP = tablas.fileToMatrix(fileDatosMDP, "\t");
        return tablaDatosMDP;
     }

    /**
     *@return double utilidad
     *@descripcion obtiene la utilidad de acuerdo a los valores discreto de datos de las
     *             variables (msf, ffw, d, pd, g)
     */
    public double getUtilidad(int [] estadosDis ){
        double utilidad_= -1.0;
		   for(int i=0; i<tablaDatosMDP.length; i++) {
                       if(i>0){
			   if(Integer.parseInt(tablaDatosMDP[i][0]) == estadosDis[0] &&
			      Integer.parseInt(tablaDatosMDP[i][1]) == estadosDis[1] &&
			      Integer.parseInt(tablaDatosMDP[i][2]) == estadosDis[2] &&
			      Integer.parseInt(tablaDatosMDP[i][3]) == estadosDis[3] &&
			      Integer.parseInt(tablaDatosMDP[i][4]) == estadosDis[4]) {
				 utilidad_ =  Double.parseDouble(tablaDatosMDP[i][6]);
			   }
                     }
                   }
        
        return utilidad_;
    }

    public int getEstado(int [] estadosDis ){
        int estado_= -1;
		   for( int i=0 ; i < tablaDatosMDP.length ; i++) {
                       if(i>0){
			   if(Integer.parseInt(tablaDatosMDP[i][0]) == estadosDis[0] &&
			      Integer.parseInt(tablaDatosMDP[i][1]) == estadosDis[1] &&
			      Integer.parseInt(tablaDatosMDP[i][2]) == estadosDis[2] &&
			      Integer.parseInt(tablaDatosMDP[i][3]) == estadosDis[3] &&
			      Integer.parseInt(tablaDatosMDP[i][4]) == estadosDis[4]) {
				 estado_ =  Integer.parseInt(tablaDatosMDP[i][7]);
			   }
                     }
                   }

        return estado_;
    }


    /**
     * @descripcion se alamacenan en conjuntoEstados todos los diferentes valores de
     *              cada variable P.E. conjuntoEstados({0,1,2,3,4,5},{0,1}...etc)
     */
    private void setConjuntoEstados(){
        String actual;
        conjuntoEstados = new ArrayList();
           for(int j = 0 ; j < getTablaDatosMDP()[0].length - 3 ; j++){
               ArrayList arrayTemp = new ArrayList();
               for(int i = 0 ; i < getTablaDatosMDP().length ; i++){
                   if(i > 0){
                    actual = getTablaDatosMDP()[i][j];
                        if(!arrayTemp.contains(actual) ){
                            arrayTemp.add(actual);
                         }
                   }
               }
               conjuntoEstados.add(arrayTemp);
            }
    }

    public String[][] getTablaDatosMDP(){
        return tablaDatosMDP;
    }

    
    /**
     * @return the valoresDis
     */
    public int[] getValoresDis() {
        return valoresDis;
    }

    /**
     * @return the conjuntoEstados
     */
    public ArrayList<ArrayList> getConjuntoEstados() {
        return conjuntoEstados;
    }


}
