package com.skillsoft.junit;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProductTest {

    static Product tv;

    @BeforeAll
    static void init(){
        tv = new Product("Diallonic", "Delta", 13423,
                800, "Electronics");
    }

    @Test
    @DisplayName("Brand Name Check")
    @Order(2)
    void brandNameTest(){

        assertEquals("Diallonic",
                tv.getBrandName(),
                "Brand Name Test");
    }

    @Test
    @DisplayName("Price Check")
    @Order(1)
    void priceTest(){
        assertEquals(800,
                tv.getPrice(),
                "Price Test");
    }

    @Test
    @DisplayName("Category Check")
    @Order(3)
    void categoryTest(){
        assertEquals("Electronics",
                tv.getCategory(),
                "Category Test");
    }

    @Test
    @DisplayName("Model Check")
    @Order(3)
    void modelTest(){
        assertEquals("Delta",
                tv.getModel(),
                "Model Test");
    }
}
