package org.stanislav.task6.ex_003_hibernate_get_and_insert.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.stanislav.task6.ex_003_hibernate_get_and_insert.entity.Author;

import java.util.List;


/**
 * Created by Asus on 01.11.2017.
 */
public class AuthorRepository {

    private SessionFactory sessionFactory;

    public AuthorRepository() {
        sessionFactory = HibernateUtil.getSessionFactory();
    }

    public List<Author> getAuthorList() {
        Session session = sessionFactory.openSession();

        List<Author> authorList = session.createQuery("FROM Author", Author.class).getResultList();

        session.close();

        return authorList;
    }

    public Author getAuthorById(long id) {
        Session session = sessionFactory.openSession();
        Author author = (Author) session.get(Author.class, id); // получение объекта по id
        return author;
    }

    public Author addAuthor(Author author) {

        Session session = sessionFactory.openSession();

        session.beginTransaction();

        session.save(author); // сгенерит ID и вставит в объект

        session.getTransaction().commit();

        session.close();

        return author;

    }
}
