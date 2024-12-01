import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int MAX_VALUE = 5000;
    static int[][][][][] DP = new int[51][51][51][51][2];
    static char[] A;
    static char[] B;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        A = bf.readLine().toCharArray();
        B = bf.readLine().toCharArray();

        System.out.println(solution());
    }

    static int solution() {
        if (A.length != B.length) return -1;
        var result = find(0, A.length-1, 0, B.length-1, false);
        if (result >= MAX_VALUE) return -1;
        return result-1;
    }

    static int find(int as, int ae, int bs, int be, boolean isReverse) {
        if (as > ae) return 1;
        if (DP[as][ae][bs][be][isReverse ? 1 : 0] > 0) return DP[as][ae][bs][be][isReverse ? 1 : 0];

        var now = MAX_VALUE;

        if (isReverse) {
            if (A[as] == B[be]) now = Math.min(now, find(as + 1, ae, bs, be - 1, true));
            if (A[as] == B[bs]) now = Math.min(now, find(as + 1, ae, bs + 1, be, false) + 1);
            if (A[ae] == B[bs]) now = Math.min(now, find(as, ae - 1, bs + 1, be, true));
            if (A[ae] == B[be]) now = Math.min(now, find(as, ae - 1, bs, be - 1, false) + 1);
        } else {
            if (A[as] == B[bs]) now = Math.min(now, find(as + 1, ae, bs + 1, be, false));
            if (A[as] == B[be]) now = Math.min(now, find(as + 1, ae, bs, be - 1, true) + 1);
            if (A[ae] == B[be]) now = Math.min(now, find(as, ae - 1, bs, be - 1, false));
            if (A[ae] == B[bs]) now = Math.min(now, find(as, ae - 1, bs + 1, be, true) + 1);
        }

        DP[as][ae][bs][be][isReverse ? 1 : 0] = now;
        return now;
    }
}