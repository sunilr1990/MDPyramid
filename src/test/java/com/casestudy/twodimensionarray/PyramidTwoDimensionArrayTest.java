package com.casestudy.twodimensionarray;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

import com.casestudy.app.AbstractTest;
import com.casestudy.pyramid.exception.PyramidException;
import com.casestudy.twodimensionarray.PyramidTwoDimensionArray;

public class PyramidTwoDimensionArrayTest extends AbstractTest {

    static PyramidTwoDimensionArray pyramidService = null;

    @BeforeClass
    public static void setUp() {
        pyramidService = new PyramidTwoDimensionArray();
    }

	@Test
	public void testRun_givenInputIsNull__returnException() {

		// Given
		String filePath = null;

		// When
		thrown.expect(PyramidException.class);
		thrown.expectMessage("File path can not be null or empty");

        pyramidService.run(filePath);
	}

	@Test
	public void testRun_givenInputIsEmpty_returnException() {

		// Given
		String filePath = "";

		// When
		thrown.expect(PyramidException.class);
		thrown.expectMessage("File path can not be null or empty");

        pyramidService.run(filePath);
	}

	@Test
	public void testRun_givenInput_returnMaxPathSum16() {

        long currentTimeMillis = System.currentTimeMillis();

		// Given
		String filePath = "/testInput/Case_1.txt";
		
		// Case_1.txt has
		// 1
		// 8 9
		// 1 5 9
		// 4 5 2 3

		// When
        int maxPathSum = pyramidService.run(filePath);

        long currentTimeMillis1 = System.currentTimeMillis();
        System.out.println(currentTimeMillis1 - currentTimeMillis);

		// Expected
		assertEquals(16, maxPathSum);
	}

	@Test
	public void testRun_givenInput__returnMaxPathSum8186() {

        long currentTimeMillis = System.currentTimeMillis();

		// Given
		String filePath = "/testInput/Case_2.txt";
		
		// TestCase_02.txt contains
		// 215
		// 192 124
		// 117 269 442
		// 218 836 347 235
		// 320 805 522 417 345
		// 229 601 728 835 133 124
		// 248 202 277 433 207 263 257
		// 359 464 504 528 516 716 871 182
		// 461 441 426 656 863 560 380 171 923
		// 381 348 573 533 448 632 387 176 975 449
		// 223 711 445 645 245 543 931 532 937 541 444
		// 330 131 333 928 376 733 017 778 839 168 197 197
		// 131 171 522 137 217 224 291 413 528 520 227 229 928
		// 223 626 034 683 839 052 627 310 713 999 629 817 410 121
		// 924 622 911 233 325 139 721 218 253 223 107 233 230 124 233

		// When
        int maxPathSum = pyramidService.run(filePath);

        long currentTimeMillis1 = System.currentTimeMillis();
        System.out.println(currentTimeMillis1 - currentTimeMillis);

		// Expected
		assertEquals(8186, maxPathSum);
	}

	@Test
	public void testRun_givenInput__returnException() {
		
		// Given
		String filePath = "/testInput/Case_10.txt";

		// When
		thrown.expect(PyramidException.class);
		thrown.expectMessage("File does not exist");

        pyramidService.run(filePath);
	}

}
