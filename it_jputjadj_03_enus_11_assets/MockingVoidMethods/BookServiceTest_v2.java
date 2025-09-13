package com.skillsoft.mocks;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @Mock
    private BookRepository bookRepositoryMock;

    @InjectMocks
    private BookService bookService;

    @Test
    public void testAddBook() {

        bookService.addBook(new Book(
                "book123", "Diary of a Wimpy Kid", "Jeff Kinney"));
        bookService.addBook(new Book(
                "book456", "Game of Thrones", "George R. R. Martin"));
        bookService.addBook(new Book(
                "book789", "Hunger Games", "Suzanne Collins"));

        verify(bookRepositoryMock, times(3)).addBook(isA(Book.class));
    }

    @Test
    public void testUpdateBook() {

        bookService.updateBook(new Book(
                "book123", "Diary of a Wimpy Kid (Series)", "Jeff Kinney"));

        verify(bookRepositoryMock, atLeastOnce()).updateBook(isA(Book.class));
    }

    @Test
    public void testDeleteBook() {

        bookService.deleteBook("book123");

        verify(bookRepositoryMock, atLeastOnce()).deleteBook(anyString());
    }
}
