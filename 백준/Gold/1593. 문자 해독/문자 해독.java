import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

/*
문자 : W
문자열 : S
문자가 가진 char의 수가 같으면 됨
슬라이딩 윈도우
 */

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(bf.readLine());

        int WN = Integer.parseInt(st.nextToken());
        int SN = Integer.parseInt(st.nextToken());

        int[] count = new int[52];
        
        for (char c : bf.readLine().toCharArray()) {
            count[convertIdx(c)]--;
        }
        
        int[] S = bf.readLine().chars().map(Main::convertIdx).toArray();

        System.out.println(solution(WN, SN, count, S));
    }

    static int solution(int WN, int SN, int[] count, int[] S) {
        for (int i=0; i<WN; i++) {
            count[S[i]]++;
        }

        int result = 0;
        
        if (chk(count)) result++;
        
        for (int i=0; i<SN-WN; i++) {
            count[S[i]]--;
            count[S[i+WN]]++;
            if (chk(count)) result++;
        }
        
        return result;
    }

    static boolean chk(int[] count) {
        for (int i : count) {
            if (i!=0) return false;
        }
        return true;
    }

    static int convertIdx(int c) {
        return c>='a'?c-'a':c-'A'+26;
    }
}