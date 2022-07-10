package ru.otus.spring.model;

public class Book {

    private Long id;
    private String bookName;
    private Author author;
    private Genre genre;


    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        Author newAuthor = new Author();
        newAuthor.setId(author.getId());
        newAuthor.setAuthorName(author.getAuthorName());
        this.author = newAuthor;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        Genre newGenre = new Genre();
        newGenre.setId(genre.getId());
        newGenre.setGenreName(genre.getGenreName());
        this.genre = newGenre;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }
}
