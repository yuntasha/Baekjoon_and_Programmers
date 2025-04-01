import sys
input = sys.stdin.readline

N = int(input())
maps = []
for _ in range(N):
    maps.append(list(map(int,input().split())))
results = []
for line in maps:
    result = 0
    for node in line:
        result = result|node
    results.append(result)
print(*results)