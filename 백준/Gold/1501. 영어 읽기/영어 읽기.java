import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/*
앞 뒤 알파벳 같음
subString으로 빼서 중앙 정렬
그리고 트리맵으로 개수 세기
해시맵은 예외 있을수도
 */

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int W = Integer.parseInt(bf.readLine());

        Map<String, Integer> words = new TreeMap<>();

        for (int w = 0; w < W; w++) {
            String now = convert(bf.readLine());
            words.put(now, words.getOrDefault(now, 0) + 1);
        }

        int N = Integer.parseInt(bf.readLine());
        StringJoiner result = new StringJoiner("\n");

        for (int i=0; i<N; i++) {
            result.add(String.valueOf(solution(W, words, bf.readLine())));
        }

        System.out.println(result.toString());
    }

    public static int solution(int W, Map<String, Integer> words, String s) {
        String[] ws = s.split(" ");

        if (ws.length == 0) {
            return 0;
        }

        int result = 1;

        for (String w : ws) {
            result *= words.getOrDefault(convert(w), 0);
        }

        return result;
    }

    public static String convert(String s) {
        if (s.length() < 4) return s;

        StringBuilder sb = new StringBuilder();

        sb.append(s.charAt(0));

        char[] a = s.substring(1, s.length() - 1).toCharArray();
        Arrays.sort(a);
        sb.append(a);

        sb.append(s.charAt(s.length() - 1));
        
        return sb.toString();
    }
}