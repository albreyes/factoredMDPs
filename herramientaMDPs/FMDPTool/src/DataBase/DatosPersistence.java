package DataBase;

import java.util.Properties;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Liliana Sánchez R.
 */
public class DatosPersistence {

	private  static String mensaje       = "";
        private  static String driver        = "";
        private  static String URL           = "";
        private  static DatosPersistence conexion    = null;
        private static int statusConexion = -1;
        public static int tipoServidor = 0;
        public static EntityManagerFactory emf;


    public static void DatosPersistence_(String host, String user,String password, String port,String nameDB,String instancia,int tipoServer) {

        try{
        Properties props = new Properties();
        props.put("javax.persistence.jdbc.user",user);
        props.put("javax.persistence.jdbc.password",password);

            if(tipoServer == 1){
                driver = "com.mysql.jdbc.Driver";
                URL    = "jdbc:mysql://"+host+":"+port+"/"+nameDB;
            }else if(tipoServer == 0){
                driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
                URL = "jdbc:sqlserver://"+host+"\\"+instancia+":"+port+";databaseName="+nameDB;
            }else if(tipoServer == 2){
                driver = "sun.jdbc.odbc.JdbcOdbcDriver";
                URL    = "jdbc:odbc:" + host;
            }else if(tipoServer == 3){
                driver = "sun.jdbc.odbc.JdbcOdbcDriver";
                URL    = "jdbc:odbc:" + host;
            }

        props.put("javax.persistence.jdbc.url",URL);
        props.put("javax.persistence.jdbc.driver",driver);
        
        emf = Persistence.createEntityManagerFactory("AsistoFinalPU",props);

            statusConexion = 0;
        }catch(Exception ex) {
            statusConexion = 1;
            mensaje = ex.getMessage();
            System.out.println(ex.getMessage());
        }
      
    }


    public static DatosPersistence getConexion(){
        if(conexion == null ){
            conexion = new DatosPersistence();
	}
	return conexion;
    }

    public static int getStatusConexion() {
        return statusConexion;
    }

    public  static String getMensaje() {
	return mensaje;
    }





}
