package com.example.platzi.ejs;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FizzBuzzShould 
{

    @Test
    void return_fizz_if_number_is_divisible_by_3()
    {
        assertEquals("Fizz", FizzBuzz.fizzBuzz(18));
        assertEquals("Fizz", FizzBuzz.fizzBuzz(36));
        assertEquals("Fizz", FizzBuzz.fizzBuzz(18));

    }

    @Test
    void return_buzz_if_number_is_divisible_by_5()
    {
        assertEquals("Buzz", FizzBuzz.fizzBuzz(50));
        assertEquals("Buzz", FizzBuzz.fizzBuzz(100));
        assertEquals("Buzz", FizzBuzz.fizzBuzz(25));
    }

    @Test
    void return_fizzbuzz_if_number_is_divisible_by_3_and_by_5()
    {
        assertEquals("FizzBuzz", FizzBuzz.fizzBuzz(30));
        assertEquals("FizzBuzz", FizzBuzz.fizzBuzz(60));
        assertEquals("FizzBuzz", FizzBuzz.fizzBuzz(15));
    }
    @Test
    void return_the_number()
    {
        assertEquals("7", FizzBuzz.fizzBuzz(7));
        assertEquals("14", FizzBuzz.fizzBuzz(14));
        assertEquals("16", FizzBuzz.fizzBuzz(16));

    }
}