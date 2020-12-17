package com.example.viewpager2.data;

import com.example.viewpager2.data.core.Math;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;

public class MathTest extends TestCase {

    Math math;

    @Before
    public void setUp() throws Exception {
        math = new Math();
    }

    @After
    public void tearDown() throws Exception {
    }

    public void testAdd() {
        assertEquals(5, math.add(2, 3));
    }

    public void testSub() {
        assertEquals(4, math.sub(6, 2));
    }

    public void testMultiply() {
        assertEquals(2, math.multiply(2, 1));
    }

    public void testDivide() {
        assertEquals(0, math.divide(6, 0));
    }
}