package org.stanislav.task7.database.repository;

/**
 * @author Stanislav Hlova
 */
public interface CreateOperation<E> {
    void create(E entity);
}
