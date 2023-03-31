package org.stanislav.task6.ex_003_hibernate_get_and_insert;


import org.stanislav.task6.ex_003_hibernate_get_and_insert.entity.Author;
import org.stanislav.task6.ex_003_hibernate_get_and_insert.entity.Book;
import org.stanislav.task6.ex_003_hibernate_get_and_insert.repository.AuthorRepository;
import org.stanislav.task6.ex_003_hibernate_get_and_insert.repository.BookRepository;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

/**
 * Created by Asus on 01.11.2017.
 */
public class DemoTask6 {
    private static final String BOOK_PATTERN = "|%5s|%40s|%40s|\n";
    private static final String AUTHOR_PATTERN = "|%5s|%40s|\n";

    public static void main(String[] args) {
        AuthorRepository authorRepository = new AuthorRepository();
        BookRepository bookRepository = new BookRepository();

        Author author = new Author("Author1", null);
        Book book = new Book("Book1", author);
        author.setBookList(List.of(book));

        authorRepository.addAuthor(author);
        bookRepository.create(book);

        List<Book> books = bookRepository.readAll();
        List<Author> authors = authorRepository.getAuthorList();

        printAllBooks(books);
        printAllAuthors(authors);

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter an id of book for selecting from database:");
        int id = Integer.parseInt(scanner.nextLine());
        Optional<Book> bookOptional = bookRepository.read(id);
        if (bookOptional.isPresent()) {
            System.out.printf(BOOK_PATTERN, "id", "title", "author");
            System.out.printf(BOOK_PATTERN, book.getId(), book.getName(), book.getAuthor().getName());
        } else {
            System.out.println("No book was found.");
        }
    }

    private static void printAllBooks(List<Book> books) {
        System.out.println("All books from database:");
        System.out.printf(BOOK_PATTERN, "id", "title", "author");
        for (Book book : books) {
            System.out.printf(BOOK_PATTERN, book.getId(), book.getName(), book.getAuthor().getName());
        }
    }

    private static void printAllAuthors(List<Author> authors) {
        System.out.println("All authors from database:");
        System.out.printf(AUTHOR_PATTERN, "id", "name");
        for (Author author : authors) {
            System.out.printf(AUTHOR_PATTERN, author.getId(), author.getName());
        }
    }

}
