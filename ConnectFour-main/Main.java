import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);

        int w = stdin.nextInt();
        int h = stdin.nextInt();

        Board board = new Board(w,h);

        System.out.print("Which algorithm do you want? Min-Max (1), Alfa-Beta (2) or MCTS (3)? ");
        int algorithm = stdin.nextInt();

        System.out.print("Do you want to start first? (yes or no) ");
        if (stdin.next().equals("yes")) Game.start(board, PlayerType.Player, stdin, algorithm);
        else Game.start(board, PlayerType.Computer, stdin, algorithm);

    }
}