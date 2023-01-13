package manager;

import entity.Flow;
import entity.Generation;
import entity.Level;
import entity.Pressure;
import entity.Variables;
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
public class VariablesBean {

    private EntityManager em;

     public VariablesBean(EntityManagerFactory emf) {
        em = emf.createEntityManager();
     }

     public List<Variables> findAll() {
            List<Variables> emp = null;
            try {

                Query query =em.createNamedQuery("Variables.findAll");
                emp =  query.getResultList();
                return emp;
            } catch (NoResultException ex) {
                System.out.println( "error en la busqueda " +ex.getMessage() );
                return emp;
            }
    }

     public Variables findOne(String var) {
            Variables emp = null;
            try {

                Query query =em.createNamedQuery("Variables.findByIdvariable");
                query.setParameter("idvariable", var);
                emp = (Variables) query.getSingleResult();
                return emp;
            } catch (NoResultException ex) {
                System.out.println( "error en la busqueda " +ex.getMessage() );
                return emp;
            }
    }

     public Flow findOneFlow(String var) {
            Flow emp = null;
            try {

                Query query =em.createNamedQuery("Flow.findByIdvariable");
                query.setParameter("idvariable", var);
                emp = (Flow) query.getSingleResult();
                return emp;
            } catch (NoResultException ex) {
                System.out.println( "error en la busqueda " +ex.getMessage() );
                return emp;
            }
    }

     public Generation findOneGeneration(String var) {
            Generation emp = null;
            try {

                Query query =em.createNamedQuery("Generation.findByIdvariable");
                query.setParameter("idvariable", var);
                emp = (Generation) query.getSingleResult();
                return emp;
            } catch (NoResultException ex) {
                System.out.println( "error en la busqueda " +ex.getMessage() );
                return emp;
            }
    }

    public Pressure findOnePressure(String var) {
            Pressure emp = null;
            try {

                Query query =em.createNamedQuery("Pressure.findByIdvariable");
                query.setParameter("idvariable", var);
                emp = (Pressure) query.getSingleResult();
                return emp;
            } catch (NoResultException ex) {
                System.out.println( "error en la busqueda " +ex.getMessage() );
                return emp;
            }
    }

    public Level findOneLevel(String var) {
            Level emp = null;
            try {

                Query query =em.createNamedQuery("Level.findByIdvariable");
                query.setParameter("idvariable", var);
                emp = (Level) query.getSingleResult();
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
