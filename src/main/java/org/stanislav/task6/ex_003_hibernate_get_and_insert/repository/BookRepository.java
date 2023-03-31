package org.stanislav.task6.ex_003_hibernate_get_and_insert.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.stanislav.task6.ex_003_hibernate_get_and_insert.entity.Book;

import java.util.List;
import java.util.Optional;

/**
 * @author Stanislav Hlova
 */
public class BookRepository {
    private final SessionFactory sessionFactory;

    public BookRepository() {
        this.sessionFactory = HibernateUtil.getSessionFactory();
    }

    public List<Book> readAll() {
        List<Book> books;
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            books = session.createQuery("FROM Book", Book.class).getResultList();
            session.getTransaction().commit();
        }
        return books;
    }

    public void create(Book book) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(book);
            session.getTransaction().commit();
        }
    }
    public Optional<Book> read(int id){
        Optional<Book> result;
        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            result = Optional.ofNullable(session.get(Book.class,id));
            session.getTransaction().commit();
        }
        return result;
    }
}
