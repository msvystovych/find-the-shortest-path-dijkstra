package org.example;


import org.example.model.Node;
import org.example.util.Util;

import java.util.List;

import static org.example.util.Util.printPath;

public class App {
    public static void main(String[] args) {
        char[][] data = Util.readDataSet();
        printPath(findShortestPath(data), data);
    }

    public static List<Node> findShortestPath(char[][] data) {
        return new Grid(data).getShortestPath();
    }
}
