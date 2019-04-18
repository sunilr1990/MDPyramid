package com.casestudy.pyramid.service;

import com.casestudy.pyramid.domain.Node;

public interface PyramidService {

	public abstract void readInput(final String filePath);

	public abstract void constructTree();

	public abstract void convertToDiagonalTree();

	public abstract void findMaximumSumPath(Node node, int path[], int pathLen);

	public abstract int writeOutput();
	
	public abstract Node getRoot();

}
