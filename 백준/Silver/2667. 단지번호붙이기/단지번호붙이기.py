from collections import deque
import heapq

n = int(input())

miro=[]

for _ in range(n):
    miro.append(list(map(int,input())))
    
dx=[0,1,0,-1]
dy=[1,0,-1,0]
cnt=0
heap = []

def BFSmiro(x,y):
    cnts=1
    xq=deque()
    yq=deque()
    xq.append(x)
    yq.append(y)
    miro[x][y]=3
    while xq:
        x=xq.popleft()
        y=yq.popleft()
        
        for i in range(4):
            xx=x+dx[i]
            yy=y+dy[i]
            
            if xx<0 or xx==n or yy<0 or yy==n:
                continue
            if miro[xx][yy]==1:
                miro[xx][yy]=3
                xq.append(xx)
                yq.append(yy)
                cnts+=1
    return cnts
        

for i in range(n):
    for j in range(n):
        if miro[i][j]==1:
            Y=BFSmiro(i,j)
            heapq.heappush(heap,Y)
            cnt+=1
            
print(cnt)
for _ in range(cnt):
    a=heapq.heappop(heap)
    print(a)