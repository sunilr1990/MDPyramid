package com.casestudy.pyramid.utils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.casestudy.pyramid.exception.PyramidException;

public class FileReadUtil {

    private FileReadUtil() {
        throw new IllegalStateException("Utility class");
      }

    public static List<int[]> readInput(final String filePath) {

        if (null == filePath || filePath.isEmpty()) {
            throw new PyramidException("File path can not be null or empty");
        }

        try {
            URL url = FileReadUtil.class.getResource(filePath);

            if (null == url) {
                throw new PyramidException("File does not exist");
            }

            File file = new File(url.getPath());
            return Files.lines(Paths.get(file.getPath())).map(s -> Arrays.stream(s.trim().split("\\s+")).mapToInt(Integer::parseInt).toArray()).collect(Collectors.toList());
        } catch (IOException exception) {
            throw new PyramidException("Exception occured while reading file " + filePath, exception);
        }
    }

}
