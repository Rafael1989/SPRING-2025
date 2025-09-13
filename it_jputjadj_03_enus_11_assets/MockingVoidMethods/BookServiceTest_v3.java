package com.skillsoft.mocks;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @Mock
    private BookRepository bookRepositoryMock;

    @InjectMocks
    private BookService bookService;

    @Test
    public void testAddBook() {

        ArgumentCaptor<Book> inputBookCaptor = ArgumentCaptor.forClass(Book.class);

        doNothing().when(bookRepositoryMock).addBook(inputBookCaptor.capture());

        Book book1 = new Book("book456", "Game of Thrones", "George R. R. Martin");
        bookService.addBook(book1);

        assertEquals(book1, inputBookCaptor.getValue());

        bookService.addBook(new Book(
                "book789", "Hunger Games", "Suzanne Collins"));

        assertEquals(
                new Book("book789", "Hunger Games", "Suzanne Collins"),
                inputBookCaptor.getValue());

        verify(bookRepositoryMock, times(2)).addBook(isA(Book.class));
    }

    // Run

    @Test
    public void testUpdateBook() {

        ArgumentCaptor<Book> inputBookCaptor = ArgumentCaptor.forClass(Book.class);

        doNothing().when(bookRepositoryMock).updateBook(inputBookCaptor.capture());

        bookService.updateBook(new Book(
                "book123", "Diary of a Wimpy Kid (Series)", "Jeff Kinney"));
        bookService.updateBook(new Book(
                "book456", "Game of Thrones", "George R. R. Martin"));

        assertTrue(inputBookCaptor.getAllValues().contains(new Book(
                "book123", "Diary of a Wimpy Kid (Series)", "Jeff Kinney")));

        assertTrue(inputBookCaptor.getAllValues().contains(new Book(
                "book456", "Game of Thrones", "George R. R. Martin")));

        verify(bookRepositoryMock, times(2)).updateBook(isA(Book.class));
    }

    // Run

    @Test
    public void testDeleteBook() {
        ArgumentCaptor<String> inputStringCaptor = ArgumentCaptor.forClass(String.class);

        doNothing().when(bookRepositoryMock).deleteBook(inputStringCaptor.capture());

        bookService.deleteBook("book123");

        assertEquals("book123", inputStringCaptor.getValue());

        verify(bookRepositoryMock, atLeastOnce()).deleteBook(anyString());
    }

    // Run

}
