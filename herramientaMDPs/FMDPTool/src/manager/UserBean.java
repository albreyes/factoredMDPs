package manager;




import entity.Usuario;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Query;

public class UserBean {

    private EntityManager em;

     public UserBean(EntityManagerFactory emf) {
        em = emf.createEntityManager();
     }

     public int guardarUsuario(Usuario p) {
          try {
            em.getTransaction().begin();
            em.persist(p);
            em.flush();
            em.getTransaction().commit();
            return 1;
        } catch (Exception e) {
            System.out.println( e.getMessage() );
            em.getTransaction().rollback();
            return 0;
         }
    }

     public int UpdateNivel(String nombreUsuario,Long idNivel) {
          try {
            em.getTransaction().begin();
            Usuario u2 = em.find(Usuario.class, nombreUsuario);
            u2.getNivelusuario().setIdnivel(idNivel);
            em.flush();
            em.getTransaction().commit();

            return 1;
        } catch (Exception e) {
            System.out.println( e.getMessage() );
            em.getTransaction().rollback();
            return 0;
         }
    }

     public Usuario findByUsuarioPass(String nombreUsuario,String contra) {
            Usuario emp = null;
            try {

                Query query =em.createNamedQuery("Usuario.findByContraPass");
                query.setParameter("contra", contra);
                query.setParameter("nombreUsuario", nombreUsuario);
                emp = (Usuario) query.getSingleResult();
                return emp;
            } catch (NoResultException ex) {
                System.out.println( "error en la busqueda " +ex.getMessage() );
                return emp;
            }
    }

     public Usuario findByUserName(String nombreUsuario) {
            Usuario emp = null;
            try {

                Query query =em.createNamedQuery("Usuario.findByNombreUsuario");
                query.setParameter("nombreUsuario", nombreUsuario);
                emp = (Usuario) query.getSingleResult();
                return emp;
            } catch (NoResultException ex) {
                System.out.println( "error en la busqueda " +ex.getMessage() );
                return emp;
            }
    }

     public List<Usuario> findAll() {
            List<Usuario> emp = null;
            try {

                Query query =em.createNamedQuery("Usuario.findAll");
                emp =  query.getResultList();
                return emp;
            } catch (NoResultException ex) {
                System.out.println( "error en la busqueda " +ex.getMessage() );
                return emp;
            }
    }

    public Usuario find(Long nombreUsuario) {//quitar
            Usuario cliente = null;
            try {
                return em.find(Usuario.class, nombreUsuario);
            } catch (Exception e) {
                System.out.println( "error en la busqueda " +e.getMessage() );
                return cliente;
            }
    }

     public Usuario getNivelInicial(String nombreUsuario){

        Usuario u = null;
        try{
            Query q = em.createQuery("SELECT u "
                                            + "FROM Usuario u  "
                                            + " WHERE u.nombreUsuario= :nombreUsuario ");
                q.setParameter("nombreUsuario", nombreUsuario);
                u =  (Usuario) q.getSingleResult();
                return u;
        }catch(NoResultException e){
            return null;
        }catch(Exception e){
            System.err.println("error getModoNivel " +e.getMessage() + " "+ e.getClass());
            return null;
        }
    }




     public Object last_insert_id() {
        Query query = em.createNativeQuery(" select last_insert_id()");
        //query.executeUpdate();
        Object listOfActors = query.getSingleResult();
        return listOfActors;
     }



}
