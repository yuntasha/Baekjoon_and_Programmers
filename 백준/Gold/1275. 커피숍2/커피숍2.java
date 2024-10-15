import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        var NQ = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        var N = NQ[0];
        var Q = NQ[1];

        var arr = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        var tree = new SegTree(arr);

        for (int i=0; i<Q; i++) {
            var now = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            System.out.println(tree.find(Math.min(now[0], now[1]), Math.max(now[0], now[1])));
            tree.modify(now[2], now[3]);
        }
    }

    static class SegTree {
        long[] nodes;
        int range;

        SegTree(int[] arr) {
            this.range = arr.length;
            var ncnt = 1;
            while (ncnt<range) ncnt*=2;
            ncnt*=2;
            nodes = new long[ncnt];
            createTree(1, 1, range, arr);
        }

        long createTree(int now, int start, int end, int[] arr) {
            if (start == end) {
                nodes[now] = arr[start - 1];
            } else {
                var mid = (start + end) / 2;
                nodes[now] = createTree(now*2, start, mid, arr) + createTree(now*2+1, mid+1, end, arr);
            }

            return nodes[now];
        }

        long find(int start, int end) {
            return search(1, start, end, 1, range);
        }

        long search(int now, int sStart, int sEnd, int nStart, int nEnd) {
            if (nStart>=sStart && nEnd<=sEnd) return nodes[now];

            var result = 0L;
            var mid = (nStart + nEnd)/2;
            if (mid<sEnd) result += search(now*2+1, sStart, sEnd, mid+1, nEnd);
            if (sStart<=mid) result += search(now*2, sStart, sEnd, nStart, mid);
            return result;
        }

        void modify(int index, int num) {
            findModify(index, num, 1, 1, range);
        }

        long findModify(int index, int num, int now, int start, int end) {
            long result;
            if (start == end) {
                result = num - nodes[now];
                nodes[now] = num;
            } else {
                var mid = (start + end)/2;
                if(mid<index) result = findModify(index, num, now*2+1, mid+1, end);
                else result = findModify(index, num, now*2, start, mid);
                nodes[now] += result;
            }

            return result;
        }
    }
}