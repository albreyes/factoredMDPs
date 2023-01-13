package planta;



import ia.FMDP;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.*;
import javax.swing.JPanel;


public class TableData extends JPanel  {

    private int filas;
    private int columnas;
    private Object datos;
    private FMDP fmdp;

    public TableData() {         
    }


    public void setDimension(int filas,int columnas){
        this.filas = filas;
        this.columnas = columnas;
    }

    public void setDatos(FMDP fmdp){
        this.fmdp =  fmdp;
    }

   public void contruyeTabla(){

    Object[][] data = new Object[fmdp.s.length][columnas];//[filas][columnas]

    for(int i=0;i<data.length;i++){
         for(int j=0;j<data.length;j++){
             if(columnas == 8){
                 if(j==0)
                    data[i][j] = fmdp.s[i][j];
                 if(j==1)
                    data[i][j] = fmdp.s[i][j];
                 if(j==2)
                    data[i][j] = fmdp.s[i][j];
                 if(j==3)
                    data[i][j] = fmdp.s[i][j];
                 if(j==4)
                    data[i][j] = fmdp.s[i][j];
                 if(j==5)
                    data[i][j] = fmdp.reward[i];
                 if(j==6)
                    data[i][j] = fmdp.utilidad[i];
                 if(j==7)
                    data[i][j] = fmdp.politica[i];
             }else{
                if(j==0)
                    data[i][j] = fmdp.s[i][j];
                if(j==1)
                    data[i][j] = fmdp.reward[i];
                 if(j==2)
                    data[i][j] = fmdp.utilidad[i];
                 if(j==3)
                    data[i][j] = fmdp.politica[i];

             }

         }
    }

        //array de String's con los títulos de las columnas

  JTable table=null;
    if(columnas ==8){
    String[] columnNames = {"msf","fwf","d","pd","g","Recompensa","Utilidad","Politica"};
    table= new JTable(data, columnNames);
    }

    if(columnas ==4){
    String[] columnNames = {"Q","Recompensa","Utilidad","Politica"};
    table= new JTable(data, columnNames);
    }

        //se crea la Tabla        
        table.setPreferredScrollableViewportSize(new Dimension(500,120));

        //Creamos un JscrollPane y le agregamos la JTable
        JScrollPane scrollPane = new JScrollPane(table);

        //Agregamos el JScrollPane al contenedor
        this.add(scrollPane, BorderLayout.CENTER);
       
    }

}