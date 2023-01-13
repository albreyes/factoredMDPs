package manager;

import entity.Componentes;
import entity.ControlValve;
import entity.Drum;
import entity.ShutdownValve;
import entity.Turbine;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author lilith
 */
public class ComponentesBean {

    private EntityManager em;

     public ComponentesBean(EntityManagerFactory emf) {
        em = emf.createEntityManager();
     }

     public List<Componentes> findAll() {
            List<Componentes> emp = null;
            try {

                Query query =em.createNamedQuery("Componentes.findAll");
                emp =  query.getResultList();
                return emp;
            } catch (NoResultException ex) {
                System.out.println( "error en la busqueda " +ex.getMessage() +" " + ex.getClass()  );
                return emp;
            }
    }

     public Componentes findOne(String idcomponente) {
            Componentes emp = null;
            try {

                Query query =em.createNamedQuery("Componentes.findByIdcomponente");
                query.setParameter("idcomponente", idcomponente);
                emp = (Componentes) query.getSingleResult();
                return emp;
            } catch (NoResultException ex) {
                System.out.println( "error en la busqueda " +ex.getMessage()+" " + ex.getClass() );
                return emp;
            }
    }

     public  List<Componentes> findByIdVariable(String variable){
         List<Componentes> emp = null;
         try {
                 Query q = em.createQuery("SELECT c "
                                            + " FROM Componentes c JOIN  "
                                            + " c.variablesCollection vc"
                                            + " WHERE vc.idvariable=:idvariable");
                q.setParameter("idvariable", variable);
                emp =   q.getResultList();
                return emp;
            } catch (NoResultException ex) {
                System.out.println( "error en la busqueda " +ex.getMessage()+" " + ex.getClass() );
                return emp;
            }catch (Exception e){
                System.out.print(e.getMessage());
                return emp;
            }

    }

    public Turbine findOneTurbine(String idcomponente) {
            Turbine emp = null;
            try {

                Query query =em.createNamedQuery("Turbine.findByIdcomponente");
                query.setParameter("idcomponente", idcomponente);
                emp = (Turbine) query.getSingleResult();
                return emp;
            } catch (NoResultException ex) {
                System.out.println( "error en la busqueda " +ex.getMessage() );
                return emp;
            }
    }

    public ControlValve findOneControlValve(String idcomponente) {
            ControlValve emp = null;
            try {

                Query query =em.createNamedQuery("ControlValve.findByIdcomponente");
                query.setParameter("idcomponente", idcomponente);
                emp = (ControlValve) query.getSingleResult();
                return emp;
            } catch (NoResultException ex) {
                System.out.println( "error en la busqueda " +ex.getMessage() );
                return emp;
            }
    }

    public ShutdownValve findOneShutDownValve(String idcomponente) {
            ShutdownValve emp = null;
            try {
                Query query =em.createNamedQuery("ShutdownValve.findByIdcomponente");
                query.setParameter("idcomponente", idcomponente);
                emp = (ShutdownValve) query.getSingleResult();
                return emp;
            } catch (NoResultException ex) {
                System.out.println( "error en la busqueda " +ex.getMessage() );
                return emp;
            }
    }

     public Drum findOneDrum(String idcomponente) {
            Drum emp = null;
            try {
                Query query =em.createNamedQuery("Drum.findByIdcomponente");
                query.setParameter("idcomponente", idcomponente);
                emp = (Drum) query.getSingleResult();
                return emp;
            } catch (NoResultException ex) {
                System.out.println( "error en la busqueda " +ex.getMessage() );
                return emp;
            }
    }

    public BufferedImage getImagen(byte[] imagen) {

      ByteArrayInputStream bis = new ByteArrayInputStream(imagen);
      Iterator readers = ImageIO.getImageReadersByFormatName("jpeg");
      ImageReader reader = (ImageReader) readers.next();
      Object source = bis; // File o InputStream
      ImageInputStream iis;
      BufferedImage imagen2 = null;

        try {

            iis = ImageIO.createImageInputStream(source);
            reader.setInput(iis, true);
            ImageReadParam param = reader.getDefaultReadParam();
            imagen2 = reader.read(0, param);
            return imagen2;

        } catch (IOException ex) {
            return imagen2;
        }
    }
}
