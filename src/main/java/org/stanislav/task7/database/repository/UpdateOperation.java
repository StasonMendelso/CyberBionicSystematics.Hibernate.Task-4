package org.stanislav.task7.database.repository;

/**
 * @author Stanislav Hlova
 */
public interface UpdateOperation<E, K> {
    void update(K id, E entity);
}
