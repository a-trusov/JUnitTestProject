package com.haulmont;

import com.haulmont.categories.NumberTestCategory;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class NumberServiceTest {
    private NumberService numberService;
    @Before
    public void setUp(){
        numberService = new NumberService();
    }

    @Test
//    @Category(NumberTestCategory.class)
    public void testIsEvenPositive(){
        assertTrue(numberService.isEven(2));
    }

    @Test
//    @Category(NumberTestCategory.class)
    public void testIsEvenNegative(){
        assertFalse(numberService.isEven(1));
    }

}
