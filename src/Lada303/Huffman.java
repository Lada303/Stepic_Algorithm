package Lada303;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class Huffman {

    static int counterChar=0;
    static String[][] arrayChar;
    static List <TreeElement> listTree = new LinkedList<>();
    static int lengthCode=0;

    static void encodingHuffman(String str) {
        arrayCharFrequencyFromString(str);
        for (int i = 0; i < counterChar; i++) {
            listTree.add(new TreeElement(arrayChar[i][0], Integer.parseInt(arrayChar[i][1])));
            lengthCode += Integer.parseInt(arrayChar[i][1]);
        }
        listTree.sort(Comparator.comparingInt(TreeElement::getFrequency));

        int min1;
        int min2;
        int indexTake = 0;
        while (indexTake < counterChar*2-2) {
            min1 = indexTake++;
            listTree.get(min1).setCode("0");
            min2 = indexTake++;
            listTree.get(min2).setCode("1");
            int newFrequency = listTree.get(min1).getFrequency() + listTree.get(min2).getFrequency();
            for (int i = indexTake; i < listTree.size() ; i++) {
                if (listTree.get(i).getFrequency() > newFrequency) {
                    listTree.add(i, new TreeElement(listTree.get(min1).getName() + listTree.get(min2).getName(),
                            newFrequency));
                    lengthCode += newFrequency;
                    break;
                }
                if (i == listTree.size()-1) {
                    listTree.add(new TreeElement(listTree.get(min1).getName() + listTree.get(min2).getName(),
                            newFrequency));
                    lengthCode += newFrequency;
                    break;
                }
            }
        }
        printAnswer(str);
    }

    static void arrayCharFrequencyFromString (String str) {
        arrayChar = new String[str.length()][3];
        for (int i = 0; i < str.length(); i++) {
            for (int j = 0; j < str.length(); j++) {
                if (arrayChar[j][0] == null) {
                    arrayChar[j][0] = str.substring(i,i+1);
                    arrayChar[j][1] = "1";
                    counterChar++;
                    break;
                }
                if (str.substring(i,i+1).equals(arrayChar[j][0])) {
                    arrayChar[j][1] = Integer.toString(Integer.parseInt(arrayChar[j][1])+1);
                    break;
                }
            }
        }
    }


    static void printAnswer(String str) {
        System.out.println(counterChar + " " + lengthCode);
        if (counterChar == 1) {
            listTree.get(0).setCode("0");
        }
        for (int i = 0; i < counterChar; i++) {
            StringBuilder codChar = new StringBuilder();
            for (int j = listTree.size()-1; j >=0; j--) {
                if (listTree.get(j).getName().contains(arrayChar[i][0])) {
                    codChar.append(listTree.get(j).getCode());
                }
            }
            arrayChar[i][2] = codChar.toString();
            System.out.println(arrayChar[i][0] + ": " + arrayChar[i][2]);
        }
        for (int i = 0; i < str.length(); i++) {
            for (String[] strings : arrayChar) {
                if (str.charAt(i) == strings[0].charAt(0)) {
                    System.out.print(strings[2]);
                    break;
                }
            }
        }
    }
}

class TreeElement {
    private final String name;
    private final int frequency;
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
