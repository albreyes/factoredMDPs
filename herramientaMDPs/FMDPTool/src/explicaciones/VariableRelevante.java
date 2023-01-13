package explicaciones;

/**
 *
 * @author liliana.sanchez
 */
public class VariableRelevante {

    public DatosMDP mdp; 
    private int numeroVariables;
    private int valor;
    private int politica;
    private int [] valoresBase;
    private int numVariableRelevante = -1;
    private double utilidadMayor     = 0;
    private double utilidadMenor     = 0;
    private double utilidad          = 0;
    private double diferencia        = 0;
    private double diferenciaMayor   = 0;

    public VariableRelevante(){
        mdp = new DatosMDP();  
    }
    public void setValoresBase(int [] estadosDis) {
        numeroVariables = estadosDis.length; //numero variables discretizadas
        mdp.setValoresDis(estadosDis);
    }

    /**
     * @return variableRelevante
     * @descripción Algoritmo para encontrar la variable relevante
     */
    public int ejecutarAlgoritmo(){

           valoresBase = mdp.getValoresDis();

           for( int i = 0 ; i < numeroVariables ; i++ ) {

               utilidadMayor = 0;
	       utilidadMenor = 0;
	       utilidad      = 0;

               for(int j=0; j < mdp.getConjuntoEstados().get(i).size();j++) {

                   valor = Integer.parseInt(String.valueOf(mdp.getConjuntoEstados().get(i).get(j)));

                       utilidad = mdp.getUtilidad(nuevoEstado(valor,i));

                          if(j==0) {
				   utilidadMayor = utilidad;
				   utilidadMenor = utilidad;
			   } else if(utilidad > utilidadMayor){
				   utilidadMayor = utilidad;
			   } else if(utilidad < utilidadMenor){
				   utilidadMenor = utilidad;
			   }
               }

               diferencia = Math.abs(utilidadMayor - utilidadMenor);

		   if(i==0) {
			   diferenciaMayor = diferencia;
			   numVariableRelevante = 0;
		   } else if(diferencia > diferenciaMayor) {
			   diferenciaMayor = diferencia;
			   numVariableRelevante = i;
		   }
           }
         
           return numVariableRelevante;
    }

    /**
     * @return nuevoEstado[]
     * @descripción combinacion de nuevos estados
     */
    public int[] nuevoEstado(int valor, int numVar){
        int [] nuevoEstado = new int[numeroVariables];
        for(int i = 0 ; i < nuevoEstado.length ; i++){
            if(i == numVar)
                nuevoEstado[i] =  valor;
            else
                nuevoEstado[i] = valoresBase[i];

        }

        return nuevoEstado;
    }

    /**
     * @return the politica
     */
    public int getPolitica() {
        return politica;
    }

    /**
     * @param politica the politica to set
     */
    public void setPolitica(int politica) {
        this.politica = politica;
    }


}
