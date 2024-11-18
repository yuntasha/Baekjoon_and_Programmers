import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        var NM = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        var N = NM[0];
        var T = NM[1];

        var segTree = new SegTree(N);

        StringJoiner sb = new StringJoiner("\n");

        for (int t = 1; t <= T; t++) {
            var input = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            sb.add(String.valueOf(solution(t, input[0], input[1], segTree)));
        }

        System.out.println(sb.toString());
    }

    static int solution(int t, int type, int port, SegTree segTree) {
        if (type==1) {
            return segTree.usePort(t, port);
        } else {
            return segTree.unUsedPort(port);
        }
    }

    static class SegTree{
        int[] nodes;
        int range;
        int[] log;

        SegTree(int N) {
            range = N;
            var ncnt = 1;
            while (ncnt<range) ncnt*=2;
            ncnt*=2;
            nodes = new int[ncnt];
            log = new int[ncnt];
            createNode(1, 1, N);
        }

        int createNode(int idx, int start, int end) {
            if (start==end) {
                nodes[idx] = 1;
            } else {
                var mid = (start + end) / 2;
                nodes[idx] = createNode(idx*2, start, mid) + createNode(idx*2+1, mid+1, end);
            }
            return nodes[idx];
        }

        int usePort(int t, int num) {
            var result = findAndUsePort(t, num, 1, 1, range);
            if (result==0) return -1;
            return result;
        }

        int findAndUsePort(int t, int num, int idx, int start, int end) {
            var mid = (start + end) / 2;
            if (start==end) {
                if (nodes[idx]==0) {
                    return 0;
                }
                log[idx] = t;
                nodes[idx] = 0;
                return end;
            } else {
                if (nodes[idx]==0) {
                    return 0;
                }
                if (mid>=num) {
                    var result = findAndUsePort(t, num, idx * 2, start, mid);
                    if (result > 0) {
                        nodes[idx]--;
                        return result;
                    }
                }

                var result = findAndUsePort(t, num, idx * 2 + 1, mid + 1, end);
                if (result > 0) {
                    nodes[idx]--;
                    return result;
                }
            }
            return 0;
        }

        int unUsedPort(int num) {
            return findUsedPort(num, 1, 1, range);
        }

        int findUsedPort(int num, int idx, int start, int end) {
            if (start==end) {
                if (nodes[idx] == 1) {
                    return -1;
                }
                nodes[idx] = 1;
                return log[idx];
            } else {
                var mid = (start+end)/2;
                int result;
                if (num>mid) {
                    result = findUsedPort(num, idx*2+1, mid+1, end);
                } else {
                    result = findUsedPort(num, idx*2, start, mid);
                }
                if (result!=-1) {
                    nodes[idx]++;
                }
                return result;
            }
        }
    }
}