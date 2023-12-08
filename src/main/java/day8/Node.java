package day8;

import java.util.Objects;

public class Node {
    private final String nodeName;
    private final String leftNode;
    private final String rightNode;

    public Node(String nodeName, String leftNode, String rightNode) {
        this.nodeName = nodeName;
        this.leftNode = leftNode;
        this.rightNode = rightNode;
    }

    public boolean isZZZ() {
        return nodeName.equals("ZZZ");
    }

    public String nextNode(char instruction) {
        return switch (instruction) {
            case 'L' -> leftNode;
            case 'R' -> rightNode;
            default -> throw new IllegalStateException("Unexpected value: " + instruction);
        };
    }

    public String getName() {
        return nodeName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return Objects.equals(nodeName, node.nodeName) && Objects.equals(leftNode, node.leftNode) && Objects.equals(rightNode, node.rightNode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nodeName, leftNode, rightNode);
    }

    @Override
    public String toString() {
        return "Node{" +
                "nodeName='" + nodeName + '\'' +
                ", leftNode='" + leftNode + '\'' +
                ", rightNode='" + rightNode + '\'' +
                '}';
    }

    public boolean endsWith(String suffix) {
        return nodeName.endsWith(suffix);
    }
}
