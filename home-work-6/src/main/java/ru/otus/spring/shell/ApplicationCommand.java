package ru.otus.spring.shell;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.spring.dao.AuthorDao;
import ru.otus.spring.dao.BookDao;
import ru.otus.spring.dao.GenreDao;
import ru.otus.spring.model.Author;
import ru.otus.spring.model.Book;
import ru.otus.spring.model.Genre;
import ru.otus.spring.service.*;


@ShellComponent
public class ApplicationCommand {

    private final static String BOOK_IDENT = "book";
    private final static String AUTHOR_IDENT = "author";
    private final static String GENRE_IDENT = "genre";
    private final static String GENERATED_RECORD_ERROR = "error";

    private final PrintService printService;
   // private final DaoService daoService;
    private final BookDaoService bookDaoService;
    private final AuthorDaoService authorDaoService;
    private final GenreDaoService genreDaoService;

    public ApplicationCommand(PrintService printService,
                             /* DaoService daoService,*/
                              BookDaoService bookDaoService,
                              AuthorDaoService authorDaoService,
                              GenreDaoService genreDaoService){
        this.printService = printService;
       // this.daoService = daoService;
        this.bookDaoService = bookDaoService;
        this.authorDaoService = authorDaoService;
        this.genreDaoService = genreDaoService;
    }

    @ShellMethod(value = "Show All Books", key = {"showBooks", "sb", "showB"})
    public void showAllBooks(){
        printService.print(bookDaoService.findAll(), BOOK_IDENT);
    }

    @ShellMethod(value = "Show All Authors", key = {"showAuthors", "sa", "showA"})
    public void showAllAuthors(){
        printService.print(authorDaoService.findAll(), AUTHOR_IDENT);
    }

    @ShellMethod(value = "Show All Genres", key = {"showGenres", "sg", "showG"})
    public void showAllGenres(){
        printService.print(genreDaoService.findAll(), GENRE_IDENT);
    }

    @ShellMethod(value = "Find Book by Author", key = {"findBookAuthor", "fba", "findBA"})
    public void findBookByAuthor(String authorName){
        try {
            Author author = authorDaoService.findAuthorByName(authorName);
            printService.print(bookDaoService.findBooksByAuthor(author), BOOK_IDENT);
        } catch (EmptyResultDataAccessException e){
            printService.print("Книг с таким автором не найдено.");
        }
    }

    @ShellMethod(value = "Find Book by Genre", key = {"findBookGenre", "fbg", "findBG"})
    public void findBookByGenre(String genreName){
        try {
            Genre genre = genreDaoService.findGenreByName(genreName);
            printService.print(bookDaoService.findBooksByGenre(genre), BOOK_IDENT);
        } catch (EmptyResultDataAccessException e){
            printService.print("Книг такого литературного жанра не найдено.");
        }
    }

    @ShellMethod(value = "Add new Book", key = {"addBook", "ab", "addB"})
    public void addNewBook(String bookName, String authorName, String genreName){
        Book book = new Book();
        book.setBookName(bookName);
        book.setAuthor(createAuthor(authorName));
        book.setGenre(createGenre(genreName));
        try{
            bookDaoService.addBook(book);
            printService.print("Новая книга успешно добавлена в базу.");
        } catch (RuntimeException e){
            switch (e.getMessage()) {
                case BOOK_IDENT:
                    printService.print("Книга с таким названием уже существует в базе.");
                    break;
                case AUTHOR_IDENT:
                    printService.print("Автора с таким именем нет в базе. Сначала надо добавить этого автора в базу.");
                    break;
                case GENRE_IDENT:
                    printService.print("Жанра литературы с таким именем нет в базе. Сначала надо добавить этот жанр в базу.");
                    break;
            }
        }
    }

    @ShellMethod(value = "Add new Author", key = {"addAuthor", "aa", "addA"})
    public void addNewAuthor(String authorName){
        try {
            authorDaoService.addAuthor(createAuthor(authorName));
            printService.print("Новый автор успешно добавлен в базу.");
        } catch (RuntimeException e){
            printService.print("Автор с таким именем уже существует в базе. Поробуйте задать другое имя.");
        }
    }

    @ShellMethod(value = "Add new Genre", key = {"addGenre", "ag", "addG"})
    public void addNewGenre(String genreName){
        try {
            genreDaoService.addGenre(createGenre(genreName));
            printService.print("Новый жанр успешно добавлен в базу.");
        } catch (RuntimeException e){
            printService.print("Такой жанр литературы уже существует в базе. Поробуйте задать другой жанр.");
        }
    }

    @ShellMethod(value = "Delete Book", key = {"deleteBook", "db", "deleteB"})
    public void deleteBook(String bookName){
        Book book = new Book();
        book.setBookName(bookName);
        try {
            bookDaoService.deleteBook(book);
            printService.print("Книга успешно удалена из базы.");
        } catch (RuntimeException e){
            printService.print("Невозможно удалить книгу из базы. Такой книги не существует.");
        }
    }

    @ShellMethod(value = "Delete Author", key = {"deleteAuthor", "da", "deleteA"})
    public void deleteAuthor(String authorName){
        try {
            authorDaoService.deleteAuthor(createAuthor(authorName));
            printService.print("Автор успешно удален из базы.");
        } catch (RuntimeException e){
            if(e.getMessage().equals(GENERATED_RECORD_ERROR)){
                printService.print("Невозможно удалить автора из базы. " +
                        "Данный автор привязан к существующей книге. " +
                        "Удалите сначала все книги с таким автором.");
            } else {
                printService.print("Невозможно удалить автора из базы. Автора с таким именем не существует.");
            }
        }
    }

    @ShellMethod(value = "Delete Genre", key = {"deleteGenre", "dg", "deleteG"})
    public void deleteGenre(String genreName){
        try {
            genreDaoService.deleteGenre(createGenre(genreName));
            printService.print("Жанр литературы успешно удален из базы.");
        } catch (RuntimeException e){
            if(e.getMessage().equals(GENERATED_RECORD_ERROR)){
                printService.print("Невозможно удалить жанр литературы из базы. " +
                        "Данный жанр привязан к существующей книге. " +
                        "Удалите сначала все книги с таким жанром.");
            } else {
                printService.print("Невозможно удалить жанр литературы из базы. Жанра таким именем не существует.");
            }
        }
    }

    @ShellMethod(value = "Change Book", key = {"changeBook", "cb", "changeB"})
    public void changeBook(String oldBookName, String newBookName){
        Book oldBook = new Book();
        oldBook.setBookName(oldBookName);
        Book newBook = new Book();
        newBook.setBookName(newBookName);
        try {
            bookDaoService.changeBook(oldBook, newBook);
            printService.print("Книга была успешно изменена.");
        } catch (RuntimeException e){
            printService.print("Невозможно изменить книгу. Книги с таким названием не существует.");
        }
    }

    @ShellMethod(value = "Change Author", key = {"changeAuthor", "ca", "changeA"})
    public void changeAuthor(String oldAuthorName, String newAuthorName){
        try {
            authorDaoService.changeAuthor(createAuthor(oldAuthorName), createAuthor(newAuthorName));
            printService.print("Автор был успешно изменён.");
        } catch (RuntimeException e){
            printService.print("Невозможно изменить автора. Автора с таким именем не существует.");
        }
    }

    @ShellMethod(value = "Change Genre", key = {"changeGenre", "cg", "changeG"})
    public void changeGenre(String oldGenreName, String newGenreName){
        try {
            genreDaoService.changeGenre(createGenre(oldGenreName), createGenre(newGenreName));
            printService.print("Жанр литературы успешно изменён.");
        } catch (RuntimeException e){
            printService.print("Невозможно изменить жанр литературы. Жанра таким именем не существует.");
        }
    }

    private Author createAuthor(String authorName){
        Author author = new Author();
        author.setAuthorName(authorName);
        return author;
    }

    private Genre createGenre(String genreName){
        Genre genre = new Genre();
        genre.setGenreName(genreName);
        return genre;
    }
}
