package com.casestudy.pyramid.service;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import com.casestudy.pyramid.domain.Node;
import com.casestudy.pyramid.exception.PyramidException;

public class PyramidServiceImpl implements PyramidService {

	private Node root = null;
	private int maxPathSum = 0;
	private List<int[]> inputArrayList = new ArrayList<>();

	@Override
	public void readInput(final String filePath) {
		validateFilePath(filePath);
		try {
			URL url = getClass().getResource(filePath);
			if (null == url) {
				throw new PyramidException("File does not exist");
			}
			File file = new File(url.getPath());
			Files.lines(Paths.get(file.getPath())).forEach(s -> getArrayFromLine(s));
		} catch (IOException exception) {
			throw new PyramidException("Exception occured while reading file " + filePath, exception);
		}
	}
	
	private void validateFilePath(final String filePath) {
        if (null == filePath || filePath.isEmpty()) {
            throw new PyramidException("File path can not be null or empty");
        }
    }

	private void getArrayFromLine(final String line) {
		inputArrayList.add(Arrays.stream(line.trim().split("\\s+")).mapToInt(Integer::parseInt).toArray());
	}

	@Override
	public void constructTree() {
		inputArrayList.forEach(t -> addNewLevelToTree(t));
	}

	@Override
	public void convertToDiagonalTree() {
		if (root == null)
			return;

		if (root.leftNode == null && root.rightNode == null)
			return;

		Queue<Node> queue = new LinkedList<>();
		queue.add(root.leftNode);
		queue.add(root.rightNode);

		while (!queue.isEmpty()) {
			int count = queue.size();
			int nodeCount = 0;
			while (!queue.isEmpty()) {
				nodeCount++;
				if (nodeCount > count) {
					break;
				}
				Node qNode = queue.poll();
				if (qNode.leftNode != null) {
					queue.add(qNode.leftNode);
				}
				if (qNode.rightNode != null) {
					queue.add(qNode.rightNode);
				}
				if (qNode.rightNode != null)
					break;
				Node peakNode = queue.peek();
				if (peakNode != null) {
					if (peakNode.leftNode != null) {
						qNode.rightNode = peakNode.leftNode;
					}
				}
			}
		}

	}

	@Override
	public int writeOutput() {
		return maxPathSum;
	}

	private void addNewLevelToTree(int[] nodes) {

		if (nodes == null || nodes.length < 1)
			return;

		if (nodes.length == 1 && root == null) {
			root = new Node(nodes[0]);
			return;
		}

		if (nodes.length == 2 && root != null) {
			root.leftNode = new Node(nodes[0]);
			root.rightNode = new Node(nodes[1]);
			return;
		}
		int levelCount = 1;
		Queue<Node> queue = new LinkedList<Node>();
		queue.add(root);
		while (!queue.isEmpty()) {
			levelCount++;
			int count = queue.size();
			int nodeCount = 0;
			while (!queue.isEmpty()) {
				nodeCount++;
				if (nodeCount > count) {
					break;
				}
				Node queueNode = queue.poll();
				if (queueNode.leftNode != null) {
					queue.add(queueNode.leftNode);
				}
				if (queueNode.rightNode != null) {
					queue.add(queueNode.rightNode);
				}
			}
			if (levelCount == nodes.length - 1) {
				break;
			}
		}
		int index = -1;
		while (queue.size() > 1) {
			Node queueNode = queue.poll();
			queueNode.leftNode = new Node(nodes[++index]);
		}
		Node queueNode = queue.poll();
		queueNode.leftNode = new Node(nodes[++index]);
		queueNode.rightNode = new Node(nodes[++index]);
	}

	@Override
	public void findMaximumSumPath(Node node, int[] path, int pathLen) {

		if (node == null)
			return;
		path[pathLen] = node.data;
		pathLen++;
		if (node.leftNode == null && node.rightNode == null)
			calculateSum(path, pathLen);
		else {
			findMaximumSumPath(node.leftNode, path, pathLen);
			findMaximumSumPath(node.rightNode, path, pathLen);
		}

	}

	private void calculateSum(int ints[], int len) {
		int i;
		int value = 0;
		for (i = 0; i < len; i++) {
			if (i > 0) {
				if (ints[i - 1] % 2 != 0 && ints[i] % 2 != 0) {
					value = -1;
					break;
				}
			}
			value += ints[i];
		}
		if (maxPathSum < value) {
			maxPathSum = value;
		}
	}

	@Override
	public Node getRoot() {
		return root;
	}

}
