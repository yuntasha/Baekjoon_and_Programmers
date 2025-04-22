#include <stdio.h>
#include <string.h>
#include <stdlib.h>

#define MAX_STACK_SIZE 30

int Test[MAX_STACK_SIZE][14]; //[k층][n호]//
void Check(int k, int n) {
   for (int al = 1; al <= k; al++) {
      for (int bl = 1; bl <= n; bl++) {
         Test[al][bl] = Test[al][(bl) - 1] + Test[(al) - 1][bl];
      }
   }
}

int main() {
   for (int i = 0; i < MAX_STACK_SIZE; i++) {
      Test[i][0] = 1; // 모든 층의 1호는 1명이 산다.//
   }
   for (int c = 1; c < 14; c++) {
      Test[0][c] = (c + 1);//0층의 i호에는 i명이 산다.//
   }
   int a, b, t;
   scanf("%d", &t);
   while (t != 0) {
      scanf("%d", &a);
      scanf("%d", &b);
      Check(a, b-1);
      printf("%d\n", Test[a][b - 1]);
      t--;
   }
}