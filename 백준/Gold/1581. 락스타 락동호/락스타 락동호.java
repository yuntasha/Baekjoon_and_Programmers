import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
1. 느린거로 끝나면 느린거로 시작
2. 빠른거로 끝나면 빠른거로 시작
3. FF, FS으로 먼저 시작

최대 몇곡?

FS = 0, SF = 0 -> FF SS중 많은 것
FS > 0, SF = 0 -> FF SS + 1 하면 됨
FS = 0, SF > 0 -> SS + 1
FS > 0, SF > 0, FS = SF -> FF SS FS SF
FS > 0, SF > 0, FS != SF -> FF SS min(SF*2+1, FF*2)
 */

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(bf.readLine());

        int FF = Integer.parseInt(st.nextToken());
        int FS = Integer.parseInt(st.nextToken());
        int SF = Integer.parseInt(st.nextToken());
        int SS = Integer.parseInt(st.nextToken());

        System.out.println(solution(FF, FS, SF, SS));
    }

    static int solution(int FF, int FS, int SF, int SS) {
        if (FS == 0 && SF == 0) {
            if (FF > 0) return FF;
            return Math.max(FF, SS);
        }
        if (FS > 0 && SF == 0) {
            return FF + SS + 1;
        }
        if (FS == 0 && SF > 0) {
            if (FF > 0) return FF;
            return Math.max(FF, SS + 1);
        }
        return FF + SS + Math.min(SF * 2 + 1, FS * 2);
    }
}