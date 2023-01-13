/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebas;

/**
 *
 * @author Home
 */
public class PruebaEstadoHidro {
    
    public static boolean agendasVacias(EstadoHidros eh[]){
        
        boolean resp=true;
        for(int i=0; i<eh.length; i++){
            if(!eh[i].agendaVacia()){
                resp=false;
                break;
            }
            
        }
        return resp;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        //int estados[]={1,3,5};
        int estados[]=new int[2500];
        EstadoHidros eh[]=new EstadoHidros[estados.length];
        int acciones[]={1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27};
        int s,a;
        
        // se crean estados con sus agendas
        for(int i=0;i<estados.length; i++)
            eh[i]=new EstadoHidros(acciones);
        
        while (!agendasVacias(eh)) {
            
            s = (int) (Math.random() * estados.length);
            if (eh[s].agendaVacia()) {
                a = (int) (Math.random() * acciones.length);
            } else {
                a = eh[s].accionAEjecutar();
            }
            System.out.println("ejecutando accion " + a + " en estado " + s);
        }
        
    }
    
}
