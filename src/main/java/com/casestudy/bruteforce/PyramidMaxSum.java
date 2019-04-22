package com.casestudy.bruteforce;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;

import com.casestudy.pyramid.exception.PyramidException;

/**
 * This program has Brute force approach
 * 
 * @author sunil.r
 *
 */
public class PyramidMaxSum {

    /**
     * Accepts file and calculates max sum of triangle
     * 
     * @param filePath
     * @return
     */
	public int run(final String filePath) {
		return getMaxSumFromArray(dataRetrievalFromFile(filePath));
	}

    /**
     * Calculates maximum sum for the given array.
     * 
     * @param data
     * @return
     */
	private int getMaxSumFromArray(final int data[][]) {
        int sum = 0;
        int even = 1;
        int k = 0;
        int i;
        int j;
		for (i = 0; i < data.length; i++) {
			for (j = k; j < data.length; j++) {
                if (i >= j) {
					if (i == 0 && j == 0) {
						sum = sum + data[i][j];
						if (data[i][j] % 2 == 0) {
							even = 1;
						} else
							even = 0;
					} else {
						int x1 = (data[i][j] % 2 == even) ? data[i][j] : 0;
						int x2 = (data[i][j + 1] % 2 == even) ? data[i][j + 1] : 0;
						int pickValue = x1 > x2 ? x1 : x2;
						if (x1 > x2) {
							k = j;
						} else {
							k = j + 1;
						}
						sum = sum + pickValue;
						even = even == 1 ? 0 : 1;
						break;
					}
				}
			}
		}
		return sum;
	}

    /**
     * Reads data from file.
     * 
     * @param filePath
     * @return
     */
    private int[][] dataRetrievalFromFile(final String filePath) {
        if (null == filePath || filePath.isEmpty()) {
            throw new PyramidException("File path can not be null or empty");
        }
        URL url = getClass().getResource(filePath);
        if (null == url) {
            throw new PyramidException("File does not exist");
        }
        File file = new File(url.getPath());
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            List<String> inputData = bufferedReader.lines().map(Object::toString).collect(Collectors.toList());
            int[][] data = new int[inputData.size()][inputData.size()];
            for (int i = 0; i < inputData.size(); i++) {
                String[] split = inputData.get(i).split("\\s+");
                for (int j = 0; j < split.length; j++) {
                    data[i][j] = Integer.valueOf(split[j]);
                }
            }
            return data;
        } catch (IOException ioException) {
            throw new PyramidException("Exception occured while reading file " + ioException.getMessage());
        }
    }
}