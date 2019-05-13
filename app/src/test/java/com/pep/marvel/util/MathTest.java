package com.pep.marvel.util;

import org.junit.Test;

import java.util.Scanner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


public class MathTest {

    @Test
    public void mathTest() {

        Math math = new Math();
        String text = "2 * -(- 2)";
        String [] readText = math.readText(text);
        assertNotNull("Text format invalid",readText);
        System.out.println(" *** Result *** ");
        double result = math.mathOperation(readText);
        System.out.println(result);
        System.out.println("\n *** Multiple *** ");
        double multiple = math.getMultiple(result);
        String message = (multiple == 2) ? "Default" : String.valueOf((int) multiple);
        System.out.println(message);
    }


    @Test
    public void multipleTest() {
        Math math = new Math();
        double result = 5;
        System.out.println(" *** Multiple *** ");
        double multiple = math.getMultiple(result);
        String message = (multiple == 2) ? "Default" : String.valueOf((int) multiple);
        System.out.println((message));
    }

}