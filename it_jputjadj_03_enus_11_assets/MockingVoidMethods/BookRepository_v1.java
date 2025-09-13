package com.skillsoft.mocks;

public interface BookRepository {

    Book getBook(String id);

    void addBook(Book book);

    void updateBook(Book book);

    void deleteBook(String id);
}
