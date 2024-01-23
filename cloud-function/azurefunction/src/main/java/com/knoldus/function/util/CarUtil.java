package com.knoldus.function.util;

public class CarUtil {

    /**
     * Converts the given mileage value from miles to kilometers per mile.
     *
     * @param mileage the mileage value in miles
     * @return the updated mileage value in kilometers per mile
     */
    public static Double updateMileage(final Double mileage) {
        return mileage*1.6093440006147;
    }

    /**
     * Converts the given price value to rupees.
     *
     * @param price the price value
     * @return the updated price value in rupees
     */
    public static Double updatePrice(final Double price) {
        return price*82.10;
    }
}
