package cz.raptors.dianotes.dao;

import java.util.List;

/**
 * Created by vamvda1 on 1.4.14.
 */
public interface GenericDao<E, EX extends Throwable>  {
    /**
     * Persist entities into database.
     *
     * @param entity new entities that will be persisted into db.
     */
    void save(E entity);

    /**
     * Merge entities into database.
     *
     * @param entity
     * @return managed entities object from database.
     * @author stroja1
     */
    E merge(E entity);

    /**
     * Delete entities object from database.
     *
     * @param entity
     * @author stroja1
     */
    void delete(E entity);

    /**
     * Find entities from database by it's ID.
     *
     * @param id
     * @return
     * @author stroja1
     */
    E getById(long id) throws EX;

    public List<E> getAllRecords();
}
