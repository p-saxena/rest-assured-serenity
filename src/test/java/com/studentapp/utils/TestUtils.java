package com.studentapp.utils;

import java.util.Random;

/**
 * Created by watchmaster on 9/25/17.
 */
public class TestUtils {

    public static String getRandomValue(){
        Random random = new Random();
        int randomInt = random.nextInt(100000); // generate a random integer  between 0 and 100000
        return Integer.toString(randomInt); // convert integer to string
    }
}
