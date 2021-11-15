package Lada303;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class DecodingHuffman {

    static void run() {
        Scanner sc = new Scanner(System.in);
        int countChar = sc.nextInt();
        int sizeEncodingString = sc.nextInt();
        sc.nextLine();
        Map<String, String> mapCharCod= new HashMap<>();
        for (int i = 0; i < countChar; i++) {
            String str = sc.nextLine();
            mapCharCod.put(str.split(" ")[1], String.valueOf(str.charAt(0)));
        }
        String encodingStr = sc.nextLine();
        decoding(encodingStr, mapCharCod);
    }

    static void decoding(String encodingString, Map<String, String> mapCharCod) {
        if (encodingString.length() == 1) {
            System.out.println(mapCharCod.get(encodingString));
            return;
        }
        int index = 0;
        while (index<encodingString.length()) {
            for (int i = 1; i < encodingString.length(); i++) {
                String code = encodingString.substring(index, index + i);
                if (mapCharCod.containsKey(code)) {
                    System.out.print(mapCharCod.get(code));
                    index += i;
                    break;
                }
            }
        }
        System.out.println();
    }
}
