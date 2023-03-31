package org.stanislav.task7.database.repository;

/**
 * @author Stanislav Hlova
 */
public interface DeleteOperation<K> {
    void delete(K id);
}
