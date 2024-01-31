import java.io.*;
import java.util.*;

public class BJ_1987 {
    public static int answer=0;
    public static int[] dx=new int[]{0,0,1,-1};
    public static int[] dy=new int[]{1,-1,0,0};
    public static int R;
    public static int C;
    public static String[] lines;
    public static Set<Character> dic=new HashSet<>();
    public static int[][] visited;

    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        lines=new String[R];
        visited=new int[R][C];

        for(int i=0;i<R;i++)
            lines[i] = br.readLine();

        visited[0][0]=1;
        dfs(0,0,0);
        System.out.println(answer);


    }
    public static void dfs(int count,int x,int y){
        dic.add(lines[x].charAt(y));

        if(dic.size()==count){
            answer=Math.max(answer,count);
            return;
        }

        count++;

        for(int i=0;i<4;i++){
            if(x+dx[i]>=0 && x+dx[i]<R && y+dy[i]>=0 && y+dy[i]<C && visited[x+dx[i]][y+dy[i]]==0) {
                visited[x+dx[i]][y+dy[i]]=1;
                dfs(count, x + dx[i], y + dy[i]);
                visited[x+dx[i]][y+dy[i]]=0;
            }
        }
        answer=Math.max(answer,count);
        dic.remove(lines[x].charAt(y));



    }

}
