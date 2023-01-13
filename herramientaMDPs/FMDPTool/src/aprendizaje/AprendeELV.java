package aprendizaje;

import elvira.learning.BICLearning;
import elvira.learning.K2Learning;
import elvira.learning.PCLearning;
import java.io.File;
import java.io.IOException;

import utileria.FileOutput01;
import utileria.Filename;
import utileria.tablas;
import elvira.*;
import elvira.parser.*;
import elvira.learning.*;
import java.io.*;
//import elvira.learning.BICLearning;
//import elvira.learning.PCLearning;

/**
 * <p>Title: Prototipo de Diagnostico</p>
 * <p>Description: Diagnostico de Fallas de una TG</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: MCC ITESM</p>
 * @author not attributable
 * @version 1.0
 */

public class AprendeELV {
  public AprendeELV() {
  }

  /*=======================================
        Aprendizaje K2 con metrica BIC
  ========================================*/
  public boolean generaRB(String ArchivoDBC,
                          String SNumMaxParents,
                          String ArchivoELV,
                          String SNumCasos){
    boolean result = false;
    String arg[] = {
      ArchivoDBC,
      SNumMaxParents,
      ArchivoELV,
      SNumCasos,
      "BIC"
    };
    String arg2[]= {
      ArchivoELV.toString()
    };
    try{
     K2Learning Aprende = new K2Learning();
     Aprende.main(arg);
     //elvira.Elvira.main(arg2); //Solo para visualizar la Red Bayesiana
     result = true;
    }catch (NullPointerException npe) {System.err.println(npe.getMessage());}
    catch(elvira.parser.ParseException p){System.err.println(p.getMessage());}
    catch(IOException ee){System.err.println(ee.getMessage());}
    return(result);
  }

  /*=======================================
        Aprendizaje K2 con metrica K2
  ========================================*/
  public boolean generaRB_BIC(String ArchivoDBC,
                              String SNumMaxParents,
                              String ArchivoELV){
    boolean result = false;
    String arg[] = { ArchivoDBC,
                     SNumMaxParents,
                     ArchivoELV};

    String arg2[]= { ArchivoELV.toString()
                   };
    try{
       BICLearning Aprende = new BICLearning();
       Aprende.main(arg);
       elvira.Elvira.main(arg2); //Solo para visualizar la Red Bayesiana
       result = true;
   }catch (NullPointerException npe) {System.err.println(npe.getMessage());}
   catch(elvira.parser.ParseException p){System.err.println(p.getMessage());}
   catch(IOException ee){System.err.println(ee.getMessage());}
   return(result);
  }
  /*=======================================
                Aprendizaje PC
  ========================================*/
  public boolean generaRB_PC(String ArchivoDBC,
                             String SNumCasos,
                             String ArchivoELV){
    boolean result = false;
    String arg[] = { ArchivoDBC,
                     SNumCasos,
                     ArchivoELV
                  };
    String arg2[]= { ArchivoELV.toString()
                   };
    try{
       PCLearning Aprende = new PCLearning();
       Aprende.main(arg);
       elvira.Elvira.main(arg2); //Solo para visualizar la Red Bayesiana
       result = true;
   }catch (NullPointerException npe) {System.err.println(npe.getMessage());}
   catch(elvira.parser.ParseException p){System.err.println(p.getMessage());}
   catch(IOException ee){System.err.println(ee.getMessage());}
   return(result);
  }
  // devuelve no casos. el archivo con datos tabulares
 public static int generaDBC(String ejemplosFilename, String attributePath){
	 
	 Filename fn=new Filename(ejemplosFilename,'/', '.');
	 
	 String path=fn.path();
	 String name=fn.filename();
	 
     String[][] ejemplos = tablas.fileToMatrix(ejemplosFilename, "\t");
     String[][] atbs=tablas.fileToMatrix(attributePath,"\t:,");

     // aqui hay que contruir con attributePath
     BaseNodos bn = new BaseNodos("base_nodos", ejemplos,
                                  atbs);
     FileOutput01 fo = new FileOutput01(path + "/" + name + ".dbc");

     fo.write(bn.getDatosCompletos());
     fo.close();
     //archivo[i] = path + "/a" + i + "Elv.dbc";
     return ejemplos.length - 1;	 
	 
 } 
 
 public static void aprendeRBconDatosCont(String ejemplosCont, String atributosCont, String atributosDis, String archivoELV, int SNumMaxParents){
		
	 String ejemplosDiscretos=discretAuto(ejemplosCont,
		atributosCont,
		atributosDis);	 
	 
		
		aprendeRBconDatosDiscretos(ejemplosDiscretos, atributosDis,  archivoELV,  SNumMaxParents);	
		
 }

 // funcion de discretizacion en base a informacion de archivos
 // recibe archivos de ejemplos y atributos continuos, atributos
 // discretos y devuelve nombre archivo con ejemplos discretizados

 public static String discretAuto(String contExampleFile, String contAttribFile, String discAttribFile){
   String[][] tabla = tablas.fileToMatrix(
       contExampleFile,
       "\t");
   String[][] atbCont = tablas.fileToMatrix(
       contAttribFile,":,\t ");
   String[][] atb = tablas.fileToMatrix(
       discAttribFile,":,\t ");

       for(int i=0; i<atbCont.length; i++)
         if(atbCont[i][1].equals("real")&&!atb[i][1].equals("real")){
           String atName=atbCont[i][0];
           int limInf=Integer.parseInt(atbCont[i][2]);
           int limSup=Integer.parseInt(atbCont[i][3]);
           int numPartes=atb[i].length-1;
         tabla=ValorDiscreto.discretizVar(tabla,atName,
                                          limInf,
                                          limSup,
                                          atb[i].length-1);
       }

     File file=new File(contExampleFile);

     String path=file.getParent()+"/";

     String nombre=file.getName();
     nombre=nombre.substring(0,nombre.length()-4)+"_DIS.txt";
     String discExampleFile=path+nombre;

      tablas.matrixToFile(discExampleFile,tabla);
      return discExampleFile;
 }
 
 public static void aprendeRBconDatosDiscretos(String ejemplosFilename, String atributosFilename, String archivoELV, int SNumMaxParents){
	 
	 int SNumCasos=generaDBC(ejemplosFilename,  atributosFilename);
	 
	 Filename fn=new Filename(ejemplosFilename,'/', '.');
	 
	 String archivoDBC=fn.path()+"/"+fn.filename()+".dbc";
	 
	 
	   AprendeELV aprende=new AprendeELV();
	     aprende.generaRB(archivoDBC,
	                      "" + SNumMaxParents,
	                      archivoELV,
	                     "" + SNumCasos);
	     
	     String lista[]={archivoELV};
	     
	     elvira.Elvira.main(lista); 
 }
  
 public static void main(String args[]){
	 

		
		String folder="../../ejemplos/powerPlant/dynamo/pruebas/";
		
		
		
		String ejemplosCont=folder+"ejemplos.dat";
		
		//String ejemplosDis=folder+"muestraEjemplosDiscretos.txt";
		String atributosDis=folder+"atributosDis.txt";
		String atributosCont=folder+"atributos.txt";
		String archivoELV=folder+"redMuestra.elv";
		int SNumMaxParents=15;
		
		//aprendeRBconDatosDiscretos( ejemplosDis,  atributosDis, archivoELV,  SNumMaxParents);
		 aprendeRBconDatosCont( ejemplosCont, atributosCont, atributosDis, archivoELV,  SNumMaxParents);

 }
 
}
