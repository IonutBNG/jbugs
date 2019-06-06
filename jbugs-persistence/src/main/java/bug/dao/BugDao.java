package bug.dao;

import bug.entity.BugEntity;
import user.entity.UserEntity;
import utils.BugStatus;

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

    /**
     * Updates the status for the given BugEntity
     * @param bugEntity used for the status update
     */
    public void setStatus(BugEntity bugEntity){
        this.entityManager
                .createNamedQuery(BugEntity.SET_STATUS)
                .setParameter(BugEntity.STATUS, bugEntity.getStatus())
                .setParameter(BugEntity.ID, bugEntity.getId())
                .executeUpdate();
    }



}
