package org.example;

import lombok.AllArgsConstructor;
import org.example.exception.NodeNotFoundException;
import org.example.model.Node;
import org.example.model.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Represents a grid.
 */
@AllArgsConstructor
public class Grid {
    public static final char START = 'S';
    public static final char END = 'E';
    public static final char FREE = 'O';
    private final char[][] nodes;


    public List<Node> getShortestPath() {
        Map<Node, Node> parents = new HashMap<>();
        Node start = getSEPoints().getLeft();
        Queue<Node> temp = new PriorityQueue<>();
        temp.add(start);
        start.setDist(0);
        parents.put(start, null);

        while (!temp.isEmpty()) {
            Node currentNode = temp.remove();
            List<Node> children = getChildren(currentNode);
            for (Node child : children) {
                if (!parents.containsKey(child)) {
                    char value = child.getValue();
                    if (Arrays.asList(FREE, END).contains(value)) {
                        temp.add(child);
                        parents.put(child, currentNode);
                    }
                }
            }
        }
        return determinePath(parents, getSEPoints().getRight());
    }

    private List<Node> determinePath(Map<Node, Node> parents, Node end) {
        Node node = end;
        List<Node> path = new ArrayList<>();
        while (node != null) {
            path.add(0, node);
            node = parents.get(node);
        }
        return path;
    }

    private Pair<Node, Node> getSEPoints() {
        Node start = null;
        Node end = null;
        for (int row = 0; row < nodes.length; row++) {
            for (int column = 0; column < nodes[row].length; column++) {
                if (nodes[row][column] == START) {
                    start = new Node(row, column, nodes[row][column]);
                }
                if (nodes[row][column] == END) {
                    end = new Node(row, column, nodes[row][column]);
                }
            }
        }

        if (start == null) {
            throw new NodeNotFoundException("Start node hasn't been found.");
        }

        if (end == null) {
            throw new NodeNotFoundException("End node hasn't been found.");
        }
        return new Pair<>(start, end);
    }

    private List<Node> getChildren(Node parent) {
        List<Node> children = new ArrayList<>();
        int x = parent.getX();
        int y = parent.getY();
        double diagonalMoveCost = Math.min(parent.getDist() + 1.414, Double.MAX_VALUE);
        double straightMoveCost = Math.min(parent.getDist() + 1, Double.MAX_VALUE);

        // UP LEFT
        if (x - 1 >= 0 && y - 1 >= 0) {
            Node child = new Node(x - 1, y - 1, nodes[x - 1][y - 1], diagonalMoveCost);
            children.add(child);
        }

        // UP RIGHT
        if (x - 1 >= 0 && y + 1 < nodes[0].length) {
            Node child = new Node(x - 1, y + 1, nodes[x - 1][y + 1], diagonalMoveCost);
            children.add(child);
        }

        // DOWN LEFT
        if (x + 1 < nodes.length && y - 1 >= 0) {
            Node child = new Node(x + 1, y - 1, nodes[x + 1][y - 1], diagonalMoveCost);
            children.add(child);
        }

        // DOWN RIGHT
        if (x + 1 < nodes.length && y + 1 < nodes[0].length) {
            Node child = new Node(x + 1, y + 1, nodes[x + 1][y + 1], diagonalMoveCost);
            children.add(child);
        }

        // UP
        if (x - 1 >= 0) {
            Node child = new Node(x - 1, y, nodes[x - 1][y], straightMoveCost);
            children.add(child);
        }
        // LEFT
        if (y - 1 >= 0) {
            Node child = new Node(x, y - 1, nodes[x][y - 1], straightMoveCost);
            children.add(child);
        }
        // DOWN
        if (x + 1 < nodes.length) {
            Node child = new Node(x + 1, y, nodes[x + 1][y], straightMoveCost);
            children.add(child);
        }

        // RIGHT
        if (y + 1 < nodes[0].length) {
            Node child = new Node(x, y + 1, nodes[x][y + 1], straightMoveCost);
            children.add(child);
        }
        return children;
    }
}


