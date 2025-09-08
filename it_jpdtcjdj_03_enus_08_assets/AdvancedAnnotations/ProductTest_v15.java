package com.skillsoft.junit;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.condition.DisabledIfEnvironmentVariable;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import org.junit.jupiter.api.condition.EnabledForJreRange;
import org.junit.jupiter.api.condition.JRE;
import org.junit.jupiter.api.condition.DisabledForJreRange;
import org.junit.jupiter.api.condition.DisabledOnOs;
import org.junit.jupiter.api.condition.OS;

import org.junit.jupiter.api.condition.EnabledIfSystemProperty;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


public class ProductTest {

    static Product tv;

    @BeforeAll
    static void init(){

        tv = new Product("Diallonic", "Delta", 13423,
                800, "Electronics");

    }


    @Retention(RetentionPolicy.RUNTIME)
    @Test
    @EnabledForJreRange(min = JRE.JAVA_11, max = JRE.JAVA_17)
    @DisabledOnOs(OS.MAC)
    @EnabledIfSystemProperty(named = "os.arch", matches = "x86_64")
    public @interface MyCustomTestConditions {
    }

    @MyCustomTestConditions
    @DisplayName("Brand Name Check")
    void brandNameTest(){

        assertEquals("Diallonic",
                tv.getBrandName(),
                "Brand Name Test");
    }

    @MyCustomTestConditions
    @EnabledIfEnvironmentVariable(named = "USER", matches = "kish.*")
    @DisplayName("Price Check")
    void priceTest(){
        assertEquals(800,
                tv.getPrice(),
                "Price Test");
    }

    @MyCustomTestConditions
    @DisabledIfEnvironmentVariable(named = "USER", matches = "kish.*")
    @DisplayName("Category Check")
    void categoryTest(){
        assertEquals("Electronics",
                tv.getCategory(),
                "Category Test");
    }

    @DisplayName("Model Check")
    @MyCustomTestConditions
    @EnabledIfEnvironmentVariable(named = "USER", matches = "skill.*")
    void modelTest(){
        assertEquals("Delta",
                tv.getModel(),
                "Model Test");
    }
}
