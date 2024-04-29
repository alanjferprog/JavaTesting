package com.example.platzi.romanNumerals;

public class RomanNumerals
{

    public static String arabicToRoman(int number)
    {
        StringBuilder result= new StringBuilder();

        while (number > 1000 ) { result.append("M"); number-= 1000; }
        if(number >= 900 && number <= 999) {result.append("CM");number-= 900;}
        if (number < 1000 && number > 500 ) {result.append("D"); number-= 500; }
        if(number >= 400 && number <= 499) {result.append("CD");number-= 400;}
        if(number < 500 && number > 99) {result.append("C");number-= 100;}
        if(number >= 90 && number <= 99) {result.append("XC");number-= 90;}
        if(number < 100 && number > 49) { result.append("L"); number-= 50;}
        if(number >= 40 && number <= 49) {result.append("XL");number-= 40;}
        while(number < 50 && number > 9) {result.append("X");number-= 10;}
        if(number == 9) {result.append("IX");number-= 9;}
        if(number < 10 && number > 4) {result.append("V");number-= 5;}
        if(number == 4) {result.append("IV");number-= 4;}
        while(number < 4 && number > 0) {result.append("I");number-= 1;}

        return result.toString();
    }
}
