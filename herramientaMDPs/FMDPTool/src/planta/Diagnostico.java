package planta;

import DataBase.DatosPersistence;
import java.util.Enumeration;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import elvira.Bnet;
import elvira.Node;
import elvira09.Evalua;
import aprendizaje.ValorDiscreto;
import IIEClases.Resultado;
import IIEClases.TDecision;
import java.awt.Color;
import java.io.BufferedWriter;
import java.io.File;
import utileria.FileOutput01;

/**
 *
 * @author alondra.nava
 */
public class Diagnostico extends javax.swing.JFrame implements Runnable
{
    public TDecision Detector;
    public TDecision Aislador;
    public String nombresE; // nombres de nodos evidenciados en deteccion
    public String valoresE; // valor de nodos evidenciados en deteccion
    public String nombresEI; // nombres de nodos evidenciados en deteccion
    public String valoresEI; //
    public int Nnodos;  // numero de nodos
    int i, cont,rangoV=0,mp=1;
    public Vector R= new Vector();
    public Resultado Rx;
    public boolean sigue = true; //control del loop infinito
    public double[] PuntosMed;
    public String variable="";
    public int valorVariable=0;
    public int turno=0;
    public boolean hayFalla = false;
    public Thread hilo = null;
    public boolean inicia=true;
    ValorDiscreto disc = new ValorDiscreto();
    public Evalua eval = new Evalua(false);
    public String nombresD[];
    public String nombresD2[];
    public String Sensores[];
    public double Valvar[];
    public double Valvar2[];
    public double Errores[];
    public double Rangos[][];
    public int ValDisc[], conta;
    public double aIHM[];
    public String s, s2 = new String();
    public Runtime r = Runtime.getRuntime();
    public long freeMem;
    public double[] Arep;
    public double incremento,acumulador,div;
    public String das;
    public String[] cadena;
    public Enumeration enumm;
    public String sFichero;
    public File fichero;
    public BufferedWriter bw;
    public Vector nodes;
    public double ValReal;
    FileOutput01 fo;
    /** Creates new form ProgressBar */
    public Diagnostico() throws Exception
    {
        initComponents();
        Rangos=new double[12][3];
        /***** NDEAR*****/
        Rangos[0][0]=0.0;//Valor Mínimo
        Rangos[0][1]=2.0;//Valor Máximo
        Rangos[0][2]=10.0;//Número de partes
        /***** XFWV *****/
        Rangos[1][0]=0.0;//Valor Mínimo
        Rangos[1][1]=1.0;//Valor Máximo
        Rangos[1][2]=10.0;//Número de partes
        /***** GWAD *****/
        Rangos[2][0]=0.0;//Valor Mínimo
        Rangos[2][1]=600.0;//Valor Máximo
        Rangos[2][2]=10.0;//Número de partes
        /***** NDO *****/
        Rangos[3][0]=0.0;//Valor Mínimo
        Rangos[3][1]=100.0;//Valor Máximo
        Rangos[3][2]=10.0;//Número de partes
        /***** PVR *****/
        Rangos[4][0]=2500.0;//Valor Mínimo
        Rangos[4][1]=5000.0;//Valor Máximo
        Rangos[4][2]=10.0;//Número de partes
        /***** XALIV *****/
        Rangos[5][0]=0.0;//Valor Mínimo
        Rangos[5][1]=1.0;//Valor Máximo
        Rangos[5][2]=5.0;//Número de partes
        /***** XZ1 *****/
        Rangos[6][0]=0.0;//Valor Mínimo
        Rangos[6][1]=1.0;//Valor Máximo
        Rangos[6][2]=10.0;//Número de partes
        /***** XZ2 *****/
        Rangos[7][0]=0.0;//Valor Mínimo
        Rangos[7][1]=1.0;//Valor Máximo
        Rangos[7][2]=10.0;//Número de partes
        /***** GVR *****/
        Rangos[8][0]=25.0;//Valor Mínimo
        Rangos[8][1]=70.0;//Valor Máximo
        Rangos[8][2]=10.0;//Número de partes
        /***** TGSR *****/
        Rangos[9][0]=400.0;//Valor Mínimo
        Rangos[9][1]=540.0;//Valor Máximo
        Rangos[9][2]=10.0;//Número de partes
        /***** GDEAR *****/
        Rangos[10][0]=0.0;//Valor Mínimo
        Rangos[10][1]=60.0;//Valor Máximo
        Rangos[10][2]=10.0;//Número de partes
        /***** WET3 *****/
        Rangos[11][0]=3300.0;//Valor Mínimo
        Rangos[11][1]=31000.0;//Valor Máximo
        Rangos[11][2]=10.0;//Número de partes
        
        Detector = new TDecision("modelos/redMuestramedia.elv");
        Aislador = new TDecision("modelos/RedAislamientomedia.elv");
        Bnet redB = (Bnet)Detector.Obtener_Red();
        Nnodos = redB.getNodeList().size();
        nombresD = new String[Nnodos];  // arreglo de nombres de nodos
        nombresD2 = new String[Nnodos+1];
        Sensores = new String[Nnodos];
        Valvar = new double[Nnodos]; //obtiene los valores de la base de datos
        Valvar2 = new double[Nnodos+1];
        Errores= new double[Nnodos];
        ValDisc = new int[Nnodos]; //guarda los valores discretos calculados
        aIHM = new double[Nnodos];
        nombresD = ObtenNombresNodos(redB);

                        
    }
   
    public String[] ObtenNombresNodos(Bnet b) 
    {
    	nodes=(b.getNodeList()).getNodes();
	cadena=new String[nodes.size()];  
	enumm = nodes.elements();
	conta=0;
		
	while(enumm.hasMoreElements())
	{  
            cadena[conta]=((Node)enumm.nextElement()).getName();
            conta++;
	}  
           
        return cadena;
    }

    public void Calculos()
    {
       das=Integer.toString(mp);
       contador.setText(das);

      //ESTO
       

      for(i=0; i< Nnodos; i++){
            try {
                Valvar[i] = dameValor(nombresD[i]);
                disc.setValoresDiscretos(Rangos[i][0], Rangos[i][1], (int) Rangos[i][2]);
                ValDisc[i] = disc.getValorDiscretoInt(Valvar[i]);
            } catch (Exception ex) {
                Logger.getLogger(Diagnostico.class.getName()).log(Level.SEVERE, null, ex);
            }
   
       }

    }
   
    public void error(String causa)
    {
    	System.out.println(">>> " + causa + " <<<");
    	System.exit(0);
    }
   
    public double[] PuntoMedio(double min, double max, int partes)
    {
    	Arep=new double[partes];
    	incremento = (max-min)/partes;
	    	        
    	for(int i=0;i<partes;i++)
    	{
    		acumulador = min ;
    		max = acumulador + incremento;
    		div = (acumulador + max) /2;
    		Arep[i]=div;
    		min = max;
    	}
     	
    	return(Arep);
    }
   
    public void diagnostico()
    {
       try
    	{
           Calculos();

           nombresEI = "";
           valoresEI = "";

          for(cont = 0; cont < Nnodos; cont++)
          {
            nombresE = "";
            valoresE = "";

            for(i=0; i< Nnodos; i++)
              {
                if(i != cont)
                {
                    nombresE = nombresE + nombresD[i];
                    rangoV = ValDisc[i];
                    valoresE = valoresE + ("" + rangoV);

                    if(i < Nnodos-1)
                    {
                        nombresE = nombresE + ",";
                        valoresE = valoresE + ",";
                    }

                  }//if(i != cont)

                  else
                  {
                      variable = nombresD[i];
                      valorVariable = ValDisc[i];
                      rangoV=ValDisc[i];
                  }

               
             }//for(i=0; i< Nnodos; i++)

             
             
             if(!Detector.ColocarEvidencia(nombresE,valoresE))
                error("Falla colocar evidencia");

              Detector.Propagar();

              if ( Detector.Estado_de_Propagacion() == true )
              {
                R = Detector.ObtenerValores();
	        Rx = (Resultado)R.elementAt(0); // unico elemento

                //organiza y llama a evalua u ve cargando evidencia de aislamiento
                if(variable.equals("NDEAR"))
                    PuntosMed=PuntoMedio(Rangos[0][0], Rangos[0][1], (int)Rangos[0][2]);

	        if(variable.equals("XFWV"))
                    PuntosMed=PuntoMedio(Rangos[1][0], Rangos[1][1], (int)Rangos[1][2]);

		if(variable.equals("GWAD"))
                    PuntosMed=PuntoMedio(Rangos[2][0], Rangos[2][1], (int)Rangos[2][2]);

		if(variable.equals("NDO"))
                    PuntosMed=PuntoMedio(Rangos[3][0], Rangos[3][1], (int)Rangos[3][2]);

		if(variable.equals("PVR"))
                    PuntosMed=PuntoMedio(Rangos[4][0], Rangos[4][1], (int)Rangos[4][2]);

		if(variable.equals("XALIV"))
                    PuntosMed=PuntoMedio(Rangos[5][0], Rangos[5][1], (int)Rangos[5][2]);

		if(variable.equals("XZ1"))
                    PuntosMed=PuntoMedio(Rangos[6][0], Rangos[6][1], (int)Rangos[6][2]);

		if(variable.equals("XZ2"))
                    PuntosMed=PuntoMedio(Rangos[7][0], Rangos[7][1], (int)Rangos[7][2]);

		if(variable.equals("GVR"))
                    PuntosMed=PuntoMedio(Rangos[8][0], Rangos[8][1], (int)Rangos[8][2]);

		if(variable.equals("TGSR"))
                    PuntosMed=PuntoMedio(Rangos[9][0], Rangos[9][1], (int)Rangos[9][2]);

		if(variable.equals("GDEAR"))
                    PuntosMed=PuntoMedio(Rangos[10][0], Rangos[10][1], (int)Rangos[10][2]);

		if(variable.equals("WET3"))
                    PuntosMed=PuntoMedio(Rangos[11][0], Rangos[11][1], (int)Rangos[11][2]);

               if(eval.decide(Rx.Valores,PuntosMed,valorVariable,variable))
                 valoresEI = valoresEI + "0";

               else
                   valoresEI = valoresEI + "1";
                
	    }// if de estado propagacion

	    else
                valoresEI = valoresEI + "0"; // marca falla en variable

            nombresEI = nombresEI + variable;

	   if(cont < Nnodos-1)
	   {
                nombresEI = nombresEI + ",";
                valoresEI = valoresEI + ",";
	   }

          }//for(cont = 0; cont < Nnodos; cont++)

        
         if(!Aislador.ColocarEvidencia(nombresEI,valoresEI))
	       error("Falla en evidencia de aislador");

	 Aislador.Propagar();

	 if ( Aislador.Estado_de_Propagacion() == true )
            R = Aislador.ObtenerValores();

         else
                error("Falla en la propagacion del aislador");

        //filtra si se envia la informacion a la IHM (filtrar fallas intermitentes)
	Rx = (Resultado)R.elementAt(0); // unico elemento

	for(hayFalla = false, cont = 0; cont < Nnodos; cont++)
	{
            Rx = (Resultado)R.elementAt(cont);
	    aIHM[cont] = Rx.Valores[0];

            Errores[cont]=Math.round(aIHM[cont]*100);
          
	    if(aIHM[cont] > 0.70)
                    hayFalla = true;


        }

                         
        mp=mp+1;
           
        }//try

        catch(Exception error)
    	{
            System.out.println(error);
    	}
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scrollContainer = new javax.swing.JScrollPane();
        pnContainerBar = new javax.swing.JPanel();
        lbNDO = new javax.swing.JLabel();
        barxfwv = new javax.swing.JProgressBar();
        lbGWAD = new javax.swing.JLabel();
        lbXFWV = new javax.swing.JLabel();
        barndo = new javax.swing.JProgressBar();
        bargwad = new javax.swing.JProgressBar();
        lbVariables = new javax.swing.JLabel();
        lbDescripcion = new javax.swing.JLabel();
        lbDesXFWV = new javax.swing.JLabel();
        lbProbabilidad = new javax.swing.JLabel();
        lbDesGWAD = new javax.swing.JLabel();
        lbDesNDO = new javax.swing.JLabel();
        lbPVR = new javax.swing.JLabel();
        lbDesPVR = new javax.swing.JLabel();
        barpvr = new javax.swing.JProgressBar();
        lbXALIV = new javax.swing.JLabel();
        lbDesXALIV = new javax.swing.JLabel();
        barxaliv = new javax.swing.JProgressBar();
        lbXZ2 = new javax.swing.JLabel();
        barxz2 = new javax.swing.JProgressBar();
        lbDesXZ2 = new javax.swing.JLabel();
        lbXZ1 = new javax.swing.JLabel();
        barxz1 = new javax.swing.JProgressBar();
        bargvr = new javax.swing.JProgressBar();
        lbGVR = new javax.swing.JLabel();
        bartgsr = new javax.swing.JProgressBar();
        lbTGSR = new javax.swing.JLabel();
        bargdear = new javax.swing.JProgressBar();
        barwet3 = new javax.swing.JProgressBar();
        lbGDEAR = new javax.swing.JLabel();
        lbNDEAR = new javax.swing.JLabel();
        lbDesXZ1 = new javax.swing.JLabel();
        lbDesGVR = new javax.swing.JLabel();
        lbDesTGSR = new javax.swing.JLabel();
        lbDesGDEAR = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator5 = new javax.swing.JSeparator();
        jSeparator6 = new javax.swing.JSeparator();
        jSeparator7 = new javax.swing.JSeparator();
        jSeparator8 = new javax.swing.JSeparator();
        jSeparator9 = new javax.swing.JSeparator();
        jSeparator10 = new javax.swing.JSeparator();
        jSeparator11 = new javax.swing.JSeparator();
        jSeparator12 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        jSeparator13 = new javax.swing.JSeparator();
        jSeparator14 = new javax.swing.JSeparator();
        lbWET3 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        barndear = new javax.swing.JProgressBar();
        TPNDEAR = new javax.swing.JLabel();
        TPXFWV = new javax.swing.JLabel();
        TPGWAD = new javax.swing.JLabel();
        TPNDO = new javax.swing.JLabel();
        TPPVR = new javax.swing.JLabel();
        TPXALIV = new javax.swing.JLabel();
        TPXZ1 = new javax.swing.JLabel();
        TPXZ2 = new javax.swing.JLabel();
        TPGVR = new javax.swing.JLabel();
        TPTGSR = new javax.swing.JLabel();
        TPGDEAR = new javax.swing.JLabel();
        TPWET3 = new javax.swing.JLabel();
        lbValue = new javax.swing.JLabel();
        btnStart = new javax.swing.JButton();
        btnStop = new javax.swing.JButton();
        pnAlertas = new javax.swing.JPanel();
        lbColorCode = new javax.swing.JLabel();
        lbRoja = new javax.swing.JLabel();
        lbAmarilla = new javax.swing.JLabel();
        lbVerde = new javax.swing.JLabel();
        lbDanger = new javax.swing.JLabel();
        lbCaution = new javax.swing.JLabel();
        lbNormal = new javax.swing.JLabel();
        contador = new javax.swing.JTextField();
        lbIterador = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Panel de probabilidad de fallas");
        setResizable(false);
        getContentPane().setLayout(null);

        pnContainerBar.setAutoscrolls(true);
        pnContainerBar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        pnContainerBar.setLayout(null);

        lbNDO.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbNDO.setText("NDO:");
        pnContainerBar.add(lbNDO);
        lbNDO.setBounds(30, 120, 40, 17);

        barxfwv.setStringPainted(true);
        pnContainerBar.add(barxfwv);
        barxfwv.setBounds(500, 40, 110, 23);

        lbGWAD.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbGWAD.setText("GWAD:");
        pnContainerBar.add(lbGWAD);
        lbGWAD.setBounds(20, 80, 50, 17);

        lbXFWV.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbXFWV.setText("XFWV:");
        pnContainerBar.add(lbXFWV);
        lbXFWV.setBounds(20, 44, 50, 20);

        barndo.setStringPainted(true);
        pnContainerBar.add(barndo);
        barndo.setBounds(500, 120, 110, 23);

        bargwad.setStringPainted(true);
        pnContainerBar.add(bargwad);
        bargwad.setBounds(500, 80, 110, 23);

        lbVariables.setFont(new java.awt.Font("Tahoma", 0, 14));
        lbVariables.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbVariables.setText("Variables");
        pnContainerBar.add(lbVariables);
        lbVariables.setBounds(10, 10, 70, 17);

        lbDescripcion.setFont(new java.awt.Font("Tahoma", 0, 14));
        lbDescripcion.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbDescripcion.setText("Description");
        pnContainerBar.add(lbDescripcion);
        lbDescripcion.setBounds(76, 11, 410, 17);

        lbDesXFWV.setText("POSICION DE LA VALVULA DE AGUA DE ALIMENTACION");
        pnContainerBar.add(lbDesXFWV);
        lbDesXFWV.setBounds(101, 46, 340, 17);

        lbProbabilidad.setFont(new java.awt.Font("Tahoma", 0, 14));
        lbProbabilidad.setText("Probability of failure");
        pnContainerBar.add(lbProbabilidad);
        lbProbabilidad.setBounds(490, 0, 170, 30);

        lbDesGWAD.setText("FLUJO DE AGUA DE ALIMENTACION");
        pnContainerBar.add(lbDesGWAD);
        lbDesGWAD.setBounds(100, 80, 250, 17);

        lbDesNDO.setText("NIVEL DEL DOMO");
        pnContainerBar.add(lbDesNDO);
        lbDesNDO.setBounds(100, 120, 150, 17);

        lbPVR.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbPVR.setText("PVR:");
        pnContainerBar.add(lbPVR);
        lbPVR.setBounds(30, 160, 40, 17);

        lbDesPVR.setText("PRESION DEL DOMO");
        pnContainerBar.add(lbDesPVR);
        lbDesPVR.setBounds(100, 160, 280, 17);

        barpvr.setStringPainted(true);
        pnContainerBar.add(barpvr);
        barpvr.setBounds(500, 160, 110, 23);

        lbXALIV.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbXALIV.setText("XALIV:");
        pnContainerBar.add(lbXALIV);
        lbXALIV.setBounds(20, 200, 50, 17);

        lbDesXALIV.setText("POSICION DE LA VALVULA DE ALIVIO");
        pnContainerBar.add(lbDesXALIV);
        lbDesXALIV.setBounds(100, 200, 250, 17);

        barxaliv.setStringPainted(true);
        pnContainerBar.add(barxaliv);
        barxaliv.setBounds(500, 200, 110, 23);

        lbXZ2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbXZ2.setText("XZ2:");
        pnContainerBar.add(lbXZ2);
        lbXZ2.setBounds(30, 280, 40, 17);

        barxz2.setStringPainted(true);
        pnContainerBar.add(barxz2);
        barxz2.setBounds(500, 280, 110, 23);

        lbDesXZ2.setText("POSICION DE LA VALVULA DE VAPOR 2");
        pnContainerBar.add(lbDesXZ2);
        lbDesXZ2.setBounds(100, 280, 300, 17);

        lbXZ1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbXZ1.setText("XZ1:");
        pnContainerBar.add(lbXZ1);
        lbXZ1.setBounds(32, 240, 40, 17);

        barxz1.setStringPainted(true);
        pnContainerBar.add(barxz1);
        barxz1.setBounds(500, 240, 110, 23);

        bargvr.setStringPainted(true);
        pnContainerBar.add(bargvr);
        bargvr.setBounds(500, 320, 110, 23);

        lbGVR.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbGVR.setText("GVR:");
        pnContainerBar.add(lbGVR);
        lbGVR.setBounds(30, 320, 40, 17);

        bartgsr.setStringPainted(true);
        pnContainerBar.add(bartgsr);
        bartgsr.setBounds(500, 360, 110, 23);

        lbTGSR.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbTGSR.setText("TGSR:");
        pnContainerBar.add(lbTGSR);
        lbTGSR.setBounds(20, 360, 50, 17);

        bargdear.setStringPainted(true);
        pnContainerBar.add(bargdear);
        bargdear.setBounds(500, 400, 110, 23);

        barwet3.setStringPainted(true);
        pnContainerBar.add(barwet3);
        barwet3.setBounds(500, 440, 110, 23);

        lbGDEAR.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbGDEAR.setText("GDEAR:");
        pnContainerBar.add(lbGDEAR);
        lbGDEAR.setBounds(20, 400, 50, 17);

        lbNDEAR.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbNDEAR.setText("NDEAR:");
        pnContainerBar.add(lbNDEAR);
        lbNDEAR.setBounds(20, 480, 50, 17);

        lbDesXZ1.setText("POSICION DE LA VALVULA DE VAPOR 1");
        pnContainerBar.add(lbDesXZ1);
        lbDesXZ1.setBounds(100, 240, 310, 17);

        lbDesGVR.setText("FLUJO DE VAPOR DEL RECUPERADOR (VAP. PRINCIPAL)");
        pnContainerBar.add(lbDesGVR);
        lbDesGVR.setBounds(100, 320, 360, 17);

        lbDesTGSR.setText("TEMPERATURA DE LOS GASES EN LA SALIDA DEL RECUPERADOR");
        pnContainerBar.add(lbDesTGSR);
        lbDesTGSR.setBounds(100, 360, 380, 17);

        lbDesGDEAR.setText("FLUJO DEL DEAREADOR");
        pnContainerBar.add(lbDesGDEAR);
        lbDesGDEAR.setBounds(100, 400, 270, 17);

        jLabel33.setText("NIVEL DEL DEAREADOR");
        pnContainerBar.add(jLabel33);
        jLabel33.setBounds(100, 480, 340, 17);
        pnContainerBar.add(jSeparator2);
        jSeparator2.setBounds(20, 110, 750, 10);
        pnContainerBar.add(jSeparator3);
        jSeparator3.setBounds(20, 150, 750, 10);
        pnContainerBar.add(jSeparator5);
        jSeparator5.setBounds(20, 190, 750, 10);
        pnContainerBar.add(jSeparator6);
        jSeparator6.setBounds(20, 470, 750, 10);
        pnContainerBar.add(jSeparator7);
        jSeparator7.setBounds(20, 70, 750, 10);
        pnContainerBar.add(jSeparator8);
        jSeparator8.setBounds(20, 230, 750, 10);
        pnContainerBar.add(jSeparator9);
        jSeparator9.setBounds(20, 270, 750, 10);
        pnContainerBar.add(jSeparator10);
        jSeparator10.setBounds(20, 310, 750, 10);
        pnContainerBar.add(jSeparator11);
        jSeparator11.setBounds(20, 350, 750, 10);
        pnContainerBar.add(jSeparator12);
        jSeparator12.setBounds(20, 390, 750, 10);

        jSeparator4.setOrientation(javax.swing.SwingConstants.VERTICAL);
        pnContainerBar.add(jSeparator4);
        jSeparator4.setBounds(480, 30, 10, 470);

        jSeparator13.setOrientation(javax.swing.SwingConstants.VERTICAL);
        pnContainerBar.add(jSeparator13);
        jSeparator13.setBounds(80, 30, 20, 470);
        pnContainerBar.add(jSeparator14);
        jSeparator14.setBounds(20, 430, 750, 10);

        lbWET3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbWET3.setText("WET3:");
        pnContainerBar.add(lbWET3);
        lbWET3.setBounds(20, 440, 50, 17);

        jLabel34.setText("GENERACION (MW) DE LA TURBINA DE VAPOR");
        pnContainerBar.add(jLabel34);
        jLabel34.setBounds(100, 440, 340, 17);

        barndear.setStringPainted(true);
        pnContainerBar.add(barndear);
        barndear.setBounds(500, 480, 110, 23);
        pnContainerBar.add(TPNDEAR);
        TPNDEAR.setBounds(630, 480, 140, 20);
        pnContainerBar.add(TPXFWV);
        TPXFWV.setBounds(630, 40, 150, 20);
        pnContainerBar.add(TPGWAD);
        TPGWAD.setBounds(630, 80, 150, 20);
        pnContainerBar.add(TPNDO);
        TPNDO.setBounds(630, 120, 150, 20);
        pnContainerBar.add(TPPVR);
        TPPVR.setBounds(630, 160, 140, 20);
        pnContainerBar.add(TPXALIV);
        TPXALIV.setBounds(630, 200, 140, 20);
        pnContainerBar.add(TPXZ1);
        TPXZ1.setBounds(630, 240, 140, 20);
        pnContainerBar.add(TPXZ2);
        TPXZ2.setBounds(630, 280, 140, 20);
        pnContainerBar.add(TPGVR);
        TPGVR.setBounds(630, 320, 140, 20);
        pnContainerBar.add(TPTGSR);
        TPTGSR.setBounds(640, 360, 130, 20);
        pnContainerBar.add(TPGDEAR);
        TPGDEAR.setBounds(640, 400, 130, 20);
        pnContainerBar.add(TPWET3);
        TPWET3.setBounds(630, 440, 140, 20);

        lbValue.setFont(new java.awt.Font("Tahoma", 0, 14));
        lbValue.setText("Value");
        pnContainerBar.add(lbValue);
        lbValue.setBounds(700, 10, 60, 14);

        scrollContainer.setViewportView(pnContainerBar);

        getContentPane().add(scrollContainer);
        scrollContainer.setBounds(10, 20, 780, 520);

        btnStart.setText("START");
        btnStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStartActionPerformed(evt);
            }
        });
        getContentPane().add(btnStart);
        btnStart.setBounds(840, 30, 110, 29);

        btnStop.setText("STOP");
        btnStop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStopActionPerformed(evt);
            }
        });
        getContentPane().add(btnStop);
        btnStop.setBounds(840, 80, 110, 29);

        pnAlertas.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        pnAlertas.setLayout(null);

        lbColorCode.setFont(new java.awt.Font("Tahoma", 0, 12));
        lbColorCode.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbColorCode.setText("Color code");
        pnAlertas.add(lbColorCode);
        lbColorCode.setBounds(14, 14, 138, 15);

        lbRoja.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/bola1.png"))); // NOI18N
        pnAlertas.add(lbRoja);
        lbRoja.setBounds(14, 47, 31, 32);

        lbAmarilla.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/bolaA.png"))); // NOI18N
        pnAlertas.add(lbAmarilla);
        lbAmarilla.setBounds(14, 97, 31, 32);

        lbVerde.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/bola2.png"))); // NOI18N
        pnAlertas.add(lbVerde);
        lbVerde.setBounds(14, 147, 31, 32);

        lbDanger.setFont(new java.awt.Font("Tahoma", 1, 12));
        lbDanger.setText("Danger");
        pnAlertas.add(lbDanger);
        lbDanger.setBounds(63, 64, 89, 15);

        lbCaution.setFont(new java.awt.Font("Tahoma", 1, 12));
        lbCaution.setText("Caution");
        pnAlertas.add(lbCaution);
        lbCaution.setBounds(63, 114, 89, 15);

        lbNormal.setFont(new java.awt.Font("Tahoma", 1, 12));
        lbNormal.setText("Normal");
        pnAlertas.add(lbNormal);
        lbNormal.setBounds(63, 164, 70, 15);

        getContentPane().add(pnAlertas);
        pnAlertas.setBounds(810, 180, 160, 200);
        getContentPane().add(contador);
        contador.setBounds(860, 150, 80, 27);

        lbIterador.setText("Iteration number");
        getContentPane().add(lbIterador);
        lbIterador.setBounds(830, 120, 140, 20);

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-987)/2, (screenSize.height-591)/2, 987, 591);
    }// </editor-fold>//GEN-END:initComponents

    public double consultarValor(String variable){
        try {
            return dameValor(variable);
        } catch (Exception ex) {
            Logger.getLogger(Diagnostico.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }

    }


    private void btnStartActionPerformed(java.awt.event.ActionEvent evt)
    {//GEN-FIRST:event_jButton1ActionPerformed
        inicia=true;
        
       if(hilo==null)
        {
            hilo = new Thread(this);
            hilo.start();
        }

        else
            hilo.resume();
      
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnStopActionPerformed(java.awt.event.ActionEvent evt)
    {//GEN-FIRST:event_jButton2ActionPerformed
        
        inicia=false;
        hilo.suspend();
    
    }//GEN-LAST:event_jButton2ActionPerformed


    private double dameValor(String var) throws Exception {
       try{
            manager.TablaCtccBean tb = new manager.TablaCtccBean(DatosPersistence.emf);
            entity.TablaCtcc te = new entity.TablaCtcc();
            te= tb.getValor(var);
            if(te!=null){
                return te.getValor();
            }

            return 0;
       }catch(Exception e){
           System.out.print("Error en dameValor...."+e.getLocalizedMessage());
           return -1;
       }

   }



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel TPGDEAR;
    private javax.swing.JLabel TPGVR;
    private javax.swing.JLabel TPGWAD;
    private javax.swing.JLabel TPNDEAR;
    private javax.swing.JLabel TPNDO;
    private javax.swing.JLabel TPPVR;
    private javax.swing.JLabel TPTGSR;
    private javax.swing.JLabel TPWET3;
    private javax.swing.JLabel TPXALIV;
    private javax.swing.JLabel TPXFWV;
    private javax.swing.JLabel TPXZ1;
    private javax.swing.JLabel TPXZ2;
    private javax.swing.JProgressBar bargdear;
    private javax.swing.JProgressBar bargvr;
    private javax.swing.JProgressBar bargwad;
    private javax.swing.JProgressBar barndear;
    private javax.swing.JProgressBar barndo;
    private javax.swing.JProgressBar barpvr;
    private javax.swing.JProgressBar bartgsr;
    private javax.swing.JProgressBar barwet3;
    private javax.swing.JProgressBar barxaliv;
    private javax.swing.JProgressBar barxfwv;
    private javax.swing.JProgressBar barxz1;
    private javax.swing.JProgressBar barxz2;
    private javax.swing.JButton btnStart;
    private javax.swing.JButton btnStop;
    private javax.swing.JTextField contador;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator12;
    private javax.swing.JSeparator jSeparator13;
    private javax.swing.JSeparator jSeparator14;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JLabel lbAmarilla;
    private javax.swing.JLabel lbCaution;
    private javax.swing.JLabel lbColorCode;
    private javax.swing.JLabel lbDanger;
    private javax.swing.JLabel lbDesGDEAR;
    private javax.swing.JLabel lbDesGVR;
    private javax.swing.JLabel lbDesGWAD;
    private javax.swing.JLabel lbDesNDO;
    private javax.swing.JLabel lbDesPVR;
    private javax.swing.JLabel lbDesTGSR;
    private javax.swing.JLabel lbDesXALIV;
    private javax.swing.JLabel lbDesXFWV;
    private javax.swing.JLabel lbDesXZ1;
    private javax.swing.JLabel lbDesXZ2;
    private javax.swing.JLabel lbDescripcion;
    private javax.swing.JLabel lbGDEAR;
    private javax.swing.JLabel lbGVR;
    private javax.swing.JLabel lbGWAD;
    private javax.swing.JLabel lbIterador;
    private javax.swing.JLabel lbNDEAR;
    private javax.swing.JLabel lbNDO;
    private javax.swing.JLabel lbNormal;
    private javax.swing.JLabel lbPVR;
    private javax.swing.JLabel lbProbabilidad;
    private javax.swing.JLabel lbRoja;
    private javax.swing.JLabel lbTGSR;
    private javax.swing.JLabel lbValue;
    private javax.swing.JLabel lbVariables;
    private javax.swing.JLabel lbVerde;
    private javax.swing.JLabel lbWET3;
    private javax.swing.JLabel lbXALIV;
    private javax.swing.JLabel lbXFWV;
    private javax.swing.JLabel lbXZ1;
    private javax.swing.JLabel lbXZ2;
    private javax.swing.JPanel pnAlertas;
    private javax.swing.JPanel pnContainerBar;
    private javax.swing.JScrollPane scrollContainer;
    // End of variables declaration//GEN-END:variables

    public void run()
    {
       while(inicia)
 	{
            try
            {
                diagnostico();

               barndear.setValue((int)Math.round(Errores[0]));

                if (barndear.getValue() <= 25)
                    barndear.setForeground(Color.green);

                else if(barndear.getValue() > 25 && barndear.getValue() <= 50)
                    barndear.setForeground(Color.yellow);

                else if(barndear.getValue() > 50)
                    barndear.setForeground(Color.red);

                TPNDEAR.setText(String.valueOf(Valvar[0]));

                barxfwv.setValue((int)Math.round(Errores[1]));

                if (barxfwv.getValue() <= 25)
                    barxfwv.setForeground(Color.green);

                else if(barxfwv.getValue() > 25 && barxfwv.getValue() <= 50)
                    barxfwv.setForeground(Color.yellow);

                else if(barxfwv.getValue() > 50)
                    barxfwv.setForeground(Color.red);

                TPXFWV.setText(String.valueOf(Valvar[1]));

                bargwad.setValue((int)Math.round(Errores[2]));

                if (bargwad.getValue() <= 25)
                    bargwad.setForeground(Color.green);

                else if(bargwad.getValue() > 25 && bargwad.getValue() <= 50)
                    bargwad.setForeground(Color.yellow);

                else if(bargwad.getValue() > 50)
                    bargwad.setForeground(Color.red);

                TPGWAD.setText(String.valueOf(Valvar[2]));

                barndo.setValue((int)Math.round(Errores[3]));

                if (barndo.getValue() <= 25)
                    barndo.setForeground(Color.green);

                else if(barndo.getValue() > 25 && barndo.getValue() <= 50)
                    barndo.setForeground(Color.yellow);

                else if(barndo.getValue() > 50)
                    barndo.setForeground(Color.red);

                TPNDO.setText(String.valueOf(Valvar[3]));
               
                barpvr.setValue((int)Math.round(Errores[4]));

                if (barpvr.getValue() <= 25)
                    barpvr.setForeground(Color.green);

                else if(barpvr.getValue() > 25 && barpvr.getValue() <= 50)
                    barpvr.setForeground(Color.yellow);

                else if(barpvr.getValue() > 50)
                    barpvr.setForeground(Color.red);

                TPPVR.setText(String.valueOf(Valvar[4]));

                barxaliv.setValue((int)Math.round(Errores[5]));

                if (barxaliv.getValue() <= 25)
                    barxaliv.setForeground(Color.green);

                else if(barxaliv.getValue() > 25 && barxaliv.getValue() <= 50)
                    barxaliv.setForeground(Color.yellow);

                else if(barxaliv.getValue() > 50)
                    barxaliv.setForeground(Color.red);

                TPXALIV.setText(String.valueOf(Valvar[5]));

                barxz1.setValue((int)Math.round(Errores[6]));

                if (barxz1.getValue() <= 25)
                    barxz1.setForeground(Color.green);

                else if(barxz1.getValue() > 25 && barxz1.getValue() <= 50)
                    barxz1.setForeground(Color.yellow);

                else if(barxz1.getValue() > 50)
                    barxz1.setForeground(Color.red);

                TPXZ1.setText(String.valueOf(Valvar[6]));

                barxz2.setValue((int)Math.round(Errores[7]));

                if (barxz2.getValue() <= 25)
                    barxz2.setForeground(Color.green);

                else if(barxz2.getValue() > 25 && barxz2.getValue() <= 50)
                    barxz2.setForeground(Color.yellow);

                else if(barxz2.getValue() > 50)
                    barxz2.setForeground(Color.red);

                TPXZ2.setText(String.valueOf(Valvar[7]));

                bargvr.setValue((int)Math.round(Errores[8]));

                if (bargvr.getValue() <= 25)
                    bargvr.setForeground(Color.green);

                else if(bargvr.getValue() > 25 && bargvr.getValue() <= 50)
                    bargvr.setForeground(Color.yellow);

                else if(bargvr.getValue() > 50)
                    bargvr.setForeground(Color.red);

                TPGVR.setText(String.valueOf(Valvar[8]));

                bartgsr.setValue((int)Math.round(Errores[9]));

                if (bartgsr.getValue() <= 25)
                    bartgsr.setForeground(Color.green);

                else if(bartgsr.getValue() > 25 && bartgsr.getValue() <= 50)
                    bartgsr.setForeground(Color.yellow);

                else if(bartgsr.getValue() > 50)
                    bartgsr.setForeground(Color.red);

                TPTGSR.setText(String.valueOf(Valvar[9]));

                bargdear.setValue((int)Math.round(Errores[10]));

                if (bargdear.getValue() <= 25)
                    bargdear.setForeground(Color.green);

                else if(bargdear.getValue() > 25 && bargdear.getValue() <= 50)
                    bargdear.setForeground(Color.yellow);

                else if(bargdear.getValue() > 50)
                    bargdear.setForeground(Color.red);

                TPGDEAR.setText(String.valueOf(Valvar[10]));

                barwet3.setValue((int)Math.round(Errores[11]));
                
                 if (barwet3.getValue() <= 25)
                    barwet3.setForeground(Color.green);

                else if(barwet3.getValue() > 25 && barwet3.getValue() <= 50)
                    barwet3.setForeground(Color.yellow);

                else if(barwet3.getValue() > 50)
                    barwet3.setForeground(Color.red);

                TPWET3.setText(String.valueOf(Valvar[11]));

                freeMem = r.freeMemory();
                r.gc();


                Thread.sleep(500);
            }

 catch (InterruptedException ex)
            {
                Logger.getLogger(Diagnostico.class.getName()).log(Level.SEVERE, null, ex);
            }

            
        }
    }

}

