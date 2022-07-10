package org.example.util;

import org.example.model.Node;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Util {
    public static final int ROWS = 20;
    public static final int COLUMNS = 39;
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String TEST_SET_FILE = "test.txt";
    public static final String PATH_ITEM = "+";

    public static char[][] readDataSet() {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        Scanner sc = new Scanner(Objects.requireNonNull(classloader.getResourceAsStream(TEST_SET_FILE)));

        char[][] chars = new char[ROWS][COLUMNS];
        while (sc.hasNextLine()) {
            for (char[] item : chars) {
                if (sc.hasNextLine()) {
                    char[] line = sc.nextLine().toCharArray();
                    System.arraycopy(line, 0, item, 0, line.length);
                }
            }
        }
        return chars;
    }

    public static void printPath(List<Node> path, char[][] nodes) {
        for (int row = 0; row < nodes.length; row++) {
            for (int column = 0; column < nodes[row].length; column++) {
                String value = nodes[row][column] + "";

                for (int i = 1; i < path.size() - 1; i++) {
                    Node node = path.get(i);
                    if (node.getX() == row && node.getY() == column) {
                        value = ANSI_RED + PATH_ITEM + ANSI_RESET;
                        break;
                    }
                }
                if (column == nodes[row].length - 1) {
                    System.out.println(value);
                } else {
                    System.out.print(value);
                }
            }
        }
        System.out.println();
        System.out.println("Path: " + path);
    }
}

