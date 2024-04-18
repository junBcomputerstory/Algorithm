import java.io.*;
import java.util.*;

public class BJ_2206 {
    public static int[] dx = new int[]{1,-1,0,0};
    public static int[] dy = new int[]{0,0,1,-1};
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] map = new int [N+1][M+1];
        int[][][] visited = new int [N+1][M+1][2];

        String line;
        for(int i=1;i<=N;i++){
            line = br.readLine();
            for(int j=1;j<=M;j++){
                map[i][j]=line.charAt(j-1)-'0';
            }
        }

        Queue<Point> qu = new LinkedList<>();
        qu.add(new Point(1,1,0));
        visited[1][1][0]=1;

        while(!qu.isEmpty()){
            Point tmp = qu.poll();
            int x = tmp.x;
            int y = tmp.y;


            for(int i=0; i<4; i++){
                if(x+dx[i]<=0||x+dx[i]>N||y+dy[i]<=0||y+dy[i]>M)
                    continue;
                if(map[x+dx[i]][y+dy[i]]==1&&tmp.block==1)
                    continue;
                if(map[x+dx[i]][y+dy[i]]==1&&tmp.block==0&&visited[x+dx[i]][y+dy[i]][1]==0) {
                    visited[x+dx[i]][y+dy[i]][1]=visited[x][y][0]+1;
                    qu.add(new Point(x + dx[i], y + dy[i], 1));
                    continue;
                }
                if(map[x+dx[i]][y+dy[i]]==1&&tmp.block==0&&visited[x+dx[i]][y+dy[i]][1]!=0)
                    continue;
                if(visited[x+dx[i]][y+dy[i]][tmp.block]!=0)
                    continue;

                qu.add(new Point(x + dx[i], y + dy[i], tmp.block));
                visited[x+dx[i]][y+dy[i]][tmp.block]=visited[x][y][tmp.block]+1;
            }

        }
    if(visited[N][M][0]==0&&visited[N][M][1]==0){
        System.out.println("-1");
    }
    else if(visited[N][M][0]!=0&&visited[N][M][1]==0){
        System.out.println(visited[N][M][0]);
    }
    else if(visited[N][M][0]==0&&visited[N][M][1]!=0){
        System.out.println(visited[N][M][1]);
    }
    else{
        System.out.println(Math.min(visited[N][M][1],visited[N][M][0]));
    }


    }

    public static class Point{
        int x,y,block;


        public Point(int x,int y,int block){
            this.x=x;
            this.y=y;
            this.block=block;
        }
    }
}
