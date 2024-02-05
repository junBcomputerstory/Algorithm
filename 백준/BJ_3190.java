import java.io.*;
import java.util.*;

public class BJ_3190 {
    static int [][]  map;
    static int [] dx = new int[]{0,1,0,-1};
    static int [] dy = new int[]{1,0,-1,0};
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        map = new int[N+1][N+1];

        int apple = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for(int i=0; i<apple; i++){
            st =new StringTokenizer(br.readLine());
            map[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())]=2;
        }

        map[1][1]=1;
        int inst = Integer.parseInt(br.readLine());
        Map<Integer,Integer> direction =new HashMap<>();
        int num=0;

        for(int i=0;i<inst;i++){
            st = new StringTokenizer(br.readLine());
            num = Integer.parseInt(st.nextToken());

            if(st.nextToken().equals("D"))
                direction.put(num,1);
            else
                direction.put(num,-1);
        }

        Queue<point> tail = new LinkedList<>();
        tail.add(new point(1,1));

        int time=0;
        point tmp;
        int current_x=1;
        int current_y=1;

        int direction_idx=0;

        while(true){
            map[current_x][current_y]=1;

            if(direction.containsKey(time)){
                direction_idx+=direction.get(time);

                if(direction_idx<0)
                    direction_idx = 3;
                else if(direction_idx>3)
                    direction_idx = 0;
            }


            if(current_x+dx[direction_idx]<=0||current_x+dx[direction_idx]>N||current_y+dy[direction_idx]<=0||current_y+dy[direction_idx]>N||map[current_x+dx[direction_idx]][current_y+dy[direction_idx]]==1){
                time++;
                break;
            }

            time++;
            current_x+=dx[direction_idx];
            current_y+=dy[direction_idx];

            tail.add(new point(current_x,current_y));

            if(map[current_x][current_y]==2) {
                continue;
            }

            tmp=tail.poll();
            map[tmp.x][tmp.y]=0;
            map[current_x][current_y]=1;

        }

        System.out.println(time);


    }


}
class point extends Object{
    int x;
    int y;
    public point(int x,int y){
        this.x = x;
        this.y = y;
    }
}

//
//1,3
//1,4
//1,5
//1,6
//1,7
//1,8