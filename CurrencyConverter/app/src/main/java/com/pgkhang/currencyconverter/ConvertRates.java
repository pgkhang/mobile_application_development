package com.pgkhang.currencyconverter;

import java.util.HashMap;
import java.util.Map;

public class ConvertRates {
    static Map<String, Double> rates = new HashMap<String, Double>() {{
        put("USD", 1.0);
        put("VND", 22774.89);
        put("EUR", 0.813974);
        put("GBP", 0.714176);
        put("INR", 66.2243);
        put("AUD", 1.30409);
        put("CAD", 1.27645);
        put("SGD", 1.31500);
        put("CHF", 0.974642);
        put("MYR", 3.89750);
        put("JPY", 107.661);
        put("CNY", 6.29490);
        put("THB", 31.3444);
        put("KRW", 1070.11);
    }};
}
