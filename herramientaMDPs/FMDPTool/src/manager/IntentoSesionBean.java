package manager;




import entity.IntentosSesion;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Query;

public class IntentoSesionBean {

    private EntityManager em;

     public IntentoSesionBean(EntityManagerFactory emf) {
        em = emf.createEntityManager();
     }

     public int guardarIntentoSesion(IntentosSesion p) {
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

     public Long getCountIntentos(Long idsesion){
        Long s ;
        try{
            Query q = em.createQuery("SELECT COUNT(s.idintento) AS TOTAL " +
                                     " FROM IntentosSesion s " +
                                     " WHERE s.sesiones.idsesion=:idsesion ");

            q.setParameter("idsesion", idsesion);
            s =  (Long) q.getSingleResult();
            if(s == null){
                return (long)0;
            }
            return s;
        }catch(NoResultException e){
            return (long)0;
        }catch(Exception e){
            System.err.println("error getCountSesionesUsuario " +e.getMessage());
            return null;
        }
    }

     public List<IntentosSesion> getDatosSesionUsuario(Long idsesion){

        try{
            Query q = em.createQuery(" SELECT ins " +
                     " FROM IntentosSesion ins " +
                     " WHERE ins.sesiones.idsesion =:idsesion " );
            q.setParameter("idsesion", idsesion);
            return  q.getResultList();
        }catch(NoResultException e){
            return null;
        }catch(Exception e){
            System.err.println("error getDatosSesionUsuario " +e.getMessage());
            return null;
        }
    }

     public Object last_insert_idMySQL() {
        Query query = em.createNativeQuery(" select last_insert_id()");
        Object listOfActors = query.getSingleResult();
        return listOfActors;
     }

     public Object last_insert_idMSSQL() {
        Query query = em.createNativeQuery(" select last_insert_id()");
        Object listOfActors = query.getSingleResult();
        return listOfActors;
     }

    

 

}
