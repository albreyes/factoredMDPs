package manager;

import entity.Acciones;
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
public class AccionesBean {

    private EntityManager em;

     public AccionesBean(EntityManagerFactory emf) {
        em = emf.createEntityManager();
     }

     public List<Acciones> findAll() {
            List<Acciones> emp = null;
            try {

                Query query =em.createNamedQuery("Acciones.findAll");
                emp =  query.getResultList();
                return emp;
            } catch (NoResultException ex) {
                System.out.println( "error en la busqueda " +ex.getMessage() +" " + ex.getClass()  );
                return emp;
            }
    }

     public Acciones findOne(String var) {
            Acciones emp = null;
            try {

                Query query =em.createNamedQuery("Acciones.findByIdaccion");
                query.setParameter("idaccion", var);
                emp = (Acciones) query.getSingleResult();
                return emp;
            } catch (NoResultException ex) {
                System.out.println( "error en la busqueda " +ex.getMessage()+" " + ex.getClass() );
                return emp;
            }
    }

}
