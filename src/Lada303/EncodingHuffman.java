package Lada303;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class EncodingHuffman {

    static int counterChar=0;
    static List<TreeElement> listChar = new LinkedList<>();
    static List<TreeElement> listTree = new LinkedList<>();
    static int lengthCode=0;

    static void run(String str) {
        arrayCharFrequencyFromString(str);
        fillingTreeWithLeaves();
        listTree.sort(Comparator.comparingInt(TreeElement::getFrequency));
        fillingTreeWithNodes();
        createCharCode();
        printAnswer(str);
    }

    static void arrayCharFrequencyFromString (String str) {
        listChar.add(new TreeElement(String.valueOf(str.charAt(0)),1));
        counterChar++;
        for (int i = 1; i < str.length(); i++) {
            for (int j = 0; j < listChar.size(); j++) {
                if (str.charAt(i) == listChar.get(j).getName().charAt(0)) {
                    listChar.get(j).setFrequency(listChar.get(j).getFrequency() + 1);
                    break;
                }
                if (j == listChar.size() - 1) {
                    listChar.add(new TreeElement(String.valueOf(str.charAt(i)),1));
                    counterChar++;
                    break;
                }
            }
        }
    }

    static void fillingTreeWithLeaves() {
        for (int i = 0; i < counterChar; i++) {
            listTree.add(listChar.get(i));
            lengthCode += listChar.get(i).getFrequency();
        }
    }

    static void fillingTreeWithNodes() {
        if (counterChar == 1) {
            listTree.get(0).setCode("0");
            return;
        }
        int index = -1;
        while (index != listTree.size()-1) {
            listTree.get(index+1).setCode("0");
            listTree.get(index+2).setCode("1");
            String newName = listTree.get(index+1).getName() + listTree.get(index+2).getName();
            int newFrequency = listTree.get(index+1).getFrequency() + listTree.get(index+2).getFrequency();
            index += 2;
            for (int i = index+1; i < listTree.size() ; i++) {
                if (listTree.get(i).getFrequency() > newFrequency) {
                    listTree.add(i, new TreeElement(newName, newFrequency));
                    lengthCode += newFrequency;
                    break;
                }
                if (i == listTree.size()-1) {
                    listTree.add(new TreeElement(newName, newFrequency));
                    lengthCode += newFrequency;
                    break;
                }
            }
        }
    }

    static void createCharCode() {
        for (int i = 0; i < counterChar; i++) {
            StringBuilder codChar = new StringBuilder();
            for (int j = listTree.size()-1; j >=0; j--) {
                if (listTree.get(j).getName().contains(listChar.get(i).getName())) {
                    codChar.append(listTree.get(j).getCode());
                }
            }
            listChar.get(i).setCode(codChar.toString());
        }
    }

    static void printAnswer(String str) {
        System.out.println(counterChar + " " + lengthCode);
        for (int i = 0; i < counterChar; i++) {
            System.out.println(listChar.get(i).getName() + ": " + listChar.get(i).getCode());
        }
        for (int i = 0; i < str.length(); i++) {
            for (TreeElement charCode : listChar) {
                if (str.charAt(i) == charCode.getName().charAt(0)) {
                    System.out.print(charCode.getCode());
                    break;
                }
            }
        }
        System.out.println();
    }
}

class TreeElement {
    private final String name;
    private int frequency;
    private String code;

    TreeElement(String name, int f) {
        this.name = name;
        this.frequency = f;
    }

    String getName() {
        return name;
    }

    int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    String getCode() {
        return code;
    }

    void setCode(String code) {
        this.code = code;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (obj instanceof  TreeElement that) {
            return this.frequency == that.frequency;
        }
        return false;
    }

    @Override
    public String toString() {
        return "{" +name + " " + frequency + " " + code +'}';
    }
}
