
import java.io.*;
import java.util.*;
/*풀이: bfs를 사용하여 상어의 시작점으로 부터 맵을 탐색한다 이때 방문처리용 map은 먹은생선갯수로
 갱신한다( 생선을 새로 먹지 않은 이상 이길을 다시 사용할 이유가 없음으로. 생선을 먹고나서는 다시 사용할 수도있음)
 같은 시간이 걸리는 거리의 생선들을 전부 pq에 담는다. 가장위면서 가장왼쪽인 생선을 먹고 큐와 우선순위 큐 둘다 초기화
*/


public class BJ_16236 {
    public static int[] dx = new int[]{-1,0,1,0};
    public static int[] dy = new int[]{0,1,0,-1};
    public static int[][] map;
    public static int[][] visited;//방문처리를 위한 맵
    public static int fishes=0;//총 생선갯수
    public static int eated = 0;//먹은 생선갯수
    public static int N;
    public static Queue<Point> qu = new LinkedList<>();//진행방향 bfs큐
    public static PriorityQueue<Point> pq = new PriorityQueue<>();//먹을수있는생선 우선순위큐

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        visited = new int[N][N];


        for(int i=0;i<N;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());

            for(int j=0;j<N;j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                visited[i][j] = -1;
                if(map[i][j]==9) { //상어일 경우 qu에 현재 좌표를 담고 방문맵과 맵을 갱신
                    qu.add( new Point(i, j, 0));
                    visited[i][j]=0;
                    map[i][j]=0;
                    continue;
                }
                if(map[i][j]>=1&&map[i][j]<=6){ //생선이 있을경우 생선 갯수 추가
                   fishes++;
                }
            }
        }

        Shark shark = new Shark(2,2,0);//상어생성 크기,필요경험치,생성시간을 저장해준다

        while(!qu.isEmpty()||!pq.isEmpty()){
            if(fishes==0)//모든생선을 먹었지만 더돌수있는경우 시간복잡도를위해 종료조건을 건다
                break;

            if(!pq.isEmpty()) {//먹을 수 있는 생선이 있을 경우

                if (qu.isEmpty()) { //갈수있는길에 모두 생선이 놓인경우
                    fishing(shark);
                    continue;
                }

                Point tmp = qu.peek();//갈수있는길의 시간이 현재 생선을 먹을때 시간보다 적으면 길을 더확인해야함
                int x=tmp.x;
                int y=tmp.y;

                if(pq.peek().time==tmp.time){//이동 할 길의 걸린 시간과 생선 까지의 걸린 시간이 같으면 이후에 담기는 생선은 어차피 더 먼 생선이다.
                    fishing(shark);
                    continue;
                }
            }

            Point tmp = qu.poll();
            int x=tmp.x;
            int y=tmp.y;



            for(int i=0;i<4;i++){
                if(x+dx[i]<0||x+dx[i]>=N||y+dy[i]<0||y+dy[i]>=N||visited[x+dx[i]][y+dy[i]]>=eated||map[x+dx[i]][y+dy[i]]>shark.level)
                    continue;
                visited[x+dx[i]][y+dy[i]]=eated;
                if(map[x+dx[i]][y+dy[i]]!=0&&map[x+dx[i]][y+dy[i]]<shark.level){
                    pq.add(new Point(x+dx[i],y+dy[i],tmp.time+1));
                    continue;
                }
                qu.add(new Point(x+dx[i],y+dy[i],tmp.time+1));

            }

        }
        System.out.println(shark.age);

    }
    public static void fishing(Shark shark){//생선 먹기
        Point tmp = pq.poll();
        shark.age= tmp.time;
        shark.eat();
        eated++;
        fishes--;
        pq.clear();
        qu.clear();
        qu.add(tmp);
        map[tmp.x][tmp.y]=0;
        visited[tmp.x][tmp.y]=eated;
    }
    public static class Shark{
        int level,exp,age;

        public Shark(int level,int exp,int age){
            this.level=level;
            this.exp=exp;
            this.age=age;
        }
        public void eat(){
            --exp;
            if(exp==0){
                ++level;
                exp=level;
            }
        }
    }
    public static class Point implements Comparable<Point>{
        int x;
        int y;
        int time;

        public Point(int x,int y,int time){
            this.x=x;
            this.y=y;
            this.time=time;
        }

        @Override
        public int compareTo(Point o){
            if(this.x==o.x)
                return this.y-o.y;
            return this.x-o.x;

        }

    }
}
