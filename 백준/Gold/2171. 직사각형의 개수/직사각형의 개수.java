/*
일단 흠...
x축 기점으로 각 위치를 찾아서 처리
그 다음에 y축 같은거 존재하는지 찾기
HashMap<Integer, HashSet<>>이렇게 만들면 될듯?
그냥 해시를 만들자
범위 ㅈㄴ크네...
점 2개를...
해시셋 쓰지말고 리스트로 만들어서 처리하자

x 2개 지정
y가 같은거 개수 찾기
n이라고 할때
result += n(n-1)/2
3개다? 3개임
xy를 그냥
Map사용해서 처리하자
좌표 줄이기로만 사용하고 더 쓰지말자
HashMap -> vToIdx
2차원 리스트로 해결
y값은 굳이 할 필요 없을듯
 */

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf =new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bf.readLine());

        HashMap<Integer, Integer> vToIdx = new HashMap<>();
        int idx = 0;
        List<List<Integer>> points = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            StringTokenizer input = new StringTokenizer(bf.readLine());

            int x = Integer.parseInt(input.nextToken());
            int y = Integer.parseInt(input.nextToken());

            if (!vToIdx.containsKey(x)) {
                vToIdx.put(x, idx++);
                points.add(new ArrayList<>());
            }

            points.get(vToIdx.get(x)).add(y);
        }

        System.out.println(solution(N, points));
    }

    static int solution(int N, List<List<Integer>> points) {
        int result = 0;

        for (int i = 0; i < points.size(); i++) {
            for (int j = i + 1; j < points.size(); j++) {
                int cnt = 0;
                for (int y : points.get(i)) {
                    if (points.get(j).contains(y)) cnt++;
                }
                result += (cnt - 1) * cnt / 2;
            }
        }

        return result;
    }
}