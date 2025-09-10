package com.skillsoft.mocks;

import org.junit.jupiter.api.Test;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ComparatorMock {

    @Test
    public void mockComparator() {

        Comparator<String> comparatorMock = mock(Comparator.class);

        System.out.println("toString() of Comparator: " + comparatorMock);
        System.out.println("getClass() of Comparator: " + comparatorMock.getClass());
        System.out.println("comparatorMock instanceof Comparator: " + (comparatorMock instanceof Comparator));
    }
}
