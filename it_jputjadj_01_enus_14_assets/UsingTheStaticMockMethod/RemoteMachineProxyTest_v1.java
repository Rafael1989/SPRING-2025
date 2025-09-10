package com.skillsoft.mocks;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RemoteMachineProxyTest {

    private Iterator<String> iteratorMock;

    @BeforeEach
    public void setupMocks() {
        iteratorMock = mock(Iterator.class);

        when(iteratorMock.next())
                .thenReturn("John Deere, john@infomoto.com, 23")
                .thenReturn("James Cameron, james@infomoto.com, 45")
                .thenReturn("Justine Smith, justine@infomoto.com, 28")
                .thenReturn("Johanna Robbins, johanna@infomoto.com, 34");
    }

    @AfterEach
    public void releaseMocks() {
        iteratorMock = null;
    }

    @Test
    public void testRemoteMachineProxyGetNextFile() {

        RemoteMachineProxy proxy = new RemoteMachineProxy("test_url");
        proxy.setTestIterator(iteratorMock);

        when(iteratorMock.hasNext()).thenReturn(true);
        assertEquals("john@infomoto.com", proxy.getNextFile());

        // Run

        when(iteratorMock.hasNext()).thenReturn(true);
        assertEquals("james@infomoto.com", proxy.getNextFile());

        when(iteratorMock.hasNext()).thenReturn(true);
        assertEquals("justine@infomoto.com", proxy.getNextFile());

        when(iteratorMock.hasNext()).thenReturn(true);
        assertEquals("johanna@infomoto.com", proxy.getNextFile());

        // Run

        // NOTE: That the last return value will continue to be returned for
        // the remaining invocations to getNextFile()
        when(iteratorMock.hasNext()).thenReturn(true);
        assertEquals("johanna@infomoto.com", proxy.getNextFile());

        // Run

        when(iteratorMock.hasNext()).thenReturn(false);
        assertNull(proxy.getNextFile());
    }
}
