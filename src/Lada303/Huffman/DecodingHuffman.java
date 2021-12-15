package Lada303.Huffman;
/*
Восстановите строку по её коду и беспрефиксному коду символов.
В первой строке входного файла заданы два целых числа k и l через пробел — количество различных букв,
встречающихся в строке, и размер получившейся закодированной строки, соответственно. В следующих k строках
записаны коды букв в формате "letter: code". Ни один код не является префиксом другого. Буквы могут быть
перечислены в любом порядке. В качестве букв могут встречаться лишь строчные буквы латинского алфавита;
каждая из этих букв встречается в строке хотя бы один раз. Наконец, в последней строке записана
закодированная строка. Исходная строка и коды всех букв непусты. Заданный код таков, что закодированная
строка имеет минимальный возможный размер.
В первой строке выходного файла выведите строку s. Она должна состоять из строчных букв латинского алфавита.
Гарантируется, что длина правильного ответа не превосходит 10^4 символов.
 */

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
//Без использования дерева
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
