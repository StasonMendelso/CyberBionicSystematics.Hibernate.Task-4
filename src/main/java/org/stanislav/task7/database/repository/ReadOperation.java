package org.stanislav.task7.database.repository;

import java.util.Optional;

/**
 * @author Stanislav Hlova
 */
public interface ReadOperation<E,K> {
    Optional<E> read(K id);
}
