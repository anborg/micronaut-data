package io.micronaut.data.tck.entities;


import io.micronaut.data.annotation.MappedEntity;

@MappedEntity
public class ShelfBook {

    private Shelf shelf;
    private Book book;

    public Shelf getShelf() {
        return shelf;
    }

    public void setShelf(Shelf shelf) {
        this.shelf = shelf;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
