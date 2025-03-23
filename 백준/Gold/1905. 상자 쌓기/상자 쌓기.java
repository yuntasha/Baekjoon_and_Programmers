/*
2차원으로 사각형 만들고 이전 것들과 겹치는지 확인
결국 가장 높은 애들 위에서 높이 만들기
 */

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {

        int Lx = read();
        int Ly = read();
        int N = read();

        List<Box> boxes = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            int lx = read();
            int ly = read();
            int lz = read();
            int px = read();
            int py = read();

            boxes.add(new Box(px, px + lx, py, py + ly, lz));
        }

        System.out.println(solution(N, boxes));
    }

    public static int solution(int N, List<Box> boxes) {
        int result = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < i; j++) {
                boxes.get(i).setBox(boxes.get(j));
            }

            result = Math.max(result, boxes.get(i).h + boxes.get(i).z);
        }

        return result;
    }

    static int read() throws IOException {
        int n = 0;
        int c;
        while ((c = System.in.read()) >= '0') {
            n = (n << 3) + (n << 1) + (c & 15);
        }

        return n;
    }

    public static class Box {
        int x1;
        int x2;
        int y1;
        int y2;
        int z;
        int h;

        public Box(int x1, int x2, int y1, int y2, int z) {
            this.x1 = x1;
            this.x2 = x2;
            this.y1 = y1;
            this.y2 = y2;
            this.z = z;
            this.h = 0;
        }

        public void setBox(Box box) {
            if (isO(box)) {
                this.h = Math.max(box.h + box.z, this.h);
            }
        }

        public boolean isO(Box box) {
            return this.x1 < box.x2 && box.x1 < this.x2 && this.y1 < box.y2 && box.y1 < this.y2;
        }
    }
}