/*
입력을 그냥 문자열로 받아서 직접 꺼내는게 나을 것 같다

'='
전은 앞 식, 뒤는 뒤 식

'+'
나오면 해당 부분 저장하고 다시 입력받기

'원소기호'
대문자 나오면 원소기호 시작

'개수'
숫자 안나오면 1로 처리

'합쳐진 것 개수'
합쳐진 것 개수는 처음부터 숫자가 나오면 바로 사용

= 처리
처음에는 -1이였다가 = 나오면 1로 변환

앞의 숫자 처리
원소가 나오지 않은 상태에서 숫자가 나오면 끝까지 읽고 변경

원소 찾기
대문자가 앞에오고 소문자 계속 나오는거 읽기

원소 개수 찾기
원소가 나오고 나오는 숫자 모두 가져와서 읽기

메서드로 움직이면 좋을 것 같습니다...
기본적으로 해시맵 사용하면 좋을 것 같습니다...

빈 칸은 모두 무시한다가 전재가 깔려야합니다.
그냥 replaceAll로 다 없애기

숫자 입력받기
- 숫자인 애들까지 최대한 끌어모아서 읽기
- 없으면 기본값 1로 그냥 가져옴

원소 입력받기
- 대문자 한번 읽고 소문자 나오는 구간까지 모두 읽기

+ 기호인지 판정

= 기호인지 판정

코드 설계

앞 숫자 입력받기
while(인덱스 범위 넘지 않는지)
    문자 입력받기
    원소 개수 입력받기
    + 판정
        앞 숫자 입력받기
    = 판정
        = 숫자 1로 변경
        앞 숫자 입력받기

앞 숫자 pn
= 숫자 m

 */

import java.io.*;
import java.util.*;

public class Main {

    static char PLUS = '+';
    static char EQUAL = '=';
    static char A = 'A';
    static char Z = 'Z';

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        String input;
        StringBuilder output = new StringBuilder();

        int n = 1;
        boolean isRemove = false;

        while (!(input = bf.readLine()).equals("#")) {
            input = input.replaceAll(" ", "");
            Solution s = new Solution(input.toCharArray());
            String result = s.solution();

            if (result.isEmpty()) {
                output.append("\n").append(balance(n));
                isRemove = false;
            } else {
                output.append("\n").append(unbalance(n)).append("\n").append(result);
                isRemove = true;
            }
            n++;
        }

        System.out.println(output.substring(1, output.length() - (isRemove ? 1 : 0)));
    }

    static String balance(int t) {
        return "Equation " + t + " is balanced.";
    }

    static String unbalance(int t) {
        return "Equation " + t + " is unbalanced.";
    }

    static class Solution {
        int m;
        int atomCount;
        char[] cmd;
        int idx;
        TreeMap<String, Integer> map = new TreeMap<>();

        Solution(char[] cmd) {
            this.m = -1;
            this.atomCount = 1;
            this.cmd = cmd;
            this.idx = 0;
        }

        String solution() {
            int atomCount = getNum();

            while (isIn()) {
                String atom = getAtom();
                int cnt = getNum();

                map.put(atom, map.getOrDefault(atom, 0) + atomCount * cnt * m);

                if (isPlus()) {
                    idx++;
                    atomCount = getNum();
                }
                if (isEqual()) {
                    idx++;
                    atomCount = getNum();
                    m = 1;
                }
            }

            return getOutput();
        }

        String getOutput() {
            StringBuilder output = new StringBuilder();

            for (String atom : map.keySet()) {
                if (map.get(atom) < 0) {
                    output.append(destory(atom, map.get(atom))).append("\n");
                }

                if (map.get(atom) > 0) {
                    output.append(create(atom, map.get(atom))).append("\n");
                }
            }

            return output.toString();
        }

        String destory(String atom, int count) {
            if (count == -1) return "You have destroyed " + -count + " atom of " + atom + ".";
            return "You have destroyed " + -count + " atoms of " + atom + ".";
        }

        String create(String atom, int count) {
            if (count == 1) return "You have created " + count + " atom of " + atom + ".";
            return "You have created " + count + " atoms of " + atom + ".";
        }

        String getAtom() {
            StringBuilder result = new StringBuilder();

            if (!isBig()) return null;

            result.append(cmd[idx++]);

            while(isSmall()) {
                result.append(cmd[idx++]);
            }

            return result.toString();
        }

        int getNum() {
            int i = 0;
            while (isNum()) {
                i = (i << 3) + (i << 1) + (cmd[idx++] & 15);
            }

            return Math.max(i, 1);
        }

        boolean isEqual() {
            return isIn() && cmd[idx] == EQUAL;
        }

        boolean isPlus() {
            return isIn() && cmd[idx] == PLUS;
        }

        boolean isSmall() {
            return isIn() && 'a' <= cmd[idx] && cmd[idx] <= 'z';
        }

        boolean isBig() {
            return isIn() && A <= cmd[idx] && cmd[idx] <= Z;
        }

        boolean isNum() {
            return isIn() && '0' <= cmd[idx] && cmd[idx] <= '9';
        }

        boolean isIn() {
            return idx < cmd.length;
        }
    }
}