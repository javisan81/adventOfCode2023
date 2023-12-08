package day8;

import java.util.Objects;

public class NodeVisited {
    private final Node node;
    private final String nextInstructions;

    public NodeVisited(Node node, String nextInstructions) {
        this.node = node;
        this.nextInstructions = nextInstructions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NodeVisited that = (NodeVisited) o;
        return Objects.equals(node, that.node) && Objects.equals(nextInstructions, that.nextInstructions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(node, nextInstructions);
    }

    public String key() {
        return node.toString()+"-"+nextInstructions;
    }
}
