package org.stanislav.task7.database.repository;

/**
 * @author Stanislav Hlova
 */
public interface CrudOperations<E, K> extends CreateOperation<E>, ReadOperation<E, K>, UpdateOperation<E, K>, DeleteOperation<K> {
}
