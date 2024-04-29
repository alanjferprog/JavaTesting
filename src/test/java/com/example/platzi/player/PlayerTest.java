package com.example.platzi.player;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest
{
    @Test
    void losses_when_dice_number_is_too_low() {
        Dice dice= Mockito.mock(Dice.class);
        Mockito.when(dice.roll()).thenReturn(2);

        Player player= new Player(dice, 3);
        assertFalse(player.play());
    }

    @Test
    void wins_when_dice_number_is_too_big() {
        Dice dice= Mockito.mock(Dice.class);
        Mockito.when(dice.roll()).thenReturn(4);

        Player player= new Player(dice, 3);
        assertTrue(player.play());
    }
}