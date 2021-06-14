package com.jcharnet.app.ideas.bin2dec;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.jcharnet.app.ideas.bin2dec.engine.BinaryToDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Bin2DecApplicationTests {

	//@Test
	void contextLoads() {
	}

	@Test
	public void testConvertBinaryToDecimalNullValue() {

		BinaryToDecimal bin2dec = new BinaryToDecimal();
		String binaryNumber = null;
		Exception exception = assertThrows(IllegalArgumentException.class, () -> 
			bin2dec.convertBinaryToDecimal(binaryNumber));

		assertEquals(BinaryToDecimal.BINARY_NULL_EXCEPTION, exception.getMessage());
	}

	@Test
	public void testConvertBinaryToDecimalEmptyValue() {

		BinaryToDecimal bin2dec = new BinaryToDecimal();
		String binaryNumber = "";
		Exception exception = assertThrows(IllegalArgumentException.class, () -> 
			bin2dec.convertBinaryToDecimal(binaryNumber));

		assertEquals(BinaryToDecimal.BINARY_EMPTY_EXCEPTION, exception.getMessage());

		String binaryNumber1 = "   ";
		exception = assertThrows(IllegalArgumentException.class, () -> 
			bin2dec.convertBinaryToDecimal(binaryNumber1));

		assertEquals(BinaryToDecimal.BINARY_EMPTY_EXCEPTION, exception.getMessage());
	}

	@Test
	public void testConvertBinaryToDecimalInvalidValue() {

		BinaryToDecimal bin2dec = new BinaryToDecimal();
		String binaryNumber = "Test";
		Exception exception = assertThrows(IllegalArgumentException.class, () -> 
			bin2dec.convertBinaryToDecimal(binaryNumber));

		assertEquals(String.format(BinaryToDecimal.BINARY_INVALID_EXCEPTION, binaryNumber), exception.getMessage());

		String binaryNumber1 = "-1";
		exception = assertThrows(IllegalArgumentException.class, () -> 
			bin2dec.convertBinaryToDecimal(binaryNumber1));

		assertEquals(String.format(BinaryToDecimal.BINARY_INVALID_EXCEPTION, binaryNumber1), exception.getMessage());


		String binaryNumber2 = "12345";
		exception = assertThrows(IllegalArgumentException.class, () -> 
			bin2dec.convertBinaryToDecimal(binaryNumber2));

		assertEquals(String.format(BinaryToDecimal.BINARY_INVALID_EXCEPTION, binaryNumber2), exception.getMessage());
	}

	@Test
	public void testConvertBinaryToDecimal() {
		BinaryToDecimal bin2dec = new BinaryToDecimal();

		String binaryNumber = "00000001";
		double result = bin2dec.convertBinaryToDecimal(binaryNumber);
		assertEquals(1d, result);

		binaryNumber = "00000011";
		result = bin2dec.convertBinaryToDecimal(binaryNumber);
		assertEquals(3d, result);

		binaryNumber = "10000001";
		result = bin2dec.convertBinaryToDecimal(binaryNumber);
		assertEquals(129d, result);

		binaryNumber = "00000101";
		result = bin2dec.convertBinaryToDecimal(binaryNumber);
		assertEquals(5d, result);

		binaryNumber = "11111111";
		result = bin2dec.convertBinaryToDecimal(binaryNumber);
		assertEquals(255d, result);

	}

}
