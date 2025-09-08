package com.skillsoft.junit;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.condition.DisabledIfSystemProperty;
import org.junit.jupiter.api.condition.EnabledIfSystemProperty;

public class ProductTest {

    static Product tv;

    @BeforeAll
    static void init(){
        
        tv = new Product("Diallonic", "Delta", 13423,
                800, "Electronics");

        System.out.println("System properties:");
        System.out.println("os.arch: " + System.getProperty("os.arch"));
        System.out.println("os.version: " + System.getProperty("os.version"));
        System.out.println("os.name: " + System.getProperty("os.name"));
        System.out.println("user.name: " + System.getProperty("user.name"));
    }

    @Test
    @DisplayName("Brand Name Check")
    @EnabledIfSystemProperty(named = "os.arch", matches = "x86_64")
    void brandNameTest(){

        assertEquals("Diallonic",
                tv.getBrandName(),
                "Brand Name Test");
    }

    @Test
    @DisplayName("Price Check")
    @EnabledIfSystemProperty(named = "os.version", matches = "10\\..*")
    void priceTest(){
        assertEquals(800,
                tv.getPrice(),
                "Price Test");
    }

    @Test
    @DisplayName("Category Check")
    @EnabledIfSystemProperty(named = "os.name", matches = "Windows 10")
    void categoryTest(){
        assertEquals("Electronics",
                tv.getCategory(),
                "Category Test");
    }

    @Test
    @DisplayName("Model Check")
    @DisabledIfSystemProperty(named = "os.arch", matches = ".*_64")
    void modelTest(){
        assertEquals("Delta",
                    tv.getModel(),
                    "Model Test");
    }
}
