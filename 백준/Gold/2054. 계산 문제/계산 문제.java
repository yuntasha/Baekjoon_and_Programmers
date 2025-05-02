/*
최대 9자까지 가능하다
(27)^9 정도인가?
뭐 그냥 완탐해도 괜찮을듯?
덧셈 뺄셈 곱셈임
현재 숫자부터 끝 숫자까지 탐색해서 숫자 만들기

- 숫자 탐색
    - 시작이 0이라면 그냥 0으로 만들고 시작
    - 시작이 0이 아니라면 전체 반복문으로 숫자 만들고 탐색
- 덧셈, 곱셈, 뺄셈
    - 각 숫자에 대해 결과, 문자열을 만들어서 다음 dfs로 넘기자

곱셈부터 처리해야하는구나...
식을 만들고 끝까지 가면 그것을 연산해주는게 맞다...

연산 어떻게 하지?
일단 곱셈은 그냥 진행시키고...
후위 표기식인가 그거로 바꿔야할듯...

어떻게하더라?
* + -밖에 없으니까

아 그냥 곱셈이면 앞 뒤로 전부 곱해주고 +-는 일단 다음에
 */

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        String s = bf.readLine();

        System.out.println(solution(s));
    }

    static String solution(String s) {
        List<String> output = new ArrayList<>();
        dfs(0, new ArrayList<>(), s, output);
        output.sort(Comparator.naturalOrder());
        return output.stream().collect(Collectors.joining("\n"));
    }

    static void dfs(int idx, List<String> ns, String s, List<String> output) {
        if (!ns.isEmpty() && !ns.get(0).equals("+")) return;
        if (idx == s.length()) {
            if (cal(ns) == 2000) {
                output.add(ns.stream().collect(Collectors.joining("")).substring(1));
            }
            return;
        }

        if (s.charAt(idx) == '0') {
            ns.add("+");
            ns.add("0");
            dfs(idx + 1, ns, s, output);
            ns.remove(ns.size() - 1);
            ns.remove(ns.size() - 1);

            ns.add("-");
            ns.add("0");
            dfs(idx + 1, ns, s, output);
            ns.remove(ns.size() - 1);
            ns.remove(ns.size() - 1);

            ns.add("*");
            ns.add("0");
            dfs(idx + 1, ns, s, output);
            ns.remove(ns.size() - 1);
            ns.remove(ns.size() - 1);
            return;
        }

        for (int i = idx + 1; i <= s.length(); i++) {
            String n = s.substring(idx, i);

            ns.add("+");
            ns.add(n);
            dfs(i, ns, s, output);
            ns.remove(ns.size() - 1);
            ns.remove(ns.size() - 1);

            ns.add("-");
            ns.add(n);
            dfs(i, ns, s, output);
            ns.remove(ns.size() - 1);
            ns.remove(ns.size() - 1);

            ns.add("*");
            ns.add(n);
            dfs(i, ns, s, output);
            ns.remove(ns.size() - 1);
            ns.remove(ns.size() - 1);
        }
    }

    static int cal(List<String> ms) {
        ArrayDeque<String> sik = new ArrayDeque<>();

        int idx = 1;

        while (idx < ms.size()) {
            if (ms.get(idx).equals("*")) {
                int n = Integer.parseInt(sik.removeLast());
                n *= Integer.parseInt(ms.get(idx + 1));
                idx++;
                sik.add(String.valueOf(n));
            } else {
                sik.add(ms.get(idx));
            }
            idx++;
        }

        int n = Integer.parseInt(sik.remove());

        while (!sik.isEmpty()) {
            if (sik.remove().equals("+")) {
                n += Integer.parseInt(sik.remove());
            } else {
                n -= Integer.parseInt(sik.remove());
            }
        }

        return n;
    }
}