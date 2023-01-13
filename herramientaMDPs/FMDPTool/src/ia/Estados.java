package ia;

import java.util.Arrays;
import utileria.*;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

public class Estados {

  String[] vars;
  int[][] code;

  public Estados(String[] vars, int[][] code) {
    for(int i=0; i<vars.length; i++)
      this.vars[i]=vars[i];

    for(int i=0; i<code.length;i++)
      this.code[i]=code[i];
  }

 private static int dameNoEstados(int[] noValxVar){

   int p=1;
   for(int i=0; i<noValxVar.length; i++)
    p=p*noValxVar[i];
    return p;
 }


  private static int[] formaGrupos(int valor, int grupos, int veces){

    int totalEstados=valor*grupos*veces;

    int[] columna=new int[totalEstados];

   // int indice=0;
    for(int g=0, indice=0; g<grupos; g++)
    for(int i=0; i<valor; i++){
      for(int j=0; j<veces; j++){
     // System.out.println(""+i);
       columna[indice++]=i;   }
    }

  return columna;
  }

  public static int[][] generaEstados(Variable[] vars){
    int[] noValxVar=new int[vars.length];

    for(int i=0; i<vars.length; i++)
      noValxVar[i]=vars[i].values.length;

    return generaEstados(noValxVar);
  }

   public static int[][] generaEstados(int[] noValxVar){
     int totalEstados=1;

     for(int i=0; i<noValxVar.length; i++)
       totalEstados*=noValxVar[i];

   //System.out.println("total estados= "+totalEstados);

   // int[][] tabla=new int[totalEstados][noValxVar.length];
   int[][] tabla=new int[totalEstados][0];
    int repeticiones=1;
   // int veces=totalEstados/noValxVar[0];
   int veces=totalEstados/noValxVar[noValxVar.length-1];

    //Listas.imprimeLista(formaGrupos(noValxVar[noValxVar.length-1], veces, repeticiones));

    tabla=Matrices.insCol(tabla, formaGrupos(noValxVar[noValxVar.length-1], veces, repeticiones),0);

  //  for(int i=1; i<noValxVar.length; i++){
 for(int i=noValxVar.length-2; i>=0; i--){
      repeticiones*=noValxVar[i+1];
      veces=totalEstados/(noValxVar[i]*repeticiones);
      //Listas.imprimeLista(formaGrupos(noValxVar[i], veces, repeticiones));
      //System.out.println("repeticiones= "+repeticiones);
      //System.out.println("veces= "+ veces);
      //System.out.println("no. val= "+noValxVar[i]);
      tabla=Matrices.insCol(tabla, formaGrupos(noValxVar[i], veces, repeticiones),0);
    }

    return tabla;
   }

  public static void main(String[] args){
    //formaGrupos(2, 6, 1);
   // formaGrupos(2, 3, 2);
   // formaGrupos(3, 1, 4);

  // Listas.imprimeLista(formaGrupos(3, 6, 1));

   int[] lista={3,8,8,9,12};

   //generaEstados(lista);
   Listas.imprimeLista(lista);
   Matrices.imprimeTabla(generaEstados(lista));
/*
  int[][] tabla=new int[3][3];
  int[] col={0,8,6};

  Matrices.imprimeTabla(Matrices.insCol(tabla,col, 0));
  Matrices.imprimeTabla(Matrices.insCol(tabla,col, 1));

        */
  }

  }