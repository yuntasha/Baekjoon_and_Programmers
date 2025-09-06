/*
트리 구조로 만들면 될 것 같은데
노드에 이름, Set넣어주고
 */

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;


public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bf.readLine());

        Tree tree = new Tree();

        for (int i = 0; i < N; i++) {
            tree.insert(bf.readLine().split(" "));
        }

        System.out.print(tree);
    }

    public static class Tree {
        Map<String, Node> nodes;

        Tree() {
            this.nodes = new TreeMap<>();
        }

        public void insert(String[] input) {
            int N = Integer.parseInt(input[0]);

            if (!nodes.containsKey(input[1])) nodes.put(input[1], new Node(input[1]));
            Node now = nodes.get(input[1]);

            for (int i = 2; i <= N; i++) {
                if (!now.children.containsKey(input[i])) now.children.put(input[i], new Node(input[i]));
                now = now.children.get(input[i]);
            }
        }

        @Override
        public String toString() {
            StringBuilder output = new StringBuilder();
            for (String key : nodes.keySet()) {
                find(0, nodes.get(key), output);
            }

            return output.toString();
        }

        public void find(int depth, Node now, StringBuilder output) {
            for (int i = 0; i < depth; i++) output.append("--");
            output.append(now.name).append("\n");
            for (String key : now.children.keySet()) {
                find(depth + 1, now.children.get(key), output);
            }
        }
    }

    public static class Node {
        String name;
        Map<String, Node> children;

        public Node(String name) {
            this.name = name;
            this.children = new TreeMap<>();
        }

        public String getName() {
            return name;
        }
    }
}