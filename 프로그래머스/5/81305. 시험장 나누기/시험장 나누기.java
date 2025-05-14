import java.util.*;
/*
하나의 노드는 하나의 시험장
검은 바탕의 흰 숫자는 각 시험장의 아이디
빨간 숫자는 각 시험장의 응시자 수
노드의 간선은 해당 시험장이 연결되어 있음

각 시험장에서 오는 트래픽을 분산
K개의 그룹이 있음

가장 큰 그룹의 값을 최소

각 시험장의 응시자 수를 나타내는 1차원 정수 배열 num
시험장의 연결 상태를 나타내는 정수 배열 links

k : 그룹의 수
num : 각 시험장의 응시자 수를 나타내는 배열
lines : i번째 행은 왼쪽 자식 노드 번호, 오른쪽 자식 노드 번호

10000개의 노드와 10000개의 배열

완탐은 어떻게하지 각 링크마다 걸어볼까
그럼 걸었을때하고 안걸었을때...

최대가 a라고 하면 a로 나눴을때 k개 이하로 나눠지는지 여부

일단 루트를 찾고
총합 - 각 자식노드 아이디 = 루트 아이디
루트부터 쫙쫙
*/

class Solution {
    public int solution(int k, int[] num, int[][] links) {
        int answer = 0;
        
        int root = getRootId(links);
        // System.out.println(root);        
        // System.out.println(isSuccess(k, 10, root, num, links));
        // System.out.println(isSuccess(k, 40, root, num, links));
        
        int s = 0;
        int e = 100_000_001;
        
        while (s + 1 < e) {
            int m = (s + e) >> 1;
            
            if (isSuccess(k, m, root, num, links)) {
                e = m;
            } else {
                s = m;
            }
        }
        
        return e;
    }
    
    public boolean isSuccess(int k, int n, int root, int[] num, int[][] links) {
        int[] result = find(root, n, num, links);
        // System.out.println(Arrays.toString(result));
        return result[0] < k && result[1] <= n;
    }
    
    public int[] find(int now, int n, int[] num, int[][] links) {
        int[] result = new int[2];
        
        if (num[now] > n) {
            result[0] = 10001;
            return result;
        }
        
        int[] a;
        int[] b;
        
        if (links[now][0] != -1) {
            a = find(links[now][0], n, num, links);
        } else {
            a = new int[2];
        }
        
        if (links[now][1] != -1) {
            b = find(links[now][1], n, num, links);
        } else {
            b = new int[2];
        }
        
        if (a[1] + b[1] + num[now] <= n) {
            // System.out.println("now0 " + now);
            // System.out.println(Arrays.toString(a));
            // System.out.println(Arrays.toString(b));
            result[0] = a[0] + b[0];
            result[1] = a[1] + b[1] + num[now];
        } else if (a[1] + num[now] <= n || b[1] + num[now] <= n) {
            // System.out.println("now1 " + now);
            // System.out.println(Arrays.toString(a));
            // System.out.println(Arrays.toString(b));
            result[0] = a[0] + b[0] + 1;
            result[1] = Math.min(a[1], b[1]) + num[now];
        } else {
            // System.out.println("now2 " + now);
            // System.out.println(Arrays.toString(a));
            // System.out.println(Arrays.toString(b));
            result[0] = a[0] + b[0] + 2;
            result[1] = num[now];
        }
        
        // System.out.println(Arrays.toString(result));
        
        return result;
    }
    
    public int getRootId(int[][] links) {
        int n = links.length;
        int result = (n) * (n - 1) / 2;
        for (int[] link : links) {
            for (int i : link) {
                if (i >= 0) result -= i;
            }
        }
        
        return result;
    }
}