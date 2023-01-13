package manager;

import entity.Nivelusuario;
import entity.Tipousuarios;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author lilith
 */
public class NivelUsuarioBean {

    private EntityManager em;

     public NivelUsuarioBean(EntityManagerFactory emf) {
        em = emf.createEntityManager();
     }

     public List<Nivelusuario> findAll() {
            List<Nivelusuario> emp = null;
            try {

                Query query =em.createNamedQuery("Nivelusuario.findAll");
                emp =  query.getResultList();
                return emp;
            } catch (NoResultException ex) {
                System.out.println( "error en la busqueda " +ex.getMessage() + " " +ex.getCause() );
                return emp;
            }
    }

     public Nivelusuario findByDescripcion(String tipo) {
            Nivelusuario emp = null;
            try {

                Query query =em.createNamedQuery("Nivelusuario.findByDescripcion");
                query.setParameter("descripcion", tipo);
                emp = (Nivelusuario) query.getSingleResult();
                return emp;
            } catch (NoResultException ex) {
                System.out.println( "error en la busqueda " +ex.getMessage() );
                return emp;
            }
    }

     public Long descripcionToID(String tipo) {
            Nivelusuario emp = null;
            try {

                Query query =em.createNamedQuery("Nivelusuario.findByDescripcion");
                query.setParameter("descripcion", tipo);
                emp = (Nivelusuario) query.getSingleResult();
                if(emp!= null)
                    return emp.getIdnivel();
                return null;
            } catch (NoResultException ex) {
                System.out.println( "error en la busqueda " +ex.getMessage() );
                return null;
            }
    }

}
