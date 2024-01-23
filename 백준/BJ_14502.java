import java.io.*;
import java.util.*;

public class BJ_14502 {
    public static int answer=0;
    public static int n;
    public static int m;
    public static ArrayList<int[]> virus=new ArrayList<>();
    public static int[][] map;
    public static int[][] visited;
    public static int[] dx={1,-1,0,0};
    public static int[] dy={0,0,1,-1};

    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        visited = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                if (map[i][j] == 2)
                    virus.add(new int[]{i,j});
            }
        }

        make_wall(0);
        System.out.println(answer);
    }
    public static void spread() {
        Queue<int[]> qu=new LinkedList<>();
        int[][] tmp = new int[n][m];

        for(int i=0; i<n; i++)
            tmp[i] = map[i].clone();



        qu.addAll(virus);

        while(!qu.isEmpty()) {
            int[] point = qu.poll();

            for(int i=0; i<4; i++) {
                if(point[0]+dx[i]>=0&&point[0]+dx[i]<n&&point[1]+dy[i]>=0&&point[1]+dy[i]<m&&tmp[point[0]+dx[i]][point[1]+dy[i]]==0) {
                    qu.add(new int[]{point[0]+dx[i],point[1]+dy[i]});
                    tmp[point[0]+dx[i]][point[1]+dy[i]]=2;
                }
            }
        }


        int cnt=0;
        for(int i=0; i<n; i++) {
            for (int j = 0; j < m; j++) {

                if(tmp[i][j]==0)
                    cnt++;

            }
        }
        answer=Math.max(answer,cnt);
    }
    public static void make_wall(int cnt){
        if(cnt==3) {
            spread();
            return;
        }

        for(int i=0; i<n; i++) {

            for (int j = 0; j < m; j++) {

                if(map[i][j]==0) {
                    map[i][j]=1;
                    make_wall(cnt+1);
                    map[i][j]=0;
                }
                //이렇게 돌경우 방문했던 지점을 다시 체크하게 되며 성능이 안좋아지는데 어떻게 나아지게 할수있을까..?
                //초기에 0인 벽을 놓을수 있는 좌표를 리스트에 담아놓고 콤비네이션으로 하는 방법을 쓰면 중복되는 경우를 없앴수 있다.
            }
        }

    }
}

