import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);
	PlayerType pt = PlayerType.Computer;

	Board b = new Board(1, 1);

        int width = stdin.nextInt();
        int height = stdin.nextInt();
	int limit = stdin.nextInt();


	while(!b.isFinal()) {

	        stdin.nextLine();

		int h = height;
		int w = width;
	        char[][] m = new char[w][h];

	        for (int i = h-1; i >= 0; i--) {
	            String line = stdin.nextLine();
	            for (int j = 0; j < w; j++) {
	                m[j][i] = line.charAt(j);
	            }
	        }

	        Board test = new Board(m,w,h);

		b = MCTS.runMCTS(test, pt, limit);
		System.out.println(b);

//	        System.out.println(test);
//	        System.out.println(test.getUtility());
	}
    }
}
