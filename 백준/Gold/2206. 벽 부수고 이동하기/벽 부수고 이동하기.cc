// 스터디 다른 사람꺼 봐주는 용입니다. 이거 아니에요 제꺼

#include <iostream>
#include <queue>
#include <vector>
#include <algorithm>
using namespace std;

struct MoveInfo {
	int x, y, broken, dist;
};

bool isIn(int x, int y, int N, int M)
{
	return x >= 0 && x < N && y >= 0 && y < M;
}

void breakableBfs(const int N, const int M, const vector<vector<int>>& board, MoveInfo start)
{
	int dir = 4;
	int dx[] = { -1, 1, 0, 0 };
	int dy[] = { 0, 0, -1, 1 };

	queue<MoveInfo> q;
	q.push(start);
	vector<vector<vector<bool>>> visited(N, vector<vector<bool>>(M, vector<bool>(2, false)));
	// 시작점 방문처리
	visited[start.x][start.y][start.broken] = true;

	while (!q.empty())
	{
		MoveInfo curInfo = q.front();
		q.pop();

		// 도착지점에 도달한 경우 거리 반환
		if (curInfo.x == N - 1 && curInfo.y == M - 1) {
			cout << curInfo.dist;
			return;
		}

		for (int i = 0; i < dir; i++)
		{
			int nx = curInfo.x + dx[i];
			int ny = curInfo.y + dy[i];

			// 범위 밖인 경우 패스
			if (isIn(nx, ny, N, M) == false)
				continue;

			// 벽이고, 부쉈을 때 방문한 적이 없는 경우
			if (board[nx][ny] == 1 && curInfo.broken == 0 && !visited[nx][ny][1]) {
				visited[nx][ny][1] = true; // 벽을 부순 상태로 방문 처리
				q.push({ nx, ny, 1, curInfo.dist + 1 });
			}

			// 빈 칸이고, 해당 상태에서 방문한 적 없는 경우
			if (board[nx][ny] == 0 && !visited[nx][ny][curInfo.broken]) {
				visited[nx][ny][curInfo.broken] = true; // 현재 상태로 방문 처리
				q.push({ nx, ny, curInfo.broken, curInfo.dist + 1 });
			}
		}
	}

	// 도착지점에 도달하지 못한 경우
	cout << -1;
}

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);

	int N, M;
	cin >> N >> M;

	vector<vector<int>> board(N, vector<int>(M, 0));
    
    for (int i = 0; i < N; i++)
        for (int j = 0; j < M; j++)
        {
            char ch = std::cin.get();
            while (ch == '\n') ch = std::cin.get();
            board[i][j] = ch - '0';
        }

	breakableBfs(N, M, board, { 0, 0, 0, 1 });

	return 0;
}