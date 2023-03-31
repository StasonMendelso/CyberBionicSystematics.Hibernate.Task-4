package org.stanislav.task7;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.stanislav.task7.database.repository.AnimalRepository;
import org.stanislav.task7.database.repository.CrudOperations;
import org.stanislav.task7.entity.Animal;

import java.util.Optional;

/**
 * @author Stanislav Hlova
 */
public class DemoTask7 {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = null;
        try {
            entityManagerFactory = Persistence.createEntityManagerFactory("hibernate-persistence-provider");

            CrudOperations<Animal, Integer> animalRepository = new AnimalRepository(entityManagerFactory);

            System.out.println("Creating animal");
            Animal animal = new Animal(10, "Dog", true);
            animalRepository.create(animal);

            Optional<Animal> animalOptional = animalRepository.read(1);
            if (animalOptional.isPresent()) {
                System.out.println("The animal was created: " + animalOptional.get());
            } else {
                System.out.println("Animal wasn't created. Exit program.");
                return;
            }
            System.out.println("Updating animal");
            Animal animalUpdate = new Animal(5, "Cat", true);
            animalRepository.update(1, animalUpdate);

            animalOptional = animalRepository.read(1);
            if (animalOptional.isPresent() && animalUpdate != animalOptional.get()) {
                System.out.println("The animal was updated: " + animalOptional.get());
            } else {
                System.out.println("Animal wasn't updated. Exit program.");
                return;
            }
            System.out.println("Deleting animal");
            animalRepository.delete(1);

            animalOptional = animalRepository.read(1);
            if (animalOptional.isPresent()) {
                System.out.println("The animal wasn't deleted: " + animalOptional.get());
            } else {
                System.out.println("Animal was deleted. Exit program.");
            }
        } finally {
            if (entityManagerFactory != null) {
                entityManagerFactory.close();
            }
        }
    }
}
