
//백준 16236 아기 상어
import java.awt.Point;
import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class Main {
    static int n;
    static int[][] fishBowl;
    static boolean[][] visited;
    static Point shark;
    static int babyShark = 2;
    static int time = 0;
    static boolean flag = true;
    static int eatCnt=0;

    static class Point {
        int r;
        int c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        fishBowl = new int[n][n];

        shark = new Point(-1, -1);
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                fishBowl[i][j] = Integer.parseInt(st.nextToken());
                if (fishBowl[i][j] == 9) {
                    shark.r = i;
                    shark.c = j;
                    fishBowl[i][j] = 0;
                }
            }
        }
        while (flag) {
            bfs(shark.r, shark.c);
//            for(int k=0; k<n; k++){
//                System.out.println(Arrays.toString(fishBowl[k]));
//
//            }
//            System.out.println("size: " + babyShark + " cnt: " + eatCnt + " time: " + time);
//            System.out.println();
        }
        System.out.println(time);
    }

    public static void bfs(int r, int c) {
//      System.out.println(time);
        Queue<Point> queue = new LinkedList<>();
        visited = new boolean[n][n];

        int[] dr = { -1, 0, 0, 1 };
        int[] dc = { 0, -1, 1, 0 };
        int dist = 1;

        queue.add(new Point(r, c));
        visited[r][c] = true;

        boolean eatFlag = false;
        int er = 100;
        int ec = 100;

        while (!queue.isEmpty()) {
            int qSize = queue.size();
            while (qSize-- > 0) {
                Point p = queue.poll();

                for (int d = 0; d < 4; d++) {
                    int nr = p.r + dr[d];
                    int nc = p.c + dc[d];
                    if (nr < 0 || nc < 0 || nr >= n || nc >= n || visited[nr][nc] || fishBowl[nr][nc] > babyShark) {
                        continue;
                    }
                    if (fishBowl[nr][nc] < babyShark && fishBowl[nr][nc] != 0) {
                        if(nr < er){
                            er = nr;
                            ec = nc;
                        }else if(nr == er){
                            if(nc < ec){
                                er = nr;
                                ec = nc;
                            }
                        }
                    }
                    if (fishBowl[nr][nc] == 0 || fishBowl[nr][nc] == babyShark) {
                        queue.add(new Point(nr, nc));
                        visited[nr][nc] = true;
                    }
                }
            }

            if(er != 100){
                fishBowl[er][ec] = 0;
                shark.r = er;
                shark.c = ec;
                time += dist;
                flag = true;
                eatCnt++;
                if(eatCnt == babyShark) {
                    babyShark++;
                    eatCnt=0;
                }
                return;
            }

            dist++;
        }
        flag = false;
        return;
    }

}
