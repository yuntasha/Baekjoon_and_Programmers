/*
한 선 맞네
그럼 시작점과 끝점 이렇게 만들자
시작점 빠른애부터 처리
앞에서부터 탐색하면서
시작이 설정 끝보다 짧은 경우 더 큰 끝으로 업데이트
시작이 더 큰경우 기존 거리 구해서 결과에 더해주고 새로운 시작
 */

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        int N = getI();

        List<Line> lines = new ArrayList<>(N);

        for (int i = 0; i < N; i++) {
            lines.add(new Line(read(), read()));
        }

        System.out.println(solution(N, lines));
    }

    static int solution(int N, List<Line> lines) {
        int result = 0;

        lines.sort(Comparator.comparingInt(Line::getS));

        int s = -1_000_000_000;
        int e = -1_000_000_000;

        for (Line line : lines) {
            if (line.s <= e) {
                e = Math.max(e, line.e);
            } else {
                result += e - s;
                s = line.s;
                e = line.e;
            }
        }

        return e - s + result;
    }

    static class Line {
        int s;
        int e;

        public Line(int s, int e) {
            this.s = s;
            this.e = e;
        }

        public int getS() {
            return s;
        }
    }

    static int read() throws IOException {
        int n = System.in.read();
        return n == '-' ? -getI(0) : getI(n & 15);
    }

    static int getI(int n) throws IOException {
        int c;
        while ((c = System.in.read()) >= '0') {
            n = (n << 3) + (n << 1) + (c & 15);
        }
        return n;
    }
    static int getI() throws IOException {
        int n = 0;
        int c;
        while ((c = System.in.read()) >= '0') {
            n = (n << 3) + (n << 1) + (c & 15);
        }
        return n;
    }
}