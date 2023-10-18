package com.swd.test.integration;

import org.testng.annotations.Test;

public class Currency {

    private String replaceCurrency(String price){
        return price.replaceAll("[^\\.^\\d]","");
    }

    @Test
    public void shouldReturnDouble(){
        String result = replaceCurrency("$1.235");
    }
}
