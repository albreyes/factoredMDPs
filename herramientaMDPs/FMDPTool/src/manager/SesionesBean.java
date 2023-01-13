package manager;




import entity.Sesiones;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Query;

public class SesionesBean {

    private EntityManager em;

     public SesionesBean(EntityManagerFactory emf) {
        em = emf.createEntityManager();
     }

     public int guardarSesion(Sesiones p) {
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

      public int UpdateIntentos(Sesiones s,Long idSesion) {
          try {
            em.getTransaction().begin();
            Sesiones s2 = em.find(Sesiones.class, idSesion);

            s2.setNumIntentos(s.getNumIntentos());
            em.flush();
            em.getTransaction().commit();

            return 1;
        } catch (Exception e) {
            System.out.println( e.getMessage() );
            em.getTransaction().rollback();
            return 0;
         }
    }

       public int UpdateNivel(Sesiones s,Long idSesion) {
          try {
            em.getTransaction().begin();
            Sesiones s2 = em.find(Sesiones.class, idSesion);

            s2.setNivelusuario(s.getNivelusuario());
            em.flush();
            em.getTransaction().commit();

            return 1;
        } catch (Exception e) {
            System.out.println( e.getMessage() );
            em.getTransaction().rollback();
            return 0;
         }
    }

    public Long getSesionIdNivelNotNull(String nombreUsuario){
        Long s ;
        try{
            Query q = em.createQuery("SELECT COUNT(s.idsesion) FROM Sesiones s  "
                                   + " WHERE s.usuario.nombreUsuario=:nombreUsuario and s.nivelusuario IS NOT NULL");
            q.setParameter("nombreUsuario", nombreUsuario);
            s =  (Long) q.getSingleResult();
            if(s == null){
                return (long)0;
            }
            return s;
        }catch(NoResultException e){
            return (long)0;
        }catch(Exception e){
            System.err.println("error getSesionIdNivelNotNull " +e.getMessage() + " "+ e.getClass());
            return null;
        }
    }

     public Long getIdNivelNotNull(Long idSesion){
        Long s ;
        try{
            Query q = em.createQuery("SELECT COUNT(s.idsesion) FROM Sesiones s  "
                                   + " WHERE s.idsesion=:idsesion and s.nivelusuario.idnivel IS NOT NULL");


            q.setParameter("idsesion", idSesion);
            s =  (Long) q.getSingleResult();
            if(s == null){
                return (long)0;
            }
            return s;
        }catch(NoResultException e){
            return (long)0;
        }catch(Exception e){
            System.err.println("error getSesionIdNivelNotNull " +e.getMessage() + " "+ e.getClass());
            return null;
        }
    }

    public Sesiones getNivelActual(String nombreUsuario){

        Sesiones s = null;
        try{
            Query q = em.createQuery("SELECT s "
                                            + "FROM Sesiones s  "
                                            + " WHERE s.fecha = "
                                                           + " ( SELECT max(s2.fecha) "
                                                           + " FROM  Sesiones s2 WHERE s2.usuario.nombreUsuario = :nombreUsuario )");
                q.setParameter("nombreUsuario", nombreUsuario);
                s =  (Sesiones) q.getSingleResult();
                return s;
        }catch(NoResultException e){
            return null;
        }catch(Exception e){
            System.err.println("error getModoNivel "+e.getMessage() + " "+ e.getClass());
            return null;
        }
    }

     public List<Sesiones> getSesionesUsuario(String nombreUsuario){
        try{
            Query q = em.createQuery("SELECT s FROM Sesiones s where s.usuario.nombreUsuario=:nombreUsuario ");
            q.setParameter("nombreUsuario", nombreUsuario);
            return q.getResultList();
        }catch(NoResultException e){
            return null;
        }catch(Exception e){
            System.err.println("error getSesionesUsuario " +e.getMessage() + " "+ e.getClass());
            return null;
        }
    }

      public List<Sesiones> getSesionesUsuarioByNivel(String descripcion){
        try{
            Query q = em.createQuery("SELECT s FROM Sesiones s WHERE s.usuario.nivelusuario.descripcion=:descripcion ");
            q.setParameter("descripcion", descripcion);
            return q.getResultList();
        }catch(NoResultException e){
            return null;
        }catch(Exception e){
            System.err.println("error getSesionesUsuarioByNivel " +e.getMessage() + " "+ e.getClass());
            return null;
        }
    }



    

    public Long getCountSesionesUsuario(String nombreUsuario){
        Long s ;
        try{
            Query q = em.createQuery("SELECT COUNT(s.idsesion) AS TOTAL " +
                                     " FROM Sesiones s " +
                                     " WHERE s.usuario.nombreUsuario=:nombreUsuario ");
            q.setParameter("nombreUsuario", nombreUsuario);
            s =  (Long) q.getSingleResult();
            if(s == null){
                return (long)0;
            }
            return s;
        }catch(NoResultException e){
            return (long)0;
        }catch(Exception e){
            System.err.println("error getCountSesionesUsuario "+e.getMessage() + " "+ e.getClass());
            return null;
        }
    }


    public Long getMaxLevel(String nombreUsuario){
        Long s ;
        try{
            Query q = em.createQuery("SELECT MAX(s.nivelusuario.idnivel) AS TOTAL " +
                                     " FROM Sesiones s " +
                                     " WHERE s.usuario.nombreUsuario=:nombreUsuario");
            q.setParameter("nombreUsuario", nombreUsuario);
            s =  (Long) q.getSingleResult();
            if(s == null){
                return (long)0;
            }
            return s;
        }catch(NoResultException e){
            return (long)0;
        }catch(Exception e){
            System.err.println("error getMaxLevel " +e.getMessage() + " "+ e.getClass());
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
