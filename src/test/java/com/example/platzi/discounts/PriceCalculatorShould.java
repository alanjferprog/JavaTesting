package com.example.platzi.discounts;

import static org.hamcrest.CoreMatchers.is;
import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.assertThat;

class PriceCalculatorShould
{


    @Test
    void total_zero_when_there_are_prices()
    {
        PriceCalculator calculator = new PriceCalculator();

        assertThat(calculator.getTotal(), is(0.0));
    }

    @Test
    void total_is_the_sum_of_prices()
    {
        PriceCalculator calculator = new PriceCalculator();

        calculator.addPrice(10.2);
        calculator.addPrice(15.5);

        assertThat( calculator.getTotal() , is(25.7));
    }

    @Test
    void apply_discount_to_prices()
    {
        PriceCalculator calculator = new PriceCalculator();

        calculator.addPrice(12.3);
        calculator.addPrice(17.7);

        calculator.setDiscount(50);

        assertThat( calculator.getTotal() , is(15.0));
    }
}