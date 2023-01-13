package manager;


import entity.TablaCtcc;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;

public class TablaCtccBean {

    private EntityManager em;


     public TablaCtccBean(EntityManagerFactory emf) {
        em = emf.createEntityManager();
     }

     public TablaCtccBean(EntityManager em) {
         this.em = em;
        //em = DatosPersistence.emf.createEntityManager();
     }

     public int guardarTablaCtcc(TablaCtcc p) {
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

     public int UpdateValor(String variable, double valor) {
          try {
            em.getTransaction().begin();
            TablaCtcc t = em.find(TablaCtcc.class, variable);

            t.setValor(valor);
            em.flush();
            em.refresh(t);
            t.getValor();
            em.getTransaction().commit();

            return 1;
        } catch (Exception e) {
            System.out.println( e.getMessage() );
            em.getTransaction().rollback();
            return 0;
         }finally{
            em.close();
         }
    }

     public int UpdateDescripcion(String variable, String descripcion) {
          try {
            em.getTransaction().begin();
            TablaCtcc t = em.find(TablaCtcc.class, variable);

            t.setDescripcion(descripcion);
            em.flush();
            em.getTransaction().commit();

            return 1;
        } catch (Exception e) {
            System.out.println( e.getMessage() );
            em.getTransaction().rollback();
            return 0;
         }
    }

     public TablaCtcc getValor(String variable) {
          try {
              em.getTransaction().begin();

                TablaCtcc t  = new TablaCtcc();
                 t = em.find(TablaCtcc.class, variable);
                 em.refresh(t);
                if(t == null){
               
                    return null;
                }
             em.getTransaction().commit();
            return t;
        } catch(NoResultException e){
            return null;
        } catch (Exception e) {
            System.out.println( e.getMessage() );
            em.getTransaction().rollback();
            return null;
         }
    }


}
