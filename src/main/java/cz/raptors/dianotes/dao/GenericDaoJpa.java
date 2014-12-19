package cz.raptors.dianotes.dao;

/**
 * Created by vamvda1 on 1.4.14.
 */

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import cz.raptors.dianotes.entities.AbstractEntity;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author @author stroja1,jecmja1
 *
 * @param <E>
 * @param <EX>
 */
public abstract class GenericDaoJpa<E extends AbstractEntity, EX extends Throwable>
        implements GenericDao<E, EX> {
    private static final Log LOGGER = LogFactory.getLog(GenericDaoJpa.class);

    @PersistenceContext
    protected EntityManager entityManager;

    @Override
    public void save(E entity) {
    	LOGGER.info("Saving entity: " + entity.toString());
        entityManager.persist(entity);
    }

    /**
     * Merge entities into database.
     *
     * @param entity
     * @return managed entities object from database.
     * @author stroja1
     */
    @Override
    public E merge(E entity) {
        return entityManager.merge(entity);
    }

    /**
     * Delete entities object from database.
     *
     * @param entity
     * @author stroja1
     */
    @Override
    public void delete(E entity) {
        entity = merge(entity);
        entityManager.remove(entity);
    }

    /**
     * Get entities from database by it's ID.
     *
     * @param id
     * @return
     * @author stroja1
     */
    @Override
    public E getById(long id) throws EX {

        E entity = entityManager.find(getEntityClass(), id);

        if (entity == null || !isValidEntity(entity)) {
            throw getException(getClass().getSimpleName() + " with id: '" + id
                    + "' is not found in database!");
        }

        return entity;
    }

    @Override
    public List<E> getAllRecords() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<E> query = criteriaBuilder.createQuery(getEntityClass());
        Root<E> root = query.from(getEntityClass());
        query.select(root);
        TypedQuery<E> typedQuery = entityManager.createQuery(query);
        return typedQuery.getResultList();
    }

    /**
     * Getting {@link TypedQuery} for named query.
     *
     * @param namedQuery
     * @return
     * @author stroja1
     */
    protected TypedQuery<E> getNamedQuery(String namedQuery) {
        return entityManager.createNamedQuery(namedQuery, getEntityClass());
    }

    /**
     * Getting {@link TypedQuery} for query.
     *
     * @param query
     * @return
     * @author stroja1
     */
    protected TypedQuery<E> getQuery(String query) {
        return entityManager.createQuery(query, getEntityClass());
    }

    /**
     * Entity class for current DAO.
     *
     * @return
     * @author stroja1
     */
    protected abstract Class<E> getEntityClass();

    /**
     * Exception for message.
     *
     * @return
     * @author stroja1
     */
    protected abstract EX getException(String message);

    /**
     * <p>
     * Getting entities
     * </p>
     *
     * @return
     * @author stroja1
     */
    protected String getEntityName() {
        return getEntityClass().getSimpleName();
    }

    /**
     * <p>
     * Getting valid entities from list of entities.
     * </p>
     *
     * @param entities
     * @return list of valid entities.
     * @author stroja1
     */
    protected List<E> getValidEntities(List<E> entities) {
        List<E> validEntities = new ArrayList<E>();

        for (E entity : entities) {
            if (entity != null && isValidEntity(entity)) {
                validEntities.add(entity);
            }
        }

        return validEntities;
    }

    /**
     * <p>
     * Indicate if entities is valid. Method for Overriding for custom validation
     * of entities.
     * </p>
     *
     * @param entity
     * @return always true
     * @author stroja1
     */
    protected boolean isValidEntity(E entity) {
        return true;
    }

    /**
     * This setter is used for testing.
     *
     * @param entityManager
     */
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
