package com.casestudy.pyramid.service;

import java.util.List;

import com.casestudy.pyramid.domain.Node;
import com.casestudy.pyramid.utils.FileReadUtil;

public class PyramidServiceImpl implements PyramidService {


    @Override
    public int run(String filePath) {
        List<int[]> readInput = FileReadUtil.readInput(filePath);
        Node mdPyramidService = new Node();
        mdPyramidService.constructTree(readInput);
        mdPyramidService.convertToDiagonalTree();
        mdPyramidService.findMaximumSumPath(mdPyramidService.getRoot(), new int[1000], 0);
        return mdPyramidService.writeOutput();

    }

}
