package bug.dao;

import bug.entity.BugEntity;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class BugDao {

    @PersistenceContext(unitName = "jbugs-persistence")
    EntityManager  entityManager;

    /**
     * Persist bug entity
     * @param newBugEntity the input entity to be persisted
     * */
    public void createBug(BugEntity newBugEntity){
        System.out.println("Dao "+newBugEntity.toString());
        entityManager.persist(newBugEntity);
    }

}
