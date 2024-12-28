import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

/*
a b k d e g h i l m n ng o p r s t u w y
a b c d e g h i l m n    o p r s t u w y
k랑 c 그리고 ng가 문제
k라면 c를 주고 ng를 o로 주고  o이상인 애들을 전부 +1시킴

원래대로 돌리는 방법은 c는 k로 o는 ng로 p이상이면 -1
 */

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bf.readLine());

        List<String> result  = new ArrayList<>(N);

        for (int i=0; i<N; i++) {
            result.add(convertSort(bf.readLine()));
        }

        result.sort(Comparator.naturalOrder());

        System.out.println(result.stream().map(Main::convertOriginal).collect(Collectors.joining("\n")));
    }

    static String convertSort(String s) {
        boolean isNg = false;
        StringBuilder result = new StringBuilder();

        for (char c : s.toCharArray()) {
            if (isNg) {
                if (c == 'g') {
                    result.append('o');
                    isNg = false;
                    continue;
                }
                result.append('n');
                isNg = false;
            }

            if (c=='k') {
                result.append('c');
            } else if (c == 'n') {
                isNg = true;
            } else if (c >= 'o') {
                result.append((char) (c + 1));
            } else {
                result.append(c);
            }
        }

        if (isNg) result.append('n');
        
        return result.toString();
    }
    
    static String convertOriginal(String s) {
        StringBuilder result = new StringBuilder();
        
        for (char c: s.toCharArray()) {
            if (c == 'c') {
                result.append('k');
            } else if (c == 'o') {
                result.append("ng");
            } else if (c > 'o') {
                result.append((char) (c-1));
            } else {
                result.append(c);
            }
        }

        return result.toString();
    }
}