package com.skillsoft.mocks;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.mockito.Mockito.mock;

public class ArrayListMock {

    @Test
    public void mockArrayList() {

        ArrayList someArrayList = mock(ArrayList.class);

        System.out.println("toString() of mock: " + someArrayList);
        System.out.println("getClass() of mock: " + someArrayList.getClass());
        System.out.println("getClass().getSuperClass() of mock: " + someArrayList.getClass().getSuperclass());

        System.out.println("someArrayList instanceof ArrayList: " + (someArrayList instanceof ArrayList));
        System.out.println("someArrayList instanceof List: " + (someArrayList instanceof List));
        System.out.println("someArrayList instanceof Collection: " + (someArrayList instanceof Collection));
        System.out.println("someArrayList instanceof Iterable: " + (someArrayList instanceof Iterable));
        System.out.println("someArrayList instanceof Object: " + (someArrayList instanceof Object));
    }
}
