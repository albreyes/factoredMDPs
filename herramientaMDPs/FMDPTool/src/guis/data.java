/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guis;

import utileria.ClientTCP;
import utileria.Listas;
import ia.FMDP;
import ia.ServidorPolitica;
import javax.swing.JOptionPane;
import guis.ServidorPoliticaFrame;
import static guis.ServidorPoliticaFrame.policyFile;
import java.io.File;
import javax.swing.JFileChooser;

/**
 *
 * @author Alejandro
 */

public class data {
    String estados= "";
    String estado2="";
    
    
    
    public void getData(){
        
        System.out.print("Inicia\n");

        
        estados=ClienteFrame.String_fact.getText();
        estado2=estados.replace("S", "");
        String[] strEst = estado2.split(",");
        
        int length = strEst.length;
       
        int[] arrayEstado = new int[length];
        
        for(int i=0; i<length;i++){
            arrayEstado[i]=Integer.parseInt(strEst[i]);
        }
        
        //String policyFilename="C:\\Users\\Alejandro\\Documents\\Pasantia\\INEEL\\herramienta\\herramientaMDPs\\ejemplos\\Ej6\\fmdp.obj";
       
        String policyFilename=policyFile;
        
        //String policyFilename="C:\\Users\\Alejandro\\Documents\\Pasantia\\INEEL\\herramienta\\herramientaMDPs\\ejemplos\\Ej6\\fmdp.obj";
        
        FMDP fmdp=FMDP.retrieveMDP(policyFilename);
        //FMDP fmdp=FMDP.retrieveMDP(policyFilename);
      
        
        ClientTCP client=new ClientTCP("localhost","5000");   
        
        if (fmdp.s[1].length==length){
        
             //ClienteFrame.EstadoMod.setText(Listas.imprimeLista(fmdp.s[i]));
            for(int i=0; i<fmdp.s.length;i++){
            
                if (Listas.esIgual(fmdp.s[i], arrayEstado)){
                    //--------------------------------------------
                    System.out.print("estado ");
                    Listas.imprimeLista(fmdp.s[i]);
                    //--------------------------------------------
                    client.send(fmdp.s[i]);
                    int pol=client.receiveIntSpecial();
                    String Ltext=Listas.intArray2String(fmdp.s[i]);
                    //---Imprime en pantalla-----------------------------------------
                    ClienteFrame.EstadoMod.setText(Ltext);
                    ClienteFrame.PolMod.setText(Integer.toString(pol));
                    System.out.println("politica: "+pol);
                }
            }
        }
        else{
            ClienteFrame.MensajeE.setText("Error de Dimensión");
        }  
    }
    

}
