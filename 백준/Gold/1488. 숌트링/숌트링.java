import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        var st = new StringTokenizer(bf.readLine());

        var countA = Integer.parseInt(st.nextToken());
        var countB = Integer.parseInt(st.nextToken());
        var maxA = Integer.parseInt(st.nextToken());
        var maxB = Integer.parseInt(st.nextToken());

        System.out.println(solution(countA, countB, maxA, maxB));
    }

    static int solution(int countA, int countB, int maxA, int maxB) {
//        answer = 0
//
//        if countA > countB:
//        countA, countB, maxA, maxB = countB, countA, maxB, maxA
//
//        if countA == 0 or maxA == 0:
//            answer = maxB if maxB < countB else countB
//        elif countB == 0 or maxB == 0:
//            answer = maxA if maxA < countA else countA
//        elif countA == countB:
//            answer += (countA * 2)
//        else:
//        n = countA + 1
//        answer += (countA + n)
//        countB -= n
//        answer += ((maxB-1)*n) if (countB//n) >= (maxB -1) else countB

        var answer = 0;

        if (countA > countB) {
            var temp = countA;
            countA = countB;
            countB = temp;
            temp = maxA;
            maxA = maxB;
            maxB = temp;
        }

        if (countA == 0 || maxA == 0) {
            answer = Math.min(maxB, countB);
        } else if (countB == 0 || maxB == 0) {
            answer = Math.min(maxA, countA);
        } else if (countA == countB) {
            answer = countA * 2;
        } else {
            var n = countA + 1;
            answer += countA + n;
            countB -= n;
            if ((countB/n) >= maxB-1) {
                answer += (maxB-1) * n;
            } else {
                answer += countB;
            }
        }

        return answer;
    }
}