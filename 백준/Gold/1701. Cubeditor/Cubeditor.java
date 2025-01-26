import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/*
KMP로 풀어보자
모든 문자열은 소문자
KMP에서는 현재 패턴에서 중복된 부분을 찾음
시작 부분을 다르게해서 KMP로 만들어서 구하는 것?
KMP... ㅇ믐.....
PI가 가장 큰 것을 찾는 것
그럼 일단 만들고 어디로 넘어갈지 놔야함
배열에는 어디로 이동할지
PI[N]으로 만듦
PI[N]의 값은 비교해야할 그 위치
맞으면 이전 인덱스에서 +1해서 저장
틀리면 그 위치로 초기화??

 */

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        System.out.println(solution(bf.readLine()));
    }

    public static int solution(String input) {
        int result = 0;

        int[] table;

        for (int i = 0; i < input.length(); i++) { // 그냥 문자열 시작 위치
            int idx = 0; // 이게 뭐냐면.... 앞에서부터 나오는 것 (접두사라고 하던데) DP[A] = A에서 틀렸다면
            int end = input.length() - i;
            table = new int[end];

            for (int j = 1; j < end; j++) { // 이건 현재 비벼가는 중 (접미사)

                while (idx > 0 && input.charAt(idx + i) != input.charAt(j + i)) { // 현재 위치
                    idx = table[idx-1];
                }

                if (input.charAt(idx + i) == input.charAt(j + i)) {
                    table[j] = ++idx;
                    result = Math.max(result, idx);
                }
            }
        }

        return result;
    }
}