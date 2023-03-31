package org.stanislav.task7.database.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.stanislav.task7.entity.Animal;

import java.util.Optional;

/**
 * @author Stanislav Hlova
 */
public class AnimalRepository implements CrudOperations<Animal, Integer> {
    private final EntityManagerFactory entityManagerFactory;

    public AnimalRepository(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public void create(Animal entity) {
        EntityManager entityManager = null;
        try {
            entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();

            entityManager.persist(entity);

            entityManager.getTransaction().commit();
        } finally {
            closeEntityManager(entityManager);
        }
    }


    @Override
    public void delete(Integer key) {
        EntityManager entityManager = null;
        try {
            entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();

            entityManager.remove(entityManager.find(Animal.class, key));

            entityManager.getTransaction().commit();
        } finally {
            closeEntityManager(entityManager);
        }
    }

    @Override
    public Optional<Animal> read(Integer id) {
        Optional<Animal> result;
        EntityManager entityManager = null;
        try {
            entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();

            result = Optional.ofNullable(entityManager.find(Animal.class, id));

            entityManager.getTransaction().commit();
        } finally {
            closeEntityManager(entityManager);
        }
        return result;
    }

    @Override
    public void update(Integer id, Animal entity) {
        EntityManager entityManager = null;
        try {
            entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();

            entity.setId(id);
            entityManager.merge(entity);

            entityManager.getTransaction().commit();
        } finally {
            closeEntityManager(entityManager);
        }
    }

    private void closeEntityManager(EntityManager entityManager) {
        if (entityManager != null) {
            entityManager.close();
        }
    }
}
