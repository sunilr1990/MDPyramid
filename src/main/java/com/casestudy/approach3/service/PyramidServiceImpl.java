package com.casestudy.approach3.service;

import com.casestudy.approach3.domain.Node;

public class PyramidServiceImpl implements PyramidService {

    @Override
    public int run(String filePath) {
        Node mdPyramidService = new Node();
        mdPyramidService.readInput(filePath);
        mdPyramidService.convertToDiagonalTree();
        mdPyramidService.findMaximumSumPath(mdPyramidService.getRoot(), new int[1000], 0);
        return mdPyramidService.writeOutput();

    }

}
