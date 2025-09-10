
// ############################ Overall recording notes

// -- Recording notes remains the same
// -- Please look at the instructions within the code


// # Mockito offers two equivalent ways of mocking:
// # Using static methods and
// # Using @Mock annotations.

// # So we will start with static methods first

// ######################## demo-02-UsingTheStaticMockMethod #########################

// # In this demo I am showing how can we create mock object using static methods
// # Then we will test the mock 
// # Also we are going to mock different objects as well as interfaces

// # Using the Mockito libraries should be done with a modern dependency system like Maven or Gradle. All modern IDEs (Eclipse, Visual Studio Code, IntelliJ) support both Maven and Gradle.

// # The following contains detailed descriptions for your environment, pick the one which is relevant for you. The latest version of Mockito can be found via https://search.maven.org/artifact/org.mockito/mockito-core.

-- First go to this site https://search.maven.org/artifact/org.mockito/
-- Search for "mockito-core" and show the latest is 3.9.0
-- Click on the search result and show the dependency XML

-- Go back to this page https://search.maven.org/artifact/org.mockito/
-- Search for "mockito-junit-jupiter" and show the latest is 3.9.0
-- Click on the search result and show the dependency XML


// # First let's add following dependency

    <dependency>
      <groupId>org.mockito</groupId>
      <artifactId>mockito-core</artifactId>
      <version>3.9.0</version>
    </dependency>

    <dependency>
      <groupId>org.mockito</groupId>
      <artifactId>mockito-junit-jupiter</artifactId>
      <version>3.9.0</version>
      <scope>test</scope>
    </dependency>

-- Add the properties

  <properties>
    <maven.compiler.source>15</maven.compiler.source>
    <maven.compiler.target>15</maven.compiler.target>
  </properties>


-- Right-click -> Maven -> Reload project


// # So for complete pom.xml refer pom.xml

// # In this demo we are showing that with a single class how can we test with mockito
// # We will always add the testing class under src/test/java

-- Show ArrayListMock.java (v1)

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


-- On the top-right click on Edit Configuration
-- Click on the + button on the left and add a new configuration for JUnit
-- Call the configuration ArrayListTest
-- Specify the name of the class com.skillsoft.mock.ArrayListTest
-- Click ok

-- Now run the code and show results in console

// ####################

-- Show ArrayListMock.java (v2)

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

-- Run and show that all tests pass

// #########################

-- Now update the return values to be of the wrong type
-- Hover over the red underline and show the error
-- Run the code and show the error


public class ArrayListMock {

    @Test
    public void mockArrayList() {

        ArrayList someArrayList = mock(ArrayList.class);

        when(someArrayList.isEmpty()).thenReturn(100);
        when(someArrayList.size()).thenReturn(true);
        when(someArrayList.toArray()).thenReturn("some string");

        assertTrue(someArrayList.isEmpty());
        assertEquals(23, someArrayList.size());
        assertArrayEquals(new Object[] {1, 3, 6, 9}, someArrayList.toArray());
    }
}


// #########################

-- Change one line of code (first assert)

        assertFalse(someArrayList.isEmpty());
        assertEquals(23, someArrayList.size());
        assertArrayEquals(new Object[] {1, 3, 6, 9}, someArrayList.toArray());

-- Run and show failure
-- Clikc on the link that says "Click to see difference"

-- Change that line back and change another line of code (second assert)

        assertTrue(someArrayList.isEmpty());
        assertEquals(25, someArrayList.size());
        assertArrayEquals(new Object[] {1, 3, 6, 9}, someArrayList.toArray());

-- Run and show failure
-- Clikc on the link that says "Click to see difference"

-- Change that line back and change another line of code (third assert)

        assertTrue(someArrayList.isEmpty());
        assertEquals(23, someArrayList.size());
        assertArrayEquals(new Object[] {2, 3, 6, 9}, someArrayList.toArray());

-- Run and show the error

// #########################

-- Show ArrayListMock.java (v3)

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ArrayListMock {

    @Test
    public void mockArrayList() {

        ArrayList someArrayList = mock(ArrayList.class);

        when(someArrayList.contains("Alice")).thenReturn(true);
        when(someArrayList.contains("Bob")).thenReturn(false);

        assertTrue(someArrayList.contains("Alice"));
        assertFalse(someArrayList.contains("Bob"));

        // Run

        assertTrue(someArrayList.contains("Charles"));

        // Run - this will fail
        // Comment out the previous line of code

        when(someArrayList.get(0)).thenReturn("Alice");
        when(someArrayList.get(1)).thenReturn("Bob");
        when(someArrayList.get(2)).thenReturn("Charles");

        assertEquals("Alice", someArrayList.get(0));
        assertEquals("Bob", someArrayList.get(1));
        assertEquals("Charles", someArrayList.get(2));

        // Run

        when(someArrayList.indexOf("Alice")).thenReturn(100);
        when(someArrayList.indexOf("Bob")).thenReturn(101);
        when(someArrayList.indexOf("Charles")).thenReturn(102);

        assertEquals(100, someArrayList.indexOf("Alice"));
        assertEquals(101, someArrayList.indexOf("Bob"));
        assertEquals(102, someArrayList.indexOf("Charles"));

        // Run

        when(someArrayList.subList(100, 103)).thenReturn(Arrays.asList("Alice", "Bob", "Charles"));
        when(someArrayList.subList(1000, 1003)).thenReturn(Arrays.asList("Xavier", "Yasmin", "Zoe"));

        assertLinesMatch(Arrays.asList("Alice", "Bob", "Charles"), someArrayList.subList(100, 103));
        assertLinesMatch(Arrays.asList("Xavier", "Yasmin", "Zoe"), someArrayList.subList(1000, 1003));

        // Run

        assertLinesMatch(Arrays.asList("alice", "Bob", "Charles"), someArrayList.subList(100, 103));

        // Run and show the error on the previous line
        // Click on "Click to see difference" and show
        // Comment out the code
    }
}


// #########################

-- We can mock interfaces as well, not just classes

-- Show ComparatorMock.java (v1)

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


// #########################

-- Show ComparatorMock.java (v2)

public class ComparatorMock {

    @Test
    public void mockComparator() {

        Comparator<String> comparatorMock = mock(Comparator.class);

        when(comparatorMock.compare("Alice", "Bob")).thenReturn(1);
        when(comparatorMock.compare("Bob", "Charles")).thenReturn(1);
        when(comparatorMock.compare("Alice", "Charles")).thenReturn(1);

        when(comparatorMock.compare("Bob", "Alice")).thenReturn(-1);
        when(comparatorMock.compare("Charles", "Bob")).thenReturn(-1);
        when(comparatorMock.compare("Charles", "Alice")).thenReturn(-1);

        when(comparatorMock.compare("Alice", "Alice")).thenReturn(0);
        when(comparatorMock.compare("Bob", "Bob")).thenReturn(0);
        when(comparatorMock.compare("Charles", "Charles")).thenReturn(0);

        assertTrue(comparatorMock.compare("Alice", "Charles") > 0);
        assertTrue(comparatorMock.compare("Charles", "Bob") < 0);
        assertEquals(0, comparatorMock.compare("Bob", "Bob"));
    }
}


// #########################

-- Show TreeSetTest.java (v1)

-- The TreeSet is using our mocked comparator to display the results in the reverse alphabetical order

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TreeSetTest {

    private Comparator<String> comparatorMock;

    @BeforeEach
    public void setupMocks() {
        comparatorMock = mock(Comparator.class);

        when(comparatorMock.compare("Alice", "Bob")).thenReturn(1);
        when(comparatorMock.compare("Bob", "Charles")).thenReturn(1);
        when(comparatorMock.compare("Alice", "Charles")).thenReturn(1);

        when(comparatorMock.compare("Bob", "Alice")).thenReturn(-1);
        when(comparatorMock.compare("Charles", "Bob")).thenReturn(-1);
        when(comparatorMock.compare("Charles", "Alice")).thenReturn(-1);

        when(comparatorMock.compare("Alice", "Alice")).thenReturn(0);
        when(comparatorMock.compare("Bob", "Bob")).thenReturn(0);
        when(comparatorMock.compare("Charles", "Charles")).thenReturn(0);


    }

    @AfterEach
    public void releaseMocks() {
        comparatorMock = null;
    }

    @Test
    public void testTreeSet() {
        TreeSet<String> treeSet = new TreeSet<>(comparatorMock);

        treeSet.add("Bob");
        treeSet.add("Alice");
        treeSet.add("Charles");

        for (String element : treeSet) {
            System.out.println(element);
        }
    }
}

// #########################

-- Show TreeSetTest.java (v2)

-- Change the elements in the tree set, these are elements for which the comparator does not have mocked method calls and return values

-- The rest of the code remains the same

        treeSet.add("Yasmin");
        treeSet.add("Xavier");
        treeSet.add("Zoe");

-- Run the code and only "Yasmin" will be printed out to screen


// #########################

-- Show TreeSetTest.java (v3)
-- Once we use a mocked comparator we can test the methods of the tree set that will use the mock for its comparisons

public class TreeSetTest {

    // Rest of the code remains exactly the same

    @Test
    public void testTreeSet() {
        TreeSet<String> treeSet = new TreeSet<>(comparatorMock);

        treeSet.add("Bob");
        treeSet.add("Alice");
        treeSet.add("Charles");

        assertEquals("Charles", treeSet.first());
        assertEquals("Alice", treeSet.last());

        assertEquals("Bob", treeSet.higher("Charles"));
        assertEquals("Bob", treeSet.lower("Alice"));
    }
}


// #########################

-- Show RemoteMachineProxy.java (v1) in the src/main/java folder

-- Show RemoteMachineProxyTest.java (v1) in the src/test/java folder

-- Run using the instructions in the test file


