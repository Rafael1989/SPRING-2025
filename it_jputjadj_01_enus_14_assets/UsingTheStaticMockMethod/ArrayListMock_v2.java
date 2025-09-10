package com.skillsoft.mocks;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ArrayListMock {

    @Test
    public void mockArrayList() {

        ArrayList someArrayList = mock(ArrayList.class);

        when(someArrayList.isEmpty()).thenReturn(true);
        when(someArrayList.size()).thenReturn(23);
        when(someArrayList.toArray()).thenReturn(new Object[] {1, 3, 6, 9});

        assertTrue(someArrayList.isEmpty());
        assertEquals(23, someArrayList.size());
        assertArrayEquals(new Object[] {1, 3, 6, 9}, someArrayList.toArray());
    }
}
