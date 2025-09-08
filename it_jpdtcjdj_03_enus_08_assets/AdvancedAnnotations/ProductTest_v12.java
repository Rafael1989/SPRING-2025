package com.skillsoft.junit;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.condition.DisabledIfEnvironmentVariable;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;

public class ProductTest {

    static Product tv;

    @BeforeAll
    static void init(){
        
        tv = new Product("Diallonic", "Delta", 13423,
                800, "Electronics");

        System.out.println("Environment variables:");
        System.out.println("PWD: " + System.getenv("PWD"));
        System.out.println("USER: " + System.getenv("USER"));
        System.out.println("ALL: " + System.getenv());
    }

    @Test
    @DisplayName("Brand Name Check")
    @EnabledIfEnvironmentVariable(named = "PWD", matches = ".*junit.*")
    void brandNameTest(){

        assertEquals("Diallonic",
                    tv.getBrandName(),
                    "Brand Name Test");
    }

    @Test
    @DisplayName("Price Check")
    @EnabledIfEnvironmentVariable(named = "USER", matches = "loonycorn")
    void priceTest(){
        assertEquals(800,
                    tv.getPrice(),
                    "Price Test");
    }

    @Test
    @DisplayName("Category Check")
    @DisabledIfEnvironmentVariable(named = "USER", matches = "loony.*")
    void categoryTest(){
        assertEquals("Electronics",
                    tv.getCategory(),
                    "Category Test");
    }

    @Test
    @DisplayName("Model Check")
    @DisabledIfEnvironmentVariable(named = "LANG", matches = "de_DE*")
    void modelTest(){
        assertEquals("Delta",
                    tv.getModel(),
                    "Model Test");
    }
}
