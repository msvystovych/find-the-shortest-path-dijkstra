package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Objects;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class Node implements Comparable<Node> {
    private final int x;
    private final int y;
    private final char value;
    public double dist;

    public Node(int x, int y, char value) {
        this.x = x;
        this.y = y;
        this.value = value;
        this.dist = Integer.MAX_VALUE;
    }

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
        this.value = '-';
    }

    @Override
    public int compareTo(Node o) {
        return Double.compare(this.dist, o.dist);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {return true;}
        if (o == null || getClass() != o.getClass()) {return false;}
        Node node = (Node) o;
        return x == node.x && y == node.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}