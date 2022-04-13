import java.util.Scanner;

public class Game {
    public static void start(Board init, PlayerType pt, Scanner stdin, int algorithm) { //algorithm = 1 (MinMax) ; algorithm = 2 (MCTS)
        System.out.println(init);
        if (pt.getName().equals(PlayerType.Player.getName())) {
            System.out.print("Which column do you want to play? ");
            int col = stdin.nextInt() - 1;
            while (col >= init.getWidth() || init.getHigh()[col] >= init.getHeight()) {
                System.out.println("You can't play in the column: "+(col+1)+"\n");

                System.out.print("Which column do you want to play? ");
                col = stdin.nextInt() - 1;
            }

            Board next = init.insert(col, pt);
            int t = next.terminal(pt);
            if (t == -512 || t == 0 || t == 512) end(next, t);
            else start(next, PlayerType.Computer, stdin, algorithm);
        }
        else {
            Board next;
            if (algorithm == 1) next = MinMax.run(init);
            else if (algorithm == 2) next = AlfaBeta.run(init);
            else next = MCTS.run(init);

            int t = next.terminal(pt);
            if (t == -512 || t == 0 || t == 512) end(next, t);
            else start(next, PlayerType.Player, stdin, algorithm);
        }
    }

    private static void end(Board last, int t) {
        System.out.println(last);
        if (t == -512) System.out.println("You won");
        else if (t == 0) System.out.println("Draw");
        else if (t == 512) System.out.println("You lost");
    }
}
