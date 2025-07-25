# [Gold I] 그래프 복원 - 2278 

[문제 링크](https://www.acmicpc.net/problem/2278) 

### 성능 요약

메모리: 16564 KB, 시간: 156 ms

### 분류

해 구성하기, 데이크스트라, 플로이드–워셜, 그래프 이론, 그리디 알고리즘, 최단 경로

### 제출 일자

2025년 7월 22일 12:32:38

### 문제 설명

<p>N(1 ≤ N ≤ 100)개의 정점과 M(1 ≤ M ≤ 1,000)개의 간선을 갖는 연결된 무향 그래프가 있다. 이 그래프의 간선에 대한 정보는 전혀 알 수 없지만, 이 그래프의 임의의 서로 다른 두 정점 사이의 최단거리가 모두 알려져 있다. 이를 토대로 원래의 그래프를 복원하라. 정점의 번호는 1부터 N까지 하나씩 매겨져 있다.</p>

### 입력 

 <p>첫째 줄에 두 자연수 N, M이 주어진다. 다음 N-1개의 줄에 두 정점 사이의 최단거리가 주어지며, 여기서 i번째 줄의 정보는 정점 i와 다른 정점과의 최단 거리이고, 정점 i+1, i+2, ..., N 까지의 최단 거리가 공백을 사이에 두고 주어진다. 두 정점 사이의 최단 거리는 500보다 작거나 같은 자연수이다.</p>

### 출력 

 <p>첫째 줄에 그래프를 복원할 수 있으면 1을, 없으면 0을 출력한다. 복원할 수 있을 때, 다음 M개의 줄에 각 간선을 a, b, c의 형태로 출력한다. 이는 정점 a, b를 연결하는 가중치 c인 간선이 있다는 의미이다. a와 b는 같지 않아야 하고, c는 500보다 작거나 같은 자연수가 되어야 한다.</p>

