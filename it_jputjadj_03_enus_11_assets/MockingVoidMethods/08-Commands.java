
// ############################ Overall recording notes



// ############################## demo-08-MockingVoidMethods ##########################

// -- https://www.baeldung.com/mockito-void-methods

-- Show Book.java (v1)

-- Show BookRepository.java (v1)

-- Show BookService.java (v1)

-- In BookServiceTest.java show that when().thenReturn() cannot be used for void methods

-- Hover over the red underline and show the error

-- Run the test and show the error


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

        when(bookRepositoryMock.addBook(any())).thenReturn(Void);

    }
}

// ################################

-- Use doNothing() to mock void methods

-- Show BookServiceTest.java (v1) -- please see run instructions in the code, run one test at a time


// ################################

-- However, doNothing() is Mockitos default behavior for void methods.

-- Show BookServiceTest.java (v2)

-- Here I have simply removed all the doNothing() calls, everything else is the same

-- Run and everything works exactly like it did before


// ################################

-- Show how doNothing() can be used to capture arguments for void methods

-- Show BookServiceTest.java (v3) -- please paste in and run one test at a time


// ################################

-- Show how the @Captor annotation can be used to set up ArgumentCaptors

-- Show BookServiceTest.java (v4) 

-- Show the change in the whole code and run all tests



































