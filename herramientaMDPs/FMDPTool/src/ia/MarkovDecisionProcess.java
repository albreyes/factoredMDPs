package ia;

import utileria.*;


/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

public class MarkovDecisionProcess {

    private double[][][] modelo;
    private double[]reward;
    public int[] politica;
    public double[] utilidad;
    double factorDesc;
    double EPSILON;
    int ITERACIONES;

  public MarkovDecisionProcess(double[][][] modelo, double[] reward,
                               double factorDesc, int noIt, double epsilon) {

    this.modelo=modelo;
    this.reward=reward;
    EPSILON=epsilon;
    ITERACIONES=noIt;
    this.factorDesc=factorDesc;

    politica=new int[reward.length];

    for(int i=0;i<reward.length; i++)
    politica[i]=-1;

    utilidad=new double[reward.length];
  }

  public int[] valueIteration(){

   long time=System.currentTimeMillis();

		double u[]=new double[reward.length];
		double uprima[]=new double[reward.length];
		int c=0;

	  	for(int i=0; i<u.length; i++)
		  { u[i]=reward[i]; uprima[i]=reward[i]; }

	do{
			//hace u=uprima;
			for(int s=0; s<u.length; s++) u[s]=uprima[s];

			for(int state=0; state<reward.length; state++)
			{
			if(u[state]!=1.0)
			uprima[state]=reward[state]+ factorDesc*mue(state,u);
			}
			c++;
		}
		while(error(u,uprima)>EPSILON&&c<ITERACIONES);
                System.out.println("numero de iteraciones: "+c);

    time=System.currentTimeMillis()-time;
    System.out.println("convege en "+time+" ms");

		 utilidad=u;
                 return politica;
	}


  double mue(int s, double[] u){

    double[] l=new double[modelo.length];

    double sum;

    for(int a=0; a<modelo.length; a++){
      sum=sumatoria(s,a,u);
      l[a]= sum;
    }

      double max=Listas.max(l);

      politica[s]=Listas.indice(l,max);

      return max;
  }





  double sumatoria(int s, int a, double[] u) {
    double suma = 0.0;

    for (int s1 = 0; s1 < modelo[a].length; s1++)
      suma += modelo[a][s][s1] * u[s1];

    return suma;

  }


   // aqui se calcula el error cuadratico medio

  private double error(double u[], double uprima[]) {

  double promedio;
  double sum=0;

   for(int k=0;k<u.length;k++)
     sum=(u[k]-uprima[k])*(u[k]-uprima[k])+sum;

   promedio=sum/(double) u.length;
   System.out.println("error="+promedio);
   return(promedio);

  }

  public static void main(String args[]){
/*
    String ejemplosCrudos="../ejemplos/crudos/robot/robotFalsosEj.txt";
    String atributosCrudos="../ejemplos/crudos/robot/robotFalsosAt.txt";
    String elvCasesFile="../ejemplos/weka/robot/robotFalsosEj.arff";
    double umbral=0.05;
    int noAcciones=3;
    int SNumMaxParents=10;
    String elvOutputFolder="../ejemplos/elvira/robot/";
*/

/*
String ejemplosCrudos="../ejemplos/crudos/robot/robotFalsosContEj.txt";
String atributosCrudos="../ejemplos/crudos/robot/robotFalsosContAt.txt";
double umbral=0.05;
int noAcciones=3;
int SNumMaxParents=10;
*/

/*
String ejemplosCrudos="../ejemplos/crudos/golf/ejemplos.txt";
String atributosCrudos="../ejemplos/crudos/golf/atributos.txt";
double umbral=0.05;
int noAcciones=2;
int SNumMaxParents=10;
*/

/*
String ejemplosCrudos="../ejemplos/crudos/golf1/ejemplosComb.txt";
String atributosCrudos="../ejemplos/crudos/golf1/atributosComb.txt";
double umbral=0.05;
int noAcciones=2;
int SNumMaxParents=10;
*/
/*
String ejemplosCrudos="../ejemplos/crudos/golf2/ejemplosComb.txt";
String atributosCrudos="../ejemplos/crudos/golf2/atributosComb.txt";
double umbral=0.05;
int noAcciones=2;
int SNumMaxParents=10;
*/

//String ejemplosCrudos="../ejemplos/robotNoObst/todosDiscretos10x10x5/ejemplosDis.txt";
//String atributosCrudos="../ejemplos/robotNoObst/todosDiscretos10x10x5/atributos.txt";
//String ejemplosCrudos="../ejemplos/robotNoObst/angDiscreto/ejemplosDis.txt";
//String atributosCrudos="../ejemplos/robotNoObst/angDiscreto/atributos.txt";
//String ejemplosCrudos="../ejemplos/robotNoObst/conTodas/ejemplos.txt";
//String atributosCrudos="../ejemplos/robotNoObst/conTodas/atributos.txt";




  }

}