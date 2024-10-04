import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Main {

    static int[][] map;
    static String result = "gg";
    static int[] first = new int[5];

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        var L = Integer.parseInt(bf.readLine());

        var cnt = 0;

        Arrays.fill(first, -1);

        var peace = new char[5][][];

        for (int p=0; p<5; p++) {
            var NM = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            peace[p] = new char[NM[0]][NM[1]];

            for (int n=0; n<NM[0]; n++) {
                peace[p][n] = bf.readLine().toCharArray();
                for (int m=0; m<NM[1]; m++) {
                    if (peace[p][n][m]=='#') {
                        if (n==0 && first[p]==-1) first[p] = m;
                        cnt++;
                    }
                }
            }
        }

        if (cnt!=L*L) {
            System.out.println("gg");
            return;
        }
        solution(L, peace);
        System.out.println(result);
    }

    static void solution(int L, char[][][] peace) {
        map = new int[L][L];
        var used = new boolean[5];
        fill(L, peace, 0, used, 0);
    }

    private static boolean fill(int L, char[][][] peace, int cnt, boolean[] used, int now) {
        if (cnt==5) {
            var sb = new StringBuilder();
            for (int i=0; i<L; i++) {
                for (int j=0; j<L; j++) {
                    sb.append(map[i][j]);
                }
                sb.append('\n');
            }
            result = sb.toString();
            return true;
        }
        var i = now/L;
        var j = now%L;

        for (int p=0; p<5; p++) {
            if (used[p]) continue;
            var x = i;
            var y = j-first[p];

            if (y<0 || y+peace[p][0].length>L || x+peace[p].length>L) continue;

            if (!valid(x, y, p, peace)) continue;

            draw(x, y, p, peace);

            used[p] = true;
            if (fill(L, peace, cnt+1, used, findNext(L, now))) return true;
            used[p] = false;
            erase(x, y, p, peace);
        }

        return false;
    }

    static int findNext(int L, int now) {
        while (now<L*L) {
            if (map[now/L][now%L]==0) return now;
            now++;
        }
        return -1;
    }

    static boolean valid(int x, int y, int p, char[][][] peace) {
        for (int i=0; i< peace[p].length; i++) {
            for (int j=0; j<peace[p][0].length; j++) {
                if (peace[p][i][j]=='#' && map[x+i][y+j]>0) return false;
            }
        }
        return true;
    }

    static void draw(int x, int y, int p, char[][][] peace) {
        for (int i=0; i< peace[p].length; i++) {
            for (int j=0; j<peace[p][0].length; j++) {
                if (peace[p][i][j]=='#') map[x+i][y+j] = p+1;
            }
        }
    }

    static void erase(int x, int y, int p, char[][][] peace) {
        for (int i=0; i< peace[p].length; i++) {
            for (int j=0; j<peace[p][0].length; j++) {
                if (peace[p][i][j]=='#') map[x+i][y+j] = 0;
            }
        }
    }
}