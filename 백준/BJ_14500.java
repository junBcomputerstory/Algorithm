import java.io.*;
import java.util.*;
public class BJ_14500 {

    public static int shape_x[][]=new int[][]{{0,1,1},{0,0,0},{0,1,0},{1,2,2},{1,1,2}};
    public static int shape_y[][]=new int[][]{{1,0,1},{1,2,3},{1,1,2},{0,0,1},{0,1,1}};

    public static int max=0;



    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int [][] map = new int[n][m];

        for(int i=0;i<n;i++){
            st=new StringTokenizer(br.readLine());
            for(int j=0;j<m;j++)
                map[i][j]=Integer.parseInt(st.nextToken());
        }


        for(int i=0;i<5;i++){
            check(i,map,map.length,map[0].length);
            flip_map(map, map.length, map[0].length);
        }




        for(int i=0;i<4;i++){
            map=turn_map(map,map.length,map[0].length);
            flip_map(map, map.length, map[0].length);
            for(int j=1;j<5;j++)
                check(j,map, map.length,map[0].length);

        }
        System.out.println(max);


    }

    public static void check(int index,int[][] map,int N,int M){
        int sum = 0;
        int flag = 0;

        for(int i=0; i<N; i++){

            for(int j=0; j<M; j++){

                sum = map[i][j];
                flag = 0;

                for(int a=0;a<3;a++){
                    if(i+shape_x[index][a]>=0&&i+shape_x[index][a]<N&&j+shape_y[index][a]>=0&&j+shape_y[index][a]<M) {
                        sum += map[i + shape_x[index][a]][j + shape_y[index][a]];

                    }
                    else {
                        flag = 1;
                        break;
                    }
                }

                if(flag==0) {
                    max = Math.max(max, sum);
                }

            }
        }
    }

    public static int[][] turn_map(int[][] map,int N,int M){

        int[][] tmp = new int[M][N];

        for(int i=0;i<N;i++){

            for(int j=0;j<M;j++){

                tmp[j][N-1-i] = map[i][j];

            }

        }

        return tmp;
    }

    public static void flip_map(int[][] map,int N,int M){
        int[][] tmp = new int[N][M];

        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++)
                tmp[i][M-1-j]=map[i][j];
        }

        for(int i=1;i<5;i++)
            check(i,tmp,N,M);

        for(int i=0;i<M;i++){
            for(int j=0;j<N;j++)
                tmp[N-1-j][i]=map[j][i];
        }

        for(int i=1;i<5;i++)
            check(i,tmp,N,M);




    }

}
