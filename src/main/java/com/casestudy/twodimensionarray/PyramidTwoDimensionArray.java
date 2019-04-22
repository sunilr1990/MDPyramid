package com.casestudy.twodimensionarray;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.casestudy.pyramid.exception.PyramidException;

/**
 * Case study implementation using Two dimensional array.
 * 
 * @author sunil.r
 *
 */
public class PyramidTwoDimensionArray {

    /**
     * Returns max sum by reading data on File
     * 
     * @param filePath :path of the File.
     * @return maximum sum calculated
     */
    public int run(String filePath) {
        int[][] data = dataRetrievalFromFile(filePath);
        int[][] input = Arrays.stream(data).map(row -> row.clone()).toArray(int[][]::new);
        getMaxSum(data, input);
        return data[0][0];
    }

    /**
     * Calculates max sum for given two dimensional array.
     * 
     * @param data : Data read from File
     * @param input : Cloned array from file
     */
    private void getMaxSum(int[][] data, int[][] input) {
        for (int i = data.length - 2; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                boolean leftFlag = (input[i][j] % 2) != (input[i + 1][j] % 2);
                boolean rightFlag = (input[i][j] % 2) != (input[i + 1][j + 1] % 2);
                if (leftFlag && rightFlag) {
                    if (data[i + 1][j] > data[i + 1][j + 1])
                        data[i][j] += data[i + 1][j];
                    else
                        data[i][j] += data[i + 1][j + 1];
                } else if (leftFlag) {
                    data[i][j] += data[i + 1][j];
                } else if (rightFlag) {
                    data[i][j] += data[i + 1][j + 1];
                }
            }
        }
    }

    /**
     * Reads data from File
     * 
     * @param filePath
     * @return integer array
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
                for (int j = 0; j < inputData.size(); j++) {
                    if (j >= split.length) {
                        data[i][j] = 0;
                    } else
                        data[i][j] = Integer.valueOf(split[j]);
                }
            }
            return data;
        } catch (IOException ioException) {
            throw new PyramidException("Exception occured while reading file" + ioException.getMessage());
        }
    }

}