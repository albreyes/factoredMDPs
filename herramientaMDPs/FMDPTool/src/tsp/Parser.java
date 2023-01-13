package tsp;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.lang.*;
import java.io.*;

/**
 * <p>Title: Parser </p>
 * <p>Description: Realiza la traducci�n del MDP SPI a MDP SPUDD</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: Instituto de Investigaciones El�ctricas</p>
 * @author Christian Guti�rrez Bravo
 * @version 1.0
 */

 public class Parser{
  String MDPspudd;
  StringTokenizer tokens;

  /*Palabras reservadas para SPUDD */

    final String action = "action";
    final String endaction = "endaction";
    final String openpar = "(";
    final String closepar = ")";
    final String variables = "variables";
    final String unnormalized = "unnormalized";
    final String normalized = "normalized";
    final String reward = "reward";
    final String discount = "discount";
    final String tolerance = "tolerance";
    final String espace = " ";
    final String tab = "\t";
    final String breakline = "\n";
    final String function = "function";

  /*Tokens para buscar en SPI */

    final String varspi = "variables";
    final String nodo = "nodo:";
    final String valores = "valores:";
    final String padre = "padre:";
    final String accion = "accion";
    final String acorchete = "[";
    final String ccorchete = "]";
    final String apar = "(";
    final String cpar = ")";
    final String asterisco = "*";


  /*Definiendo errores*/
    final String error1 = "La definici�n incluida en su archivo esta corrupta";
    final String error2 = "El aprenidizaje en la acci�n fue nulo";

  /*Variables utilizadas para manipulaci�n de caracteres */
    int countt = 0;
    int countts = 0;
    int tmem = 0;
    int countact = 0;
    int sigact = 0;
    int varbs = 0;
    int tv = 0;
    int mitad = 0;
    int ttk = 0;
    int conttr = 0;
    int conttrm = 0;
    int cvarpad = 0;
    int cvalores = 0;
    int cvnodo2 = 0;
    int nval = 0;
    int tabula = 0;

  /*Variables auxiliares*/
    Vector actions;
    Vector nodop;
    Vector spudd;
    String nameactspi;
    String actemp;
    Vector biblioteca;
    Vector memoria;
    Vector dreward;
    Vector rewardm;
    Vector rewardf;
    Vector posrec;
    Vector tpar;
    Vector vnodo2;
    Vector val;
    Vector valact;
    Vector varact;
    Vector varpad;
    Vector postemp;
    Vector cpt;


  public Parser(Vector mem) {

  /*Definiendo el espacio en memoria para el MDP traducido*/
    MDPspudd = "" ;

    spudd = new Vector();
    actions = new Vector();
    nodop = new Vector();
    biblioteca = new Vector();
    memoria = new Vector();
    dreward = new Vector();
    rewardm = new Vector();
    rewardf = new Vector();
    posrec = new Vector();
    tpar = new Vector();
    vnodo2 = new Vector();
    val = new Vector();
    valact = new Vector();
    varact = new Vector();
    varpad = new Vector();
    postemp = new Vector();
    cpt = new Vector();
  }

 void parser(Vector mem)
  {
    /*Para la versi�n donde recibe el archivo*/
    /*tokens = new StringTokenizer(MDPspi);*/
    /*Dividiendo en tokens la cadena*/
    /*while (tokens.hasMoreTokens())
    { String token;
      token = new String();
      token = tokens.nextToken();
    /*Anexando cada token al vector*/
      /*memoria.add((String)token);
     }*/

  /*-------------------------------------*/
     /*Traduction MDPspi to MDPspudd*/
  /*-------------------------------------*/
   memoria = mem;

  /********************************************************/
    tmem = memoria.size();
  /*-------------------------------------------------------*/
  /*Traduciendo variables*/

  /*Buscando por medio de la definici�n de variables cuantas veces
   han sido definidas variables*/

    for(int i = 0; i < tmem; i ++)
    {
      if(variables.equals((String)memoria.get(i)))
      {
        varbs ++;
      }

    }

  /*Add el parentesis ( para las variables */

    spudd.add(openpar);

  /*Anexando el token variables*/

    spudd.add(variables);

 /*Anexando las variables del MDP que son anexadas desde el analizador*/

  for( int v = 0; v < varbs; v ++ )
    {
      countt = memoria.indexOf(variables,countt);
      if( v != 0)
      {spudd.add(tab);}
       spudd.add(tab);
       spudd.add(openpar);

     /*Obtener nombre de la variable*/
      countt ++;
      spudd.add((String)memoria.get(countt));
      countt ++;

      /*Obtener valores de las variables */

      while(!(variables.equals((String)memoria.get(countt))))
      {
         /*Agregando espacio para reconocimiento de variables*/

          spudd.add(espace);

          /*Verificando que se haya especificado la normalizaci�n*/

          if(normalized.equals(memoria.get(countt)))
          {
            break;
          }

          if(unnormalized.equals(memoria.get(countt)))
          {
            break;
          }
          /*Anexando normalizaci�n*/

          spudd.add( (String) memoria.get(countt));
          countt++;
      }

  /*Saltando para traducir las acciones */

      spudd.add(closepar);
      spudd.add(breakline);
    }

      spudd.add(closepar);
      spudd.add(breakline);

  /*Definiendo la normalizaci�n*/

    if(unnormalized.equals(memoria.get(countt)))
          {
            spudd.add(memoria.get(countt));
            spudd.add(breakline);
          }

  /*Contabilizando las acciones en el MDP */

    int cactions = 0;

    for(int i = 0; i < tmem ; i ++)
       { actemp =(String)memoria.get(i);

         if(accion.equals((String)actemp))
            {
             cactions ++;
            }

           }
  /****************************************************/
  /*Inicio traducci�n de acciones                     */
  /****************************************************/

boolean ponda = false;

  endprog:

  for (int ca = 0 ; ca < cactions; ca ++)
    {

  /*Analizando si la acci�n contiene aprendizaje */

  /***********************************************/

  /*Si existe acci�n desde 0 (tendra que ser cambiado por el actual*/

  countt = memoria.indexOf(accion,countt);

  /*Iniciando action en SPUDD*/
   spudd.add(action);
   countt ++;

  /*Tomando el nombre de la accion */
   spudd.add(espace);

   nameactspi = (String)(memoria.get(countt));
   countt ++;

  /*Agregando el nombre a la accion*/
   spudd.add(nameactspi);

   /*A�adiendo peso en caso de que se encuentre*/

   if(reconoce_valor((String)memoria.get(countt)))
   {
     spudd.add(espace);
     spudd.add(memoria.get(countt));
     countt ++;
     ponda = true;
   }

  /*Agregando espacios para insertar nuevo token*/
   spudd.add(breakline);

  /*Discriminando y desechando casos en que las acciones no contienen aprendizaje */

  while(accion.equals((String)memoria.get(countt)))
   {ca ++;

  /*Borrando y actualizando vector spudd para casos donde no contiene aprendizaje*/

    int rm = spudd.size();
    rm = rm - 2 ;
    if(ponda)
    {
      rm = rm - 2;
    }

    spudd.remove(rm);
    spudd.remove(rm);

    if(ponda)
    {
      spudd.remove(rm);
      spudd.remove(rm);
      ponda = false;
    }

    /*Posicionando en el nuevo nombre de acci�n*/
    countt ++;

    /*Corrigiendo nombre a la acci�n*/
    spudd.add((String)memoria.get(countt));
    countt ++;

    if(reconoce_valor((String)memoria.get(countt)))
      {
      spudd.add(espace);
      spudd.add(memoria.get(countt));
      countt ++;
      ponda = true;
     }

     spudd.add(breakline);

    if(countt == tmem)
    { rm = spudd.size();
      rm = rm - 4;
      for ( int i = 0; i < 4 ; i ++ )
      {spudd.remove(rm);}
      continue endprog;
    }

   }

   /*Desechando caso en que la acci�n es la �ltima y no contiene aprendizaje*/

    if((countt == tmem) || (reward.equals((String)memoria.get(countt))))
     { ca ++;
       int rem = spudd.size();
       rem = rem - 6;
       if(ponda)
       {
         rem = rem - 2;
       }

       for(int i = 0; i < 6; i ++)
       {spudd.remove(rem);}
       if(ponda)
       {
         spudd.remove(rem);
         spudd.remove(rem);
         ponda = false;
       }
       continue endprog;
      }

   /*Ciclo padre */
  int cnodo = 0;
  sigact = memoria.indexOf(accion,countt);
  if(sigact < 0)
   {sigact = tmem;}
  for( int i = countt; i < sigact; i ++)
   {
     actemp =(String)memoria.get(i);
    if(nodo.equals((String)actemp))
      {
       cnodo ++;
      }
  }

/*Realizando cnodo = 1 ... n en una acci�n*/

  for(int cn = 0; cn < cnodo ; cn ++)
   {
     cvarpad = 0;
     cvalores = 0;
     postemp.removeAllElements();
     varpad.removeAllElements();
     vnodo2.removeAllElements();
     cpt.removeAllElements();

  /*Agregando a vector vfather y vchildren */
   spudd.add(tab);   /*Tabulador para inicio de nodo*/

   String vtemp;
   Vector vfather;
   Vector vnodo;
   int padret;

   vfather = new Vector();
   vnodo = new Vector();
   vtemp = new String();

   /*Definiendo las variables nodo*/

   /*Saltando nodo: */
   countt ++;

   String nodon = (String)memoria.get(countt);
   countt = countt + 2;
   padret = memoria.indexOf(padre,countt);

/*Mientras no sea el token padre, a�adir valores
para nodo                                      */

   while(countt<padret)
   {
   vtemp = (String)memoria.get(countt);
   vnodo.add((String)vtemp);
   countt ++;
   }

   nval = vnodo.size();

/*Definiendo las variables para padre*/

int lip =  memoria.indexOf(nodo,countt);
int cpadres = 0;


if(lip == -1)
  {
    lip = memoria.indexOf(accion,countt);
   }

if(lip == -1)
  {
    lip = memoria.indexOf(reward,countt);
   }

for(int pc = countt ; pc < lip ; pc ++)
   {
    if( padre.equals(memoria.get(pc)))
      {
       /*Avanzando para obtener la variable padre */
       pc ++;
       varpad.add(memoria.get(pc));
       cpadres ++;
       }
   }

/*Avanzando para llegar a las primeras variables */

biblioteca();

int tvar = varpad.size();
int a;

/*Obteniendo los valores de la variable */
for(a = 0; a < tvar ; a ++)
   {
     countt = countt + 3;
     vnodo2.add(asterisco);
     while (!((reconoce_valor( (String) memoria.get(countt))) || (padre.equals((String)memoria.get(countt))))) {
       vnodo2.add(memoria.get(countt));
       countt++;
     }
   }
  vnodo2.add(asterisco);

/*Obteniendo los valores */
String vt;
String temp34 = new String();

   while(reconoce_valor((String)memoria.get(countt)))
   {
     vt = convert_float((String)memoria.get(countt));
     temp34 =redondeo(vt);
     cpt.add(temp34);
     countt ++;
   }

int tnodo = vnodo.size();
int t = 1;
int c = 0;

for(int g = 1; g < vnodo2.size(); g ++)
   {
     while(!asterisco.equals((String)vnodo2.get(g)))
           {
           c ++;
           g ++;
         }

     t = c * t;
     c = 0;
   }

cpt_transpuesto(tnodo,t);


/*Agregar nodo al archivo de SPUDD                         */
     spudd.add(nodon);

/*Traduciendo nodos */
      funcion_nodo();

      spudd.add(cpar);
      spudd.add(breakline);
     vnodo2.removeAllElements();


   } /*Termina nodos*/

  spudd.add(endaction);
  if((ca + 1) != cactions )
   {
     spudd.add(breakline);
     spudd.add(breakline);
   }
  }

/*Anexando factores del MDP */
    spudd.add(breakline);
    spudd.add(breakline);

/*Anexando reward */
    countt = memoria.indexOf(reward,countt);
    countt ++;

/*Saltando function*/
    countt ++;

    String rew_S = "";

/*Obteniendo la funcion de reward en una cadena */
while(!discount.equals(memoria.get(countt)))
     {
       rew_S = rew_S +  memoria.get(countt) + " " ;
       countt ++;
     }

     spudd.add(reward);

/*Traduciendo funci�n de reward*/
     inicia_reward(rew_S);

/*****************************/
/*reward tree            *****/
/*****************************/


/*Agregando discount */
    countt = memoria.indexOf(discount,countt);

/*Agregando dos saltos de l�nea*/
    spudd.add(breakline);
    spudd.add(breakline);

/*Agregando el token discount   */
    spudd.add(discount);
    spudd.add(espace);
    countt ++;

/*Agregando el valor de descuento */
    spudd.add(memoria.get(countt));
    spudd.add(breakline);

    countt = memoria.indexOf(tolerance,countt);

/*Agregando el token tolerance para definir el valor de tolerancia*/
    spudd.add(tolerance);
    spudd.add(espace);
    countt ++;

/*Agregando el valor de tolerance*/

    spudd.add(memoria.get(countt));
    spudd.add(breakline);

/*Enviar a pantalla el vector*/

      for (int i = 0; i < spudd.size(); i ++ )
      {
        MDPspudd =MDPspudd + (String)spudd.get(i);
      }
    }



boolean carga_reward(String reward)
      {

         boolean acept = false;

        StringBuffer  buf;

        /*Numero de caracteres de la cadena */
        int ncar;
        char cade[];
        ncar = reward.length();
        /*Cade es un arreglo de caracteres para el String reward */
        cade = new char[ncar];
        reward.getChars(0, ncar, cade, 0);
        String car;
        car = new String();
        buf = new StringBuffer();
        boolean correct = true;
        boolean enda = true;
        int i = 0;
        int var = 0;
        int val = 0;

    /*Analizando el arreglo de caracteres para convertirlo en vector */

        for(i = 0 ; i < ncar ; i ++ )
       {


         if(cade[i] == '[')
         {

          buf.append('[');
          buf.append(' ');

          i ++;

          while(!((cade[i] == '(') || (cade[i] == ':')))
          {
            buf.append(cade[i]);
            i ++;
          }

          if(cade[i] == ':')
          {
            buf.append(':');
          }

          buf.append(' ');
        }

         if(cade[i] == ']')
         {
           buf.append(']');
           buf.append(' ');
         }

         if(cade[i] == '=')
         {
           i ++;
           while(!((cade[i] == ',') || (cade[i] == '[')))
           {
             buf.append(cade[i]);
             i ++;
           }

           if(cade[i] == '[')
           {
             i --;
           }
            buf.append(' ');

         }
      }

         StringTokenizer reward_tk;
         reward_tk = new StringTokenizer(buf.toString());
         String reward_t;
         reward_t = new String();

         while (reward_tk.hasMoreTokens()) {
         reward_t = reward_tk.nextToken();
         /*Anexando cada token al vector*/
         rewardm.add( (String) reward_t);
       }

      return acept;
    }


void funcion_nodo()
    {

      spudd.add(tab);
      spudd.add(apar);
      spudd.add(varpad.get(cvarpad));
      spudd.add(tab);
      carga_var( (String) varpad.get(cvarpad));

      for (int cvaract = 0; cvaract < varact.size(); cvaract++) {

        carga_var( (String) varpad.get(cvarpad));
        if(cvaract < varact.size())
        {
        spudd.add(apar);
        spudd.add(varact.get(cvaract));


        if (! ( (cvarpad + 1) == varpad.size())) {
          add_token_postemp(cvarpad);
          cvarpad++;

          funcion_nodo();

          spudd.add(cpar);

          int upt = postemp.size() - 1;

          cvarpad = convert_S_entero( (String) postemp.get(upt));
          postemp.remove(upt);

          carga_var( (String) varpad.get(cvarpad));

        }



        if ( (cvarpad + 1) >= varpad.size()) {
          spudd.add(tab);

         spudd.add(apar);

          carga_val();

          for (int cvalact = 0; cvalact < valact.size(); cvalact++) {
            spudd.add(valact.get(cvalact));
            spudd.add(espace);

          }

          spudd.add(cpar);

        }

        spudd.add(cpar);

      }
      if(!((cvaract + 1) == varact.size()))
      {
        spudd.add(breakline);
      }

      }
    }


void cpt_transpuesto(int tnodo, int tpadres)
      {
/*Tranformar CPT a CPTree */

/*Extrayendo CPT a vector CPTREE */
     String vt;
     int tcol;
     int tfil;
     int tcpt;

/*Vamos a iniciar la var tcol en el token donde final donde debe terminar el conteo
esto es contt + vnodo.size();*/
     String corta;
     tfil = tnodo;
     tcol = tpadres;
     tcpt = tcol * tfil;
     Vector cptree;
     vt = new String();
     cptree = new Vector();

/*Copiandolos al vector cptree en el orden necesario para la traducci�n*/

     for(int i = 0; i < tcol; i ++)
     {
       for(int j = 0; j < tfil; j++)
       {
      int posv = i + j * tcol;
      vt = (String)(cpt.get((int) posv));
       cptree.add(vt);
      }

   }

   cpt.removeAllElements();

   for(int i = 0; i < cptree.size(); i ++ )
   {
     cpt.add(cptree.get(i));
   }

 }


void carga_var(String t)
   {varact.removeAllElements();
    int pt = varpad.indexOf(t);
    int pos = 1;
    for(int i = 0; i < pt ; i ++)
     {
        pos = vnodo2.indexOf(asterisco,pos);
        pos ++;
     }

    while(!(asterisco.equals(vnodo2.get(pos))) )
         {
         varact.add(vnodo2.get(pos));
         pos ++;
         if(pos > vnodo2.size())
         {
           break;
         }
         }

}


void carga_val()
    {
      valact.removeAllElements();
      for(int i = cvalores; i < nval + cvalores; i ++)
      {
        valact.add(cpt.get(i));
      }
      cvalores = cvalores + nval;
    }


String redondeo(String vt)
    {
     String dev = new String();
     String corta;
      double t = 0.0000;
/*Operacion para romper cadena en 5 caracteres 3 dig decimales y redondeo */

           char cade [];
           cade = new char[6];

           vt.getChars(0 , 6, cade , 0);

/*Redondeando digito en la cadena */
           int afec;
           int fsigue = 0;
           char car;
         for(int i = 5; i >= 0 ; i --)
         { car = cade[i];

          if((car == '6') || (car == '7') || (car == '8') || (car == '9') || (fsigue == 1))
           { fsigue = 0;
              if( i == 2 )
                {afec = 0;}
              else
                {afec = i -1;}

            switch(cade[afec])
             {
              case '1':
                cade[afec] = '2';
                i = 0;
                break;
              case '2':
                cade[afec] = '3';
                i = 0;
                break;
              case '3':
                cade[afec] = '4';
                i = 0;
                break;
              case '4':
                cade[afec] = '5';
                i = 0;
                break;
              case '5':
                cade[afec] = '6';
                i = 0;
                break;
              case '6':
                cade[afec] = '7';
                i = 0;
                break;
              case '7':
                cade[afec] = '8';
                i = 0;
                break;
               case '8':
                cade[afec] = '9';
                i = 0;
                 break;
               case '9':
                 cade[afec] = '0';
                 fsigue = 1;
                 break;
               case '0':
                 cade[afec] = '1';
                 i = 0;
                 break;
             }
            }
           else
           { i = 0; }
         }
           String cadena;
           cadena = new String(cade);
           corta = cadena.substring(0,5);

           t = Double.valueOf((String)corta).doubleValue();
           double v = 0.0;
           int gv = 0;
           v = (Math.rint(t * 10000))/10000;
           gv = (int)Math.rint(t * 10000);

           corta = String.valueOf(v);
           corta = convert_float(corta);

           cadena = corta;

           corta = cadena.substring(0,5);

     return corta;
    }

void add_token_posrec(int pos)
     {
      /*Convierte n�mero de error a un elemento v�lido para poder anexarlo
       al vector de error*/
       String tem;
       StringBuffer posb;
       posb = new StringBuffer();
       tem = new String();
       posb.append(pos);
       posrec.add(posb.toString());
      }

void add_token_postemp(int pos)
     {
     /*Convierte n�mero de error a un elemento v�lido para poder anexarlo
     al vector de error*/
     String tem;
     StringBuffer posb;
     posb = new StringBuffer();
     tem = new String();
     posb.append(pos);
     postemp.add(posb.toString());
     }


int  convert_S_entero(String entero)
     {
      int numero=Integer.parseInt(entero.trim());
      return numero;
     }


boolean reconoce_variable_reward(String naction)
       {
         /*Sobrecarga del m�todo reconoce variable, este m�todo solo
          es para definir si es v�lida , no incorpora la funci�n
          modif_var*/

         int ncar;
         char cade[];


         /*String que ser� devuelta */

          ncar = naction.length();
          cade = new char[ncar];
          naction.getChars(0, ncar, cade, 0);

           char car;
           boolean correct = true;

           for(int i = 0; i < biblioteca.size() ; i ++)
              {
                if(naction.equals((String)biblioteca.get(i)))
                {
                  correct = false;
                }
              }

              if(cade[ncar - 1] != ':')
               {
                 correct = false;
               }


             for (int i = 0; i < ncar; i++) {
             car = cade[i];

             if (i == 0) {
               if (! ( (Character.isLetter(car)) || (car == '_') || (car == '\"'))) {
                 i = ncar;
                 correct = false;
               }
             }
             else {

               if (! ( (Character.isLetterOrDigit(car)) || (car == '_') || (car == '\"') || (car ==':') )) {
                 i = ncar;
                 correct = false;
               }

             }
           }

           return correct;
         }


String varreward_var(String variable)
      {

   /*En caso de que una variable que pasa SPI sea v�lida para el pero no v�lida
   en SPUDD, la variable es modificada de tal forma que sea v�lida para SPUDD*/
   int ncar;
   char cade[];

   /*String que sera devuelta */
   String  naction = new String();

   /*Buffer temporal*/
   StringBuffer t = new StringBuffer();
   StringBuffer t2 = new StringBuffer();
   ncar = variable.length();
   cade = new char[ncar];

   /*Agregando cadena al buffer*/
  t.append(variable);
  t2.append(variable);

  variable.getChars(0, ncar, cade, 0);

  int ncar2 = t.length();
  int d = 0;

  for(int i = 0; i < ncar2 ; i ++)
  {
    if(t.charAt(i)  == ':' )
    {
      t2.deleteCharAt(i - d);
      d ++;
      }
  }

  naction = t2.toString();

  return naction;
      }


void inicia_reward(String reward)
     {
      carga_reward(reward);
      ttk = rewardm.size();
      conttr = 0;

      while( conttr < ttk )
          {
           funcion_reward();
           conttr ++;
          }

     rewardm.add(cpar);
     }


void funcion_reward()
    {

    if(acorchete.equals(rewardm.get(conttr)))
      {
      rewardf.add(apar);
      spudd.add(apar);
      spudd.add(espace);
      rewardm.remove(conttr);
      rewardm.add(conttr,asterisco);
      conttr ++;
      }

    if(reconoce_variable_reward((String)rewardm.get(conttr)))
      {
       String t = new String();
       t = varreward_var((String)rewardm.get(conttr));
       rewardf.add(t);
       spudd.add(t);
       spudd.add(espace);
       rewardm.remove(conttr);
       rewardm.add(conttr, asterisco);
       conttr ++;
       }

     if(reconoce_variable((String)rewardm.get(conttr)))
       {
        conttrm = rewardm.indexOf(acorchete, conttr);
        rewardf.add(apar);
        rewardf.add(rewardm.get(conttr));
        spudd.add(apar);
        spudd.add(espace);
        spudd.add(rewardm.get(conttr));
        spudd.add(espace);
        rewardm.remove(conttr);
        rewardm.add(conttr,asterisco);
        conttr ++;
        add_token_posrec(conttr);
        conttr = conttrm;
        mitad= 0;
        funcion_reward();
        int fin;
        fin = posrec.size() - 1;
        if(mitad == 0)
          {
           conttr = convert_S_entero( (String) posrec.get(fin));
           posrec.remove(fin);

           mitad = 1;

           if(asterisco.equals(rewardm.get(conttr)))
             {
              conttr = 0;
             }

           rewardf.add(cpar);
           spudd.add(cpar);
           spudd.add(espace);
           funcion_reward();

           }
         else{
             rewardf.add(cpar);
             spudd.add(cpar);
             spudd.add(espace);
             }
       }


                  if(reconoce_valor((String)rewardm.get(conttr)))
                  {
                    rewardf.add(rewardm.get(conttr));
                    spudd.add(rewardm.get(conttr));
                    spudd.add(espace);
                    rewardm.remove(conttr);
                    rewardm.add(conttr,asterisco);
                    conttr ++;
                  }

                 if(ccorchete.equals(rewardm.get(conttr)))
                 {
                   rewardf.add(cpar);
                   spudd.add(cpar);
                   spudd.add(espace);
                   rewardm.remove(conttr);
                   rewardm.add(conttr,asterisco);
                   conttr ++;
                   }

                 if(conttr < ttk)
                 {
                   if(ccorchete.equals(rewardm.get(conttr)))
                   {
                     rewardf.add(cpar);
                     spudd.add(cpar);
                     spudd.add(espace);
                     rewardm.remove(conttr);
                     rewardm.add(conttr,asterisco);
                     conttr ++;
                     }
                   }

                   if(conttr < ttk)
                   {
                     if(ccorchete.equals(rewardm.get(conttr)))
                     {
                       rewardf.add(cpar);
                       spudd.add(cpar);
                       spudd.add(espace);
                       rewardm.remove(conttr);
                       rewardm.add(conttr,asterisco);
                       conttr ++;
                       }
                     }
               }


void biblioteca() {
/*Crea la biblioteca inicial de tokens v�lidos para nuestro lenguaje*/

        biblioteca.add(unnormalized);
        biblioteca.add(reward);
        biblioteca.add(discount);
        biblioteca.add(tolerance);
        biblioteca.add(accion);
        biblioteca.add(nodo);
        biblioteca.add(valores);
        biblioteca.add(padre);
      }


boolean reconoce_valor(String nvalor) {
/*Reconoce si un token es v�lido para nuestro lenguaje, si no es v�lido
 devuelve el error correspondiente*/

        int ncar;
        /*int ncar2;*/
        int punto = 0;
        char cade[];

        char cade2[];

        ncar = nvalor.length();

        cade = new char[ncar];

        nvalor.getChars(0, ncar, cade, 0);

        char car;
        boolean correct = true;

        for (int i = 0; i < ncar; i++) {
             car = cade[i];
             if (! (Character.isDigit(car))) {
                  if (!((car == '.') || (car == '-'))) {
                        i = ncar;
                        correct = false;
                        }
                  if(car == '-')
                    {
                     if( i != 0 )
                       {
                        correct = false;
                       }
                     }
                   if(car == '.' )
                     {
                      punto ++;
                     }
                }
            }
        if(punto > 1)
          {
           correct = false;
          }

                 return correct;
            }


boolean reconoce_variable(String naction) {
/*Sobrecarga del m�todo reconoce variable*/
       int ncar;
       char cade[];
       ncar = naction.length();
       cade = new char[ncar];
       ncar = naction.length();
       naction.getChars(0, ncar, cade, 0);
       char car;
       boolean correct = true;

       for(int i = 0; i < biblioteca.size() ; i ++)
          {
            if(naction.equals((String)biblioteca.get(i)))
            {
              correct = false;
            }
          }

       for (int i = 0; i < ncar; i++) {
         car = cade[i];

         if (i == 0) {
           if (! ( (Character.isLetter(car)) || (car == '_'))) {
             i = ncar;
             correct = false;
           }
         }
         else {

           if (! ( (Character.isLetterOrDigit(car)) || (car == '_'))) {
             i = ncar;
             correct = false;
           }

         }
       }

       return correct;
     }


String convert_float(String num)
  {String convert;
    int ncar;
    convert = new String();
    char cade[];
     ncar = num.length();
     cade = new char[ncar];
     boolean Intg = true;
     int ct = 0;

     for(int i = 0; i < ncar ; i ++)
     {
       if(cade[i] != '0')
       {
         break;
       }
       ct ++;
     }

     num.getChars(ct, ncar, cade, 0);

     for(int i = 0; i < ncar ; i ++)
     {

       if(cade[i] == '.')
       {
         Intg = false;
         break;

       }

     }

    if(Intg)
    {
      convert = num + ".00000";
    }
    else
    {
      convert = num + "00000";
    }
    return convert;
  }




void load_reward(int val , int var)
  {
    /*Anexando la funci�n de reward en spudd */
    int cvar = 0;
    int cval = 0;
    /* reward */
    spudd.add(reward);
    spudd.add(espace);
    spudd.add(openpar);
    spudd.add(dreward.get(cvar));
    cvar ++;

    for( ; cvar < var ; cvar ++)
    {
      if( cvar > 1 )
      {
      spudd.add(tab);
      }
      spudd.add(tab);
      spudd.add(openpar);
      spudd.add(dreward.get(cvar));
      spudd.add(tab);
      spudd.add(openpar);
      spudd.add(dreward.get(cvar + val));
      spudd.add(closepar);
      spudd.add(closepar);
      spudd.add(breakline);
    }

   spudd.add(closepar);
  }
}

