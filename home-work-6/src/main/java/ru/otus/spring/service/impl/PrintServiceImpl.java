package ru.otus.spring.service.impl;

import org.springframework.stereotype.Service;
import ru.otus.spring.model.Author;
import ru.otus.spring.model.Book;
import ru.otus.spring.model.Comment;
import ru.otus.spring.model.Genre;
import ru.otus.spring.service.PrintService;

import java.util.List;

@Service
public class PrintServiceImpl implements PrintService {

    private final static String ALL_BOOKS_MESSAGE = "Все книги в библиотеке: ";
    private final static String ALL_AUTHORS_MESSAGE = "Все авторы книг в библиотеке: ";
    private final static String ALL_GENRE_MESSAGE = "Все жанры книг в библиотеке: ";
    private final static String BOOK_IDENT = "book";
    private final static String AUTHOR_IDENT = "author";
    private final static String GENRE_IDENT = "genre";

    @Override
    public void print(List<?> objectList, String objectName) {
        switch (objectName){
            case (BOOK_IDENT):
                print(ALL_BOOKS_MESSAGE);
                break;
            case (AUTHOR_IDENT):
                print(ALL_AUTHORS_MESSAGE);
                break;
            case (GENRE_IDENT):
                print(ALL_GENRE_MESSAGE);
                break;
            default:

                break;
        }
        objectList.forEach(o ->{
            if (o.getClass().equals(Book.class)) {
                Book book = (Book) o;
                printBook(book);
            } else if (o.getClass().equals(Author.class)){
                Author author = (Author) o;
                printAuthor(author);
            } else if (o.getClass().equals(Genre.class)){
                Genre genre = (Genre) o;
                printGenre(genre);
            }
        });
    }

    @Override
    public void print(String str){
        System.out.println(str);
    }

    private void printBook(Book book){
        System.out.println(book.getId() + "     " + "'" + book.getBookName() + "'" + "      " +
                "'" + book.getAuthor().getAuthorName() + "'" + "        " + "'" + book.getGenre().getGenreName() + "'"
                +  "        " + "'" + getComments(book.getComments())  + "'");
    }

    private void printAuthor(Author author){
        System.out.println(author.getId() + "       " + "'" + author.getAuthorName() + "'");
    }

    private void printGenre(Genre genre){
        System.out.println(genre.getId() + "        " + "'" + genre.getGenreName() + "'");
    }

    private String getComments(List<Comment> comments){
        StringBuilder sb = new StringBuilder();
        comments.forEach(c ->{
            sb.append(c.getComment());
            sb.append("; ");
        });
        return sb.substring(0, sb.length() - 2);
    }

}
