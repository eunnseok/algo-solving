import java.util.*;
import java.io.*;

// BJ #1515 - 수 이어 쓰기
// Strategy:

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str = br.readLine(); // 일부가 지워진 수의 문자열
        int str_idx = 0;

        int c = 0;
        while(true){
            c++;
            String check = Integer.toString(c);
            int check_idx = 0;

            while(str_idx < str.length() && check_idx < check.length()){
                if(check.charAt(check_idx) == str.charAt(str_idx)){
                    str_idx++;
                }
                check_idx++;
            }

            if(str_idx == str.length()){
                System.out.println(c);
                break;
            }
        }


    }
}