package bug.dao;

import bug.entity.BugEntity;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class BugDao {

    @PersistenceContext(unitName = "jbugs-persistence")
    EntityManager  entityManager;

    /**
     * Persist bug entity
     * @param newBugEntity the input entity to be persisted
     * */
    public void createBug(BugEntity newBugEntity){
        entityManager.persist(newBugEntity);
    }

    public List<BugEntity> getAllBugs() {
        return this.entityManager.createNamedQuery(BugEntity.GET_ALL_BUGS, BugEntity.class).getResultList();
    }

}
