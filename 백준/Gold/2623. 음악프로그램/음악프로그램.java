import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        var NM = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        var N = NM[0];
        var M = NM[1];

        var arr = new ArrayList<Node>();

        for (int i=0; i<=N; i++){
            arr.add(new Node(i));
        }

        for (int i=0; i<M; i++){
            var now = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            arr.get(now[1]).next.add(arr.get(now[2]));
            for (int j=2; j< now.length-1; j++){
                arr.get(now[j]).next.add(arr.get(now[j+1]));
                arr.get(now[j]).before.add(arr.get(now[j-1]));
            }
            arr.get(now[now.length-1]).before.add(arr.get(now[now.length-2]));
        }



        System.out.println(solution(N, arr));
    }

    static String solution(int N, List<Node> arr){
        var result = new ArrayList<Integer>();
        var q = new LinkedList<Node>();
        for (int i=1; i<arr.size(); i++){
//            System.out.println(arr.get(i));
            if (arr.get(i).beforeIsEmpty()) {
                q.add(arr.get(i));
            }
        }

        while (!q.isEmpty()) {
            var now = q.remove();
            result.add(now.n);
            for (Node n: now.next){
                if (n.check(now)){
                    q.add(n);
                }
            }
        }
        return result.size()==N?result.stream().map(String::valueOf).collect(Collectors.joining("\n")):"0";
    }


    static class Node {
        Set<Node> before;
        int n;
        Set<Node> next;

        Node(int n){
            this.before = new HashSet<Node>();
            this.n = n;
            this.next = new HashSet<Node>();
        }

        boolean beforeIsEmpty(){
            return before.isEmpty();
        }

        boolean check(Node node){
            before.remove(node);
            return beforeIsEmpty();
        }

        public String toString(){
            StringJoiner sj = new StringJoiner("\n","\n==================\n", "\n==================\n");
            sj.add("before");
            for (Node node: before) sj.add(String.valueOf(node.n));
            sj.add("n = "+ n);
            sj.add("next");
            for (Node node: next) sj.add(String.valueOf(node.n));
            return sj.toString();
        }

    }
}