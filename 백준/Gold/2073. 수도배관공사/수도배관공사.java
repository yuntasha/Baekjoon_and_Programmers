/*
수도관을 설치해서 D거리에 있는 곳에서 물을 끌어오기로
근처 인간 마을에서 P개의 파이프를 받음
각각은 길이 L과 용량 C로 나타낼 수 있음
용량은 파이프의 용량의 최소값
길이는 파이프 길이의 합
정확히 파이프 길이를 D로 만들 경우 가능한 최대 수도관 용량
DP로 가도 될듯
D까지의 거리가 중요

파이프의 개수 P <= 350
D의 최대 거리 10만
슬라이딩 윈도우 느낌으로 가자
 */

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer input = new StringTokenizer(bf.readLine());

        int D = Integer.parseInt(input.nextToken());
        int P = Integer.parseInt(input.nextToken());

        int[][] pipes = new int[P][];

        for (int i = 0; i < P; i++) {
            pipes[i] = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        System.out.println(solution(D, P, pipes));
    }

    static int solution(int D, int P, int[][] pipes) {
        int[] window = new int[D + 1];

        for (int[] pipe : pipes) {
            for (int i = D; i > pipe[0]; i--) {
                window[i] = Math.max(window[i], Math.min(window[i - pipe[0]], pipe[1]));
            }

            window[pipe[0]] = Math.max(window[pipe[0]], pipe[1]);
        }

        return window[D];
    }
}