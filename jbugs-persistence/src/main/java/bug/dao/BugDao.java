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

    /**
     * Extracts all of the bugs from the database
     * @return <list>BugEntity</list>
     */
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


    /**
     * Gets the desired sublist by applying sort, filter and truncate operations on the list persisted in the database
     * @param field used for the sort operation
     * @param value used for the filter operation
     * @param startingItem used for setting the first item of the sublist as the nth(n = startingItem)
     * @param numberOfItems used for getting numberOfItems consecutive filtered items after the startingItem
     * @return <list>BugEntity</list>
     */
    public List<BugEntity> getSublist(String field, String value, int startingItem, int numberOfItems){
        return this.entityManager
                .createNativeQuery("Select * from jbugs.bugs where bugs.title like '%" + value + "%' order by bugs."+field, BugEntity.class)
                .setFirstResult(startingItem)
                .setMaxResults(numberOfItems)
                .getResultList();
    }


    /**
     * Edits the bug that is persisted in the databased using the new values based on his id
     * @param bugEntity used in the query
     */
    public void editBug(BugEntity bugEntity){
        this.entityManager
                .createNamedQuery(BugEntity.EDIT_BUG)
                .setParameter(BugEntity.TITLE, bugEntity.getTitle())
                .setParameter(BugEntity.DESCRIPTION, bugEntity.getDescription())
                .setParameter(BugEntity.VERSION, bugEntity.getVersion())
                .setParameter(BugEntity.FIXED_IN_VERSION, bugEntity.getFixedVersion())
                .setParameter(BugEntity.SEVERITY, bugEntity.getSeverity())
                .setParameter(BugEntity.STATUS, bugEntity.getStatus())
                .setParameter(BugEntity.ASSIGNED_TO, bugEntity.getAssignedTo())
                .setParameter(BugEntity.ID, bugEntity.getId());
    }

}
