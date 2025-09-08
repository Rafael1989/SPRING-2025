package com.skillsoft.junit;

import org.junit.jupiter.api.BeforeAll;
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
    void brandNameTest(){

        assertEquals("Diallonic",
                        tv.getBrandName(),
                		"Brand Name Test");
    }
}
