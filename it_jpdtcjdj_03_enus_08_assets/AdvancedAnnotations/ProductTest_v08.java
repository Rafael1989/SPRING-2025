package com.skillsoft.junit;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.condition.DisabledOnJre;
import org.junit.jupiter.api.condition.EnabledOnJre;
import org.junit.jupiter.api.condition.JRE;

public class ProductTest {

    static Product tv;

    @BeforeAll
    static void init(){
        tv = new Product("Diallonic", "Delta", 13423,
                            800, "Electronics");
    }

    @Test
    @DisplayName("Brand Name Check")
    @EnabledOnJre(JRE.JAVA_16)
    void brandNameTest(){

        assertEquals("Diallonic",
                        tv.getBrandName(),
                "Brand Name Test");
    }

    @Test
    @DisplayName("Price Check")
    @EnabledOnJre(JRE.JAVA_9)
    void priceTest(){
        assertEquals(800,
                tv.getPrice(),
                "Price Test");
    }

    @Test
    @DisplayName("Category Check")
    @DisabledOnJre(JRE.JAVA_16)
    void categoryTest(){
        assertEquals("Electronics",
                tv.getCategory(),
                "Category Test");
    }
}
