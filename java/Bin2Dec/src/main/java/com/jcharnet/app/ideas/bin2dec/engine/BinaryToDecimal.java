package com.jcharnet.app.ideas.bin2dec.engine;

import java.util.OptionalDouble;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BinaryToDecimal {
    private static Logger logger = LoggerFactory.getLogger(BinaryToDecimal.class);

    public final static String BINARY_NULL_EXCEPTION = "Binary Number cannot be null!";
    public final static String BINARY_EMPTY_EXCEPTION = "Binary Number cannot be empty";
    public final static String BINARY_INVALID_EXCEPTION = "Invalid Binary Number [%s]";

    public double convertBinaryToDecimal(String binaryNumber) {
        
        logger.debug(String.format("Binary Number [%s]", binaryNumber));
        
        this.validateBinaryNumber(binaryNumber);

        /*
        double decimalNumber = 0;
        for (int i=0; i < binaryNumber.length(); i++) {
            decimalNumber += Character.getNumericValue(binaryNumber.charAt(binaryNumber.length() - 1 - i)) * Math.pow(2, i);
        }
        */

        OptionalDouble optDecNumber = binaryNumber.chars()
            .mapToDouble(i -> Character.getNumericValue(i))
            .reduce((x,y) -> x * 2 + y);

        return optDecNumber.getAsDouble();
    }

    private void validateBinaryNumber(String binaryNumber) {

        if (binaryNumber == null) {
            throw new IllegalArgumentException(BINARY_NULL_EXCEPTION);
        }

        if (binaryNumber.trim().length() == 0) {
            throw new IllegalArgumentException(BINARY_EMPTY_EXCEPTION);
        }
        
        try {
            double tempNum = Double.valueOf(binaryNumber);
            if (tempNum < 0) {
                throw new IllegalArgumentException(String.format(BINARY_INVALID_EXCEPTION, binaryNumber));
            }
        } catch (NumberFormatException nfe) {
            throw new IllegalArgumentException(String.format(BINARY_INVALID_EXCEPTION, binaryNumber));
        }

        if (!this.isBinaryNumber(binaryNumber)) {
            throw new IllegalArgumentException(String.format(BINARY_INVALID_EXCEPTION, binaryNumber));
        }
    }

    private boolean isBinaryNumber(String number) {
        String regex = "[01]*";

        return number.matches(regex);
    }
}
