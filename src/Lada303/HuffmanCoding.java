package Lada303;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;

public class HuffmanCoding {

    class Node implements Comparable <Node>{
        final int sumFrequency;
        String code;

        public Node(int sumFrequency) {
            this.sumFrequency = sumFrequency;
        }

        void buildCode(String code) {
            this.code = code;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(sumFrequency, o.sumFrequency);
        }
    }

    class ParentNode extends Node {
        Node left;
        Node right;

        public ParentNode(Node left, Node right) {
            super(left.sumFrequency + right.sumFrequency);
            this.left = left;
            this.right = right;
        }

        @Override
        void buildCode(String code) {
            super.buildCode(code);
            left.buildCode(code + "0");
            right.buildCode(code + "1");
        }
    }

    class LeafNode extends Node {
        char symbol;

        public LeafNode(char symbol, int count) {
            super(count);
            this.symbol = symbol;
        }

        @Override
        void buildCode(String code) {
            super.buildCode(code);
            System.out.println(symbol + ": " + super.code);
        }
    }

    public void run () throws FileNotFoundException {
        Scanner input = new Scanner(new File("input.txt"));
        String str = input.nextLine();
        Map<Character, Integer> charFrequency = new HashMap<>();

        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (charFrequency.containsKey(ch)) {
                charFrequency.put(ch, charFrequency.get(ch) + 1);
            } else {
                charFrequency.put(ch, 1);
            }
        }
        //charFrequency.forEach((k,v) -> System.out.println(k + ": " + v));

        PriorityQueue<Node> priorityQueue = new PriorityQueue<>();
        Map<Character, Node> charNode = new HashMap<>();
        for (Map.Entry<Character, Integer> entry : charFrequency.entrySet()) {
            LeafNode leafNode = new LeafNode(entry.getKey(), entry.getValue());
            charNode.put(entry.getKey(), leafNode);
            priorityQueue.add(leafNode);
        }
        int sumAllFrequency=0;
        while (priorityQueue.size() > 1) {
            Node first = priorityQueue.poll();
            Node second = priorityQueue.poll();
            ParentNode parentNode = new ParentNode(first, second);
            sumAllFrequency += parentNode.sumFrequency;
            priorityQueue.add(parentNode);
        }
        if (charFrequency.size() == 1) {
            sumAllFrequency = str.length();
        }
        System.out.println(charFrequency.size() + " " + sumAllFrequency);

        Node root = priorityQueue.poll();
        if (charFrequency.size() == 1) {
            root.buildCode("0");
        } else {
            root.buildCode("");
        }

        StringBuilder encodingString = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            encodingString.append(charNode.get(ch).code);
        }
        System.out.println(encodingString);

    }

    public static void main(String[] args) throws FileNotFoundException {

        long startTime = System.currentTimeMillis();
        new HuffmanCoding().run();
        long finishTime = System.currentTimeMillis();
        System.out.println(finishTime - startTime + " ms");
    }
}
