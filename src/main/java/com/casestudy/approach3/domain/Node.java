package com.casestudy.approach3.domain;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

import com.casestudy.pyramid.exception.PyramidException;

/**
 * Domain class constructed in DDD approch to give max sum
 * 
 * @author sunil.r
 *
 */
public class Node {

    private int  data;
    private Node leftNode;
    private Node rightNode;
    private Node root       = null;
    private int  maxPathSum = 0;

    public Node(int data) {
        this.data = data;
    }

    public Node() {
    }

    /**
     * Converts loaded nodes to diagonal tree
     */
    public void convertToDiagonalTree() {

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
                Node qNode = addToQueue(queue);
                Node peakNode = queue.peek();
                if (peakNode != null && peakNode.leftNode != null) {
                    qNode.rightNode = peakNode.leftNode;
                }
            }
        }

    }

    private Node addToQueue(Queue<Node> queue) {
        Node qNode = queue.poll();
        if (qNode.leftNode != null) {
            queue.add(qNode.leftNode);
        }
        if (qNode.rightNode != null) {
            queue.add(qNode.rightNode);
        }
        return qNode;
    }

    /**
     * Adding array to nodes
     * 
     * @param nodes
     */
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
        Queue<Node> queue = new LinkedList<>();
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

    /**
     * Calculates max sum
     * 
     * @param node
     * @param path
     * @param pathLen
     */
    public void findMaximumSumPath(Node node, int[] path, int pathLen) {

        if (node == null)
            return;
        path[pathLen] = node.data;
        if (node.leftNode == null && node.rightNode == null)
            calculateSum(path, pathLen + 1);
        else {
            findMaximumSumPath(node.leftNode, path, pathLen + 1);
            findMaximumSumPath(node.rightNode, path, pathLen + 1);
        }

    }

    private void calculateSum(int[] num, int len) {
        int i;
        int value = 0;
        for (i = 0; i < len; i++) {
            if (i > 0 && num[i - 1] % 2 != 0 && num[i] % 2 != 0) {
                value = -1;
                break;
            }
            value += num[i];
        }
        if (maxPathSum < value) {
            maxPathSum = value;
        }
    }

    public Node getRoot() {
        return root;
    }

    public void readInput(final String filePath) {

        if (null == filePath || filePath.isEmpty()) {
            throw new PyramidException("File path can not be null or empty");
        }

        try {
            URL url = Node.class.getResource(filePath);

            if (null == url) {
                throw new PyramidException("File does not exist");
            }

            File file = new File(url.getPath());

            Files.lines(Paths.get(file.getPath())).forEach(s -> addNewLevelToTree(Arrays.stream(s.trim().split("\\s+")).mapToInt(Integer::parseInt).toArray()));
        } catch (IOException exception) {
            throw new PyramidException("Exception occured while reading file " + filePath, exception);
        }
    }

    public int writeOutput() {
        return maxPathSum;
    }

}
