package com.example.platzi.util;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StringUtilTest
{
    @Test
    public void repeat_string_once() {
        assertEquals("hola", StringUtil.repeat("hola", 1));
    }

    @Test
    public void repeat_string_multiple_times() {
        assertEquals("holaholahola", StringUtil.repeat("hola", 3));
    }

    @Test
    public void repeat_string_zero_times() {
        assertEquals("", StringUtil.repeat("hola", 0));
    }

    @Test
    public void repeat_string_negative_times() {
        assertThrows(IllegalArgumentException.class, () -> {StringUtil.repeat("hola", -1); });
    }

    @Test
    void is_not_empty() {
        String str= "aaA";

        Boolean result= StringUtil.isEmpty(str);

        assertFalse(result);
    }

    @Test
    void void_is_void() {
        String str= "";

        Boolean result= StringUtil.isEmpty(str);

        assertTrue(result);
    }

    @Test
    void null_is_void() {
        String str= null;

        Boolean result= StringUtil.isEmpty(str);

        assertTrue(result);
    }

    @Test
    void space_is_void() {
        String str= "  ";

        Boolean result= StringUtil.isEmpty(str);

        assertTrue(result);
    }
}