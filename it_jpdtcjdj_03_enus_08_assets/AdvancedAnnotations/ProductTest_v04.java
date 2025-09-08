package com.skillsoft.junit;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductTest {

    static Product tv;

    @BeforeAll
    static void init(){
        tv = new Product("Diallonic", "Delta", 13423,
                            800, "Electronics");
    }

    @Test
    @DisplayName("Brand Name Check")
    @Disabled
    void brandNameTest(){

        assertEquals("Diallonic",
                        tv.getBrandName(),
                "Brand Name Test");
    }

    @Test
    @DisplayName("Price Check")
    @Disabled
    void priceTest(){
        assertEquals(800,
                tv.getPrice(),
                "Price Test");
    }

    @Test
    @DisplayName("Category Check")
    void categoryTest(){
        assertEquals("Electronics",
                tv.getCategory(),
                "Category Test");
    }
}
