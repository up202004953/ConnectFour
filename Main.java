package ConnectFour;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);

        int w = stdin.nextInt();
        int h = stdin.nextInt();

        stdin.nextLine();

        char[][] m = new char[w][h];

        for (int i = h-1; i >= 0; i--) {
            String line = stdin.nextLine();
            for (int j = 0; j < w; j++) {
                m[j][i] = line.charAt(j);
            }
        }

        Board test = new Board(m,w,h);

        System.out.println(test.getUtility());
    }
}