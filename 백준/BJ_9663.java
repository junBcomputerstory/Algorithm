import java.util.*;
import java.io.*;

public class BJ_9663 {

    public static int score=0;
    public static int[] line;
    public static int n;

    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        line = new int[n];
        //N*N 맵에 N개를 놔야하기 때문에 카운트가 곧 가로나 세로 둘중하나의 index라고 생각하면 1차배열이 가능

        Nqueen(0);
        System.out.println(score);

    }

    public static void Nqueen(int x){
        if(x==n){
            score++;
            return;
        }

        for(int i=0; i<n; i++){
            line[x] = i;

            if(possible(x)){
                Nqueen(x+1);
            }
        }
    }
    public static boolean possible(int x){
        for(int i=0; i<x; i++){
            if(line[x]==line[i])
                return false;
            if(Math.abs(i-x)==Math.abs(line[x]-line[i]))
                return false;
        }

        return true;
    }

}
