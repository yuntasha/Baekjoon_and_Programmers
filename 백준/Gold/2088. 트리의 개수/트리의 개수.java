import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

/*
a 진입했어
그럼 a ~~~ a로 끝나는지 확인
안끝나면 그냥 0
끝나면 a --- a --- a 처럼 중간에 A가 있는지 확인
a ---- a ---- a ---- a 이렇게 여러개로 나뉘는 경우 안됨

왼쪽은 항상 붙여서 사용할까
a --- a , a ---- a ---- a ---- a...
a ------ a, a ---- a ---- a
그러니까 앞에 붙여서 했기 때문에
s + 1 m - 1, m e
앞에는 일단 자식으로 내려감
한 자식으로 내려간다는 거니까 안겹치겠다

있으면
왼쪽, 오른쪽 재귀적인 진입

ABABABA
B B B
  A

  이안됨

  CurrentTimeScale = FMath::Max(CurrentTimeScale + (FinalDesiredTimeScale - CurrentTimeScale) * TimeScaleEasingFactor * InDeltaTime, 0.f);
 */

public class Main {

    static long MAX = 1_000_000_000;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        System.out.println(solution(bf.readLine()));
    }

    static long solution(String s) {
        long[][] dp = new long[s.length()][s.length()];
        long result = getCount(0, s.length() - 1, s, dp);
        return result;
    }

    static long getCount(int sIdx, int eIdx, String s, long[][] dp) {
        if (sIdx > eIdx) return 0;
        if (sIdx == eIdx) return 1;

        if (dp[sIdx][eIdx] != 0) return Math.max(dp[sIdx][eIdx], 0);

        long result = 0;

        for (int mid = sIdx + 1; mid <= eIdx; mid++) {
            if (s.charAt(sIdx) == s.charAt(mid)) {
                long l = getCount(sIdx + 1, mid - 1, s, dp);
                long r = getCount(mid, eIdx, s, dp);
                result += (l * r) % MAX;
                result %= MAX;
            }
        }

        if (result == 0) {
            dp[sIdx][eIdx] = -1;
        } else {
            dp[sIdx][eIdx] = result;
        }

        return result;
    }
}