import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bf.readLine());

        StringTokenizer st = new StringTokenizer(bf.readLine());

        List<String> arr = new ArrayList<String>();

        for (int i=0; i<N; i++) {
            arr.add(st.nextToken());
        }

        System.out.println(solution(N, arr));
    }

    static String solution(int N, List<String> arr) {
        arr.sort(Main::compare);

        StringBuilder result = new StringBuilder();

        for (String s : arr) {
            result.append(s);
        }

        int s = 0;
        while (s<N && result.charAt(s) == '0') {
            s++;
        }

        if (s==result.length()) s--;

        return result.substring(s, result.length());
    }


    static int compare(String s1, String s2) {
        return -(s1+s2).compareTo(s2+s1);
    }
}