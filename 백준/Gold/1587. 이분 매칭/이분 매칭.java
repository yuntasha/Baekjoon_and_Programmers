import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
둘 다 홀수인 경우 홀수끼리 이어진 선이 있으면 합/2 선이 없으면 합/2-1
나머지는 합/2

 */

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(bf.readLine());

        int nA = Integer.parseInt(st.nextToken());
        int nB = Integer.parseInt(st.nextToken());

        int M = Integer.parseInt(bf.readLine());

        boolean isOdd = false;

        for (int m=0; m<M; m++) {
            st = new StringTokenizer(bf.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if ((a&b&1) == 1) isOdd = true;
        }

        System.out.println(((nA + nB)>>1) - (((nA&1)==1 && (nB&1)==1) & !isOdd?1:0));
    }
}