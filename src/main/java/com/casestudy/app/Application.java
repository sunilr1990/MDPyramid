package com.casestudy.app;

import com.casestudy.pyramid.service.PyramidService;
import com.casestudy.pyramid.service.PyramidServiceImpl;

/**
 * Application class to run the case study implementation
 */
public class Application {

	public static int run(final String filePath) {
		PyramidService mdPyramidService = new PyramidServiceImpl();
		mdPyramidService.readInput(filePath);
		mdPyramidService.constructTree();
		mdPyramidService.convertToDiagonalTree();
		mdPyramidService.findMaximumSumPath(mdPyramidService.getRoot(), new int[1000], 0);
		return mdPyramidService.writeOutput();
	}

}
