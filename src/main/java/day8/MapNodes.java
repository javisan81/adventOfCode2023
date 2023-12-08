package day8;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MapNodes {
    private final List<Node> nodes;
    private final Map<String, Node> nodesByName;

    public MapNodes(List<Node> nodes) {
        this.nodes = nodes;
        this.nodesByName = nodes.stream().collect(Collectors.toMap(Node::getName, Function.identity()));
    }

    public long statesToAchieveZZZ(String instructions) {
        Node currentNode = nodesByName.getOrDefault("AAA", nodesByName.get("ZZZ"));
        return stepsToAchieve(instructions, currentNode, Node::isZZZ);
    }

    private long stepsToAchieve(String instructions, Node currentNode, Function<Node, Boolean> isTheEnd) {
        int steps = 0;
        int nextInstructionIndex = 0;
        List<String> nodesVisited = new ArrayList<>();
        while (!isTheEnd.apply(currentNode)) {
            currentNode = nodesByName.get(currentNode.nextNode(instructions.charAt(nextInstructionIndex)));
            NodeVisited nodeVisited = new NodeVisited(currentNode, instructions.substring(nextInstructionIndex));
            String key = nodeVisited.key();
            if (nodesVisited.contains(key)) {
                return -1;
            } else {
                nodesVisited.add(key);
            }
            nextInstructionIndex = nextInstructionIndex(instructions, nextInstructionIndex, 1);
            steps++;
        }
        return steps;
    }

    public long ghostMovesToZs(String instructions) {
        Map<String, Long> stepsByNodes = new HashMap<>();
        List<Node> startingNodes = nodesEndingIn("A");
        List<Node> endingNodes = nodesEndingIn("Z");
        for (Node startingNode : startingNodes) {
            for (Node endingNode : endingNodes) {
                long stepsToAchieve = stepsToAchieve(instructions, startingNode, (node -> node == endingNode));
                if (stepsToAchieve >= 0) {
                    stepsByNodes.put(startingNode.getName() + "-" + endingNode.getName(), stepsToAchieve);
                }
            }
        }
        return lcm(stepsByNodes.values());
    }

    private static long lcm(Collection<Long> numbers) {
        return numbers.stream().reduce(1L, (x, y) -> x * (y / gcd(x, y)));
    }

    private static long gcd(long x, long y) {
        return (y == 0) ? x : gcd(y, x % y);
    }

    private List<Node> nodesEndingIn(String suffix) {
        return nodes.stream()
                .filter(node -> node.endsWith(suffix))
                .collect(Collectors.toList());
    }

    private static int nextInstructionIndex(String instructions, int nextInstructionIndex, int stepsDone) {
        return (nextInstructionIndex + stepsDone) % instructions.length();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MapNodes mapNodes = (MapNodes) o;
        return Objects.equals(nodes, mapNodes.nodes) && Objects.equals(nodesByName, mapNodes.nodesByName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nodes, nodesByName);
    }

    @Override
    public String toString() {
        return "MapNodes{" +
                "nodes=" + nodes +
                ", nodesByName=" + nodesByName +
                '}';
    }


}
