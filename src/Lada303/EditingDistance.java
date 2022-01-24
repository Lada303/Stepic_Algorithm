package Lada303;
/*
Вычислите расстояние редактирования двух данных непустых строк длины не более 10^2, содержащих
строчные буквы латинского алфавита.
 */
import java.util.*;

public class EditingDistance {

    public void run() {
        Scanner sc = new Scanner(System.in);
        String str1 = sc.nextLine();
        String str2 = sc.nextLine();
        List<int[]> list = new ArrayList<>();
        list.add(new int[str1.length() + 1]);
        for (int i = 0; i <= str1.length(); i++) {
            list.get(0)[i] = i;
        }
        System.out.println(Arrays.toString(list.get(0)));
        for (int j = 1; j <= str2.length(); j++) {
            list.add(new int[str1.length() + 1]);
            list.get(1)[0] = j;
            for (int i = 1; i <= str1.length(); i++) {
                int c = (str2.charAt(j - 1) == str1.charAt(i - 1) ? 0 : 1);
                int left = list.get(1)[i - 1] + 1;
                int up = list.get(0)[i] + 1;
                int x = list.get(0)[i - 1] + c;
                list.get(1)[i] = Math.min(left, Math.min (up, x));
            }
            //System.out.println(Arrays.toString(list.get(1)));
            list.remove(0);
        }
        System.out.println(list.get(0)[str1.length()]);
    }
}
