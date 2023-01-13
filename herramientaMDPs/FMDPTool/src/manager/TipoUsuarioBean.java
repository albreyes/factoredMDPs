package manager;

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
public class TipoUsuarioBean {

    private EntityManager em;

     public TipoUsuarioBean(EntityManagerFactory emf) {
        em = emf.createEntityManager();
     }

     public List<Tipousuarios> findAll() {
            List<Tipousuarios> emp = null;
            try {

                Query query =em.createNamedQuery("Tipousuarios.findAll");
                emp =  query.getResultList();
                return emp;
            } catch (NoResultException ex) {
                System.out.println( "error en la busqueda " +ex.getMessage() );
                return emp;
            }
    }

     public Tipousuarios findByDescripcion(String tipo) {
            Tipousuarios emp = null;
            try {

                Query query =em.createNamedQuery("Tipousuarios.findByDescripcion");
                query.setParameter("descripcion", tipo);
                emp = (Tipousuarios) query.getSingleResult();
                return emp;
            } catch (NoResultException ex) {
                System.out.println( "error en la busqueda " +ex.getMessage() );
                return emp;
            }
    }

     public Long descripcionToID(String tipo) {
            Tipousuarios emp = null;
            try {

                Query query =em.createNamedQuery("Tipousuarios.findByDescripcion");
                query.setParameter("descripcion", tipo);
                emp = (Tipousuarios) query.getSingleResult();
                if(emp!= null)
                    return emp.getIdTipoUsuario();
                return null;
            } catch (NoResultException ex) {
                System.out.println( "error en la busqueda " +ex.getMessage() );
                return null;
            }
    }

}
