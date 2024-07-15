import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        var NM = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        var N = NM[0];
        var M = NM[1];

        var links = new LinkedList<Link>();

        for (int i=0; i<M; i++){
            var link = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            links.add(new Link(link[0], link[1], link[2]));
        }

        System.out.println(solution(N, M, links));
    }

    static int solution(int N, int M, LinkedList<Link> links){
        var nodeSet = IntStream.range(0, N+1).toArray();
        links.sort(Comparator.comparing(Link::getValue));
        var result = 0;
        var cnt = 0;
        while (cnt<N-1){
            var now = links.remove();
            var one = findMom(now.node1, nodeSet);
            var two = findMom(now.node2, nodeSet);

            if (one == two) continue;

            if (one < two) {
                nodeSet[two] = one;
            } else {
                nodeSet[one] = two;
            }
            cnt++;
            result += now.value;
        }
        return result;
    }

    static int findMom(int n, int[] nodeSet){
        if (n == nodeSet[n]) return n;
        nodeSet[n] = findMom(nodeSet[n], nodeSet);
        return nodeSet[n];
    }

    static class Link{
        int node1;
        int node2;
        int value;


        Link(int node1, int node2, int value){
            this.node1 = node1;
            this.node2 = node2;
            this.value = value;
        }

        int getValue(){
            return this.value;
        }
    }
}