package day8;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class MapNodeParser {
    private final List<String> inputData;

    public MapNodeParser(List<String> inputData) {
        this.inputData = inputData;
    }

    public MapNodes parse() {
        List<String> nodes = new ArrayList<>(inputData);
        nodes.remove(0);
        return new MapNodes(
                nodes.stream()
                        .filter(Predicate.not(String::isEmpty))
                        .map(this::createNode)
                        .collect(Collectors.toList())
        );
    }

    private Node createNode(String line) {
        String nodeName = line.split("=")[0].strip();
        String leftNode = nextNode(line, 'L');
        String rightNode = nextNode(line, 'R');
        return new Node(nodeName, leftNode, rightNode);
    }

    private static String nextNode(String line, char leftOrRight) {
        int pos = leftOrRight == 'L' ? 0 : 1;
        return line.split("=")[1]
                .strip()
                .split(",")[pos]
                .replaceAll("\\(", "")
                .replaceAll("\\)", "")
                .strip();
    }

    public String getInstructions() {
        return inputData.get(0);
    }
}
