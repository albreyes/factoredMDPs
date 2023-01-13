package tsp;
import java.io.*;
import java.util.*;
import java.awt.*;

/**
 * <p>Title: Analizador </p>
 * <p>Description: Realiza el an�lisis y configura el MDP SPI. </p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: Instituto de Investigaciones El�ctricas IIE  </p>
 * <p>Descripci�n: Realiza un an�lisis sencillo y agrega componentes
 * <p> necesarios para la traducci�n del MDP SPI a MDP SPUDD <p>
 * @author: Christian Guti�rrez Bravo
 * @version 1.0
 *
 */

public class Analizador {
  /*Tokens pertenecientes a nuestra gram�tica */

  final String nodo = "nodo:";
  final String valores = "valores:";
  final String padre = "padre:";
  final String accion = "accion";
  final String reward = "reward";
  final String discount = "discount";
  final String tolerance = "tolerance";
  final String normalized = "normalized";
  final String unnormalized = "unnormalized";
  final String function = "function";
  final String comillas ="\"";
  final String acorchete = "[";
  final String ccorchete = "]";
  final String apar = "(";
  final String cpar = ")";
  final String asterisco = "*";
 
  /*Definiendo errores*/
  final String msg =
      "La acci�n definida no contiene aprendizaje                      ";
  final String error0 =
      "No se puede realizar la traducci�n el archivo esta en blanco    ";
  final String error1 =
      "Error l�xico                                                    ";
  final String error2 =
      "El aprendizaje en la acci�n fue nulo                           ";
  final String error3 =
      "Definici�n del nombre de la acci�n no es v�lida                 ";
  final String error4 =
      "Definici�n del variable nodo incorrecto                         ";
  final String error5 =
      "Definici�n del valor no es correcto                             ";
  final String error6 =
      "Definici�n del nombre del nombre del nodo padre no es v�lido    ";
  final String error7 =
      "Elemento dentro del CPT contiene un valor no v�lido             ";
  final String error8 =
      "El CPT no esta completo faltan elementos                        ";
  final String error9 =
      "El CPT sobrepasa el n�mero de elementos                         ";
  final String warning =
      "El MDP en cuenta con acciones sin aprendizaje                   ";
  final String completo =
      "El an�lisis ha sido completado satisfactoriamente               ";
  final String error11 =
      "Falta definici�n normalized o unnormalized                      ";
  final String error12 =
      "El MDP no cumple con el m�nimo de elementos requeridos revise   ";
  final String error13 =
      "El MDP esta incompleto revise                                   ";
  final String error14 =
      "La sem�ntica en el MDP spudd es incorrecta revise               ";
  final String error15 =
      "El valor de discount no es v�lido                               ";
  final String error16 =
      "El valor de tolerance no es v�lido                              ";
  final String error17 =
      "La normalizaci�n en el token es incorrecta                      ";
  final String error18 =
      "El token unnormalized ha sido definido mas de una vez           ";
  final String error19 =
      "El token reward ha sido definido mas de una vez                 ";
  final String error20 =
      "El token discount ha sido definido mas de una vez               ";
  final String error21 =
      "El token tolerance ha sido definido mas de una vez              ";
  final String error41 =
      "El token function ha sido definido mas de una vez              ";
  final String error22 =
      "El token inicial debe ser unnormalized o accion verifique       ";
  final String error23 =
      "El elemento no se encuentra definido en el MDP                  ";
  final String error24 =
      "Error el CPT sobrepasa el l�mite de total de memoria            ";
  final String error25 =
      "Error el CPT sobrepasa el l�mite de la acci�n                   ";
  final String error26 =
      "Error el CPT sobrepasa el l�mite del nodo                       ";
  final String error27 =
      "Se esperaba definici�n de accion:                               ";
  final String error28 =
      "Se esperaba definici�n de nodo: o nueva acci�n                  ";
  final String error29 =
      "Se esperaba definici�n de valores:                              ";
  final String error30 =
      "Se esperaba definici�n de padre:                                ";
  final String error31 =
      "Se esperaba definici�n de reward                                ";
  final String error32 =
      "Se esperaba definici�n de tolerance                             ";
  final String error33 =
      "Definici�n de variable no v�lida                                ";
  final String error34 =
      "Falta definir un valor para padre: verifique                    ";
  final String error35 =
      "Falta definir un valor para nodo: verifique                     ";
  final String error36 =
       "El CPT sobrepasa el l�mite reward                              ";
   final String error37 =
       "No se encuentra ning�n nodo definido                           ";
   final String error38 =
       "El CPT no cuenta con elementos                                 ";
   final String error39 =
       "El CPT contiene mas elementos de los que han sido definidos    ";
   final String error40 =
       "Se esperaba definici�n de function                             ";
   final String error42 =
       "La funci�n de reward no esta definida correctamente            ";
   final String error43 =
       "Alg�n valor o variable en reward no son aceptados              ";
   final String error44 =
       "El �rbol de reward esta incompleto                             ";
   final String error45 =
       "No se encontr� la funci�n de reward                            ";
   final String error46 =
       "El valor no es v�lido > 1                                      ";
   final String error47 =
       "El descuento puede ser un valor < 1                            ";
   final String error48 =
       "El peso de la acci�n no contiene un valor v�lido               ";
   final String error49 =
       "El archivo no contiene informaci�n                             ";
   final String error50 =
       "La funci�n de reward no cuenta con el n�mero m�nimo de tokens  ";
   final String error51 =
       "Falta que cierre o abra un corchete verifique                  ";
   final String error52 =
       "El token no pertenece al lenguaje de reward                    ";
   final String error53 =
       "El token inicial en la funci�n de reward debe ser [            ";
   final String error54 =
       "Se esperaba una variable de reward                             ";

   /*Variables auxiliares*/
  int n;
  int i;
  int te;
  int countt = 0;
  int countts = 0;
  int tmem = 0;
  int countact = 0;
  int sigact = 0;
  int terror = 0;
  int tfil;
  int tcol;
  int v;
  int tmesg;
  int ttk = 0;
  int conttr = 0;
  int conttrm = 0;
  int a = 1;
  int paradd = 0;
  int mitad  = 0;


  boolean complete = false;

  /*Variables auxiliares*/
  Vector actions;
  Vector nodop;
  Vector spudd;
  Vector error;
  String nameactspi;
  String actemp;
  String errores;
  Vector biblioteca;
  Vector define_var;
  Vector val_var;
  Vector variables;
  Vector mesg;
  Vector dreward;
  Vector varnec;
  Vector rewardm;
  Vector rewardf;
  Vector posrec;
  Vector tpar;
  Vector varp;

  /*Vector que contiene el alfabeto*/
  Vector alfatoken;

  /*Vector en el que se encuentra el archivo separado en tokens*/
  Vector memoria;
  String token;
  StringTokenizer tokens;

  /*Devuelve si el an�lisis se realizo */
  boolean complete_a;

  public Analizador(String MDPspi) {
    varnec = new Vector();
    mesg = new Vector();
    alfatoken = new Vector();
    memoria = new Vector();
    token = new String();
    /*Construyendo tokens*/
    tokens = new StringTokenizer(MDPspi);

    /*********************/
    spudd = new Vector();
    actions = new Vector();
    nodop = new Vector();
    error = new Vector();
    biblioteca = new Vector();
    val_var = new Vector();
    define_var = new Vector();
    complete_a = false;
    variables = new Vector();
    dreward = new Vector();
    rewardm = new Vector();
    rewardf = new Vector();
    posrec = new Vector();
    tpar = new Vector();
    varp = new Vector();
  }

  void analizer(String MDPspi) {
    int varbs = 0;
    Vector vfather;
    Vector vnodo;
    int padret;
    boolean enda = false;

    /*M�todo que analiza un c�digo expresado en tokens atrav�s
    de un vector el an�lisis se realiza en ord�n sint�ctico, l�xico y sem�ntico*/

    /*Cuando exista un error que impida que el Programa pueda seguir analizando
     se detiene asignando a enda(final del an�lisis), ya que de otra forma el
     analizador generar�a n errores y/o provocar�a que tronar� la aplicaci�n*/

end:
   if(enda == false)
    {

    /*Mediante el siguiente m�todo se carga el String al vector memoria*/
    load_vector_memoria();

    if(memoria.size() == 0 )
     {
       terror ++;
       add_token_error(0);
       error.add(error0);
       error.add(" ");
       enda = true;
       break end;
     }

   /*Siempre normalizar los valores del CPT */
    normalizar();

   /*Verificando que el MDP sea inicializado correctamente*/
    if(!iniciar())
     { enda = true;
       break end;
     }

   /*Cargando la biblioteca de tokens v�lida para el an�lisis l�xico*/
    biblioteca();

    /*Cargando semantica para definir el orden de los elementos del MDP*/
    semantica();

    /*-------------------------------------*/
    /*Analizador de Gram�tica libre de contexto*/
    /*-------------------------------------*/

   /*Verificando que exista discount y/o tolerance de otra forma agregando valores
     de descuento y tolerancia por default*/

    add_des_tol();

    /*Definiendo y cargando el n�mero total de tokens*/
      tmem = memoria.size();

    /*Verificando que el MDP cumpla con el n�mero minimo de tokens para el an�lisis*/
    if (!min_tokens()) {
      enda = true;
      break end;
    }

    /*Realizando analisis l�xico inicialmente*/
    if(!(lexic_analizer_spudd()))
       {
        enda = true;
        break end;
       }

    /*Verificando que los elementos unnormalized, discount , tolerance ,
      reward , function s�lo hayan sido declarados una sola vez*/

    if(!verific_def())
    {
      enda = true;
      break end;
    }

    /*Verificando que la sem�ntica general se cumpla que existan todos los elementos
    en el orden correcto*/

    if(!(semantic_analizer()))
        {
        enda = true;
        break end;
        }

    /*Analizando acciones en el archivo */
     int cactions = 0;

    /*Definiendo cuantas acciones son definidas*/
      cactions = count_var(accion);

    /********************************/
     boolean ponda = false;


    /*Cuando una acci�n no es bien definida el analisis de esta acci�n es truncado
     y se pasa a una nueva atrav�s de la etiqueta endp*/

endp:

    /*Analizando sint�ctica - sem�ntica y lexicamente cada acci�n */
    /*Desde la acci�n 0 hasta la �ltima analizar*/
     for (int ca = 0; ca < cactions; ca++) {

     /*El primer token de una acci�n es la definici�n de la propia
     acci�n*/

      valid_token(accion, countt, error27);
      countt++;

     /*countt  es nuestro apuntador de tokens para posicionarnos dentro del vector*/

     /*Verificando nombre de la acci�n v�lido*/
      if(!reconoce_accion((String) memoria.get(countt),error3))
      {
        enda = true;
        break end;
        }

      countt++;

     /*Reconociendo el peso de una acci�n*/

     if(reconoce_valor((String)memoria.get(countt)))
        {
         reconoce_valor((String)memoria.get(countt),error48);

     /*Reconociendo valor */

         countt ++;
         ponda = true;
        }

     /*Reconociendo nodo o ausencia de aprendizaje */

        while (accion.equals(memoria.get(countt))) {
          ca ++;
     /*Si se reconocio un valor de peso entonces retrocedemos nuestro contador
      para poder definir el nombre de la acci�n que no contiene aprendizaje  */

        if(ponda)
         {
            countt --;
         }

        countt--;

      /*mesg contiene el vector de mensajes que son enviados despu�s del an�lisis,
      mensajes que hacen referencia a una acci�n que realizo el analizador para que
      el usuario lo tenga en cuenta pero que no es precisamente un error*/

          mesg.add(msg);
          mesg.add(memoria.get(countt));
          tmesg ++;

      /*Regresando a la posici�n del sig token para continuar el an�lisis*/

          countt ++;
          countt ++;

     /*Cuando la acci�n tuvo un peso se requiere actualizar una posici�n + para que
      se coloque el contador en la posici�n adecuada*/

          if(ponda)
          {
            countt ++;
            ponda = false;
          }

       /*Si es la ultima acci�n se termina el an�lisis y se indica que el an�lisis
        ha concluido saltando a endp*/

        if(ca == cactions)
          {
            continue endp;
          }

       /*Estando en la acci�n reconocemos que la acci�n este definida con un
           nombre v�lido para acci�n*/

          if(!reconoce_accion( (String) memoria.get(countt), error3))
          {
            enda = true;
            break end;
          }

          countt++;


        /*Reconociendo valor de peso de la acci�n*/

          if(reconoce_valor((String)memoria.get(countt)))
             {
             ponda = true ;
             countt ++;
             }

      }

    /*Opci�n en la que la acci�n no fue aprendida pero la accion es la ultima
     para saber que es la ultima acci�n se define con el token reward que define
     que se terminaron las acciones   */

    if (reward.equals(memoria.get(countt))) {

       ca ++;

       if(ponda)
       {
         countt --;
       }

       countt --;
       tmesg ++;
       mesg.add(msg);
       mesg.add(memoria.get(countt));
       countt ++;
       if(ponda)
       {
         countt ++;
         ponda = false;
        }
       continue endp;
     }

      /*Mientras existan nodos realizar el mismo an�lisis*/
      int cnodo = 0;

      /*sigact define donde termina esta acci�n*/
      sigact = memoria.indexOf(accion, countt);

      /*Verificando que exista una siguiente acci�n*/
      if (sigact < 0) {
        sigact = tmem;
      }

      /*Definiendo con cuantos nodos cuenta nuestra acci�n*/

      for (i = countt; i < sigact; i++) {
       actemp = (String) memoria.get(i);

        if (nodo.equals( (String) actemp)) {
          cnodo++;
        }
      }

      /*En caso de que no tenga nodos se env�a un mensaje de error*/

      if(cnodo == 0)
      {if(!((accion.equals(memoria.get(countt)))||(reward.equals(memoria.get(countt)))))
        {
        terror ++;
        add_token_error(countt);
        error.add(error37);
        error.add(memoria.get(countt));
        enda = true;
        break end;}

      }

       /*Verificando cada nodo de la acci�n actual*/
       for (int cn = 0; cn < cnodo; cn++) {
          if(!valid_token(nodo, countt, error28))
            {
             enda = true;
             break end;
            }

            countt ++;

           /*Verificando nombre del nodo v�lido */
          if(!reconoce_variable( (String) memoria.get(countt), error4))
             {

              enda = true;
              break end;
             }

           /*reconociendo las variables inmersas en el archivo MDP y anexandolas
           al vector para pasarselas al archivo final        */

           boolean novar = false;


           if(!ex_var((String)memoria.get(countt)))
             {
              novar = true;
              }

           countt++;

          /*Verificando sig token valores */
           if(!valid_token(valores, countt, error29))
             {
               enda = true;
               break end;
             }

             countt++;

          /*Definiendo vectores father y nodo para contabilizar y obtener las
          variables correspondientes*/

          vfather = new Vector();
          vnodo = new Vector();

          /*Definiendo los valores de nodo*/

          /*Verificando cuantos padres contiene la acci�n*/
          /*padret define hasta donde se reconocen valores para la variable nodo*/


       padret = memoria.indexOf(padre, countt);

      while ((countt < padret) || (reconoce_variable((String)memoria.get(countt)))) {
            vnodo.add( (String) memoria.get(countt));
            if(!reconoce_variable( (String) memoria.get(countt), error33))
              {
               enda = true;
               break end;
              }

          /*Anexando la variable al vector que contiene las variables que ser�n pasadas
          al archivo final*/

         if(novar)
           {val_var.add(memoria.get(countt));}

            countt ++;
           }

         /*Separando la variable en el vector*/

          if(novar)
           {
            val_var.add( (String) "*");
           }

         /*Contabilizando las variables padre */
         int lip = memoria.indexOf(nodo,countt);

         if(lip == -1)
           {
            lip = memoria.indexOf(accion,countt);
           }

         if(lip == -1)
           {
            lip = memoria.indexOf(reward,countt);
           }

         int padres = 0;

         for(int k = countt ; k < lip ; k ++)
            {
            if(padre.equals(memoria.get(k)))
              {
               padres ++;
              }
            }


            varp.add(asterisco);

            int k = countt;

            for(int a = 0 ; a < padres ; a ++ )
               {
                k = k + 3;

                while (!((reconoce_valor( (String) memoria.get(k))) || (padre.equals((String)memoria.get(k)))))
                   {
                   varp.add(memoria.get(k));
                   k ++ ;
                   }
                   varp.add(asterisco);
                }



     int t = 1;
     int c = 0;

     for(int g = 1; g < varp.size(); g ++)
        {

          while(!asterisco.equals((String)varp.get(g)))
                {

                  c ++;
                  g ++;
                 }
          t = c * t;
          c = 0;


        }




        /*Reconocimiento del token padre para inicializar la variable con sus valores*/

        if(!valid_token(padre, countt, error30))
          {
          enda = true;
          break end;
          }

          countt++;

         /*Reconociendo la variable padre */

        for( int pa = 0; pa < padres ; pa ++)
        {


          if(!reconoce_variable( (String) memoria.get(countt), error6))
          {
            enda = true;
            break end;
          }

          novar = false;

          /*Si la variable ya existe ya no se toma en cuenta para anexarla al vector de
           variables que se anexaran*/

        if(!ex_var((String)memoria.get(countt)))
        {
           novar = true;
        }

        countt++;

        /*Reconociendo valores de la variable padre*/
        if(!valid_token(valores, countt, error29))
        {
          enda = true;
          break end;
        }

        countt++;

        /*Validando las variables padre */
         while (reconoce_variable( (String) (memoria.get(countt))))
               {
               /*Reconociendo la variable padre */
               if(!reconoce_variable( (String) memoria.get(countt), error6))
               {
                 enda = true;
                 break end;
               }

               /*Si no existe se anexa al vector de variables*/
               if(novar)
               {
                 val_var.add(memoria.get(countt));
               }

               countt++;
               vfather.add( (String) memoria.get(countt));
             }

             /*Anexando elemento que define una separaci�n en el vector de
              las variables*/

             if(novar)
             { val_var.add((String)"*");
             }

             countt ++;


           }

           countt --;

        /*Definiendo el tama�o del CPT */
        /*tcol define el total de elementos padres*/
        /*tfil define el total de elementos nodo*/

        tcol = t;
        tfil = vnodo.size();

        varp.removeAllElements();
        /*calcpt define el tamaño del CPT*/

        int calcpt= 0;
        /*countem es una variable aux para recorrer el vector memoria*/
        int countem = countt;

        /*Verificando cuantos elementos contiene el CPT*/


        while(reconoce_valor((String)memoria.get(countem)))
        {
          calcpt ++;
          countem ++;
        }

        int comp = 0;

        /*Verificando que el tama�o del CPT definido de acuerdo a las variables sea el mismo
         que el le�do */

        if(calcpt > (tcol * tfil))
        {
          comp = calcpt - ( tcol * tfil);

          if(comp == tfil)
          {terror ++;
           add_token_error(countt);
            error.add(error34);
            error.add(memoria.get(countt));
          }

          if(comp == tcol)
          {terror ++;
            add_token_error(countt);
            error.add(error35);
            error.add(memoria.get(countt));
          }
          if((comp != tcol) && (comp != tfil))
          {
            terror ++;
            add_token_error(countt);
            error.add(error39);
            error.add(memoria.get(countt));
          }
           enda = true;
           break end;

        }

        /*Posicionando el CPT en memoria*/
        int tcpt = (tfil * tcol) + countt;


        /*Reconociendo que el CPT no sea mayor al n�mero de elementos del vector
         memoria*/

        if(tcpt > tmem)
        {
          add_token_error(countt);
          error.add(error24);
          error.add(memoria.get(tcpt));
          terror ++;
          enda = true;
          break end;
        }

        /*Verificando que el CPT no este fuera de la acci�n definida*/

        int sigcod = memoria.indexOf(accion,countt);

        if(sigcod == countt )
            { terror ++;
              add_token_error(countt);
              error.add(error38);
              error.add(memoria.get(countt));
              enda = true;
              break end;
             }


        if((tcpt > sigcod) && (sigcod != - 1))
        {
          add_token_error(countt);
          error.add(error25);
          error.add(memoria.get(tcpt));
          terror ++;
          enda = true;
          break end;
        }


        /*Verificando el caso de que la acci�n sea la �ltima, que el CPT no sobrepase
         el token de reward*/

        int sigrew = memoria.indexOf(reward,countt);
        if(sigrew == countt)
           { terror ++;
             add_token_error(countt);
             error.add(error38);
             error.add(memoria.get(countt));
             enda = true;
             break end;
           }


         if(tcpt > sigrew)
        {
          add_token_error(countt);
          error.add(error36);
          error.add(memoria.get(countt));
          terror ++;
          enda = true;
          break end;
        }


        int signod = memoria.indexOf(nodo,countt);
        if(signod == countt)
           { terror ++;
             add_token_error(countt);
             error.add(error38);
             error.add(memoria.get(countt));
             enda = true;
             break end;
            }

        if((tcpt > signod) && (signod != - 1))
        {
          add_token_error(countt);
          error.add(error26);
          error.add(memoria.get(tcpt));
          terror ++;
          enda = true;
          break end;
        }

        /*Reconociendo los valores del CPT */
        for (; countt < tcpt; countt++) {
          reconoce_valor( (String) memoria.get(countt), error7);
        }
      }
    }

/*Verificando la recompensa de los nodos */
    valid_token(reward, countt, error31);
    countt++;

/*Verificando que la definicion del regalo sea correcta*/
    valid_token(function, countt, error40);
    countt ++;

String rew_S = "";

/*Verificando exista reward*/
if(discount.equals(memoria.get(countt)))
    {
     add_token_error(countt);
      error.add(error45);
      error.add(reward);
      terror ++;
      enda = true;
      break end;
    }

/*Obteniendo la funcion de reward en una cadena */
while(!discount.equals(memoria.get(countt)))
     {
       rew_S = rew_S +  memoria.get(countt) + " " ;
       countt ++;
     }

/*Validando la funci�n de reward*/

     inicia_reward(rew_S);

/*Verficando que exista un descuento y que sea correcto*/
    valid_token(discount, countt, error2);
    countt++;

/*Verificando que el valor de descuento sea correcto*/
    reconoce_valor((String) memoria.get(countt), error5);
    valid_discount((String)memoria.get(countt));
    countt++;

/*Verificando que exista un valor de tolerancia*/
    valid_token(tolerance, countt, error32);
    countt++;

/*Verificando que el valor de tolerancia sea correcto*/
    reconoce_valor((String) memoria.get(countt), error5);
    valid_tolerance((String)memoria.get(countt));
    countt++;
    }

/*Verificando que el an�lisis dela archivo haya sido completado*/
   verific_complete();

/*Desplegando los errores*/
   display_errors();

/*Si no existen errores hasta ahora en el an�lisis continuamos
    con el proceso*/
if(error.size() == 0 )
   {

     /*Anexando las variables como definici�n en el archivo*/
     load_variables();

     /*Completando nodos para que el MDP este completo */
     verifica_nodos();
   }

  }



public boolean verific_complete()
  {
    /*Verifica que el an�lisis haya sido completado hasta el final
     y que no existan errores en el archivo*/
    boolean comp = false;

    if((countt == tmem)&&(terror == 0))
    {
      comp = true;
    }
    return comp;
  }


void normalizar()
  {
    /*Anexa el token unnormalized en caso de que el archivo no lo contenga*/

    if(accion.equals(memoria.get(0)))
    {
      memoria.add(0,unnormalized);
    }

  }


void verifica_nodos()
  {
    /*Verifica que en cada acci�n se evaluen todos los valores de las variables
     en caso de que el archivo venga incompleto este m�todo completa anexando
     un CPT identidad que no realiza ninguna acci�n, pero es v�lido para que el
     SP SPUDD pueda realizar su tarea*/

    int c;
    int act;
    int lims = 0;
    int n = 0;
    int vnc;
    int topac = 0;
    int ct = 0;

    /*Copiando al vector que contiene todas las variables*/
    for(int i = 0 ; i < define_var.size(); i ++)
    {
      /*Obteniendo variable*/
      varnec.add(define_var.get(i));
      /*Dejando espacio para verificar que exista*/
      varnec.add(" ");
    }

    int nactions;

/*Contabilizando acciones del archivo*/
    nactions = count_var(accion);

/*Obteniendo la posici�n inicio de una acci�n en el archivo*/
    lims = memoria.indexOf(accion , lims);
    lims ++;

    for(n = 0 ; n < nactions ; n ++ )
    {

     ct = lims;

    /*Definiendo el final de la acci�n*/
    lims = memoria.indexOf(accion , lims);

    /*Si es la �ltima acci�n el tope es reward*/
    if(lims == -1)
    {
      lims = memoria.indexOf(reward , 0);
    }

    lims ++;

    /*Iniciando an�lisis en acci�n ct lim inicial hasta lims lim final*/

    for( i = ct ; i < lims ; i ++)
    {
     /*Definiendo variable necesarias*/
      vnc = 0;

      /*Evaluando cuantas variables de las necesarias se encuentran en la
       acci�n(cuando se encuentra una variable necesaria se anexa un * que
       significa que ha sido encontrada, se avanzan dos posiciones una la var
       y otra la posici�n que verifica que ha sido encontrada a( * )*/

      for( ; vnc < varnec.size() ; )
      {
        if((memoria.get(i)).equals(varnec.get(vnc)))
        {

          if((memoria.get(i - 1)).equals(nodo))
          {
            vnc++;
            varnec.remove(vnc);
            varnec.add(vnc, "*");
          }
        }
        else
        {
          vnc ++;
        }
        vnc ++;

      }

    }

   int val = 0;
   String var;
   var = new String();
/*Analizando cu�les variables no fueron evaluadas en la acci�n*/

   for( i = 0; i < varnec.size() ; i ++)
   {
    var =(String) varnec.get(i);
    val = i ;
    i ++;

/*Dividiendo entre 2 para quedar en la posici�n correcta 0 = 0, 2/2 = 1,4/2 = 2*/
    if( val > 0)
    {
      val = val / 2 ;
    }

/*Si no contiene * significa que no fue evaluada porque no existe*/

    if(varnec.get(i) != "*" )
    {
      /*Anexando evaluaci�n de variable*/

      lims --;
      memoria.add(lims,nodo);
      lims ++;
      memoria.add(lims,var);
      lims ++;
      memoria.add(lims,valores);
      lims ++;

     int inv = 0;
      if(val == 0 )
      {
        inv = 0;
      }

/*Buscando la variable en el vector de variables definidas*/

      for(int j = 0; j < val; j ++ )
      {
        inv = val_var.indexOf("*",inv);
        inv ++;
      }

      int sigv = val_var.indexOf("*",inv);

/*Anexando los valores de la variable*/

      for(int x = inv; x < sigv ; x ++ )
      {
      memoria.add(lims, val_var.get(x));
      lims ++;
      }
/*Anexando la variable padre y sus valores de la misma forma*/
      memoria.add(lims ,padre);
      lims ++;
      memoria.add(lims ,var);
      lims ++;
      memoria.add(lims ,valores);
      lims ++;

      int mn = 0;
      for(int x = inv; x < sigv ; x ++ )
      {
      memoria.add(lims ,val_var.get(x));
      lims ++;
      mn ++;
     }

/*Anexando el CPT que contiene una matriz identidad para que no se realize
  ninguna acci�n pero que el archivo este completo para el an�lisis en
 SPUDD*/

     for(int l = 0; l < mn ; l ++)
     {
       for(int fl = 0; fl < mn ; fl ++)
       {
         if(fl == l)
         {
           memoria.add(lims ,"1.0000");
         }
         else
         {
           memoria.add( lims ,"0.0000");
         }
       lims ++;
       }
     }
      lims ++;
    }

   }
  lims ++;


   /*Limpiar varnec para un nuevo an�lisis*/
   for( i = 1; i < varnec.size(); )
   {
     varnec.remove(i);
     varnec.add(i," ");
     i = i + 2;
   }
  }

  }


boolean ex_var(String var)
  {
    /*Recolecta las variables inmersas dentro del archivo, verifica que la
     variable no haya sido reconocida y despu�s la anexa a un vector que
     donde son guardadas*/

    boolean existe = false;
    String v;
    int ctem = countt + 2 ;
    v = new String();
    int inic = 0;
    int para = 0;
    int tam = var.length();
   if(var.endsWith("_prime"))
      {
        tam = tam - 6;
        v = var.substring(0,tam);
        var = v;
        memoria.remove(countt);
        memoria.add(countt, v);
      }

    for(int i = 0; i < define_var.size() ; i ++)
    {
      if(var.startsWith((String)define_var.get(i)))
      {
        existe = true ;
        para = i ;
        i = define_var.size();
      }
    }


    if(!existe)
    {
      define_var.add((String)var);
    }

 return existe;

  }


void load_variables()
  {
    /*Anexa las variables que han sido reconocidas durante el an�lisis al
     archivo*/

    int j = 0;
    int v = 0;
    /*Verificando que el vector no este vac�o*/
    if(memoria.size() > 0 )
    {

    if(unnormalized.equals((String)memoria.get(0)))
    {
     /* memoria.add(0 , unnormalized );*/
     }
     else
     {
       memoria.add(0 , normalized);
     }

     /*Anexando variables al archivo*/

    if(val_var.size() > 0 )
    {

     for(int i = 0; i < define_var.size() ; i ++)
       {
         memoria.add(v,"variables");
         v ++;
         variables.add("variables");
         variables.add(define_var.get(i));
         memoria.add(v , define_var.get(i));
         v ++;

         while((!((String)"*").equals(val_var.get(j))) && (j < val_var.size()))
         {
           variables.add(val_var.get(j));
           memoria.add(v , val_var.get(j));

           j ++;
           v ++;

         }
          j ++;
       }
     }
    }
  }


boolean lexic_analizer_token(String l_a) {
   /*Realiza el an�lisis l�xico de un token, devuelve
    si fue correcto o no*/
    boolean acept = false;

   /*Reconociendo que pertenezcan a los tokens v�lidos
    del lenguaje, que sean un valor o una variable v�lida o un elemento
    del reward function*/

    for (int i = 0; i < biblioteca.size(); i++) {
      if ((l_a.equals((String)biblioteca.get(i)))) {
        acept = true;
      }
    }

    if (reconoce_variable(l_a))
    {
       acept = true;
    }

    if(reconoce_valor(l_a))
    {
      acept = true;
    }

    if(reconoce_t_reward(l_a))
    {
      acept = true;
    }

    return acept;
  }


boolean reconoce_t_reward(String naction)
{
    /*Sobrecarga del m�todo reconoce variable para reconocer
     elementos del reward function*/
      int ncar;
      char cade[];
      ncar = naction.length();
      cade = new char[ncar];
      ncar = naction.length();
      naction.getChars(0, ncar, cade, 0);
      char car;
      boolean correct = true;

/*No puede ser un token de las palabras que son utilizadas en el archivo
para definir alg�n elemento ( palabras reservadas) */
      for(int i = 0; i < biblioteca.size() ; i ++)
         {
           if(naction.equals((String)biblioteca.get(i)))
           {
             correct = false;
           }
         }

/*El primer caracter deve ser [ _ = (  � una letra*/
      for (int i = 0; i < ncar; i++) {
        car = cade[i];

        if (i == 0)
        {
          if (! ( (Character.isLetter(car)) || (car == '[') || (car == '_') || (car == '=' ) || (car == '('))) {
            i = ncar;
            correct = false;
          }
        }
        else {
/*Despu�s del primer caracter puede tener digitos, letras, - , _ , : , ( , ) [ , ] , . ,*/
          if (! ( (Character.isLetterOrDigit(car)) || (car == '-') || (car == '_') || (car == ':') || (car == '.') || (car == '/') || ( car == '[') || (car == ']') || (car == '(') || (car == ')') || (car == '=') || (car == ',') )) {
            i = ncar;
            correct = false;
          }

        }
      }

      return correct;
    }


boolean verific_def()
  {

/*Verifica que solo exista una definici�n de reward , discount , tolerance ,
     unnormalized , function */

   boolean correct = true;
   int a = 0;
   int b = 0;
   int c = 0;
   int d = 0;
   int e = 0;


for(int i = 0; i < memoria.size() ; i ++)
   {
   if(biblioteca.get(0).equals(memoria.get(i)))
      {
        a ++;
      }

      if(biblioteca.get(1).equals(memoria.get(i)))
      {
        b ++;
      }

      if(biblioteca.get(2).equals(memoria.get(i)))
      {
        c ++;
      }

      if(biblioteca.get(3).equals(memoria.get(i)))
      {
         d ++;
      }

      if(biblioteca.get(4).equals(memoria.get(i)))
      {
        e ++;
      }

   }

    if( a > 1 )
    {
      correct = false;
      terror ++;
      add_token_error(countt);
      error.add(error18);
      error.add(unnormalized);
    }

    if( b > 1 )
     {
       correct = false;
       terror ++;
       add_token_error(countt);
       error.add(error19);
       error.add(reward);
     }

     if( c > 1 )
     {
       correct = false;
       terror ++;
       add_token_error(countt);
       error.add(error20);
       error.add(discount);
     }

     if( d > 1 )
     {
       correct = false;
       terror ++;
       add_token_error(countt);
       error.add(error21);
       error.add(tolerance);
     }

     if( e > 1 )
     {
       correct = false;
       terror ++;
       add_token_error(countt);
       error.add(error41);
       error.add(function);
     }


    return correct;
}


void add_des_tol()
  {
   /*A�ade valores por default de descuento y/o tolernacia en caso de que no se
    encuentren*/

    int dis;
    int tol;
    dis = memoria.indexOf(discount , 0);
    tol = memoria.indexOf(tolerance , 0);

    if(tol == - 1)
    {
      memoria.add(tolerance);
      memoria.add("0.000001");
    }
    if(dis == - 1 )
    {
      memoria.add((memoria.size() - 2),discount);
      memoria.add((memoria.size() - 2), "0.900000");
    }

  }


boolean lexic_analizer_spudd()
  {
/*Verificando que todos los tokens pertenezcan al lenguaje*/

   boolean correct = true;

    for(int i = 0; i < memoria.size(); i ++)
    {
      if(!(lexic_analizer_token((String)memoria.get(i))))
      {
        correct = false;
        terror++;
        add_token_error(countt);
        error.add(error1);
        error.add(memoria.get(i));
      }
   }

    return correct;
  }


String genera_action(String action_nv)
  {
    /*Gener� un nombre v�lido para la acci�n , en caso de que el nombre sea
     num�rico*/

    String var_act;
    int i;
    var_act = new String();
    var_act = "act";
    i =(int) Math.random() * 9;
    var_act = var_act + dig_to_S(i) + action_nv;

  return var_act;
  }

String dig_to_S(int dig)
  {String S;
    S = new String();
    switch(dig)
    {
      case 0 :
        S = "0";
        break;
      case 1 :
        S = "1";
        break;
      case 2 :
        S = "2";
        break;
      case 3 :
        S = "3";
        break;
      case 4 :
        S = "4";
        break;
      case 5 :
        S = "5";
        break;
      case 6 :
        S = "6";
        break;
      case 7 :
        S = "7";
        break;
      case 8 :
        S = "8";
        break;
      case 9 :
        S = "9";
        break;
    }
    return S;
  }

boolean valid_discount(String discounta)
  {
    /*Validando el valor de descuento*/

    boolean correct = true;
    char cade [];
        cade = new char[1];

        discounta.getChars(0 , 1, cade , 0);

        if(cade[0] != '0')
        {
          terror ++;
          add_token_error(countt);
          error.add(error15);
          error.add(discounta);
          correct = false;
        }

   return correct;
  }


boolean valid_tolerance(String tolerancea)
    {
     /*Validando valor de tolerancia*/

      boolean correct = true;
      char cade [];
          cade = new char[1];

          tolerancea.getChars(0 , 1, cade , 0);
          if(cade[0] != '0')
          {
            terror ++;
            add_token_error(countt);
            error.add(error16);
            error.add(tolerancea);
            correct = false;
          }

     return correct;
    }


boolean iniciar()
  {/*Verificando que el inicio de nuestro MDP sea el primer token
     de lo contrario se genera un error*/

    boolean correct = false;
    countt = memoria.indexOf(unnormalized, 0);
    if(countt == 0)
    {
      correct = true;
      countt ++;
    }
    else
      {
       if(countt == - 1)
        {
            countt = memoria.indexOf(accion, 0);
            if(countt == 0)
              {correct = true;

              }
            if(countt == -1)
            {
              countt = 0;
            }

        }
       if(correct == false)
       {

       if(memoria.size() == 4)
        {
         terror ++;
         add_token_error(countt);
         error.add(error49);
         error.add(" ");
        }
        else
        {
          terror ++;
          add_token_error(0);
          error.add(error22);
          error.add(memoria.get(0));

        }
       }
      }

    return correct;
  }


  void biblioteca() {
  /*Crea la biblioteca inicial de tokens v�lidos para nuestro lenguaje*/
    biblioteca.add(unnormalized);
    biblioteca.add(reward);
    biblioteca.add(discount);
    biblioteca.add(tolerance);
    biblioteca.add(function);
    biblioteca.add(accion);
    biblioteca.add(nodo);
    biblioteca.add(valores);
    biblioteca.add(padre);
  }


boolean semantic_analizer()
  {/*Verifica que existan todos los elementos del
     alfabeto y en el orden correcto*/

    boolean correct = true;
    int i = 0;
    int j = 0;
    int a = 0;
    while((memoria.size() > i) && (alfatoken.size() > j ))
    {
     if(((String)memoria.get(i)).equals((String)alfatoken.get(j)))
     {
       if(!(unnormalized.equals((String)memoria.get(i))))
       { j++;
         i++;
       }
     }
    else
     {
      if(lexic_analizer_token((String)memoria.get(i)))
      {
      for( a = 0; a < (j - 1); a ++)
        {
          if(((String)alfatoken.get(a)).equals((String)memoria.get(i)))
          {
             correct = false;
             add_token_error(countt);
             error.add(error23);
             error.add(alfatoken.get(j - 1));
             terror ++;
          }

        }
      }

      i ++;
     }

    }

   if(j < 4)
   { terror ++;
     add_token_error(countt);
     error.add(error23);
     error.add(alfatoken.get(j));
   correct = false;}

   return correct;
  }


void semantica() {
  /*Creando Vector para determinar el orden sem�ntico de
      los tokens*/
     alfatoken.add(accion);
     alfatoken.add(reward);
     alfatoken.add(function);
     alfatoken.add(discount);
     alfatoken.add(tolerance);
   }



boolean min_tokens() {
     /*Define el tama�o m�nimo de un archivo para que pueda ser evaluado*/
    int min = 16;
    boolean acept = true;
    if (memoria.size() < min) {
      add_token_error(countt);
      error.add(error12);
      error.add(memoria.get(0));
      terror++;
      acept = false;
    }

    return acept;

  }

void display_errors() {
  /*Preparando String para despliegue de los errores que hayan sido encontrados
      durante el an�lisis*/
    if ((terror == 0) && ( tmesg == 0))  {
      complete = true;
    }

     /*Enviar a pantalla el vector*/
     errores = "No.Token      Mensaje                                                       Token\n";
     errores = errores + "-----------------------------------------------------------------------------------------\n";
     for (i = 0; i < error.size(); ) {
       errores = errores +  ((String)error.get(i)) + "        " + ( (String) error.get(i + 1)) +
           "   " + ( (String) error.get(i + 2)) + "\n";
     i = i + 3;
     }

     for( i = 0; i < mesg.size(); )
       {errores = errores + "####" + "        " + ( (String) mesg.get(i)) +
             "   " + ( (String) mesg.get(i + 1)) + "\n";
       i = i + 2;
       }


     errores = errores + "-----------------------------------------------------------------------------------------\n";
     errores = errores + "Errores:" + terror + "\n" + "Mensajes" + tmesg;

     if (complete == true) {
       errores = completo;
     }

   }


int count_var(String var_count) {
  /*Define el conteo de una variable en el vector memoria*/
    int counter = 0;
    int c = 0;
    for (c = 0; c < tmem; c++) {
      if (var_count.equals( (String) memoria.get(c))) {
        counter++;
      }

    }

    return counter;
  }


boolean valid_token(String token, int posvec, String nerror) {
  /*V�lida un token para que pueda ser aceptado , en caso de que
      no sea v�lido acumula el error en el vector error*/
    boolean valid = true;

     if (! (token.equals(memoria.get(countt)))) {
       add_token_error(countt);
       error.add(nerror);
       error.add(memoria.get(countt));
       terror++;
       valid = false;
     }
   return valid;
   }


void load_vector_memoria() {
    /*Carga el String Tokenizer en un vector en memoria
     ya que el an�lisis se realiza atrav�s de memoria*/

    while (tokens.hasMoreTokens()) {
      token = tokens.nextToken();

/*Anexando cada token al vector*/
      memoria.add( (String) token);
    }
}


boolean reconoce_accion(String naction, String nerror) {
   /*Reconoce si un token es una variable v�lida para una acci�n en nuestro lenguaje*/
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

   if( correct == true)
   {
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

     if( correct == false)
     {/*Generando un nuevo nombre de la acci�n*/
       String compon;
        compon = new String();
        compon = genera_action((String)memoria.get(countt));
        memoria.remove(countt);
        memoria.add(countt,compon);
        correct = true ;
     }
   }

    if (!correct) {
      add_token_error(countt);
      error.add(nerror);
      error.add(memoria.get(countt));
      terror++;
    }
    return correct;
  }


String modif_var(String variable)
  {

    /*En caso de que una variable que pasa SPI se� v�lida para el pero no v�lida
     en SPUDD, la variable es modificada de tal forma que sea v�lida para SPUDD*/
    int ncar;
    char cade[];

    /*String que ser� devuelta */
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
     if(t.charAt(i)  == '\"' )
     {
       t2.deleteCharAt(i - d);
       d ++;
       }
   }

   naction = t2.toString();

   if(d > 0 )
   {
     memoria.remove(countt);
     memoria.add(countt, naction);
   }

  return naction;

  }


boolean reconoce_variable(String naction1, String nerror) {
    /*Reconoce si un token es una variable v�lida para nuestro lenguaje, asigna
     un error si no es v�lida, contiene el m�todo modif_var para modificar una
     variable en el caso de que la variable sea v�lida para SPI y no v�lida para
     SPUDD*/
    int ncar;
    char cade[];

    /*String que sera devuelta */
    String  naction = new String();
    naction = modif_var(naction1);
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


for (int i = 0; i < ncar; i++) {
       car = cade[i];

       if (i == 0) {
         if (! ( (Character.isLetter(car)) || (car == '_') || (car == '\"') )) {
           i = ncar;
           correct = false;
         }
       }
       else {

         if (! ( (Character.isLetterOrDigit(car)) || (car == '_') || (car == '\"'))) {
           i = ncar;
           correct = false;
         }
       }
     }

if (!correct) {
      add_token_error(countt);
      error.add(nerror);
      error.add(memoria.get(countt));
      terror++;
    }
    return correct;
  }


boolean reconoce_variable(String naction) {
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

    for (int i = 0; i < ncar; i++) {
      car = cade[i];

      if (i == 0) {
        if (! ( (Character.isLetter(car)) || (car == '_') || (car == '\"'))) {
          i = ncar;
          correct = false;
        }
      }
      else {

        if (! ( (Character.isLetterOrDigit(car)) || (car == '_') || (car == '\"'))) {
          i = ncar;
          correct = false;
        }

      }
    }

    return correct;
  }


boolean reconoce_variable_reward(String naction) {
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



void reconoce_valor(String nvalor, String nerror) {
  /*Reconoce si un token es un valor v�lido para nuestro lenguaje, si no es v�lido
     devuelve el error correspondiente*/

    int ncar;
    int punto = 0;
     char cade[];
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

         if(car == '.')
         {
           if(!(( i == 0 ) || ( i == 1 ) || (i == 2)))
           {
             correct = false;
           }
           punto ++;
         }

         if(car == '-')
         {
           if(i != 0)
           {
             correct = false;
           }
         }

       }
     }


if(correct)
     {
    if(punto > 1)
    {
      correct = false;
    }

    if(punto == 0)
    {String entero;
      entero = new String();
      if(ncar > 1)
      {
        if(cade[0] != '-')
        {
        terror ++;
        add_token_error(countt);
        error.add(error46);
        error.add(memoria.get(countt));}
      else
      {
        if((cade[1] == '0') || (cade[1] == '1'))
        {
          entero = int_tofloat((String)memoria.get(countt));
          memoria.remove(countt);
          memoria.add(countt, entero);
        }
        else
        {
          terror ++;
          add_token_error(countt);
          error.add(error46);
          error.add(memoria.get(countt));
        }

      }
        }
      else
      {
        if((cade[0] == '0') || (cade[0] == '1'))
        {
          entero = int_tofloat((String)memoria.get(countt));
          memoria.remove(countt);
          memoria.add(countt, entero);
        }
        else
        {
          terror ++;
          add_token_error(countt);
          error.add(error46);
          error.add(memoria.get(countt));
        }
      }
    }

    if(punto == 1)
    {
      /*Que no sea mayor a 1 */
      if(!((cade[0] == '.') || (cade[1] == '.') || (cade[2] == '.')))
      {
        correct = false;
      }

      if(cade[0] == '.')
      {String p;
        p = new String();
        p = punto_add((String)memoria.get(countt));
        memoria.remove(countt);
        memoria.add(countt,p);
      }

      if(cade[1] == '.')
      {
        if(cade[0] != '-')
        {
          if (! ( (cade[0] == '1') || (cade[0] == '0'))) {
            terror++;
            add_token_error(countt);
            error.add(error46);
            error.add(memoria.get(countt));
          }
        }
        else
        {
          if (!( (cade[1] == '1') || (cade[1] == '0'))) {
            terror++;
            add_token_error(countt);
            error.add(error46);
            error.add(memoria.get(countt));
          }

        }
      }

      if(cade[2] == '.')
     {
       if(cade[0] != '-')
       {
       terror ++;
       add_token_error(countt);
       error.add(error46);
       error.add(memoria.get(countt));
       }

       if(!((cade[1] == '1') || (cade[1] == '0')))
       {
       terror ++;
       add_token_error(countt);
       error.add(error46);
       error.add(memoria.get(countt));
       }
     }

    }
     }

     if (!correct) {
       add_token_error(countt);
       error.add(nerror);
       error.add(memoria.get(countt));
       terror++;
     }

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


String int_tofloat(String intval)
   {
     /*Convierte un entero en flotante*/
     String fl;
     fl = new String();
     fl = intval + ".000000";
     return fl;
   }


String punto_add(String punto)
   {
     /*A�ade 0 a .0 = 0.0 */
     String fl;
     fl = new String();
     fl = "0" + punto ;
     return fl;
   }


boolean carga_reward(String reward)
   {

     boolean acept = false;

    StringBuffer  buf;

    /*N�mero de caracteres de la cadena */
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

    /*buf.append(cade[i]);*/

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


void inicia_reward(String reward)
   {

    /*Realiza el an�lisis de la funci�n de reward, el an�lisis se realiza des
     pu�s de cargar la funci�n de recompensa (reward) en un vector; por lo que
     los errores que se encuentran en el archivo original, no son detectados,
     el an�lisis es sencillo ya que s�lo contiene ciertas funciones para dectectar
     algunos errores, se puede extender para manejar o totalizar el an�lisis de
     la funci�n de reward*/

    carga_reward(reward);
    ttk = rewardm.size();
    conttr = 0;
    boolean endrw = false;


endr:

   /*Verificando que la funci�n de reward contenga el m�nimo de
    tokens*/

if(!endrw)
    {
   if(!min_tokens_reward())
    {
      endrw = true;
      break endr;
    }

   /*Verificando que los corchetes abran y cierren */
   if(!simetria_corchetes())
   {
     endrw = true;
     break endr;
   }

   /*Verificando que los elementos de reward se�n v�lidos*/
   if(!lexic_reward())
   {
     endrw = true;
     break endr;
   }

}

 }

boolean min_tokens_reward()
  {
    /*Revisa que el reward contenga el m�nimo de tokens para ser
     analizado*/
    boolean correct = true;

    int min = 7;
    if (memoria.size() < min) {
          add_token_error(countt);
          error.add(error50);
          error.add(memoria.get(0));
          terror++;
          correct = false;
        }

        return correct;
  }


boolean simetria_corchetes()
   {
     /*Revisa que existan tantos corchetes abiertos como cerrados*/
    boolean correct = true;
    int simc = 0;

   /*Comprobando y contabilizando los corchetes */
    for( i = 0; i < rewardm.size(); i ++)
    {
      if((rewardm.get(i)).equals(acorchete))
      {
        simc ++;
      }

      if((rewardm.get(i)).equals(ccorchete))
      {
        simc --;
      }

     }
     /*Analizando la variable para revisar que existan igual n�mero de
      corchetes abiertos y corchetes cerrados*/

     if(simc != 0)
     {
      add_token_error(countt);
      error.add(error51);
      error.add(memoria.get(0));
      terror++;
      correct = false;
      }

    return correct;
    }


boolean lexic_tokens_reward(String l_a)
    {
      /*Revisa que los tokens en la funci�n de reward sean v�lidos para
       el lenguaje*/

        boolean acept = false;

   /*Reconociendo que pertenezcan a los tokens v�lidos
    del lenguaje, que sean un valor o una variable v�lida o un elemento
    del reward function*/

    if (reconoce_variable(l_a))
    {
       acept = true;
    }

    if(reconoce_valor(l_a))
    {
      acept = true;
    }

    if(reconoce_variable_reward(l_a))
    {
      acept = true;
    }

    if(l_a.equals(acorchete))
    {
      acept = true;
    }

    if(l_a.equals(ccorchete))
    {
      acept = true;
    }

    return acept;

   }


boolean lexic_reward()
   {
     /*Verificando que el vector de reward contenga elementos v�lidos*/
     boolean correct = true;

     for(int i = 0; i < rewardm.size(); i ++)
       {
         if(!(lexic_tokens_reward((String)rewardm.get(i))))
         {
           correct = false;
           terror++;
           add_token_error(countt);
           error.add(error52);
           error.add(rewardm.get(i));
         }
      }


      return correct;

   }


boolean ver_var_reward(int var,int val)
   {
     /*Reconociendo que los tokens obtenidos en dreward para la funci�n
       de reward sean v�lidos*/

     boolean correct = true;
     int i = 0;


    while(i < var )
    {

      if (!reconoce_variable( (String) dreward.get(i))) {
         correct = false;
         add_token_error(countt);
         error.add(error43);
         error.add(dreward.get(i));
         terror ++;
      }
      i ++;
    }

     while( i < val + var )
     {
       if(!reconoce_valor((String) dreward.get(i)))
       {
         correct = false;
         add_token_error(countt);
         error.add(error43);
         error.add(dreward.get(i));
         terror ++;
       }
       i ++;
     }

     if(val + 1 != var)
     {
       correct = false;
       add_token_error(countt);
       error.add(error44);
       error.add(dreward.get(i));
       terror ++;
     }

     return correct;

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


void add_token_tpar(int par)
    {
    /*Convierte número de error a un elemento válido para poder anexarlo
      al vector de error*/
      String tem;
      StringBuffer parb;
      parb = new StringBuffer();
      tem = new String();
      parb.append(par);
      tpar.add(parb.toString());

     }


int  convert_S_entero(String entero)
   {
     int numero=Integer.parseInt(entero.trim());

     return numero;
   }


void add_token_error(int numerror)
   {
     /*Convierte n�mero de error a un elemento v�lido para poder anexarlo
         al vector de error*/
     String tem;
     StringBuffer errorb;
     errorb = new StringBuffer();
     tem = new String();
     errorb.append(numerror);
     error.add(errorb.toString());
     }

}









