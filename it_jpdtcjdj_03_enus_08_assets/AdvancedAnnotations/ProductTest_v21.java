package com.skillsoft.junit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.junit.jupiter.api.Timeout;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Execution(ExecutionMode.CONCURRENT)
public class ProductTest {

    Product tv;

    @BeforeEach
    void init(){
        tv = new Product("Diallonic", "Delta", 13423,
                800, "Electronics");
    }

    @Test
    @DisplayName("Brand Name Check")
    @Timeout(4)
    void brandNameTest() throws InterruptedException{

        Thread.sleep(4000);

        assertEquals("Diallonic",
                tv.getBrandName(),
                "Brand Name Test");
    }

    @Test
    @DisplayName("Price Check")
    @Timeout(4)
    void priceTest()throws InterruptedException{

        Thread.sleep(3000);

        assertEquals(800,
                tv.getPrice(),
                "Price Test");
    }

    @Test
    @DisplayName("Category Check")
    @Timeout(4)
    void categoryTest()throws InterruptedException{

        Thread.sleep(3000);

        assertEquals("Electronics",
                tv.getCategory(),
                "Category Test");
    }

    @Test
    @DisplayName("Model Check")
    @Timeout(4)
    void modelTest()throws InterruptedException{

        Thread.sleep(5000);

        assertEquals("Delta",
                tv.getModel(),
                "Model Test");
    }
}
